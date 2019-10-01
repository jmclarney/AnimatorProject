import java.awt.Color;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import cs3500.animator.model.animatormodel.AnimatorModelImpl;
import cs3500.animator.model.animatormodel.IAnimatorModel;
import cs3500.animator.model.animatormodel.ViewModel;
import cs3500.animator.model.motion.MotionImpl;
import cs3500.animator.view.SVGView;

/**
 * These are tests for the SVG View.
 */
public class SVGViewTests {
  IAnimatorModel actualModel;
  ViewModel viewModel;
  SVGView svgView1;

  IAnimatorModel actualModel2;
  ViewModel viewModel2;
  SVGView svgView2;

  IAnimatorModel actualModel3;
  ViewModel viewModel3;
  SVGView svgView3;

  Appendable ap;
  FailingAppendable failingAppendable;

  /**
   * Initializes the data.
   */
  public void initData() {
    ap = new StringBuilder();
    failingAppendable = new FailingAppendable();

    actualModel = new AnimatorModelImpl();
    actualModel.addShape("01", "ellipse");
    actualModel.addMotion(new MotionImpl(0, 50, 50, 20, 20,
        Color.GRAY), "01");
    actualModel.addMotion(new MotionImpl(10, 0, 100, 20, 20,
        Color.GRAY), "01");
    actualModel.addMotion(new MotionImpl(20, 0, 100, 50, 40,
        Color.GRAY), "01");
    actualModel.addMotion(new MotionImpl(30, 0, 100, 50, 40,
        Color.BLACK), "01");
    actualModel.addMotion(new MotionImpl(40, 75, 25, 50, 40,
        Color.BLACK), "01");
    viewModel = new ViewModel(actualModel);
    svgView1 = new SVGView(viewModel, ap, 1);

    actualModel2 = new AnimatorModelImpl();

    actualModel2.addShape("R3", "rectangle");
    actualModel2.addMotion(new MotionImpl(1, 200, 200, 50, 100,
        new Color(255, 0, 0)), "R3");
    actualModel2.addMotion(new MotionImpl(10, 200, 200, 50, 100,
        new Color(255, 0, 0)), "R3");
    actualModel2.addMotion(new MotionImpl(50, 300, 300, 50, 100,
        new Color(255, 0, 0)), "R3");
    actualModel2.addMotion(new MotionImpl(51, 300, 300, 50, 100,
        new Color(255, 0, 0)), "R3");
    actualModel2.addMotion(new MotionImpl(70, 300, 300, 25, 100,
        new Color(255, 0, 0)), "R3");
    actualModel2.addShape("01", "ellipse");
    actualModel2.addMotion(new MotionImpl(0, 50, 50, 20, 20,
        Color.GRAY), "01");
    actualModel2.addMotion(new MotionImpl(10, 0, 100, 20, 20,
        Color.GRAY), "01");
    actualModel2.addMotion(new MotionImpl(20, 0, 100, 50, 40,
        Color.GRAY), "01");
    actualModel2.addMotion(new MotionImpl(30, 0, 100, 50, 40,
        Color.BLACK), "01");
    actualModel2.addMotion(new MotionImpl(40, 75, 25, 50, 40,
        Color.BLACK), "01");


    viewModel2 = new ViewModel(actualModel2);
    svgView2 = new SVGView(viewModel2, ap, 1);

    actualModel3 = new AnimatorModelImpl();
    actualModel3.addShape("O2", "ellipse");
    viewModel3 = new ViewModel(actualModel3);
    svgView3 = new SVGView(viewModel3, ap, 1);
  }

  @Test
  public void testSVGOneShapeNoMoves() {
    initData();
    svgView3.display();

    assertEquals(""
        + "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"1000.0\" height=\"1000.0\" "
        + "version="
        + "\"1.1\">\n"
        + "<id=\"O2\">\n"
        + "</svg>", svgView3.getAp().toString());
  }

  @Test
  public void testSVGOneShape() {
    initData();
    svgView1.display();

    assertEquals(""
        + "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"1000.0\" height=\"1000.0\" version"
        + "=\"1.1\">\n"
        + "<ellipse xmlns=\"http://www.w3.org/2000/svg\" id=\"01\" cx=\"50.0\" cy=\"50.0\" rx="
        + "\"20.0\" ry=\"20.0\" fill=\"rgb(128,128,128)\" visibility=\"hidden\">\n"
        + "<set attributeType=\"xml\" begin=\"0ms\" attributeName=\"visibility\" to=\"visible\"/>\n"
        + "<set attributeType=\"xml\" begin=\"40000.0ms\" attributeName=\"visibility\" to=\"hidden"
        + "\"/>\n"
        + "<animate xmlns=\"http://www.w3.org/2000/svg\" attributeType=\"xml\" begin=\"0.0ms\" "
        + "dur=\"10000ms\" attributeName=\"cx\" from=\"50.0\" to=\"0.0\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"10000ms\" attributeName=\"cy\" "
        + "from=\"50.0\" to=\"100.0\" fill=\"freeze\" />\n"
        + "<animate xmlns=\"http://www.w3.org/2000/svg\" attributeType=\"xml\" begin=\"10000.0ms\""
        + " dur=\"10000ms\" attributeName=\"rx\" from=\"20.0\" to=\"50.0\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"10000.0ms\" dur=\"10000ms\" attributeName=\"ry\""
        + " from=\"20.0\" to=\"40.0\" fill=\"freeze\" />\n"
        + "<animate xmlns=\"http://www.w3.org/2000/svg\" attributeType=\"xml\" begin=\"20000.0ms\""
        + " dur=\"10000ms\" attributeName=\"color\" from=\"(128,128,128)\" to=\"(0,0,0)\" fill=\""
        + "freeze\" />\n"
        + "<animate xmlns=\"http://www.w3.org/2000/svg\" attributeType=\"xml\" begin=\"30000.0ms\""
        + " dur=\"10000ms\" attributeName=\"cx\" from=\"0.0\" to=\"75.0\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"30000.0ms\" dur=\"10000ms\" attributeName=\"cy\""
        + " from=\"100.0\" to=\"25.0\" fill=\"freeze\" />\n"
        + "</ellipse>\n"
        + "</svg>", svgView1.getAp().toString());
  }

  @Test
  public void testSVGTwoShapes() {
    initData();
    svgView2.display();

    assertEquals(""
        + "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"1000.0\" height=\"1000.0\" version="
        + "\"1.1\">\n"
        + "<rect xmlns=\"http://www.w3.org/2000/svg\" id=\"R3\" x=\"200.0\" y=\"200.0\" width=\""
        + "50.0\" height=\"100.0\" fill=\"rgb(255,0,0)\" visibility=\"hidden\">\n"
        + "<set attributeType=\"xml\" begin=\"1ms\" attributeName=\"visibility\" to=\"visible\"/>\n"
        + "<set attributeType=\"xml\" begin=\"70000.0ms\" attributeName=\"visibility\" to=\"hidden"
        + "\"/>\n"
        + "<animate xmlns=\"http://www.w3.org/2000/svg\" attributeType=\"xml\" begin=\"1000.0ms\""
        + " dur=\"9000ms\" fill=\"freeze\" />\n"
        + "<animate xmlns=\"http://www.w3.org/2000/svg\" attributeType=\"xml\" begin=\"10000.0ms\""
        + " dur=\"40000ms\" attributeName=\"x\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"10000.0ms\" dur=\"40000ms\" attributeName=\"y\""
        + " from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n"
        + "<animate xmlns=\"http://www.w3.org/2000/svg\" attributeType=\"xml\" begin=\"50000.0ms\""
        + " dur=\"1000ms\" fill=\"freeze\" />\n"
        + "<animate xmlns=\"http://www.w3.org/2000/svg\" attributeType=\"xml\" begin=\"51000.0ms\""
        + " dur=\"19000ms\" attributeName=\"width\" from=\"50.0\" to=\"25.0\" fill=\"freeze\" />\n"
        + "</rect>\n"
        + "<ellipse xmlns=\"http://www.w3.org/2000/svg\" id=\"01\" cx=\"50.0\" cy=\"50.0\" rx=\""
        + "20.0\" ry=\"20.0\" fill=\"rgb(128,128,128)\" visibility=\"hidden\">\n"
        + "<set attributeType=\"xml\" begin=\"0ms\" attributeName=\"visibility\" to=\"visible"
        + "\"/>\n"
        + "<set attributeType=\"xml\" begin=\"40000.0ms\" attributeName=\"visibility\" to=\""
        + "hidden\"/>\n"
        + "<animate xmlns=\"http://www.w3.org/2000/svg\" attributeType=\"xml\" begin=\"0.0ms\""
        + " dur=\"10000ms\" attributeName=\"cx\" from=\"50.0\" to=\"0.0\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"10000ms\" attributeName=\"cy\" "
        + "from=\"50.0\" to=\"100.0\" fill=\"freeze\" />\n"
        + "<animate xmlns=\"http://www.w3.org/2000/svg\" attributeType=\"xml\" begin=\"10000.0ms"
        + "\" dur=\"10000ms\" attributeName=\"rx\" from=\"20.0\" to=\"50.0\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"10000.0ms\" dur=\"10000ms\" attributeName=\"ry\""
        + " from=\"20.0\" to=\"40.0\" fill=\"freeze\" />\n"
        + "<animate xmlns=\"http://www.w3.org/2000/svg\" attributeType=\"xml\" begin=\"20000.0ms\""
        + " dur=\"10000ms\" attributeName=\"color\" from=\"(128,128,128)\" to=\"(0,0,0)\" fill=\""
        + "freeze\" />\n"
        + "<animate xmlns=\"http://www.w3.org/2000/svg\" attributeType=\"xml\" begin=\"30000.0ms\""
        + " dur=\"10000ms\" attributeName=\"cx\" from=\"0.0\" to=\"75.0\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"30000.0ms\" dur=\"10000ms\" attributeName=\"cy\""
        + " from=\"100.0\" to=\"25.0\" fill=\"freeze\" />\n"
        + "</ellipse>\n"
        + "</svg>", svgView2.getAp().toString());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNullModel() {
    initData();
    svgView1 = new SVGView(null, ap, 1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNullAppendable() {
    initData();
    svgView1 = new SVGView(viewModel, null, 1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNegativeSpeed() {
    initData();
    svgView1 = new SVGView(viewModel, ap, -7);
  }

  @Test (expected = IllegalStateException.class)
  public void testAppendableIOException() {
    initData();
    svgView1 = new SVGView(viewModel, failingAppendable, 1);
    svgView1.display();
  }

  @Test
  public void testGetAp() {
    initData();

    svgView1.display();

    assertEquals(""
        + "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"1000.0\" height=\"1000.0\" "
        + "version=\"1.1\">\n"
        + "<ellipse xmlns=\"http://www.w3.org/2000/svg\" id=\"01\" cx=\"50.0\" cy=\"50.0\" "
        + "rx=\"20.0\" ry=\"20.0\" fill=\"rgb(128,128,128)\" visibility=\"hidden\">\n"
        + "<set attributeType=\"xml\" begin=\"0ms\" attributeName=\"visibility\" to=\""
        + "visible\"/>\n"
        + "<set attributeType=\"xml\" begin=\"40000.0ms\" attributeName=\"visibility\" "
        + "to=\"hidden\"/>\n"
        + "<animate xmlns=\"http://www.w3.org/2000/svg\" attributeType=\"xml\" begin=\"0.0ms\""
        + " dur=\"10000ms\" attributeName=\"cx\" from=\"50.0\" to=\"0.0\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"10000ms\" attributeName=\"cy\""
        + " from=\"50.0\" to=\"100.0\" fill=\"freeze\" />\n"
        + "<animate xmlns=\"http://www.w3.org/2000/svg\" attributeType=\"xml\" begin=\"10000.0ms\""
        + " dur=\"10000ms\" attributeName=\"rx\" from=\"20.0\" to=\"50.0\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"10000.0ms\" dur=\"10000ms\" attributeName=\"ry\""
        + " from=\"20.0\" to=\"40.0\" fill=\"freeze\" />\n"
        + "<animate xmlns=\"http://www.w3.org/2000/svg\" attributeType=\"xml\" begin=\"20000.0ms\""
        + " dur=\"10000ms\" attributeName=\"color\" from=\"(128,128,128)\" to=\"(0,0,0)\" fill=\""
        + "freeze\" />\n"
        + "<animate xmlns=\"http://www.w3.org/2000/svg\" attributeType=\"xml\" begin=\"30000.0ms\""
        + " dur=\"10000ms\" attributeName=\"cx\" from=\"0.0\" to=\"75.0\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"30000.0ms\" dur=\"10000ms\" attributeName=\"cy\""
        + " from=\"100.0\" to=\"25.0\" fill=\"freeze\" />\n"
        + "</ellipse>\n"
        + "</svg>", svgView1.getAp().toString());

  }

}
