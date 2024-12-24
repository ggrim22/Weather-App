package Main;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class AccessAPI {
    public URLConnection connectToGeocode(String address){
        assert !address.isEmpty();
        URLConnection resultConnection;
        try {
            String encodedURLString = "https://api.geoapify.com/v1/geocode/search?" +
                    "text=" + URLEncoder.encode(address, Charset.defaultCharset()) + "&format=json&" +
                    "apiKey=" + readFromAdminFile();
            resultConnection = createURL(encodedURLString);
        } catch (Exception e) {
            return null;
        }

        return resultConnection;
    }

    public InputStream getInputStream(URLConnection connection) throws IOException {
        return connection.getInputStream();
    }

    public URLConnection connectToWeatherAPI(String lat, String lon) throws IOException {
        String encodedUrlString = "https://api.open-meteo.com/v1/forecast?latitude=" + lat + "&longitude=" + lon + "&daily=temperature_2m_max,temperature_2m_min,sunrise,sunset,uv_index_max,precipitation_sum,precipitation_probability_max";
        URL url = URI.create(encodedUrlString).toURL();
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent", "Garrett Grim");
        connection.connect();
        return connection;

    }



    public String readFromAdminFile() throws IOException {

        try (InputStream inputStream = AccessAPI.class.getClassLoader().getResourceAsStream("APIToken.txt")) {
            assert inputStream != null;
            try (Scanner scanner = new Scanner(inputStream)) {
                if (scanner.hasNextLine()) {
                    return scanner.nextLine();
                }
            }
        }

        return "";
    }

    public String saveToString(InputStream inputStream) throws IOException {
        StringBuilder textBuilder = new StringBuilder();
        try (Reader reader = new BufferedReader(new InputStreamReader
                (inputStream, StandardCharsets.UTF_8))) {
            int c;
            while ((c = reader.read()) != -1) {
                textBuilder.append((char) c);
            }
        }
        return textBuilder.toString();
    }

    private URLConnection createURL(String encodedURL) throws IOException {

        URL url = URI.create(encodedURL).toURL();
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent", "CS222FinalProject/0.1");
        connection.connect();
        return connection;
    }
}
