package Main;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DayOfWeek {
    public List<String> getDayOfWeek(List<Map<String, Object>> weeklyData){
        List<String> daysOfWeekList = new ArrayList<>();
        for(Map<String, Object> dailyData : weeklyData) {
            String date = dailyData.get("time").toString();
            String[] splitDate = date.split("-");
            LocalDate localDate = LocalDate.of(Integer.parseInt(splitDate[0]), Integer.parseInt(splitDate[1]), Integer.parseInt(splitDate[2]));
            java.time.DayOfWeek dayOfWeek = localDate.getDayOfWeek();
            daysOfWeekList.add(dayOfWeek.toString());
        }
        return daysOfWeekList;
    }
}
