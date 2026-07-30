import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SpendingService {

    public String fileName = "Spendings.txt";

    public Scanner sc = new Scanner(System.in);

    public ArrayList<Spending> spendings = new ArrayList<Spending>();

    public SpendingService() {
        // Do we need it?
    }

    public void saveSpendings() {
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

    public void loadSpendings() {
        try {
            File file = new File(fileName);

            if (!file.exists()) {
                return;
            } else {
                Scanner fileScanner = new Scanner(file);
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();

                    String[] parts = line.split(";");

                    if (parts.length == 3) {
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

    public void addSpending() {
        String name;
        String category = "";
        double amount = 0.0;
        sc.nextLine(); // Отшипивает символ от новой строки
        name = readtext("Enter the name ");
        category = readtext("Enter the category ");
        amount = readAmount("Enter the ammount ");

        Spending spending = new Spending(name, category, amount);
        spendings.add(spending);
        saveSpendings();
    }

    public void showSpending() {
        for (int i = 0; i < spendings.size(); i++) {
            System.out.println("index :" + i);
            spendings.get(i).ShowInfo();
            System.out.println("\n");
        }
    }

    public void removeSpending() {
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

    public void statisticsSpending() {
        if (!spendings.isEmpty()) {

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
        } else {
            System.out.println("Statistics are not available. The list is empty.");
        }
    }

    public String readtext(String messages) {
        String text;
        while (true) {
            System.out.println(messages);
            text = sc.nextLine();
            if (!text.trim().isEmpty()) {
                return text;
            }
            System.out.println("Input can't be empty, try again");
        }
    }

    public double readAmount(String message) {
        while (true) {
            System.out.println(message);
            if (sc.hasNextDouble()) {
                double amount = sc.nextDouble();
                sc.nextLine();
                if (amount > 0 && amount < 1000000) {
                    return amount;
                }
            } else {
                System.out.println("Please enter valid amount");
                sc.nextLine();
            }
        }
    }
}
