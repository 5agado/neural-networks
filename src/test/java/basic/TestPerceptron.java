package basic;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import model.Perceptron;
import model.PerceptronFunctions;
import model.PerceptronFunctions.TrainingPair;

import org.junit.Test;

public class TestPerceptron {	
	@Test
	public void testAND_OR() {
		double trueVal = 1.0;
		double falseVal = -1.0;
		
		//AND case
		List<Double> weights = Arrays.asList(0.5, 0.5);
		Perceptron p = new Perceptron(weights, -0.8, PerceptronFunctions.summation, 
				PerceptronFunctions.activationThresholded);
		
		assertEquals(falseVal, p.getOutput(Arrays.asList(trueVal, falseVal)), 0);
		assertEquals(falseVal, p.getOutput(Arrays.asList(falseVal, falseVal)), 0);
		assertEquals(falseVal, p.getOutput(Arrays.asList(falseVal, trueVal)), 0);
		assertEquals(trueVal, p.getOutput(Arrays.asList(trueVal, trueVal)), 0);
		
		//OR case
		//TODO Why Mitchell says -0.3??
		p.setBias(0.3);
		
		assertEquals(trueVal, p.getOutput(Arrays.asList(trueVal, falseVal)), 0);
		assertEquals(falseVal, p.getOutput(Arrays.asList(falseVal, falseVal)), 0);
		assertEquals(trueVal, p.getOutput(Arrays.asList(falseVal, trueVal)), 0);
		assertEquals(trueVal, p.getOutput(Arrays.asList(trueVal, trueVal)), 0);
		
	}
	
	@Test
	public void test() {
		Random rand = new Random();
		
		double trueVal = 1.0;
		double falseVal = -1.0;
		
		List<TrainingPair<Double, Double>> trainingSet = new ArrayList<TrainingPair<Double, Double>>();
		trainingSet.add(new TrainingPair<Double, Double>(Arrays.asList(trueVal, falseVal), falseVal));
		trainingSet.add(new TrainingPair<Double, Double>(Arrays.asList(falseVal, falseVal), falseVal));
		trainingSet.add(new TrainingPair<Double, Double>(Arrays.asList(falseVal, trueVal), falseVal));
		trainingSet.add(new TrainingPair<Double, Double>(Arrays.asList(trueVal, trueVal), trueVal));
		
		List<Double> weights = Arrays.asList(rand.nextDouble(), rand.nextDouble());
		Perceptron p = new Perceptron(weights, rand.nextDouble(), PerceptronFunctions.summation, 
				PerceptronFunctions.activationThresholded);
		
		PerceptronFunctions.perceptronRuleTraining.apply(trainingSet, p);
		System.out.println(p.getWeights().toString());
		System.out.println(p.getBias());
	}
}
