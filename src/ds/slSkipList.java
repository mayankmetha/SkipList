package ds;

import utils.randomGenerator;
import utils.trace;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class slSkipList {
    private slNode head;
    private slNode tail;
    private int maxLevel;
    private int size;
    private randomGenerator rd = new randomGenerator();

    public slSkipList() {
        head = new slNode();
        tail = new slNode();
        maxLevel = 0;
        size = 0;
        head.next.add(tail);
        tail.next.add(null);
    }

    public slNode findNext(long key,int level) {
        slNode current = head;
        while(current.next.get(level) != tail) {
            if((current.next.get(level)).key < key)
                current = current.next.get(level);
            else
                break;
        }
        return current;
    }

    public int contains(long key) {
        int level = maxLevel;
        do {
            slNode tmp = findNext(key,level);
            if(tmp.next.get(level) != null && (tmp.next.get(level)).key == key) {
                return level;
            }
        }while(level-- > 0);
        return -1;
    }

    public Boolean emptyListLevelMatch(int level) {
        if(level > 0) {
            return head.next.get(level) == tail && head.next.get(level - 1) == tail;
        }
        return false;
    }

    public void display() {
        for(int i = maxLevel; i >= 0; i--) {
            System.out.print("head->");
            for(slNode j = head.next.get(i); j != tail; j = j.next.get(i)) {
                System.out.print(j.key+"->");
            }
            System.out.println("tail");
        }
        System.out.println("Max level = "+maxLevel);
        System.out.println("Number of nodes = "+size);
    }

    public void insert(long key) {
        if(contains(key) > -1)
            return;
        slNode toAdd = new slNode(key);
        int level = rd.getLevel();
        while(level >= maxLevel) {
            maxLevel++;
            head.next.add(tail);
            tail.next.add(null);
        }
        while(level >= 0) {
            slNode current = findNext(key,level);
            toAdd.next.add(0,current.next.get(level));
            current.next.set(level,toAdd);
            level--;
        }
        size++;
        if(trace.getFlag()) {
            if(trace.getCurStep() == (trace.getMaxSteps()-1))
                display();
            trace.setCurStep((trace.getCurStep()+1)% trace.getMaxSteps());
        }
    }

    public void delete(long key) {
        int nodeLevel = contains(key);
        if(nodeLevel == -1)
            return;
        int level = nodeLevel;
        while(level >= 0) {
            slNode current = findNext(key,level);
            current.next.set(level, (current.next.get(level)).next.get(level));
            level--;
        }
        size--;
        while(maxLevel > 0) {
            if(emptyListLevelMatch(maxLevel)) {
                head.next.remove(maxLevel);
                tail.next.remove(maxLevel);
                maxLevel--;
            } else
                break;
        }
        if(trace.getFlag()) {
            if(trace.getCurStep() == (trace.getMaxSteps()-1))
                display();
            trace.setCurStep((trace.getCurStep()+1)% trace.getMaxSteps());
        }
    }

    public void show(int from, int to) {
        if(from > size && to > size && from > to)
            return;
        List<slNode> tmp = new ArrayList<>();
        List<Integer> levels = new ArrayList<>();
        int i = 0;
        for(slNode current = head.next.get(0); current != tail; current = current.next.get(0)) {
            i++;
            if (i >= from && i <=to) {
                tmp.add(current);
                levels.add(current.next.size());
            }
        }
        for(int level = Collections.max(levels)-1; level >= 0; level--) {
            for (slNode aTmp : tmp) {
                if (aTmp.next.size() > level)
                    System.out.print("->" + aTmp.key);
            }
            System.out.println("->");
        }
    }

    public void find(long key) {
        int level = contains(key);
        if(level == -1)
            return;
        while(level >=0) {
            slNode current= findNext(key,level);
            int i = 3;
            while(i != 0) {
                if(current == head)
                    System.out.print("head");
                else if(current == tail)
                    System.out.print("tail");
                else
                    System.out.print("->"+current.key+"->");
                current = current.next.get(level);
                i--;
            }
            System.out.println();
            level--;
        }
    }

    public void insert(String filePath) {
        System.out.println("Inserting file data to skiplist");
        try {
            Scanner s = new Scanner(new FileReader(filePath)).useDelimiter(",");
            while(s.hasNextLong()) {
                insert(s.nextLong());
            }
        } catch (IOException e) {
            System.out.println("Failed to read "+filePath);
        }
    }

    public void delete(String filePath) {
        System.out.println("Deleting file data to skiplist");
        try {
            Scanner s = new Scanner(new FileReader(filePath)).useDelimiter(",");
            while(s.hasNextLong()) {
                delete(s.nextLong());
            }
        } catch (IOException e) {
            System.out.println("Failed to read "+filePath);
        }
    }
}
