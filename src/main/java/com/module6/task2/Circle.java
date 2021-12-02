package com.module6.task2;

class Circle extends Figure {
    private final Point point;
    private final double radius;

    public Circle(Point point, double radius) {
        if (point == null || radius <= 0) {
            throw new IllegalArgumentException();
        }
        this.point = point;
        this.radius = radius;
    }

    public Point getPoint() {
        return point;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public Point centroid() {
        return point;
    }

    @Override
    public boolean isTheSame(Figure figure) {
        if (figure == null) {
            throw new IllegalArgumentException("empty figure coordinates");
        }
        if (this.getClass() == figure.getClass()) {
            final double DELTA = 0.0001;
            double point1 = (point.getX() - figure.centroid().getX());
            double point2 = (point.getY() - figure.centroid().getY());
            double rad = radius - ((Circle) figure).getRadius();
            return Math.abs(point1) < DELTA && Math.abs(point2) < DELTA && Math.abs(rad) < DELTA;

        }
        return false;
    }
}

