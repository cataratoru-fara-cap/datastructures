package Implementations.RangeMinQuery;

import Data.RangeMinQuery.RMQ;

/**
 * An <O(n^2), O(1)> implementation of RMQ that precomputes the
 * value of RMQ_A(i, j) for all possible i and j.
 * 
 * Une implémentation <O (n ^ 2), O (1)> de RMQ qui calcule le
 * valeur de RMQ_A (i, j) pour tous les i et j possibles.
 */
public class PrecomputedRMQ implements RMQ {
	int[][] precomputed;
	
    /**
     * Creates a new PrecomputedRMQ structure to answer queries about the
     * array given by elems.
     * @elems The array over which RMQ should be computed.
     * 
     * Crée une nouvelle structure PrecomputedRMQ pour répondre aux questions sur la
     * tableau donné par elems.
     * @elems Le tableau sur lequel RMQ doit être calculé.
     */
    public PrecomputedRMQ(float[] elems) {
    	precomputed = new int[elems.length][elems.length];
        
        for (int i = 0; i < elems.length; i++) {
            precomputed[i][i] = i;
        }
        
        for (int i = 0; i < elems.length; i++) {
            for (int j = i + 1; j < elems.length; j++) {
                if (elems[precomputed[i][j - 1]] < elems[j]) {
                    precomputed[i][j] = precomputed[i][j - 1];
                }
                else {
                    precomputed[i][j] = j;
                }
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
    	return precomputed[i][j];
    }
}
