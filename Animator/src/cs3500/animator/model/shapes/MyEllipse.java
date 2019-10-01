package cs3500.animator.model.shapes;

import cs3500.animator.model.motion.IMotion;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Represents a MyEllipse shape which is an implementation of the IShape interface. It is a simple
 * 2D shape of an ellipse.
 */
public class MyEllipse extends AShape {

  /**
   * Constructs a MyEllipse with the given name.
   *
   * @param name the unique name of this oval
   */
  public MyEllipse(String name) {
    super(name);
  }

  /**
   * Constructs an ellipse with the given name and moves.
   *
   * @param name the unique name of this oval
   * @param moves this oval's list of IMotions
   */
  private MyEllipse(String name, ArrayList<IMotion> moves) {
    super(name, moves);
  }

  /**
   * Returns the type of this shape which is an ellipse.
   *
   * @return the String "oval"
   */
  @Override
  public String getType() {
    return "ellipse";
  }

  /**
   * Makes a copy of this MyEllipse.
   *
   * @return a copy of this MyEllipse
   */
  @Override
  public IShape copy() {
    return new MyEllipse(this.getName(), this.copyMoves());
  }

  /**
   * Draws this ellipse to the screen using the current graphics and the shape.
   *
   * @param g the graphics used to draw the shape
   * @param shapeAtTick the shape at the desired time in the animation
   */
  @Override
  public void drawShape(Graphics g, IMotion shapeAtTick) {
    g.fillOval((int) shapeAtTick.getX(), (int) shapeAtTick.getY(),
        (int) shapeAtTick.getWidth(), (int) shapeAtTick.getHeight());

  }

  /**
   * Delegates to each shape to get its width text, "width" for a rectangle but "rx" for an
   * ellipse. Since this is in ellipse it returns "rx".
   *
   * @return the text describing this shape's width
   */
  @Override
  public String getWidthText() {
    return "rx";
  }

  /**
   * Delegates to each shape to get its height text, "height" for a rectangle but "ry" for an
   * ellipse. Since this is in ellipse it returns "ry".
   *
   * @return a String describing this shape's height
   */
  @Override
  public String getHeightText() {
    return "ry";
  }

  /**
   * Delegates to each shape to get its x type, just "x" for a rectangle and "cx" for an ellipse.
   *
   * @return a String describing this shape's x value
   */
  @Override
  public String getXType() {
    return "cx";
  }

  /**
   * Delegates to each shape to get its y type, just "y" for a rectangle and "cy" for an ellipse.
   *
   * @return a String describing this shape's y value
   */
  @Override
  public String getYType() {
    return "cy";
  }
}
