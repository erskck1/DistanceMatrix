package dictionary;

import java.util.ArrayList;
import java.util.List;

public class Word {

    private List<Alphabet> letters;

    public Word() {
        this.letters = new ArrayList<Alphabet>();
    }

    public void append(Alphabet letter) {
        this.letters.add(letter);
    }

    public int length() {
        return letters.size();
    }

    public void concat(Word word) {
        for (Alphabet a : word.letters) {
            this.append(a);
        }
    }

    @Override
    public String toString() {
        StringBuilder word = new StringBuilder();
        for (Alphabet letter : letters) {
            word.append(letter.name());
        }
        return word.toString();
    }
}
