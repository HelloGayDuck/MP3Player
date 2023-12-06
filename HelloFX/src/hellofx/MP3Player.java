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
	public void play(String fileName) {
		System.out.println("Now playing: " + fileName);
		audioPlayer = minim.loadMP3File(fileName);
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

	public static String findFile(String name, File file){
		File[] list = file.listFiles();
		String pfad = null;

		if(list!=null)
			for (File fil : list){
				if (fil.isDirectory()){
					findFile(name, file);
				}
				else if (name.equalsIgnoreCase(fil.getName())){
					pfad = fil.getParentFile().toString();
					System.out.print(fil.getParentFile());
					return pfad;
				}
			} //Hier entsteht das Problem
		return pfad;
	}
	*/
	
}
