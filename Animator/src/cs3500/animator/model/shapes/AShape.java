
package cs3500.animator.model.shapes;

import cs3500.animator.model.motion.IMotion;
import cs3500.animator.model.motion.MotionImpl;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Represents a shape by abstracting all the similar qualities of that shape with the other shapes.
 */
public abstract class AShape implements IShape {

  private final String name;
  private ArrayList<IMotion> moves;

  /**
   * Constructs a shape given its unique name with an empty ArrayList of IMotions.
   *
   * @param name the unqiue name of this shape
   */
  protected AShape(String name) {
    this.name = name;
    this.moves = new ArrayList<IMotion>();
  }

  /**
   * Constructs a shape given its unique name and ArrayList of IMotions.
   *
   * @param name the unqiue name of this shape
   * @param moves this shapes moves, which is an ArrayList of IMotions
   */
  protected AShape(String name, ArrayList<IMotion> moves) {
    this.name = name;
    this.moves = moves;
  }

  /**
   * Gets the moves of this Shape.
   *
   * @return an ArrayList of IMotion
   */
  @Override
  public ArrayList<IMotion> getMoves() {
    return moves;
  }

  /**
   * Returns the name of this IShape.
   *
   * @return the unique name of this shape
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Adds a move to this shape's list of moves.
   *
   * @param motion the move to be added
   */
  @Override
  public void addMove(IMotion motion) throws IllegalArgumentException {
    int index = 0;
    for (IMotion m : this.moves) {
      if (motion.getStartTime() == m.getStartTime()) {
        throw new IllegalArgumentException("motion exists with same start time");
      }
      if (motion.getStartTime() > m.getStartTime()) {
        index++;
      }
    }

    if (motion.getX() < 0
        || motion.getY() < 0
        || motion.getStartTime() < 0
        || motion.getWidth() < 0
        || motion.getHeight() < 0) {
      throw new IllegalArgumentException("Cannot have any negative values");

    } else if (moves.size() >= 1) {
      IMotion previous = moves.get(index - 1);
      if (motion.getX() != previous.getX()
          || motion.getY() != previous.getY()
          && motion.getWidth() == previous.getWidth()
          && motion.getHeight() == previous.getHeight()
          && motion.getColor().equals(previous.getColor())
          && motion.getStartTime() > previous.getStartTime()) {
        this.moves.add(index, motion);
      } else if (motion.getX() == previous.getX()
          && motion.getY() == previous.getY()
          && (motion.getWidth() != previous.getWidth()
          || motion.getHeight() != previous.getHeight())
          && motion.getColor().equals(previous.getColor())
          && motion.getStartTime() > previous.getStartTime()) {
        this.moves.add(index, motion);
      } else if (motion.getX() == previous.getX()
          && motion.getY() == previous.getY()
          && motion.getWidth() == previous.getWidth()
          && motion.getHeight() == previous.getHeight()
          && !motion.getColor().equals(previous.getColor())
          && motion.getStartTime() > previous.getStartTime()) {
        this.moves.add(index, motion);
      } else if (motion.getX() == previous.getX()
          && motion.getY() == previous.getY()
          && motion.getWidth() == previous.getWidth()
          && motion.getHeight() == previous.getHeight()
          && motion.getColor().equals(previous.getColor())
          && motion.getStartTime() > previous.getStartTime()) {
        this.moves.add(index, motion);
      } else {
        throw new IllegalArgumentException("Illegal move. Can only move position or resize or "
            + "change color at one time");
      }
    }
    // if there are no motions in the current shape
    else {
      this.moves.add(motion);
    }
  }

  /**
   * Returns the shape at the given tick.
   *
   * @param time the time we want to get the shape ata
   * @return the IShape at the given time (or tick)
   */
  @Override
  public IMotion calcShapeAt(int time) {
    IMotion startPoint;
    IMotion endPoint;

    try {
      startPoint = moves.get(0); // first appearance of shape
      endPoint = startPoint;
    } catch (IndexOutOfBoundsException e) {
      throw new IllegalArgumentException("Shape has not appeared yet");
    }

    // catching if the shape hasn't appeared yet, if appeared it stays until removed
    // so no need to catch the after last motion case
    if (time < moves.get(0).getStartTime()) {
      throw new IllegalArgumentException("Shape has not appeared yet");
    }
    if (moves.size() == 0) {
      throw new IllegalArgumentException("Given shape doesn't have any motions");
    }

    if (moves.size() == 1) {
      return startPoint;
    }

    for (IMotion motion : moves) {
      if (motion.getStartTime() > time) {
        endPoint = moves.get(moves.indexOf(motion));
        break;
      }
      startPoint = moves.get(moves.indexOf(motion));
    }

    double resultX =
        this.tween(startPoint.getX(), endPoint.getX(),
            startPoint.getStartTime(), endPoint.getStartTime(), time);
    double resultY =
        this.tween(startPoint.getY(), endPoint.getY(),
            startPoint.getStartTime(), endPoint.getStartTime(), time);
    double resultWidth = this.tween(startPoint.getWidth(), endPoint.getWidth(),
        startPoint.getStartTime(), endPoint.getStartTime(), time);
    double resultHeight = this.tween(startPoint.getHeight(), endPoint.getHeight(),
        startPoint.getStartTime(), endPoint.getStartTime(), time);

    int r = (int) this.tween(startPoint.getColor().getRed(), endPoint.getColor().getRed(),
        startPoint.getStartTime(), endPoint.getStartTime(), time);
    int g = (int) this.tween(startPoint.getColor().getGreen(), endPoint.getColor().getGreen(),
        startPoint.getStartTime(), endPoint.getStartTime(), time);
    int b = (int) this.tween(startPoint.getColor().getBlue(), endPoint.getColor().getBlue(),
        startPoint.getStartTime(), endPoint.getStartTime(), time);

    Color resultColor = new Color(r, g, b);

    return new MotionImpl(time, resultX, resultY, resultWidth, resultHeight, resultColor);
  }

  /**
   * Returns the type of this shape, oval or rectangle for example.
   *
   * @return a String describing what type of shape this is
   */
  public abstract String getType();

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
  @Override
  public double tween(double startPoint, double endPoint, int startTime, int endTime, int time) {
    int denominator = endTime - startTime;

    return (startPoint * (endTime - time) / denominator)
        + (endPoint * (time - startTime) / denominator);
  }

  /**
   * Makes a copy of this IShape.
   *
   * @return a copy of this IShape
   */
  @Override
  public abstract IShape copy();

  /**
   * Makes a copy of this IShape's moves.
   *
   * @return a copy of this IShape moves
   */
  @Override
  public ArrayList<IMotion> copyMoves() {
    ArrayList<IMotion> newMoves = new ArrayList<>();
    newMoves.addAll(this.moves);
    return newMoves;
  }

  /**
   * Determines the proper output in SVG format specific to motions for the move at the given motion
   * index of the shape.
   *
   * @param motionIndex the index of the desired motion in this shape's list of motions
   * @return a string describing the motion
   */
  @Override
  public String determineMove(int motionIndex, int speed) {
    String result = "";
    String moveType;
    moveType = getMoveType(motionIndex);
    IMotion currentMotion = getMoves().get(motionIndex);
    IMotion prevMotion = getMoves().get(motionIndex - 1);
    int milliseconds = (int) calcMilliSeconds(currentMotion.getStartTime()
        - prevMotion.getStartTime(), speed);
    switch (moveType) {
      case "move x":
        result += " dur=\"" + milliseconds
            + "ms\" attributeName=\"" + this.getXType() + "\" from=\"" + prevMotion.getX()
            + "\" to=\"" + currentMotion.getX() + "\" fill=\"freeze\" />\n";
        break;
      case "move y":
        result += " dur=\"" + milliseconds
            + "ms\" attributeName=\"" + this.getYType() + "\" from=\"" + prevMotion.getY()
            + "\" to=\"" + currentMotion.getY() + "\" fill=\"freeze\" />\n";
        break;
      case "move both":
        result += " dur=\"" + milliseconds
            + "ms\" attributeName=\"" + this.getXType() + "\" from=\"" + prevMotion.getX()
            + "\" to=\"" + currentMotion.getX() + "\" fill=\"freeze\" />\n";
        result += "<animate " + "attributeType=\"xml\"" + " begin=\""
            + calcMilliSeconds(prevMotion.getStartTime(), speed) + "ms\"" + " dur=\""
            + milliseconds + "ms\" " + "attributeName=\"" + this.getYType() + "\" from=\""
            + prevMotion.getY() + "\" to=\"" + currentMotion.getY() + "\" fill=\"freeze\" />\n";
        break;
      case "width":
        result += " dur=\"" + milliseconds
            + "ms\" attributeName=\"" + this.getWidthText() + "\" from=\"" + prevMotion.getWidth()
            + "\" to=\""
            + currentMotion.getWidth() + "\" fill=\"freeze\" />\n";
        break;
      case "height":
        result += "dur=\"" + milliseconds
            + "ms\" attributeName=\"" + this.getHeightText() + "\" from=\"" + prevMotion.getHeight()
            + "\" to=\""
            + currentMotion.getHeight() + "\" fill=\"freeze\" />\n";
        break;
      case "width and height":
        result += " dur=\"" + milliseconds
            + "ms\" attributeName=\"" + this.getWidthText() + "\" from=\"" + prevMotion.getWidth()
            + "\" to=\""
            + currentMotion.getWidth() + "\" fill=\"freeze\" />\n";
        result += "<animate " + "attributeType=\"xml\"" + " begin=\""
            + calcMilliSeconds(prevMotion.getStartTime(), speed) + "ms\""
            + " dur=\"" + milliseconds
            + "ms\" attributeName=\"" + this.getHeightText() + "\" from=\"" + prevMotion.getHeight()
            + "\" to=\""
            + currentMotion.getHeight() + "\" fill=\"freeze\" />\n";
        break;
      case "color":
        result += " dur=\"" + milliseconds
            + "ms\" attributeName=\"color\" from=\"(" + prevMotion.getColor().getRed() + ","
            + prevMotion.getColor().getGreen() + "," + prevMotion.getColor().getBlue()
            + ")\" to=\"(" + currentMotion.getColor().getRed() + ","
            + currentMotion.getColor().getGreen() + "," + currentMotion.getColor().getBlue()
            + ")\" fill=\"freeze\" />\n";
        break;
      default:
        result += " dur=\"" + milliseconds
            + "ms\" fill=\"freeze\" />\n";
    }
    return result;
  }

  /**
   * Determines the type of motion the motion at the given index in this shape is.
   *
   * @param motionIndex the index of the motion to be examined
   * @return a string representing the type of motion it is
   */
  private String getMoveType(int motionIndex) {
    IMotion currentMotion = getMoves().get(motionIndex);
    IMotion prevMotion = getMoves().get(motionIndex - 1);

    if (prevMotion.getX() != currentMotion.getX() && prevMotion.getY() != currentMotion.getY()) {
      return "move both";
    } else if (prevMotion.getX() != currentMotion.getX()) {
      return "move x";
    } else if (prevMotion.getY() != currentMotion.getY()) {
      return "move y";
    } else if (prevMotion.getWidth() != currentMotion.getWidth()
        && prevMotion.getHeight() != currentMotion.getHeight()) {
      return "width and height";
    } else if (prevMotion.getWidth() != currentMotion.getWidth()) {
      return "width";
    } else if (prevMotion.getHeight() != currentMotion.getHeight()) {
      return "height";
    } else if (prevMotion.getColor().getRed() != currentMotion.getColor().getRed()
        || prevMotion.getColor().getGreen() != currentMotion.getColor().getGreen()
        || prevMotion.getColor().getBlue() != currentMotion.getColor().getBlue()) {
      return "color";
    } else {
      return "no change";
    }
  }

  /**
   * Delegates to each shape to get its x type, just "x" for a rectangle and "cx" for an ellipse.
   *
   * @return a String describing this shape's x value
   */
  @Override
  public abstract String getXType();

  /**
   * Delegates to each shape to get its y type, just "y" for a rectangle and "cy" for an ellipse.
   *
   * @return a String describing this shape's y value
   */
  @Override
  public abstract String getYType();

  /**
   * Delegates to each shape to get its width text, "width" for a rectangle but "rx" for an
   * ellipse.
   *
   * @return the text describing this shape's width
   */
  @Override
  public abstract String getWidthText();

  /**
   * Delegates to each shape to get its height text, "height" for a rectangle but "ry" for an
   * ellipse.
   *
   * @return a String describing this shape's height
   */
  @Override
  public abstract String getHeightText();

  /**
   * Calculates the milliseconds equivalent of the given tick amount.
   *
   * @param ticks the ticks to be converted
   * @return the millisecond equivalent of the given ticks
   */
  @Override
  public double calcMilliSeconds(int ticks, int speed) {
    return ticks * speed;
  }

  /**
   * Draws this shape at the current time.
   *
   * @param g the graphics to be used to draw the shape
   * @param tick the current time
   */
  @Override
  public void draw(Graphics g, int tick) {
    if (tick < this.moves.get(0).getStartTime()) {
      return;
    }
    IMotion shapeAtTick = calcShapeAt(tick);
    g.setColor(shapeAtTick.getColor());
    drawShape(g, shapeAtTick);
  }

  /**
   * Draws this shape to the screen using the current graphics and the shape.
   *
   * @param g the graphics used to draw the shape
   * @param shapeAtTick the shape at the desired time in the animation
   */
  public abstract void drawShape(Graphics g, IMotion shapeAtTick);

  /**
   * Edits this shape's motion with the same start time with the following positional size, and
   * color specifications of the given motionToAdd, or else throws and exception.
   *
   * @param motionToAdd the new motion for the shape
   */
  public void modifyMotion(IMotion motionToAdd) {
    for (int i = 0; i < moves.size(); i++) {
      IMotion move = moves.get(i);
      if (move.getStartTime() == motionToAdd.getStartTime()) {
        if (i == 0 && moves.size() == 1) {
          moves.get(i).changeEverything(motionToAdd);
          return;
        }
        if (i == 0 && moves.size() > 1) {
          IMotion nextMotion = this.moves.get((i + 1));
          if (this.isValidMove(motionToAdd, nextMotion)) {
            moves.get(i).changeEverything(motionToAdd);
            return;
          }
        } else if (i > 0 && i < moves.size() - 1) {
          IMotion previousMotion = this.moves.get(i - 1);
          IMotion nextMotion = this.moves.get((i + 1));
          if (this.isValidMove(previousMotion, motionToAdd)
              && this.isValidMove(motionToAdd, nextMotion)) {
            moves.get(i).changeEverything(motionToAdd);
            return;
          }
        } else if (i == moves.size() - 1) {
          IMotion previousMotion = this.moves.get(i - 1);
          if (this.isValidMove(previousMotion, motionToAdd)) {
            moves.get(i).changeEverything(motionToAdd);
            return;
          }
        }
      }
    }
    throw new IllegalArgumentException("motion to add is not valid");
  }

  /**
   * Returns if the move from motion1 to motion2 is a valid move, i.e., only one thing (x & y
   * positions, width and height, or color) is changing at a time.
   *
   * @param motion1 the first motion
   * @param motion2 the second motion
   * @return whether or not this is a valid move
   */
  private boolean isValidMove(IMotion motion1, IMotion motion2) {
    return
        // changing the position
        (motion1.getX() != motion2.getX()
            || motion1.getY() != motion2.getY()
            && motion1.getWidth() == motion2.getWidth()
            && motion1.getHeight() == motion2.getHeight()
            && motion1.getColor().equals(motion2.getColor()))
            // changing the size
            || (motion1.getX() == motion2.getX()
            && motion1.getY() == motion2.getY()
            && (motion1.getWidth() != motion2.getWidth()
            || motion1.getHeight() != motion2.getHeight())
            && motion1.getColor().equals(motion2.getColor()))
            // changing the color
            || (motion1.getX() == motion2.getX()
            && motion1.getY() == motion2.getY()
            && motion1.getWidth() == motion2.getWidth()
            && motion1.getHeight() == motion2.getHeight()
            && !motion1.getColor().equals(motion2.getColor()));
  }
}

