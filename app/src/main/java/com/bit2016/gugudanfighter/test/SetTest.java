package com.bit2016.gugudanfighter.test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by bit-user on 2016-11-25.
 */

public class SetTest {
    public static void main(String[] args){
        Set<Multiplication> set = new HashSet<Multiplication>();


    }

    private static int randomize(int from, int to){
        return (int)(Math.random() * to) + from;
    }

    private class Multiplication{
        private int left;
        private int right;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Multiplication that = (Multiplication) o;

            if (left != that.left) return false;
            return right == that.right;
        }

        @Override
        public int hashCode() {
            return 31*(left*right);
        }
    }
}
