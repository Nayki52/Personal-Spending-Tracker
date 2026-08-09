
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.io.File;

class Main {
    public static String fileName = "Spendings.txt";

    public static ArrayList<Spending> spendings = new ArrayList<Spending>();

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

        frame.setSize(700, 500);

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
        JButton loadButton = new JButton("Load spendings");
        buttonsPanel.add(loadButton);

        frame.add(buttonsPanel, BorderLayout.SOUTH);
        addButton.addActionListener(event -> {
            // TO DO
        });
        removeButton.addActionListener(event -> {
            int selectedIndex = spendingList.getSelectedIndex();
            if (selectedIndex < 0 || selectedIndex >= spendings.size()) {
                JOptionPane.showMessageDialog(frame, "Index out of bounderies", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                int result = JOptionPane.showConfirmDialog(frame, "Procied with delition?", "Confirmation",
                        JOptionPane.YES_NO_OPTION);

                // реакция на то, что пользователь выбрал в окне
                if (result == JOptionPane.YES_OPTION) {
                    spendings.remove(selectedIndex);
                    listModel.remove(selectedIndex);
                }
            }
        });
        statisticButton.addActionListener(event -> {
            // TO DO
        });
        loadButton.addActionListener(event -> {
            try {
                File file = new File(fileName);

                if (!file.exists()) {
                    JOptionPane.showMessageDialog(frame, "File " + fileName + " Doesnt exist", "Error",
                            JOptionPane.ERROR_MESSAGE);
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
                            listModel.addElement(s.toString());
                            spendings.add(s);
                        }

                    }
                }
            } catch (Exception e) {
                System.out.println("Error while loading spendings");
            }
        });
        frame.setVisible(true);

    }
}
