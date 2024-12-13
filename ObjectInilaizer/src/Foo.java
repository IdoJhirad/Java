package ObjectInilaizer.src;

public class Foo {
    private int value;
    private String name;

    // Default constructor
    public Foo() {
        this.value = 42;
        this.name = "Default Foo";
    }

    // Constructor with arguments
    public Foo(int value, String name) {
        this.value = value;
        this.name = name;
    }

    // A simple method to invoke via reflection
    public void f1() {
        System.out.println("Method f1() in Foo is called. Value: " + value + ", Name: " + name);
    }

    // Getters and Setters
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
