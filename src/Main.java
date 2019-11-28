
/**
 * Java implement in one weekend ray tracing.
 * @version 1.0.0
 * @author lhh 928436072@qq.com
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;


public class Main {

    public static void main(String[] args) {
	    // write your code here
        File ppm = new File("./image/out.ppm");
        if(!ppm.exists()){
            try {
                ppm.createNewFile();
            }catch (Exception e){
                System.out.println("can't not create file!");
                System.out.println(e.getMessage());
            }
        }

        try (Writer out = new FileWriter(ppm)){
            out.append("P3\n"); // image type
            out.append("3 2\n"); // image size
            out.append("255\n"); // pixel intensity level
            out.write("255 0 0 0 255 0 0 0 255\n");
            out.write("255 255 0 255 255 255 0 0 0");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }
}
