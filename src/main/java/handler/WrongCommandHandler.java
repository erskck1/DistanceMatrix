package handler;

import util.CommandsUtil;

import java.util.List;
import java.util.stream.Collectors;

public class WrongCommandHandler extends CommandHandler {

    private List<String> wrongCommands;

    public WrongCommandHandler(String[] args) {
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
    protected CommandHandler getNext() {
        return new DoubleCommandHandler(args);
    }

}
