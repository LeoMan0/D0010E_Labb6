package specific;

import general.Event;

public class PayEvent extends Event {
    StoreState specificState;
    MakeCustomer customer;

    public PayEvent(float time, MakeCustomer customer, StoreState state) {
        super(time, customer, state);
        this.nameOfCurrentEvent = "PayEvent";

    }

    //Here we don't have to calculate anything or schedule anything. Since no event follows this
    //Unless we can use this someway so that a customer can enter idk
    @Override
    public Event execute() {
        this.state.setTimePassed(this.getTime());

        specificState = (StoreState) this.state;
        customer = (MakeCustomer) this.eventTarget;

        specificState.setCustomerId(customer.getCustomerId());
        specificState.setEventName(this.nameOfCurrentEvent);

        specificState.decreaseCurrentCapacity();
        specificState.increaseCheckOutsOpen();

        if (specificState.getCheckOutQueue().isEmpty()) {
            return null;
        } else {
            return new QueueEvent(this.time, specificState.getCheckOutQueue().nextCustomer(), (StoreState) this.getState());
        }
    }


}
