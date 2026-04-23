import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

// ============================================================
//  1. OOP CORE CLASSES
// ============================================================
abstract class Account {
    private String accountNumber;
    private String holderName;
    protected double balance;

    public Account(String holderName, double initialDeposit) {
        this.accountNumber = "BNK-" + (1000 + new Random().nextInt(9000));
        this.holderName = holderName;
        this.balance = initialDeposit;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getHolderName() { return holderName; }
    public double getBalance() { return balance; }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(">> Success: ৳" + amount + " deposited.");
        }
    }

    public abstract void withdraw(double amount);

    public void displaySummary() {
        System.out.println("[" + accountNumber + "] " + holderName + " | Balance: ৳" + balance);
    }
}

class SavingsAccount extends Account {
    private final double minBalance = 500.0;

    public SavingsAccount(String name, double balance) {
        super(name, balance);
    }

    @Override
    public void withdraw(double amount) {
        if (balance - amount < minBalance) {
            throw new IllegalStateException("Insufficient funds! Must keep ৳" + minBalance + " minimum.");
        }
        balance -= amount;
        System.out.println(">> Success: Withdrew ৳" + amount);
    }
}

// ============================================================
//  2. MAIN INTERACTIVE CLASS
// ============================================================
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Account> accounts = new ArrayList<>();

        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║       WELCOME TO INTERACTIVE JAVA BANK       ║");
        System.out.println("╚══════════════════════════════════════════════╝");

        // --- STEP 1: TAKING INPUT FOR ACCOUNT CREATION ---
        System.out.print("\nEnter Account Holder Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Initial Deposit Amount: ৳");
        double initialDeposit = scanner.nextDouble();

        // Create the account based on user input
        SavingsAccount userAccount = new SavingsAccount(name, initialDeposit);
        accounts.add(userAccount);

        System.out.println("\n✔ Account Created Successfully!");
        userAccount.displaySummary();

        // --- STEP 2: INTERACTIVE MENU ---
        boolean running = true;
        while (running) {
            System.out.println("\n--- SELECT AN OPERATION ---");
            System.out.println("1. Deposit Money");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Choice: ");
            
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to deposit: ৳");
                    double dAmount = scanner.nextDouble();
                    userAccount.deposit(dAmount);
                    break;

                case 2:
                    System.out.print("Enter amount to withdraw: ৳");
                    double wAmount = scanner.nextDouble();
                    try {
                        userAccount.withdraw(wAmount);
                    } catch (IllegalStateException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println("\n--- ACCOUNT STATUS ---");
                    userAccount.displaySummary();
                    break;

                case 4:
                    System.out.println("Thank you for using Java Bank. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }
}