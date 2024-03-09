package specific;

import general.*;

public class ArrivalEvent extends Event {

    public ArrivalEvent(float time, MakeCustomer customer, StoreState storeState) {
        super(time, customer, storeState);
        this.nameOfCurrentEvent = "Ankomst";
    }

    @Override
    public Event execute() {
        //this.state.setTimePassed(this.getTime());

        StoreState specificState = (StoreState) this.state;
        specificState.updateTime(this.getTime());

        MakeCustomer customer = (MakeCustomer) this.eventTarget;

        specificState.setCustomerId(customer.getCustomerId());
        specificState.setEventName(this.nameOfCurrentEvent);


        float next = scheduleNextEventTime();
        return new EnterEvent(next, (MakeCustomer) this.getEventTarget(), (StoreState) this.getState());
    }

    // Here we have to find the time
    public float scheduleNextEventTime() {
        return time;
    }


}
