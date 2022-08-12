import org.optaplanner.core.api.score.Score;
import org.optaplanner.core.api.score.buildin.simple.SimpleScore;
import org.optaplanner.core.api.score.calculator.EasyScoreCalculator;

import java.util.Objects;

public class NQueenSimpleScoreCalculator implements EasyScoreCalculator<Board, SimpleScore> {
    @Override
    public SimpleScore calculateScore(Board board) {
        int score = 0;
        for (Queen queen : board.getQueenList()) {
            if (queen.getRow() == null) continue;
            for (Queen otherQueen : board.getQueenList()) {
                if (otherQueen.getRow() == null) continue;

                if (queen.getColumn() >= otherQueen.getColumn())
                    continue;

                if (Objects.equals(queen.getRow(), otherQueen.getRow()))
                    score--;

                if (queen.getDia1() == otherQueen.getDia1())
                    score--;

                if (queen.getDia2() == otherQueen.getDia2())
                    score--;
            }
        }
        return SimpleScore.of(score);
    }
}
