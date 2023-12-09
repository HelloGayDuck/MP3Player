package hellofx.presentation;

import hellofx.MP3Player;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

public class FirstFrameApplication extends Application {

    public void init() {

    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        MP3Player mp3Player = new MP3Player();

        HBox root = new HBox();
        Scene scene = new Scene(new StackPane(root), 640, 480);

        scene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());


        BorderPane left = new BorderPane();
        BorderPane right = new BorderPane();

        HBox buttons = new HBox();
        Text text = new Text();

        Button a = new Button("a");
        EventHandler<MouseEvent> play = new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent e){
                mp3Player.play();
            }
        };

        a.setOnMouseClicked(play);

        left.setMinWidth(150);
        right.setMinWidth(500);

        left.setStyle("-fx-background-color: #ff6500");
        right.setStyle("-fx-background-color: #002f64");

        text.setText("Name des Lieds");
        buttons.setSpacing(10);
        buttons.setPadding(new Insets(15));
        buttons.setStyle("-fx-background-color: green");

        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hello World");
        primaryStage.show();


    }
    public void stop(){

    }
}
