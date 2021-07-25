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
    private TableColumn<InventoryItem, String> valueColumn;
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
        //set up table
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        serialColumn.setCellValueFactory(new PropertyValueFactory<>("serial"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        //disable buttons
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
        //get currently selected item on table
        InventoryItem currentItem = table.getSelectionModel().getSelectedItem();
        //get data from selected item
        String value = currentItem.getValue();
        value = value.replace("$", "");
        String serial = currentItem.getSerial();
        String name = currentItem.getName();
        //clear selection
        model.selectedIndex = -1;
        //disable buttons
        removeButton.setDisable(true);
        editButton.setDisable(true);
        clearSearchButton.setDisable(true);
        //load pop up with selected item's data
        scenes.loadEditWindow(value, serial, name);
    }
    @FXML
    public void RemoveItemButtonClicked(ActionEvent actionEvent) {
        //remove selected item
        model.removeItem();
        //clear selection
        model.selectedIndex = -1;
        //disable buttons
        removeButton.setDisable(true);
        editButton.setDisable(true);
        clearSearchButton.setDisable(true);
        //refresh table
        refreshTable();
    }
    @FXML
    public void SearchButtonClicked(ActionEvent actionEvent) {
        //load pop up
        scenes.loadSearchWindow();
        //enable clear search button
        clearSearchButton.setDisable(false);
    }
    @FXML
    public void SaveAsButtonClicked(ActionEvent actionEvent) {
        //create file chooser
        FileChooser fc = new FileChooser();
        fc.setTitle("Save file as...");
        //create extension filter choices
        FileChooser.ExtensionFilter fileExtension;
        fileExtension = new FileChooser.ExtensionFilter("TSV (.txt)", "*.txt");
        fc.getExtensionFilters().add(fileExtension);
        fc.setSelectedExtensionFilter(fileExtension);
        fileExtension = new FileChooser.ExtensionFilter("HTML (.html)", "*.html");
        fc.getExtensionFilters().add(fileExtension);
        fileExtension = new FileChooser.ExtensionFilter("JSON (.json)", "*.json");
        fc.getExtensionFilters().add(fileExtension);
        //get file to save to
        File saveFile = fc.showSaveDialog(scenes.main);
        //get extension chosen
        String extension = fc.getSelectedExtensionFilter().getDescription();
        //save file with inventory data
        files.saveFile(model.inventory, saveFile, extension);
    }
    @FXML
    public void LoadButtonClicked(ActionEvent actionEvent) {
        //create file chooser
        FileChooser fc = new FileChooser();
        fc.setTitle("Load file...");
        //create extension filter choices
        FileChooser.ExtensionFilter fileExtension;
        fileExtension = new FileChooser.ExtensionFilter("TSV (.txt)", "*.txt");
        fc.getExtensionFilters().add(fileExtension);
        fc.setSelectedExtensionFilter(fileExtension);
        fileExtension = new FileChooser.ExtensionFilter("HTML (.html)", "*.html");
        fc.getExtensionFilters().add(fileExtension);
        fileExtension = new FileChooser.ExtensionFilter("JSON (.json)", "*.json");
        fc.getExtensionFilters().add(fileExtension);
        //get file to load from
        File loadFile = fc.showOpenDialog(scenes.main);
        //get extension chosen
        String extension = fc.getSelectedExtensionFilter().getDescription();
        //clear all data
        model.inventory.clear();
        model.tableInventory.clear();
        //get new data from file
        model.inventory.setAll(files.loadFile(loadFile, extension));
        model.tableInventory.setAll(files.loadFile(loadFile, extension));
        //refresh table
        refreshTable();
    }
    @FXML
    public void CloseButtonClicked(ActionEvent actionEvent) {
        //close application
        scenes.closeMain();
    }

    public void refreshTable()
    {
        //clear search
        model.searchInventory(0, "");
        //get items from model
        ObservableList<InventoryItem> items = model.tableInventory;
        //set items to table
        table.setItems(items);
    }
    @FXML
    public void tableClicked(MouseEvent mouseEvent) {
        model.selectedIndex = table.getSelectionModel().getSelectedIndex();
        //if an index is not selected, disable buttons
        if ( model.selectedIndex == -1) {
            removeButton.setDisable(true);
            editButton.setDisable(true);
        }
        //if an index is selected, enable buttons
        else {
            removeButton.setDisable(false);
            editButton.setDisable(false);
        }
    }
    @FXML
    public void ClearSearchButtonClicked(ActionEvent actionEvent) {
        //clear search
        model.searchInventory(0,"");
        //disable buttons
        removeButton.setDisable(true);
        editButton.setDisable(true);
        clearSearchButton.setDisable(true);
    }
}
