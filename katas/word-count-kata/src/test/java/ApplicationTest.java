import counter.WordCounter;
import io.Reader;
import io.Writer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

class ApplicationTest {
    Application.App cut;
    Reader reader;
    WordCounter wordCounter;
    Writer writer;
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    InputStream in = new ByteArrayInputStream("Mary had a little lamb".getBytes());

    @BeforeEach
    void setup() {
        reader = new Reader(in);
        wordCounter = new WordCounter();
        writer = new Writer(out);
        cut = new Application.App(reader, wordCounter, writer);
    }

    @Test
    void shouldCount5WordsIn5WordSentence() throws IOException {
        cut.run();
        assertThat(out.toString()).contains("Number of words: 4");
    }
}