package space.dmitry.firstapp;

import java.util.ArrayList;
import java.util.List;

public class NumberSource {
    private static NumberSource sInstance;
    private final List<Number> numberList;

    public NumberSource() {
        numberList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            numberList.add(new Number(i+1));
        }
    }

    public List<NumberSource.Number> getData() {
        return numberList;
    }

    public void addElement(int value){
        numberList.add(new Number(value));
    }

    public synchronized static NumberSource getInstance() {
        if (sInstance == null) {
            sInstance = new NumberSource();
        }
        return sInstance;
    }

    public static class Number {
        public Number(int value) {
            this.value= value;
        }
        int value;
    }
}
