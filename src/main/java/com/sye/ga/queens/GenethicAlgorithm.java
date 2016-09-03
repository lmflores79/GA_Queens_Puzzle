package com.sye.ga.queens;

/**
 * This class is the GA implementation that solves the 8 Queen problems.
 * 
 *  It is just a quick proof of concept done out of academic interest but I think it serves its purpose.
 * 
 * @author luis flores soberon
 *
 */
public class GenethicAlgorithm {

    private CrossoverExecutor crossoverExecutor;
    private Selector selector;
    private Mutator mutator;
    private int populationSize;
    private double mutationRate;
    private double crossoverRate;
    private int elitismCount;
    private int maxGenerations;
    
    
    
    
    /**
     * Takes care of getting an initial population.
     * 
     * @return - The {@link Population}
     */
    public Population initPopulation(){
       return new Population(this.populationSize, Constants.N);
    }
    
    
    /**
     * Indicates if the search should stop.
     * 
     * We know here, according to our rules, that the solution was found if we have fitness of {@link Constants.N}.
     * The search should stop also if we reach the maximum number of allowed generations.
     * 
     * @param generation  - The number of generations evolved.   
     * @param population  - The current population for this generation.
     * @return            - true if the search should stop, false otherwise.
     */
    public boolean isTerminationConditionMet(int generation, Population population){
        return generation > maxGenerations || population.getIndividual(0).getFitness() == Constants.N;        
    }
    
    
    /**
     * Method for evolving a given population.
     * 
     * Takes care of applying the necessary operations (crossover and mutation) to get the next generation available.
     *  
     * @param population  -  The {@link Population} to evolve.
     * @return            -  The {@link Population} containing the offspring.
     */
    public Population evolvePopulation(Population population) {
       
        // We initialize the offspring population.
        Population newPopulation = new Population(population.size());

        // We assume population to evolve is already ordered by fitness. This is important for the elitism rules to properly apply. 
        for (int populationIndex = 0; populationIndex < population.size(); populationIndex++) {

 
            Individual parent1 = population.getIndividual(populationIndex);

            // We want only to apply crossover and potentially mutate this individiaul if it is not part of the elite.
            if (this.crossoverRate > Math.random() && populationIndex >= this.elitismCount) {
                
                // Let the selector find a suitable individual to mate them.
                Individual parent2 = selector.selectParent(population);

                // Execute crossover.
                Individual[] offspring = crossoverExecutor.crossover(parent1, parent2);
                
                // In this proof of concept we mutate only the first child.
                mutator.mutateIndividual(offspring[0], mutationRate);
                newPopulation.setIndividual(populationIndex, offspring[0]);                
            } else {
                // Add individual to new population as is.
                newPopulation.setIndividual(populationIndex, parent1);
            }
        }

        return newPopulation;
    }
    

    /**
     * Evaluates the fitness of each {@link Individual} in the {@link Population}.
     * 
     * @param population  - {@link Population} to evaluate.
     */
    public void evaluatePopulation(Population population){
        for(Individual individual: population.getIndividuals()){
            calculateFitness(individual);
        }
        population.orderByFitness();
    }
    
    /**
     * Calculates the fitness of a given {@link Individual}.
     * 
     * The rules are simple. We start with the highest score ({@link Constants.N}) which would be the one assigned to a valid solution (No queen can see each other)
     * and then we start to compare each {@link Position} in the chromosomes with the rest of them. For each {@link Position} that is in the line of action of any other one, we subtract 
     * a point in the score.
     * 
     * The rules for finding if two {@link Position} are in the same line of action are the next ones:
     * 1.- If they share the same 'x' they see each other (They are in the same horizontal line).
     * 2.- If they share the same 'y' they see each other (They are in the same vertical line).
     * 3.- If the absolute value of the difference between  x1 and x2 is the same than the absolute value of the difference between the y1 and y2, then they are in the same diagonal.
     * 
     * @param individual  - The {@link Individual} to evaluate.
     */
    private void calculateFitness(Individual individual){
        
        int fitness = Constants.N;
        for(int i=0; i < individual.getChromosome().length; i++) {
            Position evaluatedPosition = individual.getGene(i);
            for (int j=0; j < individual.getChromosome().length; j++) {
                if (i!=j) {
                    Position position = individual.getGene(j);                    
                    int x = Math.abs(position.getX() - evaluatedPosition.getX());
                    int y = Math.abs(position.getY() - evaluatedPosition.getY());
                    // If they are in the same vertical, horizontal or diagonal line, we lower the score in one point.
                    if (x== y || evaluatedPosition.getX()==position.getX() || position.getY() == evaluatedPosition.getY()){
                        fitness--;
                    }
                }
            }
        }
        individual.setFitness(fitness);        
    }
    
    
    public void setCrossoverExecutor(CrossoverExecutor crossoverExecutor) {
        this.crossoverExecutor = crossoverExecutor;
    }



    public void setSelector(Selector selector) {
        this.selector = selector;
    }



    public void setMutator(Mutator mutator) {
        this.mutator = mutator;
    }



    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }



    public void setMutationRate(double mutationRate) {
        this.mutationRate = mutationRate;
    }



    public void setCrossoverRate(double crossoverRate) {
        this.crossoverRate = crossoverRate;
    }



    public void setElitismCount(int elitismCount) {
        this.elitismCount = elitismCount;
    }



    public void setMaxGenerations(int maxGenerations) {
        this.maxGenerations = maxGenerations;
    }





}



