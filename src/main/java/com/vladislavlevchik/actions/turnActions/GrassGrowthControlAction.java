package com.vladislavlevchik.actions.turnActions;

import com.vladislavlevchik.Cell;
import com.vladislavlevchik.Simulation;
import com.vladislavlevchik.WorldMap;
import com.vladislavlevchik.actions.Action;
import com.vladislavlevchik.entities.Entity;
import com.vladislavlevchik.entities.Grass;


import java.util.*;

public class GrassGrowthControlAction extends Action {
    public static final HashMap<Integer, ArrayList<Cell>> eatenGrassMap = new HashMap<>();

    @Override
    public void prefer(WorldMap worldMap) {
        HashMap<Cell, Entity> hashMap = worldMap.getMap();
        ArrayList<Integer> listKeysOfGrassToGrow = new ArrayList<>();

        for (Map.Entry<Integer, ArrayList<Cell>> item : eatenGrassMap.entrySet()) {
            Integer keyOfGrassToGrowUp = item.getKey();

            if (keyOfGrassToGrowUp <= Simulation.countOfMoves - 4) {
                listKeysOfGrassToGrow.add(keyOfGrassToGrowUp);
            }
        }

        Random random = new Random();
        for (Integer keyOfListGrasses : listKeysOfGrassToGrow) {
            ArrayList<Cell> cellsOfWaitToGrowthGrass = eatenGrassMap.get(keyOfListGrasses);

            for (int j = 0; j < cellsOfWaitToGrowthGrass.size(); j++) {
                Cell cell = cellsOfWaitToGrowthGrass.get(j);

                if (hashMap.get(cell) == null) {

                    if (random.nextInt(2) == 1) {
                        hashMap.put(cell, new Grass());
                        System.out.println("На клетке " + cell + "выросла трава");
                    } else {

                        StaticObjectsTrackingAction.staticEntityMap.remove(cell);
                    }
                    cellsOfWaitToGrowthGrass.remove(j);
                    j--;
                }
            }

            if (cellsOfWaitToGrowthGrass.isEmpty()) {
                eatenGrassMap.remove(keyOfListGrasses);
            }
        }
    }

    public static ArrayList<Cell> getCellsOfGrassWaitingToGrow() {
        int numberOfMoves = Simulation.countOfMoves + 1;

        ArrayList<Cell> resultList = new ArrayList<>();

        for (Map.Entry<Integer, ArrayList<Cell>> item : eatenGrassMap.entrySet()) {
            if (item.getKey() <= numberOfMoves) {
                resultList.addAll(item.getValue());
            }
        }
        return resultList;
    }


    public static void removeCell(Cell cell) {
        for (Map.Entry<Integer, ArrayList<Cell>> entry : eatenGrassMap.entrySet()) {
            ArrayList<Cell> list = entry.getValue();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals(cell)) {
                    list.remove(i);
                    break;
                }
            }
        }
    }
}
