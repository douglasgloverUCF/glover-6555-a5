package ucf.assignments;

import javafx.beans.property.SimpleDoubleProperty;
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

    public void setValue(String givenValue)
    {
        value.setValue(givenValue);
    }
    public void setSerial(String givenSerial)
    {
        serial.set(givenSerial);
    }
    public void setName(String givenName)
    {
        name.set(givenName);
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
