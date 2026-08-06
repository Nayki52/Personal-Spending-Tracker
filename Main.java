
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.BorderLayout;

class Main {

    public static Scanner sc = new Scanner(System.in);

    public static SpendingService service = new SpendingService();

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
        JFrame frame = new JFrame("Personal-Spending-Tracker");

        frame.setSize(500, 300);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new BorderLayout());

        DefaultListModel<String> listModel = new DefaultListModel<>();

        JList<String> spendingList = new JList<>(listModel);
        JPanel spendigsPanel = new JPanel();
        spendigsPanel.setLayout(new BoxLayout(spendigsPanel, BoxLayout.Y_AXIS));
        JLabel spendingLabel = new JLabel("All Spendings");
        JScrollPane horizontalPane = new JScrollPane(spendingList);
        spendigsPanel.add(spendingLabel);
        spendigsPanel.add(horizontalPane);
        frame.add(spendigsPanel, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel();

        JButton addButton = new JButton("Add spending");
        buttonsPanel.add(addButton);
        JButton removeButton = new JButton("Remove spending");
        buttonsPanel.add(removeButton);
        JButton statisticButton = new JButton("Statistics spending");
        buttonsPanel.add(statisticButton);

        frame.add(buttonsPanel, BorderLayout.SOUTH);
        frame.setVisible(true);

        service.loadSpendings();
        while (true) {
            ShowMenu();
            int choice = sc.nextInt();
            if (choice == 1) {
                service.addSpending();
            } else if (choice == 2) {
                service.showSpending();
            } else if (choice == 3) {
                service.removeSpending();
            } else if (choice == 4) {
                service.statisticsSpending();
            } else if (choice == 5) {
                System.out.println("Good bye");
                break;
            } else {
                System.out.println("Invalid option. Choose a number from 1 to 5!");
            }
        }
    }
}
