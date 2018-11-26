package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;

public class fileOp {
    public void CreateData(String filePath, long keys) {
        randomGenerator rd = new randomGenerator();
        try {
            BufferedWriter br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(filePath))));
            while(keys > 1) {
                br.write(Long.toString(rd.getRandomNumber()));
                br.write(",");
                keys--;
            }
            br.write(Long.toString(rd.getRandomNumber()));
            br.close();
        } catch (IOException e) {
            System.out.println("Creating file in "+filePath+" failed");
        }
    }
}
