package cs3500.animator.model.animatormodel;

import cs3500.animator.model.motion.IMotion;
import cs3500.animator.model.shapes.IShape;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a view-only Animator Model to separate the capabilities of the View and Model of the
 * program. Throws an exception if the user tries to mutate the model.
 */
public class ViewModel implements IAnimatorModel {

  private IAnimatorModel model;

  /**
   * Constructs a view-only model using the given Model.
   *
   * @param model the model to be made view-only
   */
  public ViewModel(IAnimatorModel model) {
    this.model = model;
  }

  /**
   * Gets the description of all of the shapes in this animator and all of their motions.
   *
   * @return a String describing every shape and their motions
   */
  @Override
  public String getDescription() {
    return this.model.getDescription();
  }

  /**
   * Adds a motion to the given shape's list of motions.
   *
   * @param motion the IMotion to be added
   * @param shapeID the ID of the shape the IMotion is to be added to
   * @throws IllegalArgumentException if there is already a motion in this shape at the same time or
   *                                  if the shape is not already in the model's list of shapes as
   *                                  the shape needs to be added before the motion is added to it.
   */
  @Override
  public void addMotion(IMotion motion, String shapeID) throws IllegalArgumentException {
    throw new UnsupportedOperationException("Cannot add motion in a read only model");
  }

  /**
   * Removes a motion from the given shape's list of motions.
   *
   * @param motionTime the time of the motion to be removed
   * @param shapeID the ID of the shape the motion should be removed from
   * @throws IllegalArgumentException if the shape doesn't exist in the list of shapes or if the
   *                                  motion isn't in the shape's list of motions
   */
  @Override
  public void removeMotion(int motionTime, String shapeID) throws IllegalArgumentException {
    throw new UnsupportedOperationException("Cannot remove motion in a read only model");

  }

  /**
   * Adds a shape to this model's list of shapes.
   *
   * @param shapeID the ID of the shape to be added
   * @param shapeType the type of the shape to be added
   * @throws IllegalArgumentException if the given shape already exists in this model's list of
   *                                  shapes
   */
  @Override
  public void addShape(String shapeID, String shapeType) throws IllegalArgumentException {
    throw new UnsupportedOperationException("Cannot add shape in a read only model");

  }

  /**
   * Removes the given shape from this model's list of shapes.
   *
   * @param shapeID the ID of the shape to be removed
   * @throws IllegalArgumentException if the model's list of shapes does not contain this shape
   */
  @Override
  public void removeShape(String shapeID) throws IllegalArgumentException {
    throw new UnsupportedOperationException("Cannot remove shape in a read only model");

  }

  /**
   * Returns a list of all of the shapes in this model.
   *
   * @return all of the shapes in this model's list of shapes.
   */
  @Override
  public List<IShape> getAllShapes() {
    ArrayList<IShape> copyOfShapes = new ArrayList<>();

    for (IShape shape : model.getAllShapes()) {
      copyOfShapes.add(shape.copy());
    }
    return copyOfShapes;
  }

  /**
   * Gets this model's top left point.
   *
   * @return the top left point of this model
   */
  @Override
  public Point2D getPoint() {
    Point2D modelPoint = model.getPoint();
    return new Point2D.Double(modelPoint.getX(), modelPoint.getY());
  }

  /**
   * Gets this model's width.
   *
   * @return this model's width
   */
  @Override
  public double getWidth() {
    return model.getWidth();
  }

  /**
   * Gets this model's height.
   *
   * @return this model's height
   */
  @Override
  public double getHeight() {
    return model.getHeight();
  }

  /**
   * Sets this model's top left point.
   *
   * @param point the point to be set
   */
  @Override
  public void setPoint(Point2D point) {
    throw new UnsupportedOperationException("Cannot mutate model");
  }

  /**
   * Sets this model's width to the given width.
   *
   * @param w the width to set this model's width to
   */
  @Override
  public void setWidth(double w) {
    throw new UnsupportedOperationException("Cannot mutate model");
  }

  /**
   * Sets this model's height to the given height.
   *
   * @param h the height to set this model's height to
   */
  @Override
  public void setHeight(double h) {
    throw new UnsupportedOperationException("Cannot mutate model");
  }

  /**
   * Edits the motion with the same shapeID and start time with the following positional size, and
   * color specifications, or else throws and exception.
   *
   * @param motionToAdd the new motion for the shape
   * @param shapeID the ID of the shape
   */
  @Override
  public void modifyMotion(IMotion motionToAdd, String shapeID)
      throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Cannot mutate model");
  }

  /**
   * Returns the amount of motions for the shape with the given unique shapeID.
   *
   * @param shapeID the shapeID of the shape
   * @return the number of motions the shape has
   */
  @Override
  public int getNumberOfMotions(String shapeID) {
    return model.getNumberOfMotions(shapeID);
  }

  /**
   * Removes everything in this animation
   */
  @Override
  public void removeEverything() {
    throw new UnsupportedOperationException("cannot mutate model");
  }
}
