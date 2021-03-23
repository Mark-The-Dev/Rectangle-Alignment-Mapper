import java.util.ArrayList;

public class SquareSpace {

    private final int length;
    private final int width;
    private final int endX;
    private final int endY;
    private ArrayList<Rectangle> rectangles;

    public SquareSpace(int length, int width) {
        this.length = length;
        this.width = width;
        this.endX = width -1;
        this.endY = length -1;
        this.rectangles = new ArrayList<>();
    }

    public void addRectangle(Rectangle rectangle, Rectangle rectangle2){
        if (this.rectangles.size() >= 2){
            return;
        } else {
          this.rectangles.add(rectangle);
            this.rectangles.add(rectangle2);

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
                    row += "*";
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
    }

}
