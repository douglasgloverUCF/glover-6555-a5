package ucf.assignments;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SceneManager {
    Stage main;
    Stage popup = new Stage();
    MainWindowController mainController;
    InventoryModel model = new InventoryModel();

    public SceneManager(Stage stage)
    {
        this.main = stage;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
            Scene scene = new Scene(loader.load());
            mainController = loader.getController();
            mainController.scenes = this;
            mainController.model = model;
            main.setScene(scene);
            main.setTitle("Inventory Manager");
            main.setResizable(false);
            main.show();
        }
        catch(Exception e) {
            //fxml failure
        }
    }
    void loadAddWindow()
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddWindow.fxml"));
            Scene scene = new Scene(loader.load());
            AddItemWindowController addController = loader.getController();
            addController.scenes = this;
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditWindow.fxml"));
            Scene scene = new Scene(loader.load());
            EditItemWindowController editController = loader.getController();
            editController.scenes = this;
            editController.valueBox.setText(value);
            editController.serialBox.setText(serial);
            editController.nameBox.setText(name);
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchWindow.fxml"));
            Scene scene = new Scene(loader.load());
            SearchItemWindowController searchController = loader.getController();
            searchController.scenes = this;
            loadScene("Search Item", scene);
        }
        catch (Exception e)
        {
            //fxml error
        }
    }
    void loadScene(String sceneName, Scene scene)
    {
        popup.setScene(scene);
        popup.setTitle(sceneName);
        popup.setResizable(false);
        popup.setAlwaysOnTop(true);
        popup.show();
    }
    void closeScene()
    {
        mainController.refreshTable();
        popup.close();
    }
    void closeMain()
    {
        main.close();
    }
}
