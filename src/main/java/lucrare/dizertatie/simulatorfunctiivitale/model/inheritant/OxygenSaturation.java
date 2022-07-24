package lucrare.dizertatie.simulatorfunctiivitale.model.inheritant;

import lucrare.dizertatie.simulatorfunctiivitale.model.MeasurementCriteria;
import lucrare.dizertatie.simulatorfunctiivitale.model.random.RandomInteger;

public class OxygenSaturation extends MeasurementCriteria<Integer, RandomInteger> {

    public OxygenSaturation(Integer upperBound, Integer lowerBound, RandomInteger randomType) {
        super(upperBound, lowerBound, randomType);
    }
}
