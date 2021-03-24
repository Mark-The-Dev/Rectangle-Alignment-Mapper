import org.w3c.dom.css.Rect;

import javax.print.DocFlavor;
import java.util.ArrayList;

public class SquareSpace {

    private final int length;
    private final int width;
    private final int endX;
    private final int endY;
    private ArrayList<Rectangle> rectangles;
    private boolean intersection = false;
    private boolean containment = false;
    private String adjacency = "none";


    public SquareSpace(int length, int width) {
        this.length = length;
        this.width = width;
        this.endX = width -1;
        this.endY = length -1;
        this.rectangles = new ArrayList<>();
    }

    public void addRectangles(Rectangle rectangle, Rectangle rectangle2){
        if (this.rectangles.size() >= 2){
            return;
        } else {
          this.rectangles.add(rectangle);
          this.rectangles.add(rectangle2);
          checkForIntersection(rectangle, rectangle2);
          checkForContainment(rectangle, rectangle2);
          checkForAdjacency(rectangle, rectangle2);
        }

    }

    private void checkForIntersection(Rectangle rectangle, Rectangle rectangle2){
        Rectangle largerRectangle = rectangle;
        Rectangle smallRectangle = rectangle2;

        if(rectangle2.getArea() > rectangle.getArea()){
            largerRectangle = rectangle2;
            smallRectangle = rectangle;
        }


        for(int i = smallRectangle.getStartX(); i<= smallRectangle.getEndX(); i++) {
            if (i == largerRectangle.getEndX() || i == largerRectangle.getStartX()){
                this.intersection = true;
                return;
            }
        }
        for(int i = smallRectangle.getStartY(); i<= smallRectangle.getEndY(); i++) {
            if (i == largerRectangle.getEndY() || i == largerRectangle.getStartY()) {
                this.intersection = true;
                return;
            }
        }
    }

    private void checkForContainment(Rectangle rectangle, Rectangle rectangle2){
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

    private void checkForAdjacency(Rectangle rectangle, Rectangle rectangle2){

        boolean adjacentByX = false;
        boolean adjacentByY = false;
//
//
//        if(rectangle2.getStartY() == rectangle.getEndY() + 1 && rectangle2.getStartX() <= rectangle.getEndX() || rectangle2.getStartX() >= rectangle.getStartX()){
//          adjacentByY = true;
//        }

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
            //this.adjacency = "Exists";

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


            Rectangle rec1 = rectangles.get(0);
            Rectangle rec2 = rectangles.get(1);



        for (int i = length; i >= 0; i--){
            String row = "";


            for (int j =0; j <= width; j++){

                if ( ((i == rec1.getEndY() || i == rec1.getStartY()) && j<= rec1.getEndX() && j>= rec1.getStartX()) || (i == rec2.getEndY() || i == rec2.getStartY()) && j<= rec2.getEndX() && j>= rec2.getStartX()){

                    if (j == rec1.getEndX() || j == rec1.getStartX() || j == rec2.getStartX() || j == rec2.getEndX()){
                        row += "X";
                    } else {
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

}
