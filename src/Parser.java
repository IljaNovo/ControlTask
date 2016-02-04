import org.apache.commons.cli.*;

public class Parser {

    public Parser() { }

    private static Option createOption(String opt, String description) {
        Option newOption = new Option(opt, true, description);
        newOption.setOptionalArg(false);

        return newOption;
    }

    public static Input parseParams(String[] commandLineArguments) throws ParseException {
        if (commandLineArguments == null) {
            throw new ParseException("invalid input data of command line");
        }
        Options posixOptions = new Options();

        posixOptions.addOption(createOption("r", "Rows"));
        posixOptions.addOption(createOption("c", "Columns"));
        posixOptions.addOption(createOption("p", "Probability"));

        CommandLineParser cmdLinePosixParser = new PosixParser();
        CommandLine commandLine = null;

        commandLine = cmdLinePosixParser.parse(posixOptions, commandLineArguments);

        commandLine.getOptionValue("r");
        commandLine.getOptionValue("c");
        commandLine.getOptionValue("p");

        return new Input(Integer.valueOf(commandLine.getOptionValue("r")),
                Integer.valueOf(commandLine.getOptionValue("c")),
                Double.valueOf(commandLine.getOptionValue("p")));
    }
}