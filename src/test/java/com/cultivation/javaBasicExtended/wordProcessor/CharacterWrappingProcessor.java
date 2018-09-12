package com.cultivation.javaBasicExtended.wordProcessor;

class CharacterWrappingProcessor extends StatefulCharacterProcessor {
    private int currentCharacterIndex = 0;

    CharacterWrappingProcessor(TextProcessorSettings settings) {
        super(settings);
    }

    @Override
    void appendCharacter(ProcessedCharacter character) {
        int lineNumber = 1 + (currentCharacterIndex / getSettings().getWidth());
        character.setLine(lineNumber);
        ++currentCharacterIndex;

        addToStorage(character);
    }
}
