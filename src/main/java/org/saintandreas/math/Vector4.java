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

/**
 * <code>Vector4f</code> defines a Vector for a four float value tuple.
 * <code>Vector4f</code> can represent any four dimensional value, such as a
 * vertex, a normal, etc. Utility methods are also included to aid in
 * mathematical calculations.
 *
 * @author Maarten Steur
 * @author Brad Davis
 */

public abstract class Vector4<ResultType extends Vector4<ResultType>> extends Vector<ResultType> implements java.io.Serializable {
    static final long serialVersionUID = 1;
    protected abstract ResultType build(float x, float y, float z, float w);

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
     * the w value of the vector.
     */
    public final float w;


    /**
     * Constructor instantiates a new <code>Vector4f</code> with provides
     * values.
     *
     * @param x
     *            the x value of the vector.
     * @param y
     *            the y value of the vector.
     * @param z
     *            the z value of the vector.
     * @param w
     *            the w value of the vector.
     */
    public Vector4(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
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
    public final boolean equals(Object o) {
        if (!(o instanceof Vector4)) { return false; }

        if (this == o) { return true; }

        @SuppressWarnings("unchecked")
        Vector4<ResultType> comp = (Vector4<ResultType>) o;
        if (Float.compare(x,comp.x) != 0) return false;
        if (Float.compare(y,comp.y) != 0) return false;
        if (Float.compare(z,comp.z) != 0) return false;
        if (Float.compare(w,comp.w) != 0) return false;
        return true;
    }

    /**
     * <code>hashCode</code> returns a unique code for this vector object based
     * on it's values. If two vectors are logically equivalent, they will return
     * the same hash code value.
     * @return the hash code value of this vector.
     */
    @Override
    public final int hashCode() {
        int hash = 37;
        hash += 37 * hash + Float.floatToIntBits(x);
        hash += 37 * hash + Float.floatToIntBits(y);
        hash += 37 * hash + Float.floatToIntBits(z);
        hash += 37 * hash + Float.floatToIntBits(w);
        return hash;
    }

    /**
     * <code>toString</code> returns the string representation of this vector.
     * The format is:
     *
     * org.jme.math.Vector3f [X=XX.XXXX, Y=YY.YYYY, Z=ZZ.ZZZZ, W=WW.WWWW]
     *
     * @return the string representation of this vector.
     */
    @Override
    public final String toString() {
        return "(" + x + ", " + y + ", " + z + ", " + w + ")";
    }

    public final float getX() {
        return x;
    }

    public final float getY() {
        return y;
    }

    public final float getZ() {
        return z;
    }

    public final float getW() {
        return w;
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
   public final ResultType mult(float scalar) {
       return build(x * scalar, y * scalar, z * scalar, w * scalar);
   }
    
    /**
     *
     * <code>dot</code> calculates the dot product of this vector with a
     * provided vector. If the provided vector is null, 0 is returned.
     *
     * @param vec
     *            the vector to dot with this vector.
     * @return the resultant dot product of this vector and a given vector.
     */
    @Override
    public final float dot(ResultType vec) {
        return x * vec.x + y * vec.y + z * vec.z + w * vec.w;
    }

    /**
     * <code>lengthSquared</code> calculates the squared value of the
     * magnitude of the vector.
     *
     * @return the magnitude squared of the vector.
     */
    @Override
    public final float lengthSquared() {
        return x * x + y * y + z * z + w * w;
    }

    /**
     * <code>distanceSquared</code> calculates the distance squared between
     * this vector and vector v.
     *
     * @param v the second vector to determine the distance squared.
     * @return the distance squared between the two vectors.
     */
    @Override
    public final float distanceSquared(ResultType v) {
        double dx = x - v.x;
        double dy = y - v.y;
        double dz = z - v.z;
        double dw = w - v.w;
        return (float) (dx * dx + dy * dy + dz * dz + dw * dw);
    }

    /**
     * <code>angleBetween</code> returns (in radians) the angle between two vectors.
     * It is assumed that both this vector and the given vector are unit vectors (iow, normalized).
     *
     * @param otherVector a unit vector to find the angle against
     * @return the angle in radians.
     */
    @Override
    public final float angleBetween(ResultType otherVector) {
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
    public final ResultType interpolate(ResultType finalVec, float changeAmnt) {
      return build(
          (1f-changeAmnt)*this.x + changeAmnt*finalVec.x,
          (1f-changeAmnt)*this.y + changeAmnt*finalVec.y,
          (1f-changeAmnt)*this.z + changeAmnt*finalVec.z,
          (1f-changeAmnt)*this.w + changeAmnt*finalVec.w);
    }

    /**
     * Check a vector... if it is null or its floats are NaN or infinite,
     * return false.  Else return true.
     * @param vector the vector to check
     * @return true or false as stated above.
     */
    @Override
    public final boolean isValid() {
      if (Float.isNaN(x) ||
          Float.isNaN(y) ||
          Float.isNaN(z)||
          Float.isNaN(w)) return false;
      if (Float.isInfinite(x) ||
          Float.isInfinite(y) ||
          Float.isInfinite(z) ||
          Float.isInfinite(w)) return false;
      return true;
    }

    /**
     * Saves this Vector3f into the given float[] object.
     *
     * @param floats
     *            The float[] to take this Vector3f. If null, a new float[3] is
     *            created.
     * @return The array, with X, Y, Z float values in that order
     */
    @Override
    public final float[] toArray() {
      return new float[] { x, y, z, w };
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
    public final boolean equalsEpsilon(ResultType v, float episilon) {
      if (Math.abs(v.x - x) > episilon) return false;
      if (Math.abs(v.y - y) > episilon) return false;
      if (Math.abs(v.z - z) > episilon) return false;
      if (Math.abs(v.w - w) > episilon) return false;
      return true;
    }

    

    /**
     * @param index
     * @return x value if index == 0, y value if index == 1 or z value if index ==
     *         2
     * @throws IllegalArgumentException
     *             if index is not one of 0, 1, 2.
     */
    public final float get(int index) {
        switch (index) {
            case 0:
                return x;
            case 1:
                return y;
            case 2:
                return z;
            case 3:
                return w;
        }
        throw new IllegalArgumentException("index must be either 0, 1, 2 or 3");
    }

    /**
     * <code>maxLocal</code> computes the maximum value for each
     * component in this and <code>other</code> vector. The result is stored
     * in this vector.
     * @param other
     */
    @Override
    public final ResultType max(ResultType other){
      return build(
          Math.max(other.x, x),
          Math.max(other.y, y),
          Math.max(other.z, z),
          Math.max(other.w, w)
        );
    }

    /**
     * <code>minLocal</code> computes the minimum value for each
     * component in this and <code>other</code> vector. The result is stored
     * in this vector.
     * @param other
     */
    @Override
    public final ResultType min(ResultType other){
      return build(
          Math.min(other.x, x),
          Math.min(other.y, y),
          Math.min(other.z, z),
          Math.min(other.w, w)
        );
    }    
}