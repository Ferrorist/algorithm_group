import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserSolution
{
	// 병사의 mID와 mTeam을 저장.
	private HashMap<Integer, Integer> allHashMap = new HashMap<>();
	
	// mTeam에 있는 병사의 mID와 mTeam을 저장.
	private HashMap<Integer, Integer>[] teamHashMap;
	
	public void init()
	{
		allHashMap.clear();
		teamHashMap = new HashMap[6];
		for(int i = 0; i < 6; i++)	teamHashMap[i] = new HashMap<>();
	}
	
	public void hire(int mID, int mTeam, int mScore)
	{
		allHashMap.put(mID, mTeam);
		teamHashMap[mTeam].put(mID, mScore);
	}
	
	public void fire(int mID)
	{
		int team = allHashMap.get(mID);
		allHashMap.remove(mID);
		teamHashMap[team].remove(mID);
	}

	public void updateSoldier(int mID, int mScore)
	{
		int team = allHashMap.get(mID);
		teamHashMap[team].put(mID, mScore);
	}

	public void updateTeam(int mTeam, int mChangeScore)
	{
		for(Integer key : teamHashMap[mTeam].keySet()) {
			int value = teamHashMap[mTeam].get(key) + mChangeScore;
			value = (value > 5) ? 5 : (value < 1) ? 1 : value;
			teamHashMap[mTeam].put(key, value);
		}
	}
	
	public int bestSoldier(int mTeam)
	{
		Comparator<Map.Entry<Integer, Integer>> comparator = (entry1, entry2) -> {
			int value = entry2.getValue().compareTo(entry1.getValue());
			if(value != 0)	return value;
			else return entry2.getKey().compareTo(entry1.getKey());
		};

		List<Map.Entry<Integer, Integer>> list = teamHashMap[mTeam].entrySet().parallelStream().sorted(comparator).collect(Collectors.toList());
		return list.get(0).getKey();
	}
}