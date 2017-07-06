package tech.rory.mobilesafe.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Read the system stream.
 */

public class StreamUtils {
	/**
	 * Convert the Input Stream to String
	 * 
	 * @param in
	 *            The Input Stream.
	 * @return The convert string stream.
	 * @throws IOException
	 */
	public static String readFormStream(InputStream in) throws IOException {

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		int len = 0;
		byte[] buffer = new byte[1024];

		while ((len = in.read(buffer)) != -1) {
			outputStream.write(buffer, 0, len);
		}
		String resultString = outputStream.toString();
		in.close();
		outputStream.close();
		return resultString;

	}

}
