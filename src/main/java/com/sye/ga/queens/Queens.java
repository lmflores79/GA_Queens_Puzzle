package com.sye.ga.queens;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



/**
 * Class for driving the main logic for solving the problem.
 * 
 * @author luis flores soberon
 *
 */
public class Queens {

    private static final Logger logger = LogManager.getLogger(Queens.class);

    public static void main(String[] args) {

        @SuppressWarnings("resource")
        ApplicationContext appContext = new ClassPathXmlApplicationContext("ga-app-config.xml");
        GenethicAlgorithm ga = (GenethicAlgorithm)appContext.getBean("ga");

        

        // We get an initial random population.
        Population population = ga.initPopulation();
        
        // We evaluate the population.
        ga.evaluatePopulation(population);        
        
        // We evolve the population until we get a solution or a max generation is reached.
        int generation = 1;
        while(!ga.isTerminationConditionMet(generation, population)){
            logger.info("Best Individual found so far: " + population.getIndividual(0));
           
            // We evolve the next generation.
            population = ga.evolvePopulation(population);

            // We evaluate the next generation.
            ga.evaluatePopulation(population);
            
            generation++;
        }
        
        // Print the best solution found.
        logger.info("Solution: " + population.getIndividual(0));

    }

}
