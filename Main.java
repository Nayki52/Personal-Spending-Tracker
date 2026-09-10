
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileWriter;

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

        frame.setSize(800, 500);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new BorderLayout());

        String[] columns = { "Name", "Category", "Amount" };

        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);

        JTable spendingsTable = new JTable(tableModel);

        spendingsTable.getColumnModel().getColumn(0).setCellRenderer(
                new javax.swing.table.DefaultTableCellRenderer() {
                    {
                        setHorizontalAlignment(SwingConstants.CENTER);
                    }
                });

        spendingsTable.getColumnModel().getColumn(1).setCellRenderer(
                new javax.swing.table.DefaultTableCellRenderer() {
                    {
                        setHorizontalAlignment(SwingConstants.CENTER);
                    }
                });

        spendingsTable.getColumnModel().getColumn(2).setCellRenderer(
                new javax.swing.table.DefaultTableCellRenderer() {
                    {
                        setHorizontalAlignment(SwingConstants.CENTER);
                    }
                });

        ((javax.swing.table.DefaultTableCellRenderer) spendingsTable.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(SwingConstants.CENTER);
        JScrollPane scrollPane = new JScrollPane(spendingsTable);

        JLabel allSpendingLabel = new JLabel("All Spending", SwingConstants.CENTER);

        frame.add(allSpendingLabel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel();

        JButton addButton = new JButton("Add spending");
        buttonsPanel.add(addButton);
        JButton removeButton = new JButton("Remove spending");
        buttonsPanel.add(removeButton);
        JButton statisticButton = new JButton("Statistics spending");
        buttonsPanel.add(statisticButton);
        JButton loadButton = new JButton("Load spendings");
        buttonsPanel.add(loadButton);
        JButton saveButton = new JButton("Save spendings");
        buttonsPanel.add(saveButton);

        frame.add(buttonsPanel, BorderLayout.SOUTH);
        addButton.addActionListener(event -> {
            JFrame addPanel = new JFrame();

            JLabel nameLabel = new JLabel("Name");
            JTextField nameField = new JTextField();
            JLabel categoryLabel = new JLabel("Category");
            JTextField categoryField = new JTextField();
            JLabel amountLabel = new JLabel("Amount");
            JTextField amountField = new JTextField();
            JButton okeyButton = new JButton("OK");

            okeyButton.addActionListener(e -> {
                String name = nameField.getText().trim();
                String category = categoryField.getText().trim();
                String amountText = amountField.getText().trim();

                if (name.isEmpty() && category.isEmpty() && amountText.isEmpty()) {
                    JOptionPane.showMessageDialog(addPanel, "Please fill in all fields", "Invalid input",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    double amount = Double.parseDouble(amountText);

                    if (amount > 0) {
                        Spending newSpending = new Spending(name, category, amount);
                        spendings.add(newSpending);
                        tableModel.addRow(new Object[] {
                                newSpending.getName(),
                                newSpending.getCategory(),
                                newSpending.getAmount()
                        });

                        addPanel.dispose();
                    }

                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(addPanel, "Amount must be a valid number", "Invalid amount",
                            JOptionPane.ERROR_MESSAGE);
                }
            });

            JButton cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(e -> {
                addPanel.dispose();
            });

            addPanel.setLayout(new GridLayout(4, 2));
            addPanel.add(nameLabel);
            addPanel.add(nameField);
            addPanel.add(categoryLabel);
            addPanel.add(categoryField);
            addPanel.add(amountLabel);
            addPanel.add(amountField);
            addPanel.add(okeyButton);
            addPanel.add(cancelButton);

            addPanel.setSize(300, 200);

            addPanel.setVisible(true);
        });

        removeButton.addActionListener(event -> {
            int selectedIndex = spendingsTable.getSelectedRow();
            if (selectedIndex < 0 || selectedIndex >= spendings.size()) {
                JOptionPane.showMessageDialog(frame, "Make a selction first", "Selection neccessary",
                        JOptionPane.WARNING_MESSAGE);
                return;
            } else {
                int result = JOptionPane.showConfirmDialog(frame, "Procied with delition?", "Confirmation",
                        JOptionPane.YES_NO_OPTION);

                // реакция на то, что пользователь выбрал в окне
                if (result == JOptionPane.YES_OPTION) {
                    spendings.remove(selectedIndex);
                    tableModel.removeRow(selectedIndex);
                }
            }
        });
        statisticButton.addActionListener(event -> {
            if (spendings.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Statistics are not available because the list is empty.");
                return;
            }

            double total = 0.0;
            Spending mostExpensive = spendings.get(0);

            for (Spending spending : spendings) {
                total += spending.getAmount();

                if (spending.getAmount() > mostExpensive.getAmount()) {
                    mostExpensive = spending;
                }
            }

            double average = total / spendings.size();

            String message = "Number of spendings: " + spendings.size() + "\nTotal: " + total + "\nAverage: " + average
                    + "\nMost expensive: " + mostExpensive;

            JOptionPane.showMessageDialog(frame, message, "Spending statistics", JOptionPane.INFORMATION_MESSAGE);
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
                            spendings.add(s);

                            tableModel.addRow(new Object[] {
                                    s.getName(),
                                    s.getCategory(),
                                    s.getAmount()
                            });
                        }

                    }
                }
            } catch (Exception e) {
                System.out.println("Error while loading spendings");
            }
        });
        saveButton.addActionListener(event -> {
            try {
                File file = new File(fileName);
                FileWriter writer = new FileWriter(file);

                for (Spending s : spendings) {
                    // Собираем строку в точности так, как её ожидает loadButton (через ";")
                    String line = s.getName() + ";" + s.getCategory() + ";" + s.getAmount();
                    writer.write(line + "\n");
                }

                writer.close(); // Обязательно закрываем файл после цикла

                JOptionPane.showMessageDialog(frame, "Spendings saved successfully!", "Success",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e) {
                System.out.println("Error while saving spendings");
            }
        });
        frame.setVisible(true);

    }
}
