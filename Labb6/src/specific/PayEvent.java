package specific;

import general.Event;

public class PayEvent extends Event {

    public PayEvent(float time, MakeCustomer customer, StoreState state) {
        super(time, customer, state);
        this.nameOfCurrentEvent = "Betalning";

    }


    @Override
    public Event execute() {
        this.state.setTimePassed(this.getTime());

        StoreState specificState = (StoreState) this.state;
        MakeCustomer customer = (MakeCustomer) this.eventTarget;

        specificState.setCustomerId(customer.getCustomerId());
        specificState.setEventName(this.nameOfCurrentEvent);


        float next = scheduleNextEventTime();
        return new LeaveEvent(next, (MakeCustomer) this.getEventTarget(), (StoreState) this.getState());
    }

    // Here we have to find the time
    public float scheduleNextEventTime() {
        return time;
    }


}
