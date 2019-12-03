import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.Arrays;
import java.util.Iterator;
/**
 * A wallet can contain a number of coins. There could be several coins of the same value, 
 * but the same coin cannot appear in the wallet twice
 */
public class Wallet {
    private Map<Double, ArrayList<Coin>> coins = new TreeMap<Double, ArrayList<Coin>> ();
    /**
     * @effects Creates a new empty wallet
     */
    public Wallet() {}


    /**
     * @modifies this
     * @effects Adds a coin to the wallet
     * @return true if the coin was successfully added to the wallet;
     * 		   false otherwise
     */
    public boolean addCoin(Coin coin) {
        ArrayList<Coin> oneValue = new ArrayList<Coin>();
        if(!coins.containsKey(coin.getValue())){
            oneValue.add(coin);
            coins.put(coin.getValue(), oneValue);
            return true;
        }
        oneValue = coins.get(coin.getValue());
        if(oneValue.contains(coin)){
            return false;
        }
        oneValue.add(coin);
        return true;
    }

    /**
     * @requires sum > 0
     * @modifies this
     * @effects tries to match at least the sum "sum" with coins in the wallet. 
     *			If transaction is possible, removes the paid coins from the wallet;
     *			else; changes nothing
     * @return the amount actually paid, 0 if amount could not be obtained
     */
    public double pay(double sum) {
        if (sum<=0){
            return 0;
        }
        if(sum > getWalletTotal()){
            return 0;
        }
        double diff = sum;
        ArrayList<Double> keys = new ArrayList<Double>(coins.keySet());
        for(int i=keys.size()-1; diff>=0 && i>=0 ;i--) {
            double key = keys.get(i);
            ArrayList<Coin> oneValue = coins.get(key);
            for(;diff>0 && !oneValue.isEmpty(); diff -= key, oneValue.remove(0)){}
            if(oneValue.isEmpty()){
                coins.remove(key);
            }
            if (diff <= 0) {
                break;
            }
        }
        return sum - diff;
    }


    /**
     * @requires sum > 0
     * @modifies this
     * @effects tries to match at least the sum "sum" with the minimum number of
     *           coins available from the wallet.
     *           If transaction is possible, removes the paid coins from the wallet; else;
     *           changes nothing
     * @return the amount actually paid, 0 if amount could not be obtained
     */
    public double payMinimum(double sum) {
        return pay(sum);
    }


    /**
     * @requires sum > 0
     * @modifies this
     * @effects tries to match the exact sum "sum" with the maximum number of
     *          coins available from the wallet.
     *          If transaction is possible, removes the paid coins from the wallet; else;
     *          changes nothing
     * @return the amount actually paid, 0 if amount could not be obtained
     */
    public double payExactMaximum(double sum) {//Dynamic programming implementation
        if (sum<=0){
            return 0;
        }
        if(sum > getWalletTotal() || (20*sum)%1 > 0){
            return 0;
        }
        int newSum = (int)(20*sum);
        int sumToNumOfCoins[] = new int[newSum + 1];
        Arrays.fill(sumToNumOfCoins, 0);
        int sumToNumOfCoinsRes[] = new int[newSum + 1];
        Arrays.fill(sumToNumOfCoinsRes, 0);
        for (Map.Entry<Double, ArrayList<Coin>> pair : coins.entrySet()) {
            for(int numOfCoin = 0; numOfCoin < pair.getValue().size(); numOfCoin++){
                sumToNumOfCoins = Arrays.copyOf(sumToNumOfCoinsRes, sumToNumOfCoinsRes.length);
                for(int s = 0; s <= newSum; s++){
                    int i = (int)(s - 20*pair.getKey());
                    if(i >= 0){
                        sumToNumOfCoinsRes[s] = ((sumToNumOfCoins[i] > 0 || i == 0) && ((1 + sumToNumOfCoins[i])>sumToNumOfCoins[s])) ?
                                1 + sumToNumOfCoins[i] : sumToNumOfCoins[s];
                    }
                }
        }
          }
        if(sumToNumOfCoinsRes[newSum] == 0){
            return 0;
        }
        int cur = newSum;


        while(cur > 0){
            for(Iterator<Map.Entry<Double, ArrayList<Coin>>> it = coins.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry<Double, ArrayList<Coin>> pair = it.next();

                int i = (int)(cur - 20*pair.getKey());
                if(i >= 0){
                    if(sumToNumOfCoinsRes[cur] == sumToNumOfCoinsRes[i] + 1){
                        pair.getValue().remove(0);
                        if(pair.getValue().isEmpty()){
                            it.remove();
                        }
                        cur = i;
                    }
                }
            }
        }
        return sum;
    }


    /**
     * @return the current total value of coins in the wallet
     */
    public double getWalletTotal() {
        double total = 0;
        for (Map.Entry<Double, ArrayList<Coin>> pair : coins.entrySet()) {
            total += pair.getKey()*pair.getValue().size();
        }
        return total;
    }


    /**
     * @return the number of coins in the wallet
     */
    public int getWalletSize() {
        int total = 0;
        for (Map.Entry<Double, ArrayList<Coin>> pair : coins.entrySet()) {
            total += pair.getValue().size();
        }
        return total;
    }


    /**
     * @modifies this
     * @effects Empties the the wallet. After this method is called,
     * 			both getWalletSize() and getWalletTotal() will return 0
     * 			if called
     */
    public void emptyWallet() {
        coins.clear();
    }


    /**
     * @return true if this wallet contains a coin with value "value"
     *  	   false otherwise
     */
    public boolean containsCoin(double value) {
        return coins.containsKey(value);
    }


    /**
     * @return true if this wallet contains an ammount of money with value "value"
     *  	   false otherwise
     */
    public boolean containsAmmount(double value) {
        return getWalletTotal() >= value;
    }



}