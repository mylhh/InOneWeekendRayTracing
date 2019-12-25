import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class JPNG extends AbstractImage{
    public static final int OPAQUE = 255;
    public JPNG(int width, int height) {
        super(width,height);
    }

    public static JPNG createImage(int width, int height){
        return new JPNG(width,height);
    }

    @Override
    void save(File outputFile) {
        BufferedImage bufferedImage = new BufferedImage(this.width, this.height, BufferedImage.TYPE_4BYTE_ABGR);
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                bufferedImage.setRGB(j, i, data[(height - 1 - i) * width + j]);
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
