public class Main {

    public static void main(String[] args) {

        Rectangle rec1 = new Rectangle(8, 8, 2,2);
        Rectangle rec2 = new Rectangle(4, 4, 2,3);
        
        SquareSpace newMap = new SquareSpace(14, 20);
        newMap.addRectangles(rec2, rec1);

        newMap.drawMap();

    }
}
