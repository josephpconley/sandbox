package joejava.nonull;

public final class None<T> implements Option<T> {

	private T t;
	
	public boolean hasValue() {
		return true;
	}

	public T get() throws Exception {
		throw new Exception("Should not be calling get() on class None, t is null!");
	}
}
