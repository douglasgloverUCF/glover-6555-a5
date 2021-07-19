package ucf.assignments;

import javafx.collections.ObservableList;
import static javafx.collections.FXCollections.observableArrayList;

public class InventoryModel {
    static ObservableList<InventoryItem> inventory = observableArrayList();
    ObservableList<InventoryItem> tableInventory = observableArrayList();
    int selectedIndex = -1;
    void addItem(String value, String serial, String name)
    {
        InventoryItem newItem = new InventoryItem(value, serial, name);
        inventory.add(newItem);
        searchInventory(0, "");
    }
    void editItem(String value, String serial, String name)
    {
        InventoryItem newItem = new InventoryItem(value, serial, name);
        int index = inventory.indexOf(tableInventory.get(selectedIndex));
        inventory.set(index, newItem);
        searchInventory(0, "");
    }
    void removeItem()
    {
        inventory.remove(tableInventory.get(selectedIndex));
        searchInventory(0, "");
    }
    InventoryItem getSelectedItem()
    {
        if(selectedIndex != -1)
        {
            int index = inventory.indexOf(tableInventory.get(selectedIndex));
            return inventory.get(selectedIndex);
        }
        else
            return new InventoryItem("", "", "");
    }
    ObservableList<InventoryItem> getInventory()
    {
        return tableInventory;
    }
    void searchInventory(int setting, String term)
    {
        tableInventory.clear();
        if(term.isBlank()) {
            tableInventory.addAll(inventory);
            return;
        }
        System.out.print(setting + " " + term);
        switch (setting) {
            case 0: {
                for (InventoryItem item : inventory) {
                    if (item.getName().contains(term))
                        tableInventory.add(item);
                }
            }
            case 1: {
                for (InventoryItem item : inventory) {
                    if (item.getSerial().contains(term))
                        tableInventory.add(item);
                }
            }
            case 2: {
                for (InventoryItem item : inventory) {
                    if (item.getValue().contains(term))
                        tableInventory.add(item);
                }
            }
        }
    }
}
