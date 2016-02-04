public class Input {

    private int countRows;
    private int countColumn;
    private double fillFactor;

    public Input(int countRows, int countColumn, double fillFactor) {
        if (countRows < 0 || countColumn < 0
                || fillFactor < 0 || fillFactor > 1.0
                || countRows > Integer.MAX_VALUE - 1
                || countColumn > Integer.MAX_VALUE - 1)
        {
            throw new IllegalArgumentException();
        }
        this.countRows = countRows;
        this.countColumn = countColumn;
        this.fillFactor = fillFactor;
    }

    public double getFillFactor() {
        return fillFactor;
    }

    public int getCountRows() {
        return countRows;
    }

    public int getCountColumn() {
        return countColumn;
    }
}
