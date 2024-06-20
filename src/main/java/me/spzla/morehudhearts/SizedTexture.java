package me.spzla.morehudhearts;

import net.minecraft.util.Identifier;

public class SizedTexture {
    public Identifier id;
    public int width;
    public int height;

    public SizedTexture(Identifier id, int width, int height) {
        this.id = id;
        this.width = width;
        this.height = height;
    }
}
