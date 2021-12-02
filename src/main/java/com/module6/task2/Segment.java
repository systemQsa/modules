package com.module6.task2;

class Segment {

    private final Point start;
    private final Point end;

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public Segment(Point start, Point end) {

        if (start == null) throw new IllegalArgumentException("start == null");
        if (end == null) throw new IllegalArgumentException("end == null");
        if (start.getX() == end.getX() && start.getY() == end.getY())
            throw new IllegalArgumentException("start == end");
        this.start = start;
        this.end = end;
    }

    Point intersection(Segment another) {

        double a;
        double b;
        double c;
        double d;
        double x;
        double y;

        // y = ax + b
        a = getA(start, end);
        b = getB(start, end);

        // y = cx + d
        c = getA(another.getStart(), another.getEnd());
        d = getB(another.getStart(), another.getEnd());

        if (a == c)
            return null;

        if (start.getX() == end.getX())
            x = start.getX();
        else if (another.start.getX() == another.end.getX()) {
            x = another.start.getX();
            c = a;
            d = b;
        } else
            x = (d - b) / (a - c);

        if (start.getY() == end.getY())
            y = start.getY();
        else if (another.start.getY() == another.end.getY())
            y = another.start.getY();
        else
            y = c * x + d;

        if ((start.getX() - x) * (end.getX() - x) > 0) return null;
        if ((another.start.getX() - x) * (another.end.getX() - x) > 0) return null;

        return new Point(x, y);

    }

    public static double getA(Point start, Point end) {
        if (end.getX() == start.getX()) return 0;
        return (end.getY() - start.getY()) / (end.getX() - start.getX());
    }

    public static double getB(Point start, Point end) {
        return start.getY() - (end.getY() - start.getY()) / (end.getX() / start.getX() - 1);
    }

}