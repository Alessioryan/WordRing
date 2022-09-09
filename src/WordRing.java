import java.util.*;
import java.io.*;

public class WordRing {

    public Map<char[], WordNode> wordNodeMap;

    public static void main(String[] args) throws FileNotFoundException{
        WordRing main = new WordRing("mainDicc.txt", 4);
        for(WordNode node : main.wordNodeMap.values() ){
            System.out.println(node.toString() );
        }
    }

    //Must define word length
    public WordRing(String fileName, int length) throws FileNotFoundException {
        this.wordNodeMap = new HashMap<>();
        Scanner scanner = new Scanner(new File(fileName));
        while (scanner.hasNext()) {
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
    public List<WordNode> findRing(String startingWord, int length){
        return findRingHelper(startingWord.toCharArray(), startingWord.toCharArray(), length, new ArrayList<>());
    }

    //DFS
    private List<WordNode> findRingHelper(char[] startingWord, char[] word, int length, List<WordNode> list){
        return null;
    }

}
