package handler;

import util.Commands;
import util.CommandsUtil;

public class HelperCommandHandler extends CommandHandler {

    private boolean isNeedHelp;

    public HelperCommandHandler(String[] args) {
        super(args);
    }

    @Override
    protected boolean isSuccess() {
        return !isNeedHelp;
    }

    @Override
    protected void execute() {
        isNeedHelp = CommandsUtil.needHelp(args);
    }

    @Override
    protected void printError() {
        System.out.println("HELP");
        System.out.println("Usage of the program");
        System.out.println("-w     		 : " + Commands.W.getDescription());
        System.out.println("-l    		 : " + Commands.L.getDescription());
        System.out.println("-d           : " + Commands.D.getDescription());
        System.out.println("-h           : " + Commands.HELP.getDescription());
    }

    @Override
    protected CommandHandler getNext() {
        return new WrongCommandHandler(args);
    }

}
