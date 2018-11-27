package ds;

import utils.trace;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class skipListDS {

    final private static int SLL = 1;
    final private static int DLL = 2;
    HashMap<String, Object> instances= new HashMap<>();
    HashMap<String, Integer> instanceType = new HashMap<>();

    public void CreateList(String list, int mode) {
        if(instances.containsKey(list)) {
            System.out.println("Failed to create list as it already exist");
        } else if(mode == SLL) {
            instances.put(list, new slSkipList());
            instanceType.put(list,1);
            System.out.println("Created "+list);
        } else if(mode == DLL) {
            instances.put(list, new dlSkipList());
            instanceType.put(list,2);
            System.out.println("Created "+list);
        } else {
            System.out.println("Invalid parameters");
        }
    }

    public void InsertNode(String list, long k1) {
        if(!instances.containsKey(list)) {
            System.out.println("List does not exist");
        } else {
            if(instanceType.get(list) == SLL) {
                ((slSkipList)instances.get(list)).insert(k1);
            } else if(instanceType.get(list) == DLL) {
                ((dlSkipList)instances.get(list)).insert(k1);
            } else {
                System.out.println("Unable to find type of list");
            }
        }
    }

    public void DeleteNode(String list, long k1) {
        if(!instances.containsKey(list)) {
            System.out.println("List oes not exist");
        } else {
            if(instanceType.get(list) == SLL) {
                ((slSkipList)instances.get(list)).delete(k1);
            } else if(instanceType.get(list) == DLL) {
                ((dlSkipList)instances.get(list)).delete(k1);
            } else {
                System.out.println("Unable to find type of list");
            }
        }
    }

    public void ShowList(String list, int from, int to) {
        if(!instances.containsKey(list)) {
            System.out.println("List oes not exist");
        } else {
            if(instanceType.get(list) == SLL) {
                ((slSkipList)instances.get(list)).show(from,to);
            } else if(instanceType.get(list) == DLL) {
                ((dlSkipList)instances.get(list)).show(from,to);
            } else {
                System.out.println("Unable to find type of list");
            }
        }
    }

    public void FindNode(String list, long key) {
        if(!instances.containsKey(list)) {
            System.out.println("List oes not exist");
        } else {
            if(instanceType.get(list) == SLL) {
                ((slSkipList)instances.get(list)).find(key);
            } else if(instanceType.get(list) == DLL) {
                ((dlSkipList)instances.get(list)).find(key);
            } else {
                System.out.println("Unable to find type of list");
            }
        }
    }

    public void DeleteList(String list) {
        if(!instances.containsKey(list)) {
            System.out.println("List oes not exist");
        } else {
            instances.remove(list);
            instanceType.remove(list);
            System.out.println(list+" deleted");
        }
    }

    public void PrintStats(String list) {
        if(!instances.containsKey(list)) {
            System.out.println("List oes not exist");
        } else {
            if(instanceType.get(list) == SLL) {
                ((slSkipList)instances.get(list)).stats();
            } else if(instanceType.get(list) == DLL) {
                ((dlSkipList)instances.get(list)).stats();
            } else {
                System.out.println("Unable to find type of list");
            }
        }
    }

    public void SetTrace(Boolean flag, int StepSize) {
        trace.setFlag(flag);
        trace.setMaxSteps(StepSize);
    }

    public void InsertNodesFromFile(String list, String filePath) {
        if(!instances.containsKey(list)) {
            System.out.println("List does not exist");
        } else {
            try {
                Scanner s = new Scanner(new FileReader(filePath)).useDelimiter(",");
                if(instanceType.get(list) == SLL) {
                    while(s.hasNextLong()) {
                        ((slSkipList)instances.get(list)).insert(s.nextLong());
                    }
                } else if(instanceType.get(list) == DLL) {
                    while(s.hasNextLong()) {
                        ((dlSkipList)instances.get(list)).insert(s.nextLong());
                    }
                } else {
                    System.out.println("Unable to find type of list");
                }
            } catch (IOException e) {
                System.out.println("Failed to read "+filePath);
            }
        }
    }

    public void DeleteNodesFromFile(String list, String filePath) {
        if(!instances.containsKey(list)) {
            System.out.println("List does not exist");
        } else {
            try {
                Scanner s = new Scanner(new FileReader(filePath)).useDelimiter(",");
                if(instanceType.get(list) == SLL) {
                    while(s.hasNextLong()) {
                        ((slSkipList)instances.get(list)).delete(s.nextLong());
                    }
                } else if(instanceType.get(list) == DLL) {
                    while(s.hasNextLong()) {
                        ((dlSkipList)instances.get(list)).delete(s.nextLong());
                    }
                } else {
                    System.out.println("Unable to find type of list");
                }
            } catch (IOException e) {
                System.out.println("Failed to read "+filePath);
            }
        }
    }

}
