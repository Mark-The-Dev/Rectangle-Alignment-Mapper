public class Main {

    public static void main(String[] args) {

        Rectangle rec1 = new Rectangle(4, 10, 4,2);
        Rectangle rec2 = new Rectangle(4, 4, 0,0);
        //Rectangle rec2 = new Rectangle(8, 8, 8,8);

        SquareSpace newMap = new SquareSpace(14, 20);
        newMap.addRectangles(rec2, rec1);

        newMap.drawMap();

    }
}
