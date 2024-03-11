package general;


import specific.*;

import java.util.ArrayList;

/**
 * Orchestrates the execution of the store simulation, initializing the simulation environment,
 * generating customers, scheduling their arrival events, and processing events through the event queue.
 * This class ties together the store's state, the event queue, and the view responsible for output,
 * managing the lifecycle of the simulation from setup to completion.
 */

public class Simulator {

    private StoreState storeState;
    private StoreView storeView;
    private EventQueue eventQueue;

    /**
     * Constructs a Simulator with specified parameters for the simulation environment.
     * Initializes the store state, generates all customers and their corresponding arrival events,
     * and optionally sets up the view for outputting simulation progress and results.
     *
     * @param lambda             The average arrival rate of customers.
     * @param seed               The seed for random number generation, ensuring reproducibility.
     * @param closeStoreTime     The time at which the store closes and no new arrivals are processed.
     * @param checkoutsOpen      The number of checkouts available for customers to use.
     * @param maxCapacityInStore The maximum number of customers that can be in the store at once.
     * @param minPayTime         The minimum time it takes for a customer to complete payment.
     * @param maxPayTime         The maximum time it takes for a customer to complete payment.
     * @param minPickTime        The minimum time it takes for a customer to pick their items.
     * @param maxPickTime        The maximum time it takes for a customer to pick their items.
     * @param print              Flag indicating whether simulation progress and results should be printed.
     */

    public Simulator(double lambda, int seed, float closeStoreTime, int checkoutsOpen, int maxCapacityInStore, double minPayTime, double maxPayTime, double minPickTime, double maxPickTime, boolean print) {

        this.storeState = new StoreState(lambda, seed, closeStoreTime, checkoutsOpen, maxCapacityInStore, minPayTime, maxPayTime, minPickTime, maxPickTime, print);


        MakeAllCustomers allCustomersGenerator = new MakeAllCustomers(storeState);
        ArrayList<MakeCustomer> allCustomers = allCustomersGenerator.getAllCustomers();
        eventQueue = new EventQueue(storeState);


        // Convert all MakeCustomer instances to ArrivalEvents and add them to the event queue
        for (MakeCustomer customer : allCustomers) {
            ArrivalEvent arrivalEvent = new ArrivalEvent(customer.getArrivalTime(), customer, storeState);
            eventQueue.addInsert(arrivalEvent);
        }

        if (print) {
            this.storeView = new StoreView(this.storeState);
            storeState.addObserver(storeView);
        }

    }

    /**
     * Starts the simulation, processing events from the event queue until it is empty,
     * indicating that the simulation has concluded. Finally, stops the store's state.
     */
    public void start() {
        while (!eventQueue.isEmpty()) {
            eventQueue.executeAndInsert();
        }
        storeState.stop();
        eventQueue.executeAndInsert();
    }

    /**
     * Retrieves the number of customers who were missed (could not be served) during the simulation.
     *
     * @return The total number of missed customers.
     */
    public int getMissedCustomers() {
        return storeState.getMissedCustomers();
    }


}
