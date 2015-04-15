package googlemapid.sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonReader {

	private static String readAll(final Reader rd) throws IOException {
		final StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public static JSONObject read(final String url) throws IOException, JSONException {
		HttpURLConnection urlc = (HttpURLConnection) new URL(url).openConnection();
		urlc.setRequestProperty("Connection", "close");
		urlc.setConnectTimeout(10000); // mTimeout is in seconds
		urlc.connect();

		if (urlc.getResponseCode() == HttpURLConnection.HTTP_OK) {

			final InputStream is = new URL(url).openStream();
			try {
				final BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
				final String jsonText = readAll(rd);
				final JSONObject json = new JSONObject(jsonText);
				return json;
			} finally {
				is.close();
			}
		} else {
			return null;
		}
	}
}