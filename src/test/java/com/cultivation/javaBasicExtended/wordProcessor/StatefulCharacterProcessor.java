package com.cultivation.javaBasicExtended.wordProcessor;

import java.util.ArrayList;
import java.util.List;

abstract class StatefulCharacterProcessor {
    private final TextProcessorSettings settings;
    private final List<ProcessedCharacter> storage = new ArrayList<>();

    StatefulCharacterProcessor(TextProcessorSettings settings) {
        this.settings = settings;
    }

    abstract void appendCharacter(ProcessedCharacter character);

    ProcessedCharacter[] getCharacters() {
        return storage.toArray(new ProcessedCharacter[0]);
    }

    TextProcessorSettings getSettings() {
        return settings;
    }

    void addToStorage(ProcessedCharacter character) {
        storage.add(character);
    }
}

