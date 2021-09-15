package com.sbe.mario.service.impl;

import com.sbe.mario.constant.Constants;
import com.sbe.mario.model.GetPathInput;
import com.sbe.mario.model.Move;
import com.sbe.mario.model.Point;
import com.sbe.mario.service.IMarioService;
import com.sbe.mario.util.Utils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class MarioServiceImpl implements IMarioService {

    @Override
    public List<Move> getPath(GetPathInput input) {
        // reverse grid for simpler indexing
        Collections.reverse(input.getGrid());
        return this.getPath(input.getGridSize(), input.getGrid());
    }

    private List<Move> getPath(int gridSize, List<String> grid) {
        // find our character locations on the grid
        Point mario = Utils.findString(Constants.MARIO, grid);
        Point bowser = Utils.findString(Constants.BOWSER, grid);
        Point peach = Utils.findString(Constants.PEACH, grid);

        // return empty list if they do not exist
        if (mario == null || bowser == null || peach == null) {
            return Collections.emptyList();
        }

        List<Move> result = new ArrayList<>();

        // find path from mario to bowser
        result.addAll(Utils.findPath(mario, bowser, grid));

        // if no path exists return empty list
        if (result.size() == 0) {
            return result;
        }

        // find path from bowser to peach
        result.addAll(Utils.findPath(bowser, peach, grid));

        return result;
    }
}
