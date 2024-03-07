package specific;

import general.*;

public class ArrivalEvent extends Event {
    StoreState specificState;
    MakeCustomer customer;

    public ArrivalEvent(float time, MakeCustomer customer, StoreState storeState) {
        super(time, customer, storeState);
        this.nameOfCurrentEvent = "ArrivalEvent";
    }

    @Override
    public Event execute() {
        this.state.setTimePassed(this.getTime());

        specificState = (StoreState) this.state;
        customer = (MakeCustomer) this.eventTarget;

        specificState.setCustomerId(customer.getCustomerId());
        specificState.setEventName(this.nameOfCurrentEvent);

        if (specificState.getCurrentCapacityInStore() == specificState.getMaxCapacityInStore()) {
            return null;
        }
        specificState.increaseCurrentCapacity();
        float next = scheduleNextEventTime();
        return new EnterEvent(next, (MakeCustomer) this.getEventTarget(), (StoreState) this.getState());
    }

    // Here we have to find the time
    public float scheduleNextEventTime() {
        return time;
    }


}
