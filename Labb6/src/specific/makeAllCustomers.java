package specific;

import random.*;

import java.util.ArrayList;


public class makeAllCustomers {

    public static void main(String[] args) {

        // Initialize parameters for the makeAllCustomers constructor
        double lambda = 1.0; // Example value
        long seed = 1234L; // Example value
        double minPickTime = 0.5; // Example value
        double maxPickTime = 1.0; // Example value
        double minPayTime = 2.0; // Example value
        double maxPayTime = 3.0; // Example value
        float closeStoreTime = 10.0f; // Example value, simulation runs until this time
        float maxWaitTime = 5.0f; // Example value

        makeAllCustomers simulation = new makeAllCustomers(lambda, seed, minPickTime, maxPickTime, minPayTime, maxPayTime, closeStoreTime, maxWaitTime);

        // Print details for each customer
        for (MakeCustomer customer : simulation.allCustomers) {
            System.out.printf("Customer ID: %d, Arrival Time: %.2f, Time to Pick: %.2f, Time to Pay: %.2f, Max Wait Time: %.2f\n",
                    customer.customerId, customer.getArrivalTime(), customer.getTimeToPick(), customer.getTimeToPay(), customer.getTimeMaxWait());
        }
    }

    ArrayList<MakeCustomer> allCustomers = new ArrayList<>();


    public makeAllCustomers(double lambda, long seed, double minPickTime, double maxPickTime, double minPayTime, double maxPayTime, float closeStoreTime, float maxWaitTime) {

        double arrivalTimes = 0;
        ExponentialRandomStream generateArrivalTime = new ExponentialRandomStream(lambda, seed);

        double pickTimes;
        UniformRandomStream generatePickTime = new UniformRandomStream(minPickTime, maxPickTime, seed);

        double payTimes;
        UniformRandomStream generatePayTime = new UniformRandomStream(minPayTime, maxPayTime, seed);

        int customerId = -1;

        while (arrivalTimes < closeStoreTime) {
            arrivalTimes = arrivalTimes + generateArrivalTime.next();
            pickTimes = generatePickTime.next();
            payTimes = generatePayTime.next();
            customerId++;

            float arrivalTimeFloat = (float) arrivalTimes;
            float pickTimeFloat = (float) pickTimes;
            float payTimeFloat = (float) payTimes;

            this.allCustomers.add(new MakeCustomer(customerId, arrivalTimeFloat, pickTimeFloat, payTimeFloat, maxWaitTime));
        }


    }


}
