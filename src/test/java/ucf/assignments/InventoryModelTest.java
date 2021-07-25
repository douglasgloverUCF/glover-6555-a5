package ucf.assignments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryModelTest {

    @Test
    void addItem_adds_item_to_next_index() {
        InventoryModel model = new InventoryModel();
        //add an item to model inventory
        model.addItem("$1.00", "1234567890", "Cow");
        //get item from inventory's first index
        InventoryItem newItem = model.inventory.get(0);
        //assert that item data is equal
        assertEquals(newItem.getName(), "Cow");
    }

    @Test
    void editItem_edits_item_at_selected_index() {
        InventoryModel model = new InventoryModel();
        //add items to model inventory
        model.addItem("$1.00", "1234567890", "Cow");
        model.addItem("$2.00", "1234567ABC", "Pig");
        //select first item in table
        model.tableInventory = model.inventory;
        model.selectedIndex = 0;
        //edit item selected to new data
        model.editItem("$3.00", "DEF4567890", "Chicken");
        //check that new data is present
        InventoryItem newItem = model.inventory.get(0);
        assertEquals(newItem.getName(), "Chicken");
    }

    @Test
    void removeItem_removes_item_at_selected_index() {
        InventoryModel model = new InventoryModel();
        //add item to model inventory
        model.addItem("$1.00", "1234567890", "Cow");
        //select first item in table
        model.tableInventory = model.inventory;
        model.selectedIndex = 0;
        //remove selected item
        model.removeItem();
        //assert that inventory is empty
        assertEquals(model.inventory.size(), 0);
    }

    @Test
    void searchInventory_search_by_name_sets_table_to_results() {
        InventoryModel model = new InventoryModel();
        //add items to model inventory
        model.addItem("$1.00", "1234567890", "Cow");
        model.addItem("$2.00", "1234567ABC", "Pig");
        model.addItem("$3.00", "DEF4567890", "Chicken");
        //search inventory for name
        model.searchInventory(0, "Chicken");
        //get first result
        InventoryItem firstResult = model.tableInventory.get(0);
        //assert that result is valid
        assertEquals(firstResult.getName(), "Chicken");
    }

    @Test
    void searchInventory_search_by_serial_sets_table_to_results() {
        InventoryModel model = new InventoryModel();
        //add items to model inventory
        model.addItem("$1.00", "1234567890", "Cow");
        model.addItem("$2.00", "1234567ABC", "Pig");
        model.addItem("$3.00", "DEF4567890", "Chicken");
        //search inventory for serial
        model.searchInventory(1, "ABC");
        //get first result
        InventoryItem firstResult = model.tableInventory.get(0);
        //assert that result is valid
        assertEquals(firstResult.getSerial(), "1234567ABC");
    }
}