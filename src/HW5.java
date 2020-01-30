import java.lang.reflect.Array;
import java.util.Arrays;

public class HW5 {
    static final int size = 10000000;
    static final int HALF = size / 2;
    static float[] arrm2 = new float[size];
    static float[] arrm1 = new float[size];


    public static void main(String[] args) {
        method1();
        method2();

    }

    public static void method1() {
        for (int i = 0; i < size; i++) {
            arrm1[i]=1;
        }
        float[] half1 = new float[HALF];
        float[] half2 = new float[HALF];


        new Thread(new Runnable() {
            @Override
            public void run() {
                System.arraycopy(arrm1,0,half1,0,HALF);
                for (int i = 0; i < 5000000; i++) {
                    half1[i] =  (float)(half1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
                System.arraycopy(half1, 0, arrm1, 0, HALF);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.arraycopy(arrm1,HALF,half2,0,HALF);
                for (int i = 0; i < 5000000; i++) {
                    half2[i] =  (float)(half2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
                System.arraycopy(half2, 0, arrm1, HALF, HALF);
            }
        }).start();
}

    public static void method2(){
        long a = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            arrm2[i]=1;
        }
        long b = System.currentTimeMillis();
        a = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            arrm2[i] = (float)(arrm2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        b = System.currentTimeMillis();
    }
}
