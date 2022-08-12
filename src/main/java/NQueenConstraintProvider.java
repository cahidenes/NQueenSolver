import org.optaplanner.core.api.score.buildin.simple.SimpleScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;
import org.optaplanner.core.api.score.stream.Joiners;

public class NQueenConstraintProvider implements ConstraintProvider {
    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[] {
                sameRow(constraintFactory),
                sameDia1(constraintFactory),
                sameDia2(constraintFactory)
        };
    }

    private Constraint sameRow(ConstraintFactory constraintFactory) {
        return constraintFactory.forEach(Queen.class).join(Queen.class,
                Joiners.equal(Queen::getRow),
                Joiners.lessThan(Queen::getColumn)
                ).penalize("sameRow", SimpleScore.ONE);
    }

    private Constraint sameDia1(ConstraintFactory constraintFactory) {
        return constraintFactory.forEach(Queen.class).join(Queen.class,
                Joiners.equal(Queen::getDia1),
                Joiners.lessThan(Queen::getColumn)
                ).penalize("sameDia1", SimpleScore.ONE);
    }

    private Constraint sameDia2(ConstraintFactory constraintFactory) {
        return constraintFactory.forEach(Queen.class).join(Queen.class,
                Joiners.equal(Queen::getDia2),
                Joiners.lessThan(Queen::getColumn)
                ).penalize("sameDia2", SimpleScore.ONE);
    }
}
