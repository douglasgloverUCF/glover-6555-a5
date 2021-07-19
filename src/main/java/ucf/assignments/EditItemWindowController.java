package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class EditItemWindowController extends MainWindowController {
    @FXML
    TextField nameBox = new TextField();
    @FXML
    TextField serialBox = new TextField();
    @FXML
    TextField valueBox = new TextField();
    @FXML
    private void initialize()
    {
        InventoryItem selectedItem = model.getSelectedItem();
        nameBox.setText(model.getSelectedItem().getName());
        serialBox.setText(selectedItem.getSerial());
        String valueString = String.valueOf(selectedItem.getValue());
        valueBox.setText(valueString);
    }
    @FXML
    public void editButtonClicked(ActionEvent actionEvent) {
        String name = nameBox.getText();
        String serial = serialBox.getText();
        String valueString = valueBox.getText();
        double value = Double.parseDouble(valueString);
        model.editItem(value, serial, name);
        scenes.closeScene(popup);
    }
    @FXML
    public void cancelButtonClicked(ActionEvent actionEvent) {
        scenes.closeScene(popup);
    }
}
