package lucrare.dizertatie.simulatorfunctiivitale.model.inheritant;

import lombok.Data;
import lucrare.dizertatie.simulatorfunctiivitale.model.MeasurementCriteria;
import lucrare.dizertatie.simulatorfunctiivitale.model.random.RandomFloat;

public class Temperature extends MeasurementCriteria<Double, RandomFloat> {
    public Temperature(Double upperBound, Double lowerBound, RandomFloat randomType) {
        super(upperBound, lowerBound, randomType);
    }
}
