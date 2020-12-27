import counter.WordCounter;
import io.Reader;
import io.Writer;
import io.vavr.control.Try;

public class Application {

    public static void main(String[] args) {
        Reader reader = new Reader();
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

        public void run(){
            writer.write("Enter text: ");
            Try<String> input = reader.readLine(System.in);
            if(input.isFailure()) {
                writer.write("Error while reading the input.\n" + input.getCause().getMessage());
                System.exit(1);
            }
            long numberOfWords = wordCounter.countWords(input.get());
            writer.write("Number of words: " + numberOfWords);
        }
    }
}
