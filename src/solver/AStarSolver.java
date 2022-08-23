package solver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Set;

public class AStarSolver implements Solver {

    public static class Path {
        public final List<Move> moves;
        public final Level level;
        public int metric;

        public Path(Level level){
            moves = new ArrayList<>();
            this.level = level;
            this.calculateMetric();
        }

        public Path(Path parent, Move move){
            this.moves = new ArrayList<>();
            this.moves.addAll(parent.moves);
            this.moves.add(move);
            this.level = parent.level.applyMove(move);
            this.calculateMetric();
        }

        private void calculateMetric(){
            for(Container c : level.containers){
                int same = 0;
                int last = c.getTopColor();
                for(int color : c.content){
                    if(color != last){
                        last = color;
                        same = 0;
                    } else {
                        same ++;
                    }
                }
                this.metric += c.capacity - same;
            }
            this.metric += moves.size();
        }

    }

    @Override
    public Optional<List<Move>> solve(Level initialLevel) {
        PriorityQueue<Path> paths = new PriorityQueue<>((a,b) -> Integer.compare(a.metric, b.metric));
        paths.add(new Path(initialLevel));
        Set<Level> visited = new HashSet<>();
        visited.add(initialLevel);

        int expanded = 0;

        while(!paths.isEmpty()){

            Path next = paths.remove();

            for(Move m : next.level.getMoves()){
                Path newPath = new Path(next, m);
                Level newLevel = newPath.level;
                expanded ++;
                if(!visited.add(newLevel))
                    continue;
                if(newLevel.isSolved()) {
                    // System.out.printf("Expanded %d nodes\n", expanded);
                    return Optional.of(newPath.moves);
                }
                paths.add(newPath);
            }

        }
        
        return Optional.empty();
    }
    


}
