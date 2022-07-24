package lucrare.dizertatie.simulatorfunctiivitale;

import lombok.RequiredArgsConstructor;
import lucrare.dizertatie.common.exception.MessagingException;
import lucrare.dizertatie.simulatorfunctiivitale.service.SimulatorService;
import lucrare.dizertatie.simulatorfunctiivitale.util.Pacient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping("/simulator")
@RequiredArgsConstructor
public class SimulatorController {

    private final SimulatorService simulatorService;
    public List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    @GetMapping
    public ResponseEntity<String> simulateVitalFunctions()
    {
        return ResponseEntity.ok(simulatorService.generateValuesForVitalFunctions());
    }

    @GetMapping("/subscribe")
    public SseEmitter subscribe(@RequestParam(required = false) Integer pacientId)
    {
        Pacient pacient;
        if (pacientId!=null)
            pacient = Pacient.getInstance(pacientId);

        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);

        try {
            sseEmitter.send(SseEmitter.event().name("INIT"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new MessagingException("SseEmitter exception", e);
        }
        sseEmitter.onCompletion(() -> emitters.remove(sseEmitter));
        emitters.add(sseEmitter);

        return sseEmitter;
    }

    @PostMapping("/dispatch-event")
    public void dispatchEventToClient(@RequestParam String news) {
        Pacient pacient = Pacient.getInstance(0);
        for (SseEmitter emitter: emitters) {
            try {
                emitter.send(SseEmitter.event().name("newsScore").data(pacient.addPacientToNewsReport(news)));
            } catch (IOException e) {
                emitters.remove(emitter);
                throw new MessagingException("Error dispatching to clients",e);
            }
        }
    }

}
