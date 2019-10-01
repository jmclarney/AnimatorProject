import static org.junit.Assert.assertEquals;

import cs3500.animator.model.animatormodel.AnimatorModelImpl;
import cs3500.animator.model.animatormodel.IAnimatorModel;
import cs3500.animator.model.animatormodel.ViewModel;
import cs3500.animator.model.motion.MotionImpl;
import cs3500.animator.model.shapes.IShape;
import cs3500.animator.model.shapes.MyEllipse;
import cs3500.animator.model.shapes.MyRectangle;
import cs3500.animator.view.TextualView;
import java.awt.Color;

import org.junit.Test;

/**
 * These are tests for the TextualView.
 */
public class TextViewTests {

  FailingAppendable failingAppendable;
  Appendable stringBuffer;
  ViewModel viewModel;

  TextualView textualView;
  IShape rect1;
  IShape rect2;
  IShape rect3;

  IShape ellipse1;
  IShape ellipse2;
  IShape ellipse3;
  IShape ellipse4;

  IAnimatorModel emptyModel;
  IAnimatorModel model1;
  IAnimatorModel model2;
  IAnimatorModel model3;
  IAnimatorModel model4;
  IAnimatorModel model5;
  IAnimatorModel model6;
  IAnimatorModel model7;

  /**
   * Initializes the data.
   */
  public void initData() {
    stringBuffer = new StringBuffer();
    failingAppendable = new FailingAppendable();

    // Initializing empty model
    emptyModel = new AnimatorModelImpl();
    // Initializing the model and adding shapes
    model1 = new AnimatorModelImpl();
    model1.addShape("R3", "rectangle");
    model1.addShape("O1", "ellipse");

    // Initializing a second model
    model2 = new AnimatorModelImpl();
    model2.addShape("R3", "rectangle");

    model3 = new AnimatorModelImpl();
    model3.addShape("R1", "rectangle");
    model3.addShape("O1", "ellipse");
    model3.addShape("O2", "ellipse");

    model4 = new AnimatorModelImpl();
    model5 = new AnimatorModelImpl();
    model6 = new AnimatorModelImpl();
    model7 = new AnimatorModelImpl();

    ellipse1 = new MyEllipse("O1");
    ellipse2 = new MyEllipse("O2");
    ellipse3 = new MyEllipse("O3");
    ellipse4 = new MyEllipse("O4");
    rect1 = new MyRectangle("R1");
    rect2 = new MyRectangle("R2");
    rect3 = new MyRectangle("R3");
    rect2.addMove(new MotionImpl(4, 50, 50, 40, 40,
        Color.GRAY));
    rect3.addMove(new MotionImpl(1, 200, 200, 50, 100,
        new Color(255, 0, 0)));
    rect3.addMove(new MotionImpl(10, 200, 200, 50, 100,
        new Color(255, 0, 0)));
    rect3.addMove(new MotionImpl(50, 300, 300, 50, 100,
        new Color(255, 0, 0)));
    rect3.addMove(new MotionImpl(51, 300, 300, 50, 100,
        new Color(255, 0, 0)));
    rect3.addMove(new MotionImpl(70, 300, 300, 25, 100,
        new Color(255, 0, 0)));
    ellipse1.addMove(new MotionImpl(0, 50, 50, 20, 20,
        Color.GRAY));
    ellipse1.addMove(new MotionImpl(10, 0, 100, 20, 20,
        Color.GRAY));
    ellipse1.addMove(new MotionImpl(20, 0, 100, 50, 40,
        Color.GRAY));
    ellipse1.addMove(new MotionImpl(30, 0, 100, 50, 40,
        Color.BLACK));
    ellipse1.addMove(new MotionImpl(40, 75, 25, 50, 40,
        Color.BLACK));
    ellipse3 = new MyEllipse("E3");
    ellipse3.addMove(new MotionImpl(0, 75, 25, 50, 40,
        Color.GREEN));
    ellipse3.addMove(new MotionImpl(10, 75, 25, 50, 40,
        Color.BLUE));
    ellipse3.addMove(new MotionImpl(12, 75, 25, 50, 40,
        Color.BLUE));
    ellipse3.addMove(new MotionImpl(15, 50, 25, 50, 40,
        Color.BLUE));
    ellipse3.addMove(new MotionImpl(16, 50, 30, 50, 40,
        Color.BLUE));
    ellipse3.addMove(new MotionImpl(19, 20, 30, 50, 40,
        Color.BLUE));
    ellipse3.addMove(new MotionImpl(22, 20, 40, 50, 40,
        Color.BLUE));
    ellipse3.addMove(new MotionImpl(24, 30, 30, 50, 40,
        Color.BLUE));
    ellipse3.addMove(new MotionImpl(27, 40, 40, 50, 40,
        Color.BLUE));
    ellipse3.addMove(new MotionImpl(28, 30, 30, 50, 40,
        Color.BLUE));
    ellipse3.addMove(new MotionImpl(29, 20, 40, 50, 40,
        Color.BLUE));
    ellipse3.addMove(new MotionImpl(30, 20, 40, 50, 40,
        Color.BLUE));
    ellipse3.addMove(new MotionImpl(31, 20, 40, 40, 40,
        Color.BLUE));
    ellipse3.addMove(new MotionImpl(32, 20, 40, 40, 50,
        Color.BLUE));
    ellipse3.addMove(new MotionImpl(33, 20, 40, 60, 60,
        Color.BLUE));
    ellipse3.addMove(new MotionImpl(34, 20, 40, 50, 50,
        Color.BLUE));
    ellipse3.addMove(new MotionImpl(35, 20, 40, 60, 40,
        Color.BLUE));
    ellipse3.addMove(new MotionImpl(36, 20, 40, 50, 50,
        Color.BLUE));
    ellipse3.addMove(new MotionImpl(37, 20, 40, 50, 50,
        Color.RED));

    model4.addShape("R1", "rectangle");
    model4.addMotion(new MotionImpl(1, 200, 200, 50, 100,
        new Color(255, 0, 0)), "R3");
    model4.addMotion(new MotionImpl(10, 200, 200, 50, 100,
        new Color(255, 0, 0)), "R3");
    model4.addMotion(new MotionImpl(50, 300, 300, 50, 100,
        new Color(255, 0, 0)), "R3");
    model4.addMotion(new MotionImpl(51, 300, 300, 50, 100,
        new Color(255, 0, 0)), "R3");
    model4.addMotion(new MotionImpl(70, 300, 300, 25, 100,
        new Color(255, 0, 0)), "R3");
    model4.addShape("O1", "ellipse");
    model4.addMotion(new MotionImpl(0, 50, 50, 20, 20,
        Color.GRAY), "O1");
    model4.addMotion(new MotionImpl(10, 0, 100, 20, 20,
        Color.GRAY), "O1");
    model4.addMotion(new MotionImpl(20, 0, 100, 50, 40,
        Color.GRAY), "O1");
    model4.addMotion(new MotionImpl(30, 0, 100, 50, 40,
        Color.BLACK), "O1");
    model4.addMotion(new MotionImpl(40, 75, 25, 50, 40,
        Color.BLACK), "O1");

    model5.addShape("R3", "rectangle");
    model5.addMotion(new MotionImpl(1, 200, 200, 50, 100,
        new Color(255, 0, 0)), "R3");
    model5.addMotion(new MotionImpl(10, 200, 200, 50, 100,
        new Color(255, 0, 0)), "R3");
    model5.addMotion(new MotionImpl(50, 300, 300, 50, 100,
        new Color(255, 0, 0)), "R3");
    model5.addMotion(new MotionImpl(51, 300, 300, 50, 100,
        new Color(255, 0, 0)), "R3");
    model5.addMotion(new MotionImpl(70, 300, 300, 25, 100,
        new Color(255, 0, 0)), "R3");

    model6.addShape("O1", "ellipse");
    model6.addMotion(new MotionImpl(0, 50, 50, 20, 20,
        Color.GRAY), "O1");
    model6.addMotion(new MotionImpl(10, 0, 100, 20, 20,
        Color.GRAY), "O1");
    model6.addMotion(new MotionImpl(20, 0, 100, 50, 40,
        Color.GRAY), "O1");
    model6.addMotion(new MotionImpl(30, 0, 100, 50, 40,
        Color.BLACK), "O1");
    model6.addMotion(new MotionImpl(40, 75, 25, 50, 40,
        Color.BLACK), "O1");
    model6.addShape("O2", "ellipse");

    model7.addShape("R3", "rectangle");
    model7.addMotion(new MotionImpl(1, 200, 200, 50, 100,
        new Color(255, 0, 0)), "R3");
    model7.addMotion(new MotionImpl(10, 200, 200, 50, 100,
        new Color(255, 0, 0)), "R3");
    model7.addMotion(new MotionImpl(50, 300, 300, 50, 100,
        new Color(255, 0, 0)), "R3");
    model7.addMotion(new MotionImpl(51, 300, 300, 50, 100,
        new Color(255, 0, 0)), "R3");
    model7.addMotion(new MotionImpl(70, 300, 300, 25, 100,
        new Color(255, 0, 0)), "R3");
    model7.addShape("O1", "ellipse");
    model7.addMotion(new MotionImpl(0, 50, 50, 20, 20,
        Color.GRAY), "O1");
    model7.addMotion(new MotionImpl(10, 0, 100, 20, 20,
        Color.GRAY), "O1");
    model7.addMotion(new MotionImpl(20, 0, 100, 50, 40,
        Color.GRAY), "O1");
    model7.addMotion(new MotionImpl(30, 0, 100, 50, 40,
        Color.BLACK), "O1");
    model7.addMotion(new MotionImpl(40, 75, 25, 50, 40,
        Color.BLACK), "O1");

  }

  // tests printing a shape of a model and its motions
  @Test
  public void testGetDescription() {
    initData();
    emptyModel.addShape("04", "ellipse");
    viewModel = new ViewModel(emptyModel);
    textualView = new TextualView(viewModel, stringBuffer);
    textualView.display();

    assertEquals(""
            + "canvas 0.0 0.0 1000.0 1000.0\n"
            + "shape 04 ellipse\n",
        stringBuffer.toString());
  }

  // tests printing a shape with no motions
  @Test
  public void testGetDescription1() {
    initData();
    viewModel = new ViewModel(model2);
    textualView = new TextualView(viewModel, stringBuffer);
    textualView.display();

    assertEquals(""
            + "canvas 0.0 0.0 1000.0 1000.0\n"
            + "shape R3 rect\n",
        stringBuffer.toString());
  }

  // tests getting the description of a model with two shapes
  @Test
  public void testGetDescription2() {
    initData();
    emptyModel.addShape("O3", "ellipse");
    emptyModel.addMotion(new MotionImpl(0, 75, 25, 50, 40,
        Color.GREEN), "O3");
    emptyModel.addMotion(new MotionImpl(10, 75, 25, 50, 40,
        Color.BLUE), "O3");
    emptyModel.addMotion(new MotionImpl(12, 75, 25, 50, 40,
        Color.BLUE), "O3");
    emptyModel.addMotion(new MotionImpl(15, 50, 25, 50, 40,
        Color.BLUE), "O3");
    emptyModel.addMotion(new MotionImpl(16, 50, 30, 50, 40,
        Color.BLUE), "O3");
    emptyModel.addMotion(new MotionImpl(19, 20, 30, 50, 40,
        Color.BLUE), "O3");
    emptyModel.addMotion(new MotionImpl(22, 20, 40, 50, 40,
        Color.BLUE), "O3");
    emptyModel.addMotion(new MotionImpl(24, 30, 30, 50, 40,
        Color.BLUE), "O3");
    emptyModel.addMotion(new MotionImpl(27, 40, 40, 50, 40,
        Color.BLUE), "O3");
    emptyModel.addMotion(new MotionImpl(28, 30, 30, 50, 40,
        Color.BLUE), "O3");
    emptyModel.addMotion(new MotionImpl(29, 20, 40, 50, 40,
        Color.BLUE), "O3");
    emptyModel.addMotion(new MotionImpl(30, 20, 40, 50, 40,
        Color.BLUE), "O3");
    emptyModel.addMotion(new MotionImpl(31, 20, 40, 40, 40,
        Color.BLUE), "O3");
    emptyModel.addMotion(new MotionImpl(32, 20, 40, 40, 40,
        Color.BLUE), "O3");
    emptyModel.addMotion(new MotionImpl(33, 20, 40, 60, 60,
        Color.BLUE), "O3");
    emptyModel.addMotion(new MotionImpl(34, 20, 40, 50, 50,
        Color.BLUE), "O3");
    emptyModel.addMotion(new MotionImpl(35, 20, 40, 60, 40,
        Color.BLUE), "O3");
    emptyModel.addMotion(new MotionImpl(36, 20, 40, 50, 50,
        Color.BLUE), "O3");
    emptyModel.addMotion(new MotionImpl(37, 20, 40, 50, 50,
        Color.RED), "O3");
    emptyModel.addShape("R3", "rectangle");
    emptyModel.addMotion(new MotionImpl(1, 200, 200, 50, 100,
        new Color(255, 0, 0)), "R3");
    emptyModel.addMotion(new MotionImpl(10, 200, 200, 50, 100,
        new Color(255, 0, 0)), "R3");
    emptyModel.addMotion(new MotionImpl(50, 300, 300, 50, 100,
        new Color(255, 0, 0)), "R3");
    emptyModel.addMotion(new MotionImpl(51, 300, 300, 50, 100,
        new Color(255, 0, 0)), "R3");
    emptyModel.addMotion(new MotionImpl(70, 300, 300, 25, 100,
        new Color(255, 0, 0)), "R3");

    viewModel = new ViewModel(emptyModel);
    textualView = new TextualView(viewModel, stringBuffer);
    textualView.display();

    assertEquals(""
            + "canvas 0.0 0.0 1000.0 1000.0\n"
            + "shape O3 ellipse\n"
            + "motion O3 0 75.0 25.0 50.0 40.0 0 255 0   10 75.0 25.0 50.0 40.0 0 0 255\n"
            + "motion O3 10 75.0 25.0 50.0 40.0 0 0 255   12 75.0 25.0 50.0 40.0 0 0 255\n"
            + "motion O3 12 75.0 25.0 50.0 40.0 0 0 255   15 50.0 25.0 50.0 40.0 0 0 255\n"
            + "motion O3 15 50.0 25.0 50.0 40.0 0 0 255   16 50.0 30.0 50.0 40.0 0 0 255\n"
            + "motion O3 16 50.0 30.0 50.0 40.0 0 0 255   19 20.0 30.0 50.0 40.0 0 0 255\n"
            + "motion O3 19 20.0 30.0 50.0 40.0 0 0 255   22 20.0 40.0 50.0 40.0 0 0 255\n"
            + "motion O3 22 20.0 40.0 50.0 40.0 0 0 255   24 30.0 30.0 50.0 40.0 0 0 255\n"
            + "motion O3 24 30.0 30.0 50.0 40.0 0 0 255   27 40.0 40.0 50.0 40.0 0 0 255\n"
            + "motion O3 27 40.0 40.0 50.0 40.0 0 0 255   28 30.0 30.0 50.0 40.0 0 0 255\n"
            + "motion O3 28 30.0 30.0 50.0 40.0 0 0 255   29 20.0 40.0 50.0 40.0 0 0 255\n"
            + "motion O3 29 20.0 40.0 50.0 40.0 0 0 255   30 20.0 40.0 50.0 40.0 0 0 255\n"
            + "motion O3 30 20.0 40.0 50.0 40.0 0 0 255   31 20.0 40.0 40.0 40.0 0 0 255\n"
            + "motion O3 31 20.0 40.0 40.0 40.0 0 0 255   32 20.0 40.0 40.0 40.0 0 0 255\n"
            + "motion O3 32 20.0 40.0 40.0 40.0 0 0 255   33 20.0 40.0 60.0 60.0 0 0 255\n"
            + "motion O3 33 20.0 40.0 60.0 60.0 0 0 255   34 20.0 40.0 50.0 50.0 0 0 255\n"
            + "motion O3 34 20.0 40.0 50.0 50.0 0 0 255   35 20.0 40.0 60.0 40.0 0 0 255\n"
            + "motion O3 35 20.0 40.0 60.0 40.0 0 0 255   36 20.0 40.0 50.0 50.0 0 0 255\n"
            + "motion O3 36 20.0 40.0 50.0 50.0 0 0 255   37 20.0 40.0 50.0 50.0 255 0 0\n"
            + "shape R3 rect\n"
            + "motion R3 1 200.0 200.0 50.0 100.0 255 0 0   10 200.0 200.0 50.0 100.0 255 0 0\n"
            + "motion R3 10 200.0 200.0 50.0 100.0 255 0 0   50 300.0 300.0 50.0 100.0 255 0 0\n"
            + "motion R3 50 300.0 300.0 50.0 100.0 255 0 0   51 300.0 300.0 50.0 100.0 255 0 0\n"
            + "motion R3 51 300.0 300.0 50.0 100.0 255 0 0   70 300.0 300.0 25.0 100.0 255 0 0\n",
        stringBuffer.toString());
  }

  // testing a model with no shapes added yet
  @Test
  public void testGetDescription3() {
    initData();
    viewModel = new ViewModel(emptyModel);
    textualView = new TextualView(viewModel, stringBuffer);
    textualView.display();

    assertEquals("canvas 0.0 0.0 1000.0 1000.0\n"
            + "No description available. No shapes in this model",
        stringBuffer.toString());
  }

  // testing printing description with shapes that have no motions
  @Test
  public void testGetDescription4() {
    initData();
    viewModel = new ViewModel(model3);
    textualView = new TextualView(viewModel, stringBuffer);
    textualView.display();

    assertEquals("canvas 0.0 0.0 1000.0 1000.0\n"
            + "shape R1 rect\n"
            + "shape O1 ellipse\n"
            + "shape O2 ellipse\n",
        stringBuffer.toString());

  }

  // tests the description of a shape with a long list of motions
  @Test
  public void testGetDescription5() {
    initData();
    emptyModel.addShape("O3", "ellipse");
    emptyModel.addMotion(new MotionImpl(0, 75, 25, 50, 40,
        Color.GREEN), "O3");
    emptyModel.addMotion(new MotionImpl(10, 75, 25, 50, 40,
        Color.BLUE), "O3");
    emptyModel.addMotion(new MotionImpl(12, 75, 25, 50, 40,
        Color.BLUE), "O3");
    emptyModel.addMotion(new MotionImpl(15, 50, 25, 50, 40,
        Color.BLUE), "O3");
    emptyModel.addMotion(new MotionImpl(16, 50, 30, 50, 40,
        Color.BLUE), "O3");
    emptyModel.addMotion(new MotionImpl(19, 20, 30, 50, 40,
        Color.BLUE), "O3");
    emptyModel.addMotion(new MotionImpl(22, 20, 40, 50, 40,
        Color.BLUE), "O3");
    emptyModel.addMotion(new MotionImpl(24, 30, 30, 50, 40,
        Color.BLUE), "O3");
    emptyModel.addMotion(new MotionImpl(27, 40, 40, 50, 40,
        Color.BLUE), "O3");
    emptyModel.addMotion(new MotionImpl(28, 30, 30, 50, 40,
        Color.BLUE), "O3");
    emptyModel.addMotion(new MotionImpl(29, 20, 40, 50, 40,
        Color.BLUE), "O3");
    emptyModel.addMotion(new MotionImpl(30, 20, 40, 50, 40,
        Color.BLUE), "O3");
    emptyModel.addMotion(new MotionImpl(31, 20, 40, 40, 40,
        Color.BLUE), "O3");
    emptyModel.addMotion(new MotionImpl(32, 20, 40, 40, 40,
        Color.BLUE), "O3");
    emptyModel.addMotion(new MotionImpl(33, 20, 40, 60, 60,
        Color.BLUE), "O3");
    emptyModel.addMotion(new MotionImpl(34, 20, 40, 50, 50,
        Color.BLUE), "O3");
    emptyModel.addMotion(new MotionImpl(35, 20, 40, 60, 40,
        Color.BLUE), "O3");
    emptyModel.addMotion(new MotionImpl(36, 20, 40, 50, 50,
        Color.BLUE), "O3");
    emptyModel.addMotion(new MotionImpl(37, 20, 40, 50, 50,
        Color.RED), "O3");

    viewModel = new ViewModel(emptyModel);
    textualView = new TextualView(viewModel, stringBuffer);
    textualView.display();

    assertEquals(""
            + "canvas 0.0 0.0 1000.0 1000.0\n"
            + "shape O3 ellipse\n"
            + "motion O3 0 75.0 25.0 50.0 40.0 0 255 0   10 75.0 25.0 50.0 40.0 0 0 255\n"
            + "motion O3 10 75.0 25.0 50.0 40.0 0 0 255   12 75.0 25.0 50.0 40.0 0 0 255\n"
            + "motion O3 12 75.0 25.0 50.0 40.0 0 0 255   15 50.0 25.0 50.0 40.0 0 0 255\n"
            + "motion O3 15 50.0 25.0 50.0 40.0 0 0 255   16 50.0 30.0 50.0 40.0 0 0 255\n"
            + "motion O3 16 50.0 30.0 50.0 40.0 0 0 255   19 20.0 30.0 50.0 40.0 0 0 255\n"
            + "motion O3 19 20.0 30.0 50.0 40.0 0 0 255   22 20.0 40.0 50.0 40.0 0 0 255\n"
            + "motion O3 22 20.0 40.0 50.0 40.0 0 0 255   24 30.0 30.0 50.0 40.0 0 0 255\n"
            + "motion O3 24 30.0 30.0 50.0 40.0 0 0 255   27 40.0 40.0 50.0 40.0 0 0 255\n"
            + "motion O3 27 40.0 40.0 50.0 40.0 0 0 255   28 30.0 30.0 50.0 40.0 0 0 255\n"
            + "motion O3 28 30.0 30.0 50.0 40.0 0 0 255   29 20.0 40.0 50.0 40.0 0 0 255\n"
            + "motion O3 29 20.0 40.0 50.0 40.0 0 0 255   30 20.0 40.0 50.0 40.0 0 0 255\n"
            + "motion O3 30 20.0 40.0 50.0 40.0 0 0 255   31 20.0 40.0 40.0 40.0 0 0 255\n"
            + "motion O3 31 20.0 40.0 40.0 40.0 0 0 255   32 20.0 40.0 40.0 40.0 0 0 255\n"
            + "motion O3 32 20.0 40.0 40.0 40.0 0 0 255   33 20.0 40.0 60.0 60.0 0 0 255\n"
            + "motion O3 33 20.0 40.0 60.0 60.0 0 0 255   34 20.0 40.0 50.0 50.0 0 0 255\n"
            + "motion O3 34 20.0 40.0 50.0 50.0 0 0 255   35 20.0 40.0 60.0 40.0 0 0 255\n"
            + "motion O3 35 20.0 40.0 60.0 40.0 0 0 255   36 20.0 40.0 50.0 50.0 0 0 255\n"
            + "motion O3 36 20.0 40.0 50.0 50.0 0 0 255   37 20.0 40.0 50.0 50.0 255 0 0\n",
        stringBuffer.toString());
  }

  @Test(expected = IllegalStateException.class)
  public void testNullModel() {
    initData();
    new TextualView(null, stringBuffer);
  }

  @Test(expected = IllegalStateException.class)
  public void testNullAppendable() {
    initData();
    new TextualView(viewModel, null);
  }

  @Test(expected = IllegalStateException.class)
  public void testFailingAppendable() {
    initData();
    TextualView textualView = new TextualView(viewModel, failingAppendable);
    textualView.display();
  }
}
