package counter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Matteo Fiore (ID 17140) This class has the purpose to read the
 *         emotionWords.csv file
 */
public class EmotionWordsCounter {
	/**
	 * @return
	 * 
	 */
	public static ArrayList<String> readSongsFromCSV() {
		String csvFile = "emotionWords.csv";
		String s = "";
		ArrayList<String> songs = new ArrayList<String>();
		try {

			BufferedReader br = new BufferedReader(new FileReader(csvFile));
			while ((s = br.readLine()) != null) {
				s.split("_");
				songs.add(s);

			}

		} catch (IOException e) {
			e.printStackTrace();

		}
		return songs;
	}
}
