package visitors;

import java.util.function.Function;

import tester.Tester;
/**
 * Problem Statement:
 * We would like to extend the functionality of the IShape Interface and implementing classes.
 * Specifically, we want to be able to calculate the Area of each of the IShape subtypes.
 * However, we are unable to add any methods to the IShape interface.
 *
 * Fortunately the creator included an accept method so that we can
 * leverage the Visitor pattern to extend the existing functionality!
 *
 *
 * @author nicolasburniske
 *
 * TODO: Try adding a Triangle or another Shape and extending the functionality of the existing AreaVisitor
 * TODO: Try doing a perimeter visitor
 *
 */

// Represents a 2 dimensional Shape
interface IShape {
    <R> R accept(IShapeVisitor<R> visitor);
}

// Represents a Circle
class Circle implements IShape {
    int radius;

    Circle(int radius) {
        this.radius = radius;
    }

    public <R> R accept(IShapeVisitor<R> visitor) {
        return visitor.visit(this);
    }
}

// Represents a Square
class Square implements IShape {
    int width, height;

    Square(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public <R> R accept(IShapeVisitor<R> visitor) {
        return visitor.visit(this);
    }
}


interface IShapeVisitor<R> extends Function<IShape, R> {
    // Given that we have extended the Function Interface we
    // now have the R apply(IShape shape) method implicitly defined in our interface
    R visit(Circle c);
    R visit(Square s);

}

// Calculates area for all IShapes
class AreaVisitor implements IShapeVisitor<Double> {

    public Double apply(IShape shape) {
        return shape.accept(this);
    }

    public Double visit(Circle c) {
        return Math.PI * c.radius * c.radius;
    }

    public Double visit(Square s) {
        return s.height * s.width * 1.0;
    }
}

class ExamplesShapesVisit {
    IShapeVisitor<Double> visitor = new AreaVisitor();
    IShape square1 = new Square(5, 10);
    IShape circle1 = new Circle(10);

    void testAreaVisitor(Tester t) {
        // these are two equivalent ways to execute the visitor
        // either have the type accept the visitor,
        // or have the visitor apply itself

        // Ask yourself: WHY DOESN'T THIS WORK?
        // t.checkInexact(visitor.visit(square1), 50.0, 0.0001);
        t.checkInexact(visitor.apply(square1), 50.0, 0.0001);
        t.checkInexact(square1.accept(visitor), 50.0, 0.0001);

        // Ask yourself: WHY DOESN'T THIS WORK?
        // t.checkInexact(visitor.visit(circle1), 50.0, 0.0001);
        t.checkInexact(visitor.apply(circle1), 314.159, 0.0001);
        t.checkInexact(circle1.accept(visitor), 314.159, 0.0001);
    }
}