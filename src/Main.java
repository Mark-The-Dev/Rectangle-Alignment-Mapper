public class Main {

    public static void main(String[] args) {

        Rectangle rec1 = new Rectangle(4, 9, 1,8);
        Rectangle rec2 = new Rectangle(8, 8, 7,0);
        //Rectangle rec2 = new Rectangle(8, 8, 8,8);

        SquareSpace newMap = new SquareSpace(50, 50);
        newMap.addRectangles(rec2, rec1);

        newMap.drawMap();

    }
}
