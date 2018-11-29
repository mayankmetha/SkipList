package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;

class fileOp {
    String CreateData(String filePath, long keys) {
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
            return ("Creating file in "+filePath+" successful");
        } catch (IOException e) {
            return ("Creating file in "+filePath+" failed");
        }
    }
}
