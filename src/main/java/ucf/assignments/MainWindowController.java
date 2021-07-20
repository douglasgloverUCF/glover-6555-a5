/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Douglas Glover
 */
package ucf.assignments;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;


public class MainWindowController {
    SceneManager scenes;
    InventoryModel model;
    FileManager files = new FileManager();
    @FXML
    TableView<InventoryItem> table;
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
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        serialColumn.setCellValueFactory(new PropertyValueFactory<>("serial"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        editButton.setDisable(true);
        removeButton.setDisable(true);
        clearSearchButton.setDisable(true);
    }
    @FXML
    public void AddItemButtonClicked(ActionEvent actionEvent) {
        scenes.loadAddWindow();
    }
    @FXML
    public void EditItemButtonClicked(ActionEvent actionEvent) {
        InventoryItem currentItem = table.getSelectionModel().getSelectedItem();

        String value = currentItem.getValue();
        value = value.replace("$", "");
        String serial = currentItem.getSerial();
        String name = currentItem.getName();

        removeButton.setDisable(true);
        editButton.setDisable(true);

        scenes.loadEditWindow(value, serial, name);
    }
    @FXML
    public void RemoveItemButtonClicked(ActionEvent actionEvent) {
        model.removeItem();
        model.selectedIndex = -1;
        removeButton.setDisable(true);
        editButton.setDisable(true);
        refreshTable();
    }
    @FXML
    public void SearchButtonClicked(ActionEvent actionEvent) {
        scenes.loadSearchWindow();
        clearSearchButton.setDisable(false);
    }
    @FXML
    public void SaveAsButtonClicked(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Save file as...");
        FileChooser.ExtensionFilter fileExtension;
        fileExtension = new FileChooser.ExtensionFilter("TSV (.txt)", "*.txt");
        fc.getExtensionFilters().add(fileExtension);
        fileExtension = new FileChooser.ExtensionFilter("HTML (.html)", "*.html");
        fc.getExtensionFilters().add(fileExtension);
        fileExtension = new FileChooser.ExtensionFilter("JSON (.json)", "*.json");
        fc.getExtensionFilters().add(fileExtension);
        File saveFile = fc.showSaveDialog(scenes.main);
        String extension = fc.getSelectedExtensionFilter().getDescription();
        files.saveFile(model.inventory, saveFile, extension);
    }
    @FXML
    public void LoadButtonClicked(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Load file...");
        FileChooser.ExtensionFilter fileExtension;
        fileExtension = new FileChooser.ExtensionFilter("TSV (.txt)", "*.txt");
        fc.getExtensionFilters().add(fileExtension);
        fileExtension = new FileChooser.ExtensionFilter("HTML (.html)", "*.html");
        fc.getExtensionFilters().add(fileExtension);
        fileExtension = new FileChooser.ExtensionFilter("JSON (.json)", "*.json");
        fc.getExtensionFilters().add(fileExtension);
        File loadFile = fc.showOpenDialog(scenes.main);
        String extension = fc.getSelectedExtensionFilter().getDescription();
        model.inventory.clear();
        model.tableInventory.clear();
        model.inventory.setAll(files.loadFile(loadFile, extension));
        model.tableInventory.setAll(files.loadFile(loadFile, extension));
        refreshTable();
    }
    @FXML
    public void CloseButtonClicked(ActionEvent actionEvent) {
        scenes.closeMain();
    }

    public void refreshTable()
    {
        model.searchInventory(0, "");
        ObservableList<InventoryItem> items = model.tableInventory;
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
        model.searchInventory(0,"");
        removeButton.setDisable(true);
        editButton.setDisable(true);
        clearSearchButton.setDisable(true);
    }
}
