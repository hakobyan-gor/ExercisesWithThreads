package com.aca.textReverse;

public class TextReverseRunnable implements Runnable {

    private StringBuffer reversedString = new StringBuffer();
    private StringBuffer someString;

    TextReverseRunnable(String someString) {
        this.someString = new StringBuffer(someString);
    }

    public void reverseString(StringBuffer stringBuffer){
        reversedString = stringBuffer.reverse();
    }

    public void reverseString(String string){
        reversedString = new StringBuffer(string).reverse();
    }

    private void reverseString(){
        reversedString = someString.reverse();
    }

    StringBuffer getReversedString() {
        return reversedString;
    }

    @Override
    public void run() {
        reverseString();
    }

}
