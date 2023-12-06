package hellofx.presentation;

import hellofx.presentation.playerview.PlayerView;
import hellofx.presentation.playerview.PlayerViewController;
import hellofx.presentation.playlistview.PlaylistView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MP3PlayerApp extends Application {
    final String PLAYER = "PLAYER";
    final String PLAYLIST = "PLAYLIST";
    Stage primaryStage;
    PlayerView playerView;
    PlaylistView playlistView;



    @Override
    public void start(Stage primaryStage) throws Exception {

        this.playerView = new PlayerView();
        this.playlistView = new PlaylistView();

        this.primaryStage = primaryStage;

        Scene scene = new Scene();
        primaryStage.setScene(scene);

        switchView(PLAYER);
        primaryStage.setTitle("Player");
        primaryStage.show();

    }

    private void switchView(String name){
        Scene scene = primaryStage.getScene();
        switch(name){
            case PLAYER:
                scene.setRoot(playerView);
                break;
            case PLAYLIST:
                scene.setRoot(playlistView);
                break;
            default:
                System.out.println("Fehler bei View");
        }

    }
}
