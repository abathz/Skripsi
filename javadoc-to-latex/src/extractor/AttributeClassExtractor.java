package extractor;


import com.sun.javadoc.*;
import java.io.*;

public class AttributeClassExtractor {

  public static void extractAttributeClassContent(FieldDoc[] fields, BufferedWriter out) throws IOException {
    out.write("Atribut yang dimiliki kelas ini adalah sebagai berikut.\n");
    if (fields.length == 0) {
      out.write("\\begin{itemize}\n");
      out.write("\\item Tidak memiliki Atribut" + "\n");
      out.write("\\end{itemize}\n");
    } else {
      out.write("\\begin{itemize}\n");
      for (int j = 0; j < fields.length; j++) {
        Type type = fields[j].type();
        out.write("\\item \\texttt{" + type.typeName() + " " + fields[j].name() + "} - " + fields[j].commentText() + "\n");
      }
      out.write("\\end{itemize}\n");
    }
  }
}
