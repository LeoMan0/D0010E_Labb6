import java.util.Random;


/**
 * Optimizes the checkout process in a store simulation to minimize the number of missed customers.
 *
 * @author Leo Man, Jacky Phuong, Leo Vedberg, Viktor Sundén
 */
public class Optimize implements K {


    // Values from k interface. Change K file to change which method to run.
    private int seed = SEED;
    private int maxCapacityInStore = M;
    private double lambda = L;
    private double minPickTime = LOW_COLLECTION_TIME;
    private double maxPickTime = HIGH_COLLECTION_TIME;
    private double minPayTime = LOW_PAYMENT_TIME;
    private double maxPayTime = HIGH_PAYMENT_TIME;
    private float closeStoreTime = (float) END_TIME;

    /**
     * Runs a single simulation instance with a specified number of checkouts and seed,
     * returning the number of missed customers.
     *
     * @param checkoutsOpen The number of checkouts to be open for this simulation run.
     * @param setSeed       The seed used for random number generation in the simulation.
     * @return The number of customers missed during the simulation.
     */


    /**
     * Main method to test the optimization process with both set and random seeds.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Optimize optimize = new Optimize();

        int test2 = optimize.optimalCheckOutsSetSeed(optimize.seed);
        System.out.println("Minsta antal kassor som ger minimalt antal missade : " + test2);
        int test3 = optimize.optimalCheckOutsRandomSeed();
        System.out.println("Minsta antal kassor som ger minimalt antal missade : " + test3);
    }

    public int runOnce(int checkoutsOpen, int setSeed) {

        RunSim instanceOfRunSim = new RunSim(lambda, setSeed, closeStoreTime, checkoutsOpen, maxCapacityInStore, minPayTime, maxPayTime, minPickTime, maxPickTime, false);

        return instanceOfRunSim.runSimAndGetMissedCustomers();
    }

    /**
     * Finds the optimal number of checkouts to minimize missed customers using a specific seed.
     * This method iteratively increases the number of checkouts to determine the setup that
     * results in the fewest missed customers.
     *
     * @param setSeed The seed used for the simulation to ensure reproducibility.
     * @return The optimal number of checkouts for minimizing missed customers.
     */

    public int optimalCheckOutsSetSeed(int setSeed) {
        int minMissedCustomers = Integer.MAX_VALUE;
        int optimalCheckouts = 1; // Start with 1 checkout as a baseline
        int checkoutsOpen = 1;
        boolean improvement = true;

        while (improvement) {
            int missedCustomers = runOnce(checkoutsOpen, setSeed);

            if (missedCustomers < minMissedCustomers) {
                minMissedCustomers = missedCustomers;
                optimalCheckouts = checkoutsOpen;
                checkoutsOpen++; // Try adding another checkout to see if it improves
            } else {
                improvement = false; // No improvement, so exit the loop
            }
        }

        return optimalCheckouts;
    }

    /**
     * Finds the most stable optimal number of checkout counters needed to minimize the number of missed customers
     * across multiple simulations, each using a different, randomly generated seed.
     *
     * @return The optimal number of checkouts for minimizing missed customers with a random seed.
     */

    public int optimalCheckOutsRandomSeed() {
        Random random = new Random(); // Use a specific seed for reproducibility.
        int optimalRandomSeed = Integer.MAX_VALUE;
        int i = 0;

        while (i < 100) {
            int currentOptimal = optimalCheckOutsSetSeed(random.nextInt());
            if (currentOptimal < optimalRandomSeed) {
                optimalRandomSeed = currentOptimal;
                i = 0;
            } else {
                i++;
            }
            System.out.println(i + " " + optimalRandomSeed);

        }
        return optimalRandomSeed;
    }


}
