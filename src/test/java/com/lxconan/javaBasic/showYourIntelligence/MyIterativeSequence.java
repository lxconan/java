package com.lxconan.javaBasic.showYourIntelligence;

import java.util.Iterator;

public class MyIterativeSequence implements Iterable<Integer> {
    private final int startInclusive;
    private final int endExclusive;

    public MyIterativeSequence(int startInclusive, int endExclusive) {
        this.startInclusive = startInclusive;
        this.endExclusive = endExclusive;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new MyIterativeSequenceIterator(startInclusive, endExclusive);
    }
}
