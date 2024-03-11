package specific;

import random.*;

import java.util.ArrayList;

/**
 * Generates all customers for the store simulation based on exponential distribution
 * to model inter-arrival times. This class uses the store's lambda value (arrival rate)
 * and seed for the random number generator to create all the customers
 * who will arrive at the store.
 *
 * @author Leo Man, Jacky Phuong, Leo Vedberg, Viktor Sundén
 */


public class MakeAllCustomers {


    ArrayList<MakeCustomer> allCustomers = new ArrayList<>();

    /**
     * Constructs and populates the list of all customers arriving at the store
     * during its operating hours, based on exponential distribution.
     *
     * @param storeState The current state of the store, including parameters such
     *                   as lambda for arrival rate and closing time.
     */
    public MakeAllCustomers(StoreState storeState) {

        double arrivalTimes = 0;
        ExponentialRandomStream generateArrivalTime = new ExponentialRandomStream(storeState.getLambda(), storeState.getSeed());

        int customerId = -1;

        while (arrivalTimes <= storeState.getCloseStoreTime()) {

            arrivalTimes = arrivalTimes + generateArrivalTime.next();
            if (arrivalTimes >= storeState.getCloseStoreTime()) {
                break;
            }


            customerId++;

            float arrivalTimeFloat = (float) arrivalTimes;

            this.allCustomers.add(new MakeCustomer(customerId, arrivalTimeFloat));
        }
    }

    /**
     * Returns the list of all customers generated for the simulation.
     *
     * @return An ArrayList containing all the customers.
     */

    public ArrayList<MakeCustomer> getAllCustomers() {
        return this.allCustomers;
    }
}
