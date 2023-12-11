package hellofx;

import hellofx.bisnis.MP3Player;
import hellofx.bisnis.Playlist;
import hellofx.bisnis.PlaylistManager;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.Scanner;

public class KeyBoardController {
    static PlaylistManager manager = new PlaylistManager();
    static MP3Player mp3Player = new MP3Player();
    public static void main(String [] args) throws Exception {
        start();
    }
    public static void start() throws Exception {

        manager.load();

        Scanner scan = new Scanner(System.in);
        String userInput = "";
        Scanner specialScan = new Scanner(System.in);


        while(!Objects.equals(userInput, "stop")){
            userInput = scan.nextLine();
            switch(userInput){
                case "play":
                    mp3Player.play();
                    break;
                case "pause":
                    mp3Player.pause();
                    break;
                case "selectSong":
                    System.out.println("Welche Nummer willst du auswählen? \nDu kannst eine Nummer zwischen 0 und " + manager.getPlaylistMap().size() + " auswählen.");
                    mp3Player.selectTrack(specialScan.nextInt());
                    mp3Player.play();
                    break;
                case "selectPlaylist":
                    System.out.println("Welche Playlist möchtest du auswählen? \n" );
                    for(Playlist playlist : manager.getPlaylistMap()) {
                        System.out.println( playlist.getId() + "  " +  playlist.getTitle() + "\n");
                    }
                    try {
                        mp3Player.setPlaylist(manager.getPlaylistMap().get(specialScan.nextInt()));
                    } catch(Exception exception){ {
                    System.out.println("Diese Playlist wurde nicht gefunden.");}
                    }
                    break;
                case "skip":
                    mp3Player.skip();
                    break;
                case "skipBack":
                    mp3Player.skipBack();
                    break;
                case "rewind":
                    mp3Player.skipTo(scan.nextInt());
                    break;
                case "volume":
                    System.out.println("Auf was willt du die Lautstärke stellen?");
                    mp3Player.adjustVolume(scan.nextFloat());
                    break;
                default:
                    System.out.println("Diese Eingabe wurde nicht erkannt. Mit \"stop\" endet man das Programm");
                    break;
            }

        }
    }
}
