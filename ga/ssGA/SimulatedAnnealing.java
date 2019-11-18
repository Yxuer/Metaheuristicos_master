package ssGA;

import java.util.Random;

public class SimulatedAnnealing {

    private double temperature;
    private double coolingRate;
    private Problem problem;
    private int solutionLength;

    private Individual currentIndividual;

    private static Random r;

    private double bestFitness;

    public SimulatedAnnealing (double temp, double rate, Problem prob, int solLength, int seed) {
        this.temperature = temp;
        this.coolingRate = rate;
        this.problem = prob;
        this.solutionLength = solLength;

        this.r = new Random();
        r.setSeed(seed);
        this.currentIndividual = new Individual(solLength, seed);
        currentIndividual.set_fitness(problem.evaluateStep(currentIndividual));
    }

    public void go_one_step() {
        // Determine current solution fitness
        double currentFitness = currentIndividual.get_fitness();

        int rand = (int)(r.nextDouble()*
                (double)solutionLength-1+0.5); // From 0 to L-1 rounded
        if(rand>solutionLength-1) rand=solutionLength-1;

        flipAllele(rand);

        // Determine mutated individual fitness
        double newFitness = problem.evaluateStep(currentIndividual);

        // If the new solution is better, set the individual fitness. OW, reset it
        if (acceptanceProbability(currentFitness, newFitness, temperature) > r.nextDouble()) {
            currentIndividual.set_fitness(newFitness);
        }
        else {
            flipAllele(rand);
            currentIndividual.set_fitness(currentFitness);
        }

        if (currentIndividual.get_fitness() > bestFitness) {
            bestFitness = currentIndividual.get_fitness();
        }

        temperature *= (1 - coolingRate);
    }

    private void flipAllele (int index) {
        // Randomly flip a bit of the solution
        if (currentIndividual.get_allele(index) == 0) {
            currentIndividual.set_allele(index, (byte)1);
        } else {
            currentIndividual.set_allele(index, (byte)0);
        }
    }

    // Calculate the acceptance probability
    private static double acceptanceProbability(double energy, double newEnergy, double temperature) {
        // If the new solution is better, accept it
        if (newEnergy > energy) {
            return 1.0;
        }
        // If the new solution is worse, calculate an acceptance probability
        return Math.exp((newEnergy - energy) / temperature);
    }

    public double get_bestf() {
        return bestFitness;
    }

    public Individual get_solution() {
        return currentIndividual;
    }

    public double getTemperature() {
        return temperature;
    }
}
