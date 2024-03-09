package specific;

import general.State;
import general.View;

import java.util.Observable;

public class StoreView extends View {

    private StoreState storeState;

    public StoreView(StoreState storeState) {
        this.storeState = storeState;
        storeState.addObserver(this);
        this.printSimulationParameters();
        this.printEventHeaders();
    }

    @Override
    public void update(Observable o, Object arg) {


        //System.out.printf("%.2f %s %s %s\n", storeState.getTimePassed(), storeState.getEventName(), storeState.getCustomerId(), storeState.getMissedCustomers());
//        if (storeState.getEventName() != "EnterEvent" && storeState.getEventName() != "QueueEvent") {
//            System.out.printf("%.2f %s %s %s\n", storeState.getTimePassed(), storeState.getEventName(), storeState.getCustomerId(), storeState.getCheckoutsOpen(), storeState.getMissedCustomers(), storeState.getCurrentCapacityInStore(), "x", storeState.getMissedCustomers());
//        }
        if (storeState.getEventName() != "EntreEvent" && storeState.getEventName() != "QueueEvent" && storeState.getEventName() != "LeaveEvent") {

            System.out.printf("%-6.2f %-10s %-5d %-4s %-5d %-7.2f %-4s %-4d %-4d %-4d %-6.2f %d %s \n",
                    storeState.getTimePassed(),
                    storeState.getEventName(),
                    storeState.getCustomerId(),
                    "ö",
                    storeState.getCheckoutsOpen(),
                    storeState.getTimePassed(),
                    storeState.getCurrentCapacityInStore(),
                    storeState.getPaidCustomers(),
                    storeState.getMissedCustomers(),
                    storeState.getTotalCustomerWhoHasQueued(),
                    storeState.getTotalQueueTime(),
                    storeState.queueLength(),
                    storeState.getLookInsideCustomerQueue()
                    /* Add the remaining variables according to their actual types and intended column widths */
            );

        }

    }

    public void printSimulationParameters() {
        System.out.println("PARAMETRAR");
        System.out.println("==========");
        System.out.println("Antal kassor, N..........: " + this.storeState.getCheckoutsOpen());
        System.out.println("Max som ryms, M..........: " + this.storeState.getMaxCapacityInStore());
        System.out.println("Ankomshastighet, lambda..: " + this.storeState.getLambda());
        System.out.println("Plocktider, [P_min..Pmax]: [" + this.storeState.getMinPickTime() + ".." + this.storeState.getMaxPickTime() + "]");
        System.out.println("Betaltider, [K_min..Kmax]: [" + this.storeState.getMinPayTime() + ".." + this.storeState.getMaxPayTime() + "]");
        System.out.println("Frö, f...................: " + this.storeState.getSeed());
    }

    public void printEventHeaders() {
        System.out.println("\nFÖRLOPP");
        System.out.println("=======");
        System.out.println("Tid    Händelse  Kund   ?   led    ledT    I    $   :-( köat köT köar [Kassakö..]");

    }
}
