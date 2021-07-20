package ucf.assignments;

import javafx.collections.ObservableList;

public class VerifyInput {
    ObservableList<InventoryItem> inventory;
    String verifyInput(String serial, String value, String exception)
    {
        if (!verifySerial(serial))
        {
            return "Invalid serial #";
        }
        if (!serialCheck(serial, exception))
        {
            return "Serial # is taken";
        }
        if (!verifyValue(value))
        {
            return "Value must be numeric";
        }
        return "Pass";
    }
    Boolean verifyValue(String input)
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
    Boolean verifySerial(String input)
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
