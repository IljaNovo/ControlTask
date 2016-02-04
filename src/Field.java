public class Field implements Cloneable {

    private CellStateSector[][] sector;

    public Field(int rows, int columns) {
        if (rows < 1 || columns < 1) {
            throw new IllegalArgumentException();
        }
        sector = new CellStateSector[rows][columns];
    }

    public Field(CellStateSector[][] sector) {
        if (sector == null) {
            throw new IllegalArgumentException();
        }
        this.sector = new CellStateSector[sector.length][sector[0].length];

        for (int i = 0; i < sector.length; ++i) {
            this.sector[i] = sector[i].clone();
        }
    }

    public int getRows() {
        return sector[0].length;
    }

    public int getColumn() {
        return sector.length;
    }

    public CellStateSector getCell(int row, int column) {
        if (row < 0 || row >= sector[0].length
                || column < 0 || column > sector.length)
        {
            throw new IndexOutOfBoundsException();
        }
        return sector[row][column];
    }

    public void setCell(int row, int column, CellStateSector state) {
        if (row < 0 || row >= sector[0].length
                || column < 0 || column > sector.length)
        {
            throw new IndexOutOfBoundsException();
        }
        sector[row][column] = state;
    }

    public Field clone() throws CloneNotSupportedException{
        CellStateSector[][] cloneSector = new CellStateSector[sector.length][sector[0].length];

        for (int i = 0; i < sector.length; ++i) {
            cloneSector[i] = sector[i].clone();
        }
        Field clone = new Field(cloneSector);

        return clone;
    }
}