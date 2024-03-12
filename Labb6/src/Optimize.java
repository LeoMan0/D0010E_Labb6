import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


/**
 * Optimizes the checkout process in a store simulation to minimize the number of missed customers.
 *
 * @author Leo Man, Jacky Phuong, Leo Vedberg, Viktor Sundén
 */


/**
 * Optimizes the checkout process in a store simulation to minimize the number of missed customers.
 * This class provides methods to find the optimal number of checkout counters needed to minimize
 * missed customers using a binary search algorithm and parallel processing for efficiency.
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

//        int test2 = optimize.optimalCheckOutsSetSeed(optimize.seed);
//        System.out.println("Minsta antal kassor som ger minimalt antal missade : " + test2);
        int test3 = optimize.optimalCheckoutsRandomSeedParallel();
        System.out.println(test3);

    }

    /**
     * Simulates the store with a given number of checkouts and seed, returning the number
     * of customers that couldn't be served (missed).
     *
     * @param checkoutsOpen Number of checkouts open in the simulation.
     * @param setSeed       Seed for random number generation in the simulation.
     * @return Number of missed customers during the simulation.
     */
    public int runOnce(int checkoutsOpen, int setSeed) {

        RunSim instanceOfRunSim = new RunSim(lambda, setSeed, closeStoreTime, checkoutsOpen, maxCapacityInStore, minPayTime, maxPayTime, minPickTime, maxPickTime, false);

        return instanceOfRunSim.runSimAndGetMissedCustomers();
    }


    /**
     * Determines the optimal number of checkouts to minimize missed customers for a given seed.
     * Utilizes a binary search approach to efficiently find the optimal number of checkouts.
     *
     * @param setSeed Seed for the simulation, ensuring reproducible results.
     * @return Optimal number of checkouts to minimize the number of missed customers.
     */
    public int optimalCheckOutsSetSeed(int setSeed) {

        // In essence the method works like this.
        // We assume that the optimal amount of checkouts are somewhere between 1 checkout,
        // and maxCapacityInStore (so each customer who have personal checkout).
        // Therefore, any of [1,2,3,4,5,6,mid,7,..., maxCapacityInStore] could be the optimal amount of checkouts.
        // If we start and check how many missed customers occurs with mid-value checkouts, and
        // also with mid-value + 1 (the value to the right). If mid-value is more optimal then mid-value + 1
        // the all numbers after mid-value+1 is discard, and the opposite t if mid-value + 1 is more optimal.
        // which is then repeated until the optimal amount of checkouts is found.

        int left = 1;
        int right = maxCapacityInStore;

        while (left < right - 1) { // Ensure at least two elements for comparison.
            int mid = (left + right) / 2;
            // Compare the number of missed customers for mid and mid + 1 checkouts.
            int missedCustomersMid = runOnce(mid, setSeed);
            int missedCustomersMidPlusOne = runOnce(mid + 1, setSeed);

            // If fewer customers are missed with mid + 1 checkouts, it's better to have more checkouts.
            if (missedCustomersMid > missedCustomersMidPlusOne) {
                left = mid + 1;
            } else {
                // Otherwise, fewer or the same number of customers are missed with mid checkouts.
                right = mid;
            }
        }

        // After narrowing down the search, run a final check to determine the optimal number.
        int missedLeft = runOnce(left, setSeed);
        int missedRight = runOnce(right, setSeed);

        // Return the checkout number with fewer missed customers.
        if (missedLeft <= missedRight) {
            return left;
        } else {
            return right;
        }
    }


    /**
     * Executes a large number of simulations where each simulation uses a different, randomly generated seed
     * This method aims to identify the most common optimal number of checkouts.
     * <p>
     * The method aggregates results into an array and then calculates the mode of these results
     * to determine the most frequently optimal configuration.
     *
     * @return The most common (mode) number of checkouts identified as optimal across all simulations.
     */


    private int optimalCheckOutsRandomSeed() {
        Random random = new Random();

        int arrayLength = 1000000;
        int[] optimalCheckoutsArray = new int[arrayLength];


        for (int i = 0; i < arrayLength; i++) {
            int seed = random.nextInt();
            int optimalCheckouts = optimalCheckOutsSetSeed(seed);
            optimalCheckoutsArray[i] = (optimalCheckouts);
        }

        return mostFrequent(optimalCheckoutsArray, arrayLength);
    }


    /**
     * .
     * This method performs the same function as optimalCheckOutsRandomSeed(), but leverages the system's available CPU cores for
     * parallel execution, significantly reducing computation tim
     *
     * @return The most common optimal number of checkouts found across all simulations.
     */
    // Running this at arrayLength 1000, for ex 7 in K, gave 460. Took 65 minutes
    // on an i5-12400.
    private int optimalCheckoutsRandomSeedParallel() {
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        int arrayLength = 1000000;
        List<Future<Integer>> futureResults = new ArrayList<>();

        // Submit tasks to be executed by the pool
        for (int i = 0; i < arrayLength; i++) {
            final int seed = new Random().nextInt();
            // Submit a callable task that calls optimalCheckOutsSetSeed and returns the result
            Future<Integer> future = executor.submit(() -> optimalCheckOutsSetSeed(seed));
            futureResults.add(future);
        }

        // Collect results
        int[] optimalCheckoutsArray = new int[arrayLength];
        try {
            for (int i = 0; i < arrayLength; i++) {
                // Wait for the task to complete and retrieve the result
                optimalCheckoutsArray[i] = futureResults.get(i).get();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Shutdown the executor
        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException ex) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        // Use the collected results as before
        return mostFrequent(optimalCheckoutsArray, arrayLength);
    }

    /**
     * Finds the most frequent value in an array of integers. This method is used to identify the
     * most common optimal number of checkouts across multiple simulations.
     *
     * @param arr Array of integers containing optimal numbers of checkouts from simulations.
     * @param n   The length of the array.
     * @return The most frequent integer in the array.
     */

    private int mostFrequent(int[] arr, int n) {
        // Sort the array
        Arrays.sort(arr);

        // find the max frequency using linear traversal
        int max_count = 1, res = arr[0];
        int curr_count = 1;

        for (int i = 1; i < n; i++) {
            if (arr[i] == arr[i - 1])
                curr_count++;
            else
                curr_count = 1;

            if (curr_count > max_count) {
                max_count = curr_count;
                res = arr[i - 1];
            }
        }
        return res;
    }


}
