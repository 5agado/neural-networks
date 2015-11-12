#Artificial Neural Networks (ANNs)
Draft template for machine learning using a neural network model.

##Basic Definition
Artificial Neural Networks (ANNs) are computational models inspired by the structure of the brain. They are represented as a directed graph in which the nodes and edges are models of respectively biological neurons and synapses.

A **perceptron** is the basic unit of ANN, takes a vector of weighted real-value inputs, uses summation processor to compute a linear combination of such values, and outputs a new value based on an activation function and adjustable threshold (bias). Similarly a neuron gets its inputs from dendrites and sends the outputs to the axon, that then branches into inhibitory or excitatory synapses. 

With **multilayer networks** we add the so called hidden layers. In a feedforward network a vector of inputs is fed to each one of the neurons in the hidden layer. This layer produces as output another vector of values that will be fed to the next layer and so on. The last layer is called output layer.

##Usage
A new perceptron can be instantiated and run by providing the required parameters and functions.

Parameters are the *weights* (initial values for the weight associated to each input of the perceptron) and the bias initial value.

The required functions are the *summationProcessor* (implements the desired linear combination between a list of inputs and the perceptron weights) and the *activationFunction* (derives the perceptron output based on both the the summation processor resulting value, and the bias).

##**WIP**

## License

Released under version 2.0 of the [Apache License].

[Apache license]: http://www.apache.org/licenses/LICENSE-2.0


