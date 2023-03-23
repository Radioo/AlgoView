package com.example.algoview;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

import java.util.random.RandomGenerator;

public class SortingController {
    @FXML
    private FlowPane fp;
    @FXML
    private ComboBox<String> sortAlgoList;
    @FXML
    private Label infoLabel;

    @FXML
    private void initialize() {
        sortAlgoList.getItems().addAll("Bubble Sort", "Selection Sort", "Insertion Sort", "Merge Sort", "Quick Sort");
    }

    @FXML
    protected void onHelloButtonClick() {
        fp.getChildren().add(new SortableCircle(15, RandomGenerator.getDefault().nextInt(100)));


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
    }

    private void bubbleSort() {
        ObservableList<Node> children = fp.getChildren();
        int n = children.size();

        for(int i = 0; i < n; i++) {
            if(i % 2 == 0) {
                for(int j = 0; j < n - 1; j++) {
                    SortableCircle c1 = (SortableCircle) children.get(j);
                    SortableCircle c2 = (SortableCircle) children.get(j + 1);

                    if(c1.compareTo(c2) > 0) {
                        c1.swap(c2);
                        return;
                    }
                }
            }
            else {
                for(int j = n - 1; j > 0; j--) {
                    SortableCircle c1 = (SortableCircle) children.get(j);
                    SortableCircle c2 = (SortableCircle) children.get(j - 1);

                    if(c1.compareTo(c2) < 0) {
                        c1.swap(c2);
                        return;
                    }
                }
            }
        }
    }
}