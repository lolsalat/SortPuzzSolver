package solver;

import java.util.Arrays;
import java.util.Random;

public class LevelCreator {

    private static Random r = new Random();

    private static boolean applyMove(int[][] cs, int first, int second){
        if(cs[first][0] != 0 && cs[second][cs[second].length - 1] == 0){

            int color = -1;
            int i;
            for(i = 0; i < cs[first].length; i++){
                if(cs[first][i] == 0){
                    break;
                }
                color = cs[first][i];
            }
            cs[first][i-1] = 0;
            
            for(i = cs[second].length - 1; i >= 0; i--){
                if(cs[second][i] != 0)
                    break;
            }
            cs[second][i+1] = color;

            return true;
        }
        return false;
    }

    private static void randomMove(int[][] cs){
        int first;
        do { 
            first = r.nextInt(cs.length);
        } while(cs[first][0] == 0);
        int second;
        do{
            second = r.nextInt(cs.length);
        }  while(second == first);

        for(int i = 0;  i < cs.length; i++){
            if(applyMove(cs, first, second))
                return;
            first ++;
            first %= cs.length;
        }
    }

    public static Level createLevel(int containers, int empty, int height, int iterations){
        int[][] cs = new int[containers][height];

        for(int i = 0; i < containers - empty; i++){
            final int j = i;
            Arrays.setAll(cs[i], x -> j+1);
        }

        for(int i = 0; i < iterations; i++){
            randomMove(cs);
        }

        Level level = new Level();
        for(int i = 0; i < containers; i++){
            Container c = new Container(height);
            for(int j = 0; j < height; j++){
                int val = cs[i][height - 1 - j];
                if(val != 0)
                    c.content.add(val);
            }
            level.containers.add(c);
        }
        return level;
    }

}
