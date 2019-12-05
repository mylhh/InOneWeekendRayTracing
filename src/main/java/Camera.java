
public class Camera {
    Vector3 lowerLeftPoint;
    Vector3 horizontal;
    Vector3 vertical;
    Vector3 origin;

    public Camera(){
        this.lowerLeftPoint = new Vector3(-2.0,-1.0,-1.0);
        this.horizontal = new Vector3(4.0,0.0,0.0);
        this.vertical = new Vector3(0.0,2.0,0.0);
        this.origin = Vector3.getZeroVector();
    }

    public Ray getRay(double u,double v){
        return new Ray(origin,lowerLeftPoint.plus(horizontal.scale(u).plus(vertical.scale(v))));
    }
}
