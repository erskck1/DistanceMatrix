package handler;

import dictionary.Alphabet;
import util.Commands;
import util.CommandsUtil;
import util.Helper;

import java.util.Map;

public class ParameterValidationHandler extends CommandHandler {

    private boolean success;

    public ParameterValidationHandler(String[] args) {
        super(args);
    }

    @Override
    protected boolean isSuccess() {
        return success;
    }

    @Override
    protected void execute() {
        Map<String, String> parametersAsMap = CommandsUtil.getParametersAsMap(args);

        int w = Integer.valueOf(parametersAsMap.get(Commands.W.getParameter()));
        int l = Integer.valueOf(parametersAsMap.get(Commands.L.getParameter()));
        int d = Integer.valueOf(parametersAsMap.get(Commands.D.getParameter()));

        if (w <= 0 || l <= 0 || d <= 0) {
            success = false;
            return;
        }

        if (d > l) {
            success = false;
            return;
        }

        int numberOfWordsWithDistance = Helper.calculateNumberOfWordCombinationsBy(l, Alphabet.count(), d);
        if (w > numberOfWordsWithDistance) {
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
    protected CommandHandler getNext() {
        return null;
    }
}
