package utils;

import ds.skipListDS;

import java.util.ArrayList;
import java.util.List;

public class parser {
    private skipListDS ds;
    private fileOp op;
    private static String listName = "";
    private static Boolean visRequired = false;
    private static int type = 0;
    private List<String> args;

    public parser() {
        ds = new skipListDS();
        op = new fileOp();
        args = new ArrayList<>();
    }

    public String parseInput(String str) {
        args.clear();
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
            if(args.get(1) != null && args.get(2) != null) {
                if(trace.getFlag()) {
                    if(trace.getCurStep() == (trace.getMaxSteps()-1)) {
                        setVisRequired(true);
                    } else {
                        setVisRequired(false);
                    }
                    trace.setCurStep((trace.getCurStep()+1)% trace.getMaxSteps());
                }
                type = 0;
                listName = args.get(1);
                return ds.InsertNode(args.get(1),Long.parseLong(args.get(2)));
            } else
                return "Syntax Error";
        } else if(args.size() == 3 && args.get(0).matches("DeleteNode")) {
            if(args.get(1) != null && args.get(2) != null) {
                if(trace.getFlag()) {
                    if(trace.getCurStep() == (trace.getMaxSteps()-1)) {
                        setVisRequired(true);
                    } else {
                        setVisRequired(false);
                    }
                    trace.setCurStep((trace.getCurStep()+1)% trace.getMaxSteps());
                }
                type = 0;
                listName = args.get(1);
                return ds.DeleteNode(args.get(1),Long.parseLong(args.get(2)));
            } else
                return "Syntax Error";
        } else if(args.size() == 4 && args.get(0).matches("ShowList")) {
            if(args.get(1) != null && args.get(2) != null && args.get(3) != null) {
                type = 1;
                setVisRequired(true);
                listName = args.get(1);
                return "Output shown in visualization area";
            } else
                return "Syntax Error";
        } else if(args.size() == 3 && args.get(0).matches("FindNode")) {
            if(args.get(1) != null && args.get(2) != null) {
                type = 2;
                setVisRequired(true);
                listName = args.get(1);
                return "Output shown in visualization area";
            } else
                return "Syntax Error";
        } else if(args.size() == 3 && args.get(0).matches("SetTrace")) {
            if(args.get(1) != null && args.get(2) != null)
                return ds.SetTrace(Boolean.parseBoolean(args.get(1)),Integer.parseInt(args.get(2)));
            else
                return "Syntax Error";
        } else if(args.size() == 3 && args.get(0).matches("CreateList")) {
            if(args.get(1) != null && args.get(2) != null) {
                type = 0;
                listName = args.get(1);
                setVisRequired(true);
                return ds.CreateList(args.get(1),Integer.parseInt(args.get(2)));
            } else
                return "Syntax Error";
        } else if(args.size() == 2 && args.get(0).matches("DeleteList")) {
            if(args.get(1) != null) {
                type = 0;
                listName = args.get(1);
                setVisRequired(true);
                return ds.DeleteList(args.get(1));
            } else
                System.out.println("SyntaxError");
        } else if(args.size() == 3 && args.get(0).matches("InsertNodesFromFile")) {
            if(args.get(1) != null && args.get(2) != null) {
                type = 0;
                listName = args.get(1);
                setVisRequired(true);
                return ds.InsertNodesFromFile(args.get(1), args.get(2));
            } else
                return "Syntax Error";
        } else if(args.size() == 3 && args.get(0).matches("DeleteNodesFromFile")) {
            if(args.get(1) != null && args.get(2) != null) {
                type = 0;
                listName = args.get(1);
                setVisRequired(true);
                return ds.DeleteNodesFromFile(args.get(1), args.get(2));
            } else
                return "Syntax Error";
        } else if(args.size() == 2 && args.get(0).matches("PrintStats")) {
            if(args.get(1) != null) {
                type = 3;
                setVisRequired(true);
                return "Output shown in visualization area";
            } else
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

    public String displayVisual() {
        if(type == 1)
            return ds.ShowList(args.get(1),Integer.parseInt(args.get(2)),Integer.parseInt(args.get(3)));
        else if(type == 2)
            return ds.FindNode(args.get(1), Long.parseLong(args.get(2)));
        else if(type == 3)
            return ds.PrintStats(args.get(1));
        else
            return ds.Display(listName);
    }

    public boolean getVisRequired() {
        return visRequired;
    }

    private void setVisRequired(boolean visRequired) {
        parser.visRequired = visRequired;
    }

    public String getListName() {
        return listName;
    }

}
