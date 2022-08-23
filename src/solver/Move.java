package solver;

public class Move {
    
    public final int fromIdx, toIdx;

    public Move(int fromIdx, int toIdx){
        this.fromIdx = fromIdx;
        this.toIdx = toIdx;
    }

    @Override
    public String toString(){
        return String.format("%d => %d", fromIdx, toIdx);
    }

}
