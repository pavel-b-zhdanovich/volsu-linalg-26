package src.edu.volsu.pimm261.linear;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.Arrays;
import java.util.Scanner;

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

	//TODO Получить из данного вектора новый вектор с переставленными i-й и j-й координатами // Ivanov Timur // Start
	public class VectorSwap {

    	/**
     	* Возвращает новый вектор, в котором i-я и j-я координаты переставлены.
     	* Исходный вектор не изменяется.
     	*
     	* @param vector исходный вектор
     	* @param i индекс первой координаты (0-based)
     	* @param j индекс второй координаты (0-based)
     	* @return новый вектор с переставленными координатами
     	*/
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
	//TODO Получить из данного вектора новый вектор с переставленными i-й и j-й координатами // Ivanov Timur // End

	//TODO Проверить, все ли координаты вектора неотрицательны // Ivanov Timur // Start
	public class VectorCheck {
	
	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	
	        // Ввод размерности вектора
	        System.out.print("Введите количество координат вектора: ");
	        int n = scanner.nextInt();
	
	        // Ввод координат
	        int[] vector = new int[n];
	        System.out.println("Введите координаты вектора:");
	        for (int i = 0; i < n; i++) {
	            System.out.print("Координата " + (i + 1) + ": ");
	            vector[i] = scanner.nextInt();
	        }
	
	        // Проверка всех координат
	        boolean allNonNegative = true;
	        int badIndex = -1;
	
	        for (int i = 0; i < n; i++) {
	            if (vector[i] < 0) {
	                allNonNegative = false;
	                badIndex = i;
	                break;
	            }
	        }
	
	        // Вывод результата
	        if (allNonNegative) {
	            System.out.println("Все координаты вектора неотрицательны.");
	        } else {
	            System.out.println("Вектор содержит отрицательную координату: " +
	                    "индекс " + badIndex + ", значение " + vector[badIndex]);
	        }
	
	        scanner.close();
	    }
	}
	//TODO Проверить, все ли координаты вектора неотрицательны // Ivanov Timur // End
	
} 
