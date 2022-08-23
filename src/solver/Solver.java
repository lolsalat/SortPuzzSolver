package solver;

import java.util.List;
import java.util.Optional;

public interface Solver {
    
    public Optional<List<Move>> solve(Level level);

}
