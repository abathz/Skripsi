package javadoc;

/**
 * Kelas ini merupakan Kelas Pengurangan
 *
 * @author Adli Fariz Bonaputra
 * @see "Pengurangan"
 */
public class Pengurangan extends OperasiMatematikaInterface {

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
    hasil = a + b;
    return hasil;
  }
}
