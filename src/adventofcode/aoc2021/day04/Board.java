package adventofcode.aoc2021.day04;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Board {

    private static int BOARD_SIZE = 5;

    private boolean[][] marks;
    private int[][] numbers;
    private boolean isWon = false;

    public Board(int[][] numbers) {
        if (numbers.length != BOARD_SIZE || numbers[0].length != BOARD_SIZE) {
            throw new IllegalArgumentException("Bingo board must be of size 5!");
        }

        this.marks = new boolean[BOARD_SIZE][BOARD_SIZE];
        this.numbers = numbers;
    }

    public void markNumber(int number) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (this.numbers[i][j] == number) {
                    this.marks[i][j] = true;
                    return;
                }
            }
        }
    }

    public int score() {
        int sum = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (this.marks[i][j] == false) {
                    sum += this.numbers[i][j];
                }
            }
        }
        return sum;
    }

    public boolean checkWinner() {
        isWon = checkRows() || checkColumns();
        return isWon;
    }

    private boolean checkRows() {
        return Arrays.stream(marks)
                .anyMatch(this::checkSingleRow);
    }

    private boolean checkSingleRow(boolean[] row) {
        return IntStream.range(0, BOARD_SIZE)
                .mapToObj(i -> row[i])
                .allMatch(b -> b);
    }

    private boolean checkColumns() {
        return IntStream.range(0, BOARD_SIZE)
                .anyMatch(this::checkSingleColumn);
    }

    private boolean checkSingleColumn(int column) {
        return IntStream.range(0, BOARD_SIZE)
                .mapToObj(i -> marks[i][column])
                .allMatch(b -> b);
    }

    public boolean isWon() {
        return this.isWon;
    }

}
