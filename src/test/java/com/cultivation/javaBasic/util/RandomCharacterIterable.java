package com.cultivation.javaBasic.util;

import java.util.Iterator;
import java.util.Random;

public class RandomCharacterIterable implements Iterable<Character> {
    private final int size;
    private final Character[] candidates;

    public RandomCharacterIterable(int size, Character[] candidates) {
        this.size = size;
        this.candidates = candidates;
        if (size <= 0) throw new IllegalArgumentException("Invalid size.");
        if (candidates == null || candidates.length == 0) throw new IllegalArgumentException("Empty candidates");
    }

    @Override
    public Iterator<Character> iterator() {
        return new RandomCharacterIterator();
    }

    private class RandomCharacterIterator implements Iterator<Character> {
        private final Random random = new Random();
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public Character next() {
            int nextIndex = random.nextInt(candidates.length);
            Character candidate = candidates[nextIndex];
            ++currentIndex;
            return candidate;
        }
    }
}
