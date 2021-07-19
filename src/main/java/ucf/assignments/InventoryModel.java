package ucf.assignments;

import javafx.collections.ObservableList;
import static javafx.collections.FXCollections.observableArrayList;

public class InventoryModel {
    static ObservableList<InventoryItem> inventory = observableArrayList();
    int selectedIndex = -1;
    void addItem(double value, String serial, String name)
    {
        InventoryItem newItem = new InventoryItem(value, serial, name);
        inventory.add(newItem);
    }
    void editItem(double value, String serial, String name)
    {
        InventoryItem newItem = new InventoryItem(value, serial, name);
        inventory.set(selectedIndex, newItem);
    }
    void removeItem()
    {
        inventory.remove(selectedIndex);
    }
    InventoryItem getSelectedItem()
    {
        if(selectedIndex != -1)
            return inventory.get(selectedIndex);
        else
            return new InventoryItem(0, "", "");
    }
    ObservableList<InventoryItem> getInventory()
    {
        return inventory;
    }
}
