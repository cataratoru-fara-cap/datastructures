package Implementations.RangeMinQuery;
import java.util.Stack;

import Data.RangeMinQuery.RMQ;

import java.util.Arrays;

/**
 * An <O(n), O(1)> implementation of the Fischer-Heun RMQ data structure.
 *
 * Une implémentation <O (n), O (1)> du RMQ utilisant une structure Fischer-Heun
 */
public class FischerHeunRMQ implements RMQ {

    SparseTableRMQ upperLevel;
    int origIdx[];
    int[] blockRMQIdx;
    PrecomputedRMQ[] blockRMQs;
    float[] origElems;
    int blockSize;

    /**
     * Creates a new FischerHeunRMQ structure to answer queries about the
     * array given by elems.
     * @elems The array over which RMQ should be computed.
     * 
     * Crée une nouvelle structure FischerHeunRMQ pour répondre aux questions sur la
     * tableau donné par elems.
     * @elems Le tableau sur lequel RMQ doit être calculé.
     */    
    public FischerHeunRMQ(float[] elems) {
        origElems = elems;

        int n = elems.length;
        if (n == 0) {
            return;
        }
        int b = (int) Math.floor( (0.25) * ( Math.log(n) / Math.log(2) ) );
        if (b == 0) {
            b = 1;
        }
        
        int arrLength = (int)Math.pow(4,b);
        blockRMQIdx = new int[n/b+1];
        blockRMQs = new PrecomputedRMQ[arrLength];
        float[] blockMins = new float [n/b+1];
        origIdx = new int[n / b + 1];
        
        for (int i = 0; i < n; i+=b) {
            int currBlockSize = Math.min(b, n-i);
            float[] subArray = Arrays.copyOfRange(elems, i, i + currBlockSize);
            int cartNum = CartesianNumber(subArray);
            blockRMQIdx[i/b] = cartNum;
            
            if (blockRMQs[cartNum] == null) {
                blockRMQs[cartNum] = new PrecomputedRMQ(subArray);
            }
            
            int minIdx = blockRMQs[cartNum].rmq(0,currBlockSize-1);
            blockMins[i/b] = subArray[minIdx];
            origIdx[i/b] = i + minIdx;
        }
        
        upperLevel = new SparseTableRMQ(blockMins);
        blockSize = b;
        
    }

    /**
     * Compute the cartesian number using a stack
     * 
     * Calculer le nombre cartésien à l'aide d'une pile
     * 
     * @param elems
     * @return
     */
    private int CartesianNumber(float[] elems) {
        int b = elems.length;
        int[] cartesianNum = new int[2*b];
        int idx = 0;
        
        // Maintenir une pile de nœuds sur l'épine droite de l'arbre.
        Stack <Float> rightSpine = new Stack <Float>();
        
        // Pour insérer un nouveau noeud:
        // - Pop la pile jusqu'à ce qu'il soit vide ou le noeud supérieur a un plus 
        //   bas valeur que la valeur actuelle.
        // - Poussez le nouveau noeud sur la pile.
        // - ajouter un 1 au nombre cartésien pour chaque push, et 0 pour chaque pop
        float topNode = 0;
        for (int i = 0; i < b; ++i) {
            float curVal = elems[i];
            if (!rightSpine.empty()) topNode = rightSpine.peek();
                
            while (topNode > curVal && !rightSpine.empty() ) {
                rightSpine.pop();
                if (!rightSpine.empty()) topNode = rightSpine.peek();
                cartesianNum[idx] = 0; idx++;
            }
            rightSpine.push(curVal);
            cartesianNum[idx] = 1; idx++;
        }
        
        // - à la fin, complétez le numéro cartésien avec 0 jusqu'à 2*b    
        while (idx < 2*b) {
            cartesianNum[idx] = 0; idx++;
        }
        
        // convertir la séquence binaire en un nombre entier
        int CN = 0;
        int one = 1;
        for (int i = 0; i < 2*b; ++i) {
            CN = CN << 1;
            if (cartesianNum[i] == 1) {
                CN = CN | one;
            } 
        }

        return CN;
    }

    private int getPrecomputedRMQ(int block, int i, int j) {
        int start = i - (block * blockSize);
        int end = j - (block * blockSize);
        return blockRMQs[blockRMQIdx[block]].rmq(start, end);
    }
    
    /**
     * Evaluates RMQ(i, j) over the array stored by the constructor, returning
     * the index of the minimum value in that range.
     * 
     * Evalue RMQ (i, j) sur le tableau stocké par le constructeur, en renvoyant
     * l'indice de la valeur minimale dans cette plage.
     */	
    public int rmq(int i, int j) {
       if (i == j) {
            return i;
        }
        
       // Use integer division to round down
       // Utilisez la division entière pour arrondir
        int iBlock = i / blockSize;
        int jBlock = j / blockSize;
        int iBlockEnd = ((iBlock + 1) * blockSize) - 1;
        int jBlockStart = jBlock * blockSize;
        
        // If i and j are in the same block or adjacent blocks use getPrecomputedRMQ
        // Si i et j sont dans le même bloc ou dans des blocs adjacents, utilisez getPrecomputedRMQ
        if (jBlock == iBlock) {
            return (iBlock * blockSize) + getPrecomputedRMQ(iBlock, i, j);
        }
        else if ((jBlock - iBlock) == 1) {  // Adjacent blocks
            int iInd = getPrecomputedRMQ(iBlock, i, iBlockEnd);
            int jInd = getPrecomputedRMQ(jBlock, jBlockStart, j);
            if (origElems[iBlock * blockSize + iInd] < origElems[jBlock * blockSize + jInd]) {
                return iBlock * blockSize + iInd;
            }
            else {
                return jBlock * blockSize + jInd;
            }
        }
        else {
            int iInd = getPrecomputedRMQ(iBlock, i, iBlockEnd);
            int jInd = getPrecomputedRMQ(jBlock, jBlockStart, j);
            
            float min;
            int minIndex;
            if (origElems[iBlock * blockSize + iInd] < origElems[jBlock * blockSize + jInd]) {
                min = origElems[iBlock * blockSize + iInd];
                minIndex = iBlock * blockSize + iInd;
            }
            else {
                min = origElems[jBlock * blockSize + jInd];
                minIndex = jBlock * blockSize + jInd;
            }
            
            int midInd = origIdx[upperLevel.rmq(iBlock + 1, jBlock - 1)];
            if (origElems[midInd] < min) {
                return midInd;
            }
            else {
                return minIndex;
            }
        }
    }
}
