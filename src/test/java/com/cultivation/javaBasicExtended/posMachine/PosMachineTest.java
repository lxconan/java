package com.cultivation.javaBasicExtended.posMachine;

import com.cultivation.javaBasicExtended.Difficulty;
import com.cultivation.javaBasicExtended.DifficultyLevel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Difficulty(DifficultyLevel.HARD)
class PosMachineTest {
    @Test
    void should_print_empty_if_barcode_is_not_provided() throws IOException {
        PosMachine posMachine = new PosMachine();
        posMachine.readDataSource(createJsonDataBase());
        String receipt = posMachine.printReceipt(null);

        String line = System.lineSeparator();
        final String expected =
            "Receipts" + line +
            "------------------------------------------------------------" + line +
            "------------------------------------------------------------" + line +
            "Price: 0" + line;

        assertEquals(expected, receipt);
    }

    @Test
    void should_print_empty_receipt() throws IOException {
        PosMachine posMachine = new PosMachine();
        posMachine.readDataSource(createJsonDataBase());
        final String barcode = "[]";
        String receipt = posMachine.printReceipt(barcode);

        String line = System.lineSeparator();
        final String expected =
            "Receipts" + line +
            "------------------------------------------------------------" + line +
            "------------------------------------------------------------" + line +
            "Price: 0" + line;

        assertEquals(expected, receipt);
    }

    @Test
    void should_print_receipt_with_one_item() throws IOException {
        PosMachine posMachine = new PosMachine();
        posMachine.readDataSource(createJsonDataBase());
        final String barcode = createBarCodes("0001");
        String receipt = posMachine.printReceipt(barcode);

        String line = System.lineSeparator();
        final String expected =
            "Receipts" + line +
            "------------------------------------------------------------" + line +
            "Coca Cola                       3          1" + line +
            "------------------------------------------------------------" + line +
            "Price: 3" + line;

        assertEquals(expected, receipt);
    }

    @Test
    void should_print_receipt_with_multiple_items() throws IOException {
        PosMachine posMachine = new PosMachine();
        posMachine.readDataSource(createJsonDataBase());
        final String barcode = createBarCodes("0001", "0003", "0005");
        String receipt = posMachine.printReceipt(barcode);

        String line = System.lineSeparator();
        final String expected =
            "Receipts" + line +
                "------------------------------------------------------------" + line +
                "Coca Cola                       3          1" + line +
                "Pepsi-Cola                      5          1" + line +
                "Dr Pepper                       7          1" + line +
                "------------------------------------------------------------" + line +
                "Price: 15" + line;

        assertEquals(expected, receipt);
    }

    @Test
    void should_print_receipt_with_multiple_grouped_items() throws IOException {
        PosMachine posMachine = new PosMachine();
        posMachine.readDataSource(createJsonDataBase());
        final String barcode = createBarCodes("0001", "0003", "0005", "0003");
        String receipt = posMachine.printReceipt(barcode);

        String line = System.lineSeparator();
        final String expected =
            "Receipts" + line +
                "------------------------------------------------------------" + line +
                "Coca Cola                       3          1" + line +
                "Pepsi-Cola                      5          2" + line +
                "Dr Pepper                       7          1" + line +
                "------------------------------------------------------------" + line +
                "Price: 20" + line;

        assertEquals(expected, receipt);
    }

    @Test
    void should_throw_if_data_source_is_not_set() throws IOException {
        PosMachine posMachine = new PosMachine();
        final String barcode = createBarCodes("0001", "0003", "0005", "0003");

        assertThrows(IllegalStateException.class, () -> posMachine.printReceipt(barcode));
    }

    private String createBarCodes(String ...ids) throws IOException {
        StringWriter writer = new StringWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(writer, ids);
        return writer.toString();
    }

    private Reader createJsonDataBase() {
        final String sampleDatabase =
            "[\n" +
            "    {\"id\": \"0001\", \"name\" : \"Coca Cola\", \"price\": 3},\n" +
            "    {\"id\": \"0002\", \"name\" : \"Diet Coke\", \"price\": 4},\n" +
            "    {\"id\": \"0003\", \"name\" : \"Pepsi-Cola\", \"price\": 5},\n" +
            "    {\"id\": \"0004\", \"name\" : \"Mountain Dew\", \"price\": 6},\n" +
            "    {\"id\": \"0005\", \"name\" : \"Dr Pepper\", \"price\": 7},\n" +
            "    {\"id\": \"0006\", \"name\" : \"Sprite\", \"price\": 8},\n" +
            "    {\"id\": \"0007\", \"name\" : \"Diet Pepsi\", \"price\": 9},\n" +
            "    {\"id\": \"0008\", \"name\" : \"Diet Mountain Dew\", \"price\": 10},\n" +
            "    {\"id\": \"0009\", \"name\" : \"Diet Dr Pepper\", \"price\": 11},\n" +
            "    {\"id\": \"0010\", \"name\" : \"Fanta\", \"price\": 12}\n" +
            "]";
        return new StringReader(sampleDatabase);
    }
}
