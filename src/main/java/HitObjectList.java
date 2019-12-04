import java.util.ArrayList;
import java.util.List;

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
     * @param record hit point information
     * @return {@code true} when ray intersects the sphere,otherwise return {@code false}
     */
    @Override
    public boolean hit(Ray ray, double tMin, double tMax, HitRecord record) {
        boolean hitAnything = false;
        HitRecord hitRecord = new HitRecord();
        double closest = tMax;
        for(Hitable hitObject:hitableList){
            if(hitObject.hit(ray,tMin,closest,hitRecord)){
                hitAnything = true;
                closest = hitRecord.t;
                record = hitRecord;
                // TODO
                System.out.println(hitRecord);
            }
        }
        //System.out.println(hitRecord);
        return hitAnything;
    }
}
