package util;

import simulator.Commands;

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
        joiner.add(Commands.WORD_COUNT.getRegex());
        joiner.add(Commands.WORD_LENGTH.getRegex());
        joiner.add(Commands.DISTANCE.getRegex());
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
        Map<String, String> parametersMap = new HashMap<>();
        List<String> parametersList = arrayToArrayList(args);

        parametersList.stream().filter(s -> s.matches(Commands.WORD_COUNT.getRegex())).forEach(s -> {
            int index = parametersList.indexOf(s);
            parametersMap.put(Commands.WORD_COUNT.getParameter(), parametersList.get(index + 1));
        });

        parametersList.stream().filter(s -> s.matches(Commands.WORD_LENGTH.getRegex())).forEach(s -> {
            int index = parametersList.indexOf(s);
            parametersMap.put(Commands.WORD_LENGTH.getParameter(), parametersList.get(index + 1));
        });

        parametersList.stream().filter(s -> s.matches(Commands.DISTANCE.getRegex())).forEach(s -> {
            int index = parametersList.indexOf(s);
            parametersMap.put(Commands.DISTANCE.getParameter(), parametersList.get(index + 1));
        });

        return parametersMap;
    }
}
