package cs3500.animator.view;

import cs3500.animator.model.animatormodel.ViewModel;
import cs3500.animator.model.shapes.IShape;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * Represents a JPanel with its paintComponent overridden to account for the current time. Shapes
 * should stay on the screen after their motions run out unless they specifically have a motion to
 * disappear.
 */
public class MyJPanel extends JPanel {

  private ViewModel model;
  private int timePassed;

  /**
   * Constructs a MyJPanel.
   *
   * @param model the model being used for the animation
   * @param timePassed the time passed in the animation
   */
  MyJPanel(ViewModel model, int timePassed) {
    if (model == null) {
      throw new IllegalArgumentException("model cannot be null");
    } else if (timePassed < 0) {
      throw new IllegalArgumentException("time cannot be negative");
    }
    this.model = model;
    this.timePassed = timePassed;
  }

  /**
   * Overridden from JPanel's paintComponent. Shapes stay on the screen until they are told to
   * disappear.
   *
   * @param g the graphics on which to draw
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    for (IShape shape : model.getAllShapes()) {
      if (shape.getMoves().size() == 0) {
        // do nothing
      } else {
        int lastStartTime = shape.getMoves().get(shape.getMoves().size() - 1).getStartTime();
        if (this.timePassed > lastStartTime) {
          // keeps the shape in the same spot, doesn't disappear
          shape.draw(g, lastStartTime);
        } else {
          shape.draw(g, this.timePassed);
        }
      }
    }
    this.timePassed++;
  }

  /**
   * Gets the time passed in this MyJPanel.
   *
   * @return an int representing the TimePassed
   */
  protected int getTimePassed() {
    return this.timePassed;
  }

  /**
   * Sets this MyJPanel's timePassed to the given int representing the new timePassed.
   *
   * @param i the new timePassed
   */
  protected void setTimePassed(int i) {
    this.timePassed = i;
  }

  /**
   * Updates the model in this MyJPanel to the given ViewModel.
   *
   * @param m the new ViewModel
   */
  protected void updateModel(ViewModel m) {
    this.model = m;
  }
}

