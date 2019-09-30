package Implementations.RangeMinQuery;

import Data.RangeMinQuery.RMQ;

/**
 * An <O(1), O(n)> implementation of RMQ that uses a naive approach
 * 
 * Une <O (1), O (n)> implémentation de RMQ utilisant une approche naïve
 *
 */
public class NaiveRMQ implements RMQ {
	
	float[] origElems;
	
    /**
     * Creates a new MyNaiveRMQ structure to answer queries about the
     * array given by elems.
     * @elems The array over which RMQ should be computed.
     * 
     * Crée une nouvelle structure MyNaiveRMQ pour répondre aux questions sur la
     * tableau donné par elems.
     * @elems Le tableau sur lequel RMQ doit être calculé.
     */
	public NaiveRMQ(float[] elems) {
		origElems = elems;
	}

    /**
     * Evaluates RMQ(i, j) over the array stored by the constructor, returning
     * the index of the minimum value in that range.
     * 
     * Evalue RMQ (i, j) sur le tableau stocké par le constructeur, en renvoyant
     * l'indice de la valeur minimale dans cette plage.
     */	
	public int rmq(int i, int j) {
		float min = Float.MAX_VALUE;
		int idx = -1;
		for(int k = i; k <= j; k++) {
			if (origElems[k] < min) {
				min = origElems[k];
				idx = k;
			}
		}
		return idx;
	}

}
