import os
directory = "."
extension = ".java"
files = [file for file in os.listdir("Lampiran/siamodels") if file.lower().endswith(extension)]

for file in files:
   print r"\lstinputlisting[language=java]{%s}" % file

