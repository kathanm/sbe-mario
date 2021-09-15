package com.sbe.mario.service;

import com.sbe.mario.model.GetPathInput;
import com.sbe.mario.model.Move;

import java.util.List;

/**
 * Service layer for Mario app
 */
public interface IMarioService {

    /**
     * Get path from Mario to defeat Bowser and then save Peach
     * @param input input including grid size and grid. grid should be formatted as list of string with
     *              'm' for Mario, 'b' for Bowser, and 'p' for Peach with '-' for free spaces and '*' for hazards
     * @return list of moves Mario must make to defeat Bowser and save Peach
     */
    public List<Move> getPath(GetPathInput input);
}
