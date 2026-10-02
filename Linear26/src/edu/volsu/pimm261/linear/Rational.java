package src.edu.volsu.pimm261.linear;

public class Rational{
	// p/q, где p целое, q - натуральное, причём  нод(p,q)=1

	protected Integer numerator;
	protected Integer denominator;

	protected void  reduce() {
		if (denominator==0)
			throw new ArithmeticException("divizion by Zero");
		Integer d = gcd(numerator,denominator);
		numerator=numerator/d;
		denominator=denominator/d;
		if(denominator<0) {
			numerator=-numerator;
			denominator=-denominator;
		};
	}



	public Rational(int p, int q) {
		numerator=p;
		denominator=q;
		reduce();
	}

	public Rational(int p) {
		this(p,1);
	}

	public Rational() {
		this(0);
	}

	public String getString() {
		return this.getNumerator()+((this.getDenominator() == 0)?"":"/"+this.getDenominator());
	}

	public Integer gcd(int a,int b) {
		return (b==0)? a : gcd(b, a%b);
	}



	public float toFloat() {
		// TODO Приведение числа к типу float 
		return 0;
	}




	public Rational(String s) {
		// TODO парсинг строкового представления дроби или целого числа
	}


	public Rational setDenominator(Integer q) {
		return new Rational(numerator,q);
	}

	public Rational setNumerator(Integer p) {
		return new Rational(p, denominator);
	}

	public Integer getNumerator() {
		return numerator;
	}

	public Integer getDenominator() {
		return denominator;
	}

	public boolean equals(Object that) {
		if (!(that instanceof Rational)) return false;
		Rational _that = (Rational)that;
		return (this.numerator == _that.numerator)&&(this.denominator == _that.denominator);
	}

	public Rational add(Rational that) {
		//TODO Использовать приведение к общему знаменателю, чтобы избежать арифметических переполнений
		return new Rational(this.numerator*this.denominator+that.numerator*that.denominator, this.denominator*that.denominator);
	}

	public Rational getOpposite(){
		return new Rational(-this.numerator,this.denominator);
	}

	public Rational diff(Rational that) {
		// TODO разность
		return null;
	}


	public Rational mult(Rational that) {
		// TODO произведение
		return null;
	}

	public Rational getInverse() {
		return new Rational(this.denominator, this.numerator);
	}

	public Rational div(Rational that) {
		// TODO частное
		return null;
	}

	// Сравнение двух чисел | 01.10.26 | Бощенко Д.А.
	public int compareTo(Rational that) {	
		long leftSide = (long) this.getNumerator() * that.getDenominator();
		long rightSide = (long) this.getDenominator() * that.getNumerator();

		// TODO Сравнение двух чисел
		return Long.compare(leftSide, rightSide);
	}

	@Override
	public Object clone() {
		return new Rational(this.numerator,this.denominator);
	}


	public boolean isZero() {
		// TODO проверка на равенство нулю
		return false; // TODO
	}

}