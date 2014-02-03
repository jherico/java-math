package org.saintandreas.math;

import java.nio.FloatBuffer;

import javax.annotation.Nonnull;

public abstract class Vector<ResultType extends Vector<ResultType>> {

  @SuppressWarnings("unchecked")
  private final ResultType This() {
    return (ResultType) this;
  }

  /**
   * <code>lengthSquared</code> calculates the squared value of the magnitude of
   * the vector.
   * 
   * @return the magnitude squared of the vector.
   */
  public abstract float lengthSquared();

  public abstract ResultType add(@Nonnull ResultType v);

  public abstract ResultType mult(float scalar);

  public abstract ResultType mult(@Nonnull ResultType scalar);
  
  public abstract float dot(@Nonnull ResultType v);

  public abstract ResultType interpolate(@Nonnull ResultType v,
      float changeAmnt);

  public abstract ResultType inverse();

  public abstract float distanceSquared(@Nonnull ResultType v);
  
  
  public abstract float angleBetween(@Nonnull ResultType v);

  public abstract float[] toArray();

  public abstract boolean isValid();

  /**
   * <code>maxLocal</code> computes the maximum value for each 
   * component in this and <code>other</code> vector. The result is stored
   * in this vector.
   * @param other 
   */
  public abstract ResultType max(@Nonnull ResultType v);

  /**
   * <code>minLocal</code> computes the minimum value for each
   * component in this and <code>other</code> vector. The result is stored
   * in this vector.
   * @param other
   */
  public abstract ResultType min(@Nonnull ResultType v);

    
  /**
   * are these two vectors almost the same? they both have the same x and y
   * values (within epsilon).
   * 
   * @param o
   *          the object to compare for equality
   * @return true if they are equal
   */
  public abstract boolean equalsEpsilon(ResultType v, float epsilon);

  /**
   * Returns true if this vector is a unit vector (length() ~= 1), returns false
   * otherwise.
   * 
   * @return true if this vector is a unit vector (length() ~= 1), or false
   *         otherwise.
   */
  public final boolean isUnitVector() {
    return FastMath.isWithinEpsilon(lengthSquared(), 1);
  }

  /**
   * <code>length</code> calculates the magnitude of this vector.
   * 
   * @return the length or magnitude of the vector.
   */
  public final float length() {
    return FastMath.sqrt(lengthSquared());
  }

  /**
   * are these two vectors almost the same? they both have the same x and y
   * values (within epsilon).
   * 
   * @param o
   *          the object to compare for equality
   * @return true if they are equal
   */
  public final boolean equalsEpsilon(ResultType v) {
    return equalsEpsilon(v, FastMath.ZERO_TOLERANCE);
  }

  /**
   * synonym for mult
   * 
   * @param scalar
   *          the value to multiply this vector by.
   * @return the new vector.
   */
  public final ResultType scale(float scalar) {
    return mult(scalar);
  }

  /**
   * <code>divide</code> divides the values of this vector by a scalar and
   * returns the result. The values of this vector remain untouched.
   * 
   * @param scalar
   *          the value to divide this vectors attributes by.
   * @return the result <code>Vector</code>.
   */
  public final ResultType divide(float scalar) {
    return mult(1f / scalar);
  }

  /**
   * <code>negate</code> returns the negative of this vector. All values are
   * negated and set to a new vector.
   * 
   * @return the negated vector.
   */
  public final ResultType negate() {
    return mult(-1);
  }

  /**
   * <code>subtract</code> subtracts the values of a given vector from those of
   * this vector creating a new vector object. If the provided vector is null,
   * an exception is thrown.
   * 
   * @param vec
   *          the vector to subtract from this vector.
   * @return the result vector.
   */
  public final ResultType subtract(ResultType vec) {
    return add(vec.negate());
  }

  /**
   * <code>normalize</code> returns the unit vector of this vector.
   * 
   * @return unit vector of this vector.
   */
  public final ResultType normalize() {
    float lengthSquared = lengthSquared();
    if (lengthSquared == 0 && FastMath.isWithinEpsilon(lengthSquared, 1)) {
      return This();
    }

    return divide(FastMath.sqrt(lengthSquared));
  }

  /**
   * <code>distance</code> calculates the distance between this vector and
   * vector v.
   * 
   * @param v
   *          the second vector to determine the distance.
   * @return the distance between the two vectors.
   */
  public final float distance(ResultType v) {
    return FastMath.sqrt(distanceSquared(v));
  }


  /**
   * <code>divide</code> divides the values of this vector by a scalar and
   * returns the result. The values of this vector remain untouched.
   *
   * @param scalar
   *            the value to divide this vectors attributes by.
   * @return the result <code>Vector</code>.
   */
  public final ResultType divide(ResultType v) {
      return mult(v.inverse());
  }
  
  public final void fillBuffer(FloatBuffer buffer) {
    buffer.put(toArray());
  }


  /**
   *
   * <code>scaleAdd</code> multiplies this vector by a scalar then adds the
   * given Vector3f.
   *
   * @param scalar
   *            the value to multiply this vector by.
   * @param add
   *            the value to add
   */
  public final ResultType scaleAdd(float scalar, ResultType add) {
    return mult(scalar).add(add);
  }

  public final ResultType project(ResultType other){
      float n = this.dot(other); // A . B
      float d = other.lengthSquared(); // |B|^2
      return other.normalize().mult(n/d);
  }


}
