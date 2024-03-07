package general;


import specific.ArrivalEvent;
import specific.StoreState;
import specific.StoreView;

public class Simulator {

    public static void main(String[] args) {
        // Create an initial event and an event queue

        StoreState storeState = new StoreState(1, 1, 1, 1, 1, 1, 1, 1);
        StoreView storeView = new StoreView(storeState);

        storeState.addObserver(storeView);


        EventQueue eventQueue = new EventQueue(storeState);

        // Add more events for testing

        int customerAmount = 6;
        for (int i = 1; i < customerAmount; i++) {
            eventQueue.addInsert(new ArrivalEvent(i, i));
        }


        int numberOfExecutions = 0;

        for (int i = 1; i <= numberOfExecutions; i++) {
            eventQueue.executeAndInsert();
            eventQueue.printQueue(eventQueue);
        }


    }


}
