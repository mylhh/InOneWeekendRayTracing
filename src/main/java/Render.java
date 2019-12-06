import javafx.util.Pair;

import java.util.Optional;

/**
 * The {@code Render} class render scenes and objects.
 */
public class Render {

    public final static int MAX_OF_RECURSION = 50;
    Vector3 startColor;
    Vector3 endColor;

    public Render() {
        // default color
        this.startColor = new Vector3(1.0, 1.0, 1.0);
        this.endColor = new Vector3(0.5, 0.7, 1.0);
    }

    /**
     * Using linear interpolation generate background,default colors are
     * white(t=0) and bule(t=1).Reference this function:
     * {@code blended_value=(1-t)start_value+t*end_value}.
     *
     * @param ray from origin(0,0,0) to screen flat
     * @return color at the intersection of a ray and a plane
     */
    public Vector3 backgroundColor(Ray ray){
        Vector3 unitDrection = ray.getDirection().makeUnitVector();
        double t = (unitDrection.y()+1.0) / 2;
        return this.startColor.scale(1.0 - t).plus(this.endColor.scale(t));
    }

    /**
     * In the scene base the method {@link Render#backgroundColor(Ray)} add a Sphere.
     *
     * @param ray from origin(0,0,0) to screen flat
     * @return color at the intersection of a ray with object
     */
    public Vector3 addSphere(Ray ray){
        if(hitSphere(new Vector3(0,0,1),0.5,ray)){
            return new Vector3(1,0,0);
        }
        return backgroundColor(ray);
    }

    /**
     * Base the method {@link Render#addSphere(Ray)} we get a red sphere
     * in the scene,now we compute and visualize normals on surface.
     *
     * @param ray from origin(0,0,0) to screen flat
     * @return color correspond normal vector
     */
    public Vector3 visualizeSphereNormals(Ray ray){
        double t = hitPointOnSphere(new Vector3(0.0,0.0,-1.0),0.5,ray);
        if(t > 0.0){
            Vector3 normal = ray.pointAtParameter(t).minus(new Vector3(0.0,0.0,-1.0)).makeUnitVector();
            return new Vector3(normal.x()+1,normal.y()+1,normal.z()+1).scale(0.5);
        }
        return backgroundColor(ray);
    }

    /**
     * Base the method {@link Render#visualizeSphereNormals(Ray)} we add other sphere in scene.
     *
     * @param ray
     * @param world object list
     * @return
     */
    public Vector3 addMutipleObject(Ray ray,Hitable world){
        Optional<HitRecord> optionalHitRecord = world.hit(ray,0.0,Double.MAX_VALUE);
        if(optionalHitRecord.isPresent()){
            HitRecord record = optionalHitRecord.get();
            return new Vector3(record.normal.x()+1,record.normal.y()+1,record.normal.z()+1).scale(0.5);
        }
        return backgroundColor(ray);
    }

    /**
     * Base the method {@link Render#addMutipleObject(Ray, Hitable)} add diffuse materials.
     * Using recursion to implement ray tracing.
     *
     * @param ray
     * @param world world object list
     * @return color with diffuse materials
     */
    public Vector3 diffuseMaterials(Ray ray,Hitable world){
        Optional<HitRecord> optionalHitRecord = world.hit(ray,0.001,Double.MAX_VALUE);
        if(optionalHitRecord.isPresent()){
            HitRecord record = optionalHitRecord.get();
            Vector3 target = record.hitPoint.plus(record.normal).plus(randomInUnitSphere());
            return diffuseMaterials(new Ray(record.hitPoint,target.minus(record.hitPoint)),world).scale(0.5);
        }
        return backgroundColor(ray);
    }

    /**
     * Add metal materials.
     * @param ray
     * @param world world object list
     * @param depth recursion depth
     * @return color
     */
    public Vector3 metalMaterials(Ray ray,Hitable world,int depth){
        Optional<HitRecord> optionalHitRecord = world.hit(ray,0.001,Double.MAX_VALUE);
        if(optionalHitRecord.isPresent()){
            HitRecord record = optionalHitRecord.get();
            Optional<Pair<Vector3,Ray>> pair = record.material.scatter(ray,record);
            if(depth < MAX_OF_RECURSION && pair.isPresent()){
                Vector3 attenuuation = pair.get().getKey();
                Ray scattered = pair.get().getValue();
                return attenuuation.vectorMultiplication(metalMaterials(scattered,world,depth+1));
            }else {
                return Vector3.getZeroVector();
            }

        }
        return backgroundColor(ray);
    }

    /**
     * Set up a sphere in 3D space,and detecting whether the ray intersect with sphere.
     *
     * @param center sphere center
     * @param radius sphere radius
     * @param ray from view point to screen plane
     * @return {@code true} when ray intersects the sphere,otherwise return {@code false}
     */
    public boolean hitSphere(Vector3 center, double radius, Ray ray){
        Vector3 A = ray.getOrigin();
        Vector3 B = ray.getDirection();
        double a = B.dot(B);
        double b = B.scale(2.0).dot(A.minus(center));
        double c = A.minus(center).dot(A.minus(center)) - radius*radius;
        double discriminant = b*b - 4*a*c;
        return discriminant > 0;
    }

    /**
     * Return parameter {@code t} on the ray {@code P(t)},when ray intersects the sphere.
     * Otherwise,retuen {@code -1}.
     *
     * @param center sphere center
     * @param radius sphere radius
     * @param ray from view point to screen plane
     * @return {@code true} when ray intersects the sphere,otherwise,retuen {@code -1}
     */
    public double hitPointOnSphere(Vector3 center, double radius, Ray ray){
        Vector3 A = ray.getOrigin();
        Vector3 B = ray.getDirection();
        double a = B.dot(B);
        double b = B.scale(2.0).dot(A.minus(center));
        double c = A.minus(center).dot(A.minus(center)) - radius*radius;
        double discriminant = b*b - 4*a*c;
        if(discriminant < 0)
            return -1.0;
        return (-b - Math.sqrt(discriminant)) / (2.0*a);
    }

    /**
     * Generate a random vector in an unit sphere that
     * is tangent to the hit point.
     *
     * @return ramdon vector3
     */
    public static Vector3 randomInUnitSphere(){
        Vector3 randomVector;
        do {
            randomVector = new Vector3(Math.random(),Math.random(),Math.random())
                    .scale(2.0).minus(new Vector3(1.0,1.0,1.0));
        }while (randomVector.squaredLength() >= 1.0);
        return randomVector;
    }



}
