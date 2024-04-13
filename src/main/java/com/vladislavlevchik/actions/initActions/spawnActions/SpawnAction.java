package com.vladislavlevchik.actions.initActions.spawnActions;

import com.vladislavlevchik.Cell;
import com.vladislavlevchik.WorldMap;
import com.vladislavlevchik.actions.Action;
import com.vladislavlevchik.entities.Entity;
import com.vladislavlevchik.services.FindRandomEmptyCellService;

import java.util.HashMap;

public abstract class SpawnAction extends Action {
    private final int count;

    public SpawnAction(int count) {
        this.count = count;
    }

    @Override
    public void prefer(WorldMap worldMap) {

        int count = this.count;

        HashMap<Cell, Entity> hashMap = worldMap.getMap();
        while (count > 0) {
            hashMap.put(FindRandomEmptyCellService.findRandomCell(worldMap), getEntity());
            count--;
        }

    }

    protected abstract Entity getEntity();
}
