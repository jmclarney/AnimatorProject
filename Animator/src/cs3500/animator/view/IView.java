package cs3500.animator.view;

/**
 * This interface represents the operations offered by the view for an animator model. One object of
 * the IView interface represents one view.
 */
public interface IView {

  /**
   * Displays the animation.
   *
   * @throws IllegalArgumentException if the appendable
   */
  void display() throws IllegalArgumentException;

  /**
   * Gets the appendable for this view implementation.
   *
   * @return the appendable object for this implementation of IView
   * @throws UnsupportedOperationException if this view doesn't have an Appendable object
   */
  Appendable getAp() throws UnsupportedOperationException;
}
