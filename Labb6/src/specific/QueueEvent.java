package specific;

import general.Event;

/**
 * Represents an event where a customer either joins a queue for checkout or
 * proceeds directly to payment if a checkout is immediately available.
 * <p>
 * Note: This event is not displayed in the StoreView update as per assignment
 * requirements.
 *
 * @author Leo Man, Jacky Phuong, Leo Vedberg, Viktor Sundén
 */
public class QueueEvent extends Event {


    private StoreState specificState;

    /**
     * Constructs a QueueEvent with specified time, customer, and store state.
     * Initializes the event with the given parameters and sets the event name to "QueueEvent".
     *
     * @param time       The execution time of this event.
     * @param customer   The customer associated with this event.
     * @param storeState The current state of the store when this event occurs.
     */

    public QueueEvent(float time, MakeCustomer customer, StoreState storeState) {
        super(time, customer, storeState);
        this.nameOfCurrentEvent = "QueueEvent";
    }

    /**
     * Executes the event by updating the store state to reflect the customer's
     * queuing status. Depending on the availability of checkouts, the
     * customer either proceeds to payment or joins the queue.
     *
     * @return A new PayEvent if a checkout is immediately available, otherwise null
     * indicating the customer has joined the queue and will wait for the
     * next available checkout.
     */
    @Override
    public Event execute() {
        this.state.setTimePassed(this.getTime());

        specificState = (StoreState) this.state;
        specificState.updateTime(this.getTime());
        MakeCustomer customer = (MakeCustomer) this.eventTarget;

        specificState.setCustomerId(customer.getCustomerId());
        specificState.setEventName(this.nameOfCurrentEvent);


        // Here is the implementation of the queue to the checkout
        // If a checkout is open the customer just pays
        // If not he is added to the queue

        if (specificState.getCheckoutsOpen() != 0) {
            specificState.decreaseCheckOutsOpen();

            float next = scheduleNextEventTime();
            return new PayEvent(next, (MakeCustomer) this.getEventTarget(), (StoreState) this.getState());
        } else {
            specificState.increaseTotalCustomerWhoHasQueued();
            specificState.getCheckOutQueue().addCustomerToQueue(customer);
            return null;
        }
    }

    /**
     * Calculates the next event time based on the current event time and the
     * randomly generated pay time for the customer. This calculation is used when
     * transitioning a customer directly to payment, bypassing the queue due to
     * an immediately available checkout.
     *
     * @return The time for the next PayEvent.
     */

    public float scheduleNextEventTime() {
        return time + (float) specificState.getPayTime();
    }


}