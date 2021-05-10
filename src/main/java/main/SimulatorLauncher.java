package main;

import dictionary.Word;
import exception.CommandFailureException;
import handler.HelperCommandHandler;
import simulator.Simulator;
import util.Commands;
import util.CommandsUtil;

import java.util.List;
import java.util.Map;

public class SimulatorLauncher {

    public static void main(String[] args) {

        // check if the commands are correct with Chain of Responsibility design pattern
        try {
            new HelperCommandHandler(args).startChain();
        } catch (CommandFailureException ce) {
            return;
        }

        Map<String, String> parametersAsMap = CommandsUtil.getParametersAsMap(args);

        int w = Integer.valueOf(parametersAsMap.get(Commands.W.getParameter()));
        int l = Integer.valueOf(parametersAsMap.get(Commands.L.getParameter()));
        int d = Integer.valueOf(parametersAsMap.get(Commands.D.getParameter()));

        Simulator s = new Simulator();
        List<Word> words = s.createUniqueWordsBy(w, l, d);  // generate words
        s.printDistanceMatrix(words);                       // print distance matrix
    }
}

