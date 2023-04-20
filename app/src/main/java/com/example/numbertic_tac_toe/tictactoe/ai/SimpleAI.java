package com.example.numbertic_tac_toe.tictactoe.ai;

import com.example.numbertic_tac_toe.tictactoe.Field;
import com.example.numbertic_tac_toe.tictactoe.Move;
import com.example.numbertic_tac_toe.tictactoe.PenguAI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class SimpleAI extends PenguAI {

    private final Random random;

    public SimpleAI() {
        random = new Random();
    }

    public Move makeMove(Field[][] board, boolean firstPlayer, boolean[] firstPlayedPieces,
                         boolean[] secondPlayedPieces) {
        List<List<Integer>> xys = new ArrayList<>();
        List<Integer> a = IntStream.range(0, 9).filter(i -> !firstPlayedPieces[i]).boxed().collect(Collectors.toList());
        List<Integer> b = IntStream.range(0, 9).filter(i -> !secondPlayedPieces[i]).boxed().collect(Collectors.toList());
        if (firstPlayer) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] != null) {
                        if (!board[i][j].firstPlayer()) {
                            for (int k = 0; k < 9; k++) {
                                if (!firstPlayedPieces[k] && k > board[i][j].value())
                                    xys.add(Arrays.asList(i, j));
                            }
                        }
                    } else xys.add(Arrays.asList(i, j));
                }
            }
            int check = 0, risk = 0;
            List<Integer> attack = new ArrayList<>();
            List<List<Integer>> defense = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == null || !board[i][j].firstPlayer()) {
                        if (attack.isEmpty() && xys.contains(Arrays.asList(i, j))) {
                            attack.add(i);
                            attack.add(j);
                        }
                    } else check++;
                }
                if (!attack.isEmpty() && check == 2) {
                    if (board[attack.get(0)][attack.get(1)] != null) {
                        for (int j = a.size() - 1; j >= 0; j--) {
                            if (a.get(j) > board[attack.get(0)][attack.get(1)].value())
                                return new Move(attack.get(0), attack.get(1), a.get(j));
                        }
                    }
                    return new Move(attack.get(0), attack.get(1), a.get(a.size() - 1));
                } else {
                    check = 0;
                    attack.clear();
                }
            }

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[j][i] == null || !board[j][i].firstPlayer()) {
                        if (attack.isEmpty() && xys.contains(Arrays.asList(j, i))) {
                            attack.add(j);
                            attack.add(i);
                        }
                    } else check++;
                }
                if (!attack.isEmpty() && check == 2) {
                    if (board[attack.get(0)][attack.get(1)] != null) {
                        for (int j = a.size() - 1; j >= 0; j--) {
                            if (a.get(j) > board[attack.get(0)][attack.get(1)].value())
                                return new Move(attack.get(0), attack.get(1), a.get(j));
                        }
                    }
                    return new Move(attack.get(0), attack.get(1), a.get(a.size() - 1));
                } else {
                    check = 0;
                    attack.clear();
                }
            }

            for (int i = 0; i < 3; i++) {
                if (board[i][i] == null || !board[i][i].firstPlayer()) {
                    if (attack.isEmpty() && xys.contains(Arrays.asList(i, i))) {
                        attack.add(i);
                        attack.add(i);
                    }
                } else check++;
            }
            if (check == 2 && !attack.isEmpty()) {
                if (board[attack.get(0)][attack.get(1)] != null) {
                    for (int j = a.size() - 1; j >= 0; j--) {
                        if (a.get(j) > board[attack.get(0)][attack.get(1)].value())
                            return new Move(attack.get(0), attack.get(1), a.get(j));
                    }
                }
                return new Move(attack.get(0), attack.get(1), a.get(a.size() - 1));
            } else {
                check = 0;
                attack.clear();
            }
            for (int i = 0; i < 3; ) {
                for (int j = 2; j >= 0; j--) {
                    if (board[i][j] == null || !board[i][j].firstPlayer()) {
                        if (attack.isEmpty() && xys.contains(Arrays.asList(i, j))) {
                            attack.add(i);
                            attack.add(j);
                        }
                    } else check++;
                    i++;
                }
            }
            if (check == 2 && !attack.isEmpty()) {
                if (board[attack.get(0)][attack.get(1)] != null) {
                    for (int j = a.size() - 1; j >= 0; j--) {
                        if (a.get(j) > board[attack.get(0)][attack.get(1)].value())
                            return new Move(attack.get(0), attack.get(1), a.get(j));
                    }
                }
                return new Move(attack.get(0), attack.get(1), a.get(a.size() - 1));
            }
//
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] != null) {
                        if (board[i][j].firstPlayer() && board[i][j].value() >= b.get(b.size() - 1)) {
                            risk = 0;
                            break;
                        }
                    }
                    if (board[i][j] != null && !board[i][j].firstPlayer()) {
                        risk++;
                    }
                }
                if (risk == 2) {
                    for (int j = 0; j < 3; j++) {
                        if (board[i][j] == null) defense.add(Arrays.asList(i, j));
                        if (board[i][j] != null && !board[i][j].firstPlayer()) {
                            if (xys.contains(Arrays.asList(i, j))){
                                defense.add(Arrays.asList(i, j));
                            }
                        }
                    }
                }
                risk = 0;
            }

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[j][i] != null) {
                        if (board[j][i].firstPlayer() && board[j][i].value() >= b.get(b.size() - 1)) {
                            risk = 0;
                            break;
                        }
                    }
                    if (board[j][i] != null && !board[j][i].firstPlayer()) {
                        risk++;
                    }
                }
                if (risk == 2) {
                    for (int j = 0; j < 3; j++) {
                        if (board[j][i] == null) defense.add(Arrays.asList(j, i));
                        if (board[j][i] != null && !board[j][i].firstPlayer()){
                            if (xys.contains(Arrays.asList(j, i))){
                                defense.add(Arrays.asList(j, i));
                            }
                        }
                    }
                }
                risk = 0;
            }

            for (int i = 0; i < 3; i++) {
                if (board[i][i] != null) {
                    if (board[i][i].firstPlayer() && board[i][i].value() >= b.get(b.size() - 1)) {
                        risk = 0;
                        break;
                    }
                }
                if (board[i][i] != null && !board[i][i].firstPlayer()) {
                    risk++;
                }
            }
            if (risk == 2) {
                for (int j = 0; j < 3; j++) {
                    if (board[j][j] == null) defense.add(Arrays.asList(j, j));
                    if (board[j][j] != null && !board[j][j].firstPlayer()){
                        if (xys.contains(Arrays.asList(j, j))){
                            defense.add(Arrays.asList(j, j));
                        }
                    }
                }
            }
            risk = 0;
            loop1:
            for (int i = 0; i < 3; ) {
                for (int j = 2; j >= 0; j--) {
                    if (board[i][j] != null){
                        if (board[i][j].firstPlayer() && board[i][j].value() >= b.get(b.size() - 1)) {
                            risk = 0;
                            break loop1;
                        }
                    }
                    if (board[i][j] != null && !board[i][j].firstPlayer()) {
                        risk++;
                    }
                    i++;
                }
            }
            if (risk == 2) {
                for (int i = 0; i < 3; ) {
                    for (int j = 2; j >= 0; j--) {
                        if (board[i][j] == null) defense.add(Arrays.asList(i, j));
                        if (board[i][j] != null && !board[i][j].firstPlayer()) {
                            if (xys.contains(Arrays.asList(i, j))){
                                defense.add(Arrays.asList(i, j));
                            }
                        }
                        i++;
                    }
                }
            }

            List<Integer> max = new ArrayList<>();
            if (!defense.isEmpty()) {
                for (int i = 0; i < defense.size(); i++) {
                    int count = 0;
                    List<Integer> temp = defense.get(i);
                    for (List<Integer> integers : defense) {
                        if (temp.equals(integers)) {
                            count++;
                        }
                    }
                    if (max.isEmpty()) {
                        max.add(temp.get(0));
                        max.add(temp.get(1));
                        max.add(count);
                    }else{
                        if (count > max.get(2)){
                            max.clear();
                            max.add(temp.get(0));
                            max.add(temp.get(1));
                            max.add(count);
                        }
                    }
                }
                if (board[max.get(0)][max.get(1)] != null){
                    if(a.get(a.size() - 1) > b.get(b.size() - 1)){
                        for (int i = 0; i < a.size(); i++) {
                            if (a.get(i) > board[max.get(0)][max.get(1)].value()) return new Move(max.get(0), max.get(1), a.get(i));
                        }
                    }
                }else{
                    if (a.get(a.size() - 1) > b.get(b.size() - 1)){
                        for (int k = a.size() - 1; k >= 0; k--) {
                            if (a.get(k) < b.get(b.size() -1)){
                                return new Move(max.get(0), max.get(1), a.get(k + 1));
                            }
                        }
                    }
                    if (a.get(a.size() - 1).equals(b.get(b.size() - 1))) return new Move(max.get(0), max.get(1), a.get(a.size() - 1));
                    return new Move(max.get(0), max.get(1), a.get(0));
                }
                for (int j = 0; j < a.size(); j++) {
                    if (a.get(j) > board[max.get(0)][max.get(1)].value()){
                        return new Move(max.get(0), max.get(1), a.get(j));
                    }
                }
            }
            if(b.size() == 1) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (board[i][j] == null) {
                            return new Move(i, j, a.get(0));
                        }
                    }
                }
            }
            if (xys.contains(Arrays.asList(1, 1))) {
                if (board[1][1] != null){
                    for (Integer integer : a) {
                        if (integer > board[1][1].value()) {
                            return new Move(1, 1, integer);
                        }
                    }
                }
                return new Move(1, 1, a.get(0));
            }
            int test = 0;
            List<Integer> test2 = new ArrayList<>();
            for (List<Integer> integers : xys) {
                int x = integers.get(0);
                int y = integers.get(1);
                if (x == 0 && y == 0) {
                    if (board[0][1] != null && board[0][1].firstPlayer() || board[0][2] != null && board[0][2].firstPlayer()) test++;
                    if (board[0][1] != null && !board[0][1].firstPlayer() && board[0][1].value() > a.get(a.size() - 1)
                    || board[0][2] != null && !board[0][2].firstPlayer() && board[0][2].value() > a.get(a.size() - 1)) {
                        test = 0;
                    }
                    if (board[1][0] != null && board[1][0].firstPlayer() || board[2][0] != null && board[2][0].firstPlayer()) test++;
                    if (board[1][0] != null && !board[1][0].firstPlayer() && board[1][0].value() > a.get(a.size() - 1)
                            || board[2][0] != null && !board[2][0].firstPlayer() && board[2][0].value() > a.get(a.size() - 1)) {
                        test = 0;
                    }
                    if (board[1][1] != null && board[1][1].firstPlayer() || board[2][2] != null && board[2][2].firstPlayer()) test++;
                    if (board[1][1] != null && !board[1][1].firstPlayer() && board[1][1].value() > a.get(a.size() - 1)
                            || board[2][2] != null && !board[2][2].firstPlayer() && board[2][2].value() > a.get(a.size() - 1)) {
                        test = 0;
                    }
                    if (test == 3) return new Move(0, 0, a.get(a.size() - 1));
                    if (test == 2 && test2.isEmpty()) {
                        test2.add(0);
                        test2.add(0);
                    }
                    test = 0;
                } else if (x == 0 && y == 2) {
                    if (board[0][1] != null && board[0][1].firstPlayer() || board[0][0] != null && board[0][0].firstPlayer()) test++;
                    if (board[0][1] != null && !board[0][1].firstPlayer() && board[0][1].value() > a.get(a.size() - 1)
                            || board[0][0] != null && !board[0][0].firstPlayer() && board[0][0].value() > a.get(a.size() - 1)) {
                        test = 0;
                    }
                    if (board[1][2] != null && board[1][2].firstPlayer() || board[2][2] != null && board[2][2].firstPlayer()) test++;
                    if (board[1][2] != null && !board[1][2].firstPlayer() && board[1][2].value() > a.get(a.size() - 1)
                            || board[2][2] != null && !board[2][2].firstPlayer() && board[2][2].value() > a.get(a.size() - 1)) {
                        test = 0;
                    }
                    if (board[1][1] != null && board[1][1].firstPlayer() || board[2][0] != null && board[2][0].firstPlayer()) test++;
                    if (board[1][1] != null && !board[1][1].firstPlayer() && board[1][1].value() > a.get(a.size() - 1)
                            || board[2][0] != null && !board[2][0].firstPlayer() && board[2][0].value() > a.get(a.size() - 1)) {
                        test = 0;
                    }
                    if (test == 3) return new Move(0, 2, a.get(a.size() - 1));
                    if (test == 2 && test2.isEmpty()) {
                        test2.add(0);
                        test2.add(2);
                    }
                    test = 0;
                }else if (x == 2 && y == 2) {
                    if (board[0][2] != null && board[0][2].firstPlayer() || board[1][2] != null && board[1][2].firstPlayer()) test++;
                    if (board[0][2] != null && !board[0][2].firstPlayer() && board[0][2].value() > a.get(a.size() - 1)
                            || board[1][2] != null && !board[1][2].firstPlayer() && board[1][2].value() > a.get(a.size() - 1)) {
                        test = 0;
                    }
                    if (board[2][1] != null && board[2][1].firstPlayer() || board[2][0] != null && board[2][0].firstPlayer()) test++;
                    if (board[2][1] != null && !board[2][1].firstPlayer() && board[2][1].value() > a.get(a.size() - 1)
                            || board[2][0] != null && !board[2][0].firstPlayer() && board[2][0].value() > a.get(a.size() - 1)) {
                        test = 0;
                    }
                    if (board[1][1] != null && board[1][1].firstPlayer() || board[0][0] != null && board[0][0].firstPlayer()) test++;
                    if (board[1][1] != null && !board[1][1].firstPlayer() && board[1][1].value() > a.get(a.size() - 1)
                            || board[0][0] != null && !board[0][0].firstPlayer() && board[0][0].value() > a.get(a.size() - 1)) {
                        test = 0;
                    }
                    if (test == 3) return new Move(2, 2, a.get(a.size() - 1));
                    if (test == 2 && test2.isEmpty()) {
                        test2.add(2);
                        test2.add(2);
                    }
                    test = 0;
                } else if (x == 2 && y == 0) {
                    if (board[2][1] != null && board[2][1].firstPlayer() || board[2][2] != null && board[2][2].firstPlayer()) test++;
                    if (board[2][1] != null && !board[2][1].firstPlayer() && board[2][1].value() > a.get(a.size() - 1)
                            || board[2][2] != null && !board[2][2].firstPlayer() && board[2][2].value() > a.get(a.size() - 1)) {
                        test = 0;
                    }
                    if (board[1][0] != null && board[1][0].firstPlayer() || board[0][0] != null && board[0][0].firstPlayer()) test++;
                    if (board[1][0] != null && !board[1][0].firstPlayer() && board[1][0].value() > a.get(a.size() - 1)
                            || board[0][0] != null && !board[0][0].firstPlayer() && board[0][0].value() > a.get(a.size() - 1)) {
                        test = 0;
                    }
                    if (board[1][1] != null && board[1][1].firstPlayer() || board[0][2] != null && board[0][2].firstPlayer()) test++;
                    if (board[1][1] != null && !board[1][1].firstPlayer() && board[1][1].value() > a.get(a.size() - 1)
                            || board[0][2] != null && !board[0][2].firstPlayer() && board[0][2].value() > a.get(a.size() - 1)) {
                        test = 0;
                    }
                    if (test == 3) return new Move(2, 0, a.get(a.size() - 1));
                    if (test == 2 && test2.isEmpty()) {
                        test2.add(2);
                        test2.add(0);
                    }
                    test = 0;
                }
            }
            if (!test2.isEmpty()){
                if (board[test2.get(0)][test2.get(1)] != null) {
                    if(a.get(a.size() - 1) > b.get(b.size() - 1)){
                        for (int i = 0; i < a.size(); i++) {
                            if (a.get(i) > board[test2.get(0)][test2.get(1)].value()) return new Move(test2.get(0), test2.get(1), a.get(i));
                        }
                    }
                    if (a.get(a.size() - 1).equals(b.get(b.size() - 1))) return new Move(test2.get(0), test2.get(1), a.get(a.size() - 1));
                }else{
                    if (a.get(a.size() - 1) > b.get(b.size() - 1)){
                        return new Move(test2.get(0), test2.get(1), a.get(0));
                    }
                    if (a.get(a.size() - 1).equals(b.get(b.size() - 1))) return new Move(test2.get(0), test2.get(1), a.get(a.size() - 1));
                    return new Move(test2.get(0), test2.get(1), a.get(0));
                }
                for (int j = 0; j < a.size(); j++) {
                    if (a.get(j) > board[test2.get(0)][test2.get(1)].value()){
                        return new Move(test2.get(0), test2.get(1), a.get(j));
                    }
                }
            }
            if (xys.contains(Arrays.asList(0, 0))) {
                if (board[0][0] != null) {
                    for (int i = 0; i < a.get(0); i++) {
                        if (a.get(i) > board[0][0].value()) {
                            return new Move(0, 0, a.get(i));
                        }
                    }
                }
            } else if (xys.contains(Arrays.asList(0, 2))) {
                if (board[0][2] != null) {
                    for (int i = 0; i < a.get(0); i++) {
                        if (a.get(i) > board[0][2].value()) {
                            return new Move(0, 2, a.get(i));
                        }
                    }
                }
            }else if (xys.contains(Arrays.asList(2, 2))) {
                if (board[2][2] != null) {
                    for (int i = 0; i < a.get(0); i++) {
                        if (a.get(i) > board[2][2].value()) {
                            return new Move(2, 2, a.get(i));
                        }
                    }
                }
            }else if (xys.contains(Arrays.asList(2, 0))) {
                if (board[2][0] != null) {
                    for (int i = 0; i < a.get(0); i++) {
                        if (a.get(i) > board[2][0].value()) {
                            return new Move(2, 0, a.get(i));
                        }
                    }
                }
            }
            if (xys.contains(Arrays.asList(0, 0))) {
                if (board[0][0] == null) {
                    return new Move(0, 0, a.get(0));
                }
            } else if (xys.contains(Arrays.asList(0, 2))) {
                if (board[0][2] == null) {
                    return new Move(0, 2, a.get(0));
                }
            }else if (xys.contains(Arrays.asList(2, 2))) {
                if (board[2][2] == null) {
                    return new Move(2, 2, a.get(0));
                }
            }else if (xys.contains(Arrays.asList(2, 0))) {
                if (board[2][0] == null) {
                    return new Move(2, 0, a.get(0));
                }
            }

            List<Integer> xy = xys.get(random.nextInt(xys.size()));
            int x = xy.get(0), y = xy.get(1);
            if (board[x][y] != null) {
                for (Integer integer : a) {
                    if (integer > board[x][y].value()) return new Move(x, y, integer);
                }
            }
            return new Move(x, y, a.get(0));
        } else {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] != null) {
                        if (board[i][j].firstPlayer()) {
                            for (int k = 0; k < 9; k++) {
                                if (!secondPlayedPieces[k] && k > board[i][j].value())
                                    xys.add(Arrays.asList(i, j));
                            }
                        }
                    } else xys.add(Arrays.asList(i, j));
                }
            }
            int check = 0, risk = 0;
            List<Integer> attack = new ArrayList<>();
            List<List<Integer>> defense = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] != null && !board[i][j].firstPlayer()) {
                        check++;
                    } else {
                        if (attack.isEmpty() && xys.contains(Arrays.asList(i, j))) {
                            attack.add(i);
                            attack.add(j);
                        }
                    }
                }
                if (!attack.isEmpty() && check == 2) {
                    if (board[attack.get(0)][attack.get(1)] != null) {
                        for (int j = b.size() - 1; j >= 0; j--) {
                            if (b.get(j) > board[attack.get(0)][attack.get(1)].value())
                                return new Move(attack.get(0), attack.get(1), b.get(j));
                        }
                    }
                    return new Move(attack.get(0), attack.get(1), b.get(b.size() - 1));
                } else {
                    check = 0;
                    attack.clear();
                }
            }

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[j][i] == null || board[j][i].firstPlayer()) {
                        if (attack.isEmpty() && xys.contains(Arrays.asList(j, i))) {
                            attack.add(j);
                            attack.add(i);
                        }
                    } else check++;
                }
                if (!attack.isEmpty() && check == 2) {
                    if (board[attack.get(0)][attack.get(1)] != null) {
                        for (int j = b.size() - 1; j >= 0; j--) {
                            if (b.get(j) > board[attack.get(0)][attack.get(1)].value())
                                return new Move(attack.get(0), attack.get(1), b.get(j));
                        }
                    }
                    return new Move(attack.get(0), attack.get(1), b.get(b.size() - 1));
                } else {
                    check = 0;
                    attack.clear();
                }
            }
            for (int i = 0; i < 3; i++) {
                if (board[i][i] == null || board[i][i].firstPlayer()) {
                    if (attack.isEmpty() && xys.contains(Arrays.asList(i, i))) {
                        attack.add(i);
                        attack.add(i);
                    }
                } else check++;
            }
            if (check == 2 && !attack.isEmpty()) {
                if (board[attack.get(0)][attack.get(1)] != null) {
                    for (int j = b.size() - 1; j >= 0; j--) {
                        if (b.get(j) > board[attack.get(0)][attack.get(1)].value())
                            return new Move(attack.get(0), attack.get(1), b.get(j));
                    }
                }
                return new Move(attack.get(0), attack.get(1), b.get(b.size() - 1));
            } else {
                check = 0;
                attack.clear();
            }
            for (int i = 0; i < 3; ) {
                for (int j = 2; j >= 0; j--) {
                    if (board[i][j] == null || board[i][j].firstPlayer()) {
                        if (attack.isEmpty() && xys.contains(Arrays.asList(i, j))) {
                            attack.add(i);
                            attack.add(j);
                        }
                    } else check++;
                    i++;
                }
            }
            if (check == 2 && !attack.isEmpty()) {
                if (board[attack.get(0)][attack.get(1)] != null) {
                    for (int j = b.size() - 1; j >= 0; j--) {
                        if (b.get(j) > board[attack.get(0)][attack.get(1)].value())
                            return new Move(attack.get(0), attack.get(1), b.get(j));
                    }
                }
                return new Move(attack.get(0), attack.get(1), b.get(b.size() - 1));
            }
//
            if (a.size() != 0){
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (board[i][j] != null) {
                            if (!board[i][j].firstPlayer() && board[i][j].value() >= a.get(a.size() - 1)) {
                                risk = 0;
                                break;
                            }
                        }
                        if (board[i][j] != null && board[i][j].firstPlayer()) {
                            risk++;
                        }
                    }
                    if (risk == 2) {
                        for (int j = 0; j < 3; j++) {
                            if (board[i][j] == null) defense.add(Arrays.asList(i, j));
                            if (board[i][j] != null && board[i][j].firstPlayer()) {
                                if (xys.contains(Arrays.asList(i, j))){
                                    defense.add(Arrays.asList(i, j));
                                }
                            }
                        }
                    }
                    risk = 0;
                }

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (board[j][i] != null) {
                            if (!board[j][i].firstPlayer() && board[j][i].value() >= a.get(a.size() - 1)) {
                                risk = 0;
                                break;
                            }
                        }
                        if (board[j][i] != null && board[j][i].firstPlayer()) {
                            risk++;
                        }
                    }
                    if (risk == 2) {
                        for (int j = 0; j < 3; j++) {
                            if (board[j][i] == null) defense.add(Arrays.asList(j, i));
                            if (board[j][i] != null && board[j][i].firstPlayer()){
                                if (xys.contains(Arrays.asList(j, i))){
                                    defense.add(Arrays.asList(j, i));
                                }
                            }
                        }
                    }
                    risk = 0;
                }

                for (int i = 0; i < 3; i++) {
                    if (board[i][i] != null) {
                        if (!board[i][i].firstPlayer() && board[i][i].value() >= a.get(a.size() - 1)) {
                            risk = 0;
                            break;
                        }
                    }
                    if (board[i][i] != null && board[i][i].firstPlayer()) {
                        risk++;
                    }
                }
                if (risk == 2) {
                    for (int j = 0; j < 3; j++) {
                        if (board[j][j] == null) defense.add(Arrays.asList(j, j));
                        if (board[j][j] != null && board[j][j].firstPlayer()){
                            if (xys.contains(Arrays.asList(j, j))){
                                defense.add(Arrays.asList(j, j));
                            }
                        }
                    }
                }
                risk = 0;
                loop1:
                for (int i = 0; i < 3; ) {
                    for (int j = 2; j >= 0; j--) {
                        if (board[i][j] != null){
                            if (!board[i][j].firstPlayer() && board[i][j].value() >= a.get(a.size() - 1)) {
                                risk = 0;
                                break loop1;
                            }
                        }
                        if (board[i][j] != null && board[i][j].firstPlayer()) {
                            risk++;
                        }
                        i++;
                    }
                }
                if (risk == 2) {
                    for (int i = 0; i < 3; ) {
                        for (int j = 2; j >= 0; j--) {
                            if (board[i][j] == null) defense.add(Arrays.asList(i, j));
                            if (board[i][j] != null && board[i][j].firstPlayer()) {
                                if (xys.contains(Arrays.asList(i, j))){
                                    defense.add(Arrays.asList(i, j));
                                }
                            }
                            i++;
                        }
                    }
                }

                List<Integer> max = new ArrayList<>();
                if (!defense.isEmpty()) {
                    for (int i = 0; i < defense.size(); i++) {
                        int count = 0;
                        List<Integer> temp = defense.get(i);
                        for (List<Integer> integers : defense) {
                            if (temp.equals(integers)) {
                                count++;
                            }
                        }
                        if (max.isEmpty()) {
                            max.add(temp.get(0));
                            max.add(temp.get(1));
                            max.add(count);
                        }else{
                            if (count > max.get(2)){
                                max.clear();
                                max.add(temp.get(0));
                                max.add(temp.get(1));
                                max.add(count);
                            }
                        }
                    }
                    if (board[max.get(0)][max.get(1)] != null){
                        if(b.get(b.size() - 1) > a.get(a.size() - 1)){
                            for (int i = 0; i < b.size(); i++) {
                                if (b.get(i) > board[max.get(0)][max.get(1)].value()) return new Move(max.get(0), max.get(1), b.get(i));
                            }
                        }
                    }else{
                        if (b.get(b.size() - 1) > a.get(a.size() - 1)){
                            for (int k = b.size() - 1; k >= 0; k--) {
                                if (b.get(k) < a.get(a.size() -1)){
                                    return new Move(max.get(0), max.get(1), b.get(k + 1));
                                }
                            }
                        }
                        if (a.get(a.size() - 1).equals(b.get(b.size() - 1))) return new Move(max.get(0), max.get(1), b.get(b.size() - 1));
                        return new Move(max.get(0), max.get(1), b.get(0));
                    }
                    for (int j = 0; j < b.size(); j++) {
                        if (b.get(j) > board[max.get(0)][max.get(1)].value()){
                            return new Move(max.get(0), max.get(1), b.get(j));
                        }
                    }
                }
            }
            if(a.size() == 0 || a.size() == 1) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (board[i][j] == null) {
                            return new Move(i, j, b.get(0));
                        }
                    }
                }
            }
            if (xys.contains(Arrays.asList(1, 1))) {
                if (board[1][1] != null){
                    for (Integer integer : b) {
                        if (integer > board[1][1].value()) {
                            return new Move(1, 1, integer);
                        }
                    }
                }
                return new Move(1, 1, b.get(0));
            }
            int test = 0;
            List<Integer> test2 = new ArrayList<>();
            for (List<Integer> integers : xys) {
                int x = integers.get(0);
                int y = integers.get(1);
                if (x == 0 && y == 0) {
                    if (board[0][1] != null && !board[0][1].firstPlayer() || board[0][2] != null && !board[0][2].firstPlayer()) test++;
                    if (board[0][1] != null && board[0][1].firstPlayer() && board[0][1].value() > b.get(b.size() - 1)
                            || board[0][2] != null && board[0][2].firstPlayer() && board[0][2].value() > b.get(b.size() - 1)) {
                        test = 0;
                    }
                    if (board[1][0] != null && !board[1][0].firstPlayer() || board[2][0] != null && !board[2][0].firstPlayer()) test++;
                    if (board[1][0] != null && board[1][0].firstPlayer() && board[1][0].value() > b.get(b.size() - 1)
                            || board[2][0] != null && board[2][0].firstPlayer() && board[2][0].value() > b.get(b.size() - 1)) {
                        test = 0;
                    }
                    if (board[1][1] != null && !board[1][1].firstPlayer() || board[2][2] != null && !board[2][2].firstPlayer()) test++;
                    if (board[1][1] != null && board[1][1].firstPlayer() && board[1][1].value() > b.get(b.size() - 1)
                            || board[2][2] != null && board[2][2].firstPlayer() && board[2][2].value() > b.get(b.size() - 1)) {
                        test = 0;
                    }
                    if (test == 3) return new Move(0, 0, b.get(b.size() - 1));
                    if (test == 2 && test2.isEmpty()) {
                        test2.add(0);
                        test2.add(0);
                    }
                    test = 0;
                } else if (x == 0 && y == 2) {
                    if (board[0][1] != null && !board[0][1].firstPlayer() || board[0][0] != null && !board[0][0].firstPlayer()) test++;
                    if (board[0][1] != null && board[0][1].firstPlayer() && board[0][1].value() > b.get(b.size() - 1)
                            || board[0][0] != null && board[0][0].firstPlayer() && board[0][0].value() > b.get(b.size() - 1)) {
                        test = 0;
                    }
                    if (board[1][2] != null && !board[1][2].firstPlayer() || board[2][2] != null && !board[2][2].firstPlayer()) test++;
                    if (board[1][2] != null && board[1][2].firstPlayer() && board[1][2].value() > b.get(b.size() - 1)
                            || board[2][2] != null && board[2][2].firstPlayer() && board[2][2].value() > b.get(b.size() - 1)) {
                        test = 0;
                    }
                    if (board[1][1] != null && !board[1][1].firstPlayer() || board[2][0] != null && !board[2][0].firstPlayer()) test++;
                    if (board[1][1] != null && board[1][1].firstPlayer() && board[1][1].value() > b.get(b.size() - 1)
                            || board[2][0] != null && board[2][0].firstPlayer() && board[2][0].value() > b.get(b.size() - 1)) {
                        test = 0;
                    }
                    if (test == 3) return new Move(0, 2, b.get(b.size() - 1));
                    if (test == 2 && test2.isEmpty()) {
                        test2.add(0);
                        test2.add(2);
                    }
                    test = 0;
                }else if (x == 2 && y == 2) {
                    if (board[0][2] != null && !board[0][2].firstPlayer() || board[1][2] != null && !board[1][2].firstPlayer()) test++;
                    if (board[0][2] != null && board[0][2].firstPlayer() && board[0][2].value() > b.get(b.size() - 1)
                            || board[1][2] != null && board[1][2].firstPlayer() && board[1][2].value() > b.get(b.size() - 1)) {
                        test = 0;
                    }
                    if (board[2][1] != null && !board[2][1].firstPlayer() || board[2][0] != null && !board[2][0].firstPlayer()) test++;
                    if (board[2][1] != null && board[2][1].firstPlayer() && board[2][1].value() > b.get(b.size() - 1)
                            || board[2][0] != null && board[2][0].firstPlayer() && board[2][0].value() > b.get(b.size() - 1)) {
                        test = 0;
                    }
                    if (board[1][1] != null && !board[1][1].firstPlayer() || board[0][0] != null && !board[0][0].firstPlayer()) test++;
                    if (board[1][1] != null && board[1][1].firstPlayer() && board[1][1].value() > b.get(b.size() - 1)
                            || board[0][0] != null && board[0][0].firstPlayer() && board[0][0].value() > b.get(b.size() - 1)) {
                        test = 0;
                    }
                    if (test == 3) return new Move(2, 2, b.get(b.size() - 1));
                    if (test == 2 && test2.isEmpty()) {
                        test2.add(2);
                        test2.add(2);
                    }
                    test = 0;
                } else if (x == 2 && y == 0) {
                    if (board[2][1] != null && !board[2][1].firstPlayer() || board[2][2] != null && !board[2][2].firstPlayer()) test++;
                    if (board[2][1] != null && board[2][1].firstPlayer() && board[2][1].value() > b.get(b.size() - 1)
                            || board[2][2] != null && board[2][2].firstPlayer() && board[2][2].value() > b.get(b.size() - 1)) {
                        test = 0;
                    }
                    if (board[1][0] != null && !board[1][0].firstPlayer() || board[0][0] != null && !board[0][0].firstPlayer()) test++;
                    if (board[1][0] != null && board[1][0].firstPlayer() && board[1][0].value() > b.get(b.size() - 1)
                            || board[0][0] != null && board[0][0].firstPlayer() && board[0][0].value() > b.get(b.size() - 1)) {
                        test = 0;
                    }
                    if (board[1][1] != null && !board[1][1].firstPlayer() || board[0][2] != null && !board[0][2].firstPlayer()) test++;
                    if (board[1][1] != null && board[1][1].firstPlayer() && board[1][1].value() > b.get(b.size() - 1)
                            || board[0][2] != null && board[0][2].firstPlayer() && board[0][2].value() > b.get(b.size() - 1)) {
                        test = 0;
                    }
                    if (test == 3) return new Move(2, 0, b.get(b.size() - 1));
                    if (test == 2 && test2.isEmpty()) {
                        test2.add(2);
                        test2.add(0);
                    }
                    test = 0;
                }
            }
            if (!test2.isEmpty()){
                if (a.size() != 0){
                    if (board[test2.get(0)][test2.get(1)] != null) {
                        if(b.get(b.size() - 1) > a.get(a.size() - 1)){
                            for (int i = 0; i < b.size(); i++) {
                                if (b.get(i) > board[test2.get(0)][test2.get(1)].value()) return new Move(test2.get(0), test2.get(1), b.get(i));
                            }
                        }
                        if (b.get(b.size() - 1).equals(a.get(a.size() - 1))) return new Move(test2.get(0), test2.get(1), b.get(b.size() - 1));
                    }else{
                        if (b.get(b.size() - 1) > a.get(a.size() - 1)){
                            return new Move(test2.get(0), test2.get(1), b.get(0));
                        }
                        if (b.get(b.size() - 1).equals(a.get(a.size() - 1))) return new Move(test2.get(0), test2.get(1), b.get(b.size() - 1));
                        return new Move(test2.get(0), test2.get(1), b.get(0));
                    }
                }
                for (int j = 0; j < b.size(); j++) {
                    if (b.get(j) > board[test2.get(0)][test2.get(1)].value()){
                        return new Move(test2.get(0), test2.get(1), b.get(j));
                    }
                }
            }

            if (xys.contains(Arrays.asList(0, 0))) {
                if (board[0][0] != null) {
                    for (int i = 0; i < b.get(0); i++) {
                        if (b.get(i) > board[0][0].value()) {
                            return new Move(0, 0, b.get(i));
                        }
                    }
                }
            } else if (xys.contains(Arrays.asList(0, 2))) {
                if (board[0][2] != null) {
                    for (int i = 0; i < b.get(0); i++) {
                        if (b.get(i) > board[0][2].value()) {
                            return new Move(0, 2, b.get(i));
                        }
                    }
                }
            }else if (xys.contains(Arrays.asList(2, 2))) {
                if (board[2][2] != null) {
                    for (int i = 0; i < b.get(0); i++) {
                        if (b.get(i) > board[2][2].value()) {
                            return new Move(2, 2, b.get(i));
                        }
                    }
                }
            }else if (xys.contains(Arrays.asList(2, 0))) {
                if (board[2][0] != null) {
                    for (int i = 0; i < b.get(0); i++) {
                        if (b.get(i) > board[2][0].value()) {
                            return new Move(2, 0, b.get(i));
                        }
                    }
                }
            }
            if (xys.contains(Arrays.asList(0, 0))) {
                if (board[0][0] == null) {
                    return new Move(0, 0, b.get(0));
                }
            } else if (xys.contains(Arrays.asList(0, 2))) {
                if (board[0][2] == null) {
                    return new Move(0, 2, b.get(0));
                }
            }else if (xys.contains(Arrays.asList(2, 2))) {
                if (board[2][2] == null) {
                    return new Move(2, 2, b.get(0));
                }
            }else if (xys.contains(Arrays.asList(2, 0))) {
                if (board[2][0] == null) {
                    return new Move(2, 0, b.get(0));
                }
            }
            List<Integer> xy = xys.get(random.nextInt(xys.size()));
            int x = xy.get(0), y = xy.get(1);
            if (board[x][y] != null) {
                for (Integer integer : b) {
                    if (integer > board[x][y].value()) return new Move(x, y, integer);
                }
            }
            return new Move(x, y, b.get(0));
        }
    }
}
