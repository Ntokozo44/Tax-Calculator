import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private final JTextField incomeTextField;
    private final JComboBox<String> paymentComboBox;
    private final JButton calculateButton;
    private final JLabel resultLabel;
    private final JLabel incomeTaxLabel;

    Color DarkBlue = new Color(23, 107, 135);

    Color offWhite = new Color(238,238,238);



    public Main() {
        setTitle("Tax Calculator");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(DarkBlue);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Heading
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(DarkBlue);
        JLabel title = new JLabel("Tax Calculator");
        title.setForeground(offWhite);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(title);
        mainPanel.add(titlePanel, BorderLayout.NORTH);

        // Content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(DarkBlue);
        mainPanel.add(contentPanel, BorderLayout.CENTER);


        // Income panel
        JPanel incomePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        incomePanel.setBackground(DarkBlue);
        JLabel income = new JLabel("Income:");
        income.setForeground(offWhite);
        incomePanel.add(income);
        incomeTextField = new JTextField(10);
        incomePanel.add(incomeTextField);
        contentPanel.add(incomePanel);

        // Payment frequency panel
        JPanel paymentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        paymentPanel.setBackground(DarkBlue);
        JLabel pay = new JLabel("Payment Frequency:");
        pay.setForeground(offWhite);
        paymentPanel.add(pay);
        paymentComboBox = new JComboBox<>(new String[]{"Monthly", "Yearly"});
        paymentPanel.add(paymentComboBox);
        contentPanel.add(paymentPanel);

        // Calculate button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(DarkBlue);
        calculateButton = new JButton("Calculate");
        calculateButton.setForeground(Color.BLUE);
        buttonPanel.add(calculateButton);
        contentPanel.add(buttonPanel);

        // Result panel
        JPanel resultPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        resultPanel.setBackground(DarkBlue);
        JLabel result = new JLabel("Tax Amount: R");
        result.setForeground(offWhite);
        resultPanel.add(result);
        resultLabel = new JLabel();
        resultLabel.setForeground(offWhite);
        resultPanel.add(resultLabel);
        contentPanel.add(resultPanel);

        //Income After Tax Panel
        JPanel afterTaxPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        afterTaxPanel.setBackground(DarkBlue);
        JLabel incomeAfterTaxLabel = new JLabel("Income After Tax: R");
        incomeAfterTaxLabel.setForeground(offWhite);
        afterTaxPanel.add(incomeAfterTaxLabel);
        incomeTaxLabel = new JLabel();
        incomeTaxLabel.setForeground(offWhite);
        afterTaxPanel.add(incomeTaxLabel);
        contentPanel.add(afterTaxPanel);

        // Add main panel to the frame
        add(mainPanel);

        // Calculate button action listener
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateTax();
            }
        });
    }

    private void calculateTax() {
        String incomeInput = incomeTextField.getText();
        String paymentFrequency = (String) paymentComboBox.getSelectedItem();



        double income = 0.0;

        // Parse age and income values
        try {
            income = Double.parseDouble(incomeInput);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid income value.");
            return;
        }


        double taxAmount = 0.0;
        double incomeAfterTax = 0.0;


            if (paymentFrequency.equals("Monthly")) {
                double monthlyIncome = income * 12.0;
                taxAmount = calculateTax(monthlyIncome) / 12;
                incomeAfterTax = income - taxAmount;
            } else if (paymentFrequency.equals("Yearly")) {
                taxAmount = calculateTax(income);
                incomeAfterTax = income - taxAmount;
            }


        resultLabel.setText(String.format("%.2f", taxAmount));
        incomeTaxLabel.setText(String.format("%.2f", incomeAfterTax));
    }

    private double calculateTax(double income) {
        double tax;

        if (income <=0 ){
            JOptionPane.showMessageDialog(null, "Invalid income amount. Please enter a positive number.");
            return 0;
        }

        if (income <= 95000){
            tax = income * 0;
        }
        else if (income <= 237100) {
            tax = income * 0.18;
        } else if (income <= 370500) {
            tax = 42678 + (income - 237100) * 0.26;
        } else if (income <= 512800) {
            tax = 77362 + (income - 370500) * 0.31;
        } else if (income <= 673000) {
            tax = 121475 + (income - 512800) * 0.36;
        } else if (income <= 857900) {
            tax = 179147 + (income - 673000) * 0.39;
        } else if (income <= 1817000) {
            tax = 251258 + (income - 857900) * 0.41;
        } else {
            tax = 644489 + (income - 1817000) * 0.45;
        }

        return tax;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}