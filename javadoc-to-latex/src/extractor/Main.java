package extractor;


import com.sun.javadoc.*;
import java.io.*;

public class Main {
  
  private static FileWriter file;
  private static BufferedWriter out;

  public static boolean start(RootDoc root) throws IOException {
    init(root.classes());
    return true;
  }

  static void init(ClassDoc[] classes) throws IOException {
    file = new FileWriter("/Users/abathz/Documents/KULIAH/Skripsi/docTest.tex");
    out = new BufferedWriter(file);
    out.write("\\documentclass{article}" + "\n");
    out.write("\\title{\\textbf{Konversi Javadoc ke \\LaTeX}}" + "\n");
    out.write("\\author{Adli Fariz Bonaputra \\\\ Teknik Informatika Unpar}" + "\n");
    out.write("\\begin{document}" + "\n" + "\\maketitle" + "\n");
    out.write("\\section{Diagram Kelas Rinci}" + "\n");
    out.write("\\begin{enumerate}\n");
    
    ClassExtractor.extractClassContent(classes, out);
    
    out.write("\\end{enumerate}" + "\n");
    out.write("\\end{document}" + "\n");
    out.close();
  }

}
