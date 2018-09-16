package javadoc;

/**
 * Kelas ini merupakan Kelas Pertambahan
 *
 * @author Adli Fariz Bonaputra
 * @see "Pertambahan"
 *
 */
public class Pertambahan extends OperasiMatematikaInterface {

  /**
   * Atribut A
   */
  private int a;
  /**
   * Atribut B
   */
  private int b;

  @Override
  public int calculate(int a, int b) {
    int hasil = 0;
    hasil = a + b + 10;
    return hasil;
  }
}
