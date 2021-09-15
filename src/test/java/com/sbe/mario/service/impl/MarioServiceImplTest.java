package com.sbe.mario.service.impl;

import com.sbe.mario.model.GetPathInput;
import com.sbe.mario.model.Move;
import com.sbe.mario.service.IMarioService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MarioServiceImplTest {

    IMarioService service = new MarioServiceImpl();

    @Test
    public void testGetPath() {
        GetPathInput input = new GetPathInput();
        input.setGridSize(7);
        List<String> grid = new ArrayList<>(Arrays.asList(
                "-----p-",
                "-*****-",
                "--b----",
                "**-----",
                "-------",
                "-******",
                "--m----"));

        input.setGrid(grid);

        assertEquals(List.of(Move.LEFT, Move.LEFT, Move.UP, Move.UP, Move.RIGHT, Move.RIGHT, Move.UP, Move.UP,
                Move.RIGHT, Move.RIGHT, Move.RIGHT, Move.RIGHT, Move.UP, Move.UP, Move.LEFT),
                service.getPath(input));
    }

    @Test
    public void testGetPath2() {
        GetPathInput input = new GetPathInput();
        input.setGridSize(7);
        List<String> grid = new ArrayList<>(Arrays.asList(
                "-----p-",
                "-*****-",
                "--b----",
                "**-----",
                "-------",
                "******-",
                "--m----"));

        input.setGrid(grid);

        assertEquals(List.of(
                Move.RIGHT, Move.RIGHT, Move.RIGHT, Move.RIGHT, Move.UP, Move.UP, Move.UP, Move.UP, Move.LEFT, Move.LEFT,
                Move.LEFT, Move.LEFT, Move.RIGHT, Move.RIGHT, Move.RIGHT, Move.RIGHT, Move.UP, Move.UP, Move.LEFT),
                service.getPath(input));
    }

    @Test
    public void testGetPathEmptyGrid() {
        GetPathInput input = new GetPathInput();
        input.setGridSize(0);
        input.setGrid(Collections.emptyList());
        assertEquals(Collections.emptyList(), service.getPath(input));
    }

    @Test
    public void testGetPathNoPath() {
        GetPathInput input = new GetPathInput();
        input.setGridSize(7);
        List<String> grid = new ArrayList<>(Arrays.asList(
                "-----p-",
                "-*****-",
                "--b----",
                "**-----",
                "-------",
                "*******",
                "--m----"));

        input.setGrid(grid);
        assertEquals(Collections.emptyList(), service.getPath(input));
    }

    @Test
    public void testGetPathMissingCharacter() {
        GetPathInput input = new GetPathInput();
        input.setGridSize(7);
        List<String> noMarioGrid = new ArrayList<>(Arrays.asList(
                "-----p-",
                "-*****-",
                "--b----",
                "**-----",
                "-------",
                "******-",
                "-------"));
        List<String> noBowserGrid = new ArrayList<>(Arrays.asList(
                "-----p-",
                "-*****-",
                "-------",
                "**-----",
                "-------",
                "******-",
                "--m----"));
        List<String> noPeachGrid = new ArrayList<>(Arrays.asList(
                "-------",
                "-*****-",
                "--b----",
                "**-----",
                "-------",
                "******-",
                "--m----"));

        input.setGrid(noMarioGrid);
        assertEquals(Collections.emptyList(), service.getPath(input));

        input.setGrid(noPeachGrid);
        assertEquals(Collections.emptyList(), service.getPath(input));

        input.setGrid(noBowserGrid);
        assertEquals(Collections.emptyList(), service.getPath(input));
    }
}