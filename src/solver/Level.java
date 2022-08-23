package solver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

public class Level {

    public List<Container> containers;

    public Level(){
        this.containers = new ArrayList<>();
    }

    public boolean isSolved(){
        return containers.stream().allMatch(Container::isSolved);
    }

    public List<Move> getMoves(){
        List<Move> moves = new ArrayList<>();
        for(int fromIdx = 0; fromIdx < containers.size(); fromIdx++){
            for(int toIdx = 0; toIdx < containers.size(); toIdx++){
                if(fromIdx == toIdx)
                    continue;
                
                if(this.containers.get(fromIdx).canPutIn(this.containers.get(toIdx)))
                    moves.add(new Move(fromIdx, toIdx));
            }
        }
        return moves;
    }


    public Level applyMove(Move move){
        // copy level
        Level result = new Level();
        for(Container container : this.containers)
            result.containers.add(container);

        // do move
        Container fromCpy = this.containers.get(move.fromIdx).copy();
        Container toCpy = this.containers.get(move.toIdx).copy();
        while(fromCpy.canPutIn(toCpy)){
            toCpy.content.push(fromCpy.content.pop());
        }
        result.containers.set(move.fromIdx, fromCpy);
        result.containers.set(move.toIdx, toCpy);

        return result;
    }

    @Override
    public String toString(){
        StringJoiner joiner = new StringJoiner("\n");
        for(Container c: containers){
            joiner.add(c.toString());
        }
        return joiner.toString();
    }

    @Override
    public int hashCode(){
        return containers.hashCode();
    }

    @Override
    public boolean equals(Object other){
        if(other instanceof Level level){
            Set<Container> me = new HashSet<>();
            Set<Container> oth = new HashSet<>();
            me.addAll(this.containers);
            oth.addAll(level.containers);
            return me.equals(oth); 
        }
        return false;
    }

}