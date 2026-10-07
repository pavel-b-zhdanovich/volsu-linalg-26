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
		return new Rational(s).add(new Rational(t))
		//TODO Татаров Никита
	}

	public static Rational diff(Rational s, Rational t) {
		int numerator = s.getNumerator() * t.getDenominator() - t.getNumerator() * s.getDenominator();
		int denominator = s.getDenominator() * t.getDenominator();
		return new Rational(numerator, denominator);
	}
	
	//Горох Алексей
	public static Rational mult(Rational s, Rational t) {
		return s.mult(t);
	}

	public static Rational div(Rational s, Rational t) {
		if (t.getNumerator() == 0)
		{
        	throw new ArithmeticException("Деление на ноль");
    	}

    	int numerator = s.getNumerator() * t.getDenominator();
    	int denominator = s.getDenominator() * t.getNumerator();

    	return new Rational(numerator, denominator);
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
	if (arr == null || arr.length == 0)
		throw new IllegalArgumentException("empty array");

	// сумма считается через BigInteger, чтобы не ловить переполнение int
	BigInteger num = BigInteger.ZERO;
	BigInteger den = BigInteger.ONE;
	for (Rational r : arr) {
		BigInteger rn = BigInteger.valueOf(r.getNumerator());
		BigInteger rd = BigInteger.valueOf(r.getDenominator());
		num = num.multiply(rd).add(rn.multiply(den));
		den = den.multiply(rd);
		BigInteger g = num.gcd(den);   // den > 0, значит g >= 1
		num = num.divide(g);
		den = den.divide(g);
	}
	// делим сумму на количество элементов
	den = den.multiply(BigInteger.valueOf(arr.length));
	BigInteger g = num.gcd(den);
	num = num.divide(g);
	den = den.divide(g);

	return new Rational(num.intValueExact(), den.intValueExact());
}

	public static void sort(Rational [] arr) {
		 //TODO сортировка массива на месте, не создавая нового массива
	}

	public static boolean isZero(Rational [] arr) {
		return false; //TODO проверить, что  массив состоит только из нулей
	}

	
}
