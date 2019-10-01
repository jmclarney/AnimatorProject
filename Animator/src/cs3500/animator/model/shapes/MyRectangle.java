package cs3500.animator.model.shapes;

import cs3500.animator.model.motion.IMotion;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Represents a Rectangle shape which is an implementation of the IShape interface. It is a simple
 * 2D rectangle shape to be used in an animation.
 */
public class MyRectangle extends AShape {

  /**
   * Constructs a Rectangle with the given name.
   *
   * @param name the unique name of this rectangle
   */
  public MyRectangle(String name) {
    super(name);
  }

  /**
   * Constucts a Rectangle with the given name and moves.
   *
   * @param name the unique name of this rectangle
   * @param moves this rectangle's list of IMotions
   */
  private MyRectangle(String name, ArrayList<IMotion> moves) {
    super(name, moves);
  }

  /**
   * Returns the type of this shape which is a Rectangle.
   *
   * @return the String "rectangle"
   */
  @Override
  public String getType() {
    return "rect";
  }

  /**
   * Makes a copy of this rectangle.
   *
   * @return a copy of this rectangle
   */
  @Override
  public IShape copy() {
    return new MyRectangle(this.getName(), this.copyMoves());
  }

  /**
   * Draws this shape to the screen using the current graphics and the shape.
   *
   * @param g the graphics used to draw the shape
   * @param shapeAtTick the shape at the desired time in the animation
   */
  @Override
  public void drawShape(Graphics g, IMotion shapeAtTick) {
    g.fillRect((int) shapeAtTick.getX(), (int) shapeAtTick.getY(),
        (int) shapeAtTick.getWidth(), (int) shapeAtTick.getHeight());
  }

  /**
   * Delegates to each shape to get its width text, "width" for a rectangle but "rx" for an
   * ellipse. Since this is in rectangle it returns "width".
   *
   * @return the text describing this shape's width
   */
  @Override
  public String getWidthText() {
    return "width";
  }

  /**
   * Delegates to each shape to get its height text, "height" for a rectangle but "ry" for an
   * ellipse. Since this is in rectangle it returns "height".
   *
   * @return a String describing this shape's height
   */
  @Override
  public String getHeightText() {
    return "height";
  }

  /**
   * Delegates to each shape to get its x type, just "x" for a rectangle and "cx" for an ellipse.
   *
   * @return a String describing this shape's x value
   */
  @Override
  public String getXType() {
    return "x";
  }

  /**
   * Delegates to each shape to get its y type, just "y" for a rectangle and "cy" for an ellipse.
   *
   * @return a String describing this shape's y value
   */
  @Override
  public String getYType() {
    return "y";
  }
}
