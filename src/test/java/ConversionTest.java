import Main.Conversion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConversionTest {
    @Test
    public void convertCelsiusToFahrenheitTest(){
        Conversion conversion = new Conversion();
        double result = conversion.convertCelsiusToFahrenheit(11.5);
        Assertions.assertEquals(52.7, result);
    }

}
