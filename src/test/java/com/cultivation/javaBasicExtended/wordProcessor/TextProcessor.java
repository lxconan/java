package com.cultivation.javaBasicExtended.wordProcessor;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class TextProcessor {
    private final TextProcessorSettings settings;

    TextProcessor(int width) {
        this(width, null);
    }

    TextProcessor(int width, char[] whitespaces) {
        if (width < 10 || width > 80) {
            throw new IllegalArgumentException("Width out of range.");
        }

        settings = new TextProcessorSettings(width, getWhitespaces(whitespaces));
    }

    private char[] getWhitespaces(char[] whitespaces) {
        return whitespaces == null ?
            new char[] {' '} :
            whitespaces;
    }

    String process(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Text is mandatory.");
        }

        ProcessedCharacter[] processedCharacters = convertToProcessedCharacters(text);
        processedCharacters = groupingCharacters(processedCharacters);
        processedCharacters = wrappingCharacters(processedCharacters);

        return convertToOutput(processedCharacters);
    }

    private String convertToOutput(ProcessedCharacter[] processedCharacters) {
        Map<Integer, List<ProcessedCharacter>> grouped = Arrays.stream(processedCharacters)
            .collect(Collectors.groupingBy(ProcessedCharacter::getGroupId));
        StringBuilder builder = new StringBuilder();
        int size = grouped.size();
        for (int i = 1; i <= size; ++i) {
            List<ProcessedCharacter> word = grouped.get(i);
            String wordString = word.stream().collect(
                StringBuilder::new,
                (sb, p) -> sb.append(p.getValue()),
                (target, sb) -> target.append(sb.toString())
            ).toString();
            String lines = word.stream().map(ProcessedCharacter::getLine).distinct().sorted()
                .map(Object::toString).collect(Collectors.joining(","));
            builder.append(wordString).append("(").append(lines).append(");");
        }

        return builder.toString();
    }

    private ProcessedCharacter[] wrappingCharacters(ProcessedCharacter[] processedCharacters) {
        return processCharacters(processedCharacters, new CharacterWrappingProcessor(settings));
    }

    private ProcessedCharacter[] groupingCharacters(ProcessedCharacter[] processedCharacters) {
        return processCharacters(processedCharacters, new CharacterGroupingProcessor(settings));
    }

    private ProcessedCharacter[] processCharacters(
        ProcessedCharacter[] processedCharacters, StatefulCharacterProcessor processor) {
        for (ProcessedCharacter c : processedCharacters) {
            processor.appendCharacter(c);
        }
        return processor.getCharacters();
    }

    private ProcessedCharacter[] convertToProcessedCharacters(String text) {
        return text.chars()
                .mapToObj(c -> (char) c)
                .map(c -> new ProcessedCharacter(c, settings.isWhitespace(c)))
                .toArray(ProcessedCharacter[]::new);
    }
}

