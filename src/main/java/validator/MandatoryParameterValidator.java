package validator;

import simulator.Commands;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MandatoryParameterValidator extends Validator {

    private boolean success;

    public MandatoryParameterValidator(String[] args) {
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

        boolean isWordCountExist = parameters.matches(Commands.WORD_COUNT.getRegexWithParameter());
        boolean isWordLengthExist = parameters.matches(Commands.WORD_LENGTH.getRegexWithParameter());
        boolean isDistanceExist = parameters.matches(Commands.DISTANCE.getRegexWithParameter());

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
    protected Validator getNext() {
        return new ParameterValueValidator(args);
    }

}
