
/**
 * Represent some objects that a ray might hit.Subclass must be
 * implement member function {@code boolean hit()}.
 */
public interface Hitable {

    /**
     * Return whether the ray intersects the objects.
     *
     * @param ray from view point to screen plane
     * @param tMin min value of parameter {@code t} range
     * @param tMax max value of parameter {@code t} range
     * @param record hit point information
     * @return {@code true} when ray intersects the sphere,otherwise return {@code false}
     */
    boolean hit(Ray ray,double tMin,double tMax,HitRecord record);
}
