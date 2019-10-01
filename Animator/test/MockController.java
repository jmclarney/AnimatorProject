import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Represents a Mock Controller used for testing purposes only.
 */
public class MockController implements ActionListener {

  private Appendable appendable;

  /**
   * Constructs a new MockController using the given MockView and Appendable.
   *
   * @param mockView the MockView for this controller
   * @param ap the Appendable on which to append text
   */
  public MockController(MockView mockView, Appendable ap) {
    mockView.setListener(this);
    this.appendable = ap;
  }

  /**
   * Invoked when an action occurs.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Remove Motion":
        this.append("Remove Motion");
        break;
      case "Remove Shape":
        this.append("Remove Shape");
        break;
      case "Add Shape":
        this.append("Add Shape");
        break;
      case "Add Motion":
        this.append("Add Motion");
        break;
      case "Modify":
        this.append("Modify");
        break;
      default:
        throw new IllegalArgumentException("Invalid ActionEvent");
    }
  }

  /**
   * Appends the given string to the Appendable.
   *
   * @param s the string to be appended
   */
  private void append(String s) {
    try {
      appendable.append(s);
    } catch (IOException e) {
      throw new IllegalArgumentException("could not append");
    }
  }
}
