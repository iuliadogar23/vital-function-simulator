package lucrare.dizertatie.simulatorfunctiivitale.model.inheritant;

import lucrare.dizertatie.simulatorfunctiivitale.model.MeasurementCriteria;
import lucrare.dizertatie.simulatorfunctiivitale.model.random.RandomBoolean;

public class OxygenAssistance  extends MeasurementCriteria<Boolean, RandomBoolean> {
    public OxygenAssistance(Boolean upperBound, Boolean lowerBound, RandomBoolean randomType) {
        super(upperBound, lowerBound, randomType);
    }
}
