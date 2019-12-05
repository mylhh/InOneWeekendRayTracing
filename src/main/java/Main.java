
/**
 * Java implement in one weekend ray tracing.
 * @version 1.0.0
 * @author lhh 928436072@qq.com
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Random;
import java.util.stream.DoubleStream;


public class Main {

    // graphcies hello world
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
                data[i*width*3+j*3] = (int)(255.999*r);
                data[i*width*3+j*3+1] = (int)(255.999*g);
                data[i*width*3+j*3+2] = (int)(255.999*b);
            }
        }
        renderImage.setData(data);
        renderImage.save("./images/chapter1-helloworld.ppm");
    }

    // use Vector3
    public static void chapter2(){
        int width = 200;
        int height = 100;
        Image renderImage = Image.createImage(width,height,PPMFormat.PIXEL_ASCILL);
        int[] data = new int[height*width*3];
        for(int i = height-1;i >= 0 ;i--){
            for(int j = 0;j < width;j++){
                Vector3 color = new Vector3(1.0* j / width,1.0* i / height,0.2);
                data[i*width*3+j*3] = (int)(255.999*color.r());
                data[i*width*3+j*3+1] = (int)(255.999*color.g());
                data[i*width*3+j*3+2] = (int)(255.999*color.b());
            }
        }
        renderImage.setData(data);
        renderImage.save("./images/chapter2-helloworld-vector3.ppm");
    }

    // rays,camera,background
    public static void chapter3(){
        int width = 200;
        int height = 100;
        Render backgroundRender = new Render();
        Vector3 lowerLeftPoint = new Vector3(-2.0,-1.0,-1.0);
        Vector3 horizontal = new Vector3(4.0,0.0,0.0);
        Vector3 vertical = new Vector3(0.0,2.0,0.0);
        Vector3 origin = Vector3.getZeroVector();
        Image renderImage = Image.createImage(width,height,PPMFormat.PIXEL_ASCILL);
        int[] data = new int[height*width*3];
        for(int i = height-1;i >= 0 ;i--){
            for(int j = 0;j < width;j++){
                double u = 1.0*j/width;
                double v = 1.0*i/height;
                Ray ray = new Ray(origin,lowerLeftPoint.plus(horizontal.scale(u).plus(vertical.scale(v))));
                Vector3 color = backgroundRender.backgroundColor(ray);
                data[i*width*3+j*3] = (int)(255.999*color.r());
                data[i*width*3+j*3+1] = (int)(255.999*color.g());
                data[i*width*3+j*3+2] = (int)(255.999*color.b());
            }
        }
        renderImage.setData(data);
        renderImage.save("./images/chapter3-backgroud.ppm");
    }

    // add a sphere
    public static void chapter4(){
        int width = 200;
        int height = 100;
        Render backgroundRender = new Render();
        Vector3 lowerLeftPoint = new Vector3(-2.0,-1.0,-1.0);
        Vector3 horizontal = new Vector3(4.0,0.0,0.0);
        Vector3 vertical = new Vector3(0.0,2.0,0.0);
        Vector3 origin = Vector3.getZeroVector();
        Image renderImage = Image.createImage(width,height,PPMFormat.PIXEL_ASCILL);
        int[] data = new int[height*width*3];
        for(int i = height-1;i >= 0 ;i--){
            for(int j = 0;j < width;j++){
                double u = 1.0*j/width;
                double v = 1.0*i/height;
                Ray ray = new Ray(origin,lowerLeftPoint.plus(horizontal.scale(u).plus(vertical.scale(v))));
                Vector3 color = backgroundRender.addSphere(ray);
                data[i*width*3+j*3] = (int)(255.999*color.r());
                data[i*width*3+j*3+1] = (int)(255.999*color.g());
                data[i*width*3+j*3+2] = (int)(255.999*color.b());
            }
        }
        renderImage.setData(data);
        renderImage.save("./images/chapter4-addshphere.ppm");
    }

    // visualize normals and multiple objects
    public static void chapter5(){
        int width = 200;
        int height = 100;
        Render render = new Render();
        Vector3 lowerLeftPoint = new Vector3(-2.0,-1.0,-1.0);
        Vector3 horizontal = new Vector3(4.0,0.0,0.0);
        Vector3 vertical = new Vector3(0.0,2.0,0.0);
        Vector3 origin = Vector3.getZeroVector();
        HitObjectList world = new HitObjectList(2);
        world.hitableList.add(new Sphere(new Vector3(0.0,0.0,-1.0),0.5));
        world.hitableList.add(new Sphere(new Vector3(0.0,-100.5,-1.0),100));

        Image renderImage = Image.createImage(width,height,PPMFormat.PIXEL_ASCILL);
        int[] data = new int[height*width*3];
        for(int i = height-1;i >= 0 ;i--){
            for(int j = 0;j < width;j++){
                double u = 1.0*j/width;
                double v = 1.0*i/height;
                Ray ray = new Ray(origin,lowerLeftPoint.plus(horizontal.scale(u).plus(vertical.scale(v))));
                Vector3 color = render.addMutipleObject(ray,world);
                data[i*width*3+j*3] = (int)(255.999*color.r());
                data[i*width*3+j*3+1] = (int)(255.999*color.g());
                data[i*width*3+j*3+2] = (int)(255.999*color.b());
            }
        }
        renderImage.setData(data);
        renderImage.save("./images/chapter5-visualizenormals-mutipleobject.ppm");
    }

    // antialiasing
    public static void chapter6(){
        int width = 200;
        int height = 100;
        int samples = 100;
        Render render = new Render();
        Camera camera = new Camera();
        HitObjectList world = new HitObjectList(2);
        world.hitableList.add(new Sphere(new Vector3(0.0,0.0,-1.0),0.5));
        world.hitableList.add(new Sphere(new Vector3(0.0,-100.5,-1.0),100));
        Image renderImage = Image.createImage(width,height,PPMFormat.PIXEL_ASCILL);
        int[] data = new int[height*width*3];

        for(int i = height-1;i >= 0 ;i--){
            for(int j = 0;j < width;j++){
                Vector3 color = Vector3.getZeroVector();
                // Average sampling
                for(int s = 0;s < samples;s++){
                    double u = 1.0*(j+Math.random())/width;
                    double v = 1.0*(i+Math.random())/height;
                    Ray ray = camera.getRay(u,v);
                    color = color.plus(render.addMutipleObject(ray,world));
                }
                color = color.scale(1.0/samples);
                data[i*width*3+j*3] = (int)(255.999*color.r());
                data[i*width*3+j*3+1] = (int)(255.999*color.g());
                data[i*width*3+j*3+2] = (int)(255.999*color.b());
            }
        }
        renderImage.setData(data);
        renderImage.save("./images/chapter6-antialiasing.ppm");
    }

    // diffuse materials
    public static void chapter7(){
        int width = 200;
        int height = 100;
        int samples = 100;
        Render render = new Render();
        Camera camera = new Camera();
        HitObjectList world = new HitObjectList(2);
        world.hitableList.add(new Sphere(new Vector3(0.0,0.0,-1.0),0.5));
        world.hitableList.add(new Sphere(new Vector3(0.0,-100.5,-1.0),100));
        Image renderImage = Image.createImage(width,height,PPMFormat.PIXEL_ASCILL);
        int[] data = new int[height*width*3];

        for(int i = height-1;i >= 0 ;i--){
            for(int j = 0;j < width;j++){
                Vector3 color = Vector3.getZeroVector();
                // Average sampling
                for(int s = 0;s < samples;s++){
                    double u = 1.0*(j+Math.random())/width;
                    double v = 1.0*(i+Math.random())/height;
                    Ray ray = camera.getRay(u,v);
                    color = color.plus(render.diffuseMaterials(ray,world));
                }
                color = color.scale(1.0/samples);
                color = new Vector3(Math.sqrt(color.r()),Math.sqrt(color.g()),Math.sqrt(color.b()));
                data[i*width*3+j*3] = (int)(255.999*color.r());
                data[i*width*3+j*3+1] = (int)(255.999*color.g());
                data[i*width*3+j*3+2] = (int)(255.999*color.b());
            }
        }
        renderImage.setData(data);
        renderImage.save("./images/chapter7-diffusematerials.ppm");
    }
    public static void main(String[] args) {
        // chapter1();
        // chapter2();
        // chapter3();
        // chapter4();
        // chapter5();
        // chapter6();
        // chapter7();
    }
}
