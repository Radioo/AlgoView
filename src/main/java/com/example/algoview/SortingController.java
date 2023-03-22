package com.example.algoview;

import javafx.animation.PathTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

import java.util.random.RandomGenerator;

public class SortingController {
    @FXML
    private Pane mainPane;

    @FXML
    private FlowPane fp;

    @FXML
    protected void onHelloButtonClick() {
        fp.getChildren().add(new SortableCircle(15, RandomGenerator.getDefault().nextInt(100)));


    }

    @FXML
    protected void onSwapButtonClick() {
        // Print scene positions of all circles
        for (Node node : fp.getChildren()) {
            System.out.println(node.localToScene(node.getBoundsInLocal()));
        }

    }
}