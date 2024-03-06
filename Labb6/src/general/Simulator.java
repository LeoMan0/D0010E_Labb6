package general;


import specific.ArrivalEvent;

public class Simulator {

    public static void main(String[] args) {
        // Create an initial event and an event queue
        Event initialEvent = new ArrivalEvent(0, 0);
        EventQueue eventQueue = new EventQueue(initialEvent);

        // Add more events for testing

        int customerAmount = 6;
        for (int i = 1; i < customerAmount; i++) {
            eventQueue.addInsert(new ArrivalEvent(i, i));
        }


        System.out.println("Initial Queue:");
        eventQueue.printQueue(eventQueue);
        int numberOfExecutions = 4;

        for (int i = 1; i <= numberOfExecutions; i++) {
            eventQueue.executeAndInsert();
            System.out.println("\nQueue after " + i + " executeAndInsert:");
            eventQueue.printQueue(eventQueue);
        }
    }


}
