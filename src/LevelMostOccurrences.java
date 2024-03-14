import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Class which represents the level of the most occurrences of a specific number.
 */
public class LevelMostOccurrences {

    /**
     * scan the binary tree according to BFS, counts number of times that a number appears in a same level memorizes
     * in which level there are the most occurrences of the number.
     *
     * @param node : node of the tree
     * @param num : the specific number
     * @return the less deep level in which the number occurred the most time, return -1 if the number isn't in the tree.
     */

    public static int getLevelWithMostOccurrences(BinNode<Integer> node, int num) {
        Queue<BinNode> parents = new ArrayDeque<>(); // The arrayDeque of the parents.
        Queue<BinNode> children = new ArrayDeque<>(); //The arrayDeque of the children.
        parents.add(node);
        int level = 0;
        int levelMaxOccurrences = -1;
        int numberMaxOccurrences = 0; // The number of times that a number appears in a same level.
        int countOccurrences = 0;
        int size = 1; // size of the ArrayDeque of the parents of a same level.
        if(node.getData() == num){
            numberMaxOccurrences = 1;
            levelMaxOccurrences = 0;
        }
        while (!parents.isEmpty()) {
            BinNode p = parents.remove();
            size--;
            if(p.getLeft() != null) {
                children.add(p.getLeft());
            }
            if(p.getRight() != null) {
                children.add(p.getRight());
            }
            while(!children.isEmpty()){
                BinNode c = children.remove();
                if(c.getData().equals(num)){
                    countOccurrences++;
                    if(countOccurrences > numberMaxOccurrences){
                        numberMaxOccurrences = countOccurrences;
                        levelMaxOccurrences = level + 1;
                    }
                }
                if(c.getLeft() != null || c.getRight() != null){
                    parents.add(c);
                }
            }
            if(size == 0){
                level++;
                countOccurrences = 0;
                size = parents.size();
            }
        }
        return levelMaxOccurrences;
    }
}