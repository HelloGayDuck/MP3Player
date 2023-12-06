package hellofx;

import hellofx.presentation.FirstFrameApplication;
import javafx.application.Application;

import java.util.Scanner;

public class App {
    public static void main(String[] args){
        Application.launch(FirstFrameApplication.class, args);
    }

    public void setUp(){
        PlaylistManager manager = new PlaylistManager();
        //App verbindet Informationen über Tracks und Playlists und der GUI


    }
}
