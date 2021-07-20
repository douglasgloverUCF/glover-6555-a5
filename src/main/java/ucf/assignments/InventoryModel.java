/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Douglas Glover
 */
package ucf.assignments;

import javafx.collections.ObservableList;

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
    }
}
