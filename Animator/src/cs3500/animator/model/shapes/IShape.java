package cs3500.animator.model.shapes;

import cs3500.animator.model.motion.IMotion;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 * This interface represents a shape and all of the operations offered by it. A shape is a 2D shape
 * that can be used in an animation.
 */
public interface IShape {

  /**
   * Returns the name of this IShape.
   *
   * @return the unique name of this shape
   */
  String getName();

  /**
   * Adds a move to this shape's list of moves.
   *
   * @param motion the move to be added
   */
  void addMove(IMotion motion) throws IllegalArgumentException;

  /**
   * Returns the shape at the given tick.
   *
   * @param time the time we want to get the shape ata
   * @return the IShape at the given time (or tick)
   */
  IMotion calcShapeAt(int time) throws IllegalArgumentException;

  /**
   * Returns the type of this shape, oval or rectangle for example.
   *
   * @return a String describing what type of shape this is
   */
  String getType();

  /**
   * Gets the moves of this Shape.
   *
   * @return an ArrayList of IMotion
   */
  ArrayList<IMotion> getMoves();

  /**
   * Makes a copy of this IShape.
   *
   * @return a copy of this IShape
   */
  IShape copy();

  /**
   * Makes a copy of this IShape's moves.
   *
   * @return a copy of this IShape moves
   */
  ArrayList<IMotion> copyMoves();

  /**
   * Determines the proper output in SVG format specific to motions for the move at the given motion
   * index of the shape.
   *
   * @param motionIndex the index of the desired motion in this shape's list of motions
   * @return a string describing the motion
   */
  String determineMove(int motionIndex, int speed);

  /**
   * Calculates the milliseconds equivalent of the given tick amount.
   *
   * @param ticks the ticks to be converted
   * @return the millisecond equivalent of the given ticks
   */
  double calcMilliSeconds(int ticks, int speed);

  /**
   * Draws this shape at the current time.
   *
   * @param g the graphics to be used to draw the shape
   * @param tick the current time
   */
  void draw(Graphics g, int tick);

  /**
   * Tweens (calculates where the shape is at based on the given time and the two motions before and
   * after it).
   *
   * @param startPoint the starting point of the motion before the current tick
   * @param endPoint the end point of the motion after the current tick
   * @param startTime the time of the motion before the current time
   * @param endTime the time of the motion after the current time
   * @param time the current time
   * @return the tweened mid-value between the given start and end values
   */
  double tween(double startPoint, double endPoint, int startTime, int endTime, int time);

  /**
   * Draws this shape to the screen using the current graphics and the shape.
   *
   * @param g the graphics used to draw the shape
   * @param shapeAtTick the shape at the desired time in the animation
   */
  void drawShape(Graphics g, IMotion shapeAtTick);

  /**
   * Delegates to each shape to get its width text, "width" for a rectangle but "rx" for an
   * ellipse.
   *
   * @return the text describing this shape's width
   */
  String getWidthText();

  /**
   * Delegates to each shape to get its height text, "height" for a rectangle but "ry" for an
   * ellipse.
   *
   * @return a String describing this shape's height
   */
  String getHeightText();

  /**
   * Delegates to each shape to get its x type, just "x" for a rectangle and "cx" for an ellipse.
   *
   * @return a String describing this shape's x value
   */
  String getXType();

  /**
   * Delegates to each shape to get its y type, just "y" for a rectangle and "cy" for an ellipse.
   *
   * @return a String describing this shape's y value
   */
  String getYType();

  /**
   * Edits this shape's motion with the same start time with the following positional size, and
   * color specifications of the given motionToAdd, or else throws and exception.
   *
   * @param motionToAdd the new motion for the shape
   */
  void modifyMotion(IMotion motionToAdd);
}
