package src.edu.volsu.pimm261.linear;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.IntStream;

public class VectorSpace<T extends IField<T>> {
	protected ArrayList<T> components;
	private Integer dimension=1;

	public T scalarProduct() {
		return null;
	}
	
	public T getAt(Integer i) {
		return this.components.get(i);
	}
	
	interface ImyAction<T>{
		public T getValue(Integer i);
	}
	
	
	public VectorSpace<T> buildVector (ImyAction<T> action){
		ArrayList<T> arr = new ArrayList<T>(dimension);
		for (int i = 0; i < dimension; i++) 
			arr.add( action.getValue(i) );
		return new VectorSpace<T>(arr);	
	}
	
	public VectorSpace<T> add1 (VectorSpace<T> that){
		ArrayList<T> arr = new ArrayList<T>(dimension);
		for (int i = 0; i < dimension; i++) 
			arr.add(this.getAt(i).add(that.getAt(i)));
		return new VectorSpace<T>(arr);	
	}
	
	public VectorSpace<T> add2 (VectorSpace<T> that){
		class MyAction implements ImyAction<T>{
			@Override
			public T getValue(Integer i) {
				return getAt(i).add(that.getAt(i));
			}
		}
		return buildVector( new MyAction());
	}
	
	public VectorSpace<T> add3(VectorSpace<T> that){
		ImyAction<T> myAction = new ImyAction<T>(){
			public T getValue(Integer i) {
				return getAt(i).add(that.getAt(i));
			}
		};
		return buildVector(myAction);
	}
	
	public VectorSpace<T> add4(VectorSpace<T> that){
		ImyAction<T> myAction = i -> getAt(i).add(that.getAt(i));
		return buildVector(myAction);
	}
	
	public VectorSpace<T> add(VectorSpace<T> that){
		return buildVector(i -> getAt(i).add(that.getAt(i)));
	}
	
	public VectorSpace<T> getOpposite1() {
		ArrayList<T> arr = new ArrayList<T>(dimension);
		for (int i = 0; i < dimension; i++) 
			arr.add(this.getAt(i).getOpposite());
		return new VectorSpace<T>(arr);
	}
	
	public VectorSpace<T> getOpposite() {
		return buildVector(i -> this.getAt(i).getOpposite());
	}
	
	public VectorSpace<T> diff1(VectorSpace<T> that) {
		ArrayList<T> arr = new ArrayList<T>(dimension);
		for (int i = 0; i < dimension; i++) 
			arr.add(this.getAt(i).diff(that.getAt(i)));
		return new VectorSpace<T>(arr);
	}
	
	public VectorSpace<T> diff(VectorSpace<T> that) {
		return buildVector(i ->this.getAt(i).diff(that.getAt(i)));
	}
	
	public VectorSpace<T> multBy(T quotient) {
		ArrayList<T> arr = new ArrayList<T>(dimension);
		for (int i = 0; i < dimension; i++) 
			arr.add(this.getAt(i).mult(quotient));
		return new VectorSpace<T>(arr);
	}
	
	public Integer getDimension() {
		return null;
	}
	
	
	protected VectorSpace (ArrayList<T> arr) {						
		this.components = arr;
		this.dimension = arr.size();
}

	
	
	public VectorSpace() {
		
	}
//+++++++++++++++++++++++++++++++++++++++++++ Контрольная работа ++++++++++++++++++++++++++++	
	public VectorSpace(String str) {
		// 2/4;3/4;3+4i
	}
	
	public String toString() {
		return null;
	}
	
	public static <T extends IField<T> & Comparable<T>> T scalarProduct(VectorSpace<T> v1, VectorSpace<T> v2){
		//TODO скалярное произведение векторов над линейно упорядоченным полем
		return null;
	}
		//TODO норма вектора над линейно упорядоченным полем
		//TODO длина вектора над линейно упорядоченным полем
		//TODO орт вектора над линейно упорядоченным полем
		//TODO клонирование
		//TODO Найти первую ненулевую координату вектора, начиная с номера i
		//TODO Проверка двух векторов на навенство
		//TODO Получить из данного вектора новый вектор с переставленными i-й и j-й координатами
		//TODO Проверить, все ли координаты вектора неотрицательны
		//TODO Проверить, является ли вектор i-м базисным вектором (0<= i < dim)
		//TODO Проверить, является ли вектор нулевым
		//TODO Создать вектор-константу (все координаты одинаковы


ПАМБУХЧЯН АЙК
	// Проверить, является ли вектор i-м базисным вектором (0 <= i < dim)
// Базисный вектор: все координаты нулевые, кроме i-й, которая равна единице поля.
public boolean isBasisVector(int i) {
    if (i < 0 || i >= this.dimension) {
        return false;
    }
    T zero = this.getAt(0).diff(this.getAt(0)); // ноль поля: x - x

    for (int k = 0; k < this.dimension; k++) {
        T x = this.getAt(k);
        if (k == i) {
            
            boolean isNonZero    = !x.diff(zero).equals(zero);
            boolean isIdempotent = x.mult(x).diff(x).equals(zero);
            if (!isNonZero || !isIdempotent) {
                return false;
            }
        } else {
            // координата должна быть нулём поля
            if (!x.diff(zero).equals(zero)) {
                return false;
            }
        }
    }
    return true;
}

// Проверить, является ли вектор нулевым
// Все координаты равны нулю поля.
public boolean isZeroVector() {
    T zero = this.getAt(0).diff(this.getAt(0)); // ноль поля: x - x
    for (int k = 0; k < this.dimension; k++) {
        if (!this.getAt(k).diff(zero).equals(zero)) {
            return false;
        }
    }
    return true;
}
}
