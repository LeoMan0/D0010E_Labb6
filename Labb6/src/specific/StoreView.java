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

        if (storeState.getEventName() != "EnterEvent" && storeState.getEventName() != "QueueEvent" && storeState.getEventName() != "LeaveEvent") {

            System.out.printf("%-6.2f %-10s %-5d %-4s %-5d %-7.2f %-4s %-4d %-6d %-6d %-6.2f %-7d %s \n",
                    storeState.getTimePassed(),
                    storeState.getEventName(),
                    storeState.getCustomerId(),
                    this.storeOpenPrint(storeState.getStoreIsOpen()),
                    storeState.getCheckoutsOpen(),
                    storeState.getCheckoutIdleTime(),
                    storeState.getCurrentCapacityInStore(),
                    storeState.getPaidCustomers(),
                    storeState.getMissedCustomers(),
                    storeState.getTotalCustomerWhoHasQueued(),
                    storeState.getTotalQueueTime(),
                    storeState.queueLength(),
                    storeState.getLookInsideCustomerQueue()
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
        System.out.println("Tid    Händelse  Kund   ?   led    ledT    I    $   :-(    köat    köT    köar    [Kassakö..]");

    }

    public String storeOpenPrint(boolean storeIsOpen) {
        if (storeIsOpen) {
            return "Ö";
        }
        return "S";
    }
}
