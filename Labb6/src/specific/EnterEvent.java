package specific;

import general.Event;

public class EnterEvent extends Event {
    private StoreState specificState;

    public EnterEvent(float time, MakeCustomer customer, StoreState storeState) {
        super(time, customer, storeState);
        this.nameOfCurrentEvent = "EnterEvent";
    }


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

    public float scheduleNextEventTime() {
        //This number is a random number in a range.
        // This is the pick time. Can be taken from state.
        return time + (float) specificState.getPickTime();

    }

}
