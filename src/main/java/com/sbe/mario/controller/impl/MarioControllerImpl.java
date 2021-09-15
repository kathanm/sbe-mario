package com.sbe.mario.controller.impl;

import com.sbe.mario.controller.IMarioController;
import com.sbe.mario.model.GetPathInput;
import com.sbe.mario.model.Move;
import com.sbe.mario.service.IMarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@RestController
@RequestMapping("/api/mario")
public class MarioControllerImpl implements IMarioController {

    @Autowired
    IMarioService marioService;


    @Override
    @PostMapping("/getPath")
    public List<Move> getPath(@RequestBody String input) {
        // create scanner to read input
        Scanner sc = new Scanner(input);
        // get grid size
        int gridSize = sc.nextInt();
        List<String> grid = new ArrayList<>();
        // iterate through input and fill out grid
        for (int i = 0; i < gridSize; i++) {
            String nextLine = sc.next();
            grid.add(nextLine);
        }

        // create input object
        GetPathInput getPathInput = new GetPathInput();
        getPathInput.setGrid(grid);
        getPathInput.setGridSize(gridSize);

        // call mario service to get path
        List<Move> result =  marioService.getPath(getPathInput);
        return result;
    }
}
