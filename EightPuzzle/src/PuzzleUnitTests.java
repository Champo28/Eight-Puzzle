import org.junit.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import static org.junit.Assert.*;

public class PuzzleUnitTests {
    @Test
    public void testConstructor(){
        Board b = new Board("023145678");
        StringWriter writer = new StringWriter();
        PrintWriter pw = new PrintWriter(writer);
        pw.println(" 23");
        pw.println("145");
        pw.println("678");
        assertEquals(b.toString(), writer.toString());
        pw.close();
    }

    @Test
    public void testConstructor2(){
        Board b = new Board("123485670");
        StringWriter writer = new StringWriter();
        PrintWriter pw = new PrintWriter(writer);
        pw.println("123");
        pw.println("485");
        pw.println("67 ");
        assertEquals(b.toString(), writer.toString());
        pw.close();
    }

    @Test
    public void testConstructor3() {
        Board b = new Board(new int[][]{{0, 2, 3}, {1, 4, 5}, {6, 7, 8}});
        StringWriter writer = new StringWriter();
        PrintWriter pw = new PrintWriter(writer);
        pw.println(" 23");
        pw.println("145");
        pw.println("678");
        assertEquals(b.toString(), writer.toString());
        pw.close();
    }

    @Test
    public void testEquals(){
        Board b = new Board(new int[][]{{0, 2, 3}, {1, 4, 5}, {6, 7, 8}});
        Board b2 = new Board("023145678");
        assertEquals(b, b2);
    }

    @Test
    public void exchangeBlankSpace(){
        Board b = new Board("123405678");
        b.exchangeBlankSpace(0,1);
        assertEquals(b, new Board("103425678"));
        b.exchangeBlankSpace(0,2);
        assertEquals(b, new Board("130425678"));
        b.exchangeBlankSpace(2,2);
        assertEquals(b, new Board("138425670"));
    }

    @Test
    public void testIsGoal(){
        //In fact is a test of the equals method
        Ilayout l = new Board("023145678");
        assertTrue(l.isGoal(new Board("023145678")));
        assertFalse(l.isGoal(new Board("123485670")));
    }

    @Test
    public void testChildren1(){
        Board b = new Board("023145678");
        List<Ilayout> children = b.children();
        assertEquals(2, children.size());
        assertEquals(children.get(0), new Board("123045678"));
        assertEquals(children.get(1), new Board("203145678"));
    }

    @Test
    public void testChildren2(){
        Board b = new Board("123456708");
        List<Ilayout> children = b.children();
        assertEquals(3, children.size());
        assertEquals(children.get(0), new Board("123406758"));
        assertEquals(children.get(1), new Board("123456078"));
        assertEquals(children.get(2), new Board("123456780"));
    }

    @Test
    public void testChildren3(){
        Board b = new Board("123405678");
        List<Ilayout> children= b.children();
        assertEquals(4, children.size());
        assertEquals(children.get(0), new Board("103425678"));
        assertEquals(children.get(1), new Board("123475608"));
        assertEquals(children.get(2), new Board("123045678"));
        assertEquals(children.get(3), new Board("123450678"));
    }
}
