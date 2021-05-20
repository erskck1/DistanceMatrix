package builder;

import dictionary.Alphabet;
import dictionary.Word;

import java.util.List;

public class WordBuilder {

    public static void createAllCombinations(int length, Word initWord, Alphabet[] alphabet, List<Word> collector) {
        if(length == 0) {
            collector.add(initWord);
            return;
        }
        for (int i = 0; i < alphabet.length; ++i) {
            Word newWord = new Word();
            newWord.concat(initWord);
            newWord.append(alphabet[i]);
            createAllCombinations(length-1, newWord, alphabet, collector);
        }
    }
}