package com.example.algoview;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.util.Random;

public class SortingBarsController {
    @FXML
    private HBox sortingHBox;
    @FXML
    private TextField delayInput;
    private Thread sortingThread;
    @FXML
    private void initialize() {

    }

    @FXML
    private void addItems() {
        Random rand = new Random();
        for(int i = 0; i < 10; i++) {
            sortingHBox.getChildren().add(new SortableBar(rand.nextFloat()));
        }
    }

    @FXML
    private void sortButtonClick() {
        this.sortingThread = new Thread(this::bubbleSort);
        this.sortingThread.start();
    }

    @FXML
    private void stopButtonClick() {
        this.sortingThread.interrupt();
    }

    @FXML
    private void resetButtonClick() {
        sortingHBox.getChildren().clear();
    }

    @FXML
    private void bubbleSort() {
        ObservableList<Node> children = sortingHBox.getChildren();
        int n = children.size();
        int delay = Integer.parseInt(delayInput.getText());

        for(int i = 0; i < n; i++) {
            if(i % 2 == 0) {
                for(int j = 0; j < n - 1; j++) {
                    SortableBar bar1 = (SortableBar) children.get(j);
                    SortableBar bar2 = (SortableBar) children.get(j + 1);

                    if(bar1.getValue() > bar2.getValue()) {
                        bar1.swap(bar2, delay);
                    }
                }
            }
            else {
                for(int j = n - 1; j > 0; j--) {
                    SortableBar bar1 = (SortableBar) children.get(j);
                    SortableBar bar2 = (SortableBar) children.get(j - 1);

                    if(bar1.getValue() < bar2.getValue()) {
                        bar1.swap(bar2, delay);
                    }
                }
            }
        }
    }
}
