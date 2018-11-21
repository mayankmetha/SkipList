package utils;

import java.util.Random;

public class randomGenerator {

    private Random random = new Random(System.currentTimeMillis());

    public long getRandomNumber() {
        char digits[] = new char[10];
        digits[0] = (char)(random.nextInt(8)+'1');
        for(int i = 1; i < digits.length; i++) {
            digits[i] = (char)((random.nextInt(9)+'0'));
        }
        return Long.parseLong(new String(digits));
    }

    public int getLevel() {
        int level = 0;
        while(random.nextBoolean())
            level++;
        return level;
    }

}
