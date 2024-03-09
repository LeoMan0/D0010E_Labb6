package specific;

import random.*;

import java.util.ArrayList;


public class MakeAllCustomers {


    ArrayList<MakeCustomer> allCustomers = new ArrayList<>();


    public MakeAllCustomers(StoreState storeState) {

        double arrivalTimes = 0;
        ExponentialRandomStream generateArrivalTime = new ExponentialRandomStream(storeState.getLambda(), storeState.getSeed());

        int customerId = -1;

        while (arrivalTimes < storeState.getCloseStoreTime()) {

            arrivalTimes = arrivalTimes + generateArrivalTime.next();

            customerId++;

            float arrivalTimeFloat = (float) arrivalTimes;

            this.allCustomers.add(new MakeCustomer(customerId, arrivalTimeFloat));
        }
    }

    public ArrayList<MakeCustomer> getAllCustomers() {
        return this.allCustomers;
    }
}
