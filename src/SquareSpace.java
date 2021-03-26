import org.w3c.dom.css.Rect;


import java.util.*;

public class SquareSpace {

    private final int length;
    private final int width;
    private ArrayList<Rectangle> rectangles;
    private boolean intersection = false;
    private boolean containment = false;
    private String adjacency = "none";


    public SquareSpace(int length, int width) {
        if(length < 12 || width < 12){
            throw new IllegalArgumentException("This map must be at least 12 by 12");
        }
        this.length = length;
        this.width = width;
        this.rectangles = new ArrayList<>();
    }



    public void addRectangles(Rectangle rectangle, Rectangle rectangle2){

        if (rectangle.getEndX() > width - 1 || rectangle.getEndY() > length - 1 || rectangle2.getEndX() > width - 1 || rectangle2.getEndY() > length - 1){
            throw new IllegalArgumentException("Rectangles must fit within the map!");

        }

        if (this.rectangles.size() >= 2){
            throw new IllegalArgumentException("This map already has 2 rectangles!");
        } else {
          this.rectangles.add(rectangle);
          this.rectangles.add(rectangle2);
          checkForIntersection(rectangle, rectangle2);
          checkForContainment(rectangle, rectangle2);
          checkForAdjacency(rectangle, rectangle2);
        }

    }

    protected void checkForIntersection(Rectangle rectangle, Rectangle rectangle2){



        for (int i = rectangle.getStartY(); i <= rectangle.getEndY(); i++) {

            for (int j = rectangle.getStartX(); j <= rectangle.getEndX(); j++) {

                if (i >= rectangle2.getStartY() && i<= rectangle2.getEndY() && j >= rectangle2.getStartX() && j <= rectangle2.getEndX()) {
                    this.intersection = true;

                }
            }


        }

    }

    protected void checkForContainment(Rectangle rectangle, Rectangle rectangle2){
        Rectangle largerRectangle = rectangle;
        Rectangle smallRectangle = rectangle2;

        if(rectangle2.getArea() > rectangle.getArea()){
            largerRectangle = rectangle2;
            smallRectangle = rectangle;
        }

        if (smallRectangle.getEndY() > largerRectangle.getEndY()){
            return;
        } else if (smallRectangle.getEndX() > largerRectangle.getEndX()){
            return;
        } else if (smallRectangle.getStartY() < largerRectangle.getStartY() || smallRectangle.getStartX() < largerRectangle.getStartX()){
            return;
        } else {
            this.containment = true;
        }

    }

    protected void checkForAdjacency(Rectangle rectangle, Rectangle rectangle2){

        boolean adjacentByX = false;
        boolean adjacentByY = false;


        if (rectangle.getStartX() == rectangle2.getEndX() + 1 || rectangle.getEndX() == rectangle2.getStartX() +1 || rectangle2.getEndX() +1 == rectangle.getStartX() || rectangle.getEndX() + 1 == rectangle2.getStartX()){

            for (int i =rectangle.getStartY(); i <= rectangle.getEndY(); i++ ){
                if(i >= rectangle2.getStartY() && i <= rectangle2.getEndY()){
                    adjacentByX = true;
                    break;
                }
            }
        } else if (rectangle.getStartY() == rectangle2.getEndY() +1 || rectangle.getEndY() + 1 == rectangle2.getStartY() || rectangle.getEndY() +1 == rectangle2.getStartY() || rectangle.getStartY() == rectangle2.getEndY() + 1){
            for (int i =rectangle.getStartX(); i <= rectangle.getEndX(); i++ ) {
                if (i >= rectangle2.getStartX() && i <= rectangle2.getEndX()) {
                    adjacentByY = true;
                    break;
                }
            }
        }





        if (adjacentByX){
            if(rectangle.getStartY() == rectangle2.getStartY() && rectangle.getEndY() == rectangle2.getEndY()){
                this.adjacency = "Proper";
            } else if((rectangle.getStartY() > rectangle2.getStartY() && rectangle.getEndY() < rectangle2.getEndY()) || (rectangle2.getStartY() > rectangle.getStartY() && rectangle2.getEndY() < rectangle.getEndY()) ){
                this.adjacency = "Sub-line";
            } else {
                this.adjacency = "Partial";
            }

        } else if (adjacentByY){
            if(rectangle.getStartX() == rectangle2.getStartX() && rectangle.getEndX() == rectangle2.getEndX()){
                this.adjacency = "Proper";
            } else if((rectangle.getStartX() > rectangle2.getStartX() && rectangle.getEndX() < rectangle2.getEndX()) || (rectangle2.getStartX() > rectangle.getStartX() && rectangle2.getEndX() < rectangle.getEndX()) ){
                this.adjacency = "Sub-line";
            } else {
                this.adjacency = "Partial";
            }
        }

    }


    public void drawMap(){
        System.out.println("This Map is: " + length + " by "+ width);

        if (this.rectangles.size() != 2){
            System.out.println("This Map does not have 2 rectangles yet!");
            return;
        }

            Rectangle rec1 = rectangles.get(0);
            Rectangle rec2 = rectangles.get(1);



        for (int i = length; i >= 0; i--){
            String row = "";


            for (int j =0; j <= width; j++){

                if ( ((i == rec1.getEndY() || i == rec1.getStartY()) && j<= rec1.getEndX() && j>= rec1.getStartX()) || (i == rec2.getEndY() || i == rec2.getStartY()) && j<= rec2.getEndX() && j>= rec2.getStartX()){

                    if ((j == rec1.getEndX()) && (i== rec1.getStartY() || i == rec1.getEndY() ) || (j == rec1.getStartX()) && (i== rec1.getStartY() || i == rec1.getEndY() ) || (j == rec2.getStartX()) && (i== rec2.getStartY() || i == rec2.getEndY()) || (j == rec2.getEndX()) && (i== rec2.getStartY() || i == rec2.getEndY())){
                        // corners rendered as X
                        row += "X";
                    } else {
                        // other borders of rectangle rendered as *
                        row += "*";
                    }

                } else if(((j == rec1.getEndX() || j == rec1.getStartX()) && i<= rec1.getEndY() && i>= rec1.getStartY()) || ((j == rec2.getEndX() || j == rec2.getStartX()) && i<= rec2.getEndY() && i>= rec2.getStartY()) ){
                    row += "*";
                } else if (i == 0 || i == length){
                    row += "-";
                } else if (j == 0 || j == width){
                    row += "|";

                } else {
                    row += " ";
                }

                if(j == width){
                    System.out.println(row);
                }

            }
        }
        System.out.println("Intersection: " + intersection + ", containment: " + containment + ", adjacency: " + adjacency );
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public ArrayList<Rectangle> getRectangles() {
        return rectangles;
    }

    protected boolean isIntersection() {
        return intersection;
    }

    protected boolean isContainment() {
        return containment;
    }

    protected String getAdjacency() {
        return adjacency;
    }
}
