package lucrare.dizertatie.simulatorfunctiivitale.model;

import lucrare.dizertatie.simulatorfunctiivitale.model.random.RandomType;

public abstract class MeasurementCriteria<T, E extends RandomType<T>> {

    private T upperBound;

    private T lowerBound;

    private E randomType;

    public MeasurementCriteria(T upperBound, T lowerBound, E randomType) {
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
        this.randomType = randomType;
    }

    public T getValueBetween()
    {
        return randomType.getRandomValue(lowerBound, upperBound);
    }

}
