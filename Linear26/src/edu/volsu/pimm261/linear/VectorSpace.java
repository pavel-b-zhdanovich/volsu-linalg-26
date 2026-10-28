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
		//TODO Найти первую ненулевую координату вектора, начиная с номера i (Бощенко ДА)
		//TODO Проверка двух векторов на навенство (Бощенко ДА)
		//TODO Получить из данного вектора новый вектор с переставленными i-й и j-й координатами
		//TODO Проверить, все ли координаты вектора неотрицательны
		//TODO Проверить, является ли вектор i-м базисным вектором (0<= i < dim)
		//TODO Проверить, является ли вектор нулевым
		//TODO Создать вектор-константу (все координаты одинаковы)

	
	// _DONE_TODO_ Найти первую ненулевую координату вектора, начиная с номера i | 28.10.2026 | Бощенко ДА
    // Возвращает индекс первой ненулевой координаты или -1, если такой не нашлось
    public int findFirstNonZeroComponentFrom(int i) {
        if (i < 0 || i >= this.dimension)
            throw new IndexOutOfBoundsException("Индекс выходят за пределы размерности вектора");
		
        for (int k = i; k < this.dimension; k++) {
            T element = this.getAt(k);
            // В поле элемент является нулевым, если элемент плюс его противоположный равен ему самому, 
            // либо если элемент равен разности самого себя x.diff(x) => 0
            T zero = element.diff(element); 
            if (!element.equals(zero)) { 
                // Возвращаем индекс компоненты
				return k;
			}
        }

		// Если все координаты с индекса i нулевые
        return -1;
    }

    // _DONE_TODO_ Проверка двух векторов на равенство | 28.10.2026 | Бощенко ДА
    public boolean equalsVector(VectorSpace<T> that) {
        if (that == null) return false;
		
        // Если размерности векторов не совпадают, они не равны
        if (!this.dimension.equals(
				that.getDimension() != null ? that.getDimension() : that.dimension)
			   )
            return false;

        // Покомпонентное сравнение
        for (int i = 0; i < this.dimension; i++)
            if (!this.getAt(i).equals(
					that.getAt(i))
				)
                return false;
		
		// Ошибок не обнаружено, значит векторы равны
        return true;
    }

} 
