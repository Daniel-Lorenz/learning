package counter;

import io.vavr.collection.List;
import io.vavr.control.Try;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class WordCounterTest {

    WordCounter cut;

    @BeforeEach
    void setup() {
        cut = new WordCounter();
    }

    @Test
    void shouldCountNumberOfWordsCorrectly() {
        String input = "hello world";

        long result = cut.countWords(input);

        assertThat(result).isEqualTo(2L);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "hello-world,   2",
            "hell0world,    2",
            "hello.world,   2",
            "'hello\nworld',  2",
            "hello\tworld,  2"
    })
    void shouldCountNumberOfWordsOfEdgeCaseExamples(String input, long expected) {
        long result = cut.countWords(input);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void shouldNotCountStopWords (){
        String input = "hello world world world";

        var result = cut.countWords(input, () -> Try.success(List.of("world")));

        assertThat(result.isSuccess()).isTrue();
        assertThat(result.get()).isEqualTo(1L);
    }

}