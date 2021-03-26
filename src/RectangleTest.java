import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {

    @Test
    @DisplayName("Creating an invalid Rectangle throws exception.")
    public void rectangleException(){

        // tests -length
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Rectangle(-1, 3,0,0));
        assertEquals("Rectangle must be at least 2 width, 2 length, and start within a positive coordinate", exception.getMessage());

        // tests -width
        exception = assertThrows(IllegalArgumentException.class, () -> new Rectangle(0, -1,0,0));
        assertEquals("Rectangle must be at least 2 width, 2 length, and start within a positive coordinate", exception.getMessage());

        // tests -x coordinate
        exception = assertThrows(IllegalArgumentException.class, () -> new Rectangle(3, 3,-1,0));
        assertEquals("Rectangle must be at least 2 width, 2 length, and start within a positive coordinate", exception.getMessage());

        // tests -y coordinate
        exception = assertThrows(IllegalArgumentException.class, () -> new Rectangle(3, 3,0,-2));
        assertEquals("Rectangle must be at least 2 width, 2 length, and start within a positive coordinate", exception.getMessage());

    }
}