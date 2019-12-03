import java.util.Scanner;


public class WalletTest {
    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);
        

        Wallet myWallet = new Wallet();
        
        // Add two of each coin to wallet.
        for (int i = 0; i < 2; i++) {
            myWallet.addCoin(new Coin(0.05));
            myWallet.addCoin(new Coin(0.5));
            myWallet.addCoin(new Coin(1));
            myWallet.addCoin(new Coin(5));
            myWallet.addCoin(new Coin(10));
        }
        int done = 0;

        while(done != 1){
            System.out.print("You have " + myWallet.getWalletTotal() + " in your wallet.\n"); 
            // Should be 16.55

            System.out.print("You have " + myWallet.getWalletSize() + " coins in your wallet.\n"); 
            
            System.out.print("Enter the amount that you would like to pay from your wallet: \n");
            double price = scan.nextDouble();

            System.out.print("Enter the method of payment (pay:1, payMinimum:2, payExactMaximum:3): \n");
            int paymentMethod = scan.nextInt();
            double result = 0;

            switch(paymentMethod) {

                case 1:

                result = myWallet.pay(price);
                
                break;

                
                case 2:

                result = myWallet.payMinimum(price);

                break;

                
                case 3:

                result = myWallet.payExactMaximum(price);

                break;

            }

            System.out.print("You paid " + result + ".\n");
            System.out.print("My wallet has more than 17.1: " + myWallet.containsAmmount(17.1) +"\n" );
            System.out.print("My wallet contains a 5 coin: " + myWallet.containsCoin(5) +"\n");
            System.out.print("Enter 0 to continue, 1 to exit or 2 to empty wallet:\n");
            
            done = scan.nextInt();
            if(done == 2){
                myWallet.emptyWallet();
                System.out.print("Enter 0 to continue, 1 to exit or 2 to empty wallet:\n");
            }
            
            if(done == 1){
                scan.close();
            }

            
        }
    }
}