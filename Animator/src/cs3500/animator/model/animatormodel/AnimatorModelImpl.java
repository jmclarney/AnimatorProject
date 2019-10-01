package cs3500.animator.model.animatormodel;

import cs3500.animator.model.motion.IMotion;
import cs3500.animator.model.motion.MotionImpl;
import cs3500.animator.model.shapes.MyEllipse;
import cs3500.animator.model.shapes.MyRectangle;
import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import cs3500.animator.util.AnimationBuilder;

import cs3500.animator.model.shapes.IShape;

/**
 * Class that represents an implementation of the IAnimatorModel that represents an animator. The
 * animation includes the size of the window visible to the user (width and height) as well as the
 * top left point of the window. This animator can create simple 2D animations.
 */
public final class AnimatorModelImpl implements IAnimatorModel {

  private Point2D topPoint;
  private double width;
  private double height;

  private List<IShape> allShapes;

  /**
   * Constructs a new AnimatorModelImpl with a new ArrayList of IShapes.
   */
  public AnimatorModelImpl() {

    this.allShapes = new ArrayList<>();
    this.topPoint = new Point2D.Double(0, 0);
    this.width = 1000;
    this.height = 1000;
  }

  /**
   * A Builder class to build the animation model based on the given file.
   */
  public static final class Builder implements AnimationBuilder<IAnimatorModel> {

    IAnimatorModel model;

    /**
     * A Constructor for the builder to build an animation model.
     */
    public Builder() {
      this.model = new AnimatorModelImpl();
    }

    /**
     * Constructs a final document.
     *
     * @return the newly constructed document
     */
    @Override
    public IAnimatorModel build() {
      return this.model;
    }

    /**
     * Specify the bounding box to be used for the animation.
     *
     * @param x The leftmost x value
     * @param y The topmost y value
     * @param width The width of the bounding box
     * @param height The height of the bounding box
     * @return This {@link AnimationBuilder}
     */
    @Override
    public AnimationBuilder<IAnimatorModel> setBounds(int x, int y, int width, int height) {
      this.model.setPoint(new Point2D.Double(x, y));
      this.model.setWidth(width);
      this.model.setHeight(height);
      return this;
    }

    /**
     * Adds a new shape to the growing document.
     *
     * @param name The unique name of the shape to be added. No shape with this name should already
     *        exist.
     * @param type The type of shape (e.g. "ellipse", "rectangle") to be added. The set of supported
     *        shapes is unspecified, but should include "ellipse" and "rectangle" as a minimum.
     * @return This {@link AnimationBuilder}
     */
    @Override
    public AnimationBuilder<IAnimatorModel> declareShape(String name, String type) {
      this.model.addShape(name, type);
      return this;
    }

    /**
     * Adds a transformation to the growing document.
     *
     * @param name The name of the shape (added with {@link AnimationBuilder#declareShape})
     * @param t1 The start time of this transformation
     * @param x1 The initial x-position of the shape
     * @param y1 The initial y-position of the shape
     * @param w1 The initial width of the shape
     * @param h1 The initial height of the shape
     * @param r1 The initial red color-value of the shape
     * @param g1 The initial green color-value of the shape
     * @param b1 The initial blue color-value of the shape
     * @param t2 The end time of this transformation
     * @param x2 The final x-position of the shape
     * @param y2 The final y-position of the shape
     * @param w2 The final width of the shape
     * @param h2 The final height of the shape
     * @param r2 The final red color-value of the shape
     * @param g2 The final green color-value of the shape
     * @param b2 The final blue color-value of the shape
     * @return This {@link AnimationBuilder}
     */
    @Override
    public AnimationBuilder<IAnimatorModel> addMotion(String name, int t1, int x1, int y1, int w1,
        int h1, int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2,
        int b2) {
      for (IShape shape : model.getAllShapes()) {
        if (name.equals(shape.getName())) {
          IMotion newMotion = new MotionImpl(t1, x1, y1, w1, h1, new Color(r1, g1, b1));
          IMotion newMotion2 = new MotionImpl(t2, x2, y2, w2, h2, new Color(r2, g2, b2));
          try {
            shape.addMove(newMotion);
          } catch (Exception e) {
            try {
              shape.addMove(newMotion2);
            } catch (Exception e2) {
              // don't add either
            }
          }
          break;
        }
      }
      return this;
    }

    /**
     * Adds an individual keyframe to the growing document.
     *
     * @param name The name of the shape (added with {@link AnimationBuilder#declareShape})
     * @param t The time for this keyframe
     * @param x The x-position of the shape
     * @param y The y-position of the shape
     * @param w The width of the shape
     * @param h The height of the shape
     * @param r The red color-value of the shape
     * @param g The green color-value of the shape
     * @param b The blue color-value of the shape
     * @return This {@link AnimationBuilder}
     */
    @Override
    public AnimationBuilder<IAnimatorModel> addKeyframe(String name, int t, int x, int y, int w,
        int h, int r, int g, int b) {
      for (IShape shape : model.getAllShapes()) {
        if (name.equals(shape.getName())) {
          IMotion newMotion = new MotionImpl(t, x, y, w, h, new Color(r, g, b));
          shape.addMove(newMotion);
          break;
        }
      }
      return this;
    }
  }

  // --- END BUILDER ---


  /**
   * Gets the description of all of the shapes in this animator and all of their motions.
   *
   * @return a String describing every shape and their motions
   */
  @Override
  public String getDescription() {
    if (allShapes.size() == 0) {
      return "No description available. No shapes in this model";
    }

    String result = "";
    for (IShape shape : allShapes) {
      result += "shape " + shape.getName() + " " + shape.getType() + "\n";

      String previousMotion = "";
      for (int i = 0; i < shape.getMoves().size(); i++) {
        IMotion motion = shape.getMoves().get(i);

        if (i == 0) {
          result += "motion " + shape.getName() + " "
              + motion.getStartTime() + " " + motion.getX() + " " + motion.getY() + " "
              + motion.getWidth() + " " + motion.getHeight() + " " + motion.getColor().getRed()
              + " " + motion.getColor().getGreen() + " " + motion.getColor().getBlue() + "   ";
        } else if (i == 1) {
          previousMotion = motion.getStartTime() + " " + motion.getX() + " " + motion.getY() + " "
              + motion.getWidth() + " " + motion.getHeight() + " " + motion.getColor().getRed()
              + " " + motion.getColor().getGreen() + " " + motion.getColor().getBlue();
          result += previousMotion + "\n";
        } else {
          result += "motion " + shape.getName() + " " + previousMotion + "   "
              + motion.getStartTime() + " " + motion.getX() + " " + motion.getY() + " "
              + motion.getWidth() + " " + motion.getHeight() + " " + motion.getColor().getRed()
              + " " + motion.getColor().getGreen() + " " + motion.getColor().getBlue() + "\n";
          previousMotion = motion.getStartTime() + " " + motion.getX() + " " + motion.getY() + " "
              + motion.getWidth() + " " + motion.getHeight() + " " + motion.getColor().getRed()
              + " " + motion.getColor().getGreen() + " " + motion.getColor().getBlue();
        }
      }
    }
    return result;
  }

  /**
   * Adds a motion to the given shape's list of motions.
   *
   * @param motion the IMotion to be added
   * @param shapeID the ID of the shape the IMotion is to be added to
   * @throws IllegalArgumentException if there is already a motion in this shape at the same time or
   *                                   if the shape is not already in the model's list of shapes as
   *                                   the shape needs to be added before the motion is added to it.
   */
  @Override
  public void addMotion(IMotion motion, String shapeID) {
    for (IShape shape : allShapes) {
      if (shapeID.equals(shape.getName())) {
        for (IMotion m : shape.getMoves()) {
          if (m.getStartTime() == motion.getStartTime()) {
            throw new IllegalArgumentException("motion at the given time already exists");
          }
        }

        if (this.allShapes.contains(shape)) {
          shape.addMove(motion);
        } else {
          throw new IllegalArgumentException("Shape does not exist. Must add shape before adding"
              + " motion to it");
        }
      }
    }
  }

  /**
   * Removes a motion from the given shape's list of motions.
   *
   * @param motionTime the time of the motion to be removed
   * @param shapeID the shape ID of the shape the motion should be removed from
   * @throws IllegalArgumentException if the shape doesn't exist in the list of shapes or if the
   *                                  motion isn't in the shape's list of motions
   */
  @Override
  public void removeMotion(int motionTime, String shapeID) throws IllegalArgumentException {
    for (IShape shape : allShapes) {
      if (shapeID.equals(shape.getName())) {
        for (IMotion m : shape.getMoves()) {
          if (m.getStartTime() == motionTime) {
            shape.getMoves().remove(m);
            return;
          }
        }
        throw new IllegalArgumentException("motion does not exist");
      }
    }
    throw new IllegalArgumentException("Shape does not exist. Can not remove motion.");
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
    for (IShape s : allShapes) {
      if (s.getName().equals(shapeID)) {
        throw new IllegalArgumentException("Can not add because shape name already exists");
      }
    }
    if (shapeType.equals("rectangle")) {
      this.allShapes.add(new MyRectangle(shapeID));
    } else if (shapeType.equals("ellipse")) {
      this.allShapes.add(new MyEllipse(shapeID));
    } else {
      throw new IllegalArgumentException("shape type does not exist");
    }
  }

  /**
   * Removes the given shape from this model's list of shapes.
   *
   * @param shapeID the ID of the shape to be removed
   * @throws IllegalArgumentException if the model's list of shapes does not contain this shape
   */
  @Override
  public void removeShape(String shapeID) throws IllegalArgumentException {
    for (IShape s : allShapes) {
      if (s.getName().equals(shapeID)) {
        allShapes.remove(s);
        return;
      }
    }
    throw new IllegalArgumentException("Can not remove Shape. Shape does not exist.");
  }

  /**
   * Returns a list of all of the shapes in this model.
   *
   * @return all of the shapes in this model's list of shapes.
   */
  @Override
  public List<IShape> getAllShapes() {
    return allShapes;
  }

  /**
   * Gets this model's top left point.
   *
   * @return the top left point of this model
   */
  @Override
  public Point2D getPoint() {
    return this.topPoint;
  }

  /**
   * Gets this model's width.
   *
   * @return this model's width
   */
  @Override
  public double getWidth() {
    return this.width;
  }

  /**
   * Gets this model's height.
   *
   * @return this model's height
   */
  @Override
  public double getHeight() {
    return this.height;
  }

  /**
   * Sets this model's top left point.
   *
   * @param point the point to be set
   */
  @Override
  public void setPoint(Point2D point) {

    this.topPoint = point;
  }

  /**
   * Sets this model's width to the given width.
   *
   * @param w the width to set this model's width to
   */
  @Override
  public void setWidth(double w) {
    this.width = w;
  }

  /**
   * Sets this model's height to the given height.
   *
   * @param h the height to set this model's height to
   */
  @Override
  public void setHeight(double h) {
    this.height = h;
  }

  /**
   * Edits the motion with the same shapeID and start time with the following positional size, and
   * color specifications, or else throws and exception.
   *
   * @param motionToAdd the new motion for the shape
   * @param shapeID the ID of the shape
   */
  @Override
  public void modifyMotion(IMotion motionToAdd, String shapeID) throws IllegalArgumentException {
    for (IShape shape : allShapes) {
      if (shape.getName().equals(shapeID)) {
        shape.modifyMotion(motionToAdd);
        return;
      }
    }
    throw new IllegalArgumentException("shape not found");
  }

  /**
   * Returns the amount of motions for the shape with the given unique shapeID.
   *
   * @param shapeID the shapeID of the shape
   * @return the number of motions the shape has
   */
  @Override
  public int getNumberOfMotions(String shapeID) {
    for (IShape s : allShapes) {
      if (s.getName().equals(shapeID)) {
        return s.getMoves().size();
      }
    }
    throw new IllegalArgumentException("could not find shape");
  }

  /**
   * Removes everything in this animation
   */
  @Override
  public void removeEverything() {
    for (IShape s : allShapes){
      this.removeShape(s.getName());
    }
  }
}
