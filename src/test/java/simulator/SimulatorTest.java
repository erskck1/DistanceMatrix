package simulator;

import dictionary.Word;
import org.junit.Test;
import util.Helper;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SimulatorTest {

    private Simulator simulator = new Simulator();

    @Test
    public void isNumberOfWordsCorrect() {
        List<Word> words1 = simulator.createUniqueWordsBy(1, 1, 1);
        List<Word> words2 = simulator.createUniqueWordsBy(4, 10, 10);
        List<Word> words3 = simulator.createUniqueWordsBy(10, 10, 1);
        assertEquals(1, words1.size());
        assertEquals(4, words2.size());
        assertEquals(10, words3.size());
    }

    @Test
    public void areWordsUnique() {
        List<Word> words = simulator.createUniqueWordsBy(820, 6, 2);
        assertTrue(words.size() == 820);
        for (Word word1 : words) {
            for (Word word2 : words) {
                if (word1 == word2) {
                    continue;
                }
                assertTrue(Helper.calculateDistanceBetween(word1.toString(), word2.toString()) >= 2);
            }
        }
    }
}
