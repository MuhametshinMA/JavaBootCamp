package ex01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.util.*;

public class Program {
    public static void main(String[] args) {
        Map<String, Integer> mapFirstFile = splitFileByWords(args[0]);
        Map<String, Integer> mapSecondFile = splitFileByWords(args[1]);
        Map<String, Integer> mergedMap1 = mergeMaps(mapFirstFile, mapSecondFile);
        Map<String, Integer> mergedMap2 = mergeMaps(mapSecondFile, mapFirstFile);
        Map<String, Integer> sortedMap1 = new TreeMap<>(mergedMap1);
        Map<String, Integer> sortedMap2 = new TreeMap<>(mergedMap2);
        double similarity = getSimilarity(sortedMap1, sortedMap2);
        System.out.println("Similarity = " + Math.floor(similarity * 100) / 100);
        writeDictionaryToFile(sortedMap1);
    }

    public static void writeDictionaryToFile(Map<String, Integer> map) {
        try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter("ex01/dictionary.txt"))) {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                writer.write(entry.getKey() + " ");
                writer.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static double getSimilarity(Map<String, Integer> map1, Map<String, Integer> map2) {
        List<Map.Entry<String, Integer>> entries1 = new ArrayList<>(map1.entrySet());
        List<Map.Entry<String, Integer>> entries2 = new ArrayList<>(map2.entrySet());
        int denominator = 0;
        int sumOfQuatros1 = 0;
        int sumOfQuatros2 = 0;
        for (int i = 0; i < entries1.size(); ++i) {
            Map.Entry<String, Integer> entry1 = entries1.get(i);
            Map.Entry<String, Integer> entry2 = entries2.get(i);
            denominator += entry1.getValue() * entry2.getValue();
            sumOfQuatros1 += entry1.getValue() * entry1.getValue();
            sumOfQuatros2 += entry2.getValue() * entry2.getValue();
        }
        return denominator / Math.sqrt(sumOfQuatros1 * sumOfQuatros2);
    }

    public static Map<String, Integer> mergeMaps(Map<String, Integer> destinationMap, Map<String, Integer> sourceMap) {
        Map<String, Integer> tmpMap = new HashMap<>(destinationMap);
        for (Map.Entry<String, Integer> entry : sourceMap.entrySet()) {
            if (!tmpMap.containsKey(entry.getKey())) {
                tmpMap.put(entry.getKey(), 0);
            }
        }
        return tmpMap;
    }

    public static Map<String, Integer> splitFileByWords(String filePath) {
        Map<String, Integer> wordMap = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\P{L}+");
                for (String word : words) {
                    wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return wordMap;
    }
}
