package com.cultivation.javaBasic.showYourIntelligence;

import java.util.*;

public class DistinctIterable<T> implements Iterable<T> {
    private Iterable<T> iterable;

    public DistinctIterable(Iterable<T> iterable) {
        this.iterable = iterable;
    }

    @Override
    public Iterator<T> iterator() {
        return new DistinctIterator<>(iterable.iterator());
    }

    public List<T> toList() {
        ArrayList<T> result = new ArrayList<>();
        this.forEach(result::add);
        return result;
    }
}

class DistinctIterator<E> implements Iterator<E> {
    // TODO: Implement the class to pass the test. Note that you cannot put all items into memory or you will fail.
    // <--start
    private final Iterator<E> iterator;
    private final Set<E> iterated = new HashSet<>();
    private E cachedNext;
    private boolean nextHasPopulated;

    DistinctIterator(Iterator<E> iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        if (nextHasPopulated) {return true;}

        while (iterator.hasNext()) {
            E next = iterator.next();
            if (iterated.add(next)) {
                cachedNext = next;
                nextHasPopulated = true;
                return true;
            }
        }

        return false;
    }

    @Override
    public E next() {
        nextHasPopulated = false;
        return cachedNext;
    }
    // --end->
}