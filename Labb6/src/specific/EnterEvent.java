package specific;

import general.Event;

/**
 * Represents a specific type of event when a customer enters the store. This class
 * extends the Event class to handle customer entry, including checking the store's
 * capacity and deciding whether the customer can start picking items or if they
 * are turned away due to the store being at maximum capacity.
 * Note: This event is not displayed in the StoreView update as per assignment
 * requirements.
 *
 * @author Leo Man, Jacky Phuong, Leo Vedberg, Viktor Sundén
 */


public class EnterEvent extends Event {
    private StoreState specificState;

    /**
     * Constructs an EnterEvent with specified time, customer, and store state.
     * Initializes the event with the given parameters and sets the event name
     * to "EnterEvent".
     *
     * @param time       The execution time of this event.
     * @param customer   The customer associated with this event.
     * @param storeState The current state of the store when this event occurs.
     */

    public EnterEvent(float time, MakeCustomer customer, StoreState storeState) {
        super(time, customer, storeState);
        this.nameOfCurrentEvent = "EnterEvent";
    }

    /**
     * Executes the event and updating the store state. It checks if the store
     * is at its maximum capacity. If so, it increments the count of missed customers in the store state.
     * Otherwise, it increments the current capacity of the store state and schedules a new
     * PickEvent for the customer to start picking items. This reflects the customer's
     * transition from entering to actively shopping.
     *
     * @return A new PickEvent if the customer can enter, otherwise null.
     */

    @Override
    public Event execute() {
        //Setting the time to when the event was executed
        //this.state.setTimePassed(this.getTime());

        specificState = (StoreState) this.state;
        specificState.updateTime(this.getTime());
        MakeCustomer customer = (MakeCustomer) this.eventTarget;

        specificState.setCustomerId(customer.getCustomerId());
        specificState.setEventName(this.nameOfCurrentEvent);

        if (specificState.getCurrentCapacityInStore() == specificState.getMaxCapacityInStore()) {
            specificState.increaseMissedCustomers();
            return null;

        }
        specificState.increaseCurrentCapacity();
        float next = scheduleNextEventTime();
        return new PickEvent(next, (MakeCustomer) this.getEventTarget(), (StoreState) this.getState());
    }

    /**
     * Calculates the time for the next event based on the current event time
     * and a random pick time from the store state. This time indicates
     * when the customer will stop picking items.
     *
     * @return The time for the next event.
     */

    public float scheduleNextEventTime() {
        //This number is a random number in a range.
        // This is the pick time. Can be taken from state.
        return time + (float) specificState.getPickTime();

    }

}
