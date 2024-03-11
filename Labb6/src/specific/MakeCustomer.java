package specific;

/**
 * Represents an individual customer within the store simulation. This class holds
 * information about the customer's ID and their arrival time to the store.
 *
 * @author Leo Man, Jacky Phuong, Leo Vedberg, Viktor Sundén
 */

public class MakeCustomer {


    float arrivalTime;
    int customerId;

    /**
     * Constructs a new Customer with a specified ID and arrival time.
     *
     * @param customerId  The unique identifier for the customer.
     * @param arrivalTime The time at which the customer arrives at the store.
     */

    public MakeCustomer(int customerId, float arrivalTime) {
        this.customerId = customerId;
        this.arrivalTime = arrivalTime;

    }

    /**
     * Gets the arrival time of the customer.
     *
     * @return The arrival time of the customer.
     */
    public float getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Gets the customer's ID.
     *
     * @return The ID of the customer.
     */
    public int getCustomerId() {
        return customerId;
    }
}
