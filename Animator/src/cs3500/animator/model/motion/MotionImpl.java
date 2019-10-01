package cs3500.animator.model.motion;

import java.awt.Color;

/**
 * Class that represents an implementation of the IMotion interface which represents one motion.
 */
public class MotionImpl implements IMotion {

  private int startTime;
  private double x;
  private double y;
  private double width;
  private double height;
  private Color color;

  /**
   * Constructs a new MotionImpl with start time, x-coordinate, y-coordinate, width, height, and
   * color of the motion.
   *
   * @param startTime the start time of the motion
   * @param x the x-coordinate of the motion
   * @param y the y-coordinate of the motion
   * @param width the motion's width
   * @param height the motion's height
   * @param color the motion's color
   */
  public MotionImpl(int startTime, double x, double y, double width, double height,
      Color color) {
    this.startTime = startTime;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.color = color;
  }

  /**
   * Gets the start time of this motion.
   *
   * @return the start time of this motion
   */
  @Override
  public int getStartTime() {
    return this.startTime;
  }

  /**
   * Gets the x-coordinate of this motion.
   *
   * @return the x-coordinate of this motion.
   */
  @Override
  public double getX() {
    return this.x;
  }

  /**
   * Gets the y-coordinate of this motion.
   *
   * @return the y-coordinate of this motion.
   */
  @Override
  public double getY() {
    return this.y;
  }

  /**
   * Gets the width of this motion.
   *
   * @return this motion's height
   */
  @Override
  public double getWidth() {
    return this.width;
  }

  /**
   * Gets the height of this motion.
   *
   * @return this motion's height
   */
  @Override
  public double getHeight() {
    return this.height;
  }

  /**
   * Gets the color of this motion.
   *
   * @return this motion's color
   */
  @Override
  public Color getColor() {
    return this.color;
  }

  /**
   * Modifies this motion to have the fields in the given motion.
   * @param motionToAdd the new motion
   */
  @Override
  public void changeEverything(IMotion motionToAdd) {
    int red = motionToAdd.getColor().getRed();
    int green = motionToAdd.getColor().getGreen();
    int blue = motionToAdd.getColor().getBlue();

    this.x = motionToAdd.getX();
    this.y = motionToAdd.getY();
    this.width = motionToAdd.getWidth();
    this.height = motionToAdd.getHeight();
    this.color = new Color(red, green, blue);
  }
}
