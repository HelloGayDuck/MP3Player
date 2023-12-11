package hellofx.bisnis;

import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
Business
Integrated
System
Network
Interface
Solution
 */


public class MP3Player {
	public static SimpleMinim minim = new SimpleMinim(true);
	public SimpleAudioPlayer audioPlayer; //Muss ich nicht hier einen erstellen??? Ich bin ein bisschen Verwirrt.
	Playlist actPlaylist;
	ArrayList<Integer> shuffleList;
	int trackNo;
	public boolean isShuffle;

	public MP3Player() {
	}

	public void setPlaylist(Playlist newPlaylist){
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

	public void selectTrack(int no){
		trackNo = no;
		audioPlayer = minim.loadMP3File(actPlaylist.getTrack(trackNo));
		audioPlayer.rewind();
	}

	public void skipTo(int milli){
		audioPlayer.skip(milli);
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

	/*
	Die Lautstärke wird von 0.0 bis 1.0 eingesetzt, vielleicht kann man 10 Stufen der Lautstärke bauen?,
	sodass wenn man am Reger schiebt, ein Punkt anvisiert wird und einrastet?,
	oder man kann den Abstand, den der Punkt(Regler) hat in Prozent oder so herausfinden und dies Umrechnen.
	 */
	public void adjustVolume(float volume){
		audioPlayer.setVolume(volume);
	}

	/*
	Wir wollen die Playlists nach dem Erstellen nicht noch bearbeiten können.
	 */
	public void addPlaylist(int id, String title, String creationDate, List<Track> tracks){
		//Id braucht man eigentlich nicht. Ich habe es hier nur drin, wegen dem Auswählen über Keyboard
		Playlist playlist = new Playlist(id, title,creationDate, tracks);

	}
}
