import pl.stqa.pft.sandbox.Point;

public class PointTest {

    public static void main(String[] args) {
        Point p1 = new Point(7,7);
        Point p2 = new Point (9.99, 9.99);

        System.out.println(" odległoś wynosi " + p2.distance(p1));

    }
}
