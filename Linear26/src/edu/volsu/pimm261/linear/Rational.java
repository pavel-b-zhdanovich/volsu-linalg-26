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
	return (float) numerator / denominator;
}




public Rational(String s) {
	// Татаров Никита парсинг строкового представления дроби или целого числа
	String[] parts = s.split("/");
	numerator = Integer.parseInt(parts[0].trim());
	denominator = (parts.length > 1) ? Integer.parseInt(parts[1].trim()) : 1;
	reduce();
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

// Лебедев Антон
public Rational add(Rational that) {
	int new_denominator = Math.abs(this.denominator / gcd(this.denominator, that.denominator) * that.denominator);
	int left_coef = new_denominator / this.denominator;
	int right_coef = new_denominator / that.denominator;
	return new Rational(this.numerator * left_coef + that.numerator * right_coef, new_denominator);
}

public Rational getOpposite(){
	return new Rational(-this.numerator,this.denominator);
}

public Rational diff(Rational that) {
	// TODO разность
	return null;
}

//Горох Алексей
public Rational mult(Rational that) {
	return new Rational(this.numerator * that.numerator, this.denominator * that.denominator);
}

public Rational getInverse() {
	return new Rational(this.denominator, this.numerator);
}

public Rational div(Rational that) {
	// TODO частное
	return null;
}

public int compareTo(Rational that) {
	return 0; // TODO Сравнение двух чисел
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
