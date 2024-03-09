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
        if (!storeState.getRun()) {
            this.printSimulationResults();
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

    public void printSimulationResults() {


        ;
        int servedCustomers = storeState.getPaidCustomers(); // Example, replace with a method call like storeState.getServedCustomers();
        int missedCustomers = storeState.getMissedCustomers(); // Example, replace with a method call like storeState.getMissedCustomers();
        int totalCustomers = servedCustomers + missedCustomers;
        double idleCheckoutTime = storeState.getCheckoutIdleTime(); // Total idle time of all checkouts, replace with calculation or method call
        double averageIdleTime = idleCheckoutTime / storeState.getCheckoutsOpen(); // Average idle time per checkout, replace with calculation or method call
        double idleTimePercentage = (averageIdleTime / storeState.getTimePassed()) * 100d; // Percentage of idle time, replace with calculation or method call
        double totalQueueTime = storeState.getTotalQueueTime(); // Total time customers queued, replace with calculation or method call

        int customersWhoQueued = storeState.getTotalCustomerWhoHasQueued();
        double averageQueueTime = totalQueueTime / customersWhoQueued; // Average queue time per customer, replace with calculation or method call

        System.out.println("RESULTAT");
        System.out.println("========");
        System.out.printf("1) Av %d kunder handlade %d medan %d missades.\n", totalCustomers, servedCustomers, missedCustomers);
        System.out.printf("2) Total tid 2 kassor varit lediga: %.2f te.\n", idleCheckoutTime);
        System.out.printf("Genomsnittlig ledig kassatid: %.2f te (dvs %.2f%% av tiden från öppning tills sista kunden betalat).\n", averageIdleTime, idleTimePercentage);
        System.out.printf("3) Total tid %d kunder tvingats köa: %.2f te.\n", customersWhoQueued, totalQueueTime);
        System.out.printf("Genomsnittlig kötid: %.2f te.\n", averageQueueTime);

        //
        //
        // 3 Passed

    }
}
