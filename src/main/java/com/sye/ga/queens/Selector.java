package com.sye.ga.queens;

/**
 * Interface for classes that want to implement a selection method.
 *  
 * @author luis flores soberon
 *
 */
public interface Selector {

   
    /**
     * Selects a parent from the {@link Population}.
     * 
     * @param population   - {@link Population} to draw the parent from. 
     * @return             - The selected parent.
     */
    public Individual selectParent(Population population);
}
