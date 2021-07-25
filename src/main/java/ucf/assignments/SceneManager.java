/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Douglas Glover
 */
package ucf.assignments;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {
    Stage main;
    Stage popup = new Stage();
    MainWindowController mainController;
    InventoryModel model = new InventoryModel();

    public SceneManager(Stage stage)
    {
        this.main = stage;
        try {
            //load main window
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
            Scene scene = new Scene(loader.load());
            mainController = loader.getController();
            //give main controller access to scene manager and inventory
            mainController.scenes = this;
            mainController.model = model;
            //show main window
            main.setScene(scene);
            main.setTitle("Inventory Manager");
            main.setResizable(false);
            main.show();
        }
        catch(Exception e) {
            //fxml error
        }
    }
    void loadAddWindow()
    {
        try {
            //load add item pop up window
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddWindow.fxml"));
            Scene scene = new Scene(loader.load());
            AddItemWindowController addController = loader.getController();
            //give add item controller access to scene manager
            addController.scenes = this;
            //create popup
            loadScene("Add Item", scene);
        }
        catch (Exception e)
        {
            //fxml error
        }
    }
    void loadEditWindow(String value, String serial, String name)
    {
        try {
            //load edit item pop up window
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditWindow.fxml"));
            Scene scene = new Scene(loader.load());
            EditItemWindowController editController = loader.getController();
            //give edit item controller access to scene manager
            editController.scenes = this;
            //give edit item controller given data
            editController.valueBox.setText(value);
            editController.serialBox.setText(serial);
            editController.nameBox.setText(name);
            //create popup
            loadScene("Edit Item", scene);
        }
        catch (Exception e)
        {
            //fxml error
        }
    }
    void loadSearchWindow()
    {
        try {
            //load search pop up window
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchWindow.fxml"));
            Scene scene = new Scene(loader.load());
            SearchItemWindowController searchController = loader.getController();
            //give search item controller access to scene manager
            searchController.scenes = this;
            //create popup
            loadScene("Search Item", scene);
        }
        catch (Exception e)
        {
            //fxml error
        }
    }
    void loadScene(String sceneName, Scene scene)
    {
        //show given scene with given title in pop up
        popup.setScene(scene);
        popup.setTitle(sceneName);
        popup.setResizable(false);
        popup.setAlwaysOnTop(true);
        popup.show();
    }
    void closeScene()
    {
        //close scene in popup window
        popup.close();
    }
    void closeMain()
    {
        //close application
        main.close();
    }
}
