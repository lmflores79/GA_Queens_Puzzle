package com.sye.ga.queens;

import java.util.Random;

/**
 * Implementation of a simple mutation technique which only flips a given gene to a new random position.
 * 
 * @author luis flores soberon
 *
 */
public class MutatorImpl implements Mutator {

    private Random randomGenerator;

    /**
     * {@inheritDoc}
     */
    @Override
    public void mutateIndividual(Individual individual, double mutationRate) {
        for (int geneIndex = 0; geneIndex < individual.getChromosomeLength(); geneIndex++) {

            // Mutate this gene only if it happens to be selected to be mutated by the mutationRate.
            if (mutationRate > randomGenerator.nextDouble()) {
                
                // Flip the gene to a valid random position.
                PositionCreatorHelper.addPosition(individual.getChromosome(), geneIndex);
                
                // TODO: Experiment with this. Does this improve the convergence speed of the GA? (Randomly changing just the X or Y position of a gene no matter that may place it in an already exiisting position??? 
                /*if (Math.random() > 0.5) {
                    individual.getGene(geneIndex).setX(randomGenerator.nextInt(8) + 1);
                } else {
                    individual.getGene(geneIndex).setY(randomGenerator.nextInt(8) + 1);
                }*/
            }
            
        }
    }

    public void setRandomGenerator(Random randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

}
