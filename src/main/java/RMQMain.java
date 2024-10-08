import Data.RangeMinQuery.RMQFactory;

/**
 * Hello world!
 *
 */
public class RMQMain 
{
    public static void main( String[] args )
    {
    	
    	RMQFactory factory = new RMQFactory();
        // factory.test("Assignments.RangeMinQuery.MyNaiveRMQ", "Implementations.RangeMinQuery.NaiveRMQ");
       	factory.test("Assignments.RangeMinQuery.MyPrecomputedRMQ", "Implementations.RangeMinQuery.PrecomputedRMQ");
//    	factory.test("Assignments.RangeMinQuery.MySparseTableRMQ", "Implementations.RangeMinQuery.SparseTableRMQ");
//    	factory.test("Assignments.RangeMinQuery.MyHybridRMQ", "Implementations.RangeMinQuery.HybridRMQ");
//    	factory.test("Assignments.RangeMinQuery.MyFischerHeunRMQ", "Implementations.RangeMinQuery.FischerHeunRMQ");
    }
}
