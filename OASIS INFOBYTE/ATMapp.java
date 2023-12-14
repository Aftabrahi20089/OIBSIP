import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class User {
    private String userId;
    private String password;

    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }
}

class Transaction {
    private String type;
    private double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }
}

class Account {
    private double balance;
    private String userId;
    private List<Transaction> transactionHistory;

    public Account(String userId) {
        this.userId = userId;
        this.balance = 1000.0; // Initial balance
        this.transactionHistory = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public String getUserId() {
        return userId;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add(new Transaction("Withdrawal", amount));
            System.out.println("Withdrawal successful. Remaining balance: " + balance);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
        }
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add(new Transaction("Deposit", amount));
            System.out.println("Deposit successful. New balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void transfer(Account recipient, double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            recipient.balance += amount;
            transactionHistory.add(new Transaction("Transfer to " + recipient.getUserId(), amount));
            System.out.println("Transfer successful. Remaining balance: " + balance);
        } else {
            System.out.println("Invalid transfer amount or insufficient funds.");
        }
    }
}

class ATMInterface {
    private User currentUser;
    private Account currentAccount;
    private Scanner scanner;

    public ATMInterface(User user, Account account) {
        this.currentUser = user;
        this.currentAccount = account;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("Welcome, " + currentUser.getUserId() + "!");
        System.out.println("1. Check Balance");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. Transfer");
        System.out.println("5. Transaction History");
        System.out.println("6. Exit");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Balance: " + currentAccount.getBalance());
                break;
            case 2:
                System.out.print("Enter withdrawal amount: ");
                double withdrawalAmount = scanner.nextDouble();
                currentAccount.withdraw(withdrawalAmount);
                break;
            case 3:
                System.out.print("Enter deposit amount: ");
                double depositAmount = scanner.nextDouble();
                currentAccount.deposit(depositAmount);
                break;
            case 4:
                System.out.print("Enter recipient's User ID: ");
                String recipientUserId = scanner.next();
                Account recipientAccount = new Account(recipientUserId);
                System.out.print("Enter transfer amount: ");
                double transferAmount = scanner.nextDouble();
                currentAccount.transfer(recipientAccount, transferAmount);
                break;
            case 5:
                displayTransactionHistory();
                break;
            case 6:
                System.out.println("Exiting. Thank you!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }

        displayMenu();
    }

    private void displayTransactionHistory() {
        List<Transaction> transactions = currentAccount.getTransactionHistory();
        System.out.println("Transaction History for " + currentUser.getUserId() + ":");
        for (Transaction transaction : transactions) {
            System.out.println(transaction.getType() + " - Amount: " + transaction.getAmount());
        }
    }
}

public class ATMapp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter User ID: ");
        String userId = scanner.next();
        System.out.print("Create Password: ");
        String password = scanner.next();

        // Create user and validate password
        User user = new User(userId, password);

        // Initialize user account
        Account account = new Account(userId);

        // Initialize and start ATM interface
        ATMInterface atmInterface = new ATMInterface(user, account);
        atmInterface.displayMenu();
    }
}