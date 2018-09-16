package extractor;

import com.sun.javadoc.*;
import java.io.*;

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
   * @param methods    sebuah array berisikan sejumlah \textit{method} dari kelas
   * @param out        turunan dari kelas \texttt{Writer} yang digunakan untuk menulis
   *                   file text
   */
  public static void extractMethodClassContent(ClassDoc superclass, MethodDoc[] methods, BufferedWriter out) {
    try {
      if (methods.length == 0) {
        out.write("Kelas ini tidak memiliki method. ");
      } else {
        out.write("\\textit{Method-method} yang dimiliki kelas ini adalah sebagai berikut.\n");
        out.write("\\begin{itemize}\n");
        for (MethodDoc method : methods) {
          if (method.overriddenMethod() != null) {
            MethodDoc overrideMethod = method.overriddenMethod();
            Type type = overrideMethod.returnType();
            Parameter[] paramOverrideMethod = overrideMethod.parameters();
            out.write("\\item \\texttt{" + overrideMethod.modifiers() + " " + type.typeName() + " " + overrideMethod.name() + "(");
            for (int k = 0; k < paramOverrideMethod.length; k++) {
              Type typeParam = paramOverrideMethod[k].type();
              out.write(typeParam.toString() + " " + paramOverrideMethod[k].name());
              if (k < paramOverrideMethod.length - 1) {
                out.write(", ");
              }
            }
            out.write(")}");

            Tag[] inlineTags = overrideMethod.inlineTags();
            for (int i = 0; i < inlineTags.length; i++) {
              if (i == 1) {
                out.write("\\texttt{" + inlineTags[i].text().replace("#", "") + "}");
              } else {
                out.write(inlineTags[i].text().replace("#", ""));
              }
            }

            out.write("\n\n");

            ParamTag[] paramTags = overrideMethod.paramTags();
            ParameterMethod(out, paramOverrideMethod, paramTags);

            Tag[] returnTags = overrideMethod.tags("return");
            Tag[] throwTags = overrideMethod.tags("throws");
            AnnotationDesc[] override = overrideMethod.annotations();
            ReturnTypeMethod(out, type, returnTags);
            ExceptionMethod(out, throwTags);
            if (override.length != 0) {
              out.write("\\textbf{Override}: \\texttt{" + overrideMethod.name() + "} dari kelas \\texttt{" + superclass.superclass().name() + "}");
              out.write("\n\n");
            }
          } else {
            Type type = method.returnType();
            Parameter[] paramMethod = method.parameters();
            out.write("\\item \\texttt{" + method.modifiers() + " " + type.typeName() + " " + method.name() + "(");
            for (int k = 0; k < paramMethod.length; k++) {
              Type typeParam = paramMethod[k].type();
              out.write(typeParam.toString() + " " + paramMethod[k].name());
              if (k < paramMethod.length - 1) {
                out.write(", ");
              }
            }
            out.write(")}");

            Tag[] inlineTags = method.inlineTags();
            for (int i = 0; i < inlineTags.length; i++) {
              if (i == 1) {
                out.write("\\texttt{" + inlineTags[i].text().replace("#", "") + "}");
              } else {
                out.write(inlineTags[i].text().replace("#", ""));
              }
            }

            out.write("\n\n");

            ParamTag[] paramTags = method.paramTags();
            ParameterMethod(out, paramMethod, paramTags);

            Tag[] returnTags = method.tags("return");
            Tag[] throwTags = method.tags("throws");
            AnnotationDesc[] override = method.annotations();
            ReturnTypeMethod(out, type, returnTags);
            ExceptionMethod(out, throwTags);
            if (override.length != 0) {
              out.write("\\textbf{Override}: \\texttt{" + method.name() + "} dari kelas \\texttt{" + superclass.superclass().name() + "}");
              out.write("\n\n");
            }
          }
        }
        out.write("\\end{itemize}\n");
      }
    } catch (IOException e) { }
  }

  /**
   * \textit{Method} ini akan menampilkan parameter \textit{method-method} yang dimiliki
   * oleh sebuah kelas
   *
   * @param out                    turunan dari kelas \texttt{Writer} yang digunakan untuk menulis file text
   * @param paramMethod            sebuah array berisikan sejumlah \textit{method} dari kelas
   * @param paramTags              sebuah array berisikan sejumlah parameter \textit{method} dari kelas
   */
  private static void ParameterMethod(BufferedWriter out, Parameter[] paramMethod, ParamTag[] paramTags){
    try {
      if (paramTags.length == 0) {
        out.write("\\textbf{Parameter:}\n");
        out.write("\\begin{itemize}\n");
        if (paramMethod.length != 0) {
          for (int i = 0; i < paramMethod.length; i++) {
            out.write("\\item \\texttt{" + paramMethod[i].toString() + "} - \n");
          }
        } else {
          out.write("\\item Tidak memiliki parameter \\textit{method}\n");
        }
        out.write("\\end{itemize}\n");
      } else {
        out.write("\\textbf{Parameter:}\n");
        out.write("\\begin{itemize}\n");
        for (int k = 0; k < paramTags.length; k++) {
          Type type = paramMethod[k].type();
          out.write("\\item \\texttt{" + type.typeName() + " " + paramTags[k].parameterName() + "} - \n");
          Tag[] inlineTagsInParameter = paramTags[k].inlineTags();
          for (int i = 0; i < inlineTagsInParameter.length; i++) {
            if (i == 1) {
              out.write("\\texttt{" + inlineTagsInParameter[i].text().replace("#", "") + "}");
            } else {
              out.write(inlineTagsInParameter[i].text());
            }
          }
          out.write("\n");
        }
        out.write("\\end{itemize}\n");
      }
    } catch (IOException e) { }
  }

  /**
   * \textit{Method} ini akan menampilkan \textit{return type} dari \textit{method-method} yang dimiliki
   * oleh sebuah kelas
   *
   * @param out          turunan dari kelas \texttt{Writer} yang digunakan untuk menulis file text
   * @param type         sebuah objek Type
   * @param returnTags   sebuah array berisikan sejumlah \textit{return type} dari \textit{method} dari kelas
   */
  private static void ReturnTypeMethod(BufferedWriter out, Type type, Tag[] returnTags){
    try {
      if (type.typeName().equals("void") || returnTags.length == 0) {
        out.write("\\textbf{Return Value}: Tidak memiliki \\textit{return value}\n\n");
      } else {
        out.write("\\textbf{Return Value}: ");
        Tag[] inlineTagsInReturnValue = returnTags[0].inlineTags();
        for (int i = 0; i < inlineTagsInReturnValue.length; i++) {
          if (i == 1) {
            out.write(inlineTagsInReturnValue[i].text().replace("#", ""));
          } else {
            out.write(inlineTagsInReturnValue[i].text().replace("{#link", "").replace("}", "").replace("#", ""));
          }
        }
        out.write("\n\n");
      }
    } catch (IOException e) { }
  }

  /**
   * \textit{Method} ini akan menampilkan \textit{return type} dari \textit{method-method} yang dimiliki
   * oleh sebuah kelas
   *
   * @param out          turunan dari kelas \texttt{Writer} yang digunakan untuk menulis file text
   * @param throwTags    sebuah array berisikan sejumlah \textit{exception} dari \textit{method} dari kelas
   */
  private static void ExceptionMethod(BufferedWriter out, Tag[] throwTags) throws IOException {
    try {
      if (throwTags.length == 0) {
        out.write("\\textbf{Exception}: Tidak memiliki \\textit{exception}");
        out.write("\n\n");
      } else {
        out.write("\\textbf{Exception}: " + throwTags[0].text());
        out.write("\n\n");
      }
    } catch (IOException e) { }
  }
}
