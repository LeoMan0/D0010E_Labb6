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
        if (storeState.getEventName() == "PayEvent") {
            System.out.printf("%.2f %s %s %s\n", storeState.getTimePassed(), storeState.getEventName(), storeState.getCustomerId(), storeState.getMissedCustomers());
        }
    }

    public void printSimulationParameters() {
        System.out.println("PARAMETRAR");
        System.out.println("==========");
        System.out.println("Antal kassor, N..........: " + this.storeState.getCheckoutsOpen());
        System.out.println("Max som ryms, M..........: " + this.storeState.getMaxCapacityInStore());
        // Assuming an attribute for arrival rate (lambda) exists, otherwise, you'll need to add it
        System.out.println("Ankomshastighet, lambda..: " + "Blank for now");
        System.out.println("Plocktider, [P_min..Pmax]: [" + this.storeState.getMinPickTime() + ".." + this.storeState.getMaxPickTime() + "]");
        System.out.println("Betaltider, [K_min..Kmax]: [" + this.storeState.getMinPayTime() + ".." + this.storeState.getMaxPayTime() + "]");
        System.out.println("Frö, f...................: " + this.storeState.getSeed());
    }

    public void printEventHeaders() {
        System.out.println("\nFÖRLOPP");
        System.out.println("=======");
        System.out.println("Tid Händelse Kund ? led ledT I $ :-( köat köT köar [Kassakö..]");
    }
}
