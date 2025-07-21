import java.util.*;
import java.io.*;

public class ML_DESIGN {
    public static void main(String[] args) throws IOException {
        String sentence = "Everyone loves him because he is honest";
        sentence = sentence.toLowerCase().replaceAll("[^a-z]", "");

        List<Character> vowels = new ArrayList<>();
        List<Character> consonants = new ArrayList<>();

        Set<String> validWords = new HashSet<>();
        Set<String> resultWords = new HashSet<>();

        String vowelsStr = "aeiou";

        // Separate vowels and consonants
        for (char c : sentence.toCharArray()) {
            if (vowelsStr.indexOf(c) >= 0) {
                vowels.add(c);
            } else {
                consonants.add(c);
            }
        }

        System.out.println("Vowels: " + vowels);
        System.out.println("Consonants: " + consonants);

        // Load dictionary words
        Scanner scanner = new Scanner(new File("src/main/resources/DATASET/dictionary.txt")); // Assume you have a dictionary file
        while (scanner.hasNext()) {
            String word = scanner.next().toLowerCase();
            validWords.add(word);
        }

        // Count available characters
        Map<Character, Integer> charCount = new HashMap<>();
        for (char c : sentence.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }

        // Check which words can be formed
        for (String word : validWords) {
            if (canFormWord(word, new HashMap<>(charCount))) {
                resultWords.add(word);
            }
        }

        System.out.println("Total valid words: " + resultWords.size());
        System.out.println("Some valid words: ");
        resultWords.stream().limit(resultWords.size()).forEach(System.out::println); // Print first 50 words
    }

    public static boolean canFormWord(String word, Map<Character, Integer> available) {
        for (char c : word.toCharArray()) {
            if (!available.containsKey(c) || available.get(c) == 0) {
                return false;
            }
            available.put(c, available.get(c) - 1);
        }
        return true;
    }
}
