package cs3500.animator.view;

import cs3500.animator.model.motion.IMotion;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import cs3500.animator.model.animatormodel.ViewModel;
import cs3500.animator.model.shapes.IShape;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.plaf.SliderUI;

/**
 * Represents an implementation of the IEditingView interface, which extends the IView interface
 * and inherits its functionality. This CompositeView is a visual view that also allows the user to
 * modify the animation at any given time as well as play/pause/restart/loop the animation or quit
 * out of the animation.
 */
public class CompositeView extends JFrame implements IEditingView {

  private ViewModel model;
  private MyJPanel canvas;
  private JPanel textInputArea;
  private JPanel buttonPanel;
  private JComboBox keyframeListComboBox;
  private JTextField textField;
  private JCheckBox loopingCheckBox;
  private Timer timer;
  private JButton removeShapeButton;
  private JButton removeMotionButton;
  private JButton addShapeButton;
  private JButton addMotionButton;
  private JButton modifyButton;
  private JButton bigRedButton;
  private int currentTime;
  private int delay;
  private boolean loopingOn;
  private int lastTimeOfAnimation;
  private JSlider scrubber;

  /**
   * Creates a new CompositeView animation using the given model and speed. This composite
   * view is a visual view that also allows the user to modify the animation and play/pause/
   * restart/loop the animation or quit out.
   *
   * @param model the given model is a read-only ViewModel so the user cannot mutate it at all
   *              on their own. They are only allowed to change it using the buttons on this view to
   *              add/remove shapes and motions and modify motions. This model houses the whole
   *              animation.
   * @param speed the speed of the animation
   */
  public CompositeView(ViewModel model, int speed) {
    super("Composite View");
    if (model == null || speed < 1) {
      throw new IllegalArgumentException("invalid input arguments");
    }
    this.model = model;
    this.currentTime = 0;
    this.loopingOn = false;
    lastTimeOfAnimation = -1;
    for (IShape shape : this.model.getAllShapes()) {
      lastTimeOfAnimation = Math.max(shape.getMoves().get(shape.getMoves().size() - 1)
          .getStartTime(), lastTimeOfAnimation);
    }

    this.canvas = new MyJPanel(this.model, currentTime);
    canvas.setPreferredSize(new Dimension((int) model.getWidth(), (int) model.getHeight()));
    JScrollPane scrollPane = new JScrollPane(canvas);
    scrollPane.setPreferredSize(new Dimension(750, 500));

    textField = new JTextField(20);
    this.textInputArea = new JPanel();
    this.setTextArea();

    this.setComboBox();

    scrubber = new JSlider();

    this.buttonPanel = new JPanel();
    this.setButtonPanel();

    this.delay = 1000 / speed;

    buttonPanel.setMaximumSize(new Dimension(1000, 100));

    this.setLayout(new BorderLayout());
    this.add(scrollPane, BorderLayout.CENTER);
    this.add(textInputArea, BorderLayout.EAST);
    this.add(buttonPanel, BorderLayout.SOUTH);
    keyframeListComboBox.setPreferredSize(new Dimension(canvas.getWidth(), 50));
    this.add(keyframeListComboBox, BorderLayout.NORTH);

    this.timer = new Timer(this.delay, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (loopingOn && canvas.getTimePassed() >= lastTimeOfAnimation) {
          canvas.setTimePassed(0);
          currentTime = 0;
          scrubber.setValue(0);
//          System.out.println("color model?" + scrollPaneubber.getBackground());
//          scrubber.setForeground(Color.RED);
        } else {
          canvas.repaint();
          scrubber.setValue(canvas.getTimePassed());
        }
      }
    });
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
    this.pack();
    timer.start();
  }


  /**
   * Sets this CompositeView's comboBox based on the model's shapes and motions.
   */
  private void setComboBox() {
    keyframeListComboBox = new JComboBox();

    for (IShape shape : this.model.getAllShapes()) {
      keyframeListComboBox.addItem(shape.getName() + ":");
      for (IMotion motion : shape.getMoves()) {
        keyframeListComboBox.addItem("shapeID: " + shape.getName() + " start-time: "
            +  motion.getStartTime() + " x: " + motion.getX()
            + " y: " + motion.getY() + " width: " + motion.getWidth() + " height: "
            + motion.getHeight() + " color: (" + motion.getColor().getRed() + ","
            + motion.getColor().getGreen() + "," + motion.getColor().getBlue() + ")");
      }
    }
  }

  /**
   * Sets this CompositeView's text area. It adds a textfield, scrollpane, buttons to remove a
   * shape, remove a motion, add a shape, add a motion, and to modify a button.
   */
  private void setTextArea() {
    textInputArea.setLayout(new BoxLayout(textInputArea, BoxLayout.Y_AXIS));
    JScrollPane scrollPane = new JScrollPane(textField);
    scrollPane.setBorder(BorderFactory.createTitledBorder("Scrollable text area"));
    removeShapeButton = new JButton("Remove Shape");
    removeMotionButton = new JButton("Remove Motion");
    addShapeButton = new JButton("Add Shape");
    addMotionButton = new JButton("Add Motion");
    modifyButton = new JButton("Modify Motion");
    bigRedButton = new JButton("Remove Everything");
    textField.setMaximumSize(new Dimension(1000, 100));
    textInputArea.add(textField);
    textInputArea.add(removeShapeButton);
    textInputArea.add(removeMotionButton);
    textInputArea.add(addShapeButton);
    textInputArea.add(addMotionButton);
    textInputArea.add(modifyButton);
    textInputArea.add(bigRedButton);

    JLabel instructions = new JLabel("Instructions:");
    JLabel removeShapeInstructionLabel = new JLabel("<html> - To remove a shape, select a "
        + "shape "
        + "from the list of shapes <br/> and motions and then press the 'remove shape' button "
        + "</html>");
    JLabel removeMotionInstructionLabel = new JLabel("<html> - To remove a motion, select "
        + "a motion "
        + "from the list of shapes <br/> and motions and then press the 'remove motion' button "
        + "</html>");
    JLabel addShapeInstructionLabel = new JLabel("<html> - To add a shape, type in the unique"
        + " shapeID and then the shape <br/> (either \"rectangle\" or \"ellipse\") then press "
        + "'add shape'. Your shape <br/> should now be in the list of shapes and motions </html>");
    JLabel addMotionInstructionLabel = new JLabel("<html> - To add a motion to a shape's "
        + "motions, type in the unique shapeID <br/> followed by the start time of the motion, then"
        + " the x- and y-positions, <br/> then the width and height of the motion, then the color"
        + "in R G B format <br/> (with values between 0 and 255 for each) then press the 'add "
        + "motion' <br/> button. One motion is one keyframe");
    JLabel modifyMotionInstructionLabel = new JLabel("<html> - To modify one of a shape's "
        + "motions, type in the unique shapeID <br/> followed by the start time of the motion "
        + "to be changed, then the x- and y-positions, <br/> then the width and height of "
        + "the motion, then the color in R G B format <br/> (with values between 0 and 255 "
        + "for each) then press the 'modify motion' button");

    textInputArea.add(instructions);
    textInputArea.add(removeShapeInstructionLabel);
    textInputArea.add(removeMotionInstructionLabel);
    textInputArea.add(addShapeInstructionLabel);
    textInputArea.add(addMotionInstructionLabel);
    textInputArea.add(modifyMotionInstructionLabel);

    this.pack();

  }

  /**
   * Sets this CompositeView's buttonPanel with buttons to play, pause, restart, speed up, slow down
   * the animation and to quit out.
   */
  private void setButtonPanel() {
    buttonPanel.setLayout(new FlowLayout());

    scrubber.setMinimum(0);
    scrubber.setMaximum(lastTimeOfAnimation);
    scrubber.setValue(scrubber.getMinimum());

    scrubber.addChangeListener((ChangeEvent e) -> {
      canvas.setTimePassed(scrubber.getValue());
      this.currentTime = scrubber.getValue();
      this.repaint();
    });
    buttonPanel.add(scrubber);

    // Adds the play button to the button panel.
    JButton playButton = new JButton("Play");
    playButton.addActionListener((ActionEvent event) -> {
      if (this.timer.isRunning()) {
        return;
      }
      this.timer.start();
    });
    buttonPanel.add(playButton);

    // Adds the pause button to the button panel.
    JButton pauseButton = new JButton("Pause");
    pauseButton.addActionListener((ActionEvent event) -> {
      timer.stop();
    });
    buttonPanel.add(pauseButton);

    // Adds the quit button to the button panel.
    JButton quitButton = new JButton("Quit");
    quitButton.addActionListener((ActionEvent event) -> {
      System.exit(0);
    });
    buttonPanel.add(quitButton);

    // Adds the restart button to the button panel.
    JButton restartButton = new JButton("Restart");
    restartButton.addActionListener((ActionEvent event) -> {
      this.timer.stop();
      canvas.setTimePassed(0);
      currentTime = 0;
      int sameDelay = this.timer.getDelay();
      this.timer = new Timer(sameDelay, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          if (loopingOn && canvas.getTimePassed() >= lastTimeOfAnimation) {
            canvas.setTimePassed(0);
            currentTime = 0;
          } else {
            canvas.repaint();
          }
        }
      });
      timer.start();
    });
    buttonPanel.add(restartButton);


    //Adds the decrease speed button to the button panel.
    JButton decreaseSpeedButton = new JButton("<<");
    decreaseSpeedButton.addActionListener((ActionEvent event) -> {
      if (this.delay < 10) {
        this.timer.setDelay(10);
        delay = 10;
      } else if (this.delay <= 25) {
        this.timer.setDelay(this.delay + 5);
        this.delay += 5;
      } else if (this.delay <= 50) {
        this.timer.setDelay(this.delay + 10);
        this.delay += 10;
      } else if (this.delay <= 200) {
        this.timer.setDelay(this.delay + 50);
        this.delay += 50;
      } else if (this.delay <= 500) {
        this.timer.setDelay(this.delay + 100);
        this.delay += 100;
      } else {
        this.timer.setDelay(this.delay + 250);
        this.delay += 250;
      }
    });
    buttonPanel.add(decreaseSpeedButton);

    // Adds the increase speed button to the button panel.
    JButton increaseSpeedButton = new JButton(">>");
    increaseSpeedButton.addActionListener((ActionEvent event) -> {
      if (delay < 10) {
        this.timer.setDelay(1);
        this.delay = 1;
      } else if (this.delay <= 25) {
        this.timer.setDelay(this.delay - 5);
        this.delay -= 5;
      } else if (this.delay <= 50) {
        this.timer.setDelay(this.delay - 10);
        this.delay -= 10;
      } else if (this.delay <= 200) {
        this.timer.setDelay(this.delay - 50);
        this.delay -= 50;
      } else if (this.delay <= 500) {
        this.timer.setDelay(this.delay - 100);
        this.delay -= 100;
      } else {
        this.timer.setDelay(this.delay - 250);
        this.delay -= 250;
      }
    });
    buttonPanel.add(increaseSpeedButton);

    // Adds the looping checkbox to the button panel.
    loopingCheckBox = new JCheckBox("Loop");
    loopingCheckBox.addActionListener((ActionEvent event) -> {
      if (!loopingOn) {
        // turn on looping
        loopingCheckBox.setSelected(true);
        loopingOn = true;
      } else {
        // turn off looping
        loopingOn = false;
        loopingCheckBox.setSelected(false);
      }
    });
    buttonPanel.add(loopingCheckBox);
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
  public Appendable getAp() {
    throw new UnsupportedOperationException("Composite View doesn't have an appendable");
  }

  /**
   * Sets the ActionListener for the view that implements this interface.
   *
   * @param listener the ActionListener for this composite view
   */
  @Override
  public void setListener(ActionListener listener) {
    removeShapeButton.setActionCommand("Remove Shape");
    removeMotionButton.setActionCommand("Remove Motion");
    addShapeButton.setActionCommand("Add Shape");
    addMotionButton.setActionCommand("Add Motion");
    modifyButton.setActionCommand("Modify");
    bigRedButton.setActionCommand("Remove Everything");
    this.removeShapeButton.addActionListener(listener);
    this.removeMotionButton.addActionListener(listener);
    this.addShapeButton.addActionListener(listener);
    this.addMotionButton.addActionListener(listener);
    this.modifyButton.addActionListener(listener);
    this.bigRedButton.addActionListener(listener);
  }

  /**
   * Gets the text input in the text field.
   *
   * @return a String of the text input
   */
  @Override
  public String getTextInputString() {
    return this.textField.getText();
  }

  /**
   * Appends the given string to the text area.
   *
   * @param s the String to be appended to the text area
   */
  @Override
  public void appendTextArea(String s) {
    textField.setText(s);
    this.repaint();
  }

  /**
   * Updates this View's model to the given model after it is mutated in the controller.
   *
   * @param m the new model
   */
  @Override
  public void updateModel(ViewModel m) {
    this.model = m;
    canvas.updateModel(m);
  }

  /**
   * Gets this view's combo box, which allows the controller to see which element was
   * clicked.
   *
   * @return the JComboBox for this view
   */
  public JComboBox getComboBox() {
    return this.keyframeListComboBox;
  }

  /**
   * Updates this combo box by removing the element at the given index and the given number after.
   *
   * @param selectionIndex the initial index to remove
   * @param numMotions how many motions to remove after the initial index
   */
  @Override
  public void updateComboBox(int selectionIndex, int numMotions) {
    while (numMotions + 1 > 0) {
      keyframeListComboBox.removeItemAt(selectionIndex);
      numMotions--;
    }
  }

  /**
   * Removes all items from this view implementation's JComboBox and recreates it. Used after
   * adding a shape or a motion to the Animation as the animation's list of shapes and motions is
   * different than it was previously.
   */
  @Override
  public void modifyComboBox() {
    this.keyframeListComboBox.removeAllItems();
    // add all the items back - including modified motion
    for (IShape shape : this.model.getAllShapes()) {
      keyframeListComboBox.addItem(shape.getName() + ":");
      for (IMotion motion : shape.getMoves()) {
        keyframeListComboBox.addItem("shapeID: " + shape.getName() + " start-time: "
            + motion.getStartTime() + " x: " + motion.getX()
            + " y: " + motion.getY() + " width: " + motion.getWidth() + " height: "
            + motion.getHeight() + " color: (" + motion.getColor().getRed() + ","
            + motion.getColor().getGreen() + "," + motion.getColor().getBlue() + ")");
      }
    }
  }
}
