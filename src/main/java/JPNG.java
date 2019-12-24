import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class JPNG extends AbstractImage{

    public JPNG(int width, int height) {
        super(width,height);
    }

    public JPNG setPixelsData(int[] data) {
        int dataLength = width*height*channel;
        assert data.length == dataLength:"image size not suitable";
        this.data = data;
        return this;
    }

    public static JPNG createImage(int width, int height, ImageFormatEnum type){
        return new JPNG(width,height);
    }

    private int to4ByteARBG(int red, int green, int blue, int alpha){
        int pixel = 0;
        pixel = ( alpha << 24 ) | (pixel & 0x00ffffff);
        pixel = ( red << 16 )   | (pixel & 0xff00ffff);
        pixel = ( green << 8 )  | (pixel & 0xffff00ff);
        pixel = ( blue << 0 )   | (pixel & 0xffffff00);
        return pixel;
    }

    @Override
    void save(File outputFile) {
        BufferedImage bufferedImage = new BufferedImage(this.width, this.height, BufferedImage.TYPE_4BYTE_ABGR);
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                int r = data[(height - 1 - i) * width * 3 + j * 3];
                int g = data[(height - 1 - i) * width * 3 + j * 3 + 1];
                int b = data[(height - 1 - i) * width * 3 + j * 3 + 2];
                bufferedImage.setRGB(j, i, to4ByteARBG(r, g, b, 255));
            }
        }
        try {
            ImageIO.write(bufferedImage, "png", outputFile);
        } catch (Exception e) {
            System.out.println("can't not create a png file!");
            System.out.println(e.getMessage());
        }
    }

}
