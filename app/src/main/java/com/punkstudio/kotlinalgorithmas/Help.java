package com.punkstudio.kotlinalgorithmas;

import android.support.annotation.Nullable;

/**
 * Date:2020/11/13-4:18 下午
 *
 * @author Mason
 */
class Help {

    public static void test() {
        String a = new String("abcd");
        String b = new String("abcd");
        String c = "abcd";
        String d = "abcd";

        System.out.println((a == b));
        System.out.println((a.equals(b)));
        System.out.println((a == c));
        System.out.println((b.equals(c)));
        System.out.println((c == d));
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(@Nullable @org.jetbrains.annotations.Nullable Object obj) {
        return super.equals(obj);
    }
}
