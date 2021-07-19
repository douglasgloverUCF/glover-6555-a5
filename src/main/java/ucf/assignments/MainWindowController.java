package ucf.assignments;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MainWindowController {
    static Stage popup = new Stage();
    static InventoryModel model = new InventoryModel();
    static SceneManager scenes = new SceneManager();

    @FXML
    private TableView<InventoryItem> table;
    @FXML
    private TableColumn<InventoryItem, Double> valueColumn;
    @FXML
    private TableColumn<InventoryItem, String> serialColumn;
    @FXML
    private TableColumn<InventoryItem, String> nameColumn;
    @FXML
    private Button editButton;
    @FXML
    private Button removeButton;
    @FXML
    private Button clearSearchButton;

    @FXML
    private void initialize()
    {
        //connect table columns to appropriate ListItem variables
        valueColumn.setCellValueFactory(new PropertyValueFactory<InventoryItem, Double>("value"));
        serialColumn.setCellValueFactory(new PropertyValueFactory<InventoryItem, String>("serial"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<InventoryItem, String>("name"));
        scenes.initialize();
        editButton.setDisable(true);
        removeButton.setDisable(true);
        clearSearchButton.setDisable(true);
    }
    @FXML
    public void AddItemButtonClicked(ActionEvent actionEvent) {
        scenes.loadScene(popup,"Add Item");
    }
    @FXML
    public void EditItemButtonClicked(ActionEvent actionEvent) {
        scenes.loadScene(popup,"Edit Item");
    }
    @FXML
    public void RemoveItemButtonClicked(ActionEvent actionEvent) {
        model.removeItem();
        model.selectedIndex = -1;
        removeButton.setDisable(true);
        editButton.setDisable(true);
    }
    @FXML
    public void SearchButtonClicked(ActionEvent actionEvent) {
        scenes.loadScene(popup,"Search Item");
        clearSearchButton.setDisable(false);
    }
    @FXML
    public void InstructionsButtonClicked(ActionEvent actionEvent) {
    }
    @FXML
    public void SaveAsButtonClicked(ActionEvent actionEvent) {
        model.saveTable((Stage) table.getScene().getWindow());
    }
    @FXML
    public void LoadButtonClicked(ActionEvent actionEvent) {
        model.loadTable((Stage) table.getScene().getWindow());
    }
    @FXML
    public void CloseButtonClicked(ActionEvent actionEvent) {
    }
    @FXML
    public void refreshTable()
    {
        ObservableList<InventoryItem> items = model.getInventory();
        table.setItems(items);
    }
    @FXML
    public void tableClicked(MouseEvent mouseEvent) {
        model.selectedIndex = table.getSelectionModel().getSelectedIndex();
        if ( model.selectedIndex == -1) {
            removeButton.setDisable(true);
            editButton.setDisable(true);
        }
        else {
            removeButton.setDisable(false);
            editButton.setDisable(false);
        }
    }
    @FXML
    public void ClearSearchButtonClicked(ActionEvent actionEvent) {
        model.searchInventory(1,"");
        removeButton.setDisable(true);
        editButton.setDisable(true);
        clearSearchButton.setDisable(true);
    }


}
