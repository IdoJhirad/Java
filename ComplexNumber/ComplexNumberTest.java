package co.il.ilrd.ComplexNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ComplexNumberTest {
    ComplexNumber number;

    @BeforeEach
    void setUp() {
         number =new ComplexNumber(15,20);
    }

    @Test
    void getReal() {
        assertEquals(15,number.getReal(),"expected 15");
    }

    @Test
    void setReal() {
        assertEquals(15,number.getReal(),"expected 15");
        number.setReal(25);
        assertEquals(25,number.getReal(),"expected 25");
        number.setReal(0);
        assertEquals(0,number.getReal(),"expected 0");
    }

    @Test
    void getImaginary() {
        assertEquals(20,number.getImaginary(),"expected 20");
        number =new ComplexNumber(15,0);
        assertEquals(0,number.getImaginary(),"expected 0");
    }

    @Test
    void setImaginary() {
        assertEquals(20,number.getImaginary(),"expected 20");
        number.setImaginary(50);
        assertEquals(50,number.getImaginary(),"expected 50");
        number.setImaginary(0);
        assertEquals(0,number.getImaginary(),"expected 0");

    }

    @Test
    void setValue() {
        number.setValue(15,50);
        assertEquals(50,number.getImaginary(),"expected 50");
        assertEquals(15,number.getReal(),"expected 15");
        number.setValue(0,0);
        assertEquals(0,number.getImaginary(),"expected 0");
        assertEquals(0,number.getReal(),"expected 0");
        number.setValue(500,7000);
        assertEquals(500,number.getReal(),"expected 500");
        assertEquals(7000,number.getImaginary(),"expected 7000");
    }

    @Test
    void isReal() {
        assertFalse(number.isReal(),"should be false");
        number.setValue(800,0);
        assertTrue(number.isReal(),"should be real");
        number.setValue(800,0);
        assertTrue(number.isReal(),"should be real");
    }

    @Test
    void isImaginary() {
        assertTrue(number.isImaginary(),"should be imaginary");
        number.setValue(800,0);
        assertFalse(number.isImaginary(),"should not be imaginary");
        number.setValue(800,7000);
        assertTrue(number.isImaginary(),"should be imaginary");
    }

    @Test
    void add(){
        ComplexNumber toAdd = new ComplexNumber(20,50);
        ComplexNumber theNew = number.add(toAdd);
        assertEquals(70,theNew.getImaginary(),"expected 70");
        assertEquals(35,theNew.getReal(),"expected 35");
    }

    @Test
    void subtract() {
        ComplexNumber toSubtract = new ComplexNumber(15,20);
        ComplexNumber theNew = number.subtract(toSubtract);
        assertEquals(0,theNew.getImaginary(),"expected 0");
        assertEquals(0,theNew.getReal(),"expected 0");
    }

    @Test
    void multipleWith() {
        ComplexNumber toMultiple = new ComplexNumber(15,20);
        ComplexNumber res = number.multipleWith(toMultiple);
        assertEquals(-175,res.getReal(),0.5);
        assertEquals(600,res.getImaginary(),0.5);


    }

    @Test
    void divideBy() {
        ComplexNumber toDivide = new ComplexNumber(1,1);
        ComplexNumber res = number.divideBy(toDivide);
        assertEquals(17.5,res.getReal(),1);
        assertEquals(2.5,res.getImaginary(),1);


    }

    @Test
    void parse() {
        ComplexNumber parsComplex = ComplexNumber.parse("15+14i");
        assert parsComplex != null;
        assertEquals(14,parsComplex.getImaginary(),"expected 14");
        assertEquals(15,parsComplex.getReal(),"expected 014");
         parsComplex = ComplexNumber.parse("385.15+3248.5i");
        assert parsComplex != null;
        assertEquals(385.15,parsComplex.getReal(),"expected 385.15");
        assertEquals(3248.5,parsComplex.getImaginary(),"expected 014");
    }

    @Test
    void testEquals() {
        ComplexNumber toCheck = new ComplexNumber(15,20);
        assertTrue(number.equals(toCheck),"should bt true");
        //symmetric
        assertTrue(toCheck.equals(number),"should bt true symmetric tst");
        //reflexive
        assertTrue(toCheck.equals(toCheck),"should bt true reflexive test");
        toCheck.setValue(1,12);
        assertFalse(toCheck.equals(number),"should bt false");
        assertFalse(number.equals(toCheck),"should bt false");

    }

    @Test
    void testHashCode() {
        ComplexNumber toCheck = new ComplexNumber(15,20);
        assertEquals(toCheck.hashCode(), number.hashCode());
    }

    @Test
    void compareTo() {
        ComplexNumber toCheck = new ComplexNumber(15,20);
        assertEquals(0,number.compareTo(toCheck));
        toCheck.setValue(1,15);
        assertEquals(1,number.compareTo(toCheck));
        toCheck.setValue(35,72);
        assertEquals(-1,number.compareTo(toCheck));
    }

    @Test
    void testToString() {
        String numberString = number.toString();
        ComplexNumber second = new ComplexNumber(15,20);
        String secondString = second.toString();
        assertEquals(secondString, numberString);
    }
}