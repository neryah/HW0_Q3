import java.util.Scanner;

class CoinCollectionTest {

    public static void main(String[] args){
        
        CoinCollection myCoins = new CoinCollection();
        Scanner scan = new Scanner(System.in);
        char next = 'C';
        
        while(next != 'Q'){
                    
            System.out.print("Type 'A' to add a coin, 'T' to get collection total, 'S' to get collection size, 'E' to empty collection and 'Q' to quit. \n");

            next = scan.next().charAt(0);
            boolean result = false;
            switch(next) {

                case 'A':
                System.out.print("Enter the coin value");

                double val = scan.nextDouble();

                result = myCoins.addCoin(new Coin(val));
                
                if(!result){
                    System.out.print("The coin could not be added.\n");

                }

                break;

                
                case 'T':

                System.out.print("The collection total is: " + myCoins.getCollectionTotal() + "\n");


                break;

                
                case 'S':

                System.out.print("The collection size is: " + myCoins.getCollectionSize() + "\n");

                break;

                case 'E':

                myCoins.emptyCollection();

                break;

                case 'Q':

                scan.close();

                break;

                default:

                System.out.print("Please enter a valid option. \n");

            }
            

            
        }
    }
}