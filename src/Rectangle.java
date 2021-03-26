public class Rectangle {
    private final int length;
    private final int width;
    private final int startX;
    private final int startY;
    private final int endX;
    private final int endY;
    private final int area;


    // constructor - validates that rectangle will fit in map
    public Rectangle(int length, int width, int startX, int startY) {
        if(length < 2 || width < 2 || startX < 0 || startY < 0){
            throw new IllegalArgumentException("Rectangle must be at least 2 width, 2 length, and start within a positive coordinate");
        }

        this.length = length;
        this.width = width;
        this.startX = startX;
        this.startY = startY;
        this.endX = startX + (width - 1);
        this.endY = startY + (length - 1);
        this.area = length * width;
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

    public int getArea() {
        return area;
    }
}
