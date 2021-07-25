package ucf.assignments;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.io.File;

import static javafx.collections.FXCollections.observableArrayList;
import static org.junit.jupiter.api.Assertions.*;

class FileManagerTest {
    FileManager files = new FileManager();

    ObservableList<InventoryItem> makeTable()
    {
        //create new table
        ObservableList<InventoryItem> table = observableArrayList();
        //give table data
        InventoryItem item1 = new InventoryItem("$1.00", "1234567890", "Cow");
        InventoryItem item2 = new InventoryItem("$2.00", "ABC4567890", "Pig");
        InventoryItem item3 = new InventoryItem("$3.00", "1234567ABC", "Chicken");
        table.add(item1);
        table.add(item2);
        table.add(item3);
        //return table
        return table;
    }
    @Test
    void saveTSV_and_loadTSV_return_correct_data() {
        //make a new table with data
        ObservableList<InventoryItem> testTable = makeTable();
        //create save file
        File saveFile = new File("tests/TestTSV");
        //save data to file
        files.saveTSV(testTable, saveFile);
        //load data from file into another table
        ObservableList<InventoryItem> loadedTable = files.loadTSV(saveFile);
        //compare data
        assertEquals(testTable.get(0).getName(), loadedTable.get(0).getName());
    }

    @Test
    void saveHTML_and_loadHTML_return_correct_data() {
        //make a new table with data
        ObservableList<InventoryItem> testTable = makeTable();
        //create save file
        File saveFile = new File("tests/TestHTML");
        //save data to file
        files.saveHTML(testTable, saveFile);
        //load data from file into another table
        ObservableList<InventoryItem> loadedTable = files.loadHTML(saveFile);
        //compare data
        assertEquals(testTable.get(0).getName(), loadedTable.get(0).getName());
    }

    @Test
    void saveJSON_and_loadJSON_return_correct_data() {
        //make a new table with data
        ObservableList<InventoryItem> testTable = makeTable();
        //create save file
        File saveFile = new File("tests/TestJSON");
        //save data to file
        files.saveJSON(testTable, saveFile);
        //load data from file into another table
        ObservableList<InventoryItem> loadedTable = files.loadJSON(saveFile);
        //compare data
        assertEquals(testTable.get(0).getName(), loadedTable.get(0).getName());
    }
}