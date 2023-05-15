/**
 * Name: Huy Nguyen
 * Email: hqnguyen.cs@gmail.com
 * 
 * This file starts up the inventory manager
 */

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import java.util.TreeMap;

/**
 * This class starts up the inventory manager
 */
public class InventoryManager {

    /**
     * This method starts up the inventory manager
     * 
     * @param args Standard main method arguments
     * @throws InvocationTargetException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        
        SwingUtilities.invokeAndWait(new Runnable() {

            @Override
            public void run() {

                final InventoryManagerApplication frame = new InventoryManagerApplication(
                        new TreeMap<String, InventoryManagerApplication.Item>());
                        
                frame.setVisible(true);

             }

        });

    }
    
}