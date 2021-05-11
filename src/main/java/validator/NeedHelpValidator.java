package validator;

import util.Commands;
import util.CommandsUtil;

public class NeedHelpValidator extends Validator {

    private boolean isNeedHelp;

    public NeedHelpValidator(String[] args) {
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
        System.out.println("-w     		 : " + Commands.WORD_COUNT.getDescription());
        System.out.println("-l    		 : " + Commands.WORD_LENGTH.getDescription());
        System.out.println("-d           : " + Commands.DISTANCE.getDescription());
        System.out.println("-h           : " + Commands.HELP.getDescription());
    }

    @Override
    protected Validator getNext() {
        return new ParameterPatternValidator(args);
    }

}
