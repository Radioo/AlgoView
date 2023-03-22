package com.example.algoview;

import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.util.Objects;

import static javafx.scene.shape.StrokeType.INSIDE;

public class SortableCircle extends Region implements Comparable<SortableCircle> {
    private Integer value;

    public SortableCircle(double radius, int value) {
        this.value = value;

        Circle circle = new Circle(radius);
        circle.setFill(Color.DODGERBLUE);
        circle.setStroke(Color.BLACK);
        circle.setStrokeType(INSIDE);

        // Position the circle in the center of the region
        circle.centerXProperty().bind(this.widthProperty().divide(2));
        circle.centerYProperty().bind(this.heightProperty().divide(2));

        Text text1 = new Text(this.value.toString());

        // Position the text in the center of the circle
        text1.xProperty().bind(circle.centerXProperty().subtract(text1.getLayoutBounds().getWidth() / 2));
        text1.yProperty().bind(circle.centerYProperty().add(text1.getLayoutBounds().getHeight() / 4));

        this.getChildren().addAll(circle, text1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SortableCircle that = (SortableCircle) o;

        return Objects.equals(value, that.value);
    }

    @Override
    public int compareTo(SortableCircle o) {
        return this.value.compareTo(o.value);
    }
}
