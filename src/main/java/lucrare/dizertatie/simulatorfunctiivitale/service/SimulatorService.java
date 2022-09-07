package lucrare.dizertatie.simulatorfunctiivitale.service;

import lucrare.dizertatie.simulatorfunctiivitale.model.VitalFunction;
import lucrare.dizertatie.simulatorfunctiivitale.model.inheritant.*;
import lucrare.dizertatie.simulatorfunctiivitale.model.random.RandomBoolean;
import lucrare.dizertatie.simulatorfunctiivitale.model.random.RandomFloat;
import lucrare.dizertatie.simulatorfunctiivitale.model.random.RandomInteger;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
public class SimulatorService{

    private final String NEWLINE= "\n";
    private final String SETG = "SETG ";

    public String generateValuesForVitalFunctions()
    {

        VitalFunction vitalFunction = generateDangerousVitalFunction();
 
        return printSimulationValues(vitalFunction);
    }

    private VitalFunction generateDangerousVitalFunction()
    {
        VitalFunction vitalFunction = new VitalFunction();
        RandomInteger randomInteger = new RandomInteger();
        RandomBoolean randomBoolean = new RandomBoolean();
        RandomFloat randomFloat = new RandomFloat();
        vitalFunction.setOxygenAssistance(new OxygenAssistance(true, false, randomBoolean).getValueBetween());
        vitalFunction.setOxygenSaturation(new OxygenSaturation(94,90, randomInteger).getValueBetween());
        vitalFunction.setRespiratoryRate(new RespiratoryRate(26,24, randomInteger).getValueBetween());
        vitalFunction.setSystolicBloodPressure(new SystolicBloodPressure(130,  85, randomInteger).getValueBetween());
        vitalFunction.setPulse(new Pulse(130,90, randomInteger).getValueBetween());
        vitalFunction.setTemperature(new Temperature(39.1,34.5, randomFloat).getValueBetween());

        return vitalFunction;
    }

    private VitalFunction generateStableVitalFunction()
    {
        VitalFunction vitalFunction = new VitalFunction();
        RandomInteger randomInteger = new RandomInteger();
        RandomBoolean randomBoolean = new RandomBoolean();
        RandomFloat randomFloat = new RandomFloat();
        vitalFunction.setOxygenAssistance(new OxygenAssistance(true, false, randomBoolean).getValueBetween());
        vitalFunction.setOxygenSaturation(new OxygenSaturation(120,96, randomInteger).getValueBetween());
        vitalFunction.setRespiratoryRate(new RespiratoryRate(20,12, randomInteger).getValueBetween());
        vitalFunction.setSystolicBloodPressure(new SystolicBloodPressure(219, 111, randomInteger).getValueBetween());
        vitalFunction.setPulse(new Pulse(90, 51, randomInteger).getValueBetween());
        vitalFunction.setTemperature(new Temperature(39.1,35.0, randomFloat).getValueBetween());

        return vitalFunction;
    }

    private String printSimulationValues(VitalFunction vitalFunction)
    {
        StringBuilder ruleFile = new StringBuilder();

        ruleFile.append(SETG)
                .append("oxygenSaturation: ").append(vitalFunction.getOxygenSaturation())
                .append(NEWLINE)
                .append(SETG)
                .append("pulse: ").append(vitalFunction.getPulse())
                .append(NEWLINE)
                .append(SETG)
                .append("respiratoryRate: ").append(vitalFunction.getRespiratoryRate())
                .append(NEWLINE)
                .append(SETG)
                .append("systolicBloodPressure: ").append(vitalFunction.getSystolicBloodPressure())
                .append(NEWLINE)
                .append(SETG)
                .append("temperature: ").append(formatDouble(vitalFunction.getTemperature()))
                .append(NEWLINE)
                .append(SETG)
                .append("oxygenAssistance: ").append(vitalFunction.isOxygenAssistance()?1:0)
                .append(NEWLINE);


        return ruleFile.toString();
    }

    private String formatDouble(Double value)
    {
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(1);
        return decimalFormat.format(value);
    }


}
