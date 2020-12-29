package counter;

import io.vavr.collection.List;
import io.vavr.control.Try;
import stopwords.StopwordsProvider;

import java.util.function.Predicate;

public class WordCounter {

    private final String whereToSplitWords = "(?![a-zA-Z]+)";

    public Try<Long> countWords(String input, StopwordsProvider provider) {
        var notAStopword = provider.getStopwords()
                .map((l) -> (Predicate<String>) (l::contains))
                .map(Predicate::negate);
        if (notAStopword.isFailure()) {
            return Try.failure(notAStopword.getCause());
        }
        return Try.success((long)
                List.of(input.split(whereToSplitWords))
                        .map(String::trim)
                        .count(notAStopword.get()));
    }

    public long countWords(String input) {
        return countWords(input, () -> Try.success(List.empty())).get();
    }
}
