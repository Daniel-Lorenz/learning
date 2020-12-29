import counter.WordCounter;
import io.Reader;
import io.Writer;
import io.vavr.control.Try;
import stopwords.FileStopwordsProvider;

import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException {
        Reader reader = new Reader(System.in);
        WordCounter wordCounter = new WordCounter();
        Writer writer = new Writer(System.out);
        App app = new App(reader, wordCounter, writer);

        app.run();
    }

    static class App{
        private final Reader reader;
        private final WordCounter wordCounter;
        private final Writer writer;

        App(Reader reader, WordCounter wordCounter, Writer writer) {
            this.reader = reader;
            this.wordCounter = wordCounter;
            this.writer = writer;
        }

        public void run() throws IOException {
            writer.write("Enter text: ");
            Try<String> input = reader.readLine();
            if(input.isFailure()) {
                writer.write("Error while reading the input.\n" + input.getCause().getMessage());
                System.exit(1);
            }
            var numberOfWords = wordCounter.countWords(input.get(), new FileStopwordsProvider("src/main/resources/stopwords.txt"));
            if(numberOfWords.isFailure()){
                writer.write("Failed to count words: " + numberOfWords.getCause().getMessage());
            }else {
                writer.write("Number of words: " + numberOfWords.get());
            }
        }
    }
}
