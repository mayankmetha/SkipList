package ds;

import java.util.ArrayList;
import java.util.List;

class slNode {
    long key;
    List<slNode> next;

    slNode() {
        key = 0;
        next = new ArrayList<>();
    }

    slNode(long key) {
        this.key = key;
        next = new ArrayList<>();
    }

}
