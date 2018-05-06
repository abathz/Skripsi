package extractor;

import com.sun.javadoc.*;
import java.io.*;

/**
 * Kelas ini merupakan kelas untuk mengambil informasi sebuah atribut yang
 * terdapat pada kelas
 *
 * @author Adli Fariz Bonaputra
 */
public class AttributeClassExtractor {

  /**
   * \textit{Method} ini akan menampilkan atribut-atribut yang dimiliki oleh
   * sebuah kelas
   *
   * @param fields sebuah array berisikan sejumlah atribut dari kelas
   * @param out turunan dari kelas \texttt{Writer} yang digunakan untuk menulis
   * file text
   */
  public static void extractAttributeClassContent(FieldDoc[] fields, BufferedWriter out) {
    try {
      if (fields.length == 0) {
        out.write("Kelas ini tidak memiliki atribut. ");
      } else {
        out.write("Atribut yang dimiliki kelas ini adalah sebagai berikut.\n");
        out.write("\\begin{itemize}\n");
        for (int j = 0; j < fields.length; j++) {
          Type type = fields[j].type();
          out.write("\\item \\texttt{" + type.typeName() + " " + fields[j].name().replace("_", "\\_") + "} - " + fields[j].commentText() + "\n");
        }
        out.write("\\end{itemize}\n");
      }
    } catch (IOException e) {
    }
  }
}
