package StreamUsage;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import static org.junit.Assert.assertTrue;

public class StreamClassTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;




    StreamClass streamnator;
    final static Logger logger = Logger.getLogger(StreamClass.class);

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        logger.info("Running the test code");
        streamnator = new StreamClass();
    }

    @Test
    public void orderPrintTest() {
        List<String> myList = Arrays.asList("b1", "c2", "a3", "c1", "a1");
        streamnator.orderPrint(myList);
        Assert.assertEquals("a1\na3\nb1\nc1\nc2\n", outContent.toString());
        //System.out.println("a1\na3\nb1\nc1\nc2\n");

    }

    @Test
    public void createListTest() {
        Stack<Long> PESELstack = new Stack();
        List<Long> inputList = Arrays.asList(97061000005L, 12300000000L, 52090557562L, 76090453562L, 56090233562L);
        List<Long> expectedOutputList = Arrays.asList(52090557562L, 56090233562L);
        PESELstack.addAll(inputList);
        Assert.assertEquals(streamnator.createList(PESELstack, 5),expectedOutputList);
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
}
