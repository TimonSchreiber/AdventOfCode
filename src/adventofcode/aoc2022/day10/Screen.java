package adventofcode.aoc2022.day10;

import java.util.List;

/**
 * Screen
 */
public class Screen {

    private static final int WIDTH  = 40;
    private static final int HEIGHT = 6;

    /**
     * Draw 6 rows of 40 Strings to the Screen on at a time. For each drawing
     * character check the value of the register at that 'time'. If the
     * register is around the current x value (+-1) then draw a '#', otherwise
     * draw a '.'.
     * @param registerValues
     */
    public void drawScreen(List<Integer> registerValues) {

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {

                int register = registerValues.get(y*WIDTH + x);
                int spriteX = register % 40;

                String str = ((x >= (spriteX-1)) && (x <= (spriteX+1))) ? "#" : ".";

                System.out.print(str);

            }
            System.out.println();
        }
    }

}
