
package random;

import java.util.Random;

/**
 * Generates random numbers following a uniform distribution within a specified range. This class
 * is useful in simulations and applications where you need random numbers that are evenly
 * distributed across a certain interval.
 *
 * @author Håkan Jonsson
 */


public class UniformRandomStream {

    private Random rand;
    private double lower, width;

    /**
     * Constructs a UniformRandomStream with specified lower and upper bounds for the distribution
     * range and a seed for the random number generator. Using a seed ensures that the sequence of
     * random numbers generated is reproducible.
     *
     * @param lower The lower bound of the random number distribution range.
     * @param upper The upper bound of the random number distribution range.
     * @param seed  The initial seed for the random number generator.
     */

    public UniformRandomStream(double lower, double upper, long seed) {
        rand = new Random(seed);
        this.lower = lower;
        this.width = upper - lower;
    }

    /**
     * Constructs a UniformRandomStream with specified lower and upper bounds for the distribution
     * range. This constructor uses a randomly chosen seed, resulting in a different sequence of
     * numbers for each instance.
     *
     * @param lower The lower bound of the random number distribution range.
     * @param upper The upper bound of the random number distribution range.
     */

    public UniformRandomStream(double lower, double upper) {
        rand = new Random();
        this.lower = lower;
        this.width = upper - lower;
    }

    /**
     * Generates the next random number within the specified uniform distribution range.
     *
     * @return A randomly generated number within the defined range.
     */
    public double next() {
        return lower + rand.nextDouble() * width;
    }
}

