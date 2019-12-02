
/**
 * The {@code Vector} class represent a <em>d</em>-dimensional Euclidean vector.
 * It includes methods for addition, subtraction,
 * dot product, scalar product, unit vector, Euclidean norm, and the Euclidean
 * distance between two vectors.
 * For additional documentation,
 * see <a href="https://algs4.cs.princeton.edu/12oop">Section 1.2</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 * By the way,I update some definition not exactly the same.
 * I also using this class as a base, encapsulate a class see {@link Vector3}.
 */
public class Vector {

    private int d;             // dimension of vector
    private double[] elements; // array of vector's components

    /**
     * Initializes a d-dimensional zero vector.
     *
     * @param dimension the dimension of the vector
     */
    public Vector(int dimension){
        this.d = dimension;
        elements = new double[dimension];
    }

    /**
     * Initializes a vector from either an array or a vararg list.
     * The vararg syntax supports a constructor that takes a variable number of
     * arugments such as Vector x = new Vector(1.0, 2.0, 3.0, 4.0).
     *
     * @param a  the array or vararg list
     */
    public Vector(double... a) {
        d = a.length;

        // defensive copy so that client can't alter our copy of data[]
        elements = new double[d];
        for (int i = 0; i < d; i++)
            elements[i] = a[i];
    }

    /**
     * Returns the dimension of this vector.
     *
     * @return the dimension of this vector
     */
    public int dimension() {
        return d;
    }

    /**
     * Returns the dot product of this vector with the specified vector.
     *
     * @param that the other vector
     * @return the dot product of this vector and that vector
     * @throws IllegalArgumentException if the dimension of the tow vectors are not equal
     */
    public double dot(Vector that){
        if(this.d != that.d) throw new IllegalArgumentException("Dimensions not equal");
        double result = 0.0;
        for(int i = 0;i < this.d;i++){
            result += this.elements[i] * that.elements[i];
        }
        return result;
    }

    /**
     * Returns the magnitude of this vector.
     * This is also known as the L2 norm or the Euclidean norm.
     *
     * @return the magnitude of this vector
     */
    public double magnitude() {
        return Math.sqrt(this.dot(this));
    }

    /**
     * Returns the sum of this vector and the specified vector.
     *
     * @param  that the vector to add to this vector
     * @return the vector whose value is {@code (this + that)}
     * @throws IllegalArgumentException if the dimensions of the two vectors are not equal
     */
    public Vector plus(Vector that) {
        if (this.d != that.d) throw new IllegalArgumentException("Dimensions not equal");
        Vector vector = new Vector(d);
        for (int i = 0; i < d; i++)
            vector.elements[i] = this.elements[i] + that.elements[i];
        return vector;
    }

    /**
     * Returns the difference between this vector and the specified vector.
     *
     * @param  that the vector to subtract from this vector
     * @return the vector whose value is {@code (this - that)}
     * @throws IllegalArgumentException if the dimensions of the two vectors are not equal
     */
    public Vector minus(Vector that) {
        if (this.d != that.d) throw new IllegalArgumentException("Dimensions not equal");
        Vector vector = new Vector(d);
        for (int i = 0; i < d; i++)
            vector.elements[i] = this.elements[i] - that.elements[i];
        return vector;
    }

    /**
     * Returns the Euclidean distance between this vector and the specified vector.
     *
     * @param  that the other vector
     * @return the Euclidean distance between this vector and that vector
     * @throws IllegalArgumentException if the dimensions of the two vectors are not equal
     */
    public double distanceTo(Vector that) {
        if (this.d != that.d) throw new IllegalArgumentException("Dimensions not equal");
        return this.minus(that).magnitude();
    }

    /**
     * return element by it's index.
     *
     * @param index the elements arrays index
     * @return element value
     */
    public double getElement(int index){
        return this.elements[index];
    }

    /**
     * Returns the scalar-vector product of this vector and the specified scalar
     *
     * @param  alpha the scalar
     * @return the vector whose value is {@code (alpha * this)}
     */
    public Vector scale(double alpha) {
        Vector c = new Vector(d);
        for (int i = 0; i < d; i++)
            c.elements[i] = alpha * elements[i];
        return c;
    }

    /**
     * Returns a unit vector in the direction of this vector.
     *
     * @return a unit vector in the direction of this vector
     * @throws ArithmeticException if this vector is the zero vector
     */
    public Vector direction() {
        if (this.magnitude() == 0.0) throw new ArithmeticException("Zero-vector has no direction");
        return this.scale(1.0 / this.magnitude());
    }

}
