package by.it.group351051.belskaya.lesson01;

import java.math.BigInteger;

public class FiboA {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboA fibo = new FiboA();
        int n = 33;
        System.out.printf("calc(%d)=%d \n\t time=%d \n\n", n, fibo.calc(n), fibo.time());

        fibo = new FiboA();
        n = 33;
        System.out.printf("slowA(%d)=%d \n\t time=%d \n\n", n, fibo.slowA(n), fibo.time());
    }


    private int calc(int n) {
        if (n < 2) return n;

        return calc(n - 1) + calc(n - 2);
    }

    BigInteger slowA(Integer n) {
        if (n == 0) return BigInteger.ZERO;
        if (n == 1) return BigInteger.ONE;

        return slowA(n - 1).add(slowA(n - 2));
    }
}

