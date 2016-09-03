package com.sye.ga.queens;

import java.util.Random;

/**
 * Implementation of the Tournament Selection method.
 * 
 * Reference: https://en.wikipedia.org/wiki/Tournament_selection
 * 
 * @author luis flores soberon
 *
 */
public class TournamentSelectorImpl implements Selector{

    private Random randomGenerator;
    private int tournamentSize;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Individual selectParent(Population population) {
        Individual bestIndividual = null;
        for(int i=0; i < tournamentSize; i++){
            Individual competitor = population.getIndividual(randomGenerator.nextInt(population.size() - 1));
            if(bestIndividual == null || competitor.getFitness() > bestIndividual.getFitness()){
                bestIndividual = competitor;
            }            
        }
        return bestIndividual;
    }

    public void setRandomGenerator(Random randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    public void setTournamentSize(int tournamentSize) {
        this.tournamentSize = tournamentSize;
    }

}
