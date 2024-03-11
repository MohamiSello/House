package com.example.pic;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {

    private static final String[] IMAGE_PATHS = {
            "pic1.jpg", "pic2.jpg", "pic3.jpg", "pic4.jpg", "pic5.jpg",
            "pic6.jpg", "pic7.jpg", "pic8.jpg", "pic9.jpg", "pic10.jpg"
    };

    private static final double IMAGE_HEIGHT = 140;
    private static final double IMAGE_WIDTH = 130;

    private int currentIndex = 0;

    @Override
    public void start(Stage stage) {
        HBox root = new HBox();
        VBox display1 = new VBox();
        VBox display2 = new VBox();
        Label house = new Label("Luxurious Homes Display");
        house.setId("house");

        Button btnClose = new Button("Close");
        Button btnNext = new Button("Next");
        Button btnPrevious = new Button("Previous");
        btnPrevious.setId("btnPrevious");
        btnNext.setId("btnNext");
        btnClose.setId("btnClose");

        HBox buttonBar = new HBox();
        buttonBar.setId("buttonBar");
        HBox cont1 = new HBox();
        HBox cont2 = new HBox();
        HBox cont3 = new HBox();

        List<ImageView> imageViews1 = createImageViews(3, 0);
        List<ImageView> imageViews2 = createImageViews(3, 4);
        List<ImageView> imageViews3 = createImageViews(3, 7);

        cont1.getChildren().addAll(imageViews1);
        cont2.getChildren().addAll(imageViews2);
        cont3.getChildren().addAll(imageViews3);
        cont1.setId("cont");
        cont2.setId("cont");
        cont3.setId("cont");

        buttonBar.getChildren().addAll(btnNext, btnClose, btnPrevious);
        display1.getChildren().addAll(cont3, cont1);
        display2.getChildren().addAll(cont2);

        ImageView mainDisplay = new ImageView();
        mainDisplay.setFitWidth(600);
        mainDisplay.setPreserveRatio(true);
        mainDisplay.setId("mainDisplay");

        // Click event handlers to update the main display
        for (ImageView imageView : imageViews1) {
            imageView.setOnMouseClicked(event -> updateMainDisplay(mainDisplay, imageView));
        }
        for (ImageView imageView : imageViews2) {
            imageView.setOnMouseClicked(event -> updateMainDisplay(mainDisplay, imageView));
        }
        for (ImageView imageView : imageViews3) {
            imageView.setOnMouseClicked(event -> updateMainDisplay(mainDisplay, imageView));
        }

        btnPrevious.setOnAction(event -> handlePrevious(mainDisplay));
        btnNext.setOnAction(event -> handleNext(mainDisplay));

        VBox left = new VBox();
        VBox right = new VBox();
        right.getChildren().addAll(buttonBar, mainDisplay);
        left.getChildren().addAll(house, display1, display2);
        root.getChildren().addAll( left, right);


        Scene scene = new Scene(root, 1000, 640);
        scene.getStylesheets().add(getClass().getResource("zxc.css").toExternalForm());

        stage.setTitle("Pictures");
        stage.setScene(scene);
        stage.show();
    }

    private List<ImageView> createImageViews(int count, int startIndex) {
        List<ImageView> imageViews = new ArrayList<>();

        int columns = 3;

        for (int i = 0; i < count; i++) {
            int rowIndex = i / columns;
            int colIndex = i % columns;

            Image image = new Image(getClass().getResource(IMAGE_PATHS[startIndex + i]).toExternalForm());
            ImageView imageView = new ImageView(image);

            imageView.setFitHeight(IMAGE_HEIGHT);
            imageView.setFitWidth(IMAGE_WIDTH);
            imageView.setPreserveRatio(true);

            imageViews.add(imageView);
        }

        return imageViews;
    }

    private void updateMainDisplay(ImageView mainDisplay, ImageView clickedImage) {
        mainDisplay.setImage(clickedImage.getImage());
    }

    private void handlePrevious(ImageView mainDisplay) {
        currentIndex = (currentIndex - 1 + IMAGE_PATHS.length) % IMAGE_PATHS.length;
        Image image = new Image(getClass().getResource(IMAGE_PATHS[currentIndex]).toExternalForm());
        mainDisplay.setImage(image);
    }

    private void handleNext(ImageView mainDisplay) {
        currentIndex = (currentIndex + 1) % IMAGE_PATHS.length;
        Image image = new Image(getClass().getResource(IMAGE_PATHS[currentIndex]).toExternalForm());
        mainDisplay.setImage(image);
    }

    public static void main(String[] args) {
        launch();
    }
}
