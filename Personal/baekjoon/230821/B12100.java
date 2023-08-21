import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B12100{
  // 상 우 하 좌
  static final int[][] dir = {{-1, 0},{0, 1},{1, 0},{0, -1}};
  static int MAX_MOVE = 5; // 최대 이동할 수 있는 횟수
  public static void main(String[] args) throws Exception{
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(in.readLine()); // 보드의 크기
    int[][] board = new int[N][N];
    for(int y = 0; y < N; y++){
      String[] input = in.readLine().split(" ");
      for(int x = 0; x < N; x++)
        board[y][x] = Integer.parseInt(input[x]);
    } // 보드 모두 입력받음.
    for(int type = 0; type < 4; type++){
      int[][] temp = copyBoard(board);
      solve(temp, 0);
    }
  }
  private static void solve(int[][] board, int count) {
    if(count == MAX_MOVE){
      return;
    }
  }

  static int[][] copyBoard(int[][] board){
    int N = board.length;
    int[][] temp = new int[N][N];
    for(int i = 0; i < N; i++)
      System.arraycopy(board[i], 0, temp[i], 0, N);
    return temp;
  }
}