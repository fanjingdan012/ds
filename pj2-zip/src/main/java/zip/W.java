package zip;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class W {
    public static void main1(String[] args) {
        File f = new File("files");

        FileOutputStream saverf;
        try {
            saverf = new FileOutputStream("pj2-zip/files/result.zip");
            ObjectOutputStream saver = new ObjectOutputStream(saverf);
            //saver.writeInt(12345);
            //saver.writeObject("Today");
            saver.writeObject("sagdfagsd");
            saver.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String args[]) throws Exception {
        String[] paths = {"pj2-zip/A", "pj2-zip/B", "pj2-zip/C", "pj2-zip/D"};
        String[] names = {"a.txt", "b.txt", "c.txt", "d.txt"};
        File[] files = new File[4];
        for (int i = 0; i < 4; i++) {
            files[i] = new File(paths[i], names[i]);
            files[i].getParentFile().mkdirs();
            files[i].createNewFile();
            System.out.println(files[i].getName());
        }
    }
}
 



