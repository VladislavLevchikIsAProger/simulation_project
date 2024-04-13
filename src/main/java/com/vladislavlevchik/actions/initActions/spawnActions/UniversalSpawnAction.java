package com.vladislavlevchik.actions.initActions.spawnActions;

import com.vladislavlevchik.WorldMap;
import com.vladislavlevchik.entities.Entity;

import java.util.function.Supplier;

public class UniversalSpawnAction extends SpawnAction {

    private final Supplier<Entity> entitySupplier;

    public UniversalSpawnAction(int count, Supplier<Entity> entitySupplier) {
        super(count);
        this.entitySupplier = entitySupplier;
    }

    @Override
    protected Entity getEntity() {
        return entitySupplier.get();
    }
}
