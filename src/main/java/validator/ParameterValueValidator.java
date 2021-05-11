package validator;

import dictionary.Alphabet;
import util.Commands;
import util.CommandsUtil;
import util.Helper;

import java.util.Map;

public class ParameterValueValidator extends Validator {

    private boolean success;

    public ParameterValueValidator(String[] args) {
        super(args);
    }

    @Override
    protected boolean isSuccess() {
        return success;
    }

    @Override
    protected void execute() {
        Map<String, String> parametersAsMap = CommandsUtil.getParametersAsMap(args);

        int wordCount = Integer.valueOf(parametersAsMap.get(Commands.WORD_COUNT.getParameter()));
        int wordLength = Integer.valueOf(parametersAsMap.get(Commands.WORD_LENGTH.getParameter()));
        int distance = Integer.valueOf(parametersAsMap.get(Commands.DISTANCE.getParameter()));

        if (wordCount <= 0 || wordLength <= 0 || distance <= 0) {
            success = false;
            return;
        }

        if (distance > wordLength) {
            success = false;
            return;
        }

        int numberOfWordsWithDistance = Helper.calculateNumberOfWordCombinationsBy(wordLength, Alphabet.count(), distance);
        if (wordCount > numberOfWordsWithDistance) {
            success = false;
            return;
        }

        success = true;
    }

    @Override
    protected void printError() {
        System.out.println("Given parameters are not suitable for generate unique words!");
    }

    @Override
    protected Validator getNext() {
        return null;
    }
}
