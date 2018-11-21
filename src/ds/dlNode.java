package ds;

import java.util.ArrayList;
import java.util.List;

class dlNode {
    List<dlNode> prev;
    long key;
    List<dlNode> next;

    dlNode() {
        prev = new ArrayList<>();
        key = 0;
        next = new ArrayList<>();
    }

    dlNode(long key) {
        prev = new ArrayList<>();
        this.key = key;
        next = new ArrayList<>();
    }

}
