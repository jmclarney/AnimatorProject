import java.io.IOException;
import java.nio.CharBuffer;

/**
 * This Appendable only throws IOExceptions.
 */
public class FailingAppendable implements Appendable {


  /**
   * Appends the specified character sequence to this <tt>Appendable</tt>.
   *
   * <p>Depending on which class implements the character sequence
   * <tt>csq</tt>, the entire sequence may not be appended.  For
   * instance, if <tt>csq</tt> is a {@link CharBuffer} then the subsequence to append is defined by
   * the buffer's position and limit.
   *
   * @param csq The character sequence to append.  If <tt>csq</tt> is
   *            <tt>null</tt>, then the four characters <tt>"null"</tt> are
   *            appended to this Appendable.
   * @return A reference to this <tt>Appendable</tt>
   * @throws IOException If an I/O error occurs
   */
  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException("IO Exception");
  }

  /**
   * Appends a subsequence of the specified character sequence to this
   * <tt>Appendable</tt>.
   *
   * <p>An invocation of this method of the form <tt>out.append(csq, start,
   * end)</tt> when <tt>csq</tt> is not <tt>null</tt>, behaves in exactly the same way as the
   * invocation
   *
   * <pre>
   *     out.append(csq.subSequence(start, end)) </pre>
   *
   * @param csq The character sequence from which a subsequence will be appended. If <tt>csq</tt> is
   *            <tt>null</tt>, then characters will be appended as if
   *            <tt>csq</tt> contained the four characters <tt>"null"</tt>.
   * @param start The index of the first character in the subsequence
   * @param end The index of the character following the last character in the subsequence
   * @return A reference to this <tt>Appendable</tt>
   * @throws IndexOutOfBoundsException If <tt>start</tt> or <tt>end</tt> are negative,
   *        <tt>start</tt> is greater than <tt>end</tt>, or <tt>end</tt> is greater than
   *        <tt>csq.length()</tt>
   * @throws IOException If an I/O error occurs
   */
  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException("IO Exception");
  }

  /**
   * Appends the specified character to this <tt>Appendable</tt>.
   *
   * @param c The character to append
   * @return A reference to this <tt>Appendable</tt>
   * @throws IOException If an I/O error occurs
   */
  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException("IO Exception");
  }
}
