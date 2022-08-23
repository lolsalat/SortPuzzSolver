package solver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class BruteforceSolver implements Solver {

    public static class Path {
        public final List<Move> moves;
        public final Level level;

        public Path(Level level){
            moves = new ArrayList<>();
            this.level = level;
        }

    }

    @Override
    public Optional<List<Move>> solve(Level initialLevel) {
        
        List<Path> states = new ArrayList<>();
        Set<Level> visited = new HashSet<>();
        states.add(new Path(initialLevel));
        visited.add(initialLevel);

        int iterations = 0;
        int removed = 0;
        int expanded = 0;

        while(!states.isEmpty()){
            System.out.printf("iterations: %d (removed %d entries, expanded %d entries)\n", ++iterations, removed, expanded);
            List<Path> newStates = new ArrayList<>();

            for(Path path : states){
                Level level = path.level;
                List<Move> moves = level.getMoves();
                for(Move move : moves){
                    Level newLevel = level.applyMove(move);
                    expanded ++;

                    if(!visited.add(newLevel)) {
                        removed ++;
                        continue;
                    }

                    Path newPath = new Path(newLevel);
                    newPath.moves.addAll(path.moves);
                    newPath.moves.add(move);
                    if(newLevel.isSolved()){
                        return Optional.of(newPath.moves);
                    }
                    newStates.add(newPath);
                }
            }

            states = newStates;
        }

        return Optional.empty();
    }
    


}
