import Main.AccessAPI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.URLConnection;

public class AccessAPITest {
    @Test
    public void ConnectToGeocodeTest() throws IOException {
        AccessAPI api = new AccessAPI();
        String address = "146 S Nursery Rd Anderson IN 46012 US";
        URLConnection result;
        result = api.connectToGeocode(address);
        Assertions.assertNotNull(result);
    }

    @Test
    public void ConnectToWeatherAPITest() throws IOException {
        AccessAPI api = new AccessAPI();
        String lat = "52.52";
        String lon = "13.41";
        URLConnection result;
        result = api.connectToWeatherAPI(lat, lon);
        Assertions.assertNotNull(result);
    }
}