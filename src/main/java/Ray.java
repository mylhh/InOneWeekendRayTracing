import java.util.Objects;

/**
 * The {@code Ray} class represent a ray.The one thing that all ray tracers have is a
 * ray class,and computation of what color is seen along a ray.Think of a ray as a
 * function <em>{@code P(t) = A + t * B}</em>,<em>A</em> is the origin and <em>B</em>
 * is the ray direction.
 */
public class Ray {

    private Vector3 A; // origin
    private Vector3 B; // direction

    public Ray(Vector3 origin,Vector3 direction){
        this.A = origin;
        this.B = direction;
    }

    /**
     * Return the result of function <em>{@code P(t) = A + t * B}</em>.
     *
     * @param t parameter
     * @return point at parameter t
     */
    public Vector3 pointAtParameter(double t){
        // P(t) = A + t * B
        return A.plus(B.scale(t));
    }

    @Override
    public String toString(){
        return "origin: "+A+" direction: "+B;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray)) return false;
        Ray ray = (Ray) o;
        return Objects.equals(A, ray.A) &&
                Objects.equals(B, ray.B);
    }

    @Override
    public int hashCode() {
        return Objects.hash(A, B);
    }

    public Vector3 getOrigin() {
        return A;
    }

    public void setOrigin(Vector3 a) {
        this.A = a;
    }

    public Vector3 getDirection() {
        return B;
    }

    public void setDirection(Vector3 direction) {
        this.B = direction;
    }
}
