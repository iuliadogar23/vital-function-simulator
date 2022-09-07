package lucrare.dizertatie.simulatorfunctiivitale;

import lombok.RequiredArgsConstructor;
import lucrare.dizertatie.common.exception.MessagingException;
import lucrare.dizertatie.simulatorfunctiivitale.service.SimulatorService;
import lucrare.dizertatie.simulatorfunctiivitale.util.Notificare;
import lucrare.dizertatie.simulatorfunctiivitale.util.Pacient;
import org.springframework.http.MediaType;
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
    private final RabbitMQSender sender;
    public List<SseEmitter> emitters = new CopyOnWriteArrayList<>();
    Pacient pacient;

    @GetMapping
    public ResponseEntity<String> simulateVitalFunctions() {
        return ResponseEntity.ok(simulatorService.generateValuesForVitalFunctions());
    }
//
//    @GetMapping(value = "/subscribe", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public Flux<String> subscribe()
//    {
//        return simulatorService.stream("INIT");
//    }
//
//    @GetMapping("/subscribe")
//    public ResponseEntity subscribe(@RequestParam(required = false) Integer pacientId) {
//        if (pacientId != null)
//            pacient = Pacient.getInstance(pacientId);
//        return ResponseEntity.ok().build();
//    }

    @GetMapping(value = "/subscribe", consumes = MediaType.ALL_VALUE)
    public SseEmitter subscribe(@RequestParam(required = false) String pacientId)
    {
        if (pacientId!=null)
            pacient = Pacient.getInstance(Integer.valueOf(pacientId));

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
        for (SseEmitter emitter: emitters) {
            try {
                emitter.send(SseEmitter.event().name("newsScore").data(pacient.addPacientToNewsReport(news, pacient.getId())));
            } catch (IOException e) {
                emitters.remove(emitter);
                throw new MessagingException("Error dispatching to clients",e);
            }
        }

        Notificare notificare = new Notificare();
        notificare.setDbName("simulator");
        notificare.setObiect(pacient!=null?pacient.addPacientToNewsReport(news, pacient.getId()):news);
        sender.send(notificare);
    }
//
//    @PostMapping("/dispatch-event")
//    public void dispatchEventToClient(@RequestParam String news) {
//        Pacient pacient = Pacient.getInstance(0);
//        for (SseEmitter emitter: emitters) {
//            try {
//                emitter.send(SseEmitter.event().name("newsScore").data(pacient.addPacientToNewsReport(news)));
//            } catch (IOException e) {
//                emitters.remove(emitter);
//                throw new MessagingException("Error dispatching to clients",e);
//            }
//        }
//    }

}
