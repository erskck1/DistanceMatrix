package simulator;

import builder.WordBuilder;
import dictionary.Alphabet;
import dictionary.Word;
import org.apache.commons.lang3.StringUtils;
import util.Helper;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Simulator {

    /* Algorithm for generating unique words
    *
    * */
    public List<Word> createUniqueWordsBy(int numberOfWords, int wordLength, int distanceBetweenWords) {
        List<Word> words = new ArrayList<Word>();

        int letterCount = Alphabet.count();     // base
        int maxWordCount = Helper.calculateNumberOfWordCombinationsBy(wordLength, letterCount); // max word combination of wordLength
        int maxWordCountWithDistance = Helper.calculateNumberOfWordCombinationsBy(wordLength, letterCount, distanceBetweenWords);  // max word combination of wordLength with at least distanceBetweenWords
        int stepSize = Helper.calculatStepSize(distanceBetweenWords, letterCount); // step size for generating unique words
        int uniqueWordInBase10 = Helper.randomInt(maxWordCountWithDistance) * stepSize; // start index

        for (int i = 0; i < numberOfWords; i++) {
            words.add(WordBuilder.generateBy(uniqueWordInBase10, wordLength));
            uniqueWordInBase10 = (uniqueWordInBase10 + stepSize);    // next unique word
            if (uniqueWordInBase10 > maxWordCount) {    // shift back the index
                uniqueWordInBase10 = 0;
            }
        }

        return words;
    }

    public void printDistanceMatrix(List<Word> words) {
        StringBuilder header = new StringBuilder();
        StringBuilder rows = new StringBuilder();
        String delimiter = "\t";

        for (Word word1 : words) {
            StringJoiner distances = new StringJoiner(delimiter);
            for (Word word2 : words) {
                int distance = Helper.calculateDistanceBetween(word1.toString(), word2.toString());
                if (word1 == word2) {
                    distance = 0;
                }
                distances.add(StringUtils.center(String.valueOf(distance), words.get(0).length(), " ").replace("0", "-"));
            }
            distances.add("\n");

            header.append(word1.toString());
            header.append(delimiter);

            rows.append(word1.toString());
            rows.append(delimiter);
            rows.append(distances);
        }

        String headerEmptyString = StringUtils.leftPad("", words.get(0).length(), " ") + delimiter;
        header.insert(0, headerEmptyString);
        header.append("\n");
        rows.insert(0, header);
        System.out.println(rows);

    }

}
