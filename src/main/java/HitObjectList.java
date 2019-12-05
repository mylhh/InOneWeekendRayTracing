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
}
