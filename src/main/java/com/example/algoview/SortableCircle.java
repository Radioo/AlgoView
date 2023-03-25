package com.example.algoview;

import javafx.animation.FillTransition;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.util.Objects;

import static javafx.scene.shape.StrokeType.INSIDE;

public class SortableCircle extends Region implements Comparable<SortableCircle> {
    private Integer value;
    private final Circle circle;
    private final Text text;

    public SortableCircle(double radius, int value) {
        this.value = value;

        this.circle = new Circle(radius);
        this.circle.setFill(Color.DODGERBLUE);
        this.circle.setStroke(Color.BLACK);
        this.circle.setStrokeType(INSIDE);

        // Position the circle in the center of the region
        this.circle.centerXProperty().bind(this.widthProperty().divide(2));
        this.circle.centerYProperty().bind(this.heightProperty().divide(2));

        this.text = new Text(this.value.toString());

        // Position the text in the center of the circle
        this.centerText();

        this.getChildren().addAll(this.circle, this.text);
    }

    public void highlight(javafx.scene.paint.Color color) {
        FillTransition ft = new FillTransition(javafx.util.Duration.millis(250), this.circle, Color.DODGERBLUE, color);
        ft.setCycleCount(2);
        ft.setAutoReverse(true);
        ft.play();
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

    public void setValue(Integer value) {
        this.value = value;
        this.text.setText(value.toString());
        this.centerText();
    }

    public Integer getValue() {
        return this.value;
    }

    public void swap(SortableCircle other) {
        other.highlight(Color.ORANGERED);
        this.highlight(Color.ORANGERED);

        Integer temp = this.value;
        this.setValue(other.getValue());
        other.setValue(temp);
    }

    private void centerText() {
        this.text.xProperty().bind(this.circle.centerXProperty().subtract(this.text.getLayoutBounds().getWidth() / 2));
        this.text.yProperty().bind(this.circle.centerYProperty().add(this.text.getLayoutBounds().getHeight() / 4));
    }
}
