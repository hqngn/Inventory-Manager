/**
 * Name: Huy Nguyen
 * Email: hqnguyen.cs@gmail.com
 * 
 * This file contains the required classes and constructors
 * to produce the user interface of the inventory manager as
 * well as provide the functionality of the inventory manager
 */

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import java.text.ParseException;

import java.util.Map;
import java.util.TreeMap;

/**
 * This class produces the primary viewing 
 * frame for the inventory manager 
 * 
 * Static Variables:
 * BACKGROUND_COLOR - The background color of the frame
 * FOREGROUND_COLOR - The foreground color of the frame
 * FONT - The font of the elements of the frame
 * MIN_DIMENSIONS - The minimum dimensions of the frame
 * 
 * Instance Variables:
 * items - A TreeMap storing and organizing the items alphabetically
 * mainPane - The main viewing pane of the inventory manager
 * mainPanel - The main viewing panel of the inventory manager
 * menuPanel - The panel containing the menu buttons of the inventory manager
 */
public class InventoryManagerApplication extends JFrame {

    /** Static Variables */

    private static final Color BACKGROUND_COLOR = Color.decode("#171738");
    private static final Color FOREGROUND_COLOR = Color.decode("#FDFDFF");
    private static final Font FONT 
            = new Font("Helvetica", Font.CENTER_BASELINE, 12);
    private static final Dimension MIN_DIMENSIONS
            = new Dimension(1200, 700);

    /** Instance Variables */

    private TreeMap<String, Item> items;
    private JScrollPane mainPane;
    private JPanel mainPanel;
    private JPanel menuPanel;

    /**
     * This class produces an item object containing the quantity of a
     * particular item and also holds it's item-specific button for editing
     * 
     * Instance Variables:
     * num - The number of the item
     * button - The button associated with the item
     */
    public class Item {

        /** Instance Variables */

        private int num;
        private ItemButton button;

        /**
         * This produces a new item object with the
         * button provided and the quantity set to 0
         * 
         * @param button The button associated with the item
         */
        public Item(ItemButton button) {

            this.num = 0;
            this.button = button;

        }

        /**
         * This method sets the quantity of the item to the provided number
         * 
         * @param num The number to set the quantity to
         */
        public void setNum(int num) {

            this.num = num;

        }

        /**
         * This method returns the quantity of the item
         * 
         * @return The quantity of the item
         */
        public int getNum() {

            return num;

        }

        /**
         * This method returns the button associated with the item
         * 
         * @return The button associated with the item
         */
        public ItemButton getButton() {

            return button;

        }

    }

    /**
     * This class produces an item specific button for the inventory manager
     * 
     * Instance Variables:
     * parentFrame - The viewing frame the button will be displayed on
     * itemName - The name of the item associated with the button
     * item - The item associated with the button
     * panel - The ItemPanel that the button will show upon being pressed
     */
    public class ItemButton extends JButton {

        /** Instance Variables */

        private InventoryManagerApplication parentFrame;
        private String itemName;
        private Item item;
        private ItemPanel panel;

        /**
         * This constructor produces an item specific button,
         * altering the appearance and functional of a JButton
         * 
         * @param parentFrame
         */
        public ItemButton(InventoryManagerApplication parentFrame) {

            super();
    
            this.parentFrame = parentFrame;
            this.item = new Item(this);
            this.panel = new ItemPanel(parentFrame, this);

            // Alter Appearance
    
            setBackground(parentFrame.getForeground());
            setForeground(parentFrame.getBackground());
    
            setFont(parentFrame.getFont());
    
            setFocusable(false);
    
            // Alter Function

            addActionListener(e -> {
    
                itemEdit(getParentFrame(), panel);
    
            });
    
        }
    
        /**
         * This method returns the frame in which the button is located
         * 
         * @return The frame in which is the button is located
         */
        public InventoryManagerApplication getParentFrame() {
        
            return parentFrame;
        
        }

        /**
         * This method returns the name of the item associated with the button
         * 
         * @return The name of the item
         */
        @Override
        public String getName() {

            return itemName;

        }

        /**
         * This method sets the name of the item associated with the button
         * 
         * @param itemName The new name of the item associated with the button
         */
        @Override
        public void setName(String itemName) {

            this.itemName = itemName;

        }

        /**
         * This method alters the item associated with the button
         * 
         * @param item The item to be associated with the button
         */
        public void setItem(Item item) {

            this.item = item;

        }
        
        /**
         * This method returns the item associated with the button
         * 
         * @return The item associated with the button
         */
        public Item getItem() {

            return item;

        }

        /**
         * This method returns the customization
         * panel associated with the button
         * 
         * @return The customization panel associated with the button
         */
        public ItemPanel getPanel() {

            return panel;

        }
    
    }

    /**
     * This class produces a customization panel
     * specific to an item for the inventory manager
     * 
     * Instance Variables: 
     * button - The button associated with the customization panel and item
     */
    public class ItemPanel extends JPanel {

        /** Instance Variables */

        private ItemButton button;

        /**
         * This constructor produces an item specific customization
         * panel, altering the appearance and function of a JPanel
         * 
         * @param parentFrame The viewing frame the panel is displayed on
         * @param button The button associated with the customization panel
         */
        public ItemPanel(InventoryManagerApplication parentFrame,
                ItemButton button) {
    
            super(new GridLayout(0, 1, 10, 10));
    
            this.button = button;

            // Alter Appearance
    
            setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
            setBackground(parentFrame.getBackground());
            setForeground(parentFrame.getForeground());
            setFont(FONT);
    
            // Create and Alter Name Label

            final JLabel nameLabel = new JLabel
                    ("Name of Item (Max 20 Characters; Min 1 Character):");
            
            nameLabel.setFont(FONT);
            nameLabel.setForeground(FOREGROUND_COLOR);
            nameLabel.setHorizontalAlignment(JLabel.CENTER);

            // Create and Alter Name Input Field

            final JFormattedTextField inventoryName
                    = new JFormattedTextField();

            inventoryName.setHorizontalAlignment(JFormattedTextField.CENTER);
            inventoryName.setText(button.getName());

            // Create and Alter Number Label

            final JLabel numLabel = new JLabel("Number of Items:");

            numLabel.setFont(FONT);
            numLabel.setForeground(FOREGROUND_COLOR);
            numLabel.setHorizontalAlignment(JLabel.CENTER);

            // Create and Alter Number Input Field
    
            final SpinnerNumberModel inventoryEdit = new SpinnerNumberModel();
            final JSpinner inventoryEditSpinner = new JSpinner(inventoryEdit);

            inventoryEdit.setValue(button.getItem().getNum());

            // Create and Alter Confirmation Button
    
            final JButton inventoryConfirm = new JButton("Confirm");

            inventoryConfirm.setBackground(FOREGROUND_COLOR);
            inventoryConfirm.setForeground(BACKGROUND_COLOR);
            inventoryConfirm.setFont(FONT);
            inventoryConfirm.setFocusable(false);
            inventoryConfirm.addActionListener(e -> {
                
                nameLabel.setText("Name of Item (Max 20 Characters" 
                        + "; Min 1 Character):");
                nameLabel.setFont(FONT);
                nameLabel.setForeground(FOREGROUND_COLOR);
                nameLabel.setHorizontalAlignment(JLabel.CENTER);

                numLabel.setText("Number of Items:");
                numLabel.setFont(FONT);
                numLabel.setForeground(FOREGROUND_COLOR);
                numLabel.setHorizontalAlignment(JLabel.CENTER);

                String name = inventoryName.getText(); 
                
                Boolean flag = false;

                // Error Checking
    
                try {

                    inventoryEditSpinner.commitEdit();

                } catch (ParseException e1) {

                    numLabel.setText("INVALID NUMBER");
                    numLabel.setForeground(Color.RED);
                    flag = true;

                }

                if (name.length() > 20) {

                    nameLabel.setText("TOO MANY CHARACTERS");
                    nameLabel.setForeground(Color.RED);
                    flag = true;

                } 

                if (name.length() <= 0) {

                    nameLabel.setText("TOO FEW CHARACTERS");
                    nameLabel.setForeground(Color.RED);
                    flag = true;

                }

                if (flag) {
                    
                    return;

                }

    
                int num = (int) inventoryEdit.getNumber();

                // Updating Button Text and Item Values

                button.setText("<html>" + name + ": " + num + "<html>");
                button.getItem().setNum(num);

                // Swap Back to Main Panel
        
                parentFrame.getContentPane().removeAll();
                parentFrame.add(getMainPane(), BorderLayout.CENTER);
                parentFrame.add(getMenuPanel(), BorderLayout.SOUTH);

                parentFrame.revalidate();
                parentFrame.repaint();

                // Update TreeMap and Update Main Panel

                TreeMap<String, Item> items = parentFrame.getTreeMap();
                
                items.put(name, getItemButton().getItem());

                JPanel panel = parentFrame.getMainPanel();

                panel.removeAll();

                panel.revalidate();
                panel.repaint();

                for (Map.Entry<String, Item> item : items.entrySet()) {
                    
                    panel.add(item.getValue().getButton());

                }

                panel.revalidate();
                panel.repaint();
                
                nameLabel.setText("Name of Item (Max 20 Characters" 
                        + "; Min 1 Character):");
                numLabel.setText("Number of Items:");
    
            });
            
            add(nameLabel);
            add(inventoryName);
            add(numLabel);
            add(inventoryEditSpinner);
            add(inventoryConfirm);
    
        }

        /**
         * This method returns the button associated with the panel
         * 
         * @return The button associated with the customization panel
         */
        public ItemButton getItemButton() {
            
            return button;

        }
    
    }

    /**
     * 
     * This constructor produces the primary viewing frame 
     * for the inventory manager, altering the appearance 
     * 
     * @param items
     */
    public InventoryManagerApplication(TreeMap<String, Item> items) {

        super("Inventory Manager");

        this.items = items;

        // Alter Appearance

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(BACKGROUND_COLOR);
        setForeground(FOREGROUND_COLOR);
        setMinimumSize(MIN_DIMENSIONS);

        // Create and Alter Main Panel & Scroll Pane
        
        final JPanel mainPanel = new JPanel();

        mainPanel.setLayout(new GridLayout(0, 4, 5, 5));
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setForeground(FOREGROUND_COLOR);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        final JScrollPane mainPane = new JScrollPane(mainPanel);

        // Create and Alter Menu Panel

        final JPanel menuPanel = new JPanel();

        menuPanel.setBackground(BACKGROUND_COLOR);
        menuPanel.setForeground(FOREGROUND_COLOR);

        // Create and Alter Add Menu Option

        final JButton addButton = new JButton("Add Item");

        addButton.setBackground(FOREGROUND_COLOR);
        addButton.setForeground(BACKGROUND_COLOR);
        addButton.setFont(FONT);
        addButton.setFocusable(false);
        addButton.addActionListener(e -> {

            itemEdit(this, new ItemButton(this).getPanel());

        });

        menuPanel.add(addButton);

        this.mainPane = mainPane;
        this.menuPanel = menuPanel;
        this.mainPanel = mainPanel;

        add(mainPane, BorderLayout.CENTER);
        add(menuPanel, BorderLayout.SOUTH);

    }

    /**
     * This method swaps the window view
     * from the main panel to an item panel
     * 
     * @param frame The viewing frame of the application
     * @param panel The item panel to swap to
     */
    public static void itemEdit(InventoryManagerApplication frame, ItemPanel panel) {

        //https://stackoverflow.com/questions/9347076/how-to-remove-all-components-from-a-jframe-in-java

        frame.getContentPane().removeAll();
        frame.add(panel);

        frame.revalidate();
        frame.repaint();
        
    }

    /**
     * This method returns the main scroll pane of the inventory manager
     * 
     * @return The main scroll pane of the inventory manager
     */
    public JScrollPane getMainPane() {

        return mainPane;

    }

    /**
     * This method returns the main panel of the inventory manager
     * 
     * @return The main panel of the inventory manager
     */
    public JPanel getMainPanel() {

        return mainPanel;

    }

    /**
     * This method returns the menu panel of the inventory manager
     * 
     * @return The menu panel of the inventory manager
     */
    public JPanel getMenuPanel() {

        return menuPanel;

    }

    /**
     * This method returns the map of items of the inventory manager
     * 
     * @return The map of items of the inventory manager
     */
    public TreeMap<String, Item> getTreeMap() {

        return items;

    }

}