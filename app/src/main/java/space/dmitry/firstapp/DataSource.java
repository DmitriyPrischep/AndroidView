package space.dmitry.firstapp;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataSource {

    private String[] mStrings = new String[] {
            "test1",
            "test2",
            "test3",
            "test4",
            "test5",
            "test6",
            "test7",
            "test8",
            "test9",
            "test10",
            "test11"
    };

    private int[] mColors = new int[] {
            Color.RED,
            Color.BLACK,
            Color.GRAY,
            Color.GREEN,
            Color.YELLOW,
            Color.BLUE,
            Color.WHITE,
            Color.CYAN
    };

    private static DataSource sInstance;
    private final List<MyData> myData;

    public DataSource() {
        myData = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            String titles = mStrings[random.nextInt(mStrings.length)];
            int color = mColors[random.nextInt(mColors.length)];
            myData.add(new MyData(titles, color));
        }
    }

    public List<MyData> getData() {
        return myData;
    }

    public synchronized static DataSource getInstance() {
        if (sInstance == null) {
            sInstance = new DataSource();
        }
        return sInstance;
    }

    public static class MyData {
        public MyData(String mTitle, int mColor) {
            this.mTitle = mTitle;
            this.mColor = mColor;
        }

        String mTitle;
        int mColor;
    }
}
