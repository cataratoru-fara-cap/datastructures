package Assignments.RangeMinQuery;

import Data.RangeMinQuery.RMQ;

/**
 * An <O(1), O(n)>; implementation of RMQ that uses a naive approach
 * 
 * Une <O (1), O (n)> implémentation de RMQ utilisant une approche naïve
 *
 */
public class MyNaiveRMQ implements RMQ {
	
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
	public MyNaiveRMQ(float[] elems) {
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
		
		// TODO
		return -1;
	}

}
