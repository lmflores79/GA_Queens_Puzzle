package com.sye.ga.queens;

/**
 * Interfaces for classes that want to implement some mutation technique. 
 * 
 * @author luis flores soberon
 *
 */
public interface Mutator {
    
    /**
     * Mutates and {@link Individual}.
     * 
     * @param individual   -  The {@link Individual} to mutate.
     * @param mutationRate -  The mutation rate to decide if we should apply mutation or not to it.
     */
    public void mutateIndividual(Individual individual, double mutationRate);
}
