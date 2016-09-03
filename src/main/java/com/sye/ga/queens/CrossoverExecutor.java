package com.sye.ga.queens;

/**
 * 
 * Interface for classes that want to provide a crossover implementation.
 * 
 * @author luis flores soberon
 *
 */
public interface CrossoverExecutor {
    
    /**
     * Apply crossover to the pair of {@link Individual}.
     * 
     * @param parent1   - First individual.
     * @param parent2   - Second individual.
     * @return          - The offspring.
     */
    public Individual[] crossover(Individual parent1, Individual parent2);
}
