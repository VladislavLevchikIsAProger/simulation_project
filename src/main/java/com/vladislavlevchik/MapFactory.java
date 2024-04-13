package com.vladislavlevchik;

import com.vladislavlevchik.actions.initActions.spawnActions.*;
import com.vladislavlevchik.entities.*;

import java.util.function.Supplier;

public class MapFactory {

    public WorldMap create(
            int len,
            int height,
            int herbivoreCount,
            int predatorCount,
            int treeCount,
            int grassCount,
            int rockCount
    ) {

        WorldMap worldMap = new WorldMap(len, height);

        spawnEntityOnMap(worldMap, Rock::new, rockCount);
        spawnEntityOnMap(worldMap, Herbivore::new, herbivoreCount);
        spawnEntityOnMap(worldMap, Predator::new, predatorCount);
        spawnEntityOnMap(worldMap, Tree::new, treeCount);
        spawnEntityOnMap(worldMap, Grass::new, grassCount);

        return worldMap;

    }

    private void spawnEntityOnMap(WorldMap worldMap, Supplier<Entity> supplier, int count) {

        UniversalSpawnAction universalSpawnAction = new UniversalSpawnAction(count, supplier);
        universalSpawnAction.prefer(worldMap);

    }

}
