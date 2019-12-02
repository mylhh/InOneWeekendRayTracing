/**
 * The {@code Vector3} class represent a 3-dimensional vector.
 * It  is a wrapper class for {@link Vector} that dimension constant three.
 */

public class Vector3 {

    private final static int THREE_DIMENSION = 3;
    private final static int INDEX_OF_XAXIS = 0,INDEX_OF_RED = 0;
    private final static int INDEX_OF_YAXIS = 1,INDEX_OF_GREEN = 1;
    private final static int INDEX_OF_ZAXIS = 2,INDEX_OF_BLUE = 2;

    private Vector vec3;

    /**
     * Initializes a 3-dimensional zero vector.
     */
    public Vector3(){
        this.vec3 = new Vector(THREE_DIMENSION);
    }

    /**
     * Initializes a 3-dimensional vector with a already existing vector.
     *
     * @param vec3 other vector object
     */
    public Vector3(Vector vec3){
        if(vec3.dimension() != THREE_DIMENSION) throw new IllegalArgumentException("Not a three-dimensional vector");
        this.vec3 = vec3;
    }

    /**
     * Initializes a vector from an array.
     *
     * @param values the array
     */
    public Vector3(double... values){
        if(values.length != THREE_DIMENSION) throw new IllegalArgumentException("Not a three-dimensional vector");
        this.vec3 = new Vector(values);
    }

    /**
     * The vector represent for locations,directions,offsets,whatever.
     * Like this {@code (x,y,z)},return the value of x-axis,also see {@link Vector3#y()},{@link Vector3#z()}.
     *
     * @return the value of x-axis.
     */
    public double x(){ return  this.vec3.getElement(INDEX_OF_XAXIS); }

    /**
     * The vector represent for locations,directions,offsets,whatever.
     * Like this {@code (x,y,z)},return the value of x-axis,also see {@link Vector3#x()},{@link Vector3#z()}.
     *
     * @return the value of y-axis.
     */
    public double y(){ return  this.vec3.getElement(INDEX_OF_YAXIS); }

    /**
     * The vector represent for locations,directions,offsets,whatever.
     * Like this {@code (x,y,z)},return the value of x-axis,also see {@link Vector3#x()},{@link Vector3#y()}.
     *
     * @return the value of z-axis.
     */
    public double z(){ return  this.vec3.getElement(INDEX_OF_ZAXIS); }

    /**
     * The vector represent for RGB color space.Like this {@code (r,g,b)},
     * return the value of red channel,also see {@link Vector3#g()},{@link Vector3#b()}.
     *
     * @return the value of red channel.
     */
    public double r(){ return  this.vec3.getElement(INDEX_OF_RED); }

    /**
     * The vector represent for RGB color space.Like this {@code (r,g,b)},
     * return the value of green channel,also see {@link Vector3#r()},{@link Vector3#b()}.
     *
     * @return the value of green channel.
     */
    public double g(){ return  this.vec3.getElement(INDEX_OF_GREEN); }

    /**
     * The vector represent for RGB color space.Like this {@code (r,g,b)},
     * return the value of blue channel,also see {@link Vector3#r()},{@link Vector3#g()}.
     *
     * @return the value of blue channel.
     */
    public double b(){ return  this.vec3.getElement(INDEX_OF_BLUE); }

    /**
     * Return the result of vector dot product itself,
     * for example {@code (x,y,z)*(x,y,z) = x^2+y^2+z^2}.
     * @return elements sum of squares
     */
    public double squaredLength(){
        return this.vec3.dot(this.vec3);
    }

    /**
     * Returns the magnitude of this vector.
     * This is also known as the L2 norm or the Euclidean norm.
     * In the tutorial <em>In One weekend ray tracing</em> it call length.
     *
     * @return the magnitude of this vector
     */
    public double length(){
        return  this.vec3.magnitude();
    }

    /**
     * Returns the dot product of this vector with the specified vector.
     * Also see {@link Vector#dot(Vector)}.
     *
     * @param that the other vector
     * @return the dot product of this vector and that vector
     */
    public double dot(Vector3 that){
        return this.vec3.dot(that.vec3);
    }

    /**
     * Returns the sum of this vector and the specified vector.
     * Also see {@link Vector#plus(Vector)}}.
     *
     * @param  that the vector to add to this vector
     * @return the vector whose value is {@code (this + that)}
     */
    public Vector3 plus(Vector3 that){
        return new Vector3(this.vec3.plus(that.vec3));
    }

    /**
     * Returns the difference between this vector and the specified vector.
     * Also see {@link Vector#minus(Vector)}.
     *
     * @param  that the vector to subtract from this vector
     * @return the vector whose value is {@code (this - that)}
     */
    public Vector3 minus(Vector3 that){
        return new Vector3(this.vec3.minus(that.vec3));
    }

    /**
     * Returns the Euclidean distance between this vector and the specified vector.
     * Also see {@link Vector#distanceTo(Vector)}.
     *
     * @param  that the other vector
     * @return the Euclidean distance between this vector and that vector
     */
    public double distanceTo(Vector3 that){
        return this.vec3.distanceTo(that.vec3);
    }

    /**
     * return element by it's index.
     * Also see {@link Vector#getElement(int)}.
     *
     * @param index the elements arrays index
     * @return element value
     */
    public double getElement(int index){
        return this.vec3.getElement(index);
    }

    /**
     * Returns the scalar-vector product of this vector and the specified scalar
     * Also see {@link Vector#scale(double)}.
     * @param  alpha the scalar
     * @return the vector whose value is {@code (alpha * this)}
     */
    public Vector3 scale(double alpha){
        return new Vector3(this.vec3.scale(alpha));
    }

    /**
     * Returns a unit vector in the direction of this vector.
     * Also see {@link Vector#direction()}.
     *
     * @return a unit vector in the direction of this vector
     */
    public Vector3 makeUnitVector(){
        return new Vector3(this.vec3.direction());
    }

    /**
     * Returns the cross product of this vector with the specified vector.Specially,
     * cross operation does not satisfy exchange law,for example {@code v1 x v2 != v2 x v1}.
     * @param that the other vector
     * @return the cross product of this vector and that vector
     */
    public Vector3 cross(Vector3 that){
        double u1 = this.vec3.getElement(INDEX_OF_XAXIS);
        double u2 = this.vec3.getElement(INDEX_OF_YAXIS);
        double u3 = this.vec3.getElement(INDEX_OF_ZAXIS);
        double v1 = that.vec3.getElement(INDEX_OF_XAXIS);
        double v2 = that.vec3.getElement(INDEX_OF_YAXIS);
        double v3 = that.vec3.getElement(INDEX_OF_ZAXIS);
        return new Vector3((u2*v3 - u3*v2),-(u1*v3 - u3*v1),(u1*v2 - u2*v1));
    }
}
