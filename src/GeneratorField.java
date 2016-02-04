public class GeneratorField {

    /*
    * in, out
    * */
    public static void generate(Field sector, double fillFactor) {
        if (fillFactor < 0.0 || fillFactor > 1.0) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < sector.getRows(); ++i) {
            for (int j = 0; j < sector.getColumn(); ++j) {
                if (Math.random() <= fillFactor) {
                    sector.setCell(i, j, CellStateSector.PEOPLE);
                }
                else {
                    sector.setCell(i, j, CellStateSector.EMPTY);
                }
            }
        }
    }
}
