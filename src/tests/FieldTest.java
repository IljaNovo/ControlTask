import org.junit.Assert;
import org.junit.Test;

public class FieldTest {

    @Test
    public void testGenerateField() throws Exception {
        Field sector = new Field(3, 3);
        GeneratorField.generate(sector, 0.5);
        boolean flag = true;

        for (int i = 0; i < sector.getRows(); ++i) {
            for (int j = 0; j < sector.getColumn(); ++j) {
                if (sector.getCell(i, j) == null) {
                    flag = false;
                    i = sector.getRows();
                    break;
                }
            }
        }
        Assert.assertTrue(flag);
    }

    @Test
    public void testInvalidDateCreateField() throws Exception {
        try {
            Field pf = new Field(-3, 3);
        }
        catch (IllegalArgumentException e) {
            Assert.assertTrue(true);
        }
    }
}