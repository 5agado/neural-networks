package model;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.IntStream;

public class PerceptronFunctions {
	public static BiFunction<List<Double>, List<Double>, Double> summation = 
			new BiFunction<List<Double>, List<Double>, Double>() {

				@Override
				public Double apply(List<Double> inputs, List<Double> weights) {
					//TODO move this check
					if (inputs.size() != weights.size()){
						System.out.println("Inputs size does not match the preceptron configuration");
						return 0.0;
					}
					return IntStream.range(0, weights.size())
				    .mapToDouble(i -> (double) weights.get(i) * inputs.get(i)).sum();
				}
				
	};
	
	public static BiFunction<Double, Double, Double> activationThresholded = 
			new BiFunction<Double, Double, Double>() {
		
				@Override
				public Double apply(Double summationVal, Double bias) {
					return summationVal+bias > 0?1.0:-1.0;
				}
	};
	
	public static BiFunction<Double, Double, Double> activationUnthresholded = 
			new BiFunction<Double, Double, Double>() {
		
				@Override
				public Double apply(Double summationVal, Double bias) {
					return summationVal+bias;
				}
	};
	
	public static BiFunction<Double, Double, Double> activationSigmoid = 
			new BiFunction<Double, Double, Double>() {
		
				@Override
				public Double apply(Double summationVal, Double bias) {
					double output = summationVal+bias;
					return ( 1 / ( 1 + Math.exp(-output)));
				}
	};
	
	public static BiFunction<List<TrainingPair<Double, Double>>, Perceptron, Perceptron> perceptronRuleTraining = 
			new BiFunction<List<TrainingPair<Double,Double>>, Perceptron, Perceptron>() {
		
		@Override
		public Perceptron apply(List<TrainingPair<Double, Double>> trainSet, Perceptron p) {
			List<Double> weights = p.getWeights();
			for (int i=0; i<10; i++){
				double error = 0;
				for (TrainingPair<Double, Double> t : trainSet){
					error += t.output - p.getOutput(t.inputs);
					double u = 0.01*(t.output - p.getOutput(t.inputs));
					for (int j=0; j<weights.size(); j++){
						weights.set(j, weights.get(j)+u*t.inputs.get(j));
					}
					p.setBias(p.getBias()+u);
				}
				System.out.println(error);
				if (error == 0)
					break;
			}
			return p;
		}
	};
	
	public static BiFunction<List<TrainingPair<Double, Double>>, Perceptron, Perceptron> gradientDescentRuleTraining = 
			new BiFunction<List<TrainingPair<Double,Double>>, Perceptron, Perceptron>() {
		
		@Override
		public Perceptron apply(List<TrainingPair<Double, Double>> trainSet, Perceptron p) {
			List<Double> weights = p.getWeights();
			for (int i=0; i<10; i++){
				double trainingError = 0;
				for (TrainingPair<Double, Double> t : trainSet){
					 trainingError += Math.pow(t.output - p.getOutput(t.inputs), 2);
				}
				trainingError = trainingError/2;
				for (int j=0; j<weights.size(); j++){
					weights.set(j, weights.get(j)+(0.01*trainingError));
				}
				p.setBias(p.getBias()+(0.01*trainingError));
			}
			return p;
		}
	};
	
	public static BiFunction<List<TrainingPair<Double, Double>>, Perceptron, Perceptron> stochasticGradientDescentRuleTraining = 
			new BiFunction<List<TrainingPair<Double,Double>>, Perceptron, Perceptron>() {
		
		@Override
		public Perceptron apply(List<TrainingPair<Double, Double>> trainSet, Perceptron p) {
			List<Double> weights = p.getWeights();
			for (int i=0; i<10; i++){
				double error = 0;
				for (TrainingPair<Double, Double> t : trainSet){
					error += Math.pow(t.output - p.getOutput(t.inputs), 2)/2;
					double u = 0.01*(t.output - p.getOutput(t.inputs));
					for (int j=0; j<weights.size(); j++){
						weights.set(j, weights.get(j)+u*t.inputs.get(j));
					}
					p.setBias(p.getBias()+u);
				}
				System.out.println(error);
				if (error == 0)
					break;
			}
			return p;
		}
	};
	
	public static class TrainingPair<T, E> {
		final List<T> inputs;
		final E output;
		
		public TrainingPair(List<T> inputs, E output) {
			super();
			this.inputs = inputs;
			this.output = output;
		}
	}
}
