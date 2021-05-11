package main;

import dictionary.Word;
import exception.CommandFailureException;
import simulator.Commands;
import simulator.Simulator;
import util.CommandsUtil;
import validator.NeedHelpValidator;

import java.util.List;
import java.util.Map;

public class SimulatorLauncher {

    public static void main(String[] args) {

        // Check if the parameters are correct by using the Chain of Responsibility design pattern
        try {
            new NeedHelpValidator(args).startChain();
        } catch (CommandFailureException ce) {
            return;
        }

        // parameters are validated, now we can use them
        Map<String, String> parametersAsMap = CommandsUtil.getParametersAsMap(args);

        int wordCount = Integer.valueOf(parametersAsMap.get(Commands.WORD_COUNT.getParameter()));
        int wordLength = Integer.valueOf(parametersAsMap.get(Commands.WORD_LENGTH.getParameter()));
        int distance = Integer.valueOf(parametersAsMap.get(Commands.DISTANCE.getParameter()));

        Simulator s = new Simulator();
        List<Word> words = s.createUniqueWordsBy(wordCount, wordLength, distance);  // generate words
        s.writeDistanceMatrixIntoFile(words);                       // print distance matrix
    }
}

