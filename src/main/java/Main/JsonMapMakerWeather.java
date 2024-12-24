package Main;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonMapMakerWeather {
    private List<Map<String, Object>> weeklyWeatherData;

    protected void parseResultsJson(String json) {
        if (weeklyWeatherData != null) {
            weeklyWeatherData.clear();
        }

        JSONObject jsonObject = new JSONObject(json);
        weeklyWeatherData = new ArrayList<>();

        // Extracting daily data
        JSONObject daily = jsonObject.getJSONObject("daily");

        // Extracting arrays from the "daily" object
        JSONArray timeArray = daily.getJSONArray("time");
        JSONArray tempMaxArray = daily.getJSONArray("temperature_2m_max");
        JSONArray tempMinArray = daily.getJSONArray("temperature_2m_min");
        JSONArray sunriseArray = daily.getJSONArray("sunrise");
        JSONArray sunsetArray = daily.getJSONArray("sunset");
        JSONArray uvIndexArray = daily.getJSONArray("uv_index_max");
        JSONArray precipitationSumArray = daily.getJSONArray("precipitation_sum");
        JSONArray precipitationProbabilityArray = daily.getJSONArray("precipitation_probability_max");

        // Iterating through the arrays and creating a map for each day
        for (int i = 0; i < timeArray.length(); i++) {
            Map<String, Object> dailyData = new HashMap<>();
            dailyData.put("time", timeArray.getString(i));
            dailyData.put("TempMax", tempMaxArray.getDouble(i));
            dailyData.put("TempMin", tempMinArray.getDouble(i));
            dailyData.put("sunrise", sunriseArray.getString(i));
            dailyData.put("sunset", sunsetArray.getString(i));
            dailyData.put("UVIndex", uvIndexArray.getDouble(i));
            dailyData.put("precipitationSum", precipitationSumArray.getDouble(i));
            dailyData.put("precipitationProbability", precipitationProbabilityArray.getDouble(i));

            // Adding the map for the current day to the weekly list
            weeklyWeatherData.add(dailyData);
        }
    }

    public void getMapFromConnection(String lat, String lon) throws IOException {
        AccessAPI access = new AccessAPI();
        parseResultsJson(access.saveToString(access.getInputStream(access.connectToWeatherAPI(lat, lon))));
    }

    public List<Map<String, Object>> getWeeklyWeatherData() {
        return weeklyWeatherData;
    }
}


