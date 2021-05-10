package handler;

import util.Commands;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommandRuleHandler extends CommandHandler {

    private boolean success;

    public CommandRuleHandler(String[] args) {
        super(args);
    }

    @Override
    protected boolean isSuccess() {
        return success;
    }

    @Override
    protected void execute() {
        List<String> commands = Arrays.asList(args);

        String parameters = commands.stream().collect(Collectors.joining(" "));

        boolean isWordCountExist = parameters.matches(Commands.W.getRegexWithParameter());
        boolean isWordLengthExist = parameters.matches(Commands.L.getRegexWithParameter());
        boolean isDistanceExist = parameters.matches(Commands.D.getRegexWithParameter());

        if (!isWordCountExist || !isWordLengthExist || !isDistanceExist) {
            success = false;
            return;
        }

        success = true;
    }

    @Override
    protected void printError() {
        System.out.println("Missing mandatory parameter(s)!");
    }

    @Override
    protected CommandHandler getNext() {
        return new ParameterValidationHandler(args);
    }

}
