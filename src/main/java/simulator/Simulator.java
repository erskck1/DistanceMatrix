package simulator;

import builder.WordBuilder;
import dictionary.Alphabet;
import dictionary.Word;
import org.apache.commons.lang3.StringUtils;
import util.Helper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Simulator {

    /* Algorithm for generating unique words :
    * Number of letters in the alphabet = N
    * Number of words of length "X" = N^X
    * Step size "S" = N^0 + .... + N^(D-1)
    * Number of words of length "X" with a distance "D" between = Math.ceil((N^X)/S)
    * Assume, our alphabet has four letters and we want to generate unique words of length 5 with a distance at least 3:
    * For a alphabet which have four letters : A, T, G, C
    * Number of words of length "5" : 4^5 = 1024
    * Step Size S : 4^0 + 4^1 + 4^2 = 21
    * Number of words of length "X" with a distance "D" between : Math.ceil(1024/21) = 49
    * My algorithm can generate 49 unique words of length 5 with at least 3 distances.
    * The word is considered as a number in base "4"
    * A refers to 0, T -> 1, G -> 2 and C -> 3
    * A word "00123" refers to AATGC. To find unique words at least with the distance "3":
    * We have to add "00111" to the "00123" for the next unique word :"00123" + "00111" = "00300" -> "AACAA"
    * If we continue to add "00111" : "00300" + "01011" -> "ATATT", we can generate words with at least 3 distances between them.
    * */
    public List<Word> createUniqueWordsBy(int numberOfWords, int wordLength, int distanceBetweenWords) {
        List<Word> words = new ArrayList<Word>();

        int letterCount = Alphabet.count();     // base
        int maxWordCount = Helper.calculateNumberOfWordCombinationsBy(wordLength, letterCount); // max word combination of wordLength
        int maxWordCountWithDistance = Helper.calculateNumberOfWordCombinationsBy(wordLength, letterCount, distanceBetweenWords);  // max word combination of wordLength with at least distanceBetweenWords
        int stepSize = Helper.calculatStepSize(distanceBetweenWords, letterCount); // step size for generating unique words
        int equivalentWordInBase10 = Helper.randomInt(maxWordCountWithDistance) * stepSize; // start index

        for (int i = 0; i < numberOfWords; i++) {
            words.add(WordBuilder.generateBy(equivalentWordInBase10, wordLength));
            equivalentWordInBase10 = (equivalentWordInBase10 + stepSize);    // next unique word
            if (equivalentWordInBase10 > maxWordCount) {    // shift back the index
                equivalentWordInBase10 = 0;
            }
        }

        return words;
    }

    public void writeDistanceMatrixIntoFile(List<Word> words) {
        StringBuilder header = new StringBuilder();
        StringBuilder rows = new StringBuilder();
        String delimiter = "\t";

        for (Word word1 : words) {
            StringJoiner distances = new StringJoiner(delimiter);
            for (Word word2 : words) {
                String distance = String.valueOf(Helper.calculateDistanceBetween(word1.toString(), word2.toString()));
                if (word1 == word2) {
                    distance = "-";
                }
                distances.add(StringUtils.center(distance, words.get(0).length(), " "));
            }

            header.append(word1.toString());
            header.append(delimiter);

            rows.append(word1.toString());
            rows.append(delimiter);
            rows.append(distances.toString().trim() + "\n");
        }

        String headerEmptyString = StringUtils.center("", words.get(0).length(), " ") + delimiter;
        rows.insert(0, headerEmptyString + header.toString().trim() + "\n");
        writeToFile(rows);

    }

    private void writeToFile(StringBuilder rows) {
        try {
            String fileName = "distance_matrix.tsv";
            FileWriter fileWriter = new FileWriter(fileName);
            File file = new File(fileName);
            fileWriter.write(rows.toString());
            fileWriter.close();
            System.out.println("Distance matrix can be found under the following path : " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

}
