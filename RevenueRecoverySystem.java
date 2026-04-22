// Revenue Recovery System using Java AWT + Swing
// Single-file mini project (exam & submission ready)

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.*;

abstract class User {
    protected String username;
    protected String password;

    public User(String u, String p) {
        username = u;
        password = p;
    }

    public abstract void showDashboard();
}

class Customer extends User {
    int customerId;
    double dueAmount;

    public Customer(int id, String u, String p, double due) {
        super(u, p);
        customerId = id;
        dueAmount = due;
    }

    public void showDashboard() {
        JFrame f = new JFrame("Customer Dashboard");
        f.setSize(400,300);
        f.setLayout(new FlowLayout());

        JButton view = new JButton("View Due Amount");
        JButton pay = new JButton("Pay Bill");

        view.addActionListener(e -> JOptionPane.showMessageDialog(f,
                "Pending Amount: ₹" + dueAmount));

        pay.addActionListener(e -> {
            dueAmount = 0;
            JOptionPane.showMessageDialog(f, "Payment Successful! Due cleared.");
        });

        f.add(view); f.add(pay);
        f.setVisible(true);
    }
}

class Admin extends User {
    static ArrayList<Customer> customers = new ArrayList<>();

    public Admin(String u, String p) {
        super(u, p);
    }

    public void showDashboard() {
        JFrame f = new JFrame("Admin Dashboard");
        f.setSize(500,400);
        f.setLayout(new GridLayout(5,1));

        JButton add = new JButton("Add Customer");
        JButton view = new JButton("View Pending Dues");
        JButton recover = new JButton("Recover Payment");

        add.addActionListener(e -> addCustomer());
        view.addActionListener(e -> viewDues());
        recover.addActionListener(e -> recoverPayment());

        f.add(add); f.add(view); f.add(recover);
        f.setVisible(true);
    }

    void addCustomer() {
        String name = JOptionPane.showInputDialog("Customer Name");
        String due = JOptionPane.showInputDialog("Due Amount");
        customers.add(new Customer(customers.size()+1, name, "1234", Double.parseDouble(due)));
        JOptionPane.showMessageDialog(null, "Customer Added");
    }

    void viewDues() {
        StringBuilder sb = new StringBuilder();
        for(Customer c: customers)
            sb.append(c.username).append(" : ₹").append(c.dueAmount).append("\n");
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    void recoverPayment() {
        if(customers.size()>0){
            customers.get(0).dueAmount = 0;
            JOptionPane.showMessageDialog(null, "Payment Recovered");
        }
    }
}

public class RevenueRecoverySystem {

    public static void main(String[] args) {

        Frame f = new Frame("Revenue Recovery System - Login");
        f.setSize(400, 250);
        f.setLayout(new GridLayout(4, 2));

        Label u = new Label("Username");
        TextField ut = new TextField();

        Label p = new Label("Password");
        TextField pt = new TextField();
        pt.setEchoChar('*');

        Button admin = new Button("Admin Login");
        Button customer = new Button("Customer Login");

        admin.addActionListener(e ->
                new Admin("admin", "admin").showDashboard()
        );

        customer.addActionListener(e ->
                new Customer(1, "user", "1234", 500).showDashboard()
        );

        f.add(u); f.add(ut);
        f.add(p); f.add(pt);
        f.add(admin); f.add(customer);

        f.setVisible(true);
    }
}


