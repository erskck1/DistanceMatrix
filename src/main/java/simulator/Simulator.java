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
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

public class Simulator {

    public List<Word> createUniqueWordsBy(int numberOfWords, int wordLength, int distanceBetweenWords) {
        List<Word> words = new ArrayList<>();
        WordBuilder.createAllCombinations(wordLength, new Word(), Alphabet.values(), words);
        List<Word> uniqueWordsWithDistance = new ArrayList<>();

        while (uniqueWordsWithDistance.size() < numberOfWords) {
            Collections.shuffle(words);
            for (Word word : words) {
                boolean isDistanceValid = true;
                for(Word wordWithDistance : uniqueWordsWithDistance) {
                    if (Helper.calculateDistanceBetween(word.toString(), wordWithDistance.toString()) < distanceBetweenWords) {
                        isDistanceValid = false;
                        break;
                    }
                }
                if(isDistanceValid) {
                    uniqueWordsWithDistance.add(word);
                }
            }
            if(uniqueWordsWithDistance.size() < numberOfWords) {
                uniqueWordsWithDistance.clear();
            }
        }

        return uniqueWordsWithDistance.subList(0,numberOfWords);
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
