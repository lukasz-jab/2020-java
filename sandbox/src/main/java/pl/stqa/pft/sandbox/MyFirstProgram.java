package pl.stqa.pft.sandbox;

public class MyFirstProgram {

	public static void main(String[] args) {

		Point p1 = new Point(3,3);
		Point p2 = new Point(6,6);

		System.out.println("Odległość pomiędzy punktem " + p2.x + " " + p2.y +
				" a punktem "+ p1.x + " " + p1.y + " wynosi " + p1.distance(p2) );


		hello("Łukasz");
		double len = 11.11;
		double a =5.5;
		double b = 10.5;

		Square s = new Square(44);

		Rectangle r = new Rectangle(8.9,6.3);

		System.out.println("Square o boku " + s.l + " ma pole " + s.area());
		System.out.println("Rectangle o bokach " + r.a +" x " + r.b + " ma pole " + r.area() );
		}

	private static double area(Square s) {

		return s.l*s.l;
	}

	public static double distance(Point p1, Point p2) {
		return Math.sqrt(((p2.x - p1.x)*(p2.x - p1.x))+((p2.y - p1.y)*(p2.y - p1.y)));
	}

	public static double area(Rectangle r) {
		return r.a*r.b;
	}

	private static void hello(String s) {
		System.out.println("Witaj " + s );
	}
}
