// My solution to the following problem from Hackerrang:
//   https://www.hackerrank.com/challenges/t9-predictions/
//
// The task is to return the top 5 word suggestions for a
// given T9 (mobile phone) sequence, using a specified corpus.

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        // Map storing the number of times each word occurs.
        Map<String, Integer> word_counts = new HashMap<String, Integer>();
        // Trie to access all words for a given T9 prefix.
        Trie trie = new Trie();
        
        // Read dictionary
        File dictionary = new File("t9Dictionary");
        
        try {
            Scanner scan_dict = new Scanner(dictionary);
            int n = scan_dict.nextInt();
            
            // Add each word to the trie and the word_counts map and set its count to 1.
            for (int i = 0; i < n; i++) {
                String word = scan_dict.next();
                word_counts.put(word, 1);
                Trie.addWord(word, trie);
            }
            
            scan_dict.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        // Read corpus
        File corpus = new File("t9TextCorpus");
        
        try {
            Scanner scan_corpus = new Scanner(corpus);
            
            while (scan_corpus.hasNext()) {
                String line = scan_corpus.next();
                if (line.equals("END-OF-CORPUS"))
                    break;
                
                // Regex that matches valid words.
                Pattern regex = Pattern.compile("[a-zA-Z][a-zA-Z'-]*[a-zA-Z]");
                Matcher matcher = regex.matcher(line);
                
                // All matched words of a line get their count increased.
                while (matcher.find()) {
                    String word = matcher.group();
                    if (word_counts.containsKey(word))
                        word_counts.put(word, word_counts.get(word) + 1);
                }
            }
            
            scan_corpus.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        // Read T9 prefixes and output suggestions
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        
        for (int i = 0; i < t; i++) {
            String n = scan.next();
            // Get all possible words.
            List<String> options = trie.getWords(n);
            
            // Get word count information for the possible words.
            Map<String,Integer> suggestions = new HashMap<String,Integer>();
            for (String option : options) {
                suggestions.put(option, word_counts.get(option));
            }
            
            // Sort the suggestions by word count, break ties with
            // the words' lexicographical order.
            ArrayList<Map.Entry<String,Integer>> top = 
                new ArrayList<Map.Entry<String,Integer>>(suggestions.entrySet());
            Collections.sort(top, new Comparator<Map.Entry<String,Integer>>() {
                public int compare(Map.Entry<String,Integer> entry1,
                                   Map.Entry<String,Integer> entry2) {
                    if (entry1.getValue() < entry2.getValue())
                        return 1;
                    else if (entry1.getValue() > entry2.getValue())
                        return -1;
                    else
                        return entry1.getKey().compareTo(entry2.getKey());
                }
            });
            
            // Return the top 5 suggestions.
            for (int j = 0; j < Math.min(5, top.size()); j++) {
                System.out.print((j == 0 ? "" : ";") + 
                                 top.get(j).getKey());
            }
            
            if (top.size() == 0)
                System.out.print("No suggestions");
            
            System.out.println();
        }
        
        scan.close();
    }
    
    // For a given word, returns its T9 representation as a string.
    public static String StringToT9(String word) {
        word = word.toLowerCase();
        StringBuilder sb = new StringBuilder(word.length());
        
        // Loop through each character and convert it to its T9 digit.
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int t9;
            
            if (c < 'a' || c > 'z')
                return sb.toString();
            else if (c >= 'a' && c <= 'c')
                t9 = 2;
            else if (c >= 'd' && c <= 'f')
                t9 = 3;
            else if (c >= 'g' && c <= 'i')
                t9 = 4;
            else if (c >= 'j' && c <= 'l')
                t9 = 5;
            else if (c >= 'm' && c <= 'o')
                t9 = 6;
            else if (c >= 'p' && c <= 's')
                t9 = 7;
            else if (c >= 't' && c <= 'v')
                t9 = 8;
            else // if (c >= 'w' && c <= 'z')
                t9 = 9;
            
            sb.append(t9);
        }
        
        return sb.toString();
    }
    
    // A basic Trie (prefix tree).
    // For the sake of this exercice, all members are public.
    public static class Trie {
        // Contains words possible with the current T9 sequence
        public ArrayList<String> words;
        // Up to 10 child tries (1 for each digit).
        public Trie[] children;
        
        // Constructor - initialise members.
        Trie() {
            words = new ArrayList<String>();
            children = new Trie[10];
        }
        
        // Returns all possible words of a trie for a given
        // T9 prefix (including from sub-tries).
        public List<String> getWords(String t9) {
            Trie trie = findPrefix(t9, this);
            return trie.getWords();
        }
        
        // Recursively returns all possible words of a trie and its sub-tries.
        private List<String> getWords() {
            List<String> allWords = new ArrayList<String>();
            allWords.addAll(this.words);
            
            for (Trie child : this.children) {
                if (child != null)
                    allWords.addAll(child.getWords());
            }
            
            return allWords;
        }
        
        // Adds a word to a trie.
        // An iterative static method was easier here.
        public static void addWord(String word, Trie trie) {
            String t9 = StringToT9(word);
            trie = findPrefix(t9, trie);
            trie.words.add(word);
        }
        
        // Returns the sub-trie for a given T9 prefix.
        // An iterative static method was easier here.
        private static Trie findPrefix(String t9, Trie trie) {
            for (int i = 0; i < t9.length(); i++) {
                int digit = Integer.parseInt(t9.substring(i, i+1));
                if (trie.children[digit] == null)
                    trie.children[digit] = new Trie();
                trie = trie.children[digit];
            }
            
            return trie;
        }
    }
}
