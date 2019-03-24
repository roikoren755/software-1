import java.util.ArrayList;
import java.util.List;

public class Point {
	Integer x;
	Integer y;

	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public static void main(String[] args) {

		List<Point> points = new ArrayList<Point>();
		points.add(new Point(10, 2));
		points.add(new Point(5, 3));
		points.add(new Point(7, 6));

		points.sort((a, b) -> a.x.compareTo(b.x));
	}
}
