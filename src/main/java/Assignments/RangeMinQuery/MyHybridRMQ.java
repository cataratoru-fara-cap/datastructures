package Assignments.RangeMinQuery;

import Data.RangeMinQuery.RMQ;
import Implementations.RangeMinQuery.SparseTableRMQ;

/**
 * An <O(n), O(log n)> implementation of the RMQ as a hybrid between
 * the sparse table (on top) and no-precomputation structure (on bottom)
 * 
 * Une implémentation <O (n), O (log n)> du RMQ en tant qu'hybride entre
 * la table clairsemée (en haut) et la structure sans précalcul (en bas)
 *
 */
public class MyHybridRMQ implements RMQ {
    SparseTableRMQ upperLevel;
    int blockSize;
    float[] origElems;
    int[] origIndices;
    
    /**
     * Creates a new HybridRMQ structure to answer queries about the
     * array given by elems.
     * @elems The array over which RMQ should be computed.
     * 
     * Crée une nouvelle structure HybridRMQ pour répondre aux questions sur la
     * tableau donné par elems.
     * @elems Le tableau sur lequel RMQ doit être calculé.
     */
    public MyHybridRMQ(float[] elems) {
        origElems = elems;
        if (elems.length <= 1) {
            return;
        }
        
        blockSize = logBase2(elems.length);
        float[] topElems = new float[elems.length / blockSize + 1];
        origIndices = new int[elems.length / blockSize + 1];
        int block = 0;
        float min = elems[0];
        int minIndex = 0;

        // compute topElems containing the minimum from each block
        // calculer les topElems contenant le minimum de chaque bloc
        // TODO: implémenter la fonction de prétraitement
        
        // create a SparseTableRMQ using the top elements
        // créer un SparseTableRMQ en utilisant les éléments du haut
        // TODO: 
        upperLevel = null;
    }
    
    /**
     * Implements a NaiveRMQ
     * Implémenter un NaiveRMQ
     * @param i
     * @param j
     * @return
     */
    private int linearScanRMQ(int i, int j) {
		// TODO: Implémenter un NaiveRMQ
		return -1;
    }

    private int logBase2(int num) {
        return (int)(Math.log(num) / Math.log(2));
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
        
        // If i and j are in the same block or adjacent blocks use linearScanRMQ
        // Si i et j sont dans le même bloc ou dans des blocs adjacents, utilisez linearScanRMQ
        //TODO
        
        // otherwise use linearScanRMQ for the left/right intervals and SparseRMQ for the 
        // middle intervals. Return the minimum of these three results
        // sinon, utilisez linearScanRMQ pour les intervalles gauche / droit et SparseRMQ pour 
        // les intervalles intermédiaires. Renvoyer le minimum de ces trois résultats
        // TODO
        return -1;
    }
}
