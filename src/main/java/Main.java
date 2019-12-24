
/**
 * Java implement in one weekend ray tracing.
 * @version 1.0.0
 * @author lhh 928436072@qq.com
 */

public class Main {

    // graphcies hello world
    public static void chapter1(){
        int width = 200;
        int height = 100;
        JPNG renderImage = JPNG.createImage(width,height, ImageFormatEnum.PPM_PIXEL_ASCILL);
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
        renderImage.save(data,"./output/chapter1-helloworld.png");
    }

    // use Vector3
    public static void chapter2(){
        int width = 200;
        int height = 100;
        JPNG renderImage = JPNG.createImage(width,height, ImageFormatEnum.PPM_PIXEL_ASCILL);
        int[] data = new int[height*width*3];
        for(int i = height-1;i >= 0 ;i--){
            for(int j = 0;j < width;j++){
                Vector3 color = new Vector3(1.0* j / width,1.0* i / height,0.2);
                data[i*width*3+j*3] = (int)(255.999*color.r());
                data[i*width*3+j*3+1] = (int)(255.999*color.g());
                data[i*width*3+j*3+2] = (int)(255.999*color.b());
            }
        }
        renderImage.save(data,"./output/chapter2-helloworld-vector3.png");
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
        JPNG renderImage = JPNG.createImage(width,height, ImageFormatEnum.PPM_PIXEL_ASCILL);
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
        renderImage.save(data,"./output/chapter3-backgroud.png");
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
        JPNG renderImage = JPNG.createImage(width,height, ImageFormatEnum.PPM_PIXEL_ASCILL);
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
        renderImage.save(data,"./output/chapter4-addshphere.png");
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

        JPNG renderImage = JPNG.createImage(width,height, ImageFormatEnum.PPM_PIXEL_ASCILL);
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
        renderImage.save(data,"./output/chapter5-visualizenormals-mutipleobject.png");
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
        JPNG renderImage = JPNG.createImage(width,height, ImageFormatEnum.PPM_PIXEL_ASCILL);
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
        renderImage.save(data,"./output/chapter6-antialiasing.png");
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
        JPNG renderImage = JPNG.createImage(width,height, ImageFormatEnum.PPM_PIXEL_ASCILL);
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
        renderImage.save(data,"./output/chapter7-diffusematerials.png");
    }

    // diffuse materials
    public static void chapter8(){
        int width = 200;
        int height = 100;
        int samples = 100;
        Render render = new Render();
        Camera camera = new Camera();
        HitObjectList world = new HitObjectList(4);
        world.hitableList.add(new Sphere(new Vector3(0.0,0.0,-1.0),0.5,new Lambertian(new Vector3(0.8,0.3,0.3))));
        world.hitableList.add(new Sphere(new Vector3(0.0,-100.5,-1.0),100,new Lambertian(new Vector3(0.8,0.8,0.0))));
        world.hitableList.add(new Sphere(new Vector3(1.0,0.0,-1.0),0.5,new Metal(new Vector3(0.8,0.6,0.2),1.0)));
        world.hitableList.add(new Sphere(new Vector3(-1.0,0.0,-1.0),0.5,new Metal(new Vector3(0.8,0.8,0.8),0.3)));
        JPNG renderImage = JPNG.createImage(width,height, ImageFormatEnum.PPM_PIXEL_ASCILL);
        int[] data = new int[height*width*3];
        for(int i = height-1;i >= 0 ;i--){
            for(int j = 0;j < width;j++){
                Vector3 color = Vector3.getZeroVector();
                // Average sampling
                for(int s = 0;s < samples;s++){
                    double u = 1.0*(j+Math.random())/width;
                    double v = 1.0*(i+Math.random())/height;
                    Ray ray = camera.getRay(u,v);
                    color = color.plus(render.metalMaterials(ray,world,0));
                }
                color = color.scale(1.0/samples);
                // gamma correction
                color = new Vector3(Math.sqrt(color.r()),Math.sqrt(color.g()),Math.sqrt(color.b()));
                data[i*width*3+j*3] = (int)(255.999*color.r());
                data[i*width*3+j*3+1] = (int)(255.999*color.g());
                data[i*width*3+j*3+2] = (int)(255.999*color.b());
            }
        }
        renderImage.save(data,"./output/chapter8-metalmaterials.png");
    }

    // dielectric materials
    public static void chapter9(){
        int width = 200;
        int height = 100;
        int samples = 100;
        Render render = new Render();
        Camera camera = new Camera();
        HitObjectList world = new HitObjectList(5);
        world.hitableList.add(new Sphere(new Vector3(0.0,0.0,-1.0),0.5,new Lambertian(new Vector3(0.1,0.2,0.5))));
        world.hitableList.add(new Sphere(new Vector3(0.0,-100.5,-1.0),100,new Lambertian(new Vector3(0.8,0.8,0.0))));
        world.hitableList.add(new Sphere(new Vector3(1.0,0.0,-1.0),0.5,new Metal(new Vector3(0.8,0.6,0.2),0.3)));
        world.hitableList.add(new Sphere(new Vector3(-1.0,0.0,-1.0),0.5,new Dielectric(1.5)));
        world.hitableList.add(new Sphere(new Vector3(-1.0,0.0,-1.0),-0.45,new Dielectric(1.5)));
        JPNG renderImage = JPNG.createImage(width,height, ImageFormatEnum.PPM_PIXEL_ASCILL);
        int[] data = new int[height*width*3];
        for(int i = height-1;i >= 0 ;i--){
            for(int j = 0;j < width;j++){
                Vector3 color = Vector3.getZeroVector();
                // Average sampling
                for(int s = 0;s < samples;s++){
                    double u = 1.0*(j+Math.random())/width;
                    double v = 1.0*(i+Math.random())/height;
                    Ray ray = camera.getRay(u,v);
                    color = color.plus(render.metalMaterials(ray,world,0));
                }
                color = color.scale(1.0/samples);
                // gamma correction
                color = new Vector3(Math.sqrt(color.r()),Math.sqrt(color.g()),Math.sqrt(color.b()));
                data[i*width*3+j*3] = (int)(255.999*color.r());
                data[i*width*3+j*3+1] = (int)(255.999*color.g());
                data[i*width*3+j*3+2] = (int)(255.999*color.b());
            }
        }
        renderImage.save(data,"./output/chapter9-dielectricmaterials.png");
    }

    // positionale camera
    public static void chapter10(){
        int width = 200;
        int height = 100;
        int samples = 100;
        Render render = new Render();
        Camera camera = new Camera(new Vector3(-2.0,2.0,1.0),new Vector3(0.0,0.0,-1.0),new Vector3(0.0,1.0,0.0),90.0,1.0*width / height);
        HitObjectList world = new HitObjectList(5);
        world.hitableList.add(new Sphere(new Vector3(0.0,0.0,-1.0),0.5,new Lambertian(new Vector3(0.1,0.2,0.5))));
        world.hitableList.add(new Sphere(new Vector3(0.0,-100.5,-1.0),100,new Lambertian(new Vector3(0.8,0.8,0.0))));
        world.hitableList.add(new Sphere(new Vector3(1.0,0.0,-1.0),0.5,new Metal(new Vector3(0.8,0.6,0.2),0.3)));
        world.hitableList.add(new Sphere(new Vector3(-1.0,0.0,-1.0),0.5,new Dielectric(1.5)));
        world.hitableList.add(new Sphere(new Vector3(-1.0,0.0,-1.0),-0.45,new Dielectric(1.5)));
        JPNG renderImage = JPNG.createImage(width,height, ImageFormatEnum.PPM_PIXEL_ASCILL);
        int[] data = new int[height*width*3];
        for(int i = height-1;i >= 0 ;i--){
            for(int j = 0;j < width;j++){
                Vector3 color = Vector3.getZeroVector();
                // Average sampling
                for(int s = 0;s < samples;s++){
                    double u = 1.0*(j+Math.random())/width;
                    double v = 1.0*(i+Math.random())/height;
                    Ray ray = camera.getRay(u,v);
                    color = color.plus(render.metalMaterials(ray,world,0));
                }
                color = color.scale(1.0/samples);
                // gamma correction
                color = new Vector3(Math.sqrt(color.r()),Math.sqrt(color.g()),Math.sqrt(color.b()));
                data[i*width*3+j*3] = (int)(255.999*color.r());
                data[i*width*3+j*3+1] = (int)(255.999*color.g());
                data[i*width*3+j*3+2] = (int)(255.999*color.b());
            }
        }
        renderImage.save(data,"./output/chapter10-positionale-camera.png");
    }

    // defocus blur
    public static void chapter11(){
        int width = 200;
        int height = 100;
        int samples = 100;
        Render render = new Render();
        Vector3 lookfrom = new Vector3(3.0,3.0,2.0);
        Vector3 lookat = new Vector3(0.0,0.0,-1.0);
        double distTofocus = lookfrom.minus(lookat).length();
        Camera camera = new Camera(lookfrom,lookat,new Vector3(0.0,1.0,0.0),20.0,1.0*width / height,2.0,distTofocus);
        HitObjectList world = new HitObjectList(5);
        world.hitableList.add(new Sphere(new Vector3(0.0,0.0,-1.0),0.5,new Lambertian(new Vector3(0.1,0.2,0.5))));
        world.hitableList.add(new Sphere(new Vector3(0.0,-100.5,-1.0),100,new Lambertian(new Vector3(0.8,0.8,0.0))));
        world.hitableList.add(new Sphere(new Vector3(1.0,0.0,-1.0),0.5,new Metal(new Vector3(0.8,0.6,0.2),0.3)));
        world.hitableList.add(new Sphere(new Vector3(-1.0,0.0,-1.0),0.5,new Dielectric(1.5)));
        world.hitableList.add(new Sphere(new Vector3(-1.0,0.0,-1.0),-0.45,new Dielectric(1.5)));
        JPNG renderImage = JPNG.createImage(width,height, ImageFormatEnum.PPM_PIXEL_ASCILL);
        int[] data = new int[height*width*3];
        for(int i = height-1;i >= 0 ;i--){
            for(int j = 0;j < width;j++){
                Vector3 color = Vector3.getZeroVector();
                // Average sampling
                for(int s = 0;s < samples;s++){
                    double u = 1.0*(j+Math.random())/width;
                    double v = 1.0*(i+Math.random())/height;
                    Ray ray = camera.getRayDefocus(u,v);
                    color = color.plus(render.metalMaterials(ray,world,0));
                }
                color = color.scale(1.0/samples);
                // gamma correction
                color = new Vector3(Math.sqrt(color.r()),Math.sqrt(color.g()),Math.sqrt(color.b()));
                data[i*width*3+j*3] = (int)(255.999*color.r());
                data[i*width*3+j*3+1] = (int)(255.999*color.g());
                data[i*width*3+j*3+2] = (int)(255.999*color.b());
            }
        }
        renderImage.save(data,"./output/chapter11-defocusblur.png");
    }

    // lots of random spheres
    public static void chapter12(){
        int width = 1000;
        int height = 500;
        int samples = 100;
        Vector3 lookfrom = new Vector3(-2.0,1.2,2.7);
        Vector3 lookat = new Vector3(0.0,0.0,-2.0);
        double distTofocus = lookfrom.minus(lookat).length();
        Render render = new Render();
        Camera camera = new Camera(lookfrom,lookat,new Vector3(0.0,1.0,0.0),90.0,1.0*width / height,0.01,distTofocus);
        Hitable world = HitObjectList.randomScene();
        JPNG renderImage = JPNG.createImage(width,height, ImageFormatEnum.PPM_PIXEL_ASCILL);
        int[] data = new int[height*width*3];
        for(int i = height-1;i >= 0 ;i--){
            for(int j = 0;j < width;j++){
                Vector3 color = Vector3.getZeroVector();
                // Average sampling
                for(int s = 0;s < samples;s++){
                    double u = 1.0*(j+Math.random())/width;
                    double v = 1.0*(i+Math.random())/height;
                    Ray ray = camera.getRayDefocus(u,v);
                    color = color.plus(render.metalMaterials(ray,world,0));
                }
                color = color.scale(1.0/samples);
                // gamma correction
                color = new Vector3(Math.sqrt(color.r()),Math.sqrt(color.g()),Math.sqrt(color.b()));
                data[i*width*3+j*3] = (int)(255.999*color.r());
                data[i*width*3+j*3+1] = (int)(255.999*color.g());
                data[i*width*3+j*3+2] = (int)(255.999*color.b());
            }
        }
        renderImage.save(data,"./output/chapter12-lotsofrandomspheres.png");
    }

    public static void main(String[] args) {
          chapter1();
          chapter2();
          chapter3();
          chapter4();
          chapter5();
          chapter6();
          chapter7();
          chapter8();
          chapter9();
          chapter10();
          chapter11();
          chapter12();
    }
}
