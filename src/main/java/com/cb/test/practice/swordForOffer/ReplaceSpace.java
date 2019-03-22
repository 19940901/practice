package com.cb.test.practice.swordForOffer;

import java.util.ArrayList;
import java.util.List;

public class ReplaceSpace {
    public static String replace(StringBuffer str) {

        List l=new ArrayList();
        List<Integer> list=new ArrayList<Integer>();
        StringBuffer sb = new StringBuffer();

        char cur;

        int index = 0;
        while (index < str.length()) {
            cur = str.charAt(index);
            if (cur == ' ') {
                sb.append("%20");
            } else {
                sb.append(cur);
            }

            index++;

        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "hello  world";
        StringBuffer sb = new StringBuffer(s);
        System.out.println(replace(sb));
    }
}
