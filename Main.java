
import java.util.Scanner;

class Main {

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
        Scanner sc = new Scanner(System.in);

        while (true) {
            ShowMenu();
            int choice = sc.nextInt();
            if (choice == 1) {

            } else if (choice == 2) {

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
