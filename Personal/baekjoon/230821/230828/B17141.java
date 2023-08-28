import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class B17141 {
  static int bomb_count, empty_count;
  static List<int[]> bombList = new ArrayList<>();
  static int min_time = -1;
  static final int[][] dir = {{-1, 0},{1, 0},{0, -1},{0, 1}}; // 상 하 좌 우
  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    String[] input = in.readLine().split(" ");
    int length = Integer.parseInt(input[0]);
    bomb_count = Integer.parseInt(input[1]);

    int[][] init_map = new int[length][length];
    for(int y = 0; y < length; y++){
      input = in.readLine().split(" ");
      for(int x = 0; x < length; x++){
        int value = Integer.parseInt(input[x]);
        if(value == 2) bombList.add(new int[] {y, x}); // 폭탄을 설치할 수 있는 위치들 저장.

        if(value != 1) empty_count++; // 벽이 아닌 모든 칸의 개수를 미리 저장함.

        // 추후에 입력받은 폭탄 위치에 폭탄을 설치할 것이므로 설치하기 이전 버전을 저장해놓음.
        init_map[y][x] = (value == 1) ? 1 : 0; 
      }
    }

    solve(bombList.size(), 0, new boolean[bombList.size()], 0, init_map);

    System.out.println(min_time);
    
  }

  static int[][] copyArray(int[][] map){
    int length = map.length;
    int[][] temp = new int[length][length];
    for(int i = 0; i < length; i++)
      System.arraycopy(map[i], 0, temp[i], 0, length);
    return temp;
  }

  static void solve(int size, int idx, boolean[] selected, int selected_bomb, int[][] map){
    if(selected_bomb == bomb_count){
      int time = calc(map, selected);
      if(time != -1){
        if(min_time == -1)  min_time = time;
        else min_time = Math.min(min_time, time);
      }
    }

    for(int i = idx; i < size; i++){
      selected[i] = true;
      solve(size, i+1, selected, selected_bomb+1, map);
      selected[i] = false;
    }
  }

  static int calc(int[][] map, boolean[] selected){
    int[][] search_map = copyArray(map);
    int length = search_map.length;
    int spread_virus = 0; // 바이러스가 퍼진 칸의 개수

    boolean[][] visited = new boolean[length][length];
    Queue<int[]> queue = new ArrayDeque<>();
    for(int i = 0; i < selected.length; i++){
      if(!selected[i]) continue; // 선택되지 않은 폭탄이면 continue
      int y = bombList.get(i)[0], x = bombList.get(i)[1];
      queue.offer(bombList.get(i));
      visited[y][x] = true;
      spread_virus++; // 폭탄이 있는 자리에는 무조건 바이러스가 퍼질 것이므로 ++
    }

    int spread_time = 1;
    while(!queue.isEmpty()){
      int size = queue.size();
      for(int i = 0; i < size; i++){
        int cy = queue.peek()[0], cx = queue.poll()[1]; // 탐색하려는 좌표 위치
        for(int type = 0; type < 4; type++){ // 4방 탐색
          int dy = cy + dir[type][0], dx = cx + dir[type][1];
          if(dy >= 0 && dx >= 0 && dy < length && dx < length && !visited[dy][dx]){
            if(search_map[dy][dx] != 1){
              visited[dy][dx] = true;
              queue.offer(new int[] {dy, dx});
              spread_virus++;
            }
          }
        } 
      }
      if(spread_virus == empty_count)	break;
      spread_time++;
    }

    if(spread_virus != empty_count)  return -1;
    else return spread_time;
  }
}
