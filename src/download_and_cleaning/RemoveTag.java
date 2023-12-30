package download_and_cleaning;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import reading.SongsReader;

/**
 * @author Matteo Fiore(ID 17140) the overall goal of this class is to clean the
 *         html files
 *
 */
public class RemoveTag {

	/**
	 * @param fileName
	 * @param clean
	 * @throws Exception
	 */
	/*
	 * this method reads the html Files and through the use of the regex it cleans
	 * all the stuff that are not the lyric of the song Then through the use of the
	 * ByteBuffer it encodes the lyrics In the end it writes the final result in the
	 * CleanedFiles folder
	 */
	public static void remove(String fileName, String clean) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new FileReader(fileName));

		String line;
		while ((line = br.readLine()) != null) {

			sb.append(line).append(System.getProperty("line.separator"));
		}
		String nohtml = sb.toString().replaceAll("\\<[^>]*>", "").replaceAll("href=\"", "")
				.replaceAll("Lyrics \\| Genius Lyrics", "").replaceAll("\\A(?s).*?(Lyrics)\\s{2,}(?-s)", "")
				.replaceAll("(?s)\\s*(More on Genius).*\\z", "").replaceAll("<(\\\"[^\\\"]*\\\"|'[^']*'|[^'\\\">])*>","");

		ByteBuffer byteBuffer = StandardCharsets.UTF_16.encode(nohtml);
		PrintWriter writer;

		try {
			writer = new PrintWriter("CleanedFiles/" + clean + ".html.clean.md");
			writer.println(nohtml);

			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
