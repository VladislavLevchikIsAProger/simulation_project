package com.vladislavlevchik.actions.turnActions;

import com.vladislavlevchik.Cell;
import com.vladislavlevchik.WorldMap;
import com.vladislavlevchik.actions.Action;
import com.vladislavlevchik.entities.Entity;
import com.vladislavlevchik.entities.Grass;
import com.vladislavlevchik.services.FindRandomEmptyCellService;

import java.util.HashMap;

public class EnsureSufficientGrassAction extends Action {
    @Override
    public void prefer(WorldMap worldMap) {
        HashMap<Cell, Entity> hashMap = worldMap.getMap();
        int numberOfGrasses = 0;
        for (int x = 1; x <= worldMap.height; x++) {
            for (int y = 1; y <= worldMap.length; y++) {
                Entity entity = hashMap.get(new Cell(x, y));
                if (entity instanceof Grass) {
                    numberOfGrasses++;
                }
            }
        }

        if (numberOfGrasses == 0) {
            Cell findCell = FindRandomEmptyCellService.findRandomCell(worldMap);
            if (findCell != null) {
                hashMap.put(findCell, new Grass());
                System.out.println("На клетке " + findCell + "выросла трава");
            } else {
                System.out.println("Траве негде вырасти");
            }
        }
    }
}

