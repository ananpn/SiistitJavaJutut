package ananpn.siistitjavajutut;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

// Ohjelmointi II v6t5
public class HirsipuuPeli extends Application {

    private double offsetX = 80;
    private double offsetY = 120;

    private double paaSade = 35;
    private double koydenPituus = 40;
    private double vartalonPituus = 140;

    public double ylaPuuVasenX = offsetX;
    public double ylapuuVasenY = offsetY;
    public double ylaPuuOikeaX = offsetX + 180;
    public double ylaPuuOikeaY = offsetY;

    public double alapuuAlaX = ylaPuuOikeaX;
    public double alapuuAlaY = ylaPuuOikeaY + 390;

    private double koysiYlaX = (ylaPuuVasenX+ylaPuuOikeaX) / 2;
    private double koysiYlaY = ylapuuVasenY;

    private double koysiAlaX = koysiYlaX;
    private double koysiAlaY = koysiYlaY + koydenPituus;

    private double paaKeskipisteX = koysiAlaX;
    private double paaKeskipisteY = koysiAlaY + paaSade;

    private double vartaloYlaX = paaKeskipisteX;
    private double vartaloYlaY = paaKeskipisteY + paaSade;

    private double vartaloAlaX = vartaloYlaX;
    private double vartaloAlaY = vartaloYlaY + vartalonPituus;

    private double kasiYlaX = vartaloYlaX;
    private double kasiYlaY = ( 5* vartaloYlaY + 2*vartaloAlaY ) / 7;

    private double vasenKasiAlaX = kasiYlaX - 30;
    private double vasenKasiAlaY = kasiYlaY + 90;
    private double oikeaKasiAlaX = kasiYlaX + 30;
    private double oikeaKasiAlaY = kasiYlaY + 90;

    private double vasenJalkaAlaX = vartaloAlaX -40;
    private double vasenJalkaAlaY = vartaloAlaY + 80;
    private double oikeaJalkaAlaX = vartaloAlaX +40;
    private double oikeaJalkaAlaY = vartaloAlaY + 80;

    private double maaRadiusX = 200;
    private double maaRadiusY = 90;



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        // Group
        Group group = new Group();

        Line ylaPuu = viiva(
                Color.SADDLEBROWN, 3, ylaPuuVasenX, ylapuuVasenY, ylaPuuOikeaX, ylaPuuOikeaY
        );
        Line alaPuu = viiva(
                Color.SADDLEBROWN, 3, ylaPuuOikeaX, ylaPuuOikeaY, alapuuAlaX, alapuuAlaY
        );
        Line koysi = viiva(
                Color.RED, 3, koysiYlaX, koysiYlaY, koysiAlaX, koysiAlaY
        );

        Circle paa = ympyra(
                Color.BLUE,
                Color.BLACK,
                2,
                paaKeskipisteX,
                paaKeskipisteY,
                paaSade
        );

        Line vartalo = viiva(
                Color.BROWN,
                3,
                vartaloYlaX,
                vartaloYlaY,
                vartaloAlaX,
                vartaloAlaY
        );

        Line vasenKasi = viiva(
                Color.BROWN,
                3,
                kasiYlaX,
                kasiYlaY,
                vasenKasiAlaX,
                vasenKasiAlaY
        );

        Line oikeaKasi = viiva(
                Color.BROWN,
                3,
                kasiYlaX,
                kasiYlaY,
                oikeaKasiAlaX,
                oikeaKasiAlaY
        );

        Line vasenJalka = viiva(
                Color.BROWN,
                3,
                vartaloAlaX,
                vartaloAlaY,
                vasenJalkaAlaX,
                vasenJalkaAlaY
        );

        Line oikeaJalka = viiva(
                Color.BROWN,
                3,
                vartaloAlaX,
                vartaloAlaY,
                oikeaJalkaAlaX,
                oikeaJalkaAlaY
        );

        Ellipse maa = ellipsi(
                Color.DARKGRAY,
                Color.BLACK,
                3,
                alapuuAlaX,
                alapuuAlaY + maaRadiusY,
                maaRadiusX,
                maaRadiusY
        );

        ArrayList<Shape> hirsipuuList = new ArrayList<>(Arrays.asList(
                maa, alaPuu, ylaPuu, koysi, paa, vartalo, vasenKasi, oikeaKasi, vasenJalka, oikeaJalka
        ));

        List<String> listaSanoista = FinnishWords.getWords();

        Random random = new Random();

        String arvattavaSana = listaSanoista.get(random.nextInt(0,listaSanoista.size())).toUpperCase();
        char[] alaviivat = new char[arvattavaSana.length()];
        Arrays.fill(alaviivat, '_');
        TextFlow alaviivatTextFlow = new TextFlow();
        alaviivatTextFlow.setLayoutX(20);
        alaviivatTextFlow.setLayoutY(30);
        for (char c : alaviivat) {
            Text letter = new Text(c + " ");
            letter.setStyle("-fx-font-family: monospace; -fx-font-size: 20; -fx-font-weight: bold;");
            alaviivatTextFlow.getChildren().add(letter);
        }

        char[] arvattu = new char[arvattavaSana.length()];
        Arrays.fill(arvattu, ' ');
        TextFlow arvattuTextFlow = new TextFlow();
        arvattuTextFlow.setLayoutX(20);
        arvattuTextFlow.setLayoutY(30);
        for (char c : arvattu) {
            Text letter = new Text(c + " ");
            letter.setStyle("-fx-font-family: monospace; -fx-font-size: 20;");
            arvattuTextFlow.getChildren().add(letter);
        }

        List<String> vaaratArvatut = new ArrayList<>();
        Text arvatutMessage = new Text("");
        arvatutMessage.setStyle("-fx-font-family: monospace; -fx-font-size: 10;");
        arvatutMessage.setLayoutX(200);
        arvatutMessage.setLayoutY(30);


        Text resultMessage = new Text("");
        resultMessage.setLayoutX(20);
        resultMessage.setLayoutY(80);
        resultMessage.setStyle("-fx-font-family: monospace; -fx-font-size: 20;");


        group.getChildren().add(alaviivatTextFlow);
        group.getChildren().add(arvattuTextFlow);
        group.getChildren().add(resultMessage);
        group.getChildren().add(arvatutMessage);



        Scene scene = new Scene(group, 500, 800, Color.WHITE);
        final int[] currentHangmanIndex = {0};
        final boolean[] peliLoppu = {false};

        scene.setOnKeyPressed(e -> {
            if ((currentHangmanIndex[0] < hirsipuuList.size()) && !peliLoppu[0]) {
                String currentCharacter = e.getText().toUpperCase();
                System.out.println(currentCharacter);
                if (arvattavaSana.contains(currentCharacter)) {
                    String remains = arvattavaSana;
                    int lastIndex = remains.lastIndexOf(currentCharacter);
                    while (lastIndex != -1 && currentCharacter.length() > 0) {
                        lastIndex = remains.lastIndexOf(currentCharacter);
                        if (lastIndex != -1 ) {
                            remains = remains.substring(0, lastIndex);
                            arvattu[lastIndex] = currentCharacter.toCharArray()[0];
                        }
                    }
                    arvattuTextFlow.getChildren().clear();
                    for (char c : arvattu) {
                        Text letter = new Text(c + " ");
                        letter.setStyle("-fx-font-family: monospace; -fx-font-size: 20;");
                        arvattuTextFlow.getChildren().add(letter);
                    }
                    if (muutaArrayStringksi(arvattu).equals(arvattavaSana)){
                        resultMessage.setText("Arvasit sanan!");
                        peliLoppu[0] = true;
                    }
                }
                else{
                    if (currentHangmanIndex[0] < hirsipuuList.size()) {
                        group.getChildren().add(hirsipuuList.get(currentHangmanIndex[0]));
                        currentHangmanIndex[0]++;
                        if (!vaaratArvatut.contains(currentCharacter)){
                            vaaratArvatut.add(currentCharacter);
                            String arvatutMessageText = "";
                            for (String c : vaaratArvatut) {
                                arvatutMessageText += c + ", ";
                            }
                            arvatutMessage.setText(arvatutMessageText);
                        }

                    }
                }
            }
            else if (!peliLoppu[0]) {
                resultMessage.setText("Hävisit pelin");
            }
        });
        stage.setTitle("Hirsipuussa");
        stage.setScene(scene);
        stage.show();
    }

    public Line viiva(Color fillColor, double lineWidth, double startX, double startY, double endX, double endY) {
        Line line = new Line(startX, startY, endX, endY);
        line.setStrokeWidth(lineWidth);
        line.setFill(fillColor);
        line.setStroke(fillColor);
        return line;
    }

    private Ellipse ellipsi(Color fillColor,
                            Color borderColor,
                            double borderWidth,
                            double centerX,
                            double centerY,
                            double radiusX,
                            double radiusY
    ) {
        Ellipse ellipse = new Ellipse();
        ellipse.setFill(fillColor);
        ellipse.setStroke(borderColor);
        ellipse.setStrokeWidth(borderWidth);
        ellipse.setCenterX(centerX);
        ellipse.setCenterY(centerY);
        ellipse.setRadiusX(radiusX);
        ellipse.setRadiusY(radiusY);
        return ellipse;
    }


    private Circle ympyra(Color fillColor,
                          Color borderColor,
                          double borderWidth,
                          double centerX,
                          double centerY,
                          double radius
    ) {
        Circle circle = new Circle();
        circle.setFill(fillColor);
        circle.setRadius(radius);
        circle.setCenterX(centerX);
        circle.setCenterY(centerY);
        circle.setStroke(borderColor);
        circle.setStrokeWidth(borderWidth);
        return circle;
    }

    private String muutaArrayStringksi(char[] charArray){
        String output = "";
        for (int i = 0; i < charArray.length; i++) {
            output += charArray[i];
        }
        return output;

    }


    class FinnishWords {
        public static List<String> getWords() {
            return Arrays.asList(
                    "kissa", "koira", "puu", "tuoli", "pöytä",
                    "auto", "vene", "järvi", "metsä", "koulu",
                    "kirja", "kynä", "paperi", "ovi", "ikkuna",
                    "avain", "puhelin", "tietokone", "hattu", "kengät",
                    "kello", "valo", "lasi", "kahvi", "maito",
                    "leipä", "voi", "juusto", "omena", "banaani",
                    "makkara", "peruna", "porkkana", "sipuli", "sieni",
                    "aurinko", "tähti", "pilvi", "taivas", "sade",
                    "lumi", "kesä", "talvi", "syksy", "kevät",
                    "lintu", "kala", "karhu", "hirvi", "jänis",
                    "susi", "kettu", "orava", "siili", "lampi"
            );
        }
    }
}
