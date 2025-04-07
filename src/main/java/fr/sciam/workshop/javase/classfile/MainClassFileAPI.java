package fr.sciam.workshop.javase.classfile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.Thread.Builder;
import java.lang.classfile.AccessFlags;
import java.lang.classfile.ClassBuilder;
import java.lang.classfile.ClassElement;
import java.lang.classfile.ClassFile;
import java.lang.classfile.ClassFile.LineNumbersOption;
import java.lang.classfile.ClassFile.StackMapsOption;
import java.lang.classfile.ClassFileBuilder;
import java.lang.classfile.ClassModel;
import java.lang.classfile.ClassTransform;
import java.lang.classfile.CodeBuilder;
import java.lang.classfile.CodeElement;
import java.lang.classfile.CodeModel;
import java.lang.classfile.CodeTransform;
import java.lang.classfile.FieldModel;
import java.lang.classfile.FieldTransform;
import java.lang.classfile.Instruction;
import java.lang.classfile.MethodElement;
import java.lang.classfile.MethodModel;
import java.lang.classfile.MethodTransform;
import java.lang.classfile.attribute.ConstantValueAttribute;
import java.lang.classfile.attribute.ExceptionsAttribute;
import java.lang.classfile.constantpool.ClassEntry;
import java.lang.classfile.constantpool.ConstantPool;
import java.lang.classfile.instruction.FieldInstruction;
import java.lang.classfile.instruction.InvokeInstruction;
import java.lang.constant.ClassDesc;
import java.lang.constant.ConstantDescs;
import java.lang.constant.MethodTypeDesc;
import java.lang.reflect.AccessFlag;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HexFormat;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class MainClassFileAPI {

  public static void main(String[] args) throws IOException {

    System.out.println("Analyse d'un fichier de classe");
    analyserFichierDeClasse();
    System.out.println();

    System.out.println("Analyse d'un fichier de classe avec le Pattern Matching");
    analyserAvecPatternMatching();
    System.out.println();

    System.out.println("Analyse d'une méthode");
    analyserMethode();
    System.out.println();

    System.out.println("Écriture d'un fichier de classe");
    ecrireFichierDeClasse();
    System.out.println();

    ecrireFichierDeClasseAvancee();
    ecrireFichierDeClasseHelloWorld();
    transformerFichierDeClasse();
  }

  private static void analyserFichierDeClasse() throws IOException {

  }

  private static void analyserAvecPatternMatching() throws IOException {

  }

  private static void analyserMethode() throws IOException {

  }

  private static void ecrireFichierDeClasse() {

  }

  private static void ecrireFichierDeClasseAvancee() {

  }

  private static void ecrireFichierDeClasseHelloWorld() {

  }

  private static void transformerFichierDeClasse() throws IOException {

  }

  /**
   * Méthode utilitaire pour obtenir le chemin d'un type donné
   */
  public static Path obtenirChemin(Class<?> clazz) {
    String classResource = clazz.getName().replace('.', File.separatorChar) + ".class";
    URL url = clazz.getClassLoader().getResource(classResource);
    if (url == null) {
      throw new IllegalStateException("Fichier de classe introuvable pour : " + clazz.getName() + " " + classResource);
    }

    try {
      return Paths.get(url.toURI());
    } catch (URISyntaxException e) {
      throw new IllegalStateException("URI invalide", e);
    }
  }

  /**
   * Méthode utilitaire pour sauvegarder un fichier de classe dans le répertoire classfile/
   */
  public static void sauvegarderFichier(byte[] bytes, String relativePath) {
    try {
      Path targetDir = Path.of("classfile");
      Path fullDirPath = targetDir.resolve(Paths.get(relativePath).getParent());
      Files.createDirectories(fullDirPath);
      Path filePath = targetDir.resolve(relativePath);
      Files.write(filePath, bytes, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
