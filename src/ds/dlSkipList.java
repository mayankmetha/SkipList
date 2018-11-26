package ds;

import utils.randomGenerator;
import utils.trace;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class dlSkipList {
    private dlNode head;
    private dlNode tail;
    private int maxLevel;
    private int size;
    private randomGenerator rd = new randomGenerator();

    public dlSkipList() {
        head = new dlNode();
        tail = new dlNode();
        maxLevel = 0;
        size = 0;
        head.prev.add(null);
        head.next.add(tail);
        tail.prev.add(head);
        tail.next.add(null);
    }

    public dlNode findNext(long key,int level) {
        dlNode current = head;
        while(current.next.get(level) != tail) {
            if((current.next.get(level)).key < key)
                current = current.next.get(level);
            else
                break;
        }
        return current;
    }

    public dlNode findPrev(long key,int level) {
        dlNode current = tail;
        while(current.prev.get(level) != head) {
            if((current.prev.get(level)).key > key)
                current = current.prev.get(level);
            else
                break;
        }
        return current;
    }

    public int contains(long key) {
        int level = maxLevel;
        do {
            dlNode tmp = findNext(key,level);
            if(tmp.next.get(level) != null && (tmp.next.get(level)).key == key) {
                return level;
            }
        }while(level-- > 0);
        return -1;
    }

    public Boolean emptyListLevelMatch(int level) {
        if(level > 0) {
            return (head.next.get(level) == tail && tail.prev.get(level) == head) &&
                    (head.next.get(level - 1) == tail && tail.prev.get(level - 1) == head);
        }
        return false;
    }

    public void display() {
        for(int i = maxLevel; i >= 0; i--) {
            System.out.print("-\u221E<->");
            for(dlNode j = head.next.get(i); j != tail; j = j.next.get(i)) {
                System.out.print(j.key+"<->");
            }
            System.out.println("\u221E");
        }
        System.out.println("Max level = "+maxLevel);
        System.out.println("Number of nodes = "+size);
    }

    public void insert(long key) {
        if(contains(key) > -1)
            return;
        dlNode toAdd = new dlNode(key);
        int level = rd.getLevel();
        while(level >= maxLevel) {
            maxLevel++;
            head.prev.add(null);
            head.next.add(tail);
            tail.prev.add(head);
            tail.next.add(null);
        }
        while(level >= 0) {
            dlNode prevNode = findNext(key,level);
            dlNode nextNode = findPrev(key,level);
            toAdd.next.add(0,prevNode.next.get(level));
            prevNode.next.set(level,toAdd);
            toAdd.prev.add(0,nextNode.prev.get(level));
            nextNode.prev.set(level,toAdd);
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
            dlNode prevNode = findNext(key,level);
            dlNode nextNode = findPrev(key,level);
            prevNode.next.set(level, (prevNode.next.get(level)).next.get(level));
            nextNode.prev.set(level, (nextNode.prev.get(level)).prev.get(level));
            level--;
        }
        size--;
        while(maxLevel > 0) {
            if(emptyListLevelMatch(maxLevel)) {
                head.prev.remove(maxLevel);
                head.next.remove(maxLevel);
                tail.next.remove(maxLevel);
                tail.prev.remove(maxLevel);
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
        List<dlNode> tmp = new ArrayList<>();
        List<Integer> levels = new ArrayList<>();
        int i = 0;
        for(dlNode current = head.next.get(0); current != tail; current = current.next.get(0)) {
            i++;
            if (i >= from && i <=to) {
                tmp.add(current);
                levels.add(current.next.size());
            }
        }
        for(int level = Collections.max(levels)-1; level >= 0; level--) {
            for (dlNode aTmp : tmp) {
                if (aTmp.next.size() > level)
                    System.out.print("<->" + aTmp.key);
            }
            System.out.println("<->");
        }
    }

    public void find(long key) {
        int level = contains(key);
        if(level == -1)
            return;
        while(level >=0) {
            dlNode current= findNext(key,level);
            int i = 3;
            while(i != 0) {
                if(current == head)
                    System.out.print("-\u221E");
                else if(current == tail)
                    System.out.print("<->\u221E");
                else
                    System.out.print("<->"+current.key);
                if(i==1 && current != tail)
                    System.out.print("<->");
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

    public long count(int level) {
        dlNode current = head;
        long nodes = 0;
        while(current.next.get(level) != tail) {
            current = current.next.get(level);
            nodes++;
        }
        return nodes;
    }

    public void stats() {
        System.out.println("Number of levels: "+maxLevel);
        System.out.println("Number of nodes excludes count for head and tail");
        for(int level = 0; level <= maxLevel; level++) {
            long nodes = count(level);
            System.out.println("Nodes at level "+level+": "+nodes);
        }
        System.out.println("Total number of nodes: "+size);
    }
}
