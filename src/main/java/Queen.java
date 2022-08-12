import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity
public class Queen {

    @PlanningId
    private Integer column;

    @PlanningVariable(valueRangeProviderRefs = {"row"})
    private Integer row;

    public Queen() {}
    public Queen(int column) {
        this.column = column;
    }

    public Integer getColumn() {
        return column;
    }

    public Integer getRow() {
        return row;
    }

    public int getDia1() {
        return column + row;
    }

    public int getDia2() {
        return column - row;
    }
}
