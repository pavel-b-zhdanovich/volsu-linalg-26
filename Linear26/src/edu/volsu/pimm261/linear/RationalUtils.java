package src.edu.volsu.pimm261.linear;

public class RationalUtils {
	
	public static Integer rnd(Integer a, Integer b) {
		return Math.round((float)(Math.random()*(b-a)+a));
	}
	
	public static Rational getRandom(Integer a, Integer b, Integer den_digits) {
		Integer l = Math.round((float)Math.pow(10, den_digits-1));
		Integer r = l*10-1;
		Integer q = rnd(l,r);
		return new Rational(rnd(a*q, b*q),q);
		
		// a <= p/q <=b
		// aq <= p <= bq
		// 
		// TODO Создать случайное рациональное число a <= q <= b,
		// имеющее не более den_digits десятичных знаков в знаменателе
	}

	public static Rational add(Rational s, Rational t) {
		return null; //TODO
	}

	public static Rational add(String s, String t) {
		return null; //TODO
	}

	public static Rational diff(Rational s, Rational t) {
		return null; //TODO
	}

	public static Rational mult(Rational s, Rational t) {
		return null; //TODO
	}

	public static Rational div(Rational s, Rational t) {
		return null; //TODO
	}

	public static Rational[] getRandomArray(int n) {
		Rational[] result = new Rational[n];
		//TODO создать массив случайных чисел
		return result;
	}
	
	public static Rational[] getConstantArray(int n, Rational c) {
		Rational[] result = new Rational[n];
		//TODO создать массив, где каждый элемент равен с
		return result;
	}

	// Лебедев Антон
	public static Rational sum(Rational [] arr) {
		Rational result = new Rational();
		if(arr != null){
			for(Rational elem : arr){
				if (elem != null){
					result = result.add(elem);
				}
			}
		}
		return result;
	}

	public static Rational min(Rational [] arr) {
		return null; //TODO наименьший элемент массива
	}

	public static Rational max(Rational [] arr) {
		return null; //TODO наибольшие элемент массива
	}

	public static Rational avg(Rational [] arr) {
		return null; //TODO среднее арифметическое всех элементов массива
	}

	public static void sort(Rational [] arr) {
		 //TODO сортировка массива на месте, не создавая нового массива
	}

	public static boolean isZero(Rational [] arr) {
		return false; //TODO проверить, что  массив состоит только из нулей
	}

	
}
