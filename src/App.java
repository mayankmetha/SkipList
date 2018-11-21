import ds.dlSkipList;
import utils.fileOp;
import utils.randomGenerator;
import utils.trace;

public class App {

    public static void main(String[] args) {
        dlSkipList list = new dlSkipList();
        trace.setFlag(false);
        trace.setMaxSteps(1);
        randomGenerator rd = new randomGenerator();
        fileOp file = new fileOp();
        file.createFile("/Users/mayankmetha/Desktop/demo.txt",10);
        list.insert("/Users/mayankmetha/Desktop/demo.txt");
        list.display();
        list.delete("/Users/mayankmetha/Desktop/demo.txt");
        list.display();
    }

}
