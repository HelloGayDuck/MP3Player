package hellofx.presentation.playerview;

import javafx.scene.control.Button;
import javafx.scene.layout.*;

public class ButtonView extends AnchorPane {
    Button playButton;
    Button skipButton;
    Button prevButton;
    Button shuffleButton;

    public ButtonView(){
        HBox view = new HBox();
        playButton = new Button("play");
        skipButton = new Button("skip");
        prevButton = new Button("go back");
        shuffleButton = new Button("shuffle");

        view.getChildren().addAll(playButton,skipButton,prevButton,shuffleButton);
        this.getChildren().add(view);
        this.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());

    }
}
