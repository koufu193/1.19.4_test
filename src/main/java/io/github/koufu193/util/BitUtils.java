package io.github.koufu193.util;

public final class BitUtils {
    /**
     * 指定された数値を収納できる最小ビット数を取得
     */
    public static int calculateBitsToStore(int number) {
        int bits = 1;
        while (true) {
            number >>>= 1;
            if (number != 0) bits++;
            else return bits;
        }
    }
}
