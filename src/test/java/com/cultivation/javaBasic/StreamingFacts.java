package com.cultivation.javaBasic;

import com.cultivation.javaBasic.util.AnimeCharacter;
import com.cultivation.javaBasic.util.ValueHolder;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class StreamingFacts {
    @Test
    void should_be_able_to_turn_collection_into_stream() {
        List<String> words = Arrays.asList("a", "quick", "brown", "fox", "jumps", "over");

        // TODO: please modify the following code to pass the test
        // <--start
        try (Stream<String> wordStream = words.stream())
        // --end-->
        {
            assertIterableEquals(
                words,
                wordStream.collect(Collectors.toList())
            );
        }
    }

    @Test
    void should_be_able_to_turn_array_into_stream() {
        String[] words = {"a", "quick", "brown", "fox", "jumps", "over"};

        // TODO: please modify the following code to pass the test
        // <--start
        try (Stream<String> wordStream = Stream.of(words))
        // --end-->
        {
            assertArrayEquals(
                words,
                wordStream.toArray(String[]::new)
            );
        }
    }

    @Test
    void should_be_able_to_generate_empty_stream() {
        // TODO: please modify the following code to pass the test
        // <--start
        try (Stream<String> emptyWordStream = Stream.empty())
        // --end-->
        {
            assertEquals(0, emptyWordStream.count());
        }
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_generate_infinite_stream_with_same_items() {
        // TODO: please modify the following code to pass the test
        // <--start
        try (Stream<String> infiniteEchos = Stream.generate(() -> "Echo"))
        // --end-->
        {
            assertEquals("Echo", infiniteEchos.skip(10000).findFirst().get());
        }
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_generate_infinite_stream_of_sequence() {
        // TODO: please modify the following code to pass the test
        // <--start
        try (Stream<Integer> infiniteSequence = Stream.iterate(0, item -> ++item))
        // --end-->
        {
            assertEquals(10000, infiniteSequence.skip(10000).findFirst().get().intValue());
        }
    }

    @SuppressWarnings("TryFinallyCanBeTryWithResources")
    @Test
    void should_be_able_to_filter_stream() {
        Stream<String> wordStream = Stream.of("a", "quick", "brown", "fox", "jumps", "over");

        // TODO: please write code to filter word whose length is greater than 4
        // <--start
        try (Stream<String> filtered = wordStream.filter(w -> w.length() > 4))
        // --end-->
        {
            assertArrayEquals(new String[]{"quick", "brown", "jumps"}, filtered.toArray(String[]::new));
        }
    }

    @Test
    void should_be_able_to_map_stream() {
        Stream<String> wordStream = Stream.of("a", "quick", "brown", "fox", "jumps", "over");

        // TODO: please write code to filter word whose length is greater than 4
        // <--start
        try (Stream<String> filtered = wordStream.map(String::toUpperCase))
        // --end-->
        {
            assertArrayEquals(
                new String[]{"A", "QUICK", "BROWN", "FOX", "JUMPS", "OVER"},
                filtered.toArray(String[]::new));
        }
    }

    @Test
    void should_be_able_to_map_item_to_a_new_type() {
        Stream<String> nameStream = Stream.of("Naruto", "Kisuke", "Tomoya");

        // TODO: please modify the following code to pass the test
        // <--start
        try (Stream<AnimeCharacter> characters = nameStream.map(AnimeCharacter::new))
        // --end-->
        {
            AnimeCharacter[] actual = characters.toArray(AnimeCharacter[]::new);
            assertArrayEquals(
                new AnimeCharacter[] {
                    new AnimeCharacter("Naruto"),
                    new AnimeCharacter("Kisuke"),
                    new AnimeCharacter("Tomoya")
                },
                actual);
        }
    }

    @Test
    void should_flatten_stream_of_streams() {
        Stream<Stream<Character>> nameStream = Stream
            .of("Naruto", "Kisuke", "Tomoya")
            .map(StreamingFacts::letters);

        // TODO: please modify the following code to pass the test
        // <--start
        try (Stream<Character> flatted = nameStream.flatMap(word -> word))
        // --end-->
        {
            assertArrayEquals(
                new Character[] {
                    'N', 'a', 'r', 'u', 't', 'o', 'K', 'i', 's', 'u', 'k',
                    'e', 'T', 'o', 'm', 'o', 'y', 'a'
                },
                flatted.toArray(Character[]::new));
        }
    }

    @Test
    void should_create_sequence_of_specified_size() {
        Stream<Integer> infiniteSequence = Stream.iterate(0, i -> i + 1);

        // TODO: please modify the following code to pass the test
        // <--start
        try (Stream<Integer> finiteStream = infiniteSequence.limit(10))
        // --end-->
        {
            assertArrayEquals(
                new Integer[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                finiteStream.toArray(Integer[]::new)
            );
        }
    }

    @Test
    void should_concat_streams() {
        Stream<Character> helloStream = Stream.of('H', 'e', 'l', 'l', 'o');
        Stream<Character> worldStream = Stream.of('W', 'o', 'r', 'l', 'd');

        // TODO: please modify the following code to pass the test
        // <--start
        try (Stream<Character> concatStream = Stream.concat(helloStream, worldStream))
        // --end-->
        {
            assertArrayEquals(
                letters("HelloWorld").toArray(Character[]::new),
                concatStream.toArray(Character[]::new)
            );
        }
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    void should_get_unique_items() {
        Stream<Character> characterStream = letters("aquickbrownfox");

        // TODO: please modify the following code to pass the test
        // <--start
        try (Stream<Character> distinct = characterStream.distinct())
        // --end-->
        {
            Character[] characters = distinct.sorted().toArray(Character[]::new);

            assertArrayEquals(
                new Character[] {'a', 'b', 'c', 'f', 'i', 'k', 'n', 'o', 'q', 'r', 'u', 'w', 'x'},
                characters
            );
        }
    }

    @Test
    void should_hook_stream_generation() {
        ValueHolder<Integer> holder = new ValueHolder<>();
        holder.setValue(0);

        Stream<Integer> hookStream = Stream
            .iterate(0, i -> i + 1)
            .limit(20)
            .filter(v -> v % 2 == 0)
            .peek(v -> holder.setValue(holder.getValue() + v));

        hookStream.forEach(i -> {});

        // TODO: please modify the following code to pass the test
        // <--start
        final int expected = 90;
        // --end-->

        assertEquals(expected, holder.getValue().intValue());
    }

    @SuppressWarnings({"ConstantConditions", "unchecked"})
    @Test
    void should_throws_if_get_value_of_empty_optional() {
        // TODO: please create an empty optional and specify the concrete exception type.
        // <--start
        Optional<String> empty = Optional.empty();
        Class errorType = NoSuchElementException.class;
        // --end-->

        assertThrows(errorType, empty::get);
    }

    @Test
    void should_provide_a_default_value_using_or_else() {
        Optional<String> empty = Optional.empty();
        Optional<String> nonEmpty = Optional.of("great");

        String emptyResult = getValue(empty, "default value");
        String nonEmptyResult = getValue(nonEmpty, "default value");

        assertEquals("default value", emptyResult);
        assertEquals("great", nonEmptyResult);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_throw_for_empty_optional() {
        Optional<String> empty = Optional.empty();

        // TODO: please modify the following code to pass the test
        // <--start
        Runnable emptyRunnable = () -> empty.orElseThrow(IllegalStateException::new);
        // --end-->

        assertThrows(IllegalStateException.class, emptyRunnable::run);
    }

    @Test
    void should_process_value_if_present() {
        Optional<String> optional = Optional.of("word");
        List<String> result = new ArrayList<>();

        // TODO: please add the value to result if optional is present
        // <--start
        Consumer<Optional<String>> optionalConsumer =
            v -> v.ifPresent(s -> result.add(s.toUpperCase()));
        // --end-->

        optionalConsumer.accept(optional);

        assertEquals("WORD", result.get(0));
    }

    @Test
    void should_map_value_if_present() {
        Optional<String> optional = Optional.of("word");
        Optional<String> empty = Optional.empty();

        List<String> result = new ArrayList<>();

        // TODO: please add the value to result if optional is present. The return the result as mapping result.
        // <--start
        Function<Optional<String>, Optional<Boolean>> mapping =
            v -> v.map(s -> result.add(s.toUpperCase()));
        // --end-->

        Optional<Boolean> mappingResult = mapping.apply(optional);
        Optional<Boolean> emptyMappingResult = mapping.apply(empty);

        assertTrue(mappingResult.orElseThrow(IllegalStateException::new));
        assertEquals("WORD", result.get(0));
        assertFalse(emptyMappingResult.isPresent());
    }

    @Test
    void should_flat_map_optional_value_do_chain_method() {
        Stream<YieldOptional> emptyStream = Stream.of(1, 2, 3)
            .filter(i -> i > 4)
            .map(i -> new YieldOptional());
        Stream<YieldOptional> nonEmptyStream = Stream.of(1, 2, 3)
            .filter(i -> i > 1)
            .map(i -> new YieldOptional());

        // TODO: please modify the following code to pass the test
        // <--start
        Function<Stream<YieldOptional>, Optional<String>> findFirstAndGet =
            (Stream<YieldOptional> s) -> s.findFirst().flatMap(YieldOptional::get);
        // --end-->

        Optional<String> emptyStreamResult = findFirstAndGet.apply(emptyStream);
        Optional<String> nonEmptyStreamResult = findFirstAndGet.apply(nonEmptyStream);

        assertFalse(emptyStreamResult.isPresent());
        assertTrue(nonEmptyStreamResult.isPresent());
        assertEquals("Hello", nonEmptyStreamResult.get());
    }

    @SuppressWarnings({"SameParameterValue", "OptionalUsedAsFieldOrParameterType"})
    private static <T> T getValue(Optional<T> optional, T defaultValue) {
        // TODO: please implement the following method to pass the test
        // <--start
        return optional.orElse(defaultValue);
        // --end-->
    }

    private static Stream<Character> letters(String text) {
        List<Character> characters = new ArrayList<>();
        for (int i = 0; i < text.length(); ++i) {
            characters.add(text.charAt(i));
        }
        return characters.stream();
    }
}

class YieldOptional {
    Optional<String> get() {
        return Optional.of("Hello");
    }
}