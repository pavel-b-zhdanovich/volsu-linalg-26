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
		

//TODO орт вектора над линейно упорядоченным полем (Бакланов Иван). 28.10.2026
//TODO клонирование (Бакланов Иван). 28.10.2026
/*
 * Возвращает орт — вектор с длиной 1.
 * toDouble — преобразование координаты T в double.
 * fromDouble — создание координаты T из double.
 * Вычисление приближённое, с точностью double.
 */
public VectorSpace<T> ort(
        java.util.function.ToDoubleFunction<? super T> toDouble,
        java.util.function.DoubleFunction<T> fromDouble) {

    java.util.Objects.requireNonNull(toDouble, "Не задан toDouble");
    java.util.Objects.requireNonNull(fromDouble, "Не задан fromDouble");

    if (components == null) {
        throw new IllegalStateException("Координаты не инициализированы");
    }

    final int size = components.size();
    double length = 0.0;

    for (int i = 0; i < size; i++) {
        double coordinate = toDouble.applyAsDouble(components.get(i));
        length = Math.hypot(length, coordinate);
    }

    if (length == 0.0) {
        throw new ArithmeticException("У нулевого вектора нет орта");
    }

    if (!Double.isFinite(length)) {
        throw new ArithmeticException(
                "Длина вектора не представима конечным числом double"
        );
    }

    ArrayList<T> result = new ArrayList<>(size);

    for (int i = 0; i < size; i++) {
        double coordinate = toDouble.applyAsDouble(components.get(i));
        result.add(fromDouble.apply(coordinate / length));
    }

    return new VectorSpace<>(result);
}

/*
 * Создаёт новый вектор с отдельным списком координат.
 * Сами объекты координат не копируются.
 * Подходит для неизменяемых элементов поля.
 */
@Override
public VectorSpace<T> clone() {
    if (components == null) {
        throw new IllegalStateException("Координаты не инициализированы");
    }

    return new VectorSpace<>(new ArrayList<>(components));
}

/*
 * Клонирование с копированием каждой координаты.
 * Используется, если элементы поля могут изменяться.
 * copyCoordinate должна возвращать независимую копию координаты.
 */
public VectorSpace<T> clone(
        java.util.function.Function<? super T, ? extends T> copyCoordinate) {

    java.util.Objects.requireNonNull(
            copyCoordinate, "Не задан способ копирования координаты"
    );

    if (components == null) {
        throw new IllegalStateException("Координаты не инициализированы");
    }

    final int size = components.size();
    ArrayList<T> result = new ArrayList<>(size);

    for (int i = 0; i < size; i++) {
        result.add(copyCoordinate.apply(components.get(i)));
    }

    return new VectorSpace<>(result);
}
} 