package extractor;

import com.sun.javadoc.*;
import java.io.*;

/**
 * Kelas ini merupakan kelas untuk menjalan \textit{custom doclet}
 * 
 * @author Adli Fariz Bonaputra
 */

public class Extractor {

  /**
   * \textit{Method} ini berperan sebagai \textit{method} untuk menjalankan \textit{custom doclet}
   * @param root berperan sebagai mengambil seluruh informasi spesifik dari \textit{option} yang terdapat pada \textit{command-line} sebuah \textit{terminal}. Selain itu berperan juga untuk mengambil informasi dari sekumpulan \textit{file java} yang akan di proses.
   * @return kondisi true
   * @throws IOException 
   */
  public static boolean start(RootDoc root) throws IOException {
    init(root.classes());
    return true;
  }
  
  /**
   * \textit{Method} ini berperan untuk menulis kedalam sebuah \textit{file} saat \textit{javadoc} berjalan.
   * @param classes sebuah array yang berisikan sekumpulan \textit{file java} yang akan di proses.
   * @throws IOException 
   */
  public static void init(ClassDoc[] classes) throws IOException {
    FileWriter file = new FileWriter("../doc.tex");
    BufferedWriter out = new BufferedWriter(file);
    out.write("\\documentclass{article}" + "\n");
    out.write("\\begin{document}" + "\n");
    out.write("\\begin{enumerate}\n");

    ClassExtractor.extractClassContent(classes, out);

    out.write("\\end{enumerate}" + "\n");
    out.write("\\end{document}" + "\n");
    out.close();
  }

}
