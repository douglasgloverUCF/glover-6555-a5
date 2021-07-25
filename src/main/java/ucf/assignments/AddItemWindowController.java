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
    VerifyInput verify = new VerifyInput();
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
        verify.inventory = scenes.model.inventory;
        //get data from boxes
        String name = nameBox.getText();
        String serial = serialBox.getText();
        String value = valueBox.getText();
        //check if input data is valid
        String verifyResult = verify.verifyInput(value, serial, name, "");
        //if not valid display error message
        if(!verifyResult.equals("Pass"))
        {
            errorText.visibleProperty().setValue(true);
            errorText.setText(verifyResult);
            return;
        }
        //if valid set value to correct formatting
        value = String.format("$%.2f", Double.valueOf(value));
        //add item to inventory with collected data
        scenes.model.addItem(value, serial, name);
        //clear any errors displayed
        errorText.visibleProperty().setValue(false);
        //refresh the table
        scenes.mainController.refreshTable();
        //close the pop up window
        scenes.closeScene();
    }
    @FXML
    public void cancelButtonClicked(ActionEvent actionEvent) {
        //close the pop up window
        scenes.closeScene();
    }
}
