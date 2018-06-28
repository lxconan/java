package com.lxconan.javaBasic;

import org.junit.Test;

import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class RegexFacts {

    private boolean isValidFlightNumber(String flightNumber) {
        // TODO: Please implement the method using regular expression to pass the test.
        //
        // The valid flight number should satisfy the following rules:
        // * The first letter must be a upper case letter 'C'
        // * The second letter is either upper case 'A' or upper case 'E'
        // * The following 4 chars must be digits.
        // * The overall length of flight number should be 6.

        // <--Start
        final String expression = "";
        // --End-->

        Pattern pattern = Pattern.compile(expression);
        return pattern.matcher(flightNumber).matches();
    }

    @SuppressWarnings("unused")
    private String[] getFlightNumbers(String text) {
        // TODO: Please implement the method using regular expression to pass the test.
        //
        // The valid flight number should satisfy the following rules:
        // * The first letter must be a upper case letter 'C'
        // * The second letter is either upper case 'A' or upper case 'E'
        // * The following 4 chars must be digits.
        // * The overall length of flight number should be 6.
        return new String[0];
    }

    @Test
    public void should_recognize_flight_identity() {
        final String[] validFlightNumbers = {"CA1223", "CA2234", "CE2368"};
        final String[] invalidFlightNumbers = {"CB2222", "CA312B", "C1112", "2222", "HAHA", "CABC22", "CA132123"};

        for (String validFlightNumber : validFlightNumbers) {
            assertTrue(isValidFlightNumber(validFlightNumber));
        }

        for (String invalidFlightNumber : invalidFlightNumbers) {
            assertFalse(isValidFlightNumber(invalidFlightNumber));
        }
    }

    @Test
    public void should_be_able_to_get_the_matching_string() {
        final String textWithFlight = "Please order CA1222 for me!";
        final String textWithMultipleFlights = "Please order CA1222 and CA1223 for us!";
        final String textWithoutFlight = "What are you saying?";

        String[] oneFlightNumber = getFlightNumbers(textWithFlight);
        String[] twoFlightNumbers = getFlightNumbers(textWithMultipleFlights);
        String[] nonFlightNumber = getFlightNumbers(textWithoutFlight);

        assertArrayEquals(new String[]{"CA1222"}, oneFlightNumber);
        assertArrayEquals(new String[]{"CA1222", "CA1223"}, twoFlightNumbers);
        assertEquals(0, nonFlightNumber.length);
    }
}
