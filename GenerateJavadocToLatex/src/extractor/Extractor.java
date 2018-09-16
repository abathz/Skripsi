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
   * atribut untuk nama \textit{file}
   */
  private static String fileName;

  /**
   * \textit{Method} ini berperan sebagai \textit{method} untuk menjalankan
   * \textit{custom doclet}
   *
   * @param root berperan sebagai mengambil seluruh informasi spesifik dari
   *             \textit{option} yang terdapat pada \textit{command-line} sebuah
   *             \textit{terminal}. Selain itu berperan juga untuk mengambil informasi dari
   *             sekumpulan \textit{file java} yang akan di proses.
   * @return kondisi true
   */
  public static boolean start(RootDoc root) {
    init(root.classes());
    return true;
  }

  /**
   * \textit{Method} ini berperan untuk menulis kedalam sebuah \textit{file}
   * saat \textit{javadoc} berjalan.
   *
   * @param classes sebuah array yang berisikan sekumpulan \textit{file java}
   *                yang akan di proses.
   */
  private static void init(ClassDoc[] classes) {
    FileWriter file;
    new File("output").mkdirs();
    try {
      if (fileName == null) {
        file = new FileWriter("output/doc.tex");
      } else {
        file = new FileWriter("output/" + fileName + ".tex");
      }
      BufferedWriter out = new BufferedWriter(file);
      out.write("\\documentclass{article}\n");
      out.write("\\begin{document}\n");
      out.write("\\begin{enumerate}\n");

      ClassExtractor.extractClassContent(classes, out);

      out.write("\\end{enumerate}\n");
      out.write("\\end{document}\n");
      out.close();
    } catch (IOException e) { }
  }

  /**
   * Method untuk menghitung banyak option yang digunakan pada
   * \textit{command-line}
   *
   * @param option sebuah option
   * @return panjang setiap option
   */
  public static int optionLength(String option) {
    if (option.equals("-filename")) {
      return 2;
    }
    return Doclet.optionLength(option);

  }

  /**
   * Pengecekan option valid
   *
   * @param args String array 2 dimensi dari option
   * @param err  sebuah error jika tidak terdapat option tersebut.
   * @return bernilai true jika option tersebut dikenali, false jika option
   * tersebut tidak dikenali
   */
  public static boolean validOptions(String[][] args, DocErrorReporter err) {
    for (int i = 0; i < args.length; ++i) {
      if (args[i][0].equals("-filename")) {
        fileName = args[i][1];
      }
    }
    return Doclet.validOptions(args, err);
  }
}

