package generic;

public class FooReference <T> {
   private T obj ;
    public FooReference(T obj){
        this.obj = obj;
    }
    public FooReference(){
    }
    public  T get(){
        return obj;
    }
    public void set( T obj){
        this.obj = obj; // when to use
    }
}
class Main{
    static  public void main(String[] args){
        FooReference<String> fooS = new FooReference<>();
        fooS.set("ido jhirad");
        System.out.println(fooS.get());
        FooReference<Integer> fooI = new FooReference<>();
        fooI.set(58);
        System.out.println(fooI.get());
        fooS.set("hhjb");
        System.out.println(fooS.get());
       // fooS.set(58);  java: incompatible types: int cannot be converted to java.lang.String
        System.out.println("fooS.get() runtime class: " + fooS.get().getClass());
        System.out.println("fooS class: " + fooI.getClass());
        System.out.println("fooI.get() runtime class: " + fooS.get().getClass());
        System.out.println("fooI class: " + fooI.getClass());
        System.out.println(fooS);
        System.out.println(fooS.toString());


    }
}
