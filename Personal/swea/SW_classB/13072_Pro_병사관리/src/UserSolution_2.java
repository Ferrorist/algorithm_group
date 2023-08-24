import java.util.Arrays;

class UserSolution_2
{
	public class Soldier implements Comparable<Soldier>{
		int mID;
		int mTeam, mScore;
		Soldier next;
		
		public Soldier(int mID, int mTeam, int mScore) {
			super();
			this.mID = mID;
			this.mTeam = mTeam;
			this.mScore = mScore;
			this.next = null;
		}

		@Override
		public int compareTo(Soldier o) {
			return this.mID - o.mID;
		}

		@Override
		public boolean equals(Object obj) {
			if(obj instanceof Soldier) {
				Soldier temp = (Soldier) obj;
				return this.mID == temp.mID;
			}
			return false;
		}

	}
	
	Soldier[] SoldierList;
	int size;
	public void init()
	{
		SoldierList = new Soldier[100_001];
		size = 0;
		SoldierList[0] = new Soldier(0, 0, 0);
	}
	
	public void hire(int mID, int mTeam, int mScore)
	{
		Soldier temp = new Soldier(mID, mTeam, mScore);
		SoldierList[size++].next = temp;
		SoldierList[size] = temp;
	}
	
	public void fire(int mID)
	{
		
	}

	public void updateSoldier(int mID, int mScore)
	{
	}

	public void updateTeam(int mTeam, int mChangeScore)
	{
		Arrays.sort(SoldierList);
	}
	
	public int bestSoldier(int mTeam)
	{
		return 0;
	}
}