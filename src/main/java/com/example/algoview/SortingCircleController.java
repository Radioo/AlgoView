package com.example.algoview;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.random.RandomGenerator;

public class SortingCircleController {
    @FXML
    private FlowPane fp;
    @FXML
    private ComboBox<String> sortAlgoList;
    @FXML
    private Label infoLabel;
    @FXML
    private VBox sortingVBox;

    private ObservableList<Node> sortingLayout;

    @FXML
    private void initialize() {
        sortAlgoList.getItems().addAll("Bubble Sort", "Selection Sort", "Insertion Sort", "Merge Sort", "Quick Sort");
    }

    @FXML
    protected void onHelloButtonClick() {
        fp.getChildren().add(new SortableCircle(15, RandomGenerator.getDefault().nextInt(100)));
    }

    @FXML
    protected void switchToTreesScene() throws IOException {


    }

    @FXML
    protected void switchToSortingBarsScene() {

        this.sortingLayout = FXCollections.observableArrayList(sortingVBox.getChildren());

        sortingVBox.getChildren().clear();

    }

    @FXML
    protected void switchToSortingScene() {
        sortingVBox.getChildren().setAll(sortingLayout);
    }

    @FXML
    protected void onSwapButtonClick() {
        // Check if a sorting algorithm is selected
        if (sortAlgoList.getValue() == null) {
            infoLabel.setText("Please select a sorting algorithm");
            return;
        }

        infoLabel.setText("");

        switch (sortAlgoList.getValue()) {
            case "Bubble Sort":
                bubbleSort();
                break;
            case "Selection Sort":
                //selectionSort();
                break;
            case "Insertion Sort":
                //insertionSort();
                break;
            case "Merge Sort":
                //mergeSort();
                break;
            case "Quick Sort":
                //quickSort();
                break;
        }

        sortAlgoList.setDisable(true);
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void setInfoText(String text) {
        Platform.runLater(() -> infoLabel.setText(text));
    }

    private void bubbleSort() {
        Thread sortThread = new Thread(this::bubbleSortTask);

        sortThread.start();
    }

    private void bubbleSortTask() {
        ObservableList<Node> children = fp.getChildren();
        int n = children.size();

        for(int i = 0; i < n; i++) {
            setInfoText("Iteration " + (i + 1));

            if(i % 2 == 0) {
                for(int j = 0; j < n - 1; j++) {
                    SortableCircle c1 = (SortableCircle) children.get(j);
                    SortableCircle c2 = (SortableCircle) children.get(j + 1);

                    if(c1.compareTo(c2) > 0) {
                        c1.swap(c2);
                    }
                    else {
                        c1.highlight(Color.LIGHTGREEN);
                        c2.highlight(Color.LIGHTGREEN);
                    }

                    this.sleep(1000);
                }
            }
            else {
                for(int j = n - 1; j > 0; j--) {
                    SortableCircle c1 = (SortableCircle) children.get(j);
                    SortableCircle c2 = (SortableCircle) children.get(j - 1);

                    if(c1.compareTo(c2) < 0) {
                        c1.swap(c2);
                    }
                    else {
                        c1.highlight(Color.LIGHTGREEN);
                        c2.highlight(Color.LIGHTGREEN);
                    }

                    this.sleep(1000);
                }
            }
        }

        setInfoText("Sorting complete");
    }
}