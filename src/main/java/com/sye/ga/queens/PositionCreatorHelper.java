package com.sye.ga.queens;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Utility class used for creating valid random {@link Position}.
 * 
 * @author luis flores soberon
 *
 */
public class PositionCreatorHelper {


    private static Random randomGenerator = new Random();

    private static Position createRandomPosition(int chromosomeLenght){
        return new Position(randomGenerator.nextInt(chromosomeLenght) + 1, randomGenerator.nextInt(chromosomeLenght) + 1);
    }
    
    /**
     * Adds a valid random {@link Position} to the {@code chromosome} at a given {@code offset}.
     * 
     * A valid {@link Position} is any {@link Position} that doesn't already exist in the {@code chromosome}. 
     * 
     * @param chromosome       - Chromosome we want to insert a {@link Position} into. 
     * @param offset           - offset where we want to insert the new {@link Position} into.
     */
    public static void addPosition(Position[] chromosome, int offset){
        List<Position> positionList = Arrays.asList(chromosome);
        
        do{          
            Position position = createRandomPosition(chromosome.length);
            if(!positionList.contains(position)){
                chromosome[offset] = position;
                break;
            }
        }while(true);
    }

}
