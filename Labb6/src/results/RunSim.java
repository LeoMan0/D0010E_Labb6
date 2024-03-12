package results;

import general.*;

/**
 * Entry point class for running the store simulation. This class initializes and starts
 * simulations with specified parameters, facilitating the execution of simulations and
 * retrieval of results, such as the number of missed customers.
 *
 * @author Leo Man, Jacky Phuong, Leo Vedberg, Viktor Sundén
 */

public class RunSim {


    private Simulator simulator;

    /**
     * Main method to run simulations with predefined parameters.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {

        // Example 1
        RunSim sim1 = new RunSim(1, 1234, 10f, 2, 5,
                2, 3, 0.5f, 1, true);
        sim1.run();

        //Example 2
        RunSim sim2 = new RunSim(3, 13, 8f, 2, 7,
                0.35f, 0.6f, 0.6f, 0.9f, true);
        sim2.run();


    }

    /**
     * Constructs a results.RunSim instance which initializes a simulation with specific parameters.
     *
     * @param lambda             The average arrival rate of customers.
     * @param seed               The seed for random number generation.
     * @param closeStoreTime     The simulation time at which the store closes.
     * @param checkoutsOpen      The number of checkouts available in the store.
     * @param maxCapacityInStore The maximum number of customers that can be in the store at once.
     * @param minPayTime         The minimum time it takes for a customer to pay.
     * @param maxPayTime         The maximum time it takes for a customer to pay.
     * @param minPickTime        The minimum time it takes for a customer to pick items.
     * @param maxPickTime        The maximum time it takes for a customer to pick items.
     * @param print              Flag indicating whether to print the simulation progress and results.
     */

    public RunSim(double lambda, int seed, float closeStoreTime, int checkoutsOpen, int maxCapacityInStore, double minPayTime,
                  double maxPayTime, double minPickTime, double maxPickTime, boolean print) {

        this.simulator = new Simulator(lambda, seed, closeStoreTime, checkoutsOpen, maxCapacityInStore, minPayTime, maxPayTime, minPickTime, maxPickTime, print);
    }

    /**
     * Starts the simulation.
     */
    public void run() {
        this.simulator.start();
    }

    /**
     * Runs the simulation and returns the number of customers who were missed because
     * the store reached its maximum capacity.
     *
     * @return The number of missed customers in the simulation.
     */
    public int runSimAndGetMissedCustomers() {
        this.simulator.start();
        return simulator.getMissedCustomers();
    }


}
