package PESELpackage;

import PESELpackage.PESELclass;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class PESELclassTest {

    PESELclass peselOperator;
    final static Logger logger = Logger.getLogger(PESELclass.class);

    @Before
    public void setUp() {
        logger.info("Running the test code");
        peselOperator = new PESELclass();
    }

    @Test
    public void checksumTest() {
        //correct checksum pesel
        assertTrue(peselOperator.peselCheck("97061000005"));
        assertTrue(peselOperator.peselCheck("52090557562"));
        //incorrect checksum pesel
        assertTrue(!peselOperator.peselCheck("97061000009"));
        assertTrue(!peselOperator.peselCheck("52090557569"));
    }

    @Test
    public void numberTest() {
        //incorrect NaN pesel
        assertTrue(!peselOperator.peselCheck("970610A0005"));
        assertTrue(!peselOperator.peselCheck("52090A57562"));
    }

    @Test
    public void lenghtTest() {
        //incorrect NaN pesel
        assertTrue(!peselOperator.peselCheck("1234567890"));
        assertTrue(!peselOperator.peselCheck("123456789012"));
    }
}