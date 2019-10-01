import cs3500.animator.model.animatormodel.ViewModel;
import cs3500.animator.view.IEditingView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JComboBox;

/**
 * Represents a MockView which implements IEditingView and used for testing purposes only.
 */
public class MockView implements IEditingView {

  Readable rd;
  Appendable ap;
  List<ActionListener> listeners;
  String command;

  /**
   * Constructs a new MockView using the given Readable and Appendable.
   *
   * @param rd the Readable that is the input
   * @param ap the Appendable that is the output location
   */
  public MockView(Readable rd, Appendable ap) {
    this.rd = rd;
    this.ap = ap;
    this.listeners = new ArrayList<>();

    Scanner scanner = new Scanner(rd);
    try {
      command = scanner.next();
      try {
        switch (command) {
          case "Play":
            this.appendPlay();
            break;
          case "Pause":
            this.appendPause();
            break;
          case "Restart":
            this.appendRestart();
            break;
          case "Quit":
            this.appendQuit();
            break;
          case "IncreaseSpeed":
            this.appendIncreaseSpeed();
            break;
          case "DecreaseSpeed":
            this.appendDecreaseSpeed();
            break;
          case "Looping":
            this.appendLooping();
            break;
          default:
            throw new IllegalArgumentException("Not a valid command");
        }
      } catch (IllegalArgumentException e) {
        try {
          this.display();
        } catch (IllegalArgumentException e2) {
          throw new IllegalArgumentException("could not display");
        }
      }
    } catch (IllegalArgumentException e3) {
      throw new IllegalArgumentException("scanner doesn't have a next");
    }
  }

  /**
   * Appends based on the looping command.
   */
  private void appendLooping() {
    this.append("Looping");
  }

  /**
   * Appends based on the decrease speed command.
   */
  private void appendDecreaseSpeed() {
    this.append("Decrease Speed");
  }

  /**
   * Appends based on the increase speed command.
   */
  private void appendIncreaseSpeed() {
    this.append("Increase Speed");
  }

  /**
   * Appends based on the quit command.
   */
  private void appendQuit() {
    this.append("Quit");
  }

  /**
   * Appends the given string to the appendable.
   *
   * @param s the String to append
   */
  private void append(String s) {
    try {
      ap.append(s);
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not append \"" + s + "\"");
    }
  }

  /**
   * Appends based on the restart command.
   */
  private void appendRestart() {
    this.append("Restart");
  }

  /**
   * Appends based on the pause command.
   */
  private void appendPause() {
    this.append("Pause");
  }

  /**
   * Appends based on the play command.
   */
  private void appendPlay() {
    this.append("Play");
  }

  /**
   * Sets the ActionListener for the view that implements this interface.
   *
   * @param listener The ActionListener for the IEditingView implementation
   */
  @Override
  public void setListener(ActionListener listener) {
    this.listeners.add(listener);
  }

  /**
   * Gets the text input in the text field.
   *
   * @return a String of the text input
   */
  @Override
  public String getTextInputString() {
    throw new UnsupportedOperationException("Unsupported Operation");
  }

  /**
   * Appends the given string to the text area.
   *
   * @param s the String to be appended to the text area
   */
  @Override
  public void appendTextArea(String s) {
    throw new UnsupportedOperationException("Unsupported Operation");
  }

  /**
   * Updates this View's model to the given model after it is mutated in the controller.
   *
   * @param model the new model
   */
  @Override
  public void updateModel(ViewModel model) {
    throw new UnsupportedOperationException("Unsupported Operation");
  }

  /**
   * Gets this view's combo box, which allows the controller to see which element was clicked.
   *
   * @return the JComboBox for this view
   */
  @Override
  public JComboBox getComboBox() {
    throw new UnsupportedOperationException("Unsupported Operation");
  }

  /**
   * Updates this combo box by removing the element at the given index and the given number after.
   *
   * @param selectionIndex the initial index to remove
   * @param numMotions how many motions to remove after the initial index
   */
  @Override
  public void updateComboBox(int selectionIndex, int numMotions) {
    throw new UnsupportedOperationException("Unsupported Operation");
  }

  /**
   * Removes all items from this view implementation's JComboBox and recreates it. Used after adding
   * a shape or a motion to the Animation as the animation's list of shapes and motions is different
   * than it was previously.
   */
  @Override
  public void modifyComboBox() {
    throw new UnsupportedOperationException("Unsupported Operation");
  }

  /**
   * Displays the animation.
   *
   * @throws IllegalArgumentException if the appendable
   */
  @Override
  public void display() throws IllegalArgumentException {
    for (ActionListener listener : listeners) {
      switch (command) {
        case "AddShape":
          listener.actionPerformed(new ActionEvent("", 1, "Add Shape"));
          break;
        case "AddMotion":
          listener.actionPerformed(new ActionEvent("", 1, "Add Motion"));
          break;
        case "RemoveShape":
          listener.actionPerformed(new ActionEvent("", 1, "Remove Shape"));
          break;
        case "RemoveMotion":
          listener.actionPerformed(new ActionEvent("", 1, "Remove Motion"));
          break;
        case "ModifyMotion":
          listener.actionPerformed(new ActionEvent("", 1, "Modify"));
          break;
        default:
          throw new IllegalArgumentException("Not a valid command");
      }
    }
  }

  /**
   * Gets the appendable for this view implementation.
   *
   * @return the appendable object for this implementation of IView
   * @throws UnsupportedOperationException if this view doesn't have an Appendable object
   */
  @Override
  public Appendable getAp() throws UnsupportedOperationException {
    return this.ap;
  }
}
