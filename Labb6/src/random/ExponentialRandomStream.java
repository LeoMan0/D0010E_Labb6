
package random;

import java.util.Random;

public class ExponentialRandomStream {

    public static void main(String[] args) {

        //Testing

        ExponentialRandomStream test = new ExponentialRandomStream(1.0, 1234L);
        double sum = test.next();

        UniformRandomStream test2 = new UniformRandomStream(0.5, 1.0, 1234L);
        double add = test2.next();

        for (int i = 0; i < 10; i++) {

            String formattedValue = String.format("%.2f", sum);
            System.out.println(formattedValue);
            add = sum + add;
            String addString = String.format("%.2f", add);
            System.out.println(addString);


            add = test2.next();
            sum = sum + test.next();

        }

    }

    private Random rand;
    private double lambda;

    public ExponentialRandomStream(double lambda, long seed) {
        rand = new Random(seed);
        this.lambda = lambda;
    }

    public ExponentialRandomStream(double lambda) {
        rand = new Random();
        this.lambda = lambda;
    }

    public double next() {
        return -Math.log(rand.nextDouble()) / lambda;
    }
}

