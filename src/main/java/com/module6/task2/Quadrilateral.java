package com.module6.task2;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import static java.lang.StrictMath.pow;

class Quadrilateral extends Figure {
    private final Point a;
    private final Point b;
    private final Point c;
    private final Point d;


    double abPoint;
    double bcPoint;
    double cdPoint;
    double adPoint;
    double digAC;
    double digBD;

    public Quadrilateral(Point a, Point b, Point c, Point d) {

        if (!validation(a, b, c, d)) {
            throw new IllegalArgumentException("incorrect input");
        }

        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    private boolean validation(Point a, Point b, Point c, Point d) {
        if (a == null || b == null || c == null || d == null) {
            return false;
        }

        abPoint = Math.sqrt(Math.pow((b.getX() - a.getX()), 2) + Math.pow((b.getY() - a.getY()), 2));
        bcPoint = Math.sqrt(Math.pow((c.getX() - b.getX()), 2) + Math.pow((c.getY() - b.getY()), 2));
        cdPoint = Math.sqrt(Math.pow((d.getX() - c.getX()), 2) + Math.pow((d.getY() - c.getY()), 2));
        adPoint = Math.sqrt(Math.pow((d.getX() - a.getX()), 2) + Math.pow((d.getY() - a.getY()), 2));

        digAC = Math.sqrt(Math.pow((c.getX() - a.getX()), 2) + Math.pow((c.getY() - a.getY()), 2));
        digBD = Math.sqrt(Math.pow((d.getX() - b.getX()), 2) + Math.pow((d.getY() - b.getY()), 2));


        if (((a.getX() - b.getX()) / (c.getX() - b.getX()) == ((a.getY() - b.getY()) / (c.getY() - b.getY()))) &&

                ((b.getX() - c.getX()) / (d.getX() - c.getX()) == (b.getY() - c.getY()) / (d.getY() - c.getY())) &&

                ((c.getX() - d.getX()) / (a.getX() - d.getX()) == (c.getY() - d.getY()) / (a.getY() - d.getY())) &&

                ((d.getX() - a.getX()) / (b.getX() - c.getX()) == (d.getY() - a.getY()) / (b.getY() - c.getY()))) {
            return false;
        }
        if (((a.getX() - c.getX()) / (b.getX() - c.getX()) == (a.getY() - c.getY()) / (b.getY() - c.getY()))) {
            return false;
        }


        if ((abPoint + bcPoint) == digAC) {
            return false;
        }

        Segment ab = new Segment(a, b);
        Segment bc = new Segment(b, c);
        Segment cd = new Segment(c, d);
        Segment da = new Segment(d, a);

        if (ab.intersection(cd) != null) {
            return false;
        }
        if (bc.intersection(da) != null) {
            return false;
        }

        Triangle abc = new Triangle(a, b, c);
        Triangle adc = new Triangle(a, d, c);
        Triangle bcd = new Triangle(b, c, d);
        Triangle bad = new Triangle(b, a, d);

        return isRelativelyEqual(abc.square(a, b, c) + adc.square(a, d, c), bcd.square(b, c, d) + bad.square(b, a, d));
    }

    @Override
    public Point centroid() {
        Triangle abd;
        Triangle bcd;
        Point a1 = new Point(a.getX(), a.getY());
        Point b1 = new Point(b.getX(), b.getY());
        Point d1 = new Point(d.getX(), d.getY());
        Point c1 = new Point(c.getX(), c.getY());

        abd = new Triangle(a1, b1, d1);
        bcd = new Triangle(b1, c1, d1);

        Point p = abd.centroid();
        Point q = bcd.centroid();

        double g = abd.square(a, b, d);
        double h = bcd.square(b, c, d);

        double x = (g * p.getX() + h * q.getX()) / (g + h);
        double y = (g * p.getY() + h * q.getY()) / (g + h);

        return new Point(x, y);
    }

    static boolean isRelativelyEqual(double d1, double d2) {
        return abs(d1 - d2) < 0.0001;
    }

    public boolean checkSame(Point point1, Point point2) {
        return isRelativelyEqual(point1.getX(), point2.getX()) && isRelativelyEqual(point1.getY(), point2.getY());
    }

    double length(Point start, Point end) {
        double len = (pow((end.getX() - start.getX()), 2) + pow((end.getY() - start.getY()), 2));
        return sqrt(len);
    }


    @Override
    public boolean isTheSame(Figure figure) {
        if (figure instanceof Quadrilateral) {
            var another = (Quadrilateral) figure;
            double ac = length(a, c);
            double bd = length(b, d);
            double anotherAC = length(another.a, another.c);
            double anotherBD = length(another.b, another.d);

            return (isRelativelyEqual(ac, anotherAC) && isRelativelyEqual(bd, anotherBD)) ||
                    isRelativelyEqual(ac, anotherBD) && isRelativelyEqual(bd, anotherAC);

        } else {
            return false;
        }
    }
}
