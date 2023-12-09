package hellofx;

import java.io.File;
import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/*
Hier sind alle möglichen Reaktionen auf Inputs
 */


public class MP3Player {
	public static SimpleMinim minim = new SimpleMinim(true);
	public SimpleAudioPlayer audioPlayer;
	Playlist actPlaylist;
	ArrayList<Integer> shuffleList;
	int trackNo;
	public boolean isShuffle;

	public MP3Player() {

	}

	public void setPlaylist(Playlist newPlaylist){
		audioPlayer.pause();
		actPlaylist = newPlaylist ;
		shuffleList = createShuffledNumbers(actPlaylist.numberOfTracks());
	}


	private static ArrayList<Integer> createShuffledNumbers(int n){
		ArrayList<Integer> list = new ArrayList<Integer>();

		for (int i = 0; i<n; i++){
			list.add(i);
		}
		for (int j = 0; j<n; j++){
			int o = (int) (Math.random()*n);
			Collections.swap(list, j, o);
		}
		return list;
	}

	void selectTrack(int no){
		trackNo = no;
		audioPlayer = minim.loadMP3File(actPlaylist.getTrack(trackNo));
		audioPlayer.rewind();
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
		audioPlayer.pause();
		if(isShuffle){
			int number = shuffleList.get((shuffleList.indexOf(trackNo)+1)%shuffleList.size());
			selectTrack(number);
			play();
		}else {
			if(trackNo < actPlaylist.numberOfTracks()){
				selectTrack(trackNo+1);
				play();
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
