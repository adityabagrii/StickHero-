package com.example.stickhero;

import org.junit.jupiter.api.Test;
import javafx.scene.shape.Rectangle;
import static org.junit.Assert.assertTrue;

public class BlocksTest {

    @Test
    public void testRandomBlockGenerator() {
        Blocks blocks = new Blocks();
        Rectangle block = blocks.randomBlockGenerator();
        assertTrue(isValidWidth(block.getWidth()));
        assertTrue(isValidHeight(block.getHeight()));
    }

    private boolean isValidWidth(double width) {
        double[] validWidths = {100, 150, 90, 75, 106, 125, 96};
        for (double validWidth : validWidths) {
            if (width == validWidth) {
                return true;
            }
        }

        return false;
    }
    private boolean isValidHeight(double height) {
        return height == 600;
    }
}
