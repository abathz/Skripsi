/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadoc;

/**
 * Kelas Abstract OperasiMatematika. Kelas ini memiliki method {@link #calculate(int, int)}
 * 
 * @author abathz
 */
public abstract class OperasiMatematikaInterface {
  
  /**
   * Method untuk menghasilkan perhitungan 2 buah bilangan
   * @param a Bilangan pertama
   * @param b Bilagan kedua
   * @return hasil perhitungan 2 buah bilangan {#link {@link Double#NaN}}
   */
  public int calculate(int a, int b){return 0;}
}
