package ds;

import utils.trace;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class skipListDS {

    final private static int SLL = 1;
    final private static int DLL = 2;
    private HashMap<String, Object> instances= new HashMap<>();
    private HashMap<String, Integer> instanceType = new HashMap<>();

    public String CreateList(String list, int mode) {
        if(instances.containsKey(list)) {
            return ("Failed to create list as it already exist");
        } else if(mode == SLL) {
            instances.put(list, new slSkipList());
            instanceType.put(list,1);
            return ("Created "+list);
        } else if(mode == DLL) {
            instances.put(list, new dlSkipList());
            instanceType.put(list,2);
            return ("Created "+list);
        }
        return ("Invalid parameters");
    }

    public String InsertNode(String list, long k1) {
        if(!instances.containsKey(list)) {
            return ("List does not exist");
        } else {
            if (instanceType.get(list) == SLL) {
                return ((slSkipList) instances.get(list)).insert(k1);
            } else if (instanceType.get(list) == DLL) {
                return ((dlSkipList) instances.get(list)).insert(k1);
            }
        }
        return ("Unable to find type of list");
    }

    public String DeleteNode(String list, long k1) {
        if(!instances.containsKey(list)) {
            return ("List does not exist");
        } else {
            if(instanceType.get(list) == SLL) {
                return ((slSkipList)instances.get(list)).delete(k1);
            } else if(instanceType.get(list) == DLL) {
                return ((dlSkipList)instances.get(list)).delete(k1);
            }
        }
        return ("Unable to find type of list");
    }

    public String ShowList(String list, int from, int to) {
        if(!instances.containsKey(list)) {
            return ("List does not exist");
        } else {
            if (instanceType.get(list) == SLL) {
                return ((slSkipList) instances.get(list)).show(from, to);
            } else if (instanceType.get(list) == DLL) {
                return ((dlSkipList) instances.get(list)).show(from, to);
            }
        }
        return ("Unable to find type of list");
    }

    public String FindNode(String list, long key) {
        if(!instances.containsKey(list)) {
            return ("List does not exist");
        } else {
            if (instanceType.get(list) == SLL) {
                return ((slSkipList) instances.get(list)).find(key);
            } else if (instanceType.get(list) == DLL) {
                return ((dlSkipList) instances.get(list)).find(key);
            }
        }
        return ("Unable to find type of list");
    }

    public String DeleteList(String list) {
        if(!instances.containsKey(list)) {
            return ("List does not exist");
        }
        instances.remove(list);
        instanceType.remove(list);
        return (list+" deleted");
    }

    public String PrintStats(String list) {
        if(!instances.containsKey(list)) {
            return ("List does not exist");
        } else {
            if (instanceType.get(list) == SLL) {
                return ((slSkipList) instances.get(list)).stats();
            } else if (instanceType.get(list) == DLL) {
                return ((dlSkipList) instances.get(list)).stats();
            }
        }
        return ("Unable to find type of list");
    }

    public String SetTrace(Boolean flag, int StepSize) {
        trace.setFlag(flag);
        trace.setMaxSteps(StepSize);
        if(flag) {
            return ("SetTrace set to True with step size "+StepSize);
        } else {
            return ("SetTrace set to False");
        }
    }

    public String InsertNodesFromFile(String list, String filePath) {
        if(!instances.containsKey(list)) {
            return ("List does not exist");
        } else {
            try {
                Scanner s = new Scanner(new FileReader(filePath)).useDelimiter(",");
                if(instanceType.get(list) == SLL) {
                    while(s.hasNextLong()) {
                        ((slSkipList)instances.get(list)).insert(s.nextLong());
                    }
                    return ("Successfully added nodes from file");
                } else if(instanceType.get(list) == DLL) {
                    while(s.hasNextLong()) {
                        ((dlSkipList)instances.get(list)).insert(s.nextLong());
                    }
                    return ("Successfully added nodes from file");
                } else {
                    return ("Unable to find type of list");
                }
            } catch (IOException e) {
                return ("Failed to read "+filePath);
            }
        }
    }

    public String DeleteNodesFromFile(String list, String filePath) {
        if(!instances.containsKey(list)) {
            return ("List does not exist");
        } else {
            try {
                Scanner s = new Scanner(new FileReader(filePath)).useDelimiter(",");
                if(instanceType.get(list) == SLL) {
                    while(s.hasNextLong()) {
                        ((slSkipList)instances.get(list)).delete(s.nextLong());
                    }
                    return ("Successfully deleted nodes from file");
                } else if(instanceType.get(list) == DLL) {
                    while(s.hasNextLong()) {
                        ((dlSkipList)instances.get(list)).delete(s.nextLong());
                    }
                    return ("Successfully deleted nodes from file");
                } else {
                    return ("Unable to find type of list");
                }
            } catch (IOException e) {
                return ("Failed to read "+filePath);
            }
        }
    }

}
