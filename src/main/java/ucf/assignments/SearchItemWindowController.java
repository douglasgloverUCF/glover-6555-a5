/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Douglas Glover
 */
package ucf.assignments;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import static javafx.collections.FXCollections.observableArrayList;

public class SearchItemWindowController {
    SceneManager scenes;
    @FXML
    TextField searchBox = new TextField();
    @FXML
    ChoiceBox<String> dropBox= new ChoiceBox<>();
    ObservableList<String> searchChoiceList = observableArrayList("Search by Name", "Search by Serial #");
    @FXML
    private void initialize() {
        dropBox.setItems(searchChoiceList);
        dropBox.setValue("Search by Name");
    }
    @FXML
    public void searchButtonClicked(ActionEvent actionEvent) {
        int setting = dropBox.getSelectionModel().getSelectedIndex();
        String term = searchBox.getText();
        scenes.model.searchInventory(setting, term);
        scenes.closeScene();
    }
    @FXML
    public void cancelButtonClicked(ActionEvent actionEvent) {
        scenes.closeScene();
    }
}
