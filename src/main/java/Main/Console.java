package Main;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Console {
    public static void main(String[] args){
        JsonMapMaker mapMaker = new JsonMapMaker();
        JsonMapMakerWeather mapMakerWeather = new JsonMapMakerWeather();
        Scanner scanner = new Scanner(System.in);
        Conversion conversion = new Conversion();
        DayOfWeek dayOfWeek = new DayOfWeek();

        Map<String, Object> mapGeocode;
        try {
            System.out.println("Enter a city and state, ex: Muncie IN, and get the forecast for the week ahead!\n");
            String userInput = scanner.nextLine();
            mapMaker.getMapFromConnectionGeocode(userInput);
            mapGeocode = mapMaker.getJsonMap();
            String lat = (String) mapGeocode.get("lat");
            String lon = (String) mapGeocode.get("lon");

            mapMakerWeather.getMapFromConnection(lat, lon);
            List<Map<String, Object>> weeklyData = mapMakerWeather.getWeeklyWeatherData();

            for (int i = 0; i < weeklyData.size(); i++) {
                Map<String, Object> dailyData = weeklyData.get(i);
                System.out.printf(
                        "Date: %s %s\nTemp Max: %.1f°F\nTemp Min: %.1f°F\nSunrise: %s\nSunset: %s\nUV Index: %.1f\nPrecipitation Sum: %.1f mm\nPrecipitation Probability: %.1f%%\n\n",
                        dayOfWeek.getDayOfWeek(weeklyData).get(i),
                        conversion.convertDate(dailyData.get("time")),
                        conversion.convertCelsiusToFahrenheit(dailyData.get("TempMax")),
                        conversion.convertCelsiusToFahrenheit(dailyData.get("TempMin")),
                        conversion.convertSunriseAndSunsetTime(dailyData.get("sunrise")),
                        conversion.convertSunriseAndSunsetTime(dailyData.get("sunset")),
                        dailyData.get("UVIndex"),
                        dailyData.get("precipitationSum"),
                        dailyData.get("precipitationProbability")
                );
            }
        }catch (Exception e){
           e.printStackTrace();
        }
    }
}
