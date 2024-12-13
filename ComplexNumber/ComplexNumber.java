package co.il.ilrd.ComplexNumber;
import java.lang.Math;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;  /*Pattern and Matcher*/


public class ComplexNumber implements Comparable<ComplexNumber> {

    private double real;
    private double imaginary;

    // Constructors
    public ComplexNumber(){
        this.imaginary = 0;
        this.real = 0;
    } // initial the real and imaginary to 0
    public ComplexNumber(double real, double imaginary){
        this.imaginary = imaginary;
        this.real =real;
    }

    // Getters and setters
    public double getReal() {
        return real;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public double getImaginary() {
        return imaginary;
    }

    public void setImaginary(double imaginary) {
        this.imaginary = imaginary;
    }

    // Set the complex number
    public void setValue(double real, double imaginary){
        this.real = real;
        this.imaginary = imaginary;
    }

    // Check if the object is real
    public boolean isReal(){
        return Double.compare(imaginary, 0) == 0;
    }

    // Check if the object is imaginary
    public boolean isImaginary(){
            return !isReal();
    }

    // Add between 2 complex numbers
    public ComplexNumber add(ComplexNumber complexNumber){
        ComplexNumber new_complex = new ComplexNumber();
        new_complex.setReal(this.real + complexNumber.getReal());
        new_complex.setImaginary(this.imaginary + complexNumber.getImaginary());

        return new_complex;
    }

    // Subtract between 2 complex numbers
    public ComplexNumber subtract(ComplexNumber complexNumber){
        ComplexNumber new_complex = new ComplexNumber();
        new_complex.setReal(this.real - complexNumber.getReal());
        new_complex.setImaginary(this.imaginary - complexNumber.getImaginary());

        return new_complex;
    }

    // Multiply two complex number
    public ComplexNumber multipleWith(ComplexNumber complexNumber){
        ComplexNumber new_complex = new ComplexNumber();
        double tempReal = (this.real*complexNumber.getReal())-(this.imaginary*complexNumber.getImaginary());
        double tempImag = (this.real*complexNumber.getImaginary())+(this.imaginary*complexNumber.getReal());
        new_complex.setReal(tempReal);
        new_complex.setImaginary(tempImag);

        return new_complex;
    }

    // Divide two complex number - if divide by 0 is undefined
    public ComplexNumber divideBy(ComplexNumber complexNumber){

        double numerator =(this.real * complexNumber.getReal())+ this.imaginary*complexNumber.getImaginary();
        double denominator =(complexNumber.getReal()*complexNumber.getReal() + complexNumber.getImaginary()*complexNumber.getImaginary());
        double real = numerator/denominator;

        numerator = (this.imaginary*complexNumber.getReal())-(this.real*complexNumber.getImaginary());
        denominator =(complexNumber.getReal()*complexNumber.getReal()+complexNumber.getImaginary()*complexNumber.getImaginary());
        double imaginary = numerator/denominator;

        return new ComplexNumber(real, imaginary);
    }

    // Parse string to complex number. pattern: x + yi
    public static ComplexNumber parse(String expression) {

        ComplexNumber ret = new ComplexNumber();
        Pattern pattern =Pattern.compile("([+-]?\\d*\\.?\\d*)[+-](\\d*\\.?\\d*)i");
        Matcher matcher = pattern.matcher(expression);
        boolean matchFound = matcher.find();
        if(matchFound) {
            double real = Double.parseDouble(matcher.group(1));
            double imag = Double.parseDouble(matcher.group(2));

            return new ComplexNumber(real ,imag);
        }
        else {
            return null;
        }
    }

    // Override equals to compare between 2 complex numbers

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof ComplexNumber)) return false;
        ComplexNumber that = (ComplexNumber) other;
        return Double.compare(real, that.real) == 0 && Double.compare(imaginary, that.imaginary) == 0;
    }

    // Override hashCode to comply with our new equals implementation
    @Override
    public int hashCode() {
        return Objects.hash(real, imaginary);
    }


    @Override
    public int compareTo(ComplexNumber other){
        double numAbs = Math.sqrt(other.getReal()* other.getReal() + other.getImaginary()*other.getImaginary());
        double curr = Math.sqrt(this.getReal()* this.getReal() + this.getImaginary()*this.getImaginary());

        if(curr > numAbs){
            return 1;
        } else if (curr < numAbs) {
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return
                  real +
                ", imaginary=" + imaginary +
                "i";
    }
}
