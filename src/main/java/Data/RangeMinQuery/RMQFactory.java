package Data.RangeMinQuery;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Random;

public class RMQFactory {

	/** Constant controlling the maximum array size in the small array tests. */
	private static final int SMALL_ARRAY_SIZE = 10;

	/** Constant controlling how many tests of each size should be run in small tests. */
	private static final int NUM_TRIALS_PER_SMALL_SIZE = 5;

	/** Constants controlling the maximum array size in the large array tests. */
	private static final int LARGE_ARRAY_SIZE = 1000;

	/** Constant controlling how many tests of each size should be run in large tests. */
	private static final int NUM_TRIALS_PER_LARGE_SIZE = 100;	

	/**
	 * Perform tests on the two implementations
	 * @param rmq1
	 * @param rmq2
	 */
	public void test(String rmqClass1, String rmqClass2) {

		Random rand = new Random();

		/* Run small test */
		float[] elems = randomArrayOfSize(rand, SMALL_ARRAY_SIZE);
		System.out.println("Running small test");
		System.out.println("==================");
		System.out.print("Array = [");
		for(int i = 0; i < elems.length; i++) {
			System.out.print(elems[i] + ",  ");
		}
		System.out.print("]\n\n");
		RMQ rmq1 = createRMQFor(rmqClass1, Arrays.copyOf(elems, elems.length));
		RMQ rmq2 = createRMQFor(rmqClass2, Arrays.copyOf(elems, elems.length));
		runTests(rmq1, rmq2, elems, rand, NUM_TRIALS_PER_SMALL_SIZE, true);
		
		/* Run large test */
		elems = randomArrayOfSize(rand, LARGE_ARRAY_SIZE);
		System.out.println("\nRunning large test");
		System.out.println("==================");
		rmq1 = createRMQFor(rmqClass1, Arrays.copyOf(elems, elems.length));
		rmq2 = createRMQFor(rmqClass2, Arrays.copyOf(elems, elems.length));
		runTests(rmq1, rmq2, elems, rand, NUM_TRIALS_PER_LARGE_SIZE, false);
		
		System.out.println("All tests completed!");
	}

	/**
	 * Generates a random array of the given size.
	 * 
	 * @param rand The random number source.
	 * @param size The size of the array to generate.
	 * @return An array of that size whose elements are randomly generated.
	 */
	private float[] randomArrayOfSize(Random rand, int size) {
		float[] result = new float[size];
		for (int i = 0; i < result.length; i++) {
			result[i] = rand.nextFloat();
		}
		return result;
	}

	private void runTests(RMQ rmq1, RMQ rmq2, float[] elems, Random rand, int numTests, boolean print) {

		for (int trialNum = 0; trialNum < numTests; trialNum++) {
			/* Choose i and j for the probe. */
			int i = rand.nextInt(elems.length);
			int j = i + rand.nextInt(elems.length - i);

			/* Evaluate RMQ on the points. */
			int sol1 = rmq1.rmq(i, j);
			int sol2 = rmq2.rmq(i, j);

			/* Confirm the provided student output is valid. */
			if (print) {
				System.out.println("Testing RMQ(" + i + ", " + j + "): rmq1 returned elem(" + sol1 + ")=" + elems[sol1] + ", rmq2 returned elem(" + sol2 + ")=" + elems[sol2]);
			}

			if (elems[sol1] != elems[sol2]) {
				System.out.println("Error testing RMQ(" + i + ", " + j + "): rmq1 returned elem(" + sol1 + ")=" + elems[sol1] + ", rmq2 returned elem(" + sol2 + ")=" + elems[sol2]);
			}
		}
	}

	/**
	 * Given the name of an RMQ class, returns an RMQ factory that creates objects of
	 * that type.
	 * 
	 * @param classname The name of the class to load.
	 */
	private RMQ createRMQFor(String classname, float[] elems) {
		try {
			/* Load the class, if we can. */ 
			final Class<?> clazz = Class.forName(classname);

			/* Get a constructor that takes in a long[]. */
			final Constructor<?> ctor = clazz.getConstructor(float[].class);

			return (RMQ) ctor.newInstance(elems);

		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
