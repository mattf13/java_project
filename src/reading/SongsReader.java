package reading;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Matteo Fiore (ID 17140) this method reads the songs.csv File
 */

public class SongsReader {

	private String author;

	private String nSong;

	/**
	 * @param author
	 * @param nSong
	 */
	public SongsReader(String author, String nSong) {
		// TODO Auto-generated constructor stub
		this.author = author;
		this.nSong = nSong;
	}

	/**
	 * @return
	 * 
	 */

	public static ArrayList<String> readSongsFromCSV() {
		String csvFile = "songs.csv";
		String s = "";

		ArrayList<String> songs = new ArrayList<String>();
		try {

			BufferedReader br = new BufferedReader(new FileReader(csvFile));

			while ((s = br.readLine()) != null) {
				s.split("_");
				songs.add(s);

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return songs;
	}

}
