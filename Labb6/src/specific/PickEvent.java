package specific;

import general.Event;

/**
 * Represents an event where a customer finishes picking items and is ready
 * to join the queue for checkout. The execution of this event includes updating the store state to reflect
 * QueueEvent, which represents the customer actually joining the checkout queue.
 *
 * @author Leo Man, Jacky Phuong, Leo Vedberg, Viktor Sundén
 */

public class PickEvent extends Event {

    /**
     * Constructs a PickEvent with specified time, customer, and store state.
     * Initializes the event with the given parameters and sets the event name to "Plock".
     *
     * @param time       The execution time of this event.
     * @param customer   The customer associated with this event.
     * @param storeState The current state of the store when this event occurs.
     */

    public PickEvent(float time, MakeCustomer customer, StoreState storeState) {
        super(time, customer, storeState);
        this.nameOfCurrentEvent = "Plock";
    }

    /**
     * Executes the event by updating the store state to indicate the customer
     * has finished picking items and is ready to queue for checkout. This method
     * schedules a new QueueEvent for the customer, marking their transition to
     * the queueing phase.
     *
     * @return A new QueueEvent indicating the customer is joining the checkout queue.
     */
    @Override
    public Event execute() {


        StoreState specificState = (StoreState) this.state;
        specificState.updateTime(this.getTime());
        MakeCustomer customer = (MakeCustomer) this.eventTarget;

        specificState.setCustomerId(customer.getCustomerId());
        specificState.setEventName(this.nameOfCurrentEvent);


        float next = scheduleNextEventTime();
        return new QueueEvent(next, (MakeCustomer) this.getEventTarget(), (StoreState) this.getState());
    }

    /**
     * Method that returns the time for the next event.
     * 
     * @return The time for the next event.
     */
    public float scheduleNextEventTime() {
        return time;
    }

}
