package com.sye.ga.queens;



/**
 * Represents an individual in the population.
 * 
 * An individual is a potential solution to the problem which in this case, is an 
 * array of {@link Position} representing a given configuration setting of the queens (The positions the queens are in the chessboard for this solution).
 * 
 * @author luis flores soberon
 *
 */
public class Individual {

    // The chromosome representing the solution.
    private Position[] chromosome;
    
    // The fitness of this solution
    private double fitness;
    
    
    /**
     * Constructor.
     * 
     * @param chromosomeLenght - Indicates how many genes the chromosome should have.
     */
    public Individual(int chromosomeLenght){
        this.chromosome = new Position[chromosomeLenght];
        
        // We initialize the Individual with random genes (positions).
        for(int i=0; i < chromosomeLenght; i++){
            PositionCreatorHelper.addPosition(chromosome, i);            
        }
    }
    
    
    /**
     * Verifies if the given position already exists.
     * 
     * @param position  - The {@link Position} to evaluate.
     * @return          - true if it already contains the position, false otherwise.
     */
    public boolean containsGene(Position position) {        
        for (Position existingPosition : chromosome){
            if (existingPosition.equals(position)) {
                return true;
            }
        }
        return false;
    }
    
    
    
    
    public int getChromosomeLength(){
        return this.chromosome.length;
    }
    
    public void setGene(int offset, Position gene){
        this.chromosome[offset] = gene;
    }
    
    public Position getGene(int offset){
        return this.chromosome[offset];
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public Position[] getChromosome() {
        return chromosome;
    }

    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        sb.append(this.fitness);
        sb.append(" ");
        for (int gene = 0; gene < this.chromosome.length; gene++) {
            sb.append(this.chromosome[gene]);
            sb.append(",");
        }
        return sb.toString();
    }


}
