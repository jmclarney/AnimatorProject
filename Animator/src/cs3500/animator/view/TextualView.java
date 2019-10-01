package cs3500.animator.view;

import cs3500.animator.model.animatormodel.ViewModel;
import java.io.IOException;
import javax.swing.JFrame;

/**
 * An implementation of the IView interface that is shown in text form.
 */
public class TextualView extends JFrame implements IView {

  private ViewModel model;
  private Appendable ap;

  /**
   * Constructs a new visual view using the given ViewModel and Appendable.
   *
   * @param model the model to be used
   * @param ap the appendable to write information about the animation to
   */
  public TextualView(ViewModel model, Appendable ap) {
    if (model == null || ap == null) {
      throw new IllegalStateException("neither model or appendable can be null");
    }
    this.model = model;
    this.ap = ap;
  }

  /**
   * Displays the animation.
   */
  @Override
  public void display() {
    try {
      ap.append("canvas " + model.getPoint().getX() + " " + model.getPoint().getY() + " "
          + model.getWidth() + " " + model.getHeight() + "\n" + model.getDescription());
    } catch (IOException e) {
      throw new IllegalStateException("could not append from the text view");
    }
  }

  /**
   * Gets the appendable of this TextualView animation.
   *
   * @return the appendable, the output
   */
  public Appendable getAp() {
    return this.ap;
  }
}
