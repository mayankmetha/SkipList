package utils;

import ds.skipListDS;

import java.util.ArrayList;
import java.util.List;

public class parser {
    private skipListDS ds;
    private fileOp op;

    public parser() {
        ds = new skipListDS();
        op = new fileOp();
    }

    public String parseInput(String str) {
        List<String> args = new ArrayList<>();
        int pos = str.indexOf('(');
        args.add(str.substring(0,pos));
        int newPos = str.indexOf(',',pos);
        while(newPos > pos && newPos < str.indexOf(')')) {
            args.add(str.substring(pos+1,newPos));
            pos = newPos;
            newPos = str.indexOf(',',pos+1);
        }
        args.add(str.substring(pos+1,str.indexOf(')')));

        if(args.size() == 3 && args.get(0).matches("InsertNode")) {
            if(args.get(1) != null && args.get(2) != null)
                return ds.InsertNode(args.get(1),Long.parseLong(args.get(2)));
            else
                return "Syntax Error";
        } else if(args.size() == 3 && args.get(0).matches("DeleteNode")) {
            if(args.get(1) != null && args.get(2) != null)
                return ds.DeleteNode(args.get(1),Long.parseLong(args.get(2)));
            else
                return "Syntax Error";
        } else if(args.size() == 4 && args.get(0).matches("ShowList")) {
            if(args.get(1) != null && args.get(2) != null && args.get(3) != null)
                return ds.ShowList(args.get(1),Integer.parseInt(args.get(2)),Integer.parseInt(args.get(3)));
            else
                return "Syntax Error";
        } else if(args.size() == 3 && args.get(0).matches("FindNode")) {
            if(args.get(1) != null && args.get(2) != null)
                return ds.FindNode(args.get(1),Long.parseLong(args.get(2)));
            else
                return "Syntax Error";
        } else if(args.size() == 3 && args.get(0).matches("SetTrace")) {
            if(args.get(1) != null && args.get(2) != null)
                return ds.SetTrace(Boolean.parseBoolean(args.get(1)),Integer.parseInt(args.get(2)));
            else
                return "Syntax Error";
        } else if(args.size() == 3 && args.get(0).matches("CreateList")) {
            if(args.get(1) != null && args.get(2) != null)
                return ds.CreateList(args.get(1),Integer.parseInt(args.get(2)));
            else
                return "Syntax Error";
        } else if(args.size() == 2 && args.get(0).matches("DeleteList")) {
            if(args.get(1) != null)
                return ds.DeleteList(args.get(1));
            else
                System.out.println("SyntaxError");
        } else if(args.size() == 3 && args.get(0).matches("InsertNodesFromFile")) {
            if(args.get(1) != null && args.get(2) != null)
                return ds.InsertNodesFromFile(args.get(1), args.get(2));
            else
                return "Syntax Error";
        } else if(args.size() == 3 && args.get(0).matches("DeleteNodesFromFile")) {
            if(args.get(1) != null && args.get(2) != null)
                return ds.DeleteNodesFromFile(args.get(1), args.get(2));
            else
                return "Syntax Error";
        } else if(args.size() == 2 && args.get(0).matches("PrintStats")) {
            if(args.get(1) != null)
                return ds.PrintStats(args.get(1));
            else
                return "Syntax Error";
        } else if(args.size() == 3 && args.get(0).matches("CreateData")) {
            if(args.get(1) != null && args.get(2) != null)
                return op.CreateData(args.get(1),Long.parseLong(args.get(2)));
            else
                return "Syntax Error";
        } else {
            return "Syntax Error";
        }
        return "Syntax Error";
    }
}
