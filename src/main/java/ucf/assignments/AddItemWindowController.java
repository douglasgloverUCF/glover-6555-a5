/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Douglas Glover
 */
package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class AddItemWindowController {
    SceneManager scenes;
    @FXML
    TextField nameBox = new TextField();
    @FXML
    TextField serialBox = new TextField();
    @FXML
    TextField valueBox = new TextField();
    @FXML
    Text errorText = new Text();

    @FXML
    public void addButtonClicked(ActionEvent actionEvent) {
        String name = nameBox.getText();
        String serial = serialBox.getText();
        String value = valueBox.getText();
        if (!scenes.model.verify(serial, "Serial"))
        {
            errorText.visibleProperty().setValue(true);
            errorText.setText("Invalid serial #");
            return;
        }
        if (!scenes.model.verify(value, "Value"))
        {
            errorText.visibleProperty().setValue(true);
            errorText.setText("Invalid value");
            return;
        }
        if (!scenes.model.serialCheck(serial, ""))
        {
            errorText.visibleProperty().setValue(true);
            errorText.setText("Serial # is taken");
            return;
        }
        value = String.format("$%.2f", Double.valueOf(value));
        scenes.model.addItem(value, serial, name);
        errorText.visibleProperty().setValue(false);
        scenes.closeScene();
    }
    @FXML
    public void cancelButtonClicked(ActionEvent actionEvent) {
        scenes.closeScene();
    }
}
