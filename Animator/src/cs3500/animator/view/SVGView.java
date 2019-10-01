package cs3500.animator.view;

import java.io.IOException;

import cs3500.animator.model.animatormodel.ViewModel;
import cs3500.animator.model.motion.IMotion;
import cs3500.animator.model.shapes.IShape;

/**
 * An implementation of the IView interface that utilizes an SVG file format, which is XML-based.
 */
public class SVGView implements IView {

  private ViewModel model;
  private Appendable ap;
  private int speed;

  /**
   * Constructs a new SVGView given a ViewModel, an Appendable (which is where to write the
   * information about this animation to) and the speed at which to play the animation.
   *
   * @param model the ViewModel to be used in this animation
   * @param ap the appendable to write all the information about this animation to
   * @param speed the speed at which to play this animation
   * @throws IllegalArgumentException if the model or appendable is null or if the speed is less
   *                                  than 1
   */
  public SVGView(ViewModel model, Appendable ap, int speed) throws IllegalArgumentException {
    if (model == null || ap == null || speed < 1) {
      throw new IllegalArgumentException("invalid input arguments");
    }
    this.model = model;
    this.ap = ap;
    this.speed = 1000 / speed;
  }


  /**
   * Displays the animation in a SVG format.
   */
  @Override
  public void display() throws IllegalArgumentException {
    try {
      ap.append("<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"" + model.getWidth()
          + "\" height=\"" + model.getHeight()
          + "\" version=\"1.1\">" + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("Can not append - SVG");
    }

    for (IShape shape : model.getAllShapes()) {
      // no motions
      if (shape.getMoves().size() == 0) {
        try {
          ap.append("<id=\"" + shape.getName() + "\">\n");
        } catch (IOException e) {
          throw new IllegalStateException("Can not append SVG0");
        }
      }
      // only one motion - only appears in animation but never moves
      else if (shape.getMoves().size() == 1) {
        IMotion oneMotion = shape.getMoves().get(0);
        try {
          ap.append(
              "<" + shape.getType() //+ " xmlns=\"http://www.w3.org/2000/svg\" "
                  + "id=\"" + shape.getName() + "\" x=\"" + oneMotion.getX()
                  + "\" y=\"" + oneMotion.getY() + "\" width=\"" + oneMotion.getWidth()
                  + "\" height=\""
                  + oneMotion.getHeight() + "\" fill=\"rgb(" + oneMotion.getColor().getRed() + ","
                  + oneMotion.getColor().getGreen() + "," + oneMotion.getColor().getBlue() + ")"
                  + "\" visibility=\"hidden\">\n");
        } catch (IOException e) {
          throw new IllegalStateException("Can not append SVG1");
        }
      }
      // at least one <animate> line
      else {
        int motionIndex = 0;

        for (IMotion motion : shape.getMoves()) {
          // index of motion we're currently on, would only reach here if it had 1 or more motions
          try {
            if (motionIndex == 0) {
              ap.append(
                  "<" + shape.getType() + " xmlns=\"http://www.w3.org/2000/svg\" id=\""
                      + shape.getName() + "\" " + shape.getXType()
                      + "=\"" + motion.getX()
                      + "\" " + shape.getYType() + "=\"" + motion.getY() + "\" " + shape
                      .getWidthText() + "=\"" + motion.getWidth()
                      + "\" " + shape.getHeightText() + "=\""
                      + motion.getHeight() + "\" fill=\"rgb(" + motion.getColor().getRed() + ","
                      + motion.getColor().getGreen() + "," + motion.getColor().getBlue() + ")"
                      + "\" visibility=\"hidden\">\n" + "<set attributeType=\"xml\" "
                      + "begin=\"" + shape.getMoves().get(0).getStartTime() + "ms\" attributeName"
                      + "=\"visibility\" to=\"visible\"/>\n"
                      + "<set attributeType=\"xml\" "
                      + "begin=\"" + shape.calcMilliSeconds(
                      shape.getMoves().get(shape.getMoves().size() - 1).getStartTime(), speed)
                      + "ms\" attributeName=\"visibility\" to=\"hidden\"/>\n");
              motionIndex++;
            } else {
              ap.append("<animate " + "xmlns=\"http://www.w3.org/2000/svg\" " + "attributeType=\""
                  + "xml\"" + " begin=\""
                  + shape.calcMilliSeconds(shape.getMoves().get(motionIndex - 1).getStartTime(),
                  speed) + "ms\"" + shape.determineMove(motionIndex, speed));
              motionIndex++;
            }
          } catch (IOException e) {
            throw new IllegalStateException("Can not append SVG2+");
          }
        }
        try {
          ap.append("</" + shape.getType() + ">\n");
        } catch (IOException e) {
          throw new IllegalStateException("Can't close the shape's motions");
        }

      }
    }
    try {
      ap.append("</svg>");
    } catch (IOException e) {
      throw new IllegalStateException("Can not append ending </svg>");
    }
  }

  /**
   * Gets the appendable of this SVGView animation.
   *
   * @return the appendable, the output
   */
  @Override
  public Appendable getAp() {
    return this.ap;
  }
}

