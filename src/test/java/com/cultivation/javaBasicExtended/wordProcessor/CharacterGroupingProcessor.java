package com.cultivation.javaBasicExtended.wordProcessor;

class CharacterGroupingProcessor extends StatefulCharacterProcessor {
    private int currentGroupNumber = 0;
    private boolean isCurrentCharacterSpace = false;

    CharacterGroupingProcessor(TextProcessorSettings settings) {
        super(settings);
    }

    @Override
    void appendCharacter(ProcessedCharacter character) {
        boolean groupChanged = currentGroupNumber == 0 ||
            isCurrentCharacterSpace != character.isWhitespace();

        if (groupChanged) {
            currentGroupNumber = ++currentGroupNumber;
            isCurrentCharacterSpace = character.isWhitespace();
        }

        character.setGroupId(currentGroupNumber);
        addToStorage(character);
    }
}
