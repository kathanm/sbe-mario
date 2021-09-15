package com.sbe.mario.controller;

import com.sbe.mario.model.GetPathInput;
import com.sbe.mario.model.Move;

import java.util.List;

/**
 * REST Controller for Mario Service
 */
public interface IMarioController {

    /**
     * Get the path to defeat Bowser and save Peach
     * @param input string formatted with the size of the grid in first line with each grid line added after
     *              grid should be formatted as list of string with 'm' for Mario, 'b' for Bowser, and 'p' for Peach
     *              with '-' for free spaces and '*' for hazards
     * @return list of moves Mario must make to first defeat Bowser and then save Peach
     */
    public List<Move> getPath(String input);
}
