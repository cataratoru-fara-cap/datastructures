package Assignments.RangeMinQuery;

import Data.RangeMinQuery.RMQ;

/**
 * An <O(n log n), O(1)>; implementation of RMQ that uses a sparse table
 * to do lookups efficiently.
 * 
 * Une <O (n log n), O (1)> implémentation de RMQ utilisant une table fragmentée
 * pour faire des recherches efficacement.
 *
 */
public class MySparseTableRMQ implements RMQ {
    int[][] sparseTable;
    int[] fastLogs;
    float[] origElems;
    
    /**
     * Creates a new SparseTableRMQ structure to answer queries about the
     * array given by elems.
     * @elems The array over which RMQ should be computed.
     * 
     * Crée une nouvelle structure MyNaiveRMQ pour répondre aux questions sur la
     * tableau donné par elems.
     * @elems Le tableau sur lequel RMQ doit être calculé.
     */
    public MySparseTableRMQ(float[] elems) {
        origElems = elems;
        
        int length = elems.length;
        populateFastLogs(length);
        
        sparseTable = new int[length][fastLogs[length] + 1];
        
        /*
         *  Initialize for the intervals with length 1
         *  initialiser M pour les intervalles de longueur 1
         */

        // TODO: implémenter la fonction de prétraitement 
        
        // Compute values from smaller to bigger intervals
        // Calculer des valeurs d'intervalles plus petits à plus grands

        // TODO: implémenter la fonction de prétraitement 
    
    }
    
    private void populateFastLogs(int length) {
        fastLogs = new int[length + 1];
        int counter = 0;
        int log = 0;
        int num = 1;
        
        fastLogs[0] = 0;
        
        for (int i = 1; i < fastLogs.length; i++) {
            counter++;
            fastLogs[i] = log;
            if (counter == num) {
                log++;
                num *= 2;
                counter = 0;
            }
        }
    }

    /**
     * Evaluates RMQ(i, j) over the array stored by the constructor, returning
     * the index of the minimum value in that range.
     * 
     * Evalue RMQ (i, j) sur le tableau stocké par le constructeur, en renvoyant
     * l'indice de la valeur minimale dans cette plage.
     */	
    public int rmq(int i, int j) {
        // TODO: implémenter RMQA(i,j)
    	return -1;
    }
}
