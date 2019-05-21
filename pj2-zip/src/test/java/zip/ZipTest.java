package zip;

import org.junit.Test;

import java.io.File;

public class ZipTest {
    @Test
    public void testZip(){
        Zip zip = new Zip();
        File test1Dir = new File(this.getClass().getClassLoader().getResource("files/test1").getFile());
        File[] files = test1Dir.listFiles();
        new File("./target/zipped/").mkdirs();
        for(File f:files){
            zip.zip(f,"./target/zipped/");
        }


    }
    @Test
    public void testUnZip() {
        Zip zip = new Zip();
        File zippedDir = new File("./target/zipped");
        File[] files = zippedDir.listFiles();
        new File("./target/unzipped/").mkdirs();
        for(File f:files){
            zip.unZip(f,"./target/unzipped/");
        }


    }
}
