import org.w3c.dom.css.Rect;


import java.util.*;

public class SquareSpace {

    private static int length;
    private static int width;
    private static ArrayList<Rectangle> rectangles;
    private static boolean intersection = false;
    private static boolean containment = false;
    private static String adjacency = "none";


    // constructor validates the map is at least 12 by 12
    public SquareSpace(int length, int width) {
        if(length < 12 || width < 12){
            throw new IllegalArgumentException("This map must be at least 12 by 12");
        }
        this.length = length;
        this.width = width;
        this.rectangles = new ArrayList<>();
    }


    // Method to add rectangles to the map
    public static void addRectangles(Rectangle rectangle, Rectangle rectangle2){

        // if rectangles go out of bounds it throws an exception
        if (rectangle.getEndX() > width - 1 || rectangle.getEndY() > length - 1 || rectangle2.getEndX() > width - 1 || rectangle2.getEndY() > length - 1){
            throw new IllegalArgumentException("Rectangles must fit within the map!");

        }

        // if map has rectangles already it will throw an exception
        if (rectangles.size() >= 2){
            throw new IllegalArgumentException("This map already has 2 rectangles!");
        } else {
          // adds rectangles to list
          rectangles.add(rectangle);
          rectangles.add(rectangle2);

          // Sets the state of rectangle properties
          checkForIntersection(rectangle, rectangle2);
          checkForContainment(rectangle, rectangle2);
          checkForAdjacency(rectangle, rectangle2);
        }

    }

    // method to check if the rectangles intersect
    protected static void checkForIntersection(Rectangle rectangle, Rectangle rectangle2){

        // Time complexity O(n^2) -- could be a bit better with a better rectangle design.

        // loops through the rectangle Y-axis
        for (int i = rectangle.getStartY(); i <= rectangle.getEndY(); i++) {

            // loops through the rectangle X-axis
            for (int j = rectangle.getStartX(); j <= rectangle.getEndX(); j++) {

                // checks if rectangle 2 point exists in rectangle 1.
                if (i >= rectangle2.getStartY() && i<= rectangle2.getEndY() && j >= rectangle2.getStartX() && j <= rectangle2.getEndX()) {
                    // sets intersection to true
                    intersection = true;

                }
            }


        }

    }

    // method to check for containment
    protected static void checkForContainment(Rectangle rectangle, Rectangle rectangle2){
        // initialize rectangle 1 as larger rectangle
        Rectangle largerRectangle = rectangle;
        Rectangle smallRectangle = rectangle2;

        // if rectangle 2 is larger, set larger to rec 2
        if(rectangle2.getArea() > rectangle.getArea()){
            largerRectangle = rectangle2;
            smallRectangle = rectangle;
        }


        // rules out small rectangle exiting the y axis at end
        if (smallRectangle.getEndY() > largerRectangle.getEndY()){
            return;

            // rules out small rectangle exiting the x axis at end
        } else if (smallRectangle.getEndX() > largerRectangle.getEndX()){
            return;
            // rules out of bounds at start points
        } else if (smallRectangle.getStartY() < largerRectangle.getStartY() || smallRectangle.getStartX() < largerRectangle.getStartX()){
            return;
        } else {
            // if no other conditions triggered containment is true!
            containment = true;
        }

    }

    // method to check for adjacency
    protected static void checkForAdjacency(Rectangle rectangle, Rectangle rectangle2){

        // if containment or intersection exists, the rectangles are not adjacent.
        if(containment || intersection){
            return;
        }

        // initialized boolean values
        boolean adjacentByX = false;
        boolean adjacentByY = false;


        // if the rectangles are potentially adjacent in the x-axis run a loop
        if (rectangle.getStartX() == rectangle2.getEndX() + 1 || rectangle.getEndX() == rectangle2.getStartX() +1 || rectangle2.getEndX() +1 == rectangle.getStartX() || rectangle.getEndX() + 1 == rectangle2.getStartX()){

            // loop through y axis of rectangle 1
            for (int i =rectangle.getStartY(); i <= rectangle.getEndY(); i++ ){
                // if at any point this contains a rectangle 2 point, rectangles are adjacent by X-axis
                if(i >= rectangle2.getStartY() && i <= rectangle2.getEndY()){
                    adjacentByX = true;
                    break;
                }
            }
            // if rectangles are potentially adjacent by y-axis run a loop
        }
        if (rectangle.getStartY() == rectangle2.getEndY() +1 || rectangle.getEndY() + 1 == rectangle2.getStartY() || rectangle.getEndY() +1 == rectangle2.getStartY() || rectangle.getStartY() == rectangle2.getEndY() + 1){
            // loop through x-axis of rectangle 1
            for (int i =rectangle.getStartX(); i <= rectangle.getEndX(); i++ ) {
                // if at any point rectangle this contains a rectangle 2 point rectangles are adjacent by y.
                if (i >= rectangle2.getStartX() && i <= rectangle2.getEndX()) {
                    adjacentByY = true;
                    break;
                }
            }
        }




        // if rectangles are adjacent by X
        if (adjacentByX){
            // compare y-axis to see if equal denoting proper
            if(rectangle.getStartY() == rectangle2.getStartY() && rectangle.getEndY() == rectangle2.getEndY()){
                adjacency = "Proper";

                // compare y-axis to see if either rectangle is adjacent sub-line
            } else if((rectangle.getStartY() > rectangle2.getStartY() && rectangle.getEndY() < rectangle2.getEndY()) || (rectangle2.getStartY() > rectangle.getStartY() && rectangle2.getEndY() < rectangle.getEndY()) ){
                adjacency = "Sub-line";
                // if none of above is true, the adjacency must be partial
            } else {
                adjacency = "Partial";
            }

        } else if (adjacentByY){
            // compare y-axis to see if equal denoting proper
            if(rectangle.getStartX() == rectangle2.getStartX() && rectangle.getEndX() == rectangle2.getEndX()){
                adjacency = "Proper";

                // compare x-axis to see if either rectangle is adjacent sub-line
            } else if((rectangle.getStartX() > rectangle2.getStartX() && rectangle.getEndX() < rectangle2.getEndX()) || (rectangle2.getStartX() > rectangle.getStartX() && rectangle2.getEndX() < rectangle.getEndX()) ){
                adjacency = "Sub-line";

                // if none of above is true, the adjacency must be partial
            } else {
                adjacency = "Partial";
            }
        }

    }


    // method to draw map to console
    public void drawMap(){
        // simple message declaring the map size
        System.out.println("This Map is: " + length + " by "+ width);

        // if the map does not contain 2 rectangles alerts user of such and returns.
        if (this.rectangles.size() != 2){
            System.out.println("This Map does not have 2 rectangles yet!");
            return;
        }

            // pointers to each rectangle
            Rectangle rec1 = rectangles.get(0);
            Rectangle rec2 = rectangles.get(1);


            // time complexity O(n^2) to draw map, could be optimized using a better data structure

        // loop through y axis of map in reverse to render the coordinates correctly
        for (int i = length; i >= 0; i--){
            // initialize row string at each y axis -- stores the chars for map render
            String row = "";


            // loop through x-axis of map
            for (int j =0; j <= width; j++){

                // checks for existing rectangle borders are in x-axis
                if ( ((i == rec1.getEndY() || i == rec1.getStartY()) && j<= rec1.getEndX() && j>= rec1.getStartX()) || (i == rec2.getEndY() || i == rec2.getStartY()) && j<= rec2.getEndX() && j>= rec2.getStartX()){

                    // if they do exist, first check if it's a corner spot
                    if ((j == rec1.getEndX()) && (i== rec1.getStartY() || i == rec1.getEndY() ) || (j == rec1.getStartX()) && (i== rec1.getStartY() || i == rec1.getEndY() ) || (j == rec2.getStartX()) && (i== rec2.getStartY() || i == rec2.getEndY()) || (j == rec2.getEndX()) && (i== rec2.getStartY() || i == rec2.getEndY())){
                        // corners rendered as X
                        row += "X";
                    } else {
                        // other borders of rectangle rendered as *
                        row += "*";
                    }
                    // grabs borders along y-axis for each rectangle to render as *
                } else if(((j == rec1.getEndX() || j == rec1.getStartX()) && i<= rec1.getEndY() && i>= rec1.getStartY()) || ((j == rec2.getEndX() || j == rec2.getStartX()) && i<= rec2.getEndY() && i>= rec2.getStartY()) ){
                    row += "*";

                    // if row is top or bottom of map render horizontal outline
                } else if (i == 0 || i == length){
                    row += "-";

                    // if column is the start of end of map render map outline vertically
                } else if (j == 0 || j == width){
                    row += "|";

                    // if no conditions met spot is a blank space
                } else {
                    row += " ";
                }

                // at the end of each row print out that row and reinitialize at next row loop.
                if(j == width){
                    System.out.println(row);
                }

            }
        }
        // simple display of rectangles status of intersection, containment and adjacency
        System.out.println("Intersection: " + intersection + ", containment: " + containment + ", adjacency: " + adjacency );
    }

    public String drawMapGui(){
        // simple message declaring the map size
        String output = "This Map is: " + length + " by "+ width +"\n";

        // if the map does not contain 2 rectangles alerts user of such and returns.
        if (this.rectangles.size() != 2){
            System.out.println("This Map does not have 2 rectangles yet!");
            return "This Map does not have 2 rectangles yet!";
        }

        // pointers to each rectangle
        Rectangle rec1 = rectangles.get(0);
        Rectangle rec2 = rectangles.get(1);


        // time complexity O(n^2) to draw map, could be optimized using a better data structure

        // loop through y axis of map in reverse to render the coordinates correctly
        for (int i = length; i >= 0; i--){
            // initialize row string at each y axis -- stores the chars for map render
            String row = "";


            // loop through x-axis of map
            for (int j =0; j <= width; j++){

                // checks for existing rectangle borders are in x-axis
                if ( ((i == rec1.getEndY() || i == rec1.getStartY()) && j<= rec1.getEndX() && j>= rec1.getStartX()) || (i == rec2.getEndY() || i == rec2.getStartY()) && j<= rec2.getEndX() && j>= rec2.getStartX()){

                    // if they do exist, first check if it's a corner spot
                    if ((j == rec1.getEndX()) && (i== rec1.getStartY() || i == rec1.getEndY() ) || (j == rec1.getStartX()) && (i== rec1.getStartY() || i == rec1.getEndY() ) || (j == rec2.getStartX()) && (i== rec2.getStartY() || i == rec2.getEndY()) || (j == rec2.getEndX()) && (i== rec2.getStartY() || i == rec2.getEndY())){
                        // corners rendered as X
                        row += "X";
                    } else {
                        // other borders of rectangle rendered as *
                        row += "*";
                    }
                    // grabs borders along y-axis for each rectangle to render as *
                } else if(((j == rec1.getEndX() || j == rec1.getStartX()) && i<= rec1.getEndY() && i>= rec1.getStartY()) || ((j == rec2.getEndX() || j == rec2.getStartX()) && i<= rec2.getEndY() && i>= rec2.getStartY()) ) {
                    row += "*";


                } else {
                    row += "  ";
                }

                // at the end of each row print out that row and reinitialize at next row loop.
                if(j == width){
                    output += row +"\n";
                }

            }
        }
        // simple display of rectangles status of intersection, containment and adjacency
        output += "Intersection: " + intersection + ", containment: " + containment + ", adjacency: " + adjacency;
        return output;
    }

    public ArrayList<Rectangle> getRectangles() {
        return rectangles;
    }

    protected static boolean isIntersection() {
        return intersection;
    }

    protected static boolean isContainment() {
        return containment;
    }

    protected String getAdjacency() {
        return adjacency;
    }
}
