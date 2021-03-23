public class Main {

    public static void main(String[] args) {

        Rectangle rec1 = new Rectangle(8, 16, 0,0);
        Rectangle rec2 = new Rectangle(16, 16, 0,0);
        //Rectangle rec2 = new Rectangle(8, 8, 8,8);

        SquareSpace newMap = new SquareSpace(50, 50);
        newMap.addRectangle(rec1, rec2);

        newMap.drawMap();

    }
}
