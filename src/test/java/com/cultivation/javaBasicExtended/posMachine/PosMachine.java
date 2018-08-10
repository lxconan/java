package com.cultivation.javaBasicExtended.posMachine;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.Reader;
import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("WeakerAccess")
public class PosMachine {
    private static final String separator = "------------------------------------------------------------";
    private final String line = System.lineSeparator();
    private Map<String, Product> products;

    public void readDataSource(Reader reader) throws IOException {
        // TODO: please implement the following method to pass the test
        // <--start
        ObjectMapper objectMapper = new ObjectMapper();
        products = Arrays.stream(objectMapper.readValue(reader, Product[].class))
            .collect(
                HashMap::new,
                (map, item) -> map.put(item.getId(), item),
                HashMap::putAll);
        // --end-->
    }

    public String printReceipt(String barcodeContent) throws IOException {
        // TODO: please implement the following method to pass the test
        // <--start
        if (products == null) { throw new IllegalStateException(); }
        String[] ids = readIds(barcodeContent);
        return createReceipt(ids);
        // --end-->
    }

    private String createReceipt(String[] ids) {
        Map<Product, Long> receiptProducts = Arrays.stream(ids)
            .map(id -> this.products.get(id))
            .collect(Collectors.groupingBy(p -> p, Collectors.counting()));

        return composeReceipt(receiptProducts);
    }

    private String composeReceipt(Map<Product, Long> receiptProducts) {
        StringBuilder receiptBuilder = new StringBuilder();
        receiptBuilder.append("Receipts").append(line).append(separator).append(line);
        writeReceiptProducts(receiptBuilder, receiptProducts);
        receiptBuilder.append(separator).append(line);
        writeReceiptPrice(receiptProducts, receiptBuilder);
        return receiptBuilder.toString();
    }

    private void writeReceiptPrice(Map<Product, Long> receiptProducts, StringBuilder receiptBuilder) {
        Long totalPrice = receiptProducts.entrySet().stream()
            .map(e -> e.getKey().getPrice() * e.getValue())
            .reduce(0L, (a, b) -> a + b);
        receiptBuilder.append(String.format("Price: %d", totalPrice)).append(line);
    }

    private void writeReceiptProducts(StringBuilder receiptBuilder, Map<Product, Long> receiptProducts) {
        receiptProducts.entrySet().stream()
            .sorted(Comparator.comparing(o -> o.getKey().getId()))
            .forEach(e -> {
                Product product = e.getKey();
                String productLine = String.format(
                    "%-32s%-11s%s",
                    product.getName(),
                    product.getPrice().toString(),
                    e.getValue().toString());
                receiptBuilder.append(productLine).append(line);
            });
    }

    private String[] readIds(String barcodeContent) throws IOException {
        String[] ids = new String[0];

        if (barcodeContent != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            ids = objectMapper.readValue(barcodeContent, String[].class);
        }
        return ids;
    }
}

class Product {
    private String id;
    private String name;
    private Integer price;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (getClass() != obj.getClass()) return false;

        Product other = (Product) obj;

        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}