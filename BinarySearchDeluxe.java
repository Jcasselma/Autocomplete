import java.util.Collections;
import java.util.Comparator;

public class BinarySearchDeluxe {

	public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
		if (a == null || key == null || comparator == null)
			throw new IllegalArgumentException("null");
		int lo = 0;
		int hi = a.length - 1;
		if (key == a[0])
			return 0;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			int comparison = comparator.compare(a[mid], key);
			if (comparison < 0)
				hi = mid -1;
			else if (comparison > 0)
				lo = mid +1;
			else if ((comparison == 0) && (a[mid - 1] == a[mid])) {
				hi = mid -1;
				comparison = comparator.compare(a[mid], key);
			} else
				return mid;

		}
		return -1;
}

	public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
		if (a == null || key == null || comparator == null)
			throw new IllegalArgumentException("null");
		int lo = 0;
		int hi = a.length - 1;
		if (key == a[a.length - 1])
			return (int) a.length - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			int comparison = comparator.compare(a[mid], key);
			if (comparison < 0)
				hi = mid - 1;
			else if (comparison > 0)
				lo = mid + 1;
			else if ((comparison == 0) && (a[mid + 1] == a[mid])) {
				lo = mid + 1;
				comparison = comparator.compare(a[mid], key);
			} else
				return mid;

		}
		return -1;
		}


	public static void main(String[] args) {
		Integer[] a = { 1, 1, 1, 2, 2, 2, 3,3,3,4,4,4,5,5,5,6,6,6 };
System.out.println(BinarySearchDeluxe.lastIndexOf(a, 1, Collections.reverseOrder()) + "\t");
System.out.println(BinarySearchDeluxe.lastIndexOf(a, 5, Collections.reverseOrder()) + "\t");
System.out.println(BinarySearchDeluxe.firstIndexOf(a, 5, Collections.reverseOrder()) + "\t");
System.out.println(BinarySearchDeluxe.firstIndexOf(a, 6, Collections.reverseOrder()) + "\t");
System.out.println(BinarySearchDeluxe.lastIndexOf(a, 6, Collections.reverseOrder()) + "\t");
}
}
// a[x].equals(x)
