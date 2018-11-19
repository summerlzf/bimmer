package com.kedu.bimmer.base;

import java.util.UUID;

/**
 * GUID created based on UUID
 *
 * @author Jef
 */
public class GUID {

    private static final char[] chs = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    private static final int size = chs.length;

    public static String generate() {
        String id = UUID.randomUUID().toString().replaceAll("-", "");
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i + 2 <= 32) {
            int n = Integer.parseInt(id.substring(i, i + 2), 16);
            sb.append(chs[n % size]);
            i += 2;
        }
        return sb.toString();
    }

    public static boolean isGUID(String text) {
        return text != null && text.length() == 16 && text.matches("^[0-9a-zA-Z]{16}$");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            String s = generate();
            System.out.println(s + "   " + isGUID(s));
        }
        String s = "123";
        System.out.println(s + "   " + isGUID(s));
    }
}
