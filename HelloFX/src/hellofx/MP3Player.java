package hellofx;

import java.io.File;
import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;
import java.util.Scanner;


public class MP3Player {
	public static SimpleMinim minim = new SimpleMinim(true);
	public SimpleAudioPlayer audioPlayer;
	Playlist actPlaylist;
	int trackNo;

	public MP3Player() {

	}

	public void setPlaylist(Playlist list){


	}
	void selectTrack(long no){

	}
	public void play(int trackNo) {
		System.out.println("Now playing: " + trackNo);
		//audioPlayer = minim.loadMP3File(fileName);
		audioPlayer.play();
	}

	public void play() {
		System.out.println("Continuing to play");
		audioPlayer.play();

	}

	public  void pause() {
		System.out.println("Pause");
		audioPlayer.pause();

	}


	public void skip() {
		System.out.println("skip");
		audioPlayer.pause();
		//in der Aktuellen Playlist muss jetzt das nächste Lied abgespielt werden
		if(trackNo > actPlaylist.numberOfTracks()){
			trackNo ++;
			play(trackNo);

		}else {
			System.out.println("Die Playlist ist vorbei.");
		}
	}

	public void skipBack() {
		System.out.println("skip back");
	}

	/*
	public static String pathInput(String eingabe) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Gebe den Pfad ein, in dem das Lied zu finden ist. Wenn es Lokal liegt, lasse es leer.");
		File file = new File(scan.next());

		return findFile(eingabe, file)+ "\\" + eingabe;
	}
	*/
	
}
