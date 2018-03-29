package extractor;

import com.sun.javadoc.*;
import java.io.*;

class ClassExtractor {

  public static void extractClassContent(ClassDoc[] classes, BufferedWriter out) throws IOException {
    for (ClassDoc classDoc : classes) {
      out.write("\\item \\texttt{" + classDoc.name() + "}\\\\ \n");
      out.write(classDoc.commentText() + "\n\n");
      
//      SeeTag[] see = classDoc.seeTags();
//      System.out.println("see also: " + see[0].text());
      
//      Tag[] tags = classDoc.firstSentenceTags();
//      System.out.println("link tag: " + tags[0].toString());
      
      FieldDoc[] fields = classDoc.fields(false);
      AttributeClassExtractor.extractAttributeClassContent(fields, out);
      
      MethodDoc[] methods = classDoc.methods(false);
      MethodExtractor.extractMethodContent(methods, out);
    }
  }
}
