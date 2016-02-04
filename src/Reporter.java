import java.util.List;
import java.util.Map;

public class Reporter {

    public static String generate(Field sector, Map<String, Integer> riskGroups,
                                  double fillFactor, List<RiskGroup> stateRiskGroups) {
        StringBuilder answer = new StringBuilder();

        answer.append(printSizeField(sector));
        answer.append(printFillFactor(fillFactor));
        answer.append(printStateRiskGroup(stateRiskGroups));
        answer.append(printFieldAndRiskGroups(sector, riskGroups));

        return answer.toString();
    }

    private static String printSizeField(Field sector) {
        StringBuilder answer = new StringBuilder();

        answer.append(generateSizeField(sector));
        answer.append(generateLine());
        answer.append(generateLine());
        answer.append(generateLineBreak());

        return answer.toString();
    }

    private static String printFillFactor(double fillFactor) {
        StringBuilder answer = new StringBuilder();

        answer.append(generateFillFactor(fillFactor));
        answer.append(generateLine());
        answer.append(generateLine());
        answer.append(generateLineBreak());

        return answer.toString();
    }

    private static String printStateRiskGroup(List<RiskGroup> stateRiskGroups) {
        StringBuilder answer = new StringBuilder();

        answer.append(generateStateRiskGroup(stateRiskGroups));
        answer.append(generateLine());
        answer.append(generateLine());
        answer.append(generateLineBreak());

        return answer.toString();
    }

    private static String printFieldAndRiskGroups(Field sector, Map<String, Integer> riskGroups) {
        StringBuilder answer = new StringBuilder();

        answer.append(generateField(sector));
        answer.append(generateLineBreak());
        answer.append(generateListFindGroup(riskGroups));

        return answer.toString();
    }

    private static String generateLineBreak() {
        return "\n";
    }

    private static String generateStateRiskGroup(List<RiskGroup> stateRiskGroups) {
        StringBuilder answer = new StringBuilder();

        answer.append("Risk groups.\n\n");

        for (RiskGroup state : stateRiskGroups) {
            answer.append("Name: ");
            answer.append(state.getName());
            answer.append(", ");
            answer.append("max count: ");
            answer.append(state.getMax());
            answer.append(" person(s)\n");
        }
        return answer.toString();
    }

    private static String generateSizeField(Field sector) {
        return String.format("Field: \"rows: %d, columns: %d\"\n", sector.getRows(), sector.getColumn());
    }

    private static String generateFillFactor(double fillFactor) {
        return String.format("FILL_FACTOR = %.2f\n", fillFactor);
    }

    private static String generateHeader() {
        return "EMERGENCY PREVENTION SYSTEM\n";
    }

    private static String generateField(Field sector) {
        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < sector.getColumn(); ++i) {
            answer.append("   ");
            answer.append(i);
        }
        answer.append("\n");

        for (int i = 0; i < sector.getRows(); ++i) {
            answer.append(i);
            answer.append(" ");

            for (int j = 0; j < sector.getColumn(); ++j) {
                answer.append(" ");

                if (sector.getCell(i, j).equals(CellStateSector.PEOPLE)) {
                    answer.append("|x|");
                }
                else {
                    answer.append("|-|");
                }
            }
            answer.append("\n");
        }
        return answer.toString();
    }

    private static String generateListFindGroup(Map<String, Integer> riskGroup) {
        StringBuilder answer = new StringBuilder();

        answer.append("Risk group report:\n");

        answer.append(generateLine());

        for (Map.Entry<String, Integer> group : riskGroup.entrySet()) {
            answer.append(group.getKey());
            answer.append(": ");
            answer.append(group.getValue());
            answer.append(" groups;");
            answer.append("\n");
        }
        return answer.toString();
    }

    private static String generateLine() {
        return "-----------------------------------------------------------------------\n";
    }
}
