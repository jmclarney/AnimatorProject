
import static org.junit.Assert.assertEquals;

import cs3500.animator.model.shapes.MyEllipse;
import org.junit.Test;

import java.awt.Color;

import cs3500.animator.model.animatormodel.IAnimatorModel;
import cs3500.animator.model.shapes.IShape;
import cs3500.animator.model.motion.MotionImpl;
import cs3500.animator.model.shapes.MyRectangle;
import cs3500.animator.model.animatormodel.AnimatorModelImpl;

/**
 * Tests for MotionImpl and its associated methods.
 */
public class MotionImplTest {

  IShape rect1;
  IShape rect2;
  IShape rect3;

  IShape ellipse1;
  IShape ellipse2;
  IShape ellipse3;
  IShape ellipse4;

  IAnimatorModel emptyModel;
  IAnimatorModel model1;
  IAnimatorModel model2;
  IAnimatorModel model3;
  IAnimatorModel model4;
  IAnimatorModel model5;
  IAnimatorModel model6;
  IAnimatorModel model7;

  /**
   * Initializes the data.
   */
  public void initData() {
    // Initializing empty model
    emptyModel = new AnimatorModelImpl();
    // Initializing the model and adding shapes
    model1 = new AnimatorModelImpl();
    model1.addShape("R3", "rectangle");
    model1.addShape("O1", "ellipse");

    // Initializing a second model
    model2 = new AnimatorModelImpl();
    model2.addShape("R3", "rectangle");

    model3 = new AnimatorModelImpl();
    model3.addShape("R1", "rectangle");
    model3.addShape("O1", "ellipse");
    model3.addShape("O2", "ellipse");

    model4 = new AnimatorModelImpl();
    model5 = new AnimatorModelImpl();
    model6 = new AnimatorModelImpl();
    model7 = new AnimatorModelImpl();

    ellipse1 = new MyEllipse("O1");
    ellipse2 = new MyEllipse("O2");
    ellipse3 = new MyEllipse("O3");
    ellipse4 = new MyEllipse("O4");
    rect1 = new MyRectangle("R1");
    rect2 = new MyRectangle("R2");
    rect3 = new MyRectangle("R3");
    rect2.addMove(new MotionImpl(4, 50, 50, 40, 40, Color.GRAY));
    rect3.addMove(new MotionImpl(1, 200, 200, 50, 100,
        new Color(255, 0, 0)));
    rect3.addMove(new MotionImpl(10, 200, 200, 50, 100,
        new Color(255, 0, 0)));
    rect3.addMove(new MotionImpl(50, 300, 300, 50, 100,
        new Color(255, 0, 0)));
    rect3.addMove(new MotionImpl(51, 300, 300, 50, 100,
        new Color(255, 0, 0)));
    rect3.addMove(new MotionImpl(70, 300, 300, 25, 100,
        new Color(255, 0, 0)));
    ellipse1.addMove(new MotionImpl(0, 50, 50, 20, 20, Color.GRAY));
    ellipse1.addMove(new MotionImpl(10, 0, 100, 20, 20, Color.GRAY));
    ellipse1.addMove(new MotionImpl(20, 0, 100, 50, 40, Color.GRAY));
    ellipse1.addMove(new MotionImpl(30, 0, 100, 50, 40, Color.BLACK));
    ellipse1.addMove(new MotionImpl(40, 75, 25, 50, 40, Color.BLACK));
    ellipse3 = new MyEllipse("E3");
    ellipse3.addMove(new MotionImpl(0, 75, 25, 50, 40, Color.GREEN));
    ellipse3.addMove(new MotionImpl(10, 75, 25, 50, 40, Color.BLUE));
    ellipse3.addMove(new MotionImpl(12, 75, 25, 50, 40, Color.BLUE));
    ellipse3.addMove(new MotionImpl(15, 50, 25, 50, 40, Color.BLUE));
    ellipse3.addMove(new MotionImpl(16, 50, 30, 50, 40, Color.BLUE));
    ellipse3.addMove(new MotionImpl(19, 20, 30, 50, 40, Color.BLUE));
    ellipse3.addMove(new MotionImpl(22, 20, 40, 50, 40, Color.BLUE));
    ellipse3.addMove(new MotionImpl(24, 30, 30, 50, 40, Color.BLUE));
    ellipse3.addMove(new MotionImpl(27, 40, 40, 50, 40, Color.BLUE));
    ellipse3.addMove(new MotionImpl(28, 30, 30, 50, 40, Color.BLUE));
    ellipse3.addMove(new MotionImpl(29, 20, 40, 50, 40, Color.BLUE));
    ellipse3.addMove(new MotionImpl(30, 20, 40, 50, 40, Color.BLUE));
    ellipse3.addMove(new MotionImpl(31, 20, 40, 40, 40, Color.BLUE));
    ellipse3.addMove(new MotionImpl(32, 20, 40, 40, 50, Color.BLUE));
    ellipse3.addMove(new MotionImpl(33, 20, 40, 60, 60, Color.BLUE));
    ellipse3.addMove(new MotionImpl(34, 20, 40, 50, 50, Color.BLUE));
    ellipse3.addMove(new MotionImpl(35, 20, 40, 60, 40, Color.BLUE));
    ellipse3.addMove(new MotionImpl(36, 20, 40, 50, 50, Color.BLUE));
    ellipse3.addMove(new MotionImpl(37, 20, 40, 50, 50, Color.RED));

    // OLD WAY ^^
    // NEW WAY

    model4.addShape("R1", "rectangle");
    model4.addMotion(new MotionImpl(1, 200, 200, 50, 100,
        new Color(255, 0, 0)), "R3");
    model4.addMotion(new MotionImpl(10, 200, 200, 50, 100,
        new Color(255, 0, 0)), "R3");
    model4.addMotion(new MotionImpl(50, 300, 300, 50, 100,
        new Color(255, 0, 0)), "R3");
    model4.addMotion(new MotionImpl(51, 300, 300, 50, 100,
        new Color(255, 0, 0)), "R3");
    model4.addMotion(new MotionImpl(70, 300, 300, 25, 100,
        new Color(255, 0, 0)), "R3");
    model4.addShape("O1", "ellipse");
    model4.addMotion(new MotionImpl(0, 50, 50, 20, 20, Color.GRAY), "O1");
    model4.addMotion(new MotionImpl(10, 0, 100, 20, 20, Color.GRAY), "O1");
    model4.addMotion(new MotionImpl(20, 0, 100, 50, 40, Color.GRAY), "O1");
    model4.addMotion(new MotionImpl(30, 0, 100, 50, 40, Color.BLACK), "O1");
    model4.addMotion(new MotionImpl(40, 75, 25, 50, 40, Color.BLACK), "O1");

    model5.addShape("R3", "rectangle");
    model5.addMotion(new MotionImpl(1, 200, 200, 50, 100,
        new Color(255, 0, 0)), "R3");
    model5.addMotion(new MotionImpl(10, 200, 200, 50, 100,
        new Color(255, 0, 0)), "R3");
    model5.addMotion(new MotionImpl(50, 300, 300, 50, 100,
        new Color(255, 0, 0)), "R3");
    model5.addMotion(new MotionImpl(51, 300, 300, 50, 100,
        new Color(255, 0, 0)), "R3");
    model5.addMotion(new MotionImpl(70, 300, 300, 25, 100,
        new Color(255, 0, 0)), "R3");

    model6.addShape("O1", "ellipse");
    model6.addMotion(new MotionImpl(0, 50, 50, 20, 20, Color.GRAY), "O1");
    model6.addMotion(new MotionImpl(10, 0, 100, 20, 20, Color.GRAY), "O1");
    model6.addMotion(new MotionImpl(20, 0, 100, 50, 40, Color.GRAY), "O1");
    model6.addMotion(new MotionImpl(30, 0, 100, 50, 40, Color.BLACK), "O1");
    model6.addMotion(new MotionImpl(40, 75, 25, 50, 40, Color.BLACK), "O1");
    model6.addShape("O2", "ellipse");

    model7.addShape("R3", "rectangle");
    model7.addMotion(new MotionImpl(1, 200, 200, 50, 100,
        new Color(255, 0, 0)), "R3");
    model7.addMotion(new MotionImpl(10, 200, 200, 50, 100,
        new Color(255, 0, 0)), "R3");
    model7.addMotion(new MotionImpl(50, 300, 300, 50, 100,
        new Color(255, 0, 0)), "R3");
    model7.addMotion(new MotionImpl(51, 300, 300, 50, 100,
        new Color(255, 0, 0)), "R3");
    model7.addMotion(new MotionImpl(70, 300, 300, 25, 100,
        new Color(255, 0, 0)), "R3");
    model7.addShape("O1", "ellipse");
    model7.addMotion(new MotionImpl(0, 50, 50, 20, 20, Color.GRAY), "O1");
    model7.addMotion(new MotionImpl(10, 0, 100, 20, 20, Color.GRAY), "O1");
    model7.addMotion(new MotionImpl(20, 0, 100, 50, 40, Color.GRAY), "O1");
    model7.addMotion(new MotionImpl(30, 0, 100, 50, 40, Color.BLACK), "O1");
    model7.addMotion(new MotionImpl(40, 75, 25, 50, 40, Color.BLACK), "O1");


  }

  @Test
  public void testGettersInIMotion() {
    initData();
    assertEquals(40, ellipse1.getMoves().get(4).getStartTime());
    assertEquals(75, ellipse1.getMoves().get(4).getX(), .01);
    assertEquals(25, ellipse1.getMoves().get(4).getY(), .01);
    assertEquals(50, ellipse1.getMoves().get(4).getWidth(), .01);
    assertEquals(40, ellipse1.getMoves().get(4).getHeight(), .01);
    assertEquals(Color.BLACK, ellipse1.getMoves().get(4).getColor());
  }

  @Test
  public void testShapeGetters() {
    initData();
    assertEquals(2, model1.getAllShapes().size());
  }

  @Test
  public void testellipseGetters() {
    initData();
    assertEquals("O2", ellipse2.getName());
  }

  @Test
  public void testRectGetters() {
    initData();
    assertEquals("R3", rect3.getName());
  }

  // tests catching an exception when trying to add a new Rectangle to the model with the same
  // ID as an existing shape
  @Test(expected = IllegalArgumentException.class)
  public void testBadAddShape1() {
    initData();
    model3.addShape("R1", "rectangle");
  }

  // tests catching an exception when trying to add a new MyEllipse to the model with the same ID
  // as an existing shape
  @Test(expected = IllegalArgumentException.class)
  public void testBadAddShape2() {
    initData();
    model1.addShape("O1", "ellipse");
  }

  @Test
  public void testMotions1() {
    this.initData();
    // at t = 0
    assertEquals(50, ellipse1.calcShapeAt(0).getX(), .01);
    assertEquals(50, ellipse1.calcShapeAt(0).getY(), .01);
    assertEquals(20, ellipse1.calcShapeAt(0).getWidth(), .01);
    assertEquals(20, ellipse1.calcShapeAt(0).getHeight(), .01);
    assertEquals(Color.GRAY, ellipse1.calcShapeAt(0).getColor());

    // at t = 2
    assertEquals(40, ellipse1.calcShapeAt(2).getX(), .01);
    assertEquals(60, ellipse1.calcShapeAt(2).getY(), .01);
    assertEquals(20, ellipse1.calcShapeAt(2).getWidth(), .01);
    assertEquals(20, ellipse1.calcShapeAt(2).getHeight(), .01);
    assertEquals(Color.GRAY, ellipse1.calcShapeAt(2).getColor());

    // at t = 12
    assertEquals(0, ellipse1.calcShapeAt(12).getX(), .01);
    assertEquals(100, ellipse1.calcShapeAt(12).getY(), .01);
    assertEquals(26, ellipse1.calcShapeAt(12).getWidth(), .01);
    assertEquals(24, ellipse1.calcShapeAt(12).getHeight(), .01);
    assertEquals(Color.GRAY, ellipse1.calcShapeAt(12).getColor());

    // at t = 20
    assertEquals(0, ellipse1.calcShapeAt(20).getX(), .01);
    assertEquals(100, ellipse1.calcShapeAt(20).getY(), .01);
    assertEquals(50, ellipse1.calcShapeAt(20).getWidth(), .01);
    assertEquals(40, ellipse1.calcShapeAt(20).getHeight(), .01);
    assertEquals(Color.GRAY, ellipse1.calcShapeAt(20).getColor());
  }

  // tests catching an exception when calculating a shape at a time it has not appeared yet
  @Test(expected = IllegalArgumentException.class)
  public void testMotions2() {
    initData();
    ellipse1.calcShapeAt(-5);
  }

  // tests catching an exception when calculating a shape at a time it has not appeared yet
  @Test(expected = IllegalArgumentException.class)
  public void testMotions3() {
    initData();
    rect3.calcShapeAt(0);
  }

  // tests catching an exception when calculating a shape at a time it has not appeared yet
  @Test(expected = IllegalArgumentException.class)
  public void testMotions5() {
    initData();
    rect2.calcShapeAt(3);
  }

  // tests catching an exception when calculating a shape at a time but the shape has not
  // appeared yet
  @Test(expected = IllegalArgumentException.class)
  public void testMotions6() {
    initData();
    ellipse2.calcShapeAt(4);
  }


  // tests printing a shape of a model and its motions
  @Test
  public void testGetDescription1() {
    initData();
    assertEquals(""
            + "shape R3 rect\n"
            + "motion R3 1 200.0 200.0 50.0 100.0 255 0 0   10 200.0 200.0 50.0 100.0 255 0 0\n"
            + "motion R3 10 200.0 200.0 50.0 100.0 255 0 0   50 300.0 300.0 50.0 100.0 255 0 0\n"
            + "motion R3 50 300.0 300.0 50.0 100.0 255 0 0   51 300.0 300.0 50.0 100.0 255 0 0\n"
            + "motion R3 51 300.0 300.0 50.0 100.0 255 0 0   70 300.0 300.0 25.0 100.0 255 0 0\n",
        model5.getDescription());
  }

  // tests printing multiple shapes of a model and their motions
  @Test
  public void testGetDescription2() {
    initData();
    assertEquals(""
            + "shape R3 rect\n"
            + "motion R3 1 200.0 200.0 50.0 100.0 255 0 0   10 200.0 200.0 50.0 100.0 255 0 0\n"
            + "motion R3 10 200.0 200.0 50.0 100.0 255 0 0   50 300.0 300.0 50.0 100.0 255 0 0\n"
            + "motion R3 50 300.0 300.0 50.0 100.0 255 0 0   51 300.0 300.0 50.0 100.0 255 0 0\n"
            + "motion R3 51 300.0 300.0 50.0 100.0 255 0 0   70 300.0 300.0 25.0 100.0 255 0 0\n"
            + "shape O1 ellipse\n"
            + "motion O1 0 50.0 50.0 20.0 20.0 128 128 128   10 0.0 100.0 20.0 20.0 128 128 128\n"
            + "motion O1 10 0.0 100.0 20.0 20.0 128 128 128   20 0.0 100.0 50.0 40.0 128 128 128\n"
            + "motion O1 20 0.0 100.0 50.0 40.0 128 128 128   30 0.0 100.0 50.0 40.0 0 0 0\n"
            + "motion O1 30 0.0 100.0 50.0 40.0 0 0 0   40 75.0 25.0 50.0 40.0 0 0 0\n",
        model7.getDescription());
  }

  // testing a model with no shapes added yet
  @Test
  public void testGetDescription3() {
    initData();
    assertEquals("No description available. No shapes in this model",
        emptyModel.getDescription());
  }

  // testing printing description with shapes that have no motions
  @Test
  public void testGetDescription4() {
    initData();
    assertEquals("shape O1 ellipse\n"
            + "motion O1 0 50.0 50.0 20.0 20.0 128 128 128   10 0.0 100.0 20.0 20.0 128 128 128\n"
            + "motion O1 10 0.0 100.0 20.0 20.0 128 128 128   20 0.0 100.0 50.0 40.0 128 128 128\n"
            + "motion O1 20 0.0 100.0 50.0 40.0 128 128 128   30 0.0 100.0 50.0 40.0 0 0 0\n"
            + "motion O1 30 0.0 100.0 50.0 40.0 0 0 0   40 75.0 25.0 50.0 40.0 0 0 0\n"
            +  "shape O2 ellipse\n",
        model6.getDescription());
  }

  //  // tests removing a shape from a model
  @Test
  public void testRemoveShapes1() {
    initData();
    assertEquals(3, model3.getAllShapes().size());
    model3.removeShape("O1");
    assertEquals(2, model3.getAllShapes().size());
  }

  // tests throwing exception when trying to remove a shape from a model that doesn't
  // exist in that model
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveShapes2() {
    initData();
    assertEquals(3, model3.getAllShapes().size());
    //model3.removeShape(new MyRectangle("dummy"));
    model3.removeShape("dummy");
    assertEquals(2, model3.getAllShapes().size());
  }

  // tests adding a shape to a model
  @Test
  public void testAddShapes1() {
    initData();
    assertEquals(3, model3.getAllShapes().size());
    model3.addShape("Add Rectangle", "rectangle");
    assertEquals(4, model3.getAllShapes().size());
  }

  // tests throwing exception when trying to add shape that already exists
  @Test(expected = IllegalArgumentException.class)
  public void testAddShapes2() {
    initData();
    assertEquals(3, model3.getAllShapes().size());
    model3.addShape("R1", "rectangle"); // already exists
  }


  // tests handling a= exception when trying to remove a motion that doesn't exist
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveMotion() {
    initData();
    assertEquals(5, ellipse1.getMoves().size());
    model3.removeMotion(2, "O1");
  }

  // tests handling an exception when trying to add a motion that doesn't exist
  @Test(expected = IllegalArgumentException.class)
  public void testAddMotion() {
    initData();
    model4.addMotion(new MotionImpl(0, 3, 5, 4, 4, Color.GRAY),
        "O1"); // motion at this time already exists
  }

  // tests the copy method, which returns a copy of the shape
  @Test
  public void testCopy() {
    initData();
    IShape copyOfellipse = ellipse1.copy();

    assertEquals(copyOfellipse.getName(), ellipse1.getName());
    assertEquals(copyOfellipse.getMoves(), ellipse1.getMoves());

    IShape copyOfRectangle = rect1.copy();

    assertEquals(copyOfRectangle.getName(), rect1.getName());
    assertEquals(copyOfRectangle.getMoves(), rect1.getMoves());
  }

  // tests adding a move to a shape when the start time is negative
  @Test(expected = IllegalArgumentException.class)
  public void testAddingAMoveStartTimeNegative() {
    initData();
    ellipse4.addMove(new MotionImpl(-1, 75, 25, 50, 40, Color.GREEN));
  }

  // tests adding a move to a shape when the x coordinate is negative
  @Test(expected = IllegalArgumentException.class)
  public void testAddingAMoveXNegative() {
    initData();
    ellipse4.addMove(new MotionImpl(1, -5, 25, 50, 40, Color.GREEN));
  }

  // tests adding a move to a shape when the y coordinate is negative
  @Test(expected = IllegalArgumentException.class)
  public void testAddingAMoveYNegative() {
    initData();
    ellipse4.addMove(new MotionImpl(1, 25, -2, 50, 40, Color.GREEN));
  }

  // tests adding a move to a shape when the width is negative
  @Test(expected = IllegalArgumentException.class)
  public void testAddingAMoveWidthNegative() {
    initData();
    ellipse4.addMove(new MotionImpl(1, 25, 0, -10, 40, Color.GREEN));
  }

  // tests adding a move to a shape when the height is negative
  @Test(expected = IllegalArgumentException.class)
  public void testAddingAMoveHeightNegative() {
    initData();
    ellipse4.addMove(new MotionImpl(1, 25, 0, 20, -10, Color.GREEN));
  }
}