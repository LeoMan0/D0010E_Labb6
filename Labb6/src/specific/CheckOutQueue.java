package specific;

import general.Event;

import java.util.ArrayList;

public class CheckOutQueue {

    private ArrayList<MakeCustomer> checkOutQueue;

    public CheckOutQueue() {
        checkOutQueue = new ArrayList<>();
    }

    public void addCustomerToQueue(MakeCustomer customer) {
        checkOutQueue.add(customer);
    }

    public MakeCustomer nextCustomer() {
        if (!checkOutQueue.isEmpty()) {
            return checkOutQueue.remove(0); // Safely remove the first customer if the queue is not empty
        }
        return null;
    }

    public boolean isEmpty() {
        return checkOutQueue.isEmpty();
    }


}
