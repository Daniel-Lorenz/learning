package stopwords;

import io.vavr.collection.List;
import io.vavr.control.Try;

@FunctionalInterface
public interface StopwordsProvider {
    Try<List<String>> getStopwords();
}

