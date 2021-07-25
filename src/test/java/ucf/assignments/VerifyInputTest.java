package ucf.assignments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VerifyInputTest {
    VerifyInput verify = new VerifyInput();

    @Test
    void verifyInput_sends_error_if_name_is_too_short() {
        //pass inventory data
        InventoryModel model = new InventoryModel();
        verify.inventory = model.inventory;
        //get result from data with invalid name
        String result = verify.verifyInput("1", "1234567890", "a", "");
        //assert that error message is correct
        assertEquals("Invalid name", result);
    }
    @Test
    void verifyInput_sends_error_if_serial_is_not_ten_characters() {
        //pass inventory data
        InventoryModel model = new InventoryModel();
        verify.inventory = model.inventory;
        //get result from data with invalid serial
        String result = verify.verifyInput("1", "123", "Cow", "");
        //assert that error message is correct
        assertEquals("Invalid serial #", result);
    }
    @Test
    void verifyInput_sends_error_if_serial_is_taken() {
        //create inventory with item
        InventoryModel model = new InventoryModel();
        model.addItem("$2.00", "1234567890", "Pig");
        //pass inventory data
        verify.inventory = model.inventory;
        //get result from data with repeated serial
        String result = verify.verifyInput("1", "1234567890", "Cow", "");
        //assert that error message is correct
        assertEquals("Serial # is already taken", result);
    }
    @Test
    void verifyInput_sends_error_if_value_is_non_numeric() {
        //pass inventory data
        InventoryModel model = new InventoryModel();
        verify.inventory = model.inventory;
        //get result from data with non numeric value
        String result = verify.verifyInput("ABC", "1234567890", "Cow", "");
        //assert that error message is correct
        assertEquals("Value must be numeric", result);
    }
    @Test
    void verifyInput_passes_if_data_is_correct() {
        //pass inventory data
        InventoryModel model = new InventoryModel();
        verify.inventory = model.inventory;
        //get result from valid data
        String result = verify.verifyInput("1", "1234567890", "Cow", "");
        //assert that pass message is correct
        assertEquals("Pass", result);
    }
}