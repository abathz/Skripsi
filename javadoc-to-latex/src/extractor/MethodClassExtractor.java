package extractor;

import java.io.*;
import com.sun.javadoc.*;

/**
 * Kelas ini merupakan kelas untuk mengambil informasi sebuah \textit{method}
 * terdapat pada kelas
 *
 * @author Adli Fariz Bonaputra
 */
public class MethodClassExtractor {

  /**
   * \textit{Method} ini akan menampilkan \textit{method-method} yang dimiliki
   * oleh sebuah kelas
   *
   * @param superclass sebuah objek ClassDoc
   * @param methods sebuah array berisikan sejumlah \textit{method} dari kelas
   * @param out turunan dari kelas \texttt{Writer} yang digunakan untuk menulis
   * file text
   */
  public static void extractMethodClassContent(ClassDoc superclass, MethodDoc[] methods, BufferedWriter out) {
    try {
      out.write("\\textit{Method-method} yang dimiliki kelas ini adalah sebagai berikut.\n");
      out.write("\\begin{itemize}\n");
      for (MethodDoc method : methods) {
        if (method.overriddenMethod() != null) {
          MethodDoc overrideMethod = method.overriddenMethod();
          Type type = overrideMethod.returnType();
          Parameter[] param = overrideMethod.parameters();
          out.write("\\item \\texttt{" + overrideMethod.modifiers() + " " + type.typeName() + " " + overrideMethod.name() + "(");
          for (int k = 0; k < param.length; k++) {
            out.write(param[k].toString());
            if (k < param.length - 1) {
              out.write(", ");
            }
          }
          out.write(")}\\\\ " + "\n");
          out.write(overrideMethod.commentText() + "\n\n");
          ParamTag[] paramTags = overrideMethod.paramTags();
          if (paramTags.length == 0) {
            out.write("\\textbf{Parameter:}");
            out.write("\\begin{itemize}\n");
            out.write("\\item Tidak memiliki parameter \\textit{method}\n");
            out.write("\\end{itemize}\n");
          } else {
            out.write("\\textbf{Parameter:}");
            out.write("\n");
            out.write("\\begin{itemize}\n");
            for (int k = 0; k < paramTags.length; k++) {
              out.write("\\item \\texttt{" + param[k].typeName() + " " + paramTags[k].parameterName() + "} - \n");
              out.write(paramTags[k].parameterComment() + "\n");
            }
            out.write("\\end{itemize}\n");
          }

          Tag[] returnTags = overrideMethod.tags("return");
          Tag[] throwTags = overrideMethod.tags("throws");
          AnnotationDesc[] override = overrideMethod.annotations();
          if (type.typeName().equals("void") || returnTags.length == 0) {
            out.write("\\textbf{Kembalian}: Tidak memiliki \\textit{return value}");
            out.write("\n\n");
          } else {
            out.write("\\textbf{Kembalian}: " + returnTags[0].text());
            out.write("\n\n");
          }
          if (throwTags.length == 0) {
            out.write("\\textbf{Exception}: Tidak memiliki \\textit{exception}");
            out.write("\n\n");
          } else {
            out.write("\\textbf{Exception}: " + throwTags[0].text());
            out.write("\n\n");
          }
          if (override.length == 0) {
          } else {
            out.write("\\textbf{Override}: \\texttt{" + overrideMethod.name() + "} dari kelas \\texttt{" + superclass.superclass().name() + "}");
            out.write("\n\n");
          }
        } else {
          Type type = method.returnType();
          Parameter[] param = method.parameters();
          out.write("\\item \\texttt{" + method.modifiers() + " " + type.typeName() + " " + method.name() + "(");
          for (int k = 0; k < param.length; k++) {
            out.write(param[k].toString());
            if (k < param.length - 1) {
              out.write(", ");
            }
          }
          out.write(")}\\\\ " + "\n");
          out.write(method.commentText() + "\n\n");
          ParamTag[] paramTags = method.paramTags();
          if (paramTags.length == 0) {
            out.write("\\textbf{Parameter:}");
            out.write("\\begin{itemize}\n");
            out.write("\\item Tidak memiliki parameter \\textit{method}\n");
            out.write("\\end{itemize}\n");
          } else {
            out.write("\\textbf{Parameter:}");
            out.write("\n");
            out.write("\\begin{itemize}\n");
            for (int k = 0; k < paramTags.length; k++) {
              out.write("\\item \\texttt{" + param[k].typeName() + " " + paramTags[k].parameterName() + "} - \n");
              out.write(paramTags[k].parameterComment() + "\n");
            }
            out.write("\\end{itemize}\n");
          }

          Tag[] returnTags = method.tags("return");
          Tag[] throwTags = method.tags("throws");
          AnnotationDesc[] override = method.annotations();
          if (type.typeName().equals("void") || returnTags.length == 0) {
            out.write("\\textbf{Kembalian}: Tidak memiliki \\textit{return value}");
            out.write("\n\n");
          } else {
            out.write("\\textbf{Kembalian}: " + returnTags[0].text());
            out.write("\n\n");
          }
          if (throwTags.length == 0) {
            out.write("\\textbf{Exception}: Tidak memiliki \\textit{exception}");
            out.write("\n\n");
          } else {
            out.write("\\textbf{Exception}: " + throwTags[0].text());
            out.write("\n\n");
          }
          if (override.length == 0) {
          } else {
            out.write("\\textbf{Override}: \\texttt{" + method.name() + "} dari kelas \\texttt{" + superclass.superclass().name() + "}");
            out.write("\n\n");
          }
        }
      }
      out.write("\\end{itemize}\n");
    } catch (IOException e) {
    }
  }
}
