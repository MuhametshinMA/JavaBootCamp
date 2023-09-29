package ex00;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.LinkedList;
import java.io.File;
import java.io.IOException;

public class Program {
  public static void main(String[] args) {

   String fileSignal = "/Users/littleca/Java_Bootcamp.Day02-1/src/ex00/signal.txt"; // find abs path function

    Map<String, String> mapSignatures = readFileSignatures(fileSignal);
    LinkedList<String> listSign = new LinkedList<String>();
    Scanner sc = new Scanner(System.in);

    while (true) {
      System.out.print("->");
      String optStr = sc.nextLine();
      if (optStr.equals("42")) {
        break;
      }
      try {
        String sigStr = getSignatureFromFile(optStr, 8);
        String fileExp = getExpansionFromFile(sigStr, mapSignatures);
        listSign.add(fileExp);
        System.out.println("PROCESSED");
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
    try {
      addSigToFile("result.txt", listSign);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public static void addSigToFile(String fileName, LinkedList<String> listSign) throws IOException {
    FileOutputStream fos = new FileOutputStream(fileName);

    for (String sign : listSign) {
      sign += "\n";
      byte[] bytes = sign.getBytes();
      fos.write(bytes);
    }
  }

  public static String getExpansionFromFile(String signatureFile, Map<String, String> mapSignatures)
      throws NoSuchElementException {
    for (Map.Entry<String, String> entry : mapSignatures.entrySet()) {
      if (signatureFile.indexOf(entry.getValue()) == 0) {
        return entry.getKey();
      }
    }
    throw new NoSuchElementException("Key not found");
  }

  public static String getSignatureFromFile(String filePath, int byteCount) {
    StringBuilder sigBuilder = new StringBuilder();
    try (FileInputStream fis = new FileInputStream(filePath)) {
      int byteRead;
      int byteCountTmp = 0;
      while ((byteRead = fis.read()) != -1 && byteCountTmp < byteCount) {
        String hexString = String.format("%02X ", byteRead);
        sigBuilder.append(hexString);
        ++byteCountTmp;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return sigBuilder.toString();
  }

  public static Map<String, String> readFileSignatures(String sourcePath) {
    Map<String, String> signatures = null;
    try (BufferedReader reader = new BufferedReader(
        new InputStreamReader(new FileInputStream(sourcePath)))) {
      signatures = new HashMap<String, String>();
      String line;
      while ((line = reader.readLine()) != null) {
        String[] words = line.split(", ");
        signatures.put(words[0], words[1]);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return signatures;
  }
}
