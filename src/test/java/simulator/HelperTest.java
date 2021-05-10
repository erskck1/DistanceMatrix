package simulator;

import org.junit.Test;
import util.Helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class HelperTest {

    @Test
    public void isRandomNumberLessThanUpperbound() {
        assertTrue(Helper.randomInt(1) == 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwExceptionIfUpperboundIsNotPositive() {
        Helper.randomInt(0);
    }

    @Test
    public void isMinimunStepSizeCorrectlyCalculated() {
        int stepSizeOfDistance3 = Helper.calculatStepSize(3, 5);
        int stepSizeOfDistance6 = Helper.calculatStepSize(6, 10);

        assertTrue(stepSizeOfDistance3 == 31);
        assertTrue(stepSizeOfDistance6 == 111111);
    }

    @Test
    public void isNumberOfWordCombinationsCorrectlyCalculated() {
        int numberOfWords = Helper.calculateNumberOfWordCombinationsBy(4, 5);
        assertTrue(numberOfWords == 625);
    }

    @Test
    public void isNumberOfWordCombinationCorrectlyCalculatedWithDistance() {
        int wordLength = 4;
        int letterCount = 5;
        int distance = 3;

        int result = Helper.calculateNumberOfWordCombinationsBy(wordLength, letterCount, distance);

        assertEquals(21, result);
    }
}