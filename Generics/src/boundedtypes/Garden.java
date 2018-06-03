package boundedtypes;

public class Garden<T extends Plant> {
    
    // instance variable to hold one plant
    private T t;
    
    public Garden(T t) {
	this.t = t;
    }
    
    public T getT() {
	return this.t;
    }
}
