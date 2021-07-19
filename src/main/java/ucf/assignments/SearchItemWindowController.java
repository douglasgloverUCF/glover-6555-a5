package ucf.assignments;

import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import static javafx.collections.FXCollections.observableArrayList;

public class SearchItemWindowController extends MainWindowController {
    @FXML
    TextField searchBox = new TextField();
    @FXML
    ChoiceBox<String> dropBox= new ChoiceBox<>();
    ObservableList<String> searchChoiceList = observableArrayList("Search by Name", "Search by Serial #", "Search by Value");
    @FXML
    private void initialize() {
        dropBox.setItems(searchChoiceList);
        dropBox.setValue("Search by Name");
    }
    @FXML
    public void searchButtonClicked(ActionEvent actionEvent) {
        int setting = dropBox.getSelectionModel().getSelectedIndex();
        String term = searchBox.getText();
        model.searchInventory(setting, term);
        scenes.closeScene(popup);
    }
    @FXML
    public void cancelButtonClicked(ActionEvent actionEvent) {
        scenes.closeScene(popup);
    }
}
