import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;
import download_and_cleaning.RemoveTag;

import reading.SongsReader;

/**
 * @author Matteo Fiore(ID 17140)
 *
 */
public class Runner {
	/**
	 * @param args
	 * 
	 */
	public static void main(String[] args) {
		Facade f = new Facade(SongsReader.readSongsFromCSV());
		Thread d = new Thread(f);
		d.start();
		try {
			d.join();

		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}

	}

}
