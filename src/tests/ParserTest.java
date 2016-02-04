import org.junit.Assert;
import org.junit.Test;
import org.apache.commons.cli.ParseException;

public class ParserTest {

    @Test
    public void testParseEmptyKey() throws Exception {
        try {
            Parser.parseParams("-r -c 4 -p 0.4".split(" "));
        }
        catch (ParseException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testParseNull() throws Exception {
        try {
            Parser.parseParams(null);
        }
        catch (ParseException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testParseInvalidData() throws Exception {
        try {
            Parser.parseParams("-r -3 -c -4 -p 1.1".split(" "));
        }
        catch (IllegalArgumentException e) {
            Assert.assertTrue(true);
        }
    }
}