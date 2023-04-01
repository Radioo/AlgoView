package com.example.algoview;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.util.Map;
import java.util.Objects;

public class MainController {
    enum Scene {
        SortingBalls, SortingBars
    }

    private Scene currentScene;
    private final Map<Scene, String> sceneFXMLMap = Map.of(
            Scene.SortingBalls, "sorting-circles.fxml",
            Scene.SortingBars, "sorting-bars.fxml"
    );
    private final ObservableMap<Scene, Node> sceneLayoutMap = FXCollections.observableHashMap();

    @FXML
    private VBox mainVBox;

    @FXML
    private void initialize() {

    }

    @FXML
    protected void setSortingBallsScene() {
        setScene(Scene.SortingBalls);
    }

    @FXML
    protected void setSortingBarsScene() {
        setScene(Scene.SortingBars);
    }

    private void setScene(Scene scene) {
        this.currentScene = scene;
        loadScene();
    }

    private void loadScene() {
        if(currentScene == null) {
            throw new RuntimeException("Current scene is null");
        }

        if(!sceneLayoutMap.containsKey(this.currentScene)) {
            loadSceneFromFXML(this.currentScene);
        }

        mainVBox.getChildren().set(1, sceneLayoutMap.get(this.currentScene));
    }

    @FXML
    private void loadSceneFromFXML(Scene scene) {
        Node loadedNode;
        try {
            loadedNode = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(sceneFXMLMap.get(scene))));
        }
        catch (Exception e) {
            throw new RuntimeException("Could not load scene from FXML", e);
        }

        sceneLayoutMap.put(scene, loadedNode);
    }

    @FXML
    protected void testFunc() {
        if(mainVBox.getChildren().size() == 2) {
            mainVBox.getChildren().remove(1);
        }
        else if(mainVBox.getChildren().size() > 2) {
            throw new RuntimeException("Main VBox has more than 2 children");
        }
    }
}
