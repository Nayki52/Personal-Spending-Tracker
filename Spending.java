
public class Spending {

    String name;
    String category;
    double amount;

    public Spending(String name, String category, double amount) {
        this.name = name;
        this.category = category;
        this.amount = amount;
    }

    void SetName(String name) {
        this.name = name;
    }

    void SetCategory(String category) {
        this.category = category;
    }

    void SetAmount(double amount) {
        this.amount = amount;
    }

    String getName() {
        return name;
    }

    String getCategory() {
        return category;
    }

    double getAmount() {
        return amount;
    }

    void ShowInfo() {
        System.out.println("Name :" + name);
        System.out.println("Category :" + category);
        System.out.println("Amount :" + amount);
    }
}
