package familymapapp.UTIL;

/**
 * Created by kittykatt on 12/5/17.
 */

public class Triplet<F,S,T> {
    private F first;
    private S second;
    private T third;

    public Triplet(F first, S second, T third){
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public F getFirst(){
        return this.first;
    }

    public S getSecond(){
        return this.second;
    }

    public T getThird(){
        return this.third;
    }
}
