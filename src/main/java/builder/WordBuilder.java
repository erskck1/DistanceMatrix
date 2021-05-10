package builder;

import dictionary.Alphabet;
import dictionary.Word;
import org.apache.commons.lang3.StringUtils;

public class WordBuilder {

    private final static int letterCount = Alphabet.count();

    public static Word generateBy(int uniqueWordInBase10, int length) {
        Word word = new Word();
        // convert uniqueWordInBase10 to equivalent number in base "letterCount"
        String equivalentInBaseLetterCount = StringUtils.leftPad(Integer.toString(uniqueWordInBase10, letterCount), length, "0");
        for (int i = 0; i < length; i++) {
            word.append(Alphabet.values()[Character.getNumericValue(equivalentInBaseLetterCount.charAt(i))]);
        }
        return word;
    }
}