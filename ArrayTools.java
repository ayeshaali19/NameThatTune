/**
* <h1>Array Tools</h1>
* The Array Tools program implements an application that
* can manipulate arrays
* <p>
* 
*
* @author  Ayesha Ali
* @version 1.0
* @since   11/17/17 
*/

public class ArrayTools {

	/**
   * This method is used to print arrays.
   * @param a[] This is the only parameter for the method: it inputs an array.
   */
	public static void printArray (double a[]) {
		for (int i = 0; i<a.length; i++) {
			System.out.println(a[i]);
		}
	}

	/**
   * This method is used to find the max value in an array.
   * @param a[] This is the only parameter for the method: it inputs an array.
   * @return max This double holds the maximum value of the array.
   */

	public static double max(double a[]) {
		double max = Double.NEGATIVE_INFINITY;
		for (int i = 0; i<a.length; i++) {
			if (a[i] > max) max = a[i];
		}
		return max;
	}

		/**
   * This method is used to find the min value in an array.
   * @param a[] This is the only parameter for the method: it inputs an array.
   * @return min This double holds the minimum value of the array.
   */

	public static double min(double a[]) {
		double min = Double.POSITIVE_INFINITY;
		for (int i = 0; i<a.length; i++) {
			if (a[i] < min) min = a[i];
		}
		return min;
	}

		/**
   * This method is used to find the average of the values of an array.
   * @param a[] This is the only parameter for the method: it inputs an array.
   * @return average This double holds the average value of the array.
   */

	public static double mean(double a[]) {
		double sum = 0.0;
		for (int i= 0; i< a.length; i++) {
			sum +=a[i];
		}
		double average = sum/a.length;
		return average;
	}

		/**
   * This method is creates a new array identical to the inputed array.
   * @param a[] This is the only parameter for the method: it inputs an array.
   * @return b[] This array is identical to the input array.
   */

	public static double[] duplicate(double a[]) {
		double[] b = new double[a.length];
		for (int i = 0; i< a.length; i++) {
			b[i] = a[i];
		}
		return b;
	}

		/**
   * This method is used to reverse the order of an array.
   * @param a[] This is the only parameter for the method: it inputs an array.
   * @return b[] This array is a reversed version of the input.
   */

	public static double[] reverse(double a[]) {
		double[] b = new double[a.length];
		for (int i = 0; i<a.length/2; i++) {
			double temp = a[i];
			b[i] = a[a.length -1 -i];
			b[a.length-i-1] = temp; 
		}
		return b;
	}

		/**
   * This method is used to figure out if an array is identical to another by both size and content.
   * @param a[] One of the arrays being compared
    *@param b[] The other array being compared
   * @return boolean; True or false is returned.
   */

	public static boolean areEqual (double a[], double b[]) {
		if (a.length == b.length) {
			for (int i = 0; i< a.length; i++) {
				if (a[i] != b[i]) {
					return false;
				} 
			}
			return true;
		} else {return false;}
	}

	/**
   * This method creates a scaled version of an inputed array.
   * @param a[] An inputed array
    *@param c The scale factor
   * @return b[] A new array that is a scaled version of a[].
   */

	public static double[] scale(double a[], double c) {
		double[] b = new double[a.length];
		for (int i = 0; i<a.length; i++) {
			b[i] = a[i]*c;
		}
		return b;
	}


	/**
   * This method is used to figure out if an array is identical to another by both size and content.
   * @param a[] One of the arrays being added
   * @param b[] The other array being added
   * @param c The weighted factor being applied to array a.
   * @param d The weighted factor being applied to array b.
   * @return e[] An array that is the weighted addition of a and b.
   */
	public static double[] add(double a[], double b[], double c, double d) {
		double[] e;
		double f;
		if (a.length > b.length) {
			e = new double[a.length];
			f = b.length;
		} else {
			e = new double[b.length];
			f = a.length;
		}

		for (int i = 0; i< e.length; i++) {
			if (i < f) {
				e[i] = a[i]*c + b[i]*d;
			} else {
				if (e.length == a.length) {
					e[i] = a[i]*c;
				} else if (e.length == b.length) {
					e[i] = b[i]*d;
				}
			}
		}

		return e;
	}

	/**
   * This method returns a subsection of an inputed array.
   * @param a[] An inputed array
   * @param b The first term number of the wanted subsection
   * @param c The last term number of the wanted subsection
   * @return d[] The created array with the subsection of the array
   */

	public static double[] copy(double a[], int b, int c) {
		double[] d = new double[c-b-1];
		for (int i = 0; i<d.length; i++) {
			d[i] = a[i+1+b];
		}
		return d;
	}

	/**
   * This method removes a subsection of an inputed array.
   * @param a[] An inputed array
   * @param b The first term number of the wanted subsection
   * @param c The last term number of the wanted subsection
   * @return d[] A created array with the subsection cut
   */
	public static double[] cut(double a[], int b, int c) {
		double[] d = new double[a.length- (c-b)];
		int counter = 0;
		for (int i = 0; i<a.length; i++) {
			if (i < b || i > (c-1)) {
				d[counter] = a[i];
				counter++;
			}
		} 
		return d;
	}

	/**
   * This method swaps two values from two inputed indexes in an array. 
   * @param a[] An inputed array
   * @param c The first term number being swapped
   * @param d The second term number being swapped
   */
	private static void swap(double a[], int c, int d) {
		double e = a[d];
		a[d] = a[c];
		a[c] = e;
	}

	/**
   * This method randomnly shuffles an inputed array.
   * @param a[] An inputed array
   * @return b A created array that contains all the values from a but in a random order.
   */
	private static double[] shuffle(double a[]) {
		double[] b = new double[a.length];
		for (int i = 0; i < a.length; i++) {
			b[i] = a[i];
		}

		for (int i =0; i < b.length; i++) {
			int rand = (int)(Math.random()*a.length);
			swap(b, i, rand);
		} 

		return b;
	}

	public static void main(String args[]) {
        double[] a = {1.0, 3.0, 5.0, 7.0, 9.0, 10.0, 12.8, 19.2, 24, 4392, 2.0, 23, 72, 19, 54, 45};
        double[] b = {2.0, 23, 72, 19, 54, 45};
        System.out.println("original:");
        printArray(a);

        System.out.println("\nreverse:");
        printArray(reverse(a));

		System.out.println("\nmax:");
        System.out.println(max(a));

        System.out.println("\nmin:");
        System.out.println(min(a));

        System.out.println("\nequal:");
        System.out.println(areEqual(a, b));

        System.out.println("\naverage:");
        System.out.println(mean(a));

        System.out.println("\nscale:");
        printArray(scale(a, 5.0));

        System.out.println("\nshuffle:");
        printArray(shuffle(b));

        System.out.println("\nadd:");
        printArray(add(a, b, .2, .5));

        System.out.println("\ncopy:");
        printArray(copy(a, 2, 5));

        System.out.println("\ncut:");
        printArray(cut(a, 2, 4));

        
    }

}