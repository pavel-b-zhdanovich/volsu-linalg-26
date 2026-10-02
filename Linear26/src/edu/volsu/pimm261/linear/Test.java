package src.edu.volsu.pimm261.linear;

public class Test {

	public static void main (String[] args) {
		t4();		
		
		testBoshchenko();
	}
	public static void t1() {
		Rational r = new Rational(2);
		Rational s = new Rational(1,2);
		System.out.println(r.getInverse().getString());
	}
	
	public static Integer rnd(Integer a, Integer b) {
		return Math.round((float)(Math.random()*(b-a)+a));
	}
	
	public static void t2() {
		for (int i = 0; i < 20; i++) 
			System.out.println(rnd(-3,7));
	}
	public static void t3() {
		System.out.println(RationalUtils.getRandom(10, 100, 6).toFloat());
	}
	
	public static void t4() {
		Rational r = new Rational(1,2);
		Rational s = (Rational)r.clone();
		System.out.println(s.getString());
	}

	public static void testBoshchenko() {
		System.out.println("\n### Test methods Boshchenko");
		
		// Test methods "compareTo(Rational that)"
		Rational rational1 = new Rational(13, 11);
		Rational rational2 = new Rational(13, 11);
		System.out.println("* Comparison to rational_1 (" 
						+ rational1.getString() 
						+ ") with rational_2 (" 
						+ rational2.getString() 
						+ "): " 
						+ rational1.compareTo(rational2));
		
		// Test methods "getRandomArray(int n)"
		System.out.println("\nThe resulting array of random rational number:");
		int countNumb = 10;
		Rational[] arrayRationalNumb = RationalUtils.getRandomArray(countNumb);
		for (int i = 0; i < countNumb; i++) {
			Rational element = arrayRationalNumb[i];
			System.out.println(" " + (i+1) + ")"+ element.getString());
		}
	}
}
