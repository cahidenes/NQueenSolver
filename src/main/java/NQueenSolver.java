import org.optaplanner.core.api.score.buildin.simple.SimpleScore;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.config.score.director.ScoreDirectorFactoryConfig;
import org.optaplanner.core.config.solver.SolverConfig;
import org.optaplanner.core.config.solver.termination.TerminationConfig;

import java.time.Duration;

import static java.lang.System.currentTimeMillis;

public class NQueenSolver {
    public static void main(String[] args) {
        SolverFactory<Board> solverFactory = SolverFactory.createFromXmlResource("nQueenSolverConfig.xml");
//        SolverFactory<Board> solverFactory = SolverFactory.create(new SolverConfig()
//                .withSolutionClass(Board.class)
//                .withEntityClasses(Queen.class)
////                .withConstraintProviderClass(NQueenConstraintProvider.class)
////                .withEasyScoreCalculatorClass(NQueenSimpleScoreCalculator.class)
//                .withScoreDirectorFactory(new ScoreDirectorFactoryConfig().withIncrementalScoreCalculatorClass(NQueenIncrementalScoreCalculator.class))
//                .withTerminationConfig(new TerminationConfig().withBestScoreLimit(SimpleScore.ZERO.toShortString()))
//        );

        Solver<Board> solver = solverFactory.buildSolver();
        Board initialBoard = new Board(16);
        long time = currentTimeMillis();
        Board result = solver.solve(initialBoard);
        System.out.println(currentTimeMillis() - time);

        System.out.println(result);

        System.out.println(result.getScore());
    }
}
