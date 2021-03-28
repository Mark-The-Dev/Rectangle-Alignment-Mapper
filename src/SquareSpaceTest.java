import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SquareSpaceTest {

    private static SquareSpace squareSpace;

    @BeforeEach
    public void setUp() throws Exception {
        squareSpace = new SquareSpace(25,25);
    }

    @Test
    @DisplayName("Creating a new SquareSpace must be of at least 12 by 12 size and not negative")
    public void mapSizeIsValid(){
        // Throws exception if x axis is negative
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new SquareSpace(-1,14));
        assertEquals("This map must be at least 12 by 12", exception.getMessage());

        // Throws exception if y axis is negative
        exception = assertThrows(IllegalArgumentException.class, () -> new SquareSpace(14,-1));
        assertEquals("This map must be at least 12 by 12", exception.getMessage());

        // Throws exception if x axis is under 12 and positive
        exception = assertThrows(IllegalArgumentException.class, () -> new SquareSpace(10,14));
        assertEquals("This map must be at least 12 by 12", exception.getMessage());

        // Throws exception if y axis is under 12 and positive
        exception = assertThrows(IllegalArgumentException.class, () -> new SquareSpace(14,10));
        assertEquals("This map must be at least 12 by 12", exception.getMessage());
    }

    @Test
    @DisplayName("Adding 2 Rectangles of safe size works")
    public void testAddRectangles(){
        Rectangle rec1 = new Rectangle(4,4,0,0);
        Rectangle rec2 = new Rectangle(4,4,4,0);

        squareSpace.addRectangles(rec1,rec2);

        int rec1StartX = squareSpace.getRectangles().get(0).getStartX();
        int rec2StartX = squareSpace.getRectangles().get(1).getStartX();

        assertEquals(0, rec1StartX, "Start X of rec 1 should equal 0");
        assertEquals(4, rec2StartX, "Start X of rec 2 should equal 4");

    }

    @Test
    @DisplayName("Adding rectangles that are outside of the maps bounds will throw an exception")
    public void testAddRectanglesException(){
        Rectangle rec1 = new Rectangle(4,4,25,0);
        Rectangle rec2 = new Rectangle(4,4,0,25);


        Throwable exception = assertThrows(IllegalArgumentException.class, () -> squareSpace.addRectangles(rec1,rec2));
        assertEquals("Rectangles must fit within the map!", exception.getMessage());

    }

    @Test
    @DisplayName("Adding a third rectangles should throw an exception")
    public void testAdding3Rectangles(){
        Rectangle rec1 = new Rectangle(4,4,0,0);
        Rectangle rec2 = new Rectangle(4,4,4,0);
        Rectangle rec3 = new Rectangle(4,4,0,0);
        Rectangle rec4 = new Rectangle(4,4,4,0);

        squareSpace.addRectangles(rec1,rec2);

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> squareSpace.addRectangles(rec3,rec4));
        assertEquals("This map already has 2 rectangles!", exception.getMessage());

    }


    @Test
    @DisplayName("Registers correct Intersection for added rectangles")
    static void checkForIntersection() {
        Rectangle rec1 = new Rectangle(4,4,0,0);
        Rectangle rec2 = new Rectangle(4,4,3,0);
        Rectangle rec3 = new Rectangle(4,4,5,0);

        squareSpace.addRectangles(rec1,rec2);

        assertEquals(true, squareSpace.isIntersection());


        // tests rectangles flipped
        squareSpace = new SquareSpace(25,25);

        squareSpace.addRectangles(rec2, rec1);

        assertEquals(true, squareSpace.isIntersection());


        // tests for false
        SquareSpace squareSpace2 = new SquareSpace(25,25);


        squareSpace2.addRectangles(rec1,rec3);

        assertEquals(false, squareSpace2.isIntersection());



    }

    @Test
    @DisplayName("Registers correct containment for added rectangles")
    static void checkForContainment() {

        Rectangle rec1 = new Rectangle(10,10,0,0);
        Rectangle rec2 = new Rectangle(3,3,2,2);

        squareSpace.addRectangles(rec1,rec2);

        assertEquals(true, squareSpace.isContainment());

        // tests rectangles flipped
        squareSpace = new SquareSpace(25,25);
        squareSpace.addRectangles(rec2, rec1);
        assertEquals(true, squareSpace.isContainment());


        // tests for false
        SquareSpace squareSpace2 = new SquareSpace(25,25);
        Rectangle rec3 = new Rectangle(4,4,8,8);

        squareSpace2.addRectangles(rec1,rec3);

        assertEquals(false, squareSpace2.isContainment());
    }

    @Test
    @DisplayName("Registers correct Adjacency for added rectangles")
    void checkForAdjacency() {

        Rectangle rec1 = new Rectangle(4,4,0,0);
        Rectangle rec2 = new Rectangle(4,4,4,0);

        squareSpace.addRectangles(rec1,rec2);

        assertEquals("Proper", squareSpace.getAdjacency());

        squareSpace = new SquareSpace(25,25);
        rec2 = new Rectangle(2,2,4,1);

        squareSpace.addRectangles(rec1,rec2);

        assertEquals("Sub-line", squareSpace.getAdjacency());

        squareSpace = new SquareSpace(25,25);
        rec2 = new Rectangle(4,4,4,2);

        squareSpace.addRectangles(rec1,rec2);

        assertEquals("Partial", squareSpace.getAdjacency());

    }
}