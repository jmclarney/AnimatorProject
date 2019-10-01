package cs3500.animator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cs3500.animator.model.animatormodel.ViewModel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.Timer;

/**
 * An implementation of the IView interface that is shown visually in an animation window to the
 * user.
 */
public class VisualView extends JFrame implements IView {

  private ViewModel model;
  private MyJPanel canvas;
  private Timer timer;

  /**
   * Constructs a new Visual view based on the given ViewModel and speed and it also uses a MyJPanel
   * to show the animation.
   *
   * @param model the ViewModel to be used
   * @param speed the speed to play this animation
   */
  public VisualView(ViewModel model, int speed) {
    super("Visual View");
    if (model == null || speed < 1) {
      throw new IllegalArgumentException("invalid input arguments");
    }
    this.model = model;
    this.canvas = new MyJPanel(this.model, 0);
    this.setCanvasInfo();

    int delay = 1000 / speed;
    this.timer = new Timer(delay, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        canvas.repaint();
      }
    });
  }

  /**
   * Sets all of the information for this canvas.
   */
  private void setCanvasInfo() {
    canvas.setPreferredSize(new Dimension((int) model.getWidth(), (int) model.getHeight()));
    this.setLayout(new BorderLayout());

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JScrollPane scrollPane = new JScrollPane(canvas);
    this.add(scrollPane, BorderLayout.SOUTH);
    scrollPane.setPreferredSize(new Dimension(500, 500));

    this.add(scrollPane);
    this.setVisible(true);
    this.pack();
  }

  /**
   * Displays the animation.
   */
  @Override
  public void display() {
    timer.start();
  }

  /**
   * Throws an UnsupportedOperationException for this VisualView because it
   * doesn't have an appendable.
   *
   * @return the appendable, the output
   * @throws UnsupportedOperationException because this view doesn't have an appendable
   */
  @Override
  public Appendable getAp()throws UnsupportedOperationException {
    throw new UnsupportedOperationException("No appendable");
  }
}
