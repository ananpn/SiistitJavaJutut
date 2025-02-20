package ananpn.siistitjavajutut;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

// Ohjelmointi II v6t6
public class AnimoituMikro extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        HBox root = new HBox();
        root.setSpacing(20);

        Image kuva = new Image(getClass().getResource("/images/ruokalautanen.png").toExternalForm());

        ImageView kuvaIV = new ImageView(kuva);
        StackPane leftPane = new StackPane(kuvaIV);
        kuvaIV.setPreserveRatio(true);
        kuvaIV.setFitHeight(300);
        kuvaIV.setFitWidth(500);
        leftPane.setMinSize(500, 300);

        root.getChildren().add(leftPane);

        BorderPane rightBorderPane = new BorderPane();


        GridPane topRightGrid = new GridPane();
        GridPane bottomRightGrid = new GridPane();
        BorderPane bottomRightBorderPane = new BorderPane();

        TextField aikaLaatikko = new TextField();
        aikaLaatikko.setText("0");
        aikaLaatikko.setMinWidth(70);

        Text kohtaValmis = new Text("Ruoka on kohta valmis");
        Text ruokaValmis = new Text("Ruoka on valmista!");

        Button alotaNappi = new Button("Start");
        Button lopetaNappi = new Button("Stop");
        Button eikuNappi = new Button("Reset");
        Button aakaseNappi = new Button("Aukaise");

        topRightGrid.add(aikaLaatikko, 0, 0, 3,1);
        topRightGrid.add(alotaNappi, 0, 1);
        topRightGrid.add(lopetaNappi, 1, 1);
        topRightGrid.add(eikuNappi, 2, 1);

        bottomRightBorderPane.setRight(aakaseNappi);

        Long[] currentTime = {0L};
        double[] currentScale = {1};
        double[] currentPhase = {0};

        Timeline animaatio = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            currentScale[0] = Math.cos(-currentPhase[0]+(-currentTime[0]+System.currentTimeMillis())/2000.0);
            kuvaIV.setScaleX(currentScale[0]);
        }));

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            if (Integer.parseInt(aikaLaatikko.getText()) <= 0) {
                leftPane.getChildren().clear();
                leftPane.getChildren().add(ruokaValmis);
                //timeline.stop();
            } else {

                aikaLaatikko.setText(String.valueOf(Integer.parseInt(aikaLaatikko.getText()) - 1));
                animaatio.play();

            }
        }));



        alotaNappi.setOnAction(e -> {
                currentTime[0] = System.currentTimeMillis();
                currentPhase[0] = Math.acos(currentScale[0]);
            leftPane.getChildren().clear();
            leftPane.getChildren().add(kuvaIV);
            try {
                timeline.setCycleCount(Integer.parseInt(aikaLaatikko.getText())+1);
                timeline.play();
                animaatio.setCycleCount(10*(Integer.parseInt(aikaLaatikko.getText())));
                animaatio.play();
            } catch (NumberFormatException ignored) {
                System.err.println("Invalid input in aikalaatikko");
            }
        });

        eikuNappi.setOnAction(e -> {
            timeline.stop();
            aikaLaatikko.setText("0");
            animaatio.stop();
            currentScale[0] = 1;
            kuvaIV.setScaleX(currentScale[0]);
            leftPane.getChildren().clear();
            leftPane.getChildren().add(kuvaIV);
        });

        lopetaNappi.setOnAction(e -> {
            timeline.stop();
            animaatio.stop();
        });

        rightBorderPane.setTop(topRightGrid);
        rightBorderPane.setBottom(bottomRightBorderPane);




        leftPane.setOnMouseEntered(e -> {
            if (timeline.getStatus() == Timeline.Status.RUNNING) {
                leftPane.getChildren().clear();
                leftPane.getChildren().add(kohtaValmis);
                leftPane.setAlignment(Pos.CENTER);
            }
        });
        leftPane.setOnMouseExited(e -> {
            if (timeline.getStatus() == Timeline.Status.RUNNING) {
                leftPane.getChildren().clear();
                leftPane.getChildren().add(kuvaIV);
                leftPane.setAlignment(Pos.CENTER);
            }
        });

        aakaseNappi.setOnAction(e -> {
            leftPane.getChildren().clear();
            currentScale[0] = 1;
            kuvaIV.setScaleX(currentScale[0]);
            timeline.stop();
            animaatio.stop();

        });



        root.getChildren().add(rightBorderPane);

        stage.setTitle("Mikroaaltouuni");

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
