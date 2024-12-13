package ObjectInilaizer.src;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

public class ObjectAnalyzer{

  static public void main (String[] args){

      try{

          Class<?> foo = Class.forName("Foo");
          Class<?> foosuperClass = foo.getSuperclass();
          System.out.println("foo super class : "+ foosuperClass.getName() );

          System.out.println("foo modifier : "+ Modifier.toString(foo.getModifiers()));

          Class<?>[] fooInterface = foo.getInterfaces();
          for(Class<?> currInterface : fooInterface){
              System.out.println("interface: "+currInterface.getName());
          }

          Field[] foofields = foo.getDeclaredFields();
          for(Field field : foofields){
              System.out.println("foo feild : "+field.getName()+" "+field.getType());
          }

          Constructor<?>[] constructors = foo.getDeclaredConstructors();
          for(Constructor<?> Constructor : constructors){
              System.out.println("foo constractor : "+Constructor.getName()+" "+Modifier.toString(Constructor.getModifiers()));
          }

          Constructor<?> constructor = foo.getConstructor();
          Foo fooInstance = (Foo)constructor.newInstance();

          Method f1 = foo.getMethod("f1");
          f1.invoke(fooInstance);

      } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException |
               IllegalAccessException e) {
          throw new RuntimeException(e);
      }
  }
}

