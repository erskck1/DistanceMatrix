package util;

import org.apache.commons.lang3.StringUtils;

import java.util.Random;

public class Helper {

    //generate random values from 0 - (upperbound - 1)
    public static int randomInt(int upperbound) {
        Random rand = new Random();
        return rand.nextInt(upperbound);
    }

    // to generate unique words, calculate minumum stepsize
    public static int calculatStepSize(int distanceBetweenWords, int letterCount) {
        String distanceString = StringUtils.leftPad("", distanceBetweenWords, "1");
        return Integer.parseInt(distanceString, letterCount);
    }

    // Number of unique words of wordLength that can be generated with the alphabet
    public static int calculateNumberOfWordCombinationsBy(int wordLength, int letterCount) {
        return (int) Math.pow(letterCount, wordLength);
    }

    public static int calculateNumberOfWordCombinationsBy(int wordLength, int letterCount, int distance) {
        float maxWordCount = Helper.calculateNumberOfWordCombinationsBy(wordLength, letterCount);
        float stepSize = Helper.calculatStepSize(distance, letterCount);
        return (int) Math.ceil(maxWordCount / stepSize);
    }

    public static int calculateDistanceBetween(String w1, String w2) {
        if (StringUtils.isEmpty(w1)) {
            return w2.length();
        }

        if (StringUtils.isEmpty(w2)) {
            return w2.length();
        }

        int distance = calculateDistanceBetween(w1.substring(1), w2.substring(1)) + costOfSubstitution(w1.charAt(0), w2.charAt(0));
        return distance;
    }

    public static int costOfSubstitution(char a, char b) {
        return a == b ? 0 : 1;
    }
}
