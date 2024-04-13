package com.vladislavlevchik.entities;

import com.vladislavlevchik.Cell;
import com.vladislavlevchik.Simulation;
import com.vladislavlevchik.WorldMap;
import com.vladislavlevchik.actions.turnActions.GrassGrowthControlAction;

import java.util.*;

public class Herbivore extends Creature {
    public Herbivore() {
        super("\uD83D\uDC30");
    }


    @Override
    public void makeMove(WorldMap worldMap, Cell cell) {
        HashMap<Cell, Entity> map = worldMap.getMap();

        Herbivore herbivore = (Herbivore) map.get(cell);

        HashMap<Integer, ArrayList<Cell>> eatenGrassMap = GrassGrowthControlAction.eatenGrassMap;

        int numberOfMoves = Simulation.countOfMoves;

        ArrayList<Cell> grassesEatenOnTheCurrentMove = eatenGrassMap.get(numberOfMoves);

        if (grassesEatenOnTheCurrentMove != null && grassesEatenOnTheCurrentMove.contains(cell)) {
            System.out.println(herbivore + "" + cell + "стоит на траве и тратит ход на ее поедание");
        } else {
            Cell targetCell = findCellForTheMove(worldMap, cell);

            if (map.get(targetCell) instanceof Grass) {

                if (!eatenGrassMap.containsKey(numberOfMoves + 1)) {
                    eatenGrassMap.put(numberOfMoves + 1, new ArrayList<>());
                    eatenGrassMap.get(numberOfMoves + 1).add(targetCell);
                } else {
                    eatenGrassMap.get(numberOfMoves + 1).add(targetCell);
                }
            }

            printToConsole(cell, targetCell, map);

            map.put(cell, null);
            map.put(targetCell, herbivore);
        }
    }

    @Override
    public boolean isTheRightCellForTheMove(WorldMap worldMap, Cell cell) {
        return !(worldMap.getMap().get(cell) instanceof Creature);
    }

    @Override
    public String toString() {
        return getType();
    }
}
