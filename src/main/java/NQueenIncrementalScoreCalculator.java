import org.optaplanner.core.api.score.Score;
import org.optaplanner.core.api.score.buildin.simple.SimpleScore;
import org.optaplanner.core.api.score.calculator.IncrementalScoreCalculator;

import java.util.ArrayList;
import java.util.Random;

public class NQueenIncrementalScoreCalculator implements IncrementalScoreCalculator<Board, SimpleScore> {

    int score;
    ArrayList<Integer> rowList;
    ArrayList<Integer> dia1List;
    ArrayList<Integer> dia2List;

    int n;

    @Override
    public void resetWorkingSolution(Board board) {
        score = 0;
        n = board.getN();
        rowList = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            rowList.add(0);
        }
        dia1List = new ArrayList<>(2*n);
        dia2List = new ArrayList<>(2*n);
        for (int i = 0; i < 2*n; i++) {
            dia1List.add(0);
            dia2List.add(0);
        }

        for (Queen queen: board.getQueenList()) {
            this.afterVariableChanged(queen, "row");
        }
    }

    @Override
    public void beforeEntityAdded(Object o) {
        System.out.println("Before Entity Added");
        assert false;
    }

    @Override
    public void afterEntityAdded(Object o) {
        System.out.println("After Entity Added");
        assert false;
    }

    @Override
    public void beforeVariableChanged(Object o, String s) {
        Queen queen = (Queen) o;
        if (queen.getRow() == null) return;

        rowList.set(queen.getRow(), rowList.get(queen.getRow())-1);
        if (rowList.get(queen.getRow()) == 1) score--;

        dia1List.set(queen.getDia1(), dia1List.get(queen.getDia1())-1);
        if (dia1List.get(queen.getDia1()) == 1) score--;

        dia2List.set(queen.getDia2()+n, dia2List.get(queen.getDia2()+n) - 1);
        if (dia2List.get(queen.getDia2()+n) == 1) score--;
    }

    @Override
    public void afterVariableChanged(Object o, String s) {
        Queen queen = (Queen) o;
        if (queen.getRow() == null) return;

        rowList.set(queen.getRow(), rowList.get(queen.getRow()) + 1);
        if (rowList.get(queen.getRow()) == 2) score++;

        dia1List.set(queen.getDia1(), dia1List.get(queen.getDia1()) + 1);
        if (dia1List.get(queen.getDia1()) == 2) score++;

        dia2List.set(queen.getDia2()+n, dia2List.get(queen.getDia2()+n) + 1);
        if (dia2List.get(queen.getDia2()+n) == 2) score++;
    }

    @Override
    public void beforeEntityRemoved(Object o) {
        System.out.println("Before Entity Removed");
        assert false;
    }

    @Override
    public void afterEntityRemoved(Object o) {
        System.out.println("After Entity Removed");
        assert false;
    }

    @Override
    public SimpleScore calculateScore() {
        return SimpleScore.of(-score);
    }
}
