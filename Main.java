
import java.util.ArrayList;
import java.util.Scanner;
//подключаем файл чтоби работать с фалом
import java.io.File;
// подключаем чтоби записивать фаил
import java.io.FileWriter;
// ловить ошибки при сохранении
import java.io.IOException;

class Main {

    public static ArrayList<Spending> spendings = new ArrayList<Spending>();
    public static Scanner sc = new Scanner(System.in);

    public static String fileName = "Spendings.txt";

    public static void saveSpendings() {
        try {
            FileWriter writer = new FileWriter(fileName);

            for (int i = 0; i < spendings.size(); i++) {
                Spending s = spendings.get(i);
                writer.write(s.getName() + ";" + s.getCategory() + ";" + s.getAmount() + "\n");
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error while saving spendings");
        }
    }

    public static void loadSpendings(){
        try {
            File file = new File(fileName);

            if (!file.exists()) {
                return;
            }
            else { 
                Scanner fileScanner = new Scanner(file);
                while(fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();

                    String[] parts = line.split(";");

                    if(parts.length == 3) {
                        String name = parts[0];
                        String category = parts[1];
                        double amount = Double.parseDouble(parts[2]);
                        Spending s = new Spending(name, category, amount);
                        spendings.add(s);
                    }
                    }
            }
        } catch (Exception e) {
        System.out.println("Error while loading spendings");
        }
    }

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
        saveSpendings();
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
        saveSpendings();
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
        loadSpendings();
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
