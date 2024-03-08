package specific;

import general.Event;

public class LeaveEvent extends Event {

    public LeaveEvent(float time, MakeCustomer customer, StoreState state) {
        super(time, customer, state);
        this.nameOfCurrentEvent = "Lämnar";

    }


    @Override
    public Event execute() {
        this.state.setTimePassed(this.getTime());

        StoreState specificState = (StoreState) this.state;
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
