package specific;

import general.Event;

public class QueueEvent extends Event {

    // This class is basically redundant or rather PayEvent is but here we are.

    StoreState specificState;
    MakeCustomer customer;

    public QueueEvent(float time, MakeCustomer customer, StoreState storeState) {
        super(time, customer, storeState);
        this.nameOfCurrentEvent = "QueueEvent";
    }

    // Likewise we have to make it so that the customer leaves the queue here
    @Override
    public Event execute() {
        this.state.setTimePassed(this.getTime());

        specificState = (StoreState) this.state;
        customer = (MakeCustomer) this.eventTarget;

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
            specificState.getCheckOutQueue().addCustomerToQueue(customer);
            return null;
        }
    }

    public float scheduleNextEventTime() {
        return time + (float) specificState.getPayTime();
    }


}