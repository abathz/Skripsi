package javadoc;

/**
 * Kelas ini merupakan Kelas Perkalian
 *
 * @author Adli Fariz Bonaputra
 * @see "Perkalian"
 *
 */
public class Perkalian extends OperasiMatematikaInterface {

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
