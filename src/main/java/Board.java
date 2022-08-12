import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.simple.SimpleScore;

import java.util.ArrayList;
import java.util.List;

@PlanningSolution
public class Board {
    @ValueRangeProvider(id = "row")
    @ProblemFactCollectionProperty
    private List<Integer> rowList;

    @PlanningEntityCollectionProperty
    private List<Queen> queenList;

    @PlanningScore
    private SimpleScore score;

    int n;

    public Board() {}

    public Board(int n) {
        this.n = n;
        rowList = new ArrayList<>(n);
        queenList = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            rowList.add(i);
            queenList.add(new Queen(i));
        }
    }

    public List<Integer> getRowList() {
        return rowList;
    }

    public List<Queen> getQueenList() {
        return queenList;
    }

    public SimpleScore getScore() {
        return score;
    }

    public int getN() {
        return n;
    }

    @Override
    public String toString() {
        int[][] board = new int[n][n];
        for (Queen queen: queenList) {
            if (queen.getRow() != null) {
                board[queen.getRow()][queen.getColumn()] = 1;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 1) {
                    sb.append("Q ");
                } else {
                    sb.append("- ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
