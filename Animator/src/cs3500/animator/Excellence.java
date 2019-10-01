package cs3500.animator;

import cs3500.animator.controller.ControllerImpl;
import cs3500.animator.controller.IController;
import cs3500.animator.model.animatormodel.AnimatorModelImpl;
import cs3500.animator.model.animatormodel.IAnimatorModel;
import cs3500.animator.model.animatormodel.ViewModel;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.CompositeView;
import cs3500.animator.view.IEditingView;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.TextualView;
import cs3500.animator.view.VisualView;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The class that contains the main method for the Animator.
 */
public final class Excellence {

  /**
   * Represents the entry point into this program. It takes inputs as command line arguments.
   *
   * @param args the arguments to be evaluated
   */
  public static void main(String[] args) {
    Readable rd;
    IAnimatorModel model;
    String animationFile = null;
    String viewType = null;
    String outputLocation = null;
    int speed = -1;

    for (int i = 0; i < args.length; i += 2) {
      String input = args[i];
      switch (input) {
        // gets the size of the board, if given
        case "-in":
          animationFile = args[i + 1];
          try {
            rd = new BufferedReader(new FileReader(animationFile));
          } catch (FileNotFoundException e) {
            throw new IllegalStateException("File not found");
          }
          break;
        // gets the location for the empty space for the board, if there is one
        case "-view":
          viewType = args[i + 1];
          break;
        case "-out":
          outputLocation = args[i + 1];
          break;
        case "-speed":
          if (Integer.valueOf(args[i + 1]) <= 0) {
            throw new IllegalArgumentException("Speed must be positive number");
          }
          speed = Integer.valueOf(args[i + 1]);
          break;
        default:
          break;
      }
    }

    if (viewType == null || animationFile == null) {
      throw new IllegalArgumentException("neither viewType nor animationFile can be null");
    }
    if (outputLocation != null && speed == -1) {
      speed = 1;
    } else if (outputLocation == null && speed > 0) {
      outputLocation = "System.out";
    } else if (outputLocation == null && speed < 0) {
      outputLocation = "System.out";
      speed = 1;
    } else if (outputLocation != null && speed > 0) {
      // both are valid inputs
    }

    switch (viewType) {
      case "text":
        createTextView(animationFile, outputLocation);
        break;
      case "svg":
        createSVGView(animationFile, outputLocation, speed);
        break;
      case "visual":
        createVisualView(animationFile, speed);
        break;
      case "composite":
        createCompositeView(animationFile, speed);
        break;
      default:
        throw new IllegalArgumentException("invalid view type");
    }
  }

  /**
   * Creates a composite view based on the given animation file and outputs it to the correct output
   * location, which is visual view but with editing features.
   *
   * @param animationFile the animation file to be read to create the animator model.
   * @param speed the speed of the animation.
   */
  private static void createCompositeView(String animationFile, int speed) {
    IAnimatorModel animation = readFileAndCreateModel(animationFile);
    IEditingView view = new CompositeView(new ViewModel(animation), speed);
    IController controller = new ControllerImpl(view, animation);
    controller.playAnimation();
  }

  /**
   * Creates a visual view based on the given animation file and outputs it to the correct output
   * location.
   *
   * @param animationFile the animation file to be read to create the animator model.
   * @param speed the speed of the animation.
   */
  private static void createVisualView(String animationFile, int speed) {
    IAnimatorModel animation = readFileAndCreateModel(animationFile);
    IView view = new VisualView(new ViewModel(animation), speed);
    view.display();
  }

  /**
   * Creates an SVG view based on the given animation file and outputs it to the correct output
   * location.
   *
   * @param animationFile the animation file to be read to create the animator model.
   * @param outputLocation where to output the animation, either a file or system.out
   */
  private static void createSVGView(String animationFile, String outputLocation, int speed) {
    IAnimatorModel animation = readFileAndCreateModel(animationFile);
    BufferedWriter writer;
    try {
      if (outputLocation.equals("System.out")) {
        Appendable ap = new StringBuilder();
        SVGView v = new SVGView(new ViewModel(animation), ap, speed);
        v.display();
        System.out.println(v.getAp());
      } else {

        File f = new File(outputLocation);
        f.createNewFile();
        writer = new BufferedWriter(new FileWriter(f, true));
        SVGView v = new SVGView(new ViewModel(animation), writer, speed);
        v.display();
        writer.flush();

      }
    } catch (IOException e) {
      throw new IllegalStateException("createSVGView error");
    }

  }

  /**
   * Creates a text view based on the given animation file and outputs it to the correct output
   * location.
   *
   * @param animationFile the animation file to be read to create the animator model.
   * @param outputLocation where to output the animation, either a file or system.out
   */
  private static void createTextView(String animationFile, String outputLocation) {
    IAnimatorModel animation = readFileAndCreateModel(animationFile);
    if (outputLocation.equals("System.out")) {
      IView view = new TextualView(new ViewModel(animation), new StringBuilder());
      view.display();
      System.out.print(view.getAp().toString());
    } else {
      try {
        IView view = new TextualView(new ViewModel(animation),
            new FileWriter(new File(outputLocation)));
        view.display();
      } catch (IOException e) {
        throw new IllegalStateException("could not create bew text file output");
      }
    }
  }

  /**
   * Reads the given file and creates the model to be used based on that.
   *
   * @param animationFile the Animation file to be read
   * @return the ViewModel to be used.
   */
  private static IAnimatorModel readFileAndCreateModel(String animationFile) {
    Readable in;
    try {
      in = new BufferedReader(new FileReader(animationFile));
      AnimatorModelImpl.Builder builder = new AnimatorModelImpl.Builder();
      return AnimationReader.parseFile(in, builder);
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not read file and create model");
    }
  }
}
