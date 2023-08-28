import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class B14502{
  private static int zero_count;
  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    String[] input = in.readLine().split(" ");

    int height = Integer.parseInt(input[0]);
    int width = Integer.parseInt(input[1]);

    int[][] room = new int[height][width];

    zero_count = 0;
    List<int[]> virusList = new ArrayList<>();
    for(int y = 0; y < height; y++){
      input = in.readLine().split(" ");
      for(int x = 0; x < width; x++){
        room[y][x] = Integer.parseInt(input[x]);
        if(room[y][x] == 0) zero_count++;
        else if(room[y][x] == 2)  virusList.add(new int[] {y, x});
      }
    }
    System.out.println("");
  }

  static void solve(int[][] board){
      
  }
}