package sort;

/**
 * Created by I312177 on 11/13/2015.
 */
public class Elem implements Comparable<Elem>{
    private int key=0;

    public Elem(int key){
        this.key=key;
    }
    public int key(){
        return key;
    }

    @Override
    public boolean equals(Object e){
        if(e instanceof Elem){
            Elem e1 = (Elem)e;
            return key==e1.key;
        }
        return false;

    }

    @Override
    public String toString(){
        return key+"";
    }


    public int compareTo(Elem o) {
        return key-o.key();
    }

    public int compareTo(int o) {
        return key-o;
    }

}
