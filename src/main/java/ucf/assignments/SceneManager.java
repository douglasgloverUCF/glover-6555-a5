package ucf.assignments;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SceneManager {
    Map<String, Scene> sceneCollection = new HashMap<>();

    void initialize()
    {
        try {
            Scene scene;
            scene = new Scene(FXMLLoader.load(getClass().getResource("AddWindow.fxml")));
            sceneCollection.put("Add Item", scene);
            scene = new Scene(FXMLLoader.load(getClass().getResource("EditWindow.fxml")));
            sceneCollection.put("Edit Item", scene);
            scene = new Scene(FXMLLoader.load(getClass().getResource("SearchWindow.fxml")));
            sceneCollection.put("Search Item", scene);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    void loadScene(Stage stage, String scene)
    {
        stage.setScene(sceneCollection.get(scene));
        stage.setTitle(scene);
        stage.show();
    }
    void closeScene(Stage stage)
    {
        stage.close();
    }
}
