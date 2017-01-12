package joejava.nonull;

public interface Option<T> {
	public boolean hasValue();
	T get() throws Exception;
}
