package org.saintandreas.math;

import static org.junit.Assert.*;

import org.junit.Test;

public class Matrix4fTest {

  public static void assertMatrixOperation(Matrix4f m, Vector4f original, Vector4f expected) {
    Vector4f result = m.mult(original);
    assertTrue(result.equalsEpsilon(expected));
  }

  @Test
  public void testRotation() {
    Matrix4f m = new Matrix4f().rotate((float)Math.PI, Vector3f.UNIT_Y);
    assertMatrixOperation(m, new Vector4f(0, 0, -1, 1), new Vector4f(0, 0, 1, 1));
    assertMatrixOperation(m, new Vector4f(-1, 0, 0, 1), new Vector4f(1, 0, 0, 1));
    assertMatrixOperation(m, new Vector4f(-1, 0, -1, 1), new Vector4f(1, 0, 1, 1));
    assertMatrixOperation(m, new Vector4f(-1, 1, -1, 1), new Vector4f(1, 1, 1, 1));
    assertMatrixOperation(m, new Vector4f(0, 1, 0, 1), new Vector4f(0, 1, 0, 1));

    m = new Matrix4f().rotate((float)Math.PI / 2f, Vector3f.UNIT_Y);
    assertMatrixOperation(m, new Vector4f(0, 0, 1, 1), new Vector4f(1, 0, 0, 1));
    assertMatrixOperation(m, new Vector4f(0, 0, -1, 1), new Vector4f(-1, 0, 0, 1));

    m = new Matrix4f().rotate((float)Math.PI, Vector3f.UNIT_Z);
    assertMatrixOperation(m, new Vector4f(0, 0, -1, 1), new Vector4f(0, 0, -1, 1)); 
  }

  @Test
  public void testTranslation() {
    Matrix4f m = new Matrix4f().translate(Vector3f.UNIT_X);
    assertMatrixOperation(m, new Vector4f(0, 0, -1, 1), new Vector4f(1, 0, -1, 1));
  }

  @Test
  public void testScale() {
    Matrix4f m = new Matrix4f().scale(2);
    assertMatrixOperation(m, new Vector4f(1, 1, 1, 1), new Vector4f(2, 2, 2, 1));
  }
}
