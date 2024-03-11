package specific;

import general.Event;

/**
 * Represents an event where a customer leaves the store after completing
 * their payment. This class extends the Event class to handle the final
 * step in the customer's store visit lifecycle.
 * <p>
 * Note: This event is not displayed in the StoreView update as per assignment
 * requirements, suggesting it's part of the background process for customer
 * flow management.
 */

public class LeaveEvent extends Event {

    /**
     * Constructs an LeaveEvent with specified time, customer, and store state.
     * Initializes the event with the given parameters and sets the event name to "LeaveEvent".
     *
     * @param time     The execution time of this event.
     * @param customer The customer associated with this event.
     * @param state    The current state of the store when this event occurs.
     */
    public LeaveEvent(float time, MakeCustomer customer, StoreState state) {
        super(time, customer, state);
        this.nameOfCurrentEvent = "LeaveEvent";

    }

    /**
     * Executes the event by updating the store state to reflect the customer's
     * departure. It decreases the store's current capacity and potentially triggers
     * a new QueueEvent if there are customers waiting in the queue, thereby managing
     * the flow of customers through the checkout process.
     *
     * @return A new QueueEvent if there are customers in the queue, otherwise null.
     */

    @Override
    public Event execute() {

        StoreState specificState = (StoreState) this.state;
        specificState.updateTime(this.getTime());
        MakeCustomer customer = (MakeCustomer) this.eventTarget;

        specificState.setCustomerId(customer.getCustomerId());
        specificState.setEventName(this.nameOfCurrentEvent);

        specificState.decreaseCurrentCapacity();
        specificState.increaseCheckOutsOpen();
        specificState.increasePaidCustomers();


        // Here is the other implementation of the queue to the checkout
        // After the customer who currently is paying is done
        // if there isn't a queue nothing happens
        // otherwise the customer who is next in queue gets scheduled to pay.
        // QueueEvent is basically more of a try to pay event

        if (specificState.getCheckOutQueue().isEmpty()) {
            return null;
        } else {
            return new QueueEvent(this.time, specificState.getCheckOutQueue().nextCustomer(), (StoreState) this.getState());
        }
    }


}
