package lucrare.dizertatie.simulatorfunctiivitale.model.random;

public class RandomFloat extends RandomType<Double> {

    @Override
    public Double getRandomValue(Double lowerBound, Double upperbound) {
        return lowerBound+getRandom().nextFloat()*(upperbound-lowerBound);
    }
}
