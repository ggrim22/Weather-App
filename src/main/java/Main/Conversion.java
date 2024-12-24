package Main;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Conversion {
    public double convertCelsiusToFahrenheit(Object celsiusTemp){
        return (double)celsiusTemp * 1.8 + 32;
    }

    public String convertDate(Object date) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd"); // Assuming input format is "yyyy-MM-dd"
        SimpleDateFormat outputFormat = new SimpleDateFormat("MMMM dd, yyyy"); // Desired output format

        if (date instanceof String) {
            try {
                Date parsedDate = inputFormat.parse((String) date); // Parse the String to a Date
                return outputFormat.format(parsedDate); // Format the parsed Date
            } catch (ParseException e) {
                return "Invalid date format"; // Handle invalid date strings
            }

        }
        return "null";
    }

    public String convertSunriseAndSunsetTime(Object date){
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
        inputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat outputFormat = new SimpleDateFormat("hh:mm aa"); // Desired output format

        if (date instanceof String) {
            try {
                Date parsedDate = inputFormat.parse((String) date); // Parse the String to a Date
                return outputFormat.format(parsedDate); // Format the parsed Date
            } catch (ParseException e) {
                return "Invalid date format"; // Handle invalid date strings
            }

        }
        return "null";
    }
}
