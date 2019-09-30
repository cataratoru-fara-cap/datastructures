package Implementations.RangeMinQuery;

import Data.RangeMinQuery.RMQ;

/**
 * An <O(n log n), O(1)> implementation of RMQ that uses a sparse table
 * to do lookups efficiently.
 * 
 * Une <O (n log n), O (1)> implémentation de RMQ utilisant une table fragmentée
 * pour faire des recherches efficacement.
 *
 */
public class SparseTableRMQ implements RMQ {
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
    public SparseTableRMQ(float[] elems) {
        origElems = elems;
        
        int length = elems.length;
        populateFastLogs(length);
        
        sparseTable = new int[length][fastLogs[length] + 1];
        
        // Initialize for the intervals with length 1
        // initialiser M pour les intervalles de longueur 1
        for (int i = 0; i < length; i++) {
            sparseTable[i][0] = i;
        }
        
        // Compute values from smaller to bigger intervals
        // Calculer des valeurs d'intervalles plus petits à plus grands
        for (int j = 1; (1 << j) <= length; j++) {
            for (int i = 0; (i + (1 << j) - 1) < length; i++) {
                int sparse1 = sparseTable[i][j - 1];
                int sparse2 = sparseTable[i + (1 << (j - 1))][j - 1];
                if (elems[sparse1] < elems[sparse2]) {
                    sparseTable[i][j] = sparse1;
                }
                else {
                    sparseTable[i][j] = sparse2;
                }
            }
        }        
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
        int log = fastLogs[j - i + 1];
        int sparse1 = sparseTable[i][log];
        int sparse2 = sparseTable[j - (int)Math.pow(2, log) + 1][log];
        if (origElems[sparse1] < origElems[sparse2]) {
            return sparse1;
        }
        return sparse2;
    }
}
