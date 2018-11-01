package com.company;


public class Robot {
    private int x;
    private int y;
    private int maxX;
    private int maxY;
    // Coordinates are represented in an int array with values equivalent to coordinates Y+1,X+1,Y-1,X-1
    private int[] coordinates = new int[]{ 1,1,-1,-1};
    private int orientation;
    private String orientationSequence = "NESW";

    private int getIndex(char direction) {
        return this.orientationSequence.indexOf(direction);
    }

    private char getDirection(int index){
        return this.orientationSequence.charAt(index);
    }

    public void dispalyValues () {
        System.out.println(this.x+" "+this.y+" "+getDirection(this.orientation));
    }

    private void turnLeft() {
        this.orientation = this.orientation - 1;
        if (this.orientation < 0)
            this.orientation = this.orientation + this.coordinates.length;
    }

    private void turnRight() {
        this.orientation = this.orientation + 1;
        if (this.orientation > 3)
            this.orientation = this.orientation - this.coordinates.length;
    }

    private boolean validMove(int currentCoordinate, int maxCoordinate ) {
        boolean increment = this.orientation < 2;
        if (increment && currentCoordinate == maxCoordinate)
            return false;
        if (!increment && currentCoordinate == 0)
            return false;
        return true;
    }

    private boolean changeOf (char coordinate){
        boolean changeOfX = (this.orientation & 1) != 0;
        if (coordinate == 'X' && changeOfX){
            return true;
        }
        if (coordinate == 'Y' && !changeOfX) {
            return true;
        }
        return false;

    }

    private void move() {
        if (changeOf('X') && validMove(this.x, this.maxX)){
            this.x = this.x + this.coordinates[orientation];
            return;
        }
        if (changeOf('Y') && validMove(this.y, this.maxY)){
            this.y = this.y + this.coordinates[orientation];
        }
    }

    public void communicate (char instruction){
        switch (instruction) {
            case 'L':
                turnLeft();
                return;
            case 'R': turnRight();
                return;
            case 'M': move();
                return;
            default:
                return;
        }
    }

    public void restart (int x, int y, char direction) throws RuntimeException {
        if (x < 0 || y < 0 || x > maxX || y > maxY) {
            throw new RuntimeException("Invalid value for intitial position");
        }
        this.orientation = getIndex(direction);
        if (this.orientation < 0) {
            throw new RuntimeException("Invalid value for the direction");
        }
        this.x = x;
        this.y = y;
    }

    Robot(int x, int y, char direction, int maxX, int maxY) throws RuntimeException {
        if (maxX < 0 || maxY < 0){
            throw new RuntimeException("Invalid value for maximum endpoints");
        }
        this.maxX = maxX;
        this.maxY = maxY;
        restart(x,y,direction);

    }
}
