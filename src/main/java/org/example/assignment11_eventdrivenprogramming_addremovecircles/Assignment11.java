// Student name: Koichi Nakata (ID: knakata595)

package org.example.assignment11_eventdrivenprogramming_addremovecircles;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;

public class Assignment11 extends Application {
    private CirclePane circlePane = new CirclePane();

    @Override
    public void start(Stage stage) throws IOException {

        // This lambda is possible because setOnMouseClicked() method already knows EventHandler<MouseEvent> object
        // will be passed, and it is a functionality interface (only has one method handle(MouseEvent e))
        circlePane.setOnMouseClicked(e -> {
            double centerX = e.getX();
            double centerY = e.getY();

           if (e.getButton() == MouseButton.PRIMARY) {
               circlePane.addCircle(centerX, centerY);
           } else if (e.getButton() == MouseButton.SECONDARY) {
               // getChildren() returns ObservableList<Node>
               // Cannot declare as ObservableList<Circle> because of downcasting
               ObservableList<Node> li = circlePane.getChildren();
               for (int i = 0; i < li.size(); i++) {
                   // Node.contains(x, y) returns true if x and y is within the Node object
                   if (li.get(i).contains(centerX, centerY)) {
                       circlePane.removeCircle(li.get(i)); // Pass Node object reference
                   }
               }
           }
        });
        circlePane.requestFocus();

        Scene scene = new Scene(circlePane, 900, 450);
        stage.setTitle("Assignment 11");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) { launch(); }

    // Caution: Circle object can't be a source object for MouseEvent
    //          because (1) we can't add a circle from nothing
    //          (2) we can't remove a source object.
    //          (2) we can't remove a source object.
    public class CirclePane extends Pane {
        private static final double RADIUS = 10;

        public void addCircle(double centerX, double centerY) {
            Circle circle = new Circle(centerX, centerY, RADIUS);
            Color color = new Color(Math.random(), Math.random(), Math.random(), 1.0);
            circle.setStroke(color);
            circle.setFill(color);

            getChildren().add(circle);
        }

        public void removeCircle(Node circle) { // Upcasting from Circle to Node
            // ObservableList.remove(object reference)
            getChildren().remove(circle);
        }
    }
}