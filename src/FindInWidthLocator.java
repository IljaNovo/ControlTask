import java.util.*;

public class FindInWidthLocator implements Locator {

    List<RiskGroup> stateRiskGroup;

    public List<RiskGroup> getStateRiskGroup() {
        return new ArrayList<>(stateRiskGroup);
    }

    public FindInWidthLocator(List<RiskGroup> stateRiskGroup) {
        this.stateRiskGroup = new LinkedList<>(stateRiskGroup);
    }

    private class CellIndices {
        private int row;
        private int column;

        public CellIndices(int row, int column) {
            this.row = row;
            this.column = column;
        }

        public int getRow() {
            return row;
        }

        public int getColumn() {
            return column;
        }
    }

    @Override
    public Map<String, Integer> findRiskGroups(Field sector) {
        boolean[][] flagOfCells = new boolean[sector.getRows()][sector.getColumn()];
        List<Integer> peopleGroups = new ArrayList<>();

        for (int i = 0; i < sector.getRows(); ++i) {
            for (int j = 0; j < sector.getColumn(); ++j) {
                if (sector.getCell(i, j).equals(CellStateSector.PEOPLE)
                        && flagOfCells[i][j] != true) {
                    peopleGroups.add(findCountPeople(flagOfCells, sector, new CellIndices(i, j)));
                }
            }
        }
        return splitIntoGroups(peopleGroups);
    }

    /*
    * in, out
    * */
    private int findCountPeople(boolean[][] flagOfCells, Field sector, CellIndices currentCell) {
        Queue<CellIndices> buffer = new LinkedList<>();
        buffer.offer(currentCell);
        int countFindPerson = 1;
        CellIndices tempCell;

        while (buffer.size() != 0) {
            tempCell = buffer.remove();
            flagOfCells[tempCell.getRow()][tempCell.getColumn()] = true;
            countFindPerson += findAllNeighbors(flagOfCells, buffer, sector, tempCell);
        }
        return countFindPerson;
    }

    /*
    * in, out
    * */
    private int findAllNeighbors(boolean[][] flagOfCells, Queue<CellIndices> buffer, Field sector, CellIndices cell) {
        int countFindPerson = 0;

        if (addCellInBuffer(flagOfCells, sector, buffer, cell.getRow() + 1, cell.getColumn())) {
            ++countFindPerson;
        }
        if (addCellInBuffer(flagOfCells, sector, buffer, cell.getRow() - 1, cell.getColumn())) {
            ++countFindPerson;
        }
        if (addCellInBuffer(flagOfCells, sector, buffer, cell.getRow(), cell.getColumn() + 1)) {
            ++countFindPerson;
        }
        if (addCellInBuffer(flagOfCells, sector, buffer, cell.getRow(), cell.getColumn() - 1)) {
            ++countFindPerson;
        }
        return  countFindPerson;
    }

    /*
    * in, out
    * */
    private boolean addCellInBuffer(boolean[][] flagOfCells, Field sector,
                                    Queue<CellIndices> buffer, int row, int column) {
        if (checkCellView(flagOfCells, sector, row, column)) {
            flagOfCells[row][column] = true;
            buffer.offer(new CellIndices(row, column));
            return true;
        }
        else  {
            return false;
        }
    }

    private boolean checkCellView(boolean[][] flagOfCells, Field sector , int row, int column) {
        if ((row >= 0 && row < sector.getRows()) &&
                (column >= 0 && column < sector.getColumn()) &&
                (flagOfCells[row][column] != true)) {
            if (sector.getCell(row, column).equals(CellStateSector.PEOPLE)) {
                return true;
            } else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    private Map<String, Integer> splitIntoGroups(List<Integer> peopleGroups) {
        Map<String, Integer> riskGroups = new HashMap<>();
        int countGroup = 0;

        for (RiskGroup group : stateRiskGroup) {
            for (Integer countPeople : peopleGroups) {
                if (group.checkEnteringInterval(countPeople)) {
                    ++countGroup;
                }
            }
            riskGroups.put(group.getName(), countGroup);
            countGroup = 0;
        }
        return riskGroups;
    }
}
