package lucrare.dizertatie.simulatorfunctiivitale.model.random;

public class RandomBoolean extends RandomType<Boolean> {
    @Override
    public Boolean getRandomValue(Boolean lowerBound, Boolean upperbound) {
        return getRandom().nextBoolean();
    }
}
