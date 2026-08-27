# 💰 Personal Spending Tracker

A simple Java desktop application for managing and tracking personal spendings.

## 📸 Screenshots

### Main Window

<!-- Insert main window screenshot here -->

<img width="912" height="612" alt="Знімок екрана 2026-08-27 о 20 55 53" src="https://github.com/user-attachments/assets/3e2cf8cb-def4-4722-b61c-13e74ab09d3a" />


The main window displays all current spendings and provides access to the main application functions.

---

## ⚙️ Features

### ➕ Add Spending

<!-- Insert screenshot here -->

<img width="412" height="312" alt="Знімок екрана 2026-08-27 о 20 56 00" src="https://github.com/user-attachments/assets/e975b157-138b-48b0-a67a-2d17a9b4238a" />


Add a new spending by entering its **name, category, and amount**.
Press **OK** to add the spending or **Cancel** to close the window.

### 🗑️ Remove Spending

<!-- Insert screenshot here -->


<img width="385" height="285" alt="Знімок екрана 2026-08-27 о 20 56 12" src="https://github.com/user-attachments/assets/c19c8ba2-ab37-43ac-830d-3e1d4cebb1fc" />


Select a spending from the list and press **Remove Spending**.
The application asks for confirmation before deleting the selected spending.

### 📊 Statistics Spending

<!-- Insert screenshot here -->

<img width="477" height="287" alt="Знімок екрана 2026-08-27 о 20 56 19" src="https://github.com/user-attachments/assets/f0b27d59-d5ea-47d4-8ee8-9c10aef14cbc" />


View basic spending statistics, including:

* Number of spendings
* Total amount spent
* Average spending
* Most expensive spending

### 📂 Load Spendings

<!-- Insert screenshot here -->

<img width="912" height="612" alt="Знімок екрана 2026-08-27 о 20 56 26" src="https://github.com/user-attachments/assets/6b516a44-3fe2-4613-a7a2-e20e3a15e353" />


Load previously saved spendings from the `Spendings.txt` file.

### 💾 Save Spendings

<!-- Insert screenshot here -->

<img width="442" height="285" alt="Знімок екрана 2026-08-27 о 20 56 32" src="https://github.com/user-attachments/assets/9a2c71ae-3767-48d5-834a-1c148de950f6" />


Save all current spendings to the `Spendings.txt` file so they can be loaded later.

---

## 🛠️ Technologies

* Java
* Java Swing
* ArrayList
* File I/O
* Object-Oriented Programming

## 📂 Project Structure

```text
Personal-Spending-Tracker/
├── Main.java
├── Spending.java
├── SpendingService.java
├── Spendings.txt
└── README.md
```

## 🚀 How to Run

```bash
git clone https://github.com/Nayki52/Personal-Spending-Tracker.git
cd Personal-Spending-Tracker
javac *.java
java Main
```

## 👨‍💻 Author

**Nayki52**

Created as a Java project for learning and portfolio purposes.
