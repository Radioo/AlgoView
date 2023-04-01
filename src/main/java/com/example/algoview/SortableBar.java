package com.example.algoview;

import javafx.application.Platform;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static javafx.scene.shape.StrokeType.INSIDE;

public class SortableBar extends Region implements Comparable<SortableBar> {
    private Float value;
    private final Rectangle rectangle;

    private final int MAX_HEIGHT = 500;

    SortableBar(float value) {
        this.value = value;

        this.rectangle = new Rectangle();
        this.rectangle.setFill(Color.DODGERBLUE);
        this.rectangle.setStroke(Color.BLACK);
        this.rectangle.setStrokeType(INSIDE);
        this.rectangle.setWidth(4);
        this.rectangle.setHeight(this.value * this.MAX_HEIGHT);

        // Position rectangle at the bottom of the region
        this.rectangle.yProperty().bind(this.heightProperty().subtract(this.rectangle.heightProperty()));

        // Center rectangle horizontally
        this.rectangle.xProperty().bind(this.widthProperty().subtract(this.rectangle.widthProperty()).divide(2));

        this.getChildren().add(this.rectangle);
    }

    public void swap(SortableBar other, int delay) {
        Platform.runLater(() -> {
            this.rectangle.setFill(Color.RED);
            other.rectangle.setFill(Color.RED);

            float temp = this.value;
            this.setValue(other.value);
            other.setValue(temp);
        });

        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Platform.runLater(() -> {
            this.rectangle.setFill(Color.DODGERBLUE);
            other.rectangle.setFill(Color.DODGERBLUE);
        });
    }

    private void setValue(float value) {
        this.value = value;
        this.rectangle.setHeight(this.value * this.MAX_HEIGHT);
    }

    public float getValue() {
        return this.value;
    }

    @Override
    public int compareTo(SortableBar o) {
        return this.value.compareTo(o.value);
    }
}
