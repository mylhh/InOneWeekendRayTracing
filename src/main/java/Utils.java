public class Utils {

    public static int to4ByteARGB(int red, int green, int blue, int alpha){
        int pixel = 0;
        pixel = ( alpha << 24 ) | (pixel & 0x00ffffff);
        pixel = ( red << 16 )   | (pixel & 0xff00ffff);
        pixel = ( green << 8 )  | (pixel & 0xffff00ff);
        pixel = ( blue << 0 )   | (pixel & 0xffffff00);
        return pixel;
    }

    public static int[] decodeToRGB(int argb){
        int[] rgb = new int[3];
        rgb[0] = (argb & 0x00ff0000) >> 16;
        rgb[1] = (argb & 0x0000ff00) >> 8;
        rgb[2] = (argb & 0x000000ff);
        return rgb;
    }
}
