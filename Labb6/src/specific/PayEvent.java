package specific;

import general.Event;

public class PayEvent extends Event {

    public PayEvent(float time, int customerName) {
        super(time, customerName);
        this.nameOfCurrentEvent = "PayEvent";

    }

    //Here we don't have to calculate anything or schedule anything. Since no event follows this
    //Unless we can use this someway so that a customer can enter idk
    @Override
    public Event execute() {


        return null;
    }


}
