package model;

import java.util.List;
import java.util.function.BiFunction;

/**
 * Basic unit of an Artificial Neural Network (ANN)
 */
public class Perceptron {
	private List<Double> weights;
	private double bias;
	//Inputs, weights, sum
	private BiFunction<List<Double>, List<Double>, Double> summationProcessor;
	//Summation value, bias, output
	private BiFunction<Double, Double, Double> activationFunction;

	/**
	 * Constructs a Perceptron described by the specified parameters
	 * @param weights initial values for the weight associated to each input of the perceptron
	 * @param bias initial bias value
	 * @param summationProcessor implements the desired linear combination 
	 * 				between a list of inputs and the Perceptron weights, and returns the resulting value. 
	 * @param activationFunction derives the Perceptron output 
	 * 				based on both the the summation processor resulting value, and the bias
	 */
	public Perceptron(List<Double> weights, double bias,
			BiFunction<List<Double>, List<Double>, Double> summationProcessor,
			BiFunction<Double, Double, Double> activationFunction) {
		setWeights(weights);
		setBias(bias);
		this.summationProcessor = summationProcessor;
		this.activationFunction = activationFunction;
	}

	public List<Double> getWeights(){
		return weights;
	}

	public void setWeights(List<Double> weights){
		this.weights = weights;
	}
	
	public final double getBias() {
		return bias;
	}

	public final void setBias(double bias) {
		this.bias = bias;
	}
	
	public double getOutput(List<Double> inputs){
		return activationFunction.apply(summationProcessor.apply(inputs, weights), bias);
	}

	public final BiFunction<List<Double>, List<Double>, Double> getSummationProcessor() {
		return summationProcessor;
	}

	public final BiFunction<Double, Double, Double> getActivationFunction() {
		return activationFunction;
	}
}
