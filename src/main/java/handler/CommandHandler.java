package handler;

import exception.CommandFailureException;

import java.util.Arrays;
import java.util.stream.Collectors;

public abstract class CommandHandler {
    protected String[] args;

    public CommandHandler(String[] args) {
        this.args = args;
    }

    public void startChain() throws CommandFailureException {
        execute();
        if (isSuccess()) {
            if (getNext() != null) {
                getNext().startChain();
            }
        } else {
            printError();
            printAllParameters();
            throw new CommandFailureException();
        }
    }

    protected void printAllParameters() {
        String allParameters = Arrays.asList(args).stream().collect(Collectors.joining(" "));
        System.out.println(String.format("Entered parameters : %s \nPlease use for more help -h", allParameters));
    }

    protected abstract boolean isSuccess();

    protected abstract void execute();

    protected abstract void printError();

    protected abstract CommandHandler getNext();

}
