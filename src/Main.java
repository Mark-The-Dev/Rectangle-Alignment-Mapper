import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        // code to test in the IDE quickly

//        Rectangle rec1 = new Rectangle(4, 4, 4,2);
//        Rectangle rec2 = new Rectangle(4, 4, 0,0);
//
//        SquareSpace newMap = new SquareSpace(14, 20);
//        newMap.addRectangles(rec2, rec1);
//
//        newMap.drawMap();

        // method to run command line game.
        command();
    }

    // command line game.
    public static void command(){

        boolean isActive = true;
        boolean isRendered = false;

        while (isActive){
            SquareSpace gameMap = new SquareSpace(20,20);

            System.out.println("Hello and welcome to the Rectangle Alignment Tool!");
            System.out.println("For the purpose of this demo you are given a 20x20 map and can add Rectangles to it.");

            Rectangle rec1 = processRectangle(1);
            Rectangle rec2 = processRectangle(2);


            gameMap.addRectangles(rec1, rec2);
            gameMap.drawMap();
            isRendered = true;

            while(isRendered){
                System.out.println("Would you like to try again? (y / n)");
                String temp = scanner.next();

                if (temp.toLowerCase().contains("y")){
                    isRendered = false;
                    continue;
                } else if(temp.toLowerCase().contains("n")){
                    System.out.println("Thanks for playing, good bye!");
                    isRendered = false;
                    isActive = false;
                } else {
                    System.out.println("please type y for yes or n for no.");
                    continue;
                }

            }

        }




    }

    // helper method for command method.
    private static Rectangle processRectangle(int num){
        int tLength =0;
        int tWidth=0;
        int tX=-1;
        int tY=-1;
        int temp = 0;


        Rectangle rec = null;
        while (rec == null){
            if (tLength == 0){
                System.out.println("please enter the length rectangle " +num  + ".");
                temp = scanner.nextInt();
                if (temp < 2 || temp > 20){
                    System.out.println("Please use a value between 2 and 20 for the length!");
                    continue;
                } else {
                    tLength = temp;
                }
            } else if (tWidth == 0){
                System.out.println("please enter the width rectangle " + num  + ".");
                temp = scanner.nextInt();
                if(temp < 2 || temp > 20){
                    System.out.println("Please use a value between 2 and 20 for the width!");
                    continue;
                } else {
                    tWidth = temp;
                }

            } else if (tX == -1){
                System.out.println("Please enter the starting x coordinate for rectangle "  + num  + ".");
                temp = scanner.nextInt();
                if (temp < 0){
                    System.out.println("You can not start on a negative coordinate!");
                    continue;
                } else if (temp + tWidth > 20){
                    System.out.println("Starting on x: " + temp + " your end X would be: " + (temp + tWidth) + " which exceeds the map width of 20");
                    continue;
                } else {
                    tX = temp;
                }

            } else if (tY == -1){
                System.out.println("Please enter the starting y coordinate for rectangle "  + num  + ".");
                temp = scanner.nextInt();
                if (temp < 0){
                    System.out.println("You can not start on a negative coordinate!");
                    continue;
                } else if (temp + tLength > 20){
                    System.out.println("Starting on y: " + temp + " your end Y would be: " + (temp + tLength) + " which exceeds the map length of 20");
                    continue;
                } else {
                    tY = temp;
                }
            } else {
                System.out.println("Rectangle " + num + " dimensions are: Width - " + tWidth +
                        " Length - " + tLength + " StartingX - " + tX + " StartingY - " + tY);
                System.out.println("Is this correct? ( hit y / n)");
                String correct = scanner.next();
                if (correct.contains("y") || correct.contains("Y")){
                    rec = new Rectangle(tWidth, tLength, tX, tY);
                    System.out.println("Rectangle " + num + " made!");
                } else if (correct.contains("n") || correct.contains("N")){
                    System.out.println("very well, please re-enter the values for rectangle "  + num  + ".");
                    tWidth =0;
                    tLength =0;
                    tX= -1;
                    tY= -1;
                    continue;
                } else {
                    System.out.println("Please type y for yes, or n for no");
                    continue;
                }
            }

        }
        return rec;
    }

}
