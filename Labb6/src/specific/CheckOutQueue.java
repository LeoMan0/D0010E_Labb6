package specific;

import java.util.ArrayList;


/**
 * Represents a checkout queue in the store. This class is responsible for managing
 * the queue of customers waiting to pay at the checkout.
 *
 * @author Leo Man, Jacky Phuong, Leo Vedberg, Viktor Sundén
 */

public class CheckOutQueue {

    private ArrayList<MakeCustomer> checkOutQueue;

    /**
     * Constructs an empty checkout queue.
     */
    public CheckOutQueue() {
        checkOutQueue = new ArrayList<>();
    }

    /**
     * Adds a customer to the end of the checkout queue.
     *
     * @param customer The customer to be added to the queue.
     */
    public void addCustomerToQueue(MakeCustomer customer) {
        checkOutQueue.add(customer);
    }

    /**
     * Retrieves and removes the next customer from the front of the checkout queue.
     *
     * @return The next customer, or null if the queue is empty.
     */
    public MakeCustomer nextCustomer() {
        if (!checkOutQueue.isEmpty()) {
            return checkOutQueue.remove(0); // Safely remove the first customer if the queue is not empty
        }
        return null;
    }

    /**
     * Checks if the checkout queue is empty.
     *
     * @return true if the queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return checkOutQueue.isEmpty();
    }

    /**
     * Returns the number of customers currently in the checkout queue.
     *
     * @return The size of the checkout queue.
     */
    public int size() {
        return checkOutQueue.size();
    }

    /**
     * Converts the checkout queue to an array of customer IDs. This method used for printing in
     * StoreView
     *
     * @return An array containing the IDs of the customers in the queue.
     */
    public int[] toArray() {

        int[] intArray = new int[checkOutQueue.size()];

        for (int i = 0; i < checkOutQueue.size(); i++) {
            MakeCustomer customer = checkOutQueue.get(i);

            intArray[i] = customer.getCustomerId();
        }

        return intArray;
    }


}
