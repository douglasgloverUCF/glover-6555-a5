package ucf.assignments;

import javafx.beans.property.SimpleStringProperty;

public class InventoryItem {
    private final SimpleStringProperty value;
    private final SimpleStringProperty serial;
    private final SimpleStringProperty name;

    public InventoryItem(String givenValue, String givenSerial, String givenName)
    {
        value = new SimpleStringProperty(givenValue);
        serial = new SimpleStringProperty(givenSerial);
        name = new SimpleStringProperty(givenName);
    }

    public String getValue()
    {
        return value.getValue();
    }
    public String getSerial()
    {
        return serial.get();
    }
    public String getName()
    {
        return name.get();
    }
}
