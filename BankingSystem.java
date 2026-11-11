
public class BankingSystem {

    public static void main(String[] args) {

        // Create the shared bank account
        BankAccount account = new BankAccount(100000);

        // Create another account for receiving transfers
        BankAccount anotherAccount = new BankAccount(0);

        // Thread A: Withdrawal
        Thread customerA = new Thread(() -> {
            account.withdraw(20000);
        });

        // Thread B: Deposit
        Thread customerB = new Thread(() -> {
            account.deposit(15000);
        });

        // Thread C: Money transfer
        Thread customerC = new Thread(() -> {
            account.transferTo(anotherAccount, 30000);
        });

        // Thread D: Withdrawal
        Thread customerD = new Thread(() -> {
            account.withdraw(25000);
        });

        // Thread E: Online purchase
        Thread customerE = new Thread(() -> {
            account.withdraw(10000);
            System.out.println("Online purchase completed.");
        });

        // Thread F: Balance inquiry
        Thread customerF = new Thread(() -> {
            System.out.println(
                "Current balance: KSh " +
                account.getBalance()
            );
        });

        // Start all threads
        customerA.start();
        customerB.start();
        customerC.start();
        customerD.start();
        customerE.start();
        customerF.start();

        // Wait for all threads to finish
        try {
            customerA.join();
            customerB.join();
            customerC.join();
            customerD.join();
            customerE.join();
            customerF.join();

        } catch (InterruptedException e) {
            System.out.println("Thread interrupted.");
            Thread.currentThread().interrupt();
        }

        // Display final balance
        System.out.println(
            "Final balance: KSh " +
            account.getBalance()
        );

        System.out.println(
            "Destination account balance: KSh " +
            anotherAccount.getBalance()
        );
    }
}
