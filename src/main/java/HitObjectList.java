import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The {@code HitObjectList} represent mutiple object in ray tracing scene.
 *
 */

public class HitObjectList implements Hitable {

    List<Hitable> hitableList;
    private int size;

    public HitObjectList(int size){
        this.size = size;
        hitableList = new ArrayList<>(size);
    }

    public HitObjectList(List<Hitable> hitableList){
        this.size = hitableList.size();
        this.hitableList = hitableList;
    }

    /**
     * Return whether the ray intersects the objects.
     *
     * @param ray    from view point to screen plane
     * @param tMin   min value of parameter {@code t} range
     * @param tMax   max value of parameter {@code t} range
     * @return {@code true} when ray intersects the sphere,otherwise return {@code false}
     */
    @Override
    public Optional<HitRecord> hit(Ray ray, double tMin, double tMax) {
        Optional<HitRecord> optionalHitRecord = Optional.empty();
        double closest = tMax;
        for(Hitable hitObject:hitableList){
            Optional<HitRecord> eachOptional = hitObject.hit(ray,tMin,closest);
            if(eachOptional.isPresent()){
                closest = eachOptional.get().t;
                optionalHitRecord = eachOptional;
            }
        }
        return optionalHitRecord;
    }

    static Hitable randomScene(){
        int n = 500;
        HitObjectList hitObjectList = new HitObjectList(n + 1);
        List<Hitable> list = hitObjectList.hitableList;
        list.add(new Sphere(new Vector3(0.0,-1000.0,0.0),1000.0,new Lambertian(new Vector3(0.5,0.5,0.5))));

        for (int a = -11;a < 11;a++){
            for (int b = -11;b < 11;b++){
                double chooseMat = Math.random();
                Vector3 center = new Vector3(a+0.9*Math.random(),0.2,b+0.9*Math.random());
                if(center.minus(new Vector3(4.0,0.2,0)).length() > 0.9){
                    if(chooseMat < 0.8){
                        list.add(new Sphere(center,0.2,
                                new Lambertian(new Vector3(Math.random()*Math.random(),Math.random()*Math.random(),Math.random()*Math.random()))));
                    }else if(chooseMat < 0.95){
                        list.add(new Sphere(center,0.2,
                                new Metal(new Vector3(0.5*(1+Math.random()),0.5*(1+Math.random()),0.5*(1+Math.random())),0.5*Math.random())));
                    }else {
                        list.add(new Sphere(center,0.2,new Dielectric(1.5)));
                    }
                }
            }
        }

        list.add(new Sphere(new Vector3(-2.5,1.0,0.0),1.0,new Metal(new Vector3(0.7,0.6,0.5),0.0)));
        list.add(new Sphere(new Vector3(0.0,1.0,0.0),1.0,new Dielectric(1.8)));
        list.add(new Sphere(new Vector3(2.5,1.0,0.0),1.0,new Lambertian(new Vector3(0.4,0.2,0.1))));
        return hitObjectList;
    }
}
