package lucrare.dizertatie.simulatorfunctiivitale.model;

import lombok.Data;

@Data
public class VitalFunction {

    private boolean oxygenAssistance;

    private Integer oxygenSaturation;

    private Integer pulse;

    private Integer respiratoryRate;

    private Integer systolicBloodPressure;

    private Double temperature;

}
