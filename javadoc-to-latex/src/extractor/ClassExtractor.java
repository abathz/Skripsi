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
   */
  public static void extractClassContent(ClassDoc[] classes, BufferedWriter out){
    try {
      for (ClassDoc classDoc : classes) {

      out.write("\\item \\texttt{" + classDoc.name() + "}\\\\ \n");
      if (classDoc.commentText() != "") {
        out.write(classDoc.commentText() + "\n\n");
      }

      FieldDoc[] fields = classDoc.fields(false);
      AttributeClassExtractor.extractAttributeClassContent(fields, out);

      MethodDoc[] methods = classDoc.methods(false);
      MethodClassExtractor.extractMethodClassContent(classDoc, methods, out);
    }
    } catch (IOException e){
    }
  }
}
