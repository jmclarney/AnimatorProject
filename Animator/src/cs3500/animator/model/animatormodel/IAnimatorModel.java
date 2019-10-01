package cs3500.animator.model.animatormodel;

import cs3500.animator.model.motion.IMotion;
import java.awt.geom.Point2D;
import java.util.List;

import cs3500.animator.model.shapes.IShape;

/**
 * This interface represents the operations offered by the Animator model. One object of the model
 * represents an animator. This animator creates simple 2D animations based on a given text file.
 */
public interface IAnimatorModel {

  /**
   * Gets the description of all of the shapes in this animator and all of their motions.
   *
   * @return a String describing every shape and their motions
   */
  String getDescription();

  /**
   * Adds a motion to the given shape's list of motions.
   *
   * @param motion the IMotion to be added
   * @param shapeID the shapeID the IMotion is to be added to
   * @throws IllegalArgumentException if there is already a motion in this shape at the same time or
   *                                  if the shape is not already in the model's list of shapes as
   *                                  the shape needs to be added before the motion is added to it.
   */
  void addMotion(IMotion motion, String shapeID) throws IllegalArgumentException;

  /**
   * Removes a motion from the given shape's list of motions.
   *
   * @param motionTime the time of the motion to be removed
   * @param shapeID the ID of the shape the motion should be removed from
   * @throws IllegalArgumentException if the shape doesn't exist in the list of shapes or if the
   *                                  motion isn't in the shape's list of motions
   */
  void removeMotion(int motionTime, String shapeID) throws IllegalArgumentException;

  /**
   * Adds a shape to this model's list of shapes.
   *
   * @param shapeID the ID of the shape to be added
   * @param shapeType the type of the shape to be added
   * @throws IllegalArgumentException if the given shape already exists in this model's list of
   *                                  shapes
   */
  void addShape(String shapeID, String shapeType) throws IllegalArgumentException;

  /**
   * Removes the given shape from this model's list of shapes.
   *
   * @param shapeID the ID of the shape to be removed
   * @throws IllegalArgumentException if the model's list of shapes does not contain this shape
   */
  void removeShape(String shapeID) throws IllegalArgumentException;

  /**
   * Returns a list of all of the shapes in this model.
   *
   * @return all of the shapes in this model's list of shapes.
   */
  List<IShape> getAllShapes();

  /**
   * Gets this model's top left point.
   *
   * @return the top left point of this model
   */
  Point2D getPoint();

  /**
   * Gets this model's width.
   *
   * @return this model's width
   */
  double getWidth();

  /**
   * Gets this model's height.
   *
   * @return this model's height
   */
  double getHeight();

  /**
   * Sets this model's top left point.
   *
   * @param point the point to be set
   */
  void setPoint(Point2D point);

  /**
   * Sets this model's width to the given width.
   *
   * @param w the width to set this model's width to
   */
  void setWidth(double w);

  /**
   * Sets this model's height to the given height.
   *
   * @param h the height to set this model's height to
   */
  void setHeight(double h);

  /**
   * Edits the motion with the same shapeID and start time with the following positional size, and
   * color specifications, or else throws and exception.
   *
   * @param motionToAdd the new motion for the shape
   * @param shapeID the ID of the shape
   */
  void modifyMotion(IMotion motionToAdd, String shapeID) throws IllegalArgumentException;

  /**
   * Returns the amount of motions for the shape with the given unique shapeID.
   *
   * @param shapeID the shapeID of the shape
   * @return the number of motions the shape has
   */
  int getNumberOfMotions(String shapeID);

  /**
   * Removes everything in this animation
   */
  void removeEverything();
}
