package com.sbe.mario.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class GetPathInput {

    private int gridSize;

    private List<String> grid;
}
