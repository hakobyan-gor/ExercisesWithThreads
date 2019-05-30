package com.aca.textReverse;

public class TextReverseTest {

    public static void main(String[] args) {
        test();
    }

    static void test(){

        String string = "Hello";
        String reversedString = ("olleH");
        TextReverse textReverse = new TextReverse();
        textReverse.reverseString(string, 1);
        if (!textReverse.getReversedString().toString().equals(reversedString)){
            throw new RuntimeException("Error!");
        }

    }

}
