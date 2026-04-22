// ============================================================
//  BankManagementSystem.java — Main Entry Point
//  Demonstrates: Encapsulation, Abstraction, Inheritance,
//                Polymorphism
// ============================================================
public class BankManagementSystem {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║       JAVA BANK MANAGEMENT SYSTEM            ║");
        System.out.println("║  OOP: Encapsulation | Abstraction            ║");
        System.out.println("║       Inheritance   | Polymorphism           ║");
        System.out.println("╚══════════════════════════════════════════════╝\n");

        // ── 1. ABSTRACTION ────────────────────────────────────
        // Bank hides complex logic; we just call clean methods
        Bank bank = new Bank("Dhaka National Bank");

        // ── 2. INHERITANCE ────────────────────────────────────
        // All three classes inherit from Account
        SavingsAccount savings = bank.openSavingsAccount("Rahim Uddin",   10000.0, 0.06);
        CurrentAccount current = bank.openCurrentAccount("Karim Hossain", 25000.0, 5000.0);
        LoanAccount    loan    = bank.openLoanAccount   ("Nasrin Begum",  50000.0, 0.12, 24);

        System.out.println();

        // ── 3. ENCAPSULATION ──────────────────────────────────
        // Direct field access is blocked; we use getters/setters
        System.out.println("── Encapsulation Demo ──────────────────────────");
        System.out.println("Holder  : " + savings.getHolderName());
        System.out.println("Balance : ৳" + savings.getBalance());
        System.out.println("Min Bal : ৳" + savings.getMinimumBalance());
        System.out.println("Overdraft Limit: ৳" + current.getOverdraftLimit());
        System.out.println("Loan EMI: ৳" + String.format("%.2f", loan.getMonthlyEMI()));
        System.out.println();

        // ── 4. POLYMORPHISM ───────────────────────────────────
        // withdraw() behaves differently for each account type
        System.out.println("── Polymorphism Demo — withdraw() ──────────────");
        savings.deposit(5000);
        savings.withdraw(3000);       // Savings: checks minimum balance
        current.withdraw(28000);      // Current: uses overdraft
        loan.withdraw(5000);          // Loan:    treats as repayment

        System.out.println();

        // Savings-specific behaviour
        System.out.println("── Savings Interest Applied ────────────────────");
        savings.applyInterest();

        System.out.println();

        // Transfer between accounts
        System.out.println("── Transfer Demo ───────────────────────────────");
        bank.transfer(savings.getAccountNumber(), current.getAccountNumber(), 2000);

        System.out.println();

        // Polymorphic getSummary() — correct version called at runtime
        bank.printAllSummaries();

        // ── Error handling demo ───────────────────────────────
        System.out.println("── Error Handling Demo ─────────────────────────");
        try {
            savings.withdraw(100000);   // Should fail: below min balance
        } catch (IllegalStateException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
        try {
            current.withdraw(50000);    // Should fail: exceeds overdraft
        } catch (IllegalStateException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }

        System.out.println("\n✔  All OOP concepts demonstrated successfully.");
    }
}
