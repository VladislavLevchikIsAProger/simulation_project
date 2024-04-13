package com.vladislavlevchik.entities;


public class Rock extends Entity {
    public Rock() {
        super("\uD83D\uDDFB");
    }

    @Override
    public String toString() {
        return getType();
    }
}
