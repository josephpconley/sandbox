package joejava.nonull;

public final class Some<T> implements Option<T> {

	private T t;
	
	public boolean hasValue() {
		return true;
	}

	public T get() {
		return t;
	}
}
