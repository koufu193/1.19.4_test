package io.github.koufu193;

import org.jglrxavpok.hephaistos.mca.*;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws IOException, AnvilException {
        Integer[] a={1,2,3,4,1};
        Arrays.sort(a, Comparator.comparingInt(Integer::intValue));
        System.out.println(Arrays.toString(a));
    }

}
