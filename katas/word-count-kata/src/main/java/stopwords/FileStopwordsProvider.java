package stopwords;

import io.vavr.collection.List;
import io.vavr.control.Try;

import java.nio.file.Files;
import java.nio.file.Path;

public class FileStopwordsProvider implements StopwordsProvider {
    private final Path path;

    public FileStopwordsProvider(String path) {
        this.path = Path.of(path);
    }

    @Override
    public Try<List<String>> getStopwords() {
        return Try.of(
                () -> Files.readAllLines(path)
                        .stream().collect(List.collector()));
    }
}
