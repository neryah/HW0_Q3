import java.util.List;
import java.util.Arrays;
/**
 A Coin can have a value of 0.05, 0.5, 1, 5, 10
 */
public class Coin {
    private double value;
    /**
     * @requires value in {0.05, 0.5, 1, 5, 10}
     * @modifies this
     * @effects Creates and initializes new Coin with the value, value
     *
     */
    public Coin(double value) throws IllegalArgumentException {
        List<Double> legalVals = Arrays.asList(0.05, 0.5, 1.0, 5.0, 10.0);
//        List<Double> legalVals = new List<Double>();//.of(0.05, 0.5, 1.0, 5.0, 10.0);
//        legalVals.add(0.05);
//        legalVals.add(0.5);
//        legalVals.add(1.0);
//        legalVals.add(5.0);
//        legalVals.add(10.0);
        if(!legalVals.contains(value)){
            throw new IllegalArgumentException("Error. Illegal value.");
        }
        this.value = value;
    }


    /**
     * @return the value of the Coin
     */
    public double getValue() {
        return value;
    }
}