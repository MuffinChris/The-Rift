package net.therift.util;

public class ArgParser {

    /**
     * Return string command arguments separated by spaces with a leading space
     * @param args the array to parse
     * @return a string containing all element spaced out
     */
    public static String getArgs(String[] args) {
        if (args.length == 0) {
            return "";
        }
        StringBuilder argString = new StringBuilder();
        argString.append(" ");
        for (String s : args) {
            argString.append(s);
            argString.append(" ");
        }
        return argString.substring(0, argString.length() - 1);
    }

}
