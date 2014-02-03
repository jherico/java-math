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
 * <code>Vector3f</code> defines a Vector for a three float value tuple.
 * <code>Vector3f</code> can represent any three dimensional value, such as a
 * vertex, a normal, etc. Utility methods are also included to aid in
 * mathematical calculations.
 *
 * @author Mark Powell
 * @author Joshua Slack
 * @author Brad Davis
 */
public final class Vector3f extends Vector<Vector3f> implements java.io.Serializable {
    static final long serialVersionUID = 1;

    public final static Vector3f ZERO = new Vector3f(0, 0, 0);
    public final static Vector3f NAN = new Vector3f(Float.NaN, Float.NaN, Float.NaN);
    public final static Vector3f UNIT_X = new Vector3f(1, 0, 0);
    public final static Vector3f UNIT_Y = new Vector3f(0, 1, 0);
    public final static Vector3f UNIT_Z = new Vector3f(0, 0, 1);
    public final static Vector3f UNIT_XYZ = new Vector3f(1, 1, 1);
    public final static Vector3f POSITIVE_INFINITY = new Vector3f(
            Float.POSITIVE_INFINITY,
            Float.POSITIVE_INFINITY,
            Float.POSITIVE_INFINITY);
    public final static Vector3f NEGATIVE_INFINITY = new Vector3f(
            Float.NEGATIVE_INFINITY,
            Float.NEGATIVE_INFINITY,
            Float.NEGATIVE_INFINITY);


    /**
     * the x value of the vector.
     */
    public final float x;

    /**
     * the y value of the vector.
     */
    public final float y;

    /**
     * the z value of the vector.
     */
    public final float z;

    /**
     * Constructor instantiates a new <code>Vector3f</code> with default
     * values of (0,0,0).
     *
     */
    public Vector3f() {
        x = y = z = 0;
    }

    /**
     * Constructor instantiates a new <code>Vector3f</code> with provides
     * values.
     *
     * @param x
     *            the x value of the vector.
     * @param y
     *            the y value of the vector.
     * @param z
     *            the z value of the vector.
     */
    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Promote a 2 vec to a 3 vec
     * @param v
     * @param z
     */
    public Vector3f(@Nonnull Vector2f v, float z) {
      this(v.x, v.y, z);
    }

    /**
     * Promote a 2 vec to a 3 vec
     * @param v
     * @param z
     */
    public Vector3f(@Nonnull Vector2f v) {
      this(v, 0);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    /**
     * <code>hashCode</code> returns a unique code for this vector object based
     * on it's values. If two vectors are logically equivalent, they will return
     * the same hash code value.
     * @return the hash code value of this vector.
     */
    @Override
    public int hashCode() {
        int hash = 37;
        hash += 37 * hash + Float.floatToIntBits(x);
        hash += 37 * hash + Float.floatToIntBits(y);
        hash += 37 * hash + Float.floatToIntBits(z);
        return hash;
    }

    /**
     * <code>toString</code> returns the string representation of this vector.
     * The format is:
     *
     * org.jme.math.Vector3f [X=XX.XXXX, Y=YY.YYYY, Z=ZZ.ZZZZ]
     *
     * @return the string representation of this vector.
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }

    /**
     *
     * <code>add</code> adds a provided vector to this vector creating a
     * resultant vector which is returned. If the provided vector is null, null
     * is returned.
     *
     * @param vec
     *            the vector to add to this.
     * @return the resultant vector.
     */
    @Override
    public Vector3f add(@Nonnull Vector3f vec) {
        return new Vector3f(x + vec.x, y + vec.y, z + vec.z);
    }

    /**
     *
     * <code>dot</code> calculates the dot product of this vector with a
     * provided vector. If the provided vector is null, 0 is returned.
     *
     * @param v
     *            the vector to dot with this vector.
     * @return the resultant dot product of this vector and a given vector.
     */
    @Override
    public float dot(@Nonnull Vector3f v) {
        return x * v.x + y * v.y + z * v.z;
    }

    /**
     * <code>cross</code> calculates the cross product of this vector with a
     * parameter vector v.
     *
     * @param v
     *            the vector to take the cross product of with this.
     * @return the cross product vector.
     */
    public Vector3f cross(Vector3f v) {
      float resX = ((y * v.z) - (z * v.y)); 
      float resY = ((z * v.x) - (x * v.z));
      float resZ = ((x * v.y) - (y * v.x));
      return new Vector3f(resX, resY, resZ);
    }

    /**
     * <code>lengthSquared</code> calculates the squared value of the
     * magnitude of the vector.
     *
     * @return the magnitude squared of the vector.
     */
    @Override
    public float lengthSquared() {
        return x * x + y * y + z * z;
    }

    /**
     * <code>distanceSquared</code> calculates the distance squared between
     * this vector and vector v.
     *
     * @param v the second vector to determine the distance squared.
     * @return the distance squared between the two vectors.
     */
    @Override
    public float distanceSquared(@Nonnull Vector3f v) {
        double dx = x - v.x;
        double dy = y - v.y;
        double dz = z - v.z;
        return (float) (dx * dx + dy * dy + dz * dz);
    }

    /**
     *
     * <code>mult</code> multiplies this vector by a scalar. The resultant
     * vector is returned.
     *
     * @param scalar
     *            the value to multiply this vector by.
     * @return the new vector.
     */
    @Override
    public Vector3f mult(float scalar) {
        return new Vector3f(x * scalar, y * scalar, z * scalar);
    }

    /**
     * multiplies a provided vector to this vector
     * internally, 
     *
     * @param v
     *            the vector to mult to this vector.
     * @return this
     */
    @Override
    public Vector3f mult(@Nonnull Vector3f v) {
      return new Vector3f(x * v.x, y * v.y, z * v.z);
    }


    /**
     * <code>maxLocal</code> computes the maximum value for each 
     * component in this and <code>other</code> vector. The result is stored
     * in this vector.
     * @param other 
     */
    @Override
    public Vector3f max(Vector3f other){
      return new Vector3f(
          Math.max(other.x, x),
          Math.max(other.y, y),
          Math.max(other.z, z)
        );
    }

    /**
     * <code>minLocal</code> computes the minimum value for each
     * component in this and <code>other</code> vector. The result is stored
     * in this vector.
     * @param other
     */
    @Override
    public Vector3f min(Vector3f other){
      return new Vector3f(
          Math.min(other.x, x),
          Math.min(other.y, y),
          Math.min(other.z, z)
        );
    }

    /**
     * <code>angleBetween</code> returns (in radians) the angle between two vectors.
     * It is assumed that both this vector and the given vector are unit vectors (iow, normalized).
     * 
     * @param otherVector a unit vector to find the angle against
     * @return the angle in radians.
     */
    @Override
    public float angleBetween(Vector3f otherVector) {
        float dotProduct = dot(otherVector);
        float angle = FastMath.acos(dotProduct);
        return angle;
    }
    
    /**
     * Sets this vector to the interpolation by changeAmnt from this to the finalVec
     * this=(1-changeAmnt)*this + changeAmnt * finalVec
     * @param finalVec The final vector to interpolate towards
     * @param changeAmnt An amount between 0.0 - 1.0 representing a precentage
     *  change from this towards finalVec
     */
    @Override
    public Vector3f interpolate(Vector3f finalVec, float changeAmnt) {
      return new Vector3f(
          (1f-changeAmnt)*this.x + changeAmnt*finalVec.x,
          (1f-changeAmnt)*this.y + changeAmnt*finalVec.y,
          (1f-changeAmnt)*this.z + changeAmnt*finalVec.z);
    }

    /**
     * Sets this vector to the interpolation by changeAmnt from beginVec to finalVec
     * this=(1-changeAmnt)*beginVec + changeAmnt * finalVec
     * @param beginVec the beging vector (changeAmnt=0)
     * @param finalVec The final vector to interpolate towards
     * @param changeAmnt An amount between 0.0 - 1.0 representing a precentage
     *  change from beginVec towards finalVec
     */
    public static Vector3f interpolate(Vector3f beginVec,Vector3f finalVec, float changeAmnt) {
      return beginVec.interpolate(finalVec, changeAmnt);
    }

    /**
     * Check a vector... if it is null or its floats are NaN or infinite,
     * return false.  Else return true.
     * @param vector the vector to check
     * @return true or false as stated above.
     */
    @Override
    public boolean isValid() {
      if (Float.isNaN(x) ||
          Float.isNaN(y) ||
          Float.isNaN(z)) return false;
      if (Float.isInfinite(x) ||
          Float.isInfinite(y) ||
          Float.isInfinite(z)) return false;
      return true;
    }

    /*
    public static void generateOrthonormalBasis(Vector3f u, Vector3f v, Vector3f w) {
        w.normalizeLocal();
        generateComplementBasis(u, v, w);
    }

    public static void generateComplementBasis(Vector3f u, Vector3f v,
            Vector3f w) {
        float fInvLength;

        if (FastMath.abs(w.x) >= FastMath.abs(w.y)) {
            // w.x or w.z is the largest magnitude component, swap them
            fInvLength = FastMath.invSqrt(w.x * w.x + w.z * w.z);
            u.x = -w.z * fInvLength;
            u.y = 0.0f;
            u.z = +w.x * fInvLength;
            v.x = w.y * u.z;
            v.y = w.z * u.x - w.x * u.z;
            v.z = -w.y * u.x;
        } else {
            // w.y or w.z is the largest magnitude component, swap them
            fInvLength = FastMath.invSqrt(w.y * w.y + w.z * w.z);
            u.x = 0.0f;
            u.y = +w.z * fInvLength;
            u.z = -w.y * fInvLength;
            v.x = w.y * u.z - w.z * u.y;
            v.y = -w.x * u.z;
            v.z = w.x * u.y;
        }
    }
    */

    /**
     * Saves this Vector3f into the given float[] object.
     * 
     * @param floats
     *            The float[] to take this Vector3f. If null, a new float[3] is
     *            created.
     * @return The array, with X, Y, Z float values in that order
     */
    @Override
    public float[] toArray() {
      return new float[] { x, y, z};
    }

    /**
     * are these two vectors the same? they are is they both have the same x,y,
     * and z values.
     *
     * @param o
     *            the object to compare for equality
     * @return true if they are equal
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Vector3f)) { return false; }

        if (this == o) { return true; }

        Vector3f comp = (Vector3f) o;
        if (Float.compare(x,comp.x) != 0) return false;
        if (Float.compare(y,comp.y) != 0) return false;
        if (Float.compare(z,comp.z) != 0) return false;
        return true;
    }

    /**
     * @param index
     * @return x value if index == 0, y value if index == 1 or z value if index ==
     *         2
     * @throws IllegalArgumentException
     *             if index is not one of 0, 1, 2.
     */
    public float get(int index) {
        switch (index) {
            case 0:
                return x;
            case 1:
                return y;
            case 2:
                return z;
        }
        throw new IllegalArgumentException("index must be either 0, 1 or 2");
    }

    @Override
    public Vector3f inverse() {
      return new Vector3f(1f / x, 1f/ y, 1f/z);
    }

    @Override
    public boolean equalsEpsilon(Vector3f v, float epsilon) {
      // TODO Auto-generated method stub
      return false;
    }

}
