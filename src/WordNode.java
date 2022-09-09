import java.util.*;

public class WordNode {

    public Set<WordNode> links;
    public char[] word;

    public WordNode(char[] word){
        this.links = new HashSet<>();
        this.word = word;
    }

    //Returns true if there is a border and adds it
    public boolean checkBorders(WordNode otherNode){
        if(borders(this.word, otherNode.word) ){
            this.links.add(otherNode);
            otherNode.links.add(this);
            return true;
        }
        return false;
    }

    //Pre: word1 != word2
    private static boolean borders(char[] word1, char[] word2){
        if(word1.length != word2.length){
            return false;
        }
        int differences = 0;
        for(int i = 0; i < word1.length; i++){
            if(word1[i] != word2[i]){
                differences++;
            }
        }
        return differences == 1;
    }

    @Override
    public String toString(){
        if(this.links.isEmpty() ){
            return new String(this.word) + " borders NOTHING";
        }
        StringBuilder temp = new StringBuilder();
        temp.append(this.word);
        temp.append(" borders ");
        for(WordNode node : this.links){
            temp.append(node.word);
            temp.append(", ");
        }
        temp.delete(temp.length() - 2, temp.length() - 1);
        return temp.toString();
        //return (new String(this.word) ) + " borders " + this.links.toString();
    }

    /*
    //Doesn't use override and Object version of equals because will only ever be used with two different WordNodes
    public boolean equals(WordNode otherNode){
        for(int i = 0; i < this.word.length; i++){
            if(this.word[i] != otherNode.word[i]){
                return false;
            }
        }
        return true;
    } */

}
