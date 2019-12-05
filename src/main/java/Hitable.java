import java.util.Optional;

/**
 * Represent some objects that a ray might hit.Subclass must be
 * implement member function {@code boolean hit()}.
 */
public interface Hitable {

    /**
     * Return a container {@code Optional<HitRecord>} may be contrain
     * an information of the ray intersects the objects.
     *
     * @param ray from view point to screen plane
     * @param tMin min value of parameter {@code t} range
     * @param tMax max value of parameter {@code t} range
     * @return {@code Optional<HitRecord>} when ray intersects the objects will contain a HitRecord object
     */
    Optional<HitRecord> hit(Ray ray, double tMin, double tMax);
}
