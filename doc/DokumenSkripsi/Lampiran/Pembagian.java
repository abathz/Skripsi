package javadoc;

/**
 * Kelas ini merupakan Kelas Pembagian
 *
 * @author Adli Fariz Bonaputra
 * @see "Pembagian"
 */
public class Pembagian extends OperasiMatematikaInterface {

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
    int hasil = a / b;
    return hasil;
  }

}
