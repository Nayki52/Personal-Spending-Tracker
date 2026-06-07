
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.text.html.HTMLDocument;

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
            spendings.get(i).ShowInfo();
            System.out.println("\n");
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

            } else if (choice == 4) {

            } else if (choice == 5) {
                System.out.println("Good buye");
                break;
            } else {
                System.out.println("Invalid option. Choose a number from 1 to 5!");
            }
        }
    }
}
