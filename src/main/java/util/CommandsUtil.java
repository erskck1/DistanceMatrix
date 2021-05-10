package util;

import java.util.*;
import java.util.stream.Collectors;

public class CommandsUtil {

    public static boolean needHelp(String[] args) {
        return Arrays.stream(args).anyMatch(s -> s.equals(Commands.HELP.getParameter()));
    }

    public static List<String> findWrongCommands(String[] args) {
        List<String> notCommands = Arrays.stream(args)
                .filter(s -> s.matches(".*-([a-z]|[A-Z])+.*"))
                .filter(s -> !s.matches(getAllRegexWithOr()))
                .collect(Collectors.toList());
        return notCommands;
    }

    public static List<String> findCommands(String[] args) {
        List<String> commands = Arrays.stream(args)
                .filter(s -> s.matches(".*-([a-z]|[A-Z])+.*"))
                .filter(s -> s.matches(getAllRegexWithOr()))
                .collect(Collectors.toList());
        return commands;
    }

    public static boolean isEmpty(List<String> list) {
        return list == null || list.size() == 0;
    }

    public static String getAllRegexWithOr() {
        StringJoiner joiner = new StringJoiner("|");
        joiner.add(Commands.W.getRegex());
        joiner.add(Commands.L.getRegex());
        joiner.add(Commands.D.getRegex());
        return String.format("(%s)", joiner.toString());
    }

    public static List<String> findDoubleCommands(List<String> commands) {
        Set<String> doubleCommands = new HashSet<String>();
        for (String command : commands) {
            long count = commands.stream().filter(s -> s.equals(command)).count();
            if (count >= 2) {
                doubleCommands.add(command);
            }
        }
        return new ArrayList(doubleCommands);
    }

    public static List<String> arrayToArrayList(String[] array) {
        return Arrays.asList(array);
    }

    public static Map<String, String> getParametersAsMap(String[] args) {
        Map<String, String> parametersMap = new HashMap<String, String>();
        List<String> parametersList = arrayToArrayList(args);

        parametersList.stream().filter(s -> s.matches(Commands.W.getRegex())).forEach(s -> {
            int index = parametersList.indexOf(s);
            parametersMap.put(Commands.W.getParameter(), parametersList.get(index + 1));
        });

        parametersList.stream().filter(s -> s.matches(Commands.L.getRegex())).forEach(s -> {
            int index = parametersList.indexOf(s);
            parametersMap.put(Commands.L.getParameter(), parametersList.get(index + 1));
        });

        parametersList.stream().filter(s -> s.matches(Commands.D.getRegex())).forEach(s -> {
            int index = parametersList.indexOf(s);
            parametersMap.put(Commands.D.getParameter(), parametersList.get(index + 1));
        });

        return parametersMap;
    }
}
