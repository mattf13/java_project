package counter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author Matteo Fiore(ID 17140) This class has the purpose to read the
 *         ignoreWords.csv file
 *
 */
public class IgnoreWordReader {
	/**
	 * @return
	 * 
	 */
	public static ArrayList<String> readSongsFromCSV() {
		String csvFile = "ignoreWords.csv";
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
