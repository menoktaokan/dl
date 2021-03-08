package com.okan.tictactoe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TicTacToeFoundWinner {
    public static void main(String[] args) {
        List<List<Integer>> board = List.of(
                List.of(-1, -1, -1),
                List.of(0, 1, 0),
                List.of(-1, -1, 1)
        );

//        System.out.println(board);
//        System.out.println(flip(board));
//        System.out.println(
//                board.stream().map(TicTacToeFoundWinner::horizontalControl).collect(Collectors.toList()));
//        System.out.println(
//                flip(board).stream().map(TicTacToeFoundWinner::horizontalControl).collect(Collectors.toList()));
//        System.out.println(
//                diagonalControl(board));
//        System.out.println(
//                rDiagonalControl(board));
//        System.out.println(winnerList(board));

        System.out.println("Winner: " + winner(board));


    }

    static Integer winner(List<List<Integer>> list) {
        return winnerList(list).stream().filter(t -> !t.equals(0)).findFirst().orElse(0);
    }

    static Integer horizontalControl(List<Integer> list) {
        if (list.get(0).equals(list.get(1)) && list.get(1).equals(list.get(2)))
            return list.get(0);
        return 0;
    }

    static List<List<Integer>> flip(List<List<Integer>> list) {
        return List.of(
                List.of(list.get(0).get(0), list.get(1).get(0), list.get(2).get(0)),
                List.of(list.get(0).get(1), list.get(1).get(1), list.get(2).get(1)),
                List.of(list.get(0).get(2), list.get(1).get(2), list.get(2).get(2))
        );
    }

    static Integer diagonalControl(List<List<Integer>> list) {
        if (list.get(0).get(0).equals(list.get(1).get(1))
                && list.get(1).get(1).equals(list.get(2).get(2)))
            return list.get(0).get(0);
        return 0;
    }

    static Integer rDiagonalControl(List<List<Integer>> list) {
        if (list.get(0).get(2).equals(list.get(1).get(1))
                && list.get(1).get(1).equals(list.get(3).get(0)))
            return list.get(3).get(0);
        return 0;
    }

    static List<Integer> winnerList(List<List<Integer>> list) {
        List<Integer> result = new ArrayList<>();
        result.addAll(list.stream().map(TicTacToeFoundWinner::horizontalControl).collect(Collectors.toList()));
        result.addAll(flip(list).stream().map(TicTacToeFoundWinner::horizontalControl).collect(Collectors.toList()));
        result.add(diagonalControl(list));
        result.add(rDiagonalControl(list));

        return result;
    }
}