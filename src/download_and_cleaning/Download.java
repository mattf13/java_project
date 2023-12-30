package download_and_cleaning;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import reading.SongsReader;;

/**
 * @author Matteo Fiore (ID 17140)
 * 
 *  The overall goal of this class is to retrieve the html file of the songs.
 *  
 *
 */
/**
 */

public class Download {
	private static HttpsURLConnection conn;

	/**
	 * the first method basically get the url from the internet. It does it in a way
	 * that prevent the error 403 through the use of the User-Agent
	 */
	/**
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static String getDownloadUrl(String url) throws IOException {
		String count = "";
		URL u = new URL(url);
		conn = (HttpsURLConnection) u.openConnection();
		conn.setReadTimeout(5000);
		conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
		conn.addRequestProperty("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String content = "";
		while ((content = in.readLine()) != null) {
			count += content + "\n";

		}
		return count;

	}

	/**
	 * This method write the html in the gile
	 */
	/**
	 * @param url
	 * @param fileName
	 * @throws IOException
	 * 
	 */
	static void writer(String url, String fileName) throws IOException {
		String page = Download.getDownloadUrl(url);
		PrintWriter writer;
		writer = new PrintWriter(fileName);
		writer.println(page);
		writer.close();
	}

	/*
	 * This method recall the previous two in order to give the file the name with
	 * the song that is associated to
	 */
	/**
	 * @param url
	 * @param song
	 * @throws IOException
	 * 
	 */
	public static void DownloadAndSave(String url, String song) throws IOException {
		getDownloadUrl(url);
		String html = "HTMLFiles/" + FileName.getFileName(song) + ".html";
		writer(url, html);
	}
}