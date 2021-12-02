package com.module6.task2;

class Triangle extends Figure {
    private final Point a;
    private final Point b;
    private final Point c;

    public Triangle(Point a, Point b, Point c) {
        if (a == null || b == null || c == null) {
            throw new IllegalArgumentException();
        }
        if (checkInput(a, b, c)) {
            throw new IllegalArgumentException();
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    private boolean checkInput(Point a, Point b, Point c) {
        double a1 = lengthLine(a, b);
        double b1 = lengthLine(b, c);
        double c1 = lengthLine(c, a);

        if ((a1 + b1) > c1 && (a1 + c1) > b1 && (b1 + c1) > a1) {
            return square(a, b, c) == 0;
        }
        return true;
    }

    public double square(Point a, Point b, Point c) {
        return Math.abs((a.getX() - c.getX()) * (b.getY() - a.getY()) -
                (a.getX() - b.getX()) * (c.getY() - a.getY())) * 0.5;

    }

    private double lengthLine(Point start, Point end) {
        double len = (Math.pow((end.getX() - start.getX()), 2) + Math.pow((end.getY() - start.getY()), 2));
        return Math.sqrt(len);
    }


    @Override
    public Point centroid() {
        double pointX = (a.getX() + b.getX() + c.getX()) / 3.0;
        double pointY = (a.getY() + b.getY() + c.getY()) / 3.0;
        return new Point(pointX, pointY);

    }

    @Override
    public boolean isTheSame(Figure figure) {
        if (this.getClass() == figure.getClass()) {
            double epsilon = 0.0001;
            double deltaX = Math.abs(figure.centroid().getX() - this.centroid().getX());
            double deltaY = Math.abs(figure.centroid().getY() - this.centroid().getY());
            if (deltaX <= epsilon) {
                return deltaY <= epsilon;
            }
            return false;
        }
        return false;
    }
}