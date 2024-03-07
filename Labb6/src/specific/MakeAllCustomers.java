package specific;

import random.*;

import java.util.ArrayList;


public class MakeAllCustomers {

    //Test Passed
    public static void main(String[] args) {

        StoreState storeState = new StoreState(1, 1234L, 5, 10f, 1, 5, 0.5f, 0.1f, 2, 3);

        // Initialize simulation with StoreState
        MakeAllCustomers simulation = new MakeAllCustomers(storeState);

        // Print details for each customer
        for (MakeCustomer customer : simulation.getAllCustomers()) {
            System.out.printf("Customer ID: %d, Arrival Time: %.2f, Pick Time: %.2f, Pay Time: %.2f, Max Wait Time: %.2f\n",
                    customer.getCustomerId(), customer.getArrivalTime(), customer.getTimeToPick(), customer.getTimeToPay(), customer.getTimeMaxWait());
        }
    }

    ArrayList<MakeCustomer> allCustomers = new ArrayList<>();


    public MakeAllCustomers(StoreState storeState) {

        double arrivalTimes = 0;
        ExponentialRandomStream generateArrivalTime = new ExponentialRandomStream(storeState.getLambda(), storeState.getSeed());

        double pickTimes;
        UniformRandomStream generatePickTime = new UniformRandomStream(storeState.getMinPickTime(), storeState.getMaxPickTime(), storeState.getSeed());

        double payTimes;
        UniformRandomStream generatePayTime = new UniformRandomStream(storeState.getMinPayTime(), storeState.getMaxPayTime(), storeState.getSeed());

        int customerId = -1;

        while (arrivalTimes < storeState.getCloseStoreTime()) {

            arrivalTimes = arrivalTimes + generateArrivalTime.next();
            if (arrivalTimes >= storeState.getCloseStoreTime()) {
                break;
            }

            pickTimes = generatePickTime.next();
            payTimes = generatePayTime.next();
            customerId++;

            float arrivalTimeFloat = (float) arrivalTimes;
            float pickTimeFloat = (float) pickTimes;
            float payTimeFloat = (float) payTimes;


            this.allCustomers.add(new MakeCustomer(customerId, arrivalTimeFloat, pickTimeFloat, payTimeFloat, storeState.getMaxWaitTime()));
        }


    }

    public ArrayList<MakeCustomer> getAllCustomers() {
        return this.allCustomers;
    }
}
