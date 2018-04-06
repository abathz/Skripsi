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
   * \textit{Method} ini akan menampilkan nama kelas berserta penjelasan dari sebuah kelas
   * @param classes sebuah array berisikan sejumlah kelas
   * @param out turunan dari kelas \texttt{Writer} yang digunakan untuk menulis file text
   * @throws IOException 
   */
  public static void extractClassContent(ClassDoc[] classes, BufferedWriter out) throws IOException {
    for (ClassDoc classDoc : classes) {
//      System.out.println(classDoc.superclass().name());

      out.write("\\item \\texttt{" + classDoc.name() + "}\\\\ \n");
      out.write(classDoc.commentText() + "\n\n");

      FieldDoc[] fields = classDoc.fields(false);
      AttributeClassExtractor.extractAttributeClassContent(fields, out);

      MethodDoc[] methods = classDoc.methods(false);
      MethodExtractor.extractMethodContent(classDoc, methods, out);
    }
  }
}
