package utils;

import java.util.ArrayList;
import java.util.List;

public class parser {
    List<String> args;
    public void parse(String str) {
        args = new ArrayList<>();
        int pos = str.indexOf('(');
        args.add(str.substring(0,pos));
        int newPos;
        newPos = str.indexOf(',',pos);
        while(newPos > pos && newPos < str.indexOf(')') && pos != newPos) {
            args.add(str.substring(pos+1,newPos));
            pos = newPos;
            newPos = str.indexOf(',',pos+1);
        }
        args.add(str.substring(pos+1,str.indexOf(')')));
        for(int x = 0; x < args.size(); x++) {
            System.out.println(args.get(x));
        }
    }
}
