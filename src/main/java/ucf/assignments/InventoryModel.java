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
        //create a new item with given data
        InventoryItem newItem = new InventoryItem(value, serial, name);
        //add item to inventory
        inventory.add(newItem);
    }
    void editItem(String value, String serial, String name)
    {
        //create a new item with given data
        InventoryItem newItem = new InventoryItem(value, serial, name);
        //get index of selected item from user's table
        int index = inventory.indexOf(tableInventory.get(selectedIndex));
        //set new item at index
        inventory.set(index, newItem);
    }
    void removeItem()
    {
        //remove selected item on table from inventory
        inventory.remove(tableInventory.get(selectedIndex));
    }
    void searchInventory(int setting, String term)
    {
        //clear table
        tableInventory.clear();
        //if search is blank display everything
        if(term.isBlank()) {
            tableInventory.addAll(inventory);
            return;
        }
        //if setting is 0 (name)
        if (setting == 0) {
            //find every item from inventory that contains search term in item's name
            for (InventoryItem item : inventory) {
                if (item.getName().contains(term))
                    //add valid items to table
                    tableInventory.add(item);
            }
        }
        //if setting is 1 (serial)
        else if (setting == 1) {
            //find every item from inventory that contains search term in item's serial
            for (InventoryItem item : inventory) {
                if (item.getSerial().contains(term))
                    //add valid items to table
                    tableInventory.add(item);
            }
        }
    }
}
