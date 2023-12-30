package counter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import Exception.MYException;
import counter.EmotionWordsCounter;
import download_and_cleaning.FileName;

import reading.SongsReader;

public class EmotionCounter {

	/**
	 * @author Matteo Fiore(ID 17140) This class is divided into two methods, the
	 *         first one read the cleaned files and count how many emotion words are
	 *         present; the second one write the results of the previous method in a
	 *         new file
	 */
	/**
	 * @param fileName
	 * @return
	 * 
	 * @throws IOException
	 */
	public static List<Entry<String, Long>> countEmotion(String fileName) throws IOException {
		ArrayList<String> list = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String s = br.readLine().replaceAll("[^\\dA-Za-z ]", "").replace("\"", "\"\"").replaceAll("\\R", "");
		while ((s = br.readLine()) != null) {
			String[] words = s.replaceAll("[^\\dA-Za-z ]", "").replace("\"", "\"\"").replaceAll("\\R", "").toLowerCase()
					.split("\\s+");
			for (String word : words) {
				if (EmotionWordsCounter.readSongsFromCSV().contains(word)) {
					list.add(word);
				} else
					list.remove(word);

			}
		}

		Map<String, Long> map = list.stream().collect(Collectors.groupingBy(w -> w, Collectors.counting()));
		List<Map.Entry<String, Long>> result = map.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(20).collect(Collectors.toList());
		br.close();
		return result;
	}

	/**
	 * @param input
	 * @param destination
	 * @throws IOException
	 */
	public static void emotionCount(String input, String destination) throws IOException {
		PrintWriter writer;

		try {
			writer = new PrintWriter("Results/" + destination + ".html.emotioncount.csv");
			for (Entry<String, Long> i : countEmotion(input)) {

				String g = i.getValue() + ";" + i.getKey();

				writer.println(g);
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}