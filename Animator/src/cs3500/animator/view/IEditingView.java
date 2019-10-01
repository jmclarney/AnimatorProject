package cs3500.animator.view;

import cs3500.animator.model.animatormodel.ViewModel;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

/**
 * This interface represents the operations offered by an editing view for an animator model.
 * One object of the IEditingView interface represents one editing view.
 */
public interface IEditingView extends IView {

  /**
   * Sets the ActionListener for the view that implements this interface.
   *
   * @param listener The ActionListener for the IEditingView implementation
   */
  void setListener(ActionListener listener);

  /**
   * Gets the text input in the text field.
   *
   * @return a String of the text input
   */
  String getTextInputString();

  /**
   * Appends the given string to the text area.
   *
   * @param s the String to be appended to the text area
   */
  void appendTextArea(String s);

  /**
   * Updates this View's model to the given model after it is mutated in the controller.
   *
   * @param model the new model
   */
  void updateModel(ViewModel model);

  /**
   * Gets this view's combo box, which allows the controller to see which element was
   * clicked.
   *
   * @return the JComboBox for this view
   */
  JComboBox getComboBox();

  /**
   * Updates this combo box by removing the element at the given index and the given number after.
   *
   * @param selectionIndex the initial index to remove
   * @param numMotions how many motions to remove after the initial index
   */
  void updateComboBox(int selectionIndex, int numMotions);

  /**
   * Removes all items from this view implementation's JComboBox and recreates it. Used after
   * adding a shape or a motion to the Animation as the animation's list of shapes and motions is
   * different than it was previously.
   */
  void modifyComboBox();
}
