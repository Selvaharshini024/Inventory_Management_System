import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class InventoryManagementSystem extends JFrame {
    private JTextField itemNameField, itemQuantityField, itemPriceField;
    private DefaultTableModel tableModel;
    private JTable inventoryTable;
    public InventoryManagementSystem() {
        // Frame setup
        setTitle("Inventory Management System");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // Panel for input fields
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        // Labels and text fields
        inputPanel.add(new JLabel("Item Name:"));
        itemNameField = new JTextField();
        inputPanel.add(itemNameField);
        inputPanel.add(new JLabel("Quantity:"));
        itemQuantityField = new JTextField();
        inputPanel.add(itemQuantityField);
        inputPanel.add(new JLabel("Price:"));
        itemPriceField = new JTextField();
        inputPanel.add(itemPriceField);
        // Add and Delete buttons
        JButton addButton = new JButton("Add Item");
        addButton.addActionListener(new AddButtonListener());
        inputPanel.add(addButton);
        JButton deleteButton = new JButton("Delete Item");
        deleteButton.addActionListener(new DeleteButtonListener());
        inputPanel.add(deleteButton);
        // Table for displaying items
        tableModel = new DefaultTableModel(new String[]{"Item Name", "Quantity", "Price"}, 0);
        inventoryTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(inventoryTable);
        // Layout setup
        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);
    }
    // Add item to the table
    private class AddButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String itemName = itemNameField.getText();
            String itemQuantity = itemQuantityField.getText();
            String itemPrice = itemPriceField.getText();
            // Check if fields are not empty
            if (!itemName.isEmpty() && !itemQuantity.isEmpty() && !itemPrice.isEmpty()) {
                // Add item to table
                tableModel.addRow(new Object[]{itemName, itemQuantity, itemPrice});
                clearFields();
            } else {
                JOptionPane.showMessageDialog(null, "Please fill all fields.");
            }
        }
    }
    // Delete selected item from the table
    private class DeleteButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int selectedRow = inventoryTable.getSelectedRow();
            if (selectedRow != -1) { // Ensure a row is selected
                tableModel.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(null, "Please select an item to delete.");
            }
        }
    }
    // Clear text fields
    private void clearFields() {
        itemNameField.setText("");
        itemQuantityField.setText("");
        itemPriceField.setText("");
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InventoryManagementSystem app = new InventoryManagementSystem();
            app.setVisible(true);
        });
    }
}
