package Data.RangeMinQuery;

/**
 * EN
 * ========
 * An interface representing an object that can answer range minimum queries.
 * It is expected that any class implementing this interface will have a
 * constructor with the signature
 * <pre>
 *       public [ClassName](float[] elems)
 * </pre>
 * that will accept as input the array elems and preprocess it so that 
 * RMQ_elems(i, j) can be computed efficiently.
 * 
 * FR
 * ========
 * Une interface représentant un objet pouvant répondre aux requêtes à intervalle minimal.
 * Il est prévu que toute classe implémentant cette interface aura un
 * constructeur avec la signature
 * <pre>
 * public [ClassName] (float [] elems)
 * </ pre>
 * qui acceptera comme entrée le tableau elems et le prétraiter afin que
 * RMQ_elems (i, j) peut être calculé efficacement.
 */
public interface RMQ {
    /**
     * EN
     * ==========
     * Given the values of i and j, returns the index of the smallest element
     * in the range A[i], A[i+1], ..., A[j]. If multiple elements in the range
     * are tied for the smallest value, this method may return any of them.
     * The implementation can assume that i <= j and does not need to handle
     * the case where this isn't true.
     *
     * @param i The lower end of the range, inclusive.
     * @param j The upper end of the range, inclusive.
     * @return The value of RMQ_A(i, j).
     * 
     * FR
     * ==========
     * Étant donné les valeurs de i et j, retourne l'index du plus petit élément
     * dans la plage A [i], A [i + 1], ..., A [j]. Si plusieurs éléments dans la plage
     * sont liés pour la plus petite valeur, cette méthode peut renvoyer n'importe laquelle d'entre elles.
     * L'implémentation peut supposer que i <= j et n'a pas besoin de gérer
     * le cas où ce n'est pas vrai.
     *
     * @param i Le bas de la plage, inclus.
     * @param j La limite supérieure de la plage, incluse.
     * @retour La valeur de RMQ_A (i, j).
     */
    public int rmq(int i, int j);
}
