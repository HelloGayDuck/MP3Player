package hellofx;

import java.io.File;
import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;
import java.util.Scanner;

/*
Hier sind alle möglichen Reaktionen auf Inputs
 */


public class MP3Player {
	public static SimpleMinim minim = new SimpleMinim(true);
	public SimpleAudioPlayer audioPlayer;
	Playlist actPlaylist;
	int trackNo;
	boolean isShuffle;

	public MP3Player() {

	}

	public void setPlaylist(Playlist newPlaylist){
		audioPlayer.pause();
		actPlaylist = newPlaylist ;
	}
	void selectTrack(int no){
		audioPlayer = minim.loadMP3File(actPlaylist.getTrack(no));
	}

	public void play(int trackNo) {
		selectTrack(trackNo);
		audioPlayer.play();
		audioPlayer.isPlaying();
	}

	public void play() {
		if(audioPlayer.isPlaying()){
			System.out.println("Der AudioPlayer spielt bereits.");
		}else {
			audioPlayer.play();
		}
	}

	public  void pause() {
		audioPlayer.pause();
	}


	public void skip() {
		System.out.println("skip");
		audioPlayer.pause();
		if(isShuffle){
			

		}else {
			if(trackNo > actPlaylist.numberOfTracks()){
				trackNo ++;
				play(trackNo);

			}else {
				System.out.println("Die Playlist ist vorbei.");
			}
		}
		//in der Aktuellen Playlist muss jetzt das nächste Lied abgespielt werden

	}

	public void skipBack() {
		audioPlayer.pause();
		if(trackNo != 0){
			trackNo --;
			audioPlayer.play(trackNo);
		} else{
			audioPlayer.rewind();
			audioPlayer.play(trackNo);
		}
	}
}
