package lucrare.dizertatie.simulatorfunctiivitale.model.random;

import java.util.Random;

public abstract class RandomType<T> {

    private Random random;

    RandomType() {
        this.random = new Random();
    }

    public Random getRandom() {
        return random;
    }

    public abstract T getRandomValue(T lowerBound, T upperbound);
}
