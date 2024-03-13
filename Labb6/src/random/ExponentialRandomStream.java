
package random;

import java.util.Random;

/**
 * Generates random numbers following an exponential distribution. This class is useful in
 * simulations where you need to model the time between events occurring continuously and
 * independently at a constant average rate, defined by the lambda parameter.
 */

public class ExponentialRandomStream {


    private Random rand;
    private double lambda;

    /**
     * Constructs an ExponentialRandomStream with a specific lambda (rate parameter) and seed.
     * The seed allows for the creation of a reproducible sequence of random numbers.
     *
     * @param lambda The rate parameter of the exponential distribution.
     * @param seed   The initial seed for the random number generator.
     */

    public ExponentialRandomStream(double lambda, long seed) {
        rand = new Random(seed);
        this.lambda = lambda;
    }

    /**
     * Constructs an ExponentialRandomStream with a specific lambda (rate parameter). This
     * constructor uses a randomly chosen seed, resulting in a different sequence of numbers
     * for each instance.
     *
     * @param lambda The rate parameter of the exponential distribution.
     */
    public ExponentialRandomStream(double lambda) {
        rand = new Random();
        this.lambda = lambda;
    }

    /**
     * Generates the next random number following the exponential distribution.
     *
     * @return A randomly generated exponential number.
     */
    public double next() {
        return -Math.log(rand.nextDouble()) / lambda;
    }
}

