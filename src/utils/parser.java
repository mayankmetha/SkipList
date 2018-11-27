package utils;

import java.util.StringTokenizer;

public class parser {
    String args[] = new String[5];
    public void parse(String str) {
        int pos = str.indexOf('(');
        args[0] = str.substring(0,pos);
        int i = 1, newPos;
        while((newPos = str.indexOf(',',pos)) > 0) {
            args[i++] = str.substring(pos+1,newPos);
            pos = newPos;
        }
        args[i] = str.substring(pos+1,str.indexOf(')'));
        for(int x = 0; x < args.length; x++) {
            System.out.println(args[x]);
        }
    }
}
