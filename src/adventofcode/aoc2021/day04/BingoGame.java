package adventofcode.aoc2021.day04;

import java.util.List;

public class BingoGame {

    private List<Integer> drawnNumbers;
    private List<Board> boards;

    public BingoGame(List<Integer> drawnNumbers, List<Board> boards) {
        this.drawnNumbers = drawnNumbers;
        this.boards = boards;
    }

    public int playForWin() {
        for (int number : drawnNumbers) {
            for (Board board : boards) {

                board.markNumber(number);

                if (board.checkWinner()) {
                    return board.score() * number;
                }
            }
        }
        return -1;
    }

    public int playToLose() {
        int counter = 0;

        for (int number : drawnNumbers) {
            for (Board board : boards) {

                board.markNumber(number);

                if (!board.isWon() && board.checkWinner()) {
                    counter++;

                    if (counter == boards.size()) {
                        return board.score() * number;
                    }
                }


            }
        }

        return -1;
    }

}
