package validator;

import util.CommandsUtil;

import java.util.List;
import java.util.stream.Collectors;

public class ParameterPatternValidator extends Validator {

    private List<String> wrongCommands;

    public ParameterPatternValidator(String[] args) {
        super(args);
    }

    @Override
    protected boolean isSuccess() {
        return CommandsUtil.isEmpty(wrongCommands);
    }

    @Override
    protected void execute() {
        wrongCommands = CommandsUtil.findWrongCommands(args);
    }

    @Override
    protected void printError() {
        String wrongCommandsAsString = wrongCommands.stream().collect(Collectors.joining(", "));
        System.out.println(String.format("This command(s) are not found : %s", wrongCommandsAsString));
    }

    @Override
    protected Validator getNext() {
        return new DuplicateParameterValidator(args);
    }

}
