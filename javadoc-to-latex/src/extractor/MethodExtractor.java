package extractor;


import java.io.*;
import com.sun.javadoc.*;

public class MethodExtractor {

  public static void extractMethodContent(MethodDoc[] methods, BufferedWriter out) throws IOException {
    out.write("\\textit{Method-method} yang dimiliki kelas ini adalah sebagai berikut.\n");
    out.write("\\begin{itemize}\n");
    for (MethodDoc method : methods) {
      Type type = method.returnType();
      Parameter[] param = method.parameters();
      out.write("\\item \\texttt{" + method.modifiers() + " " + type.typeName() + " " + method.name() + "(");
      for (int k = 0; k < param.length; k++) {
        if (k % 2 == 0) {
          out.write(param[k].typeName() + " " + param[k].name());
        } else {
          out.write(", " + param[k].typeName() + " " + param[k].name());
        }
      }
      out.write(")}\\\\ " + "\n");
      out.write(method.commentText() + "\n\n");
      ParamTag[] paramTags = method.paramTags();
      out.write("\\textbf{Parameter:}");
      if (param.length == 0) {
        out.write("\\begin{itemize}\n");
        out.write("\\item Tidak memiliki parameter \\textit{method}\n");
        out.write("\\end{itemize}\n");
      } else {
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
      if (type.typeName().equals("void")) {
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
    }
    out.write("\\end{itemize}\n");
  }
}
