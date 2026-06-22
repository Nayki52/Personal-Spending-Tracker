
import java.util.ArrayList;
import java.util.Scanner;

class Main {

    public static ArrayList<Spending> spendings = new ArrayList<Spending>();
    public static Scanner sc = new Scanner(System.in);

    public static void addSpending() {
        String name;
        String category = "";
        double amount = 0.0;
        sc.nextLine(); //Отшипивает символ от новой строки
        System.out.print("Enter the name ");
        name = sc.nextLine();
        System.out.print("Enter the category ");
        category = sc.nextLine();
        System.out.print("Enter the ammount ");
        amount = sc.nextDouble();

        Spending spending = new Spending(name, category, amount);
        spendings.add(spending);
    }

    public static void showSpending() {
        for (int i = 0; i < spendings.size(); i++) {
            System.out.println("index :" + i);
            spendings.get(i).ShowInfo();
            System.out.println("\n");
        }
    }

    public static void removeSpending() {
        if (spendings.isEmpty()) {
            System.out.println("The list is empty. Nothing to remove.");
            return;
        }
        System.out.println("\n--- Current Spendings ---");
        showSpending();

        System.out.print("Enter the index of the spending you want to remove: ");
        int index = sc.nextInt();

        if (index >= 0 && index < spendings.size()) {
            spendings.remove(index);
            System.out.println("Spending at index " + index + " successfully removed!");
        } else {
            System.out.println("Error: Invalid index! There is no spending with this index.");
        }
    }

    public static void statisticsSpending() {
        if (!spendings.isEmpty()) {
            System.out.println("Statistics are not available. The list is empty.");

            double totalSum = 0;
            double maxAmount = 0.0;
            String maxSpendingName = "";

            for (int i = 0; i < spendings.size(); i++) {
                Spending s = spendings.get(i);
                totalSum += s.getAmount();

                if (s.getAmount() > maxAmount) {
                    maxAmount = s.getAmount();
                    maxSpendingName = s.getName();
                }
            }
            double averageSpending = totalSum / spendings.size();

            System.out.println("\n=== Financial Statistics ===");
            System.out.println("Number of spending" + spendings.size());
            System.out.println("Total expenses:" + totalSum);
            System.out.println("Average check: " + averageSpending);
            System.out.println("Most expensive item: " + maxSpendingName + maxAmount);
        }
    }

    public static void ShowMenu() {
        System.out.println("=== Personal Spending Tracker ===");
        System.out.println("1. Add spending");
        System.out.println("2. Show all spendings");
        System.out.println("3. Remove spending");
        System.out.println("4. Show statistics");
        System.out.println("5. Exit");
        System.out.print("Choose an option :");
    }

    public static void main(String[] args) {
        while (true) {
            ShowMenu();
            int choice = sc.nextInt();
            if (choice == 1) {
                addSpending();
            } else if (choice == 2) {
                showSpending();
            } else if (choice == 3) {
                removeSpending();
            } else if (choice == 4) {
                statisticsSpending();
            } else if (choice == 5) {
                System.out.println("Good bye");
                break;
            } else {
                System.out.println("Invalid option. Choose a number from 1 to 5!");
            }
        }
    }
}
