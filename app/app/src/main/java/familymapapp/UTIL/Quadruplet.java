package familymapapp.UTIL;

/**
 * Created by kittykatt on 12/5/17.
 */

public class Quadruplet<F,S,T,FO> {
    private F first;
    private S second;
    private T third;
    private FO fourth;

    public Quadruplet(F first, S second, T third, FO fourth){
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
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

    public FO getFourth(){
        return this.fourth;
    }
}
