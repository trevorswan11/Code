package labcode12;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Painter extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create border pane
        BorderPane pane = new BorderPane();

        // Create a canvas
        Canvas canvas = new Canvas(500, 500);
        pane.setCenter(canvas);

        // Create the color picker
        ColorPicker picker = new ColorPicker(Color.BLACK);
        pane.setTop(picker);

        // Create a slider
        Slider slider = new Slider(1, 101, 10);
        slider.setOrientation(Orientation.VERTICAL);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        slider.setMajorTickUnit(10);
        slider.setBlockIncrement(1);
        pane.setRight(slider);

        // Anonymous class to draw on drag
        canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                canvas.getGraphicsContext2D().setFill(picker.getValue());
                canvas.getGraphicsContext2D().fillOval(e.getX() - slider.getValue() / 2,
                        e.getY() - slider.getValue() / 2,
                        slider.getValue(), slider.getValue());
            }
        });

        // Anonymous class to draw on click as well
        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                canvas.getGraphicsContext2D().setFill(picker.getValue());
                canvas.getGraphicsContext2D().fillOval(e.getY() - slider.getValue() / 2, slider.getValue(),
                        slider.getValue(), slider.getValue());
            }
        });

        // Scene needed
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Canvas");
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
