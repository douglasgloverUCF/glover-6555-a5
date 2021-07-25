package ucf.assignments;

import javafx.collections.ObservableList;

public class VerifyInput {
    ObservableList<InventoryItem> inventory;
    String verifyInput(String value, String serial, String name, String exception)
    {
        //run through each check function and return appropriate errors
        if (!verifyName(name))
        {
            return "Invalid name";
        }
        if (!verifySerial(serial))
        {
            return "Invalid serial #";
        }
        if (!serialCheck(serial, exception))
        {
            return "Serial # is already taken";
        }
        if (!verifyValue(value))
        {
            return "Value must be numeric";
        }
        //return pass if no fails
        return "Pass";
    }
    Boolean verifyValue(String input)
    {
        //check if value is a double
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
        //check if value is ten characters
        if(input.length() == 10)
        {
            input = input.toUpperCase();
            for(int i = 0; i < 10; i++)
            {
                //check if any character is non-alphabetical and non-numerical
                char c = input.charAt(i);
                if ((c < 'A' || c > 'Z') && (c < '0' || c > '9'))
                    //fail
                    return false;
            }
            //pass
            return true;
        }
        //fail
        return false;
    }
    Boolean serialCheck(String serial, String exception)
    {
        for (InventoryItem item: inventory) {
            //check if serial exists already in inventory
            if(serial.equals(item.getSerial()))
                //check if repeated serial is not the same one being edited
                if(!serial.equals(exception))
                    //fail
                    return false;
        }
        //pass
        return true;
    }
    Boolean verifyName(String input)
    {
        int length = input.length();
        //check if name is between 2 and 256 characters
        return length >= 2 && length <= 256;
    }
}
