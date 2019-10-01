package cs3500.animator.model.motion;

import java.awt.Color;

/**
 * This interface represents a motion and all of the operations offered by it. A motion is what
 * allows a shape to either move, resize, or change color and includes vital information for the
 * animation such as the time of the motion, the x and y coordinates of the shape, width, height,
 * and color.
 */
public interface IMotion {

  /**
   * Gets the start time of this motion.
   *
   * @return the start time of this motion
   */
  int getStartTime();

  /**
   * Gets the x-coordinate of this motion.
   *
   * @return the x-coordinate of this motion.
   */
  double getX();

  /**
   * Gets the y-coordinate of this motion.
   *
   * @return the y-coordinate of this motion.
   */
  double getY();

  /**
   * Gets the width of this motion.
   *
   * @return this motion's height
   */
  double getWidth();

  /**
   * Gets the height of this motion.
   *
   * @return this motion's height
   */
  double getHeight();

  /**
   * Gets the color of this motion.
   *
   * @return this motion's color
   */
  Color getColor();

  /**
   * Modifies this motion to have the fields in the given motion.
   * @param motionToAdd the new motion
   */
  void changeEverything(IMotion motionToAdd);
}
