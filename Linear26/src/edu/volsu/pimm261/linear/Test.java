package src.edu.volsu.pimm261.linear;

import java.util.ArrayList;

public class Test {

	public static void main (String[] args) {
		// t6();

		// testBoshchenko();
	    testBoshchenko2();
	}

	public static void t1() {
		Rational r = new Rational(2);
		Rational s = new Rational(1,2);
		System.out.println(r.getInverse());
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
		System.out.println(s);
	}

// 	public static void testBoshchenko() {
// 		System.out.println("\n### Test methods Boshchenko");
//
// 		// Test methods "compareTo(Rational that)"
// 		Rational rational1 = new Rational(13, 11);
// 		Rational rational2 = new Rational(13, 11);
// 		System.out.println("* Comparison to rational_1 ("
// 						+ rational1
// 						+ ") with rational_2 ("
// 						+ rational2
// 						+ "): "
// 						+ rational1.compareTo(rational2));
//
// 		// Test methods "getRandomArray(int n)"
// 		System.out.println("\nThe resulting array of random rational number:");
// 		int countNumb = 10;
// 		Rational[] arrayRationalNumb = RationalUtils.getRandomArray(countNumb);
// 		for (int i = 0; i < countNumb; i++) {
// 			Rational element = arrayRationalNumb[i];
// 			System.out.println(" " + (i+1) + ")"+ element);
// 		}
// 	}

// 	public static void t5() {
// 		VectorSpace<Rational> vs = new VectorSpace<Rational>("1/2;-2; -12/13", (s) -> new Rational(s));
// 		System.out.println(vs);
// 	}
//
// 	public static void t6() {
// 		VectorSpace<Rational> v1 = new VectorSpace<Rational>("1/5;1/2;1/3", (s) -> new Rational(s));
// 		VectorSpace<Rational> v2 = new VectorSpace<Rational>("3;2;4", (s) -> new Rational(s));
// 		System.out.println(VectorSpace.scalarProduct(v1, v2));
// 	}

	public static void testBoshchenko2() {
        // 1) исходный вектор размерности 3
        // VectorSpace<Rational> v =
        //     new VectorSpace<Rational>("1;2;3", s -> new Rational(s));
        ArrayList<Rational> arr = new ArrayList<>();
        arr.add(new Rational(13,4));
        arr.add(new Rational(12,4));
        arr.add(new Rational(5,5));

        VectorSpace<Rational> v = new VectorSpace<>(arr);

        // 2) строим константный вектор со значением 1/2
        VectorSpace<Rational> c = v.getConstantVector(new Rational(1, 2));

        // 3) печатаем результат
        System.out.println("Исходный:   " + v);   // 1, 2, 3
        System.out.println("Константа:  " + c);   // 1/2, 1/2, 1/2

        // 4) проверяем, что размерность сохранилась
        System.out.println("dim = " + c.getDimension()); // 3

        // 5) проверяем, что исходный вектор не изменился
        System.out.println("Исходный после: " + v);      // 1, 2, 3
	}

}
