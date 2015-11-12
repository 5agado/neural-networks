package basic;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import model.NeuralNetwork;
import model.Perceptron;
import model.PerceptronFunctions;

import org.junit.Test;

public class TestNeuralNetwork {
	
	BiFunction<Perceptron, Integer, Perceptron> perceptronGenerator = new BiFunction<Perceptron, Integer, Perceptron>() {

		@Override
		public Perceptron apply(Perceptron p, Integer numWeights) {
			Random rand = new Random();
			List<Double> weights = IntStream.range(0, numWeights)
					.mapToDouble(_i -> rand.nextDouble())
					.boxed()
					.collect(Collectors.toList());
			double bias = rand.nextDouble();
			
			Perceptron newP = new Perceptron(weights, bias,
					p.getSummationProcessor(), p.getActivationFunction());
			return newP; 
		}
	};
	
	@Test
	public void test() {
		List<Double> weights = Arrays.asList(0.5, 0.5);
		Perceptron p = new Perceptron(weights, -0.8, PerceptronFunctions.summation, 
				PerceptronFunctions.activationThresholded);
		
		NeuralNetwork ann = new NeuralNetwork(4, 2, 2, perceptronGenerator, p);
		ann.getOutputs(Arrays.asList(0.5, 0.5));
	}
	
	class TrainingPair<T, E> {
		final List<T> inputs;
		final List<E> outputs;
		
		public TrainingPair(List<T> inputs, List<E> outputs) {
			super();
			this.inputs = inputs;
			this.outputs = outputs;
		}
	}

}
