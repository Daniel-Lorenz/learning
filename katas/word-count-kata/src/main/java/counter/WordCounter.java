package counter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCounter {

    private final Pattern wordPattern = Pattern.compile("[a-zA-Z]+");

    public long countWords(String input) {
        return wordPattern.matcher(input).results().count();
    }
}
