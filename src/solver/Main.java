package solver;

import java.util.List;
import java.util.Optional;

public class Main {
    
    public static void main(String[] args){
        Level level = new Level();
        
        int LIGHT_GREEN = 1;
        int RED = 2;
        int PURPLE = 3;
        int BROWN = 4;
        int GREY = 5;
        int ORANGE = 6;
        int LIGHT_BLUE = 7;
        int PINK = 8;
        int BLUE = 9;
        int GREEN = 10;

        Container tmp = new Container(4);
        tmp.content.push(PURPLE);
        tmp.content.push(PURPLE);
        tmp.content.push(RED);
        tmp.content.push(LIGHT_GREEN);
        level.containers.add(tmp);

        tmp = new Container(4);
        tmp.content.push(LIGHT_GREEN);
        tmp.content.push(RED);
        tmp.content.push(PINK);
        tmp.content.push(BROWN);
        level.containers.add(tmp);

        tmp = new Container(4);
        tmp.content.push(LIGHT_GREEN);
        tmp.content.push(RED);
        tmp.content.push(GREY);
        tmp.content.push(BROWN);
        level.containers.add(tmp);

        tmp = new Container(4);
        tmp.content.push(PURPLE);
        tmp.content.push(GREEN);
        tmp.content.push(PINK);
        tmp.content.push(ORANGE);
        level.containers.add(tmp);

        tmp = new Container(4);
        tmp.content.push(GREEN);
        tmp.content.push(LIGHT_BLUE);
        tmp.content.push(GREY);
        tmp.content.push(LIGHT_BLUE);
        level.containers.add(tmp);

        tmp = new Container(4);
        tmp.content.push(BLUE);
        tmp.content.push(ORANGE);
        tmp.content.push(GREY);
        tmp.content.push(RED);
        level.containers.add(tmp);

        tmp = new Container(4);
        tmp.content.push(GREEN);
        tmp.content.push(GREEN);
        tmp.content.push(ORANGE);
        tmp.content.push(PURPLE);
        level.containers.add(tmp);
        
        tmp = new Container(4);
        tmp.content.push(BROWN);
        tmp.content.push(PINK);
        tmp.content.push(GREY);
        tmp.content.push(BLUE);
        level.containers.add(tmp);

        tmp = new Container(4);
        tmp.content.push(LIGHT_GREEN);
        tmp.content.push(BLUE);
        tmp.content.push(LIGHT_BLUE);
        tmp.content.push(ORANGE);
        level.containers.add(tmp);

        tmp = new Container(4);
        tmp.content.push(PINK);
        tmp.content.push(LIGHT_BLUE);
        tmp.content.push(BROWN);
        tmp.content.push(BLUE);
        level.containers.add(tmp);

        tmp = new Container(4);
        level.containers.add(tmp);
        
        tmp = new Container(4);
        level.containers.add(tmp);

        level = LevelCreator.createLevel(6, 2, 8, 100_000);
        System.out.println(level);
        System.out.println(level.isSolved());
        Solver solver = new AStarSolver();   
        Optional<List<Move>> path = Optional.empty();  

        // make sure jit compiler optimized everything
        path = solver.solve(level);
        boolean benchmark = true;
        if(benchmark){
            long start = System.currentTimeMillis();
            for(int i = 0; i < 100; i++)
                path = solver.solve(level);
            long end = System.currentTimeMillis();

            System.out.printf("took %dms. for 100 solves\n", end - start);
        }

        if(path.isEmpty()){
            System.err.println("keine Lösung gefunden!");
        } else {
            System.out.printf("found solution with %d moves\n", path.get().size());
            Level solved = level;
            for(Move m : path.get()){
                solved = solved.applyMove(m);
            }

            System.out.println(solved.isSolved());
        }
    }

}
