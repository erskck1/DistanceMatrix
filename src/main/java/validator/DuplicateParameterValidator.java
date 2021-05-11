package validator;

import util.CommandsUtil;

import java.util.List;
import java.util.stream.Collectors;

public class DuplicateParameterValidator extends Validator {

    private List<String> doubleCommands;

    public DuplicateParameterValidator(String[] args) {
        super(args);
    }

    @Override
    protected boolean isSuccess() {
        return CommandsUtil.isEmpty(doubleCommands);
    }

    @Override
    protected void execute() {
        List<String> commands = CommandsUtil.findCommands(args);
        doubleCommands = CommandsUtil.findDoubleCommands(commands);
    }

    @Override
    protected void printError() {
        String doubleCommandsAsString = doubleCommands.stream().collect(Collectors.joining(", "));
        System.out.println(String.format("This command(s) exists more than one times: %s", doubleCommandsAsString));
    }

    @Override
    protected Validator getNext() {
        return new MandatoryParameterValidator(args);
    }

}
