package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddItemWindowController extends MainWindowController {
    @FXML
    TextField nameBox = new TextField();
    @FXML
    TextField serialBox = new TextField();
    @FXML
    TextField valueBox = new TextField();
    @FXML
    private void initialize()
    {
    }
    @FXML
    public void addButtonClicked(ActionEvent actionEvent) {
        String name = nameBox.getText();
        String serial = serialBox.getText();
        String valueString = valueBox.getText();
        double value = Double.parseDouble(valueString);
        model.addItem(value, serial, name);
        scenes.closeScene(popup);
    }
    @FXML
    public void cancelButtonClicked(ActionEvent actionEvent) {
        scenes.closeScene(popup);
    }
}
