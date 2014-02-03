/*
 * Copyright (c) 2009-2010 jMonkeyEngine
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'jMonkeyEngine' nor the names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.saintandreas.math;

import javax.annotation.Nonnull;

/**
 * <code>Vector2f</code> defines a Vector for a two float value vector.
 * 
 * @author Mark Powell
 * @author Joshua Slack
 * @author Brad Davis
 */
public final class Vector2f extends Vector<Vector2f> implements java.io.Serializable {
  static final long serialVersionUID = 1;
  public static final Vector2f ZERO = new Vector2f(0, 0);
  public static final Vector2f UNIT_X = new Vector2f(1, 0);
  public static final Vector2f UNIT_Y = new Vector2f(0, 1);
  public static final Vector2f UNIT_XY = new Vector2f(1, 1);

  public final float x;
  public final float y;

  /**
   * Constructor instantiates a new <code>Vector3f</code> with default
   * values of (0,0,0).
   *
   */
  public Vector2f() {
      x = y = 0;
  }

  /**
   * Creates a Vector2f with the given initial x and y values.
   * 
   * @param x
   *          The x value of this Vector2f.
   * @param y
   *          The y value of this Vector2f.
   */
  public Vector2f(float x, float y) {
    this.x = x;
    this.y = y;
  }

  public float getX() {
    return x;
  }

  public float getY() {
    return y;
  }

  /**
   * <code>hashCode</code> returns a unique code for this vector object based on
   * it's values. If two vectors are logically equivalent, they will return the
   * same hash code value.
   * 
   * @return the hash code value of this vector.
   */
  @Override
  public int hashCode() {
    int hash = 37;
    hash += 37 * hash + Float.floatToIntBits(x);
    hash += 37 * hash + Float.floatToIntBits(y);
    return hash;
  }

  /**
   * <code>toString</code> returns the string representation of this vector
   * object. The format of the string is such: com.jme.math.Vector2f [X=XX.XXXX,
   * Y=YY.YYYY]
   * 
   * @return the string representation of this vector.
   */
  @Override
  public String toString() {
    return "(" + x + ", " + y + ")";
  }
  /**
   * are these two vectors the same? they are is they both have the same x and y
   * values.
   * 
   * @param o
   *          the object to compare for equality
   * @return true if they are equal
   */
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Vector2f)) {
      return false;
    }

    if (this == o) {
      return true;
    }

    Vector2f comp = (Vector2f) o;
    if (Float.compare(x, comp.x) != 0)
      return false;
    if (Float.compare(y, comp.y) != 0)
      return false;
    return true;
  }

  /**
   * <code>add</code> adds a provided vector to this vector creating a resultant
   * vector which is returned. If the provided vector is null, null is returned.
   * 
   * @param vec
   *          the vector to add to this.
   * @return the resultant vector.
   */
  @Override
  public Vector2f add(@Nonnull Vector2f vec) {
    return new Vector2f(x + vec.x, y + vec.y);
  }

  /**
   * <code>dot</code> calculates the dot product of this vector with a provided
   * vector.
   * 
   * @param vec
   *          the vector to dot with this vector.
   * @return the resultant dot product of this vector and a given vector.
   */
  @Override
  public float dot(@Nonnull Vector2f vec) {
    return x * vec.x + y * vec.y;
  }

  /**
   * <code>cross</code> calculates the cross product of this vector with a
   * parameter vector v.
   * 
   * @param v
   *          the vector to take the cross product of with this.
   * @return the cross product vector.
   */
  public Vector3f cross(@Nonnull Vector2f v) {
    return new Vector3f(0, 0, determinant(v));
  }

  public float determinant(@Nonnull Vector2f v) {
    return (x * v.y) - (y * v.x);
  }

  /**
   * Sets this vector to the interpolation by changeAmnt from this to the
   * finalVec this=(1-changeAmnt)*this + changeAmnt * finalVec
   * 
   * @param finalVec
   *          The final vector to interpolate towards
   * @param changeAmnt
   *          An amount between 0.0 - 1.0 representing a percentage change from
   *          this towards finalVec
   */
  @Override
  public Vector2f interpolate(@Nonnull Vector2f finalVec, float changeAmnt) {
    return new Vector2f((1 - changeAmnt) * this.x + changeAmnt * finalVec.x,
        (1 - changeAmnt) * this.y + changeAmnt * finalVec.y);
  }

  /**
   * Sets this vector to the interpolation by changeAmnt from beginVec to
   * finalVec this=(1-changeAmnt)*beginVec + changeAmnt * finalVec
   * 
   * @param beginVec
   *          The begining vector (delta=0)
   * @param finalVec
   *          The final vector to interpolate towards (delta=1)
   * @param changeAmnt
   *          An amount between 0.0 - 1.0 representing a precentage change from
   *          beginVec towards finalVec
   */
  public static Vector2f interpolate(@Nonnull Vector2f beginVec,
      @Nonnull Vector2f finalVec, float changeAmnt) {
    return beginVec.interpolate(finalVec, changeAmnt);
  }

  /**
   * <code>lengthSquared</code> calculates the squared value of the magnitude of
   * the vector.
   * 
   * @return the magnitude squared of the vector.
   */
  @Override
  public float lengthSquared() {
    return x * x + y * y;
  }

  /**
   * <code>distanceSquared</code> calculates the distance squared between this
   * vector and vector v.
   * 
   * @param v
   *          the second vector to determine the distance squared.
   * @return the distance squared between the two vectors.
   */
  @Override
  public float distanceSquared(Vector2f v) {
    double dx = x - v.x;
    double dy = y - v.y;
    return (float) (dx * dx + dy * dy);
  }

  /**
   * <code>mult</code> multiplies this vector by a scalar. The resultant vector
   * is returned.
   * 
   * @param scalar
   *          the value to multiply this vector by.
   * @return the new vector.
   */
  @Override
  public Vector2f mult(float scalar) {
    return new Vector2f(x * scalar, y * scalar);
  }

  @Override
  public Vector2f mult(Vector2f v) {
    return new Vector2f(x * v.x, y * v.y);
  }

  /**
   * <code>smallestAngleBetween</code> returns (in radians) the minimum angle
   * between two vectors. It is assumed that both this vector and the given
   * vector are unit vectors (iow, normalized).
   * 
   * @param otherVector
   *          a unit vector to find the angle against
   * @return the angle in radians.
   */
  public float smallestAngleBetween(Vector2f otherVector) {
    float dotProduct = dot(otherVector);
    float angle = FastMath.acos(dotProduct);
    return angle;
  }

  /**
   * <code>angleBetween</code> returns (in radians) the angle required to rotate
   * a ray represented by this vector to lie colinear to a ray described by the
   * given vector. It is assumed that both this vector and the given vector are
   * unit vectors (iow, normalized).
   * 
   * @param otherVector
   *          the "destination" unit vector
   * @return the angle in radians.
   */
  @Override
  public float angleBetween(Vector2f otherVector) {
    float angle = FastMath.atan2(otherVector.y, otherVector.x)
        - FastMath.atan2(y, x);
    return angle;
  }

  /**
   * <code>getAngle</code> returns (in radians) the angle represented by this
   * Vector2f as expressed by a conversion from rectangular coordinates (
   * <code>x</code>,&nbsp;<code>y</code>) to polar coordinates
   * (r,&nbsp;<i>theta</i>).
   * 
   * @return the angle in radians. [-pi, pi)
   */
  public float getAngle() {
    return FastMath.atan2(y, x);
  }

  /**
   * Saves this Vector2f into the given float[] object.
   * 
   * @param floats
   *          The float[] to take this Vector2f. If null, a new float[2] is
   *          created.
   * @return The array, with X, Y float values in that order
   */
  @Override
  public float[] toArray() {
    return new float[] { x, y };
  }


  /**
   * are these two vectors almost the same? they both have the same x and y
   * values (within epsilon).
   * 
   * @param o
   *          the object to compare for equality
   * @return true if they are equal
   */
  @Override
  public boolean equalsEpsilon(Vector2f v, float epsilon) {
    return FastMath.isWithinEpsilon(x, v.x, epsilon)
        && FastMath.isWithinEpsilon(y, v.y, epsilon);
  }

  @Override
  public Vector2f inverse() {
    return new Vector2f(1f / x, 1f / y);
  }

  @Override
  public boolean isValid() {
    if (Float.isNaN(x) || Float.isNaN(y))
      return false;
    if (Float.isInfinite(x) || Float.isInfinite(y))
      return false;
    return true;
  }

  @Override
  public Vector2f max(Vector2f v) {
    return new Vector2f(
        Math.max(x, v.x),
        Math.max(y, v.y)
      );
  }

  @Override
  public Vector2f min(Vector2f v) {
    return new Vector2f(
        Math.min(x, v.x),
        Math.min(y, v.y)
      );
  }

}
