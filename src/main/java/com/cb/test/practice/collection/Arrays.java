package com.cb.test.practice.collection;

public class Arrays {
    public static void main(String[] args) {


        int[][] a = {{1, 2, 3}, {2, 3, 4}, {6, 8, 7}};

        System.out.println(Find(21, a));

    }

    public static boolean Find(int target, int[][] array) {
        int fir = 0, clen = 0, len = 0;
        if (array.length == 0 || array[0].length == 0) return false;
        clen = array[0].length;
        len = array.length;

        while (fir < len) {
            int left = 0, right = clen - 1;
            while (left < clen && right >= 0) {
                if (array[fir][left] == target)
                    return true;
                else
                    left++;
                if (array[fir][right] == target)
                    return true;
                else
                    right--;
            }
            fir++;
        }


        return false;

    }
}
