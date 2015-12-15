package ch.ahv;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AHVNumberValidatorTest {

    private static final ArrayList<String> validUnformattedAhvNumbers = new ArrayList<>(
            Arrays.asList("7563147702037",
                    "7562467916773",
                    "7563075036747",
                    "7568658069655",
                    "7564917775244",
                    "7565681599272",
                    "7568332468651",
                    "7560104252130",
                    "7566046667124",
                    "7562233145604",
                    "7562766256976",
                    "7568080438791",
                    "7564320763173",
                    "7564637183596",
                    "7568842294931",
                    "7568293820338",
                    "7561775234005",
                    "7560800560997",
                    "7567518131150",
                    "7565437971581"
            ));
    private static final ArrayList<String> unformattedAhvNumbersWithErrors = new ArrayList<>(
            Arrays.asList("75631477020",
                    "756.2467916773",
                    "75630750aa747",
                    "7568658069657",
                    "7564917775248"
            ));

    private static final ArrayList<String> validFormattedAhvNumbers = new ArrayList<>(
            Arrays.asList("756.6649.0165.10",
                    "756.2954.3921.03",
                    "756.8720.5207.25",
                    "756.2171.7486.08",
                    "756.2243.0928.51",
                    "756.4726.9930.20",
                    "756.6893.4215.80",
                    "756.8085.5137.52",
                    "756.4751.1417.00",
                    "756.8983.5268.47",
                    "756.2864.3387.10",
                    "756.1873.4929.70",
                    "756.6807.7940.14",
                    "756.6283.0620.72",
                    "756.5883.1022.32",
                    "756.3058.1098.71",
                    "756.3395.8383.35",
                    "756.8054.5560.56",
                    "756.4383.9237.43",
                    "756.1004.7681.41",
                    "756.0288.4227.25"
            ));

    private static final ArrayList<String> formattedAhvNumbersWithErrors = new ArrayList<>(
            Arrays.asList("756-6649-0165-10",
                    "7562a54392103",
                    "75a.8720.5207.25",
                    "756.2171.7486.10",
                    "756.2243.0928.53",
                    "756.4726.9930.21"
            ));

    @Test
    public void shouldReturnIsNotValidAhvNumberIfInputIsNull(){
        //given
        String ahvNumberToValidate = null;

        //when
        boolean validAHVNumber = AHVNumberValidator.isValidAHVNumber(ahvNumberToValidate);

        //then
        assertFalse(validAHVNumber);
    }

    @Test
    public void shouldReturnIsNotValidAhvNumberIfInputIsEmptyString(){
        //given
        String ahvNumberToValidate = "";

        //when
        boolean validAHVNumber = AHVNumberValidator.isValidAHVNumber(ahvNumberToValidate);

        //then
        assertFalse(validAHVNumber);
    }

    @Test
    public void shouldReturnHasNotValidAHVNumberFormatIfContainsLetters() {

        //given
        String number = "452.4521.4521.aa";

        //when
        boolean validAHVNumber = AHVNumberValidator.hasAHVNumberFormat(number);

        //then
        assertFalse(validAHVNumber);
    }

    @Test
    public void shouldReturnHasNotValidAHVNumberFormatWithWrongSeparators() {

        //given
        String number = "452-4521-4581-99";

        //when
        boolean validAHVNumber = AHVNumberValidator.hasAHVNumberFormat(number);

        //then
        assertFalse(validAHVNumber);
    }

    @Test
    public void shouldReturnHasValidAHVNumberFormat() {

        //given
        String number = "123.2365.2356.12";

        //when
        boolean validAHVNumber = AHVNumberValidator.hasAHVNumberFormat(number);

        //then
        assertTrue(validAHVNumber);
    }

    @Test
    public void shouldReturnIsValidUnformattedAHVNumber() {

        //given
        String number = "7569217076985";

        //when
        boolean validAHVNumber = AHVNumberValidator.isValidUnformattedAHVNumber(number);

        //then
        assertTrue(validAHVNumber);
    }

    @Test
    public void shouldReturnIsNotValidUnformattedAHVNumberIfContainsLetters() {

        //given
        String number = "123aa65235612";

        //when
        boolean validAHVNumber = AHVNumberValidator.isValidUnformattedAHVNumber(number);

        //then
        assertFalse(validAHVNumber);
    }

    @Test
    public void shouldReturnFormattedAhvNumber() {
        //given
        String unformattedAhvNumber = "1254786665841";

        //when
        String formattedAHVNumber = AHVNumberValidator.getFormattedAHVNumber(unformattedAhvNumber);

        //then
        assertThat(formattedAHVNumber, is("125.4786.6658.41"));
    }

    @Test
    public void shouldReturnHasValidControlDigit() {
        //given
        String ahvNumber = "756.9217.0769.85";

        //when
        boolean isControlDigitCorrect = AHVNumberValidator.isControlDigitCorrect(ahvNumber);

        //then
        assertTrue(isControlDigitCorrect);
    }

    @Test
    public void shouldReturnAreValidUnformattedAHVNumbers() {
        validUnformattedAhvNumbers.forEach(ahv -> assertTrue(AHVNumberValidator.isValidUnformattedAHVNumber(ahv)));
    }

    @Test
    public void shouldReturnAreValidFormattedAHVNumbers() {
        validFormattedAhvNumbers.forEach(ahv -> assertTrue(AHVNumberValidator.isValidFormattedAHVNumber(ahv)));
    }

    @Test
    public void shouldReturnAreValidAHVNumbers() {
        validUnformattedAhvNumbers.forEach(ahv -> assertTrue(AHVNumberValidator.isValidAHVNumber(ahv)));
        validFormattedAhvNumbers.forEach(ahv -> assertTrue(AHVNumberValidator.isValidAHVNumber(ahv)));
    }

    @Test
    public void shouldReturnAreNotValidUnformattedAHVNumbers() {
        unformattedAhvNumbersWithErrors.forEach(ahv -> assertFalse(ahv + " is valid.", AHVNumberValidator.isValidUnformattedAHVNumber(ahv)));
    }

    @Test
    public void shouldReturnAreNotValidFormattedAHVNumbers() {
        formattedAhvNumbersWithErrors.forEach(ahv -> assertFalse(ahv + " is valid.", AHVNumberValidator.isValidFormattedAHVNumber(ahv)));
    }

    @Test
    public void shouldReturnAreNotValidAHVNumbers() {
        unformattedAhvNumbersWithErrors.forEach(ahv -> assertFalse(ahv + " is valid.", AHVNumberValidator.isValidAHVNumber(ahv)));
        formattedAhvNumbersWithErrors.forEach(ahv -> assertFalse(ahv + " is valid.", AHVNumberValidator.isValidAHVNumber(ahv)));
    }


}
