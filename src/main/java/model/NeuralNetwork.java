package model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.IntStream;

public class NeuralNetwork {
	private List<Perceptron> hiddenLayer;
	private List<Perceptron> outputLayer;
	
	//TODO possibility of more hidden layers
	public NeuralNetwork(int hiddenLayerSize, int numInput, int numOutput, 
			BiFunction<Perceptron, Integer, Perceptron> pGenerator, Perceptron p) {
		hiddenLayer = new ArrayList<Perceptron>(hiddenLayerSize);
		IntStream.range(0, hiddenLayerSize).forEach(i -> hiddenLayer.add(pGenerator.apply(p, numInput)));
		
		outputLayer = new ArrayList<Perceptron>(numOutput);
		IntStream.range(0, numOutput).forEach(i -> outputLayer.add(pGenerator.apply(p, hiddenLayerSize)));
	}

	public List<Double> getOutputs(List<Double> inputs){
		List<Double> hiddenLayerOutputs = new ArrayList<Double>(hiddenLayer.size());
		IntStream.range(0, hiddenLayer.size())
			.forEach(i -> hiddenLayerOutputs.add(hiddenLayer.get(i).getOutput(inputs)));
		
		List<Double> outputs = new ArrayList<Double>(outputLayer.size());
		IntStream.range(0, outputLayer.size())
			.forEach(i -> outputs.add(outputLayer.get(i).getOutput(inputs)));
		
		return outputs;
	}
}
