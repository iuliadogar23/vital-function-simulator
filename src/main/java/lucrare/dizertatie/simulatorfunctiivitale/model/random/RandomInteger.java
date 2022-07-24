package lucrare.dizertatie.simulatorfunctiivitale.model.random;

public class RandomInteger extends RandomType<Integer>{
    @Override
    public Integer getRandomValue(Integer lowerBound, Integer upperbound) {
        return getRandom().nextInt(upperbound-lowerBound)+lowerBound;
    }
}
