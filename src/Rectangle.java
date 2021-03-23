public class Rectangle {
    private final int length;
    private final int width;
    private final int startX;
    private final int startY;
    private final int endX;
    private final int endY;


    public Rectangle(int length, int width, int startX, int startY) {
        this.length = length;
        this.width = width;
        this.startX = startX;
        this.startY = startY;
        this.endX = startX + (width - 1);
        this.endY = startY + (length - 1);
        System.out.println(getEndX() + " . . ." + getEndY());
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getEndX() {
        return endX;
    }

    public int getEndY() {
        return endY;
    }
}
