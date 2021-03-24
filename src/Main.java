public class Main {

    public static void main(String[] args) {

        Rectangle rec1 = new Rectangle(6, 6, 0,2);
        Rectangle rec2 = new Rectangle(5, 6, 5,0);
        //Rectangle rec2 = new Rectangle(8, 8, 8,8);

        SquareSpace newMap = new SquareSpace(14, 20);
        newMap.addRectangles(rec2, rec1);

        newMap.drawMap();

    }
}
