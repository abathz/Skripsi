package extractor;

import com.sun.javadoc.*;
import java.io.*;

/**
 * Kelas ini merupakan kelas untuk mengambil informasi dari sebuah kelas
 *
 * @author Adli Fariz Bonaputra
 */
public class ClassExtractor {

  /**
   * \textit{Method} ini akan menampilkan nama kelas berserta penjelasan dari
   * sebuah kelas
   *
   * @param classes sebuah array berisikan sejumlah kelas
   * @param out turunan dari kelas \texttt{Writer} yang digunakan untuk menulis
   * file text
   */
  public static void extractClassContent(ClassDoc[] classes, BufferedWriter out) {
    try {
      for (ClassDoc classDoc : classes) {
        out.write("\\item \\texttt{" + classDoc.name() + "}\\\\");
//        if (classDoc.commentText() != "") {
//          out.write(classDoc.commentText() + " ");
//        }
        
        Tag[] inlineTags = classDoc.inlineTags();
        for (int i = 0; i < inlineTags.length; i++) {
          if (i == 1) {
            out.write("\\texttt{" + inlineTags[i].text().replace("#", "") + "}");
          } else {
            out.write(inlineTags[i].text().replace("#", "").replace("_", "\\_").replace("&amp;", "\\&"));
          }
        }
        out.write("\n\n");

        FieldDoc[] fields = classDoc.fields(false);
        AttributeClassExtractor.extractAttributeClassContent(fields, out);

        MethodDoc[] methods = classDoc.methods(false);
        MethodClassExtractor.extractMethodClassContent(classDoc, methods, out);
      }
    } catch (IOException e) {
    }
  }
}
