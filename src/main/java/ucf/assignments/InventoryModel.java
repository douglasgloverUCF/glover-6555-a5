package ucf.assignments;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

import static javafx.collections.FXCollections.observableArrayList;

public class InventoryModel {
    ObservableList<InventoryItem> inventory;
    ObservableList<InventoryItem> tableInventory;
    int selectedIndex = -1;
    public InventoryModel()
    {
        this.inventory = observableArrayList();
        this.tableInventory = observableArrayList();
    }

    void addItem(String value, String serial, String name)
    {
        InventoryItem newItem = new InventoryItem(value, serial, name);
        inventory.add(newItem);
    }
    void editItem(String value, String serial, String name)
    {
        InventoryItem newItem = new InventoryItem(value, serial, name);
        int index = inventory.indexOf(tableInventory.get(selectedIndex));
        inventory.set(index, newItem);
    }
    void removeItem()
    {
        inventory.remove(tableInventory.get(selectedIndex));
    }
    void searchInventory(int setting, String term)
    {
        tableInventory.clear();
        if(term.isBlank()) {
            tableInventory.addAll(inventory);
            return;
        }
        if (setting == 0) {
            for (InventoryItem item : inventory) {
                if (item.getName().contains(term))
                    tableInventory.add(item);
            }
        }
        else if (setting == 1) {
            for (InventoryItem item : inventory) {
                if (item.getSerial().contains(term))
                    tableInventory.add(item);
            }
        }
        else {
            for (InventoryItem item : inventory) {
                if (item.getValue().contains(term))
                    tableInventory.add(item);
            }
        }
    }
    Boolean verify(String input, String type)
    {
        if(type.equals("Value"))
        {
            try {
                Double.valueOf(input);
                return true;
            }
            catch(Exception e)
            {
                return false;
            }
        }
        else if(type.equals("Serial"))
        {
            if(input.length() == 10)
            {
                input = input.toUpperCase();
                for(int i = 0; i < 10; i++)
                {
                    char c = input.charAt(i);
                    if ((c < 'A' || c > 'Z') && (c < '0' || c > '9'))
                        return false;
                }
                return true;
            }
        }
        return false;
    }
    Boolean serialCheck(String serial, String exception)
    {
        for (InventoryItem item: inventory) {
            if(serial.equals(item.getSerial()))
                if(!serial.equals(exception))
                    return false;
        }
        return true;
    }
}
