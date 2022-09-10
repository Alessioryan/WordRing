import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

public class WordRing {

    public int wordLength;
    public Map<char[], WordNode> wordNodeMap;

    public static void main(String[] args) throws FileNotFoundException{
        WordRing main = new WordRing("mainDicc.txt", 4);
        for(List<WordNode> wordNodeList : main.findRings("cool", 100) ){
            System.out.println(wordRingToString(wordNodeList) );
        }
    }

    //Must define word length
    public WordRing(String fileName, int length) throws FileNotFoundException {
        this.wordLength = length;
        this.wordNodeMap = new HashMap<>();
        Scanner scanner = new Scanner(new File(fileName));
        while (scanner.hasNext() ) {
            char[] word = scanner.next().toCharArray();
            if(word.length != length){
                continue;
            }
            WordNode node = new WordNode(word);
            for (WordNode otherNode : this.wordNodeMap.values() ) {
                node.checkBorders(otherNode);
            }
            this.wordNodeMap.put(word, node);
        }
    }

    //Finds a word ring given a starting word and a length
    //The length does not double count the starting word
    //For now only returns one
    public Set<List<WordNode>> findRings(String startingWord, int length){
        if(startingWord.length() != this.wordLength){
            System.out.println("Incorrect word length");
            return new HashSet<>();
        }
        WordNode start = null;
        for(char[] word : this.wordNodeMap.keySet() ){
            if(Arrays.equals(word, startingWord.toCharArray() ) ){
                start = this.wordNodeMap.get(word);
                break;
            }
        }
        if(start == null){
            System.out.println("Word not present");
            return new HashSet<>();
        }
        ArrayList<WordNode> list = new ArrayList<>();
        list.add(start);
        Set<List<WordNode>> rings = new HashSet<>();
        findRingHelper(startingWord.toCharArray(), length, list, rings);
        if(rings.size() == 0) {
            System.out.println("No rings found");
            return new HashSet<>();
        } else {
            return rings;
        }
    }

    //DFS
    private static void findRingHelper(char[] startingWord, int length, List<WordNode> list, Set<List<WordNode>> rings){
        if(length == 0){
            return;
        }
        for(WordNode node : list.get(list.size() - 1).links){
            //Can change later if we want more rings
            if(rings.size() > 0){
                break;
            }
            if(list.contains(node) ){
                if(length == 1 && Arrays.equals(startingWord, node.word) ){
                    rings.add(new ArrayList<>(list) );
                }
                continue;
            }
            list.add(node);
            findRingHelper(startingWord, length - 1, list, rings);
            list.remove(node);
        }
    }

    public static String wordRingToString(List<WordNode> wordNodeList){
        if(wordNodeList.isEmpty() ){
            return "No loops found";
        }
        StringBuilder temp = new StringBuilder();
        for(WordNode node : wordNodeList){
            temp.append(node.word);
            temp.append(", ");
        }
        temp.append(wordNodeList.get(0).word);
        return temp.toString();
    }

    public String findBorderingWords(String word){
        for(WordNode node : this.wordNodeMap.values() ){
            if(Arrays.equals(node.word, word.toCharArray() ) ){
                return node.toString();
            }
        }
        return "Word not found";
    }

}
