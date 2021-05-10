package dictionary;

public enum Alphabet {

    A(0), T(1), G(2), C(3);

    // value in base 4
    private final int value;

    Alphabet(int value) {
        this.value = value;
    }

    public static int count() {
        return Alphabet.values().length;
    }
}
