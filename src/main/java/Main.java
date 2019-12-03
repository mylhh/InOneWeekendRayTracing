
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

    public static void chapter1(){
        int width = 200;
        int height = 100;
        Image renderImage = Image.createImage(width,height,PPMFormat.PIXEL_ASCILL);
        int[] data = new int[height*width*3];
        for(int i = height-1;i >= 0 ;i--){
            for(int j = 0;j < width;j++){
                double r = 1.0* j / width;
                double g = 1.0* i / height;
                double b = 0.2;
                data[(height - 1 - i)*width*3+j*3] = (int)(255.999*r);
                data[(height - 1 - i)*width*3+j*3+1] = (int)(255.999*g);
                data[(height - 1 - i)*width*3+j*3+2] = (int)(255.999*b);
            }
        }
        renderImage.setData(data);
        renderImage.save("./image/chapter1.ppm");
    }

    public static void chapter2(){
        int width = 200;
        int height = 100;
        Image renderImage = Image.createImage(width,height,PPMFormat.PIXEL_ASCILL);
        int[] data = new int[height*width*3];
        for(int i = height-1;i >= 0 ;i--){
            for(int j = 0;j < width;j++){
                Vector3 color = new Vector3(1.0* j / width,1.0* i / height,0.2);
                data[(height - 1 - i)*width*3+j*3] = (int)(255.999*color.r());
                data[(height - 1 - i)*width*3+j*3+1] = (int)(255.999*color.g());
                data[(height - 1 - i)*width*3+j*3+2] = (int)(255.999*color.b());
            }
        }
        renderImage.setData(data);
        renderImage.save("./image/chapter2.ppm");
    }
    public static void main(String[] args) {
        //chapter1();
        //chapter2();
    }
}
