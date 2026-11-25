package src.edu.volsu.pimm261.linear;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.Arrays;
import java.util.Scanner;

public class VectorSpace<T extends IField<T>> {
	protected ArrayList<T> components;
	private Integer dimension=1;

	public T getAt(Integer i) {
		return this.components.get(i);
	}
	
	interface ImyAction<T>{
		public T getValue(Integer i);
	}
	
	
	public VectorSpace<T> buildVectorOld (ImyAction<T> action){
		ArrayList<T> arr = new ArrayList<T>(dimension);
		for (int i = 0; i < dimension; i++) 
			arr.add( action.getValue(i) );
		return new VectorSpace<T>(arr);	
	}
	
	public VectorSpace<T> buildVector ( IntFunction<T> action){
		ArrayList<T> arr = new ArrayList<T>(dimension);
		for (int i = 0; i < dimension; i++) 
			arr.add( action.apply(i) );
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
		return buildVectorOld( new MyAction());
	}
	
	public VectorSpace<T> add3(VectorSpace<T> that){
		ImyAction<T> myAction = new ImyAction<T>(){
			public T getValue(Integer i) {
				return getAt(i).add(that.getAt(i));
			}
		};
		return buildVectorOld(myAction);
	}
	
	public VectorSpace<T> add4(VectorSpace<T> that){
		ImyAction<T> myAction = i -> getAt(i).add(that.getAt(i));
		return buildVectorOld(myAction);
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
//		ArrayList<T> arr = new ArrayList<T>(dimension);
//		for (int i = 0; i < dimension; i++) 
//			arr.add(this.getAt(i).mult(quotient));
//		return new VectorSpace<T>(arr);
	
		return buildVector(i -> this.getAt(i).mult(quotient));
	}
	
	public Integer getDimension() {
		return this.dimension;
	}
	
	
	protected VectorSpace (ArrayList<T> arr) {						
		this.components = arr;
		this.dimension = arr.size();
}

	

//+++++++++++++++++++++++++++++++++++++++++++ Контрольная работа ++++++++++++++++++++++++++++	
	public VectorSpace(String str, Function<String,T> parser ) {
		String[] parts = str.split(";");
		this.dimension = parts.length;
	
		components = new ArrayList<T>(this.dimension);
		for (int i = 0; i < this.dimension; i++) {
			this.components.add(parser.apply(parts[i]));
		}
	}
	
	public String toString() {
		String st="";
		int d = dimension-1;
		for (int i = 0; i < d; i++) {
			st=st+this.components.get(i)+", ";
		}
		return st+this.components.get(d);
	}
	
	public static <T extends IField<T> & Comparable<T>> T scalarProduct(VectorSpace<T> v1, VectorSpace<T> v2){
	
		int v1Dimm = v1.getDimension(); 
		int v2Dimm = v2.getDimension();
		if (v1Dimm != v2Dimm) {
			System.err.println("W -- Vectors of different dimensions!");
			new RuntimeException().printStackTrace();
		}
		T result = v1.getAt(0).getZero();
		int m = ((v1Dimm < v2Dimm)? v1Dimm : v2Dimm);
		for(int i = 0 ; i < m ; i++) {
			result = result.add(v1.getAt(i).mult(v2.getAt(i)));
		}
		return result;
	}

	/**
 * Проверяет, является ли вектор нулевым (все координаты равны нулю) Памбухчян */
public boolean isZero() {
	for (int i = 0; i < dimension; i++) {
		if (!this.getAt(i).isZero()) {
			return false;
		}
	}
	return true;
}
		
	//++++++++++++++TODO длина вектора над линейно упорядоченным полем (Суровкин Никита)
			/**
			 * Сумма квадратов координат:
			 * N(v) = v[0]^2 + v[1]^2 + ... + v[n - 1]^2.
			 *
			 * Результат остаётся элементом поля T.
			 */
			public static <T extends IField<T> & Comparable<T>>
			T norm(VectorSpace<T> vector) {
				if (vector == null || vector.components == null) {
					throw new IllegalArgumentException(
							"Вектор должен быть создан и заполнен"
					);
				}

				final int size = vector.components.size();

				if (size == 0) {
					// Без интерфейса IField неизвестно,
					// как создать нулевой элемент типа T.
					throw new IllegalArgumentException(
							"Для этого метода требуется непустой вектор"
					);
				}

				T coordinate = vector.components.get(0);
				T result = coordinate.mult(coordinate);

				for (int i = 1; i < size; i++) {
					coordinate = vector.components.get(i);
					result = result.add(coordinate.mult(coordinate));
				}

				return result;
			}
//TODO Проверить, все ли координаты вектора неотрицательны, Татаров Никита
			public <U extends T & Comparable<T>> boolean isNonNegative() {
	for (T c : components) {
		if (((U) c).compareTo(c.getZero()) < 0) return false;
	}
	return true;
}
			/**
			 * Евклидова длина вектора.
			 *
			 * toDouble задаёт преобразование координаты T в double.
			 * Math.hypot устойчив к промежуточному переполнению
			 * при вычислении суммы квадратов.
			 */
//			public static <T extends IField<T> & Comparable<T>>
//			double length(
//					VectorSpace<T> vector,
//					ToDoubleFunction<? super T> toDouble) {
//
//				if (vector == null || vector.components == null) {
//					throw new IllegalArgumentException(
//							"Вектор должен быть создан и заполнен"
//					);
//				}
//
//				if (toDouble == null) {
//					throw new IllegalArgumentException(
//							"Нужно указать преобразование координаты в double"
//					);
//				}
//
//				final int size = vector.components.size();
//				double result = 0.0;
//
//				for (int i = 0; i < size; i++) {
//					double coordinate =
//							toDouble.applyAsDouble(vector.components.get(i));
//
//					result = Math.hypot(result, coordinate);
//				}
//
//				return result;
//			}
	
	
		//TODO клонирование
	
	
	//TODO Найти первую ненулевую координату вектора, начиная с номера i
	//Горох Алексей
	public Integer firstNotZeroCoordSinceNumberI(int i){
		if (i < 1) i = 1;                      // координаты нумеруются с 1
		for (int k = i - 1; k < dimension; k++) {  // k — индекс с 0
			T comp = components.get(k);
			if (!comp.equals(comp.getZero())) {
				return k + 1;                  // вернуть номер с 1
			}
		}
		return -1;
	}
	// Вариант Б, где возвращаем не позицию, А само ненулевое значение
	public T firstNotZeroCoordValueSinceNumberI(int i) {
		if (i < 1) i = 1;
		for (int k = i - 1; k < dimension; k++) {
			T comp = components.get(k);
			if (!comp.equals(comp.getZero())) {
				return comp;
			}
		}
		return null;
	}
	
		//TODO Проверка двух векторов на навенство
		//TODO Получить из данного вектора новый вектор с переставленными i-й и j-й координатами - Иванов Тимур (задание на паре)
public class Main {

    
    public static double[] swap(double[] vector, int i, int j) {
        if (vector == null) {
            throw new IllegalArgumentException("Вектор не может быть null");
        }
        if (i < 0 || j < 0 || i >= vector.length || j >= vector.length) {
            throw new IndexOutOfBoundsException(
                    "Индексы должны быть в диапазоне [0, " + (vector.length - 1) + "]"
            );
        }

        double[] result = Arrays.copyOf(vector, vector.length);
        double temp = result[i];
        result[i] = result[j];
        result[j] = temp;
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите размерность вектора: ");
        int n = scanner.nextInt();
        if (n <= 0) {
            System.out.println("Размерность должна быть положительной.");
            return;
        }

        double[] vector = new double[n];
        System.out.println("Введите " + n + " координат(ы) вектора:");
        for (int k = 0; k < n; k++) {
            vector[k] = scanner.nextDouble();
        }

        System.out.print("Введите индекс i (от 0 до " + (n - 1) + "): ");
        int i = scanner.nextInt();
        System.out.print("Введите индекс j (от 0 до " + (n - 1) + "): ");
        int j = scanner.nextInt();

        try {
            double[] swapped = swap(vector, i, j);

            System.out.println("Исходный вектор: " + Arrays.toString(vector));
            System.out.println("Новый вектор: " + Arrays.toString(swapped));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}


		//TODO Проверить, все ли координаты вектора неотрицательны
		//TODO Проверить, является ли вектор i-м базисным вектором (0<= i < dimension)
		//TODO Проверить, является ли вектор нулевым
		//TODO Создать вектор-константу (все координаты одинаковы)
		
		//TODO Прибавить к i-й координате j-ю координату, умноженную на элемент поля (Суровкин Никита)
		public VectorSpace<T> addCoordinate(int i, int j, T coefficient) {
			ArrayList<T> arr = new ArrayList<T>(components);
			arr.set(i, getAt(i).add(getAt(j).mult(coefficient)));
			return new VectorSpace<T>(arr);
		}

		//TODO Дан массив A векторов одной фиксированной размерности и массив C элементов поля. 
			//Размерности A и С совпадают. Найти линейную комбинацию векторов из A со скалярами из C.
		//TODO Создать i-й базисный вектор (у которого на i-м месте стоит 1, а на остальных - нули)
			
		

} 
