package com.sye.ga.queens;

import java.util.Random;

/**
 * Implementation of the Order crossover method.
 * 
 *  Reference: http://www.dmi.unict.it/mpavone/nc-cs/materiale/moscato89.pdf
 *  
 * @author luis flores soberon
 *
 */
public class OrderCrossoverImpl implements CrossoverExecutor{

    private Random randomGenerator;

    
    /**
     * Implements order crossover method under the context of the 8 Queen problem.
     * 
     * @param parent1     - First parent.
     * @param parent2     - Second parent.
     * @param offsetStart - Position to start copying the first parent genes.
     * @param offsetEnd   - Position to finish copying the first parent genes.
     * @return            - A new {@link Individual}.
     */
    private Individual crossover(Individual parent1, Individual parent2, int offsetStart, int offsetEnd){
        Individual offspring = new Individual(parent1.getChromosomeLength());
        
        for(int i=offsetStart; i<=offsetEnd; i++){
            offspring.setGene(i, parent1.getGene(i));
        }
        
        int currentOffspringOffset = 0;
        
        for(int i=0; i<parent2.getChromosomeLength(); i++){            
            while(currentOffspringOffset >= offsetStart && currentOffspringOffset <= offsetEnd){
                currentOffspringOffset++;
            }
            if(currentOffspringOffset >= parent2.getChromosomeLength()){
                break;
            }
            
            if(!offspring.containsGene(parent2.getGene(i))){
                offspring.setGene(currentOffspringOffset, parent2.getGene(i));
                currentOffspringOffset++;
            }
        }
        
        return offspring;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Individual[] crossover(Individual parent1, Individual parent2){
        
        int offsetStart = randomGenerator.nextInt(parent1.getChromosomeLength());
        int offsetEnd = randomGenerator.nextInt(parent1.getChromosomeLength() - offsetStart) + offsetStart;

        Individual[] offsprings = new Individual[2];
        offsprings[0] = crossover(parent1, parent2, offsetStart, offsetEnd);
        offsprings[1] = crossover(parent2, parent1, offsetStart, offsetEnd);
        
        return offsprings;
    }

    public void setRandomGenerator(Random randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

}
