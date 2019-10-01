package cs3500.animator.controller;

import cs3500.animator.model.animatormodel.IAnimatorModel;
import cs3500.animator.model.animatormodel.ViewModel;
import cs3500.animator.model.motion.IMotion;
import cs3500.animator.model.motion.MotionImpl;
import cs3500.animator.model.shapes.IShape;
import cs3500.animator.view.IEditingView;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Scanner;

/**
 * A controller that implements IController that creates a new CompositeView, which is a visual view
 * that has editing features.
 */
public class ControllerImpl implements IController, ActionListener {

  private IEditingView view;
  private IAnimatorModel model;

  /**
   * Constructs a new ControllerImpl given an IAnimatorModel (which is the animation) and an
   * IEditingView, which extends IView and is implemented by CompositeView, so this creates a new
   * composite view.
   *
   * @param view the IEditingView to be used
   * @param model the IAnimatorModel which is the animation to be used
   */
  public ControllerImpl(IEditingView view, IAnimatorModel model) {
    this.view = view;
    this.model = model;
    this.view.setListener(this);
  }

  /**
   * Invoked when an action occurs. This controller is the ActionListener for the CompositeView and
   * deals with the actionPerformed that is invoked when a button is pressed to mutate the model.
   * After the model is changed, it is returned to the view (as a ViewModel, which is read-only) to
   * be updated in the view.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    String input;
    String shapeID;
    String shapeType;
    int startTime;
    double x;
    double y;
    double width;
    double height;
    int colorR;
    int colorG;
    int colorB;
    input = view.getTextInputString();
    Scanner scanner = new Scanner(input);
    int selectionIndex;

    switch (e.getActionCommand()) {
      case "Remove Everything":
        while (model.getAllShapes().size() > 0) {
          model.removeShape(this.model.getAllShapes().get(0).getName());
        }
        view.updateModel(new ViewModel(model));
        view.modifyComboBox();
        break;
      case "Remove Motion":
        selectionIndex = view.getComboBox().getSelectedIndex();

        scanner = new Scanner(view.getComboBox().getItemAt(selectionIndex).toString());
        String weDontCare1 = scanner.next();
        if (!weDontCare1.equals("shapeID:")) {
          view.appendTextArea("\n Did not select a motion.");
          return;
        }
        shapeID = scanner.next();
        String weDontCare2 = scanner.next();
        int motionTime = Integer.valueOf(scanner.next());

        try {
          view.updateComboBox(view.getComboBox().getSelectedIndex(), 0);
          model.removeMotion(motionTime, shapeID);
          view.updateModel(new ViewModel(model));

        } catch (IllegalArgumentException e2) {
          view.appendTextArea("\n Incorrect inputs. Shape with given ID already exists or "
              + "not a valid shape type");
        }
        break;

      case "Remove Shape":
        selectionIndex = view.getComboBox().getSelectedIndex();

        scanner = new Scanner(view.getComboBox().getItemAt(selectionIndex).toString());
        shapeID = scanner.next();
        shapeID = shapeID.substring(0, shapeID.length() - 1);

        if (shapeID.contains("shapeID")) {
          view.appendTextArea("\n Did not select a shape.");
        } else {
          try {
            int numMotions = model.getNumberOfMotions(shapeID);
            view.updateComboBox(view.getComboBox().getSelectedIndex(), numMotions);
            model.removeShape(shapeID);
            view.updateModel(new ViewModel(model));
          } catch (IllegalArgumentException e2) {
            view.appendTextArea("\n Incorrect inputs. Shape with given ID already exists or "
                + "not a valid shape type");
          }
        }
        break;

      case "Add Shape":
        shapeID = "";
        shapeType = "";

        while (scanner.hasNext()) {
          if (shapeID.equals("")) {
            shapeID = scanner.next();
          } else if (shapeType.equals("")) {
            shapeType = scanner.next();
          } else {
            view.appendTextArea("\n too many inputs. Please re-enter");
            return;
          }
        }

        if (!shapeID.equals("") && !shapeType.equals("")) {
          try {
            model.addShape(shapeID, shapeType);
            view.updateModel(new ViewModel(model));
            view.modifyComboBox();
          } catch (IllegalArgumentException e3) {
            view.appendTextArea("\n Incorrect inputs. Shape with given ID already exists or "
                + "not a valid shape type");
          }
        } else {
          view.appendTextArea("Incorrect inputs. Either shape already exists "
              + "or invalid motion");
        }
        break;

      case "Add Motion":
        shapeID = "";
        startTime = -1;
        x = -10000;
        y = -10000;
        width = -10000;
        height = -10000;
        colorR = -1;
        colorG = -1;
        colorB = -1;

        while (scanner.hasNext()) {
          if (shapeID.equals("")) {
            shapeID = scanner.next();
          } else if (startTime == -1) {
            startTime = Integer.valueOf(scanner.next());
          } else if (x == -10000) {
            x = Double.valueOf(scanner.next());
          } else if (y == -10000) {
            y = Double.valueOf(scanner.next());
          } else if (width == -10000) {
            width = Double.valueOf(scanner.next());
          } else if (height == -10000) {
            height = Double.valueOf(scanner.next());
          } else if (colorR == -1) {
            colorR = Integer.valueOf(scanner.next());
          } else if (colorG == -1) {
            colorG = Integer.valueOf(scanner.next());
          } else if (colorB == -1) {
            colorB = Integer.valueOf(scanner.next());
          } else {
            view.appendTextArea("\n too many inputs. Please re-enter");
            return;
          }
        }

        if (!shapeID.equals("") && startTime != -1 && x != -10000 && y != -10000
            && width != -10000 && height != -10000 && colorR != -1 && colorG != -1
            && colorB != -1) {
          IMotion motionToAdd = new MotionImpl(startTime, x, y, width, height,
              new Color(colorR, colorG, colorB));
          try {
            model.addMotion(motionToAdd, shapeID);
            view.updateModel(new ViewModel(model));
            view.modifyComboBox();
          } catch (IllegalArgumentException e4) {
            view.appendTextArea("Incorrect inputs. Either shape already exists "
                + "or invalid motion");
          }
        } else {
          view.appendTextArea("Incorrect inputs. Either shape already exists "
              + "or invalid motion");
        }
        break;

      case "Modify":
        shapeID = "";
        startTime = -1;
        x = -10000;
        y = -10000;
        width = -10000;
        height = -10000;
        colorR = -1;
        colorG = -1;
        colorB = -1;

        while (scanner.hasNext()) {
          if (shapeID.equals("")) {
            shapeID = scanner.next();
          } else if (startTime == -1) {
            startTime = Integer.valueOf(scanner.next());
          } else if (x == -10000) {
            x = Double.valueOf(scanner.next());
          } else if (y == -10000) {
            y = Double.valueOf(scanner.next());
          } else if (width == -10000) {
            width = Double.valueOf(scanner.next());
          } else if (height == -10000) {
            height = Double.valueOf(scanner.next());
          } else if (colorR == -1) {
            colorR = Integer.valueOf(scanner.next());
          } else if (colorG == -1) {
            colorG = Integer.valueOf(scanner.next());
          } else if (colorB == -1) {
            colorB = Integer.valueOf(scanner.next());
          } else {
            view.appendTextArea("\n too many inputs. Please re-enter");
            return;
          }
        }

        if (!shapeID.equals("") && startTime != -1 && x != -10000 && y != -10000
            && width != -10000 && height != -10000 && colorR != -1 && colorG != -1
            && colorB != -1) {
          IMotion motionToAdd = new MotionImpl(startTime, x, y, width, height,
              new Color(colorR, colorG, colorB));
          try {
            model.modifyMotion(motionToAdd, shapeID);
            view.updateModel(new ViewModel(model));
            view.modifyComboBox();
          } catch (IllegalArgumentException e4) {
            view.appendTextArea("\n Incorrect inputs. Either shape already exists "
                + "or invalid motion");
          }
        } else {
          view.appendTextArea("Incorrect inputs. Either shape already exists "
              + "or invalid motion");
        }
        break;

      default:
        view.appendTextArea("Illegal Command");
    }

  }

  /**
   * Plays this controller's animation using the given view, which is a composite view and has
   * features that allows the user to edit and modify the animation to their liking.
   */
  @Override
  public void playAnimation() {
    view.display();
  }
}
