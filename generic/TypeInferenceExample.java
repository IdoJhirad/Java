package generic;//package generic;//package generic;
////
////import java.util.ArrayList;
////import java.util.List;
////
////public class Box {
////    private Object object;
////
////    public void set(Object object) { this.object = object; }
////    public Object get() { return object; }
////}
////class Main{
////    public static void main(String [] args ){
//////        Box s = new Box();
//////        Box d = new Box();
//////    int j = 10;
//////        Integer i = 50;
//////        String b = "b";
//////        s.set(b);
//////        s.set(10);
//////        s.set(b);
//////        d.set(j);
//////        System.out.println((""+s.get()));
//////        System.out.println((""+s.get()));
//////        System.out.println((""+d.get()));
//////
//////        d.set("pujfdiushgiudr");
//////        System.out.println((""+d.get()));
//////
//////        List<Integer> li = new ArrayList<>();
//////        List<String> ls = new ArrayList<>();
//////        if (li.getClass() ==ls.getClass())
//////            System.out.println("Equals");
//////
//////            //List<Object> du = new ArrayList<Integer>(); java: incompatible types: java.util.ArrayList<java.lang.Integer> cannot be converted to java.util.List<java.lang.Object>
////
////        List<Integer> v = new ArrayList();
////        v.add("test");
////        Integer i = (Integer) v.get(0);
////    }
////}
//import java.util.ArrayList;
//import java.util.List;
//public class TypeInferenceExample {
////    public static void showList(List<Integer>list){
////        if(!list.isEmpty()){
////            list.forEach(System.out::println);
////        }else System.out.println("list is empty");
////    }
//    public static void printList(List<?> list) {
//        for (Object elem: list)
//            System.out.print(elem + " ");
//        System.out.println();
//    }
//    public static void main(String[] args) {
//        // An old approach(prior to Java 7) to create a list
//        List<Integer> list1 = new ArrayList<Integer>();
//        list1.add(11);
//        //showList(list1);
//        // Java 7
//        List<String> list2 = new ArrayList<>(); // You can left it blank, compiler can infer type
//       list2.add("10");
//        list2.add("546ss");
//
////        list2.add(120);
////
////        // Compiler infers type of ArrayList, in Java 8
//      printList(list2);
//
//    }
//}

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class T {
//    T   obj;
//    public  T foo(List<T> arg){
//        return new T();
    //}

    public static void main(String [] args){

        List rawList;

        List<?> listOfAnyType ;

//        List<Object> listofobject = new ArrayList<Object>();
//
//        List<String> listOfString = new ArrayList<String>();
//
//        List<Integer> listOfInteger = new ArrayList<Integer>();
//
//        rawList = listOfAnyType;
//
//        rawList = listOfString;
//
//        rawList = listOfInteger;
//
//        listOfAnyType = listOfString;
//
//        listOfAnyType = listOfInteger;
//
//        listofobject = (List<Object>) listOfString;
//
//

    }
}