import java.io.StringReader;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * These are tests for the MockController and MockView.
 */
public class MockControllerTest {

  Readable rd;
  Appendable ap;

  @Test
  public void testPlay() {
    rd = new StringReader("Play");
    ap = new StringBuilder();

    new MockView(rd, ap);

    assertEquals("Play", ap.toString());
  }

  @Test
  public void testPause() {
    rd = new StringReader("Pause");
    ap = new StringBuilder();

    new MockView(rd, ap);

    assertEquals("Pause", ap.toString());
  }

  @Test
  public void testRestart() {
    rd = new StringReader("Restart");
    ap = new StringBuilder();

    new MockView(rd, ap);

    assertEquals("Restart", ap.toString());
  }

  @Test
  public void testQuit() {
    rd = new StringReader("Quit");
    ap = new StringBuilder();

    new MockView(rd, ap);

    assertEquals("Quit", ap.toString());
  }

  @Test
  public void testLoop() {
    rd = new StringReader("Looping");
    ap = new StringBuilder();

    new MockView(rd, ap);

    assertEquals("Looping", ap.toString());
  }

  @Test
  public void testIncreaseSpeed() {
    rd = new StringReader("IncreaseSpeed");
    ap = new StringBuilder();

    new MockView(rd, ap);

    assertEquals("Increase Speed", ap.toString());
  }

  @Test
  public void testDecreaseSpeed() {
    rd = new StringReader("DecreaseSpeed");
    ap = new StringBuilder();

    new MockView(rd, ap);

    assertEquals("Decrease Speed", ap.toString());
  }

  @Test
  public void testAddShape() {
    rd = new StringReader("AddShape");
    ap = new StringBuilder();

    MockView mockView = new MockView(rd, ap);

    new MockController(mockView, ap);

    mockView.display();

    assertEquals("Add Shape", ap.toString());
  }

  @Test
  public void testAddMotion() {
    rd = new StringReader("AddMotion");
    ap = new StringBuilder();

    MockView mockView = new MockView(rd, ap);

    new MockController(mockView, ap);

    mockView.display();

    assertEquals("Add Motion", ap.toString());
  }

  @Test
  public void testRemoveMotion() {
    rd = new StringReader("RemoveMotion");
    ap = new StringBuilder();

    MockView mockView = new MockView(rd, ap);

    new MockController(mockView, ap);

    mockView.display();

    assertEquals("Remove Motion", ap.toString());
  }

  @Test
  public void testRemoveShape() {
    rd = new StringReader("RemoveShape");
    ap = new StringBuilder();

    MockView mockView = new MockView(rd, ap);

    new MockController(mockView, ap);

    mockView.display();

    assertEquals("Remove Shape", ap.toString());
  }

  @Test
  public void testModifyMotion() {
    rd = new StringReader("ModifyMotion");
    ap = new StringBuilder();

    MockView mockView = new MockView(rd, ap);

    new MockController(mockView, ap);

    mockView.display();

    assertEquals("Modify", ap.toString());
  }

}
