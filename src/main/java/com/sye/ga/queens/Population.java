package com.sye.ga.queens;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Represents a population of potential solutions comprised of an array of {@link Individual} elements.
 * 
 * @author luis flores soberon
 *
 */
public class Population {

    private Individual[] population;    

    /**
     * Constructor for initializing an empty population of size {@code populationSize}.
     * 
     * @param populationSize   - The population size.
     */
    public Population(int populationSize) {
        this.population = new Individual[populationSize];
    }
    
    
    /**
     * Constructor for initializing a new {@link Population} of size {@code populationSize} with random {@link Individual}.
     * 
     * @param populationSize   - The population size.
     * @param chromosomeLenght - The chromosome length. (How many positions queens we are dealing with).
     */
    public Population(int populationSize, int chromosomeLenght) {
        this.population = new Individual[populationSize];
        for(int i=0; i<populationSize; i++){
            population[i] = new Individual(chromosomeLenght);
        }
    }
    
    /**
     * Sorts the {@link Individual} elements in the population by fitness.
     */
    public void orderByFitness(){
        Arrays.sort(this.population, new Comparator<Individual>() {

            @Override
            public int compare(Individual o1, Individual o2) {

                if(o1.getFitness() > o2.getFitness()){
                    return -1;
                }else if(o1.getFitness() < o2.getFitness()){
                    return 1;
                }
                return 0;
            }
        } );        
    }
    
    public void setIndividual(int offset, Individual individual){
        this.population[offset] = individual;
    }    
    
    public Individual getIndividual(int offset){        
        return this.population[offset];
    }
    
    public int size(){
        return population.length;
    }

    public Individual[] getIndividuals() {
        return population;
    }

}
