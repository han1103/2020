/*
 * https://leetcode.com/problems/number-of-distinct-islands/
*/
package amazon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
	boolean[][] visited;
	char[][] grid;
	int count = 0;
	
	class Coordinate {
		int i;
		int j;
		
		public Coordinate(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + i;
			result = prime * result + j;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Coordinate other = (Coordinate) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			if (i != other.i)
				return false;
			if (j != other.j)
				return false;
			return true;
		}
		private Solution getEnclosingInstance() {
			return Solution.this;
		}				
	}
	
	class Island {
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + ((pts == null) ? 0 : pts.hashCode());
			result = prime * result + ((startPt == null) ? 0 : startPt.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Island other = (Island) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			
			if(this.pts.size()!=other.pts.size())
				return false;
			for(Coordinate pt : this.pts) {
				int deltaI = pt.i-startPt.i;
				int deltaJ = pt.j-startPt.j;
				if(deltaI<0)
					System.out.println("WOWOWOOWOW_-----------------");
				if(deltaI!=0 || deltaJ!=0) {
					Coordinate newPt = new Coordinate(other.startPt.i+deltaI, other.startPt.j+deltaJ);
					if(!other.pts.contains(newPt))
						return false;						
				}
					
			}
			
			return true;
		}
		public Island(Coordinate startPt) {
			super();
			this.startPt = startPt;
			pts.add(startPt);
		}
		Coordinate startPt;
		HashSet<Coordinate> pts = new HashSet<Solution.Coordinate>();
		private Solution getEnclosingInstance() {
			return Solution.this;
		}		
	}
	
	void traverse(int i, int j, Island island) {
		visited[i][j]=true;
		if(i+1<grid.length && !visited[i+1][j] && grid[i+1][j]=='1') {
			island.pts.add(new Coordinate(i+1, j));
			traverse(i+1, j, island);
		}
		if(j+1<grid[0].length && !visited[i][j+1] && grid[i][j+1]=='1') {
			island.pts.add(new Coordinate(i, j+1));
			traverse(i, j+1, island);
		}
		if(i-1>=0 && !visited[i-1][j] && grid[i-1][j]=='1') {
			island.pts.add(new Coordinate(i, j+1));
			traverse(i-1, j, island);
		}
		if(j-1>=0 && !visited[i][j-1] && grid[i][j-1]=='1') {
			island.pts.add(new Coordinate(i, j-1));
			traverse(i, j-1, island);
		}
	}
	
	List<Island> islands = new ArrayList<Solution.Island>();
	//Set<Island> islands = new HashSet<NumDistinctIslands.Island>();
	static int counter = 0;
	void visit(int i, int j) {
		if(visited[i][j] || grid[i][j]=='0')
			return;
		Island island = new Island(new Coordinate(i, j));
		traverse(i, j, island);
		boolean found = false;
		for(Island il : islands)
			if(island.equals(il)) {
				found = true;
				counter++;
				break;
			}
		//if(!found)		
			islands.add(island);
	}
	
	//public int numDistinctIslands(int[][] grid) {
	public int numIslands(char[][] grid) {	
		this.grid = grid;
		visited = new boolean[grid.length][];
		for(int i=0; i<grid.length; i++)
			visited[i] = new boolean[grid[0].length];
		
		for(int i=0; i<grid.length; i++)
			for(int j=0; j<grid[0].length; j++)
				visited[i][j]=false;
		
		for(int i=0; i<grid.length; i++)
			for(int j=0; j<grid[0].length; j++)
				visit(i, j);
		
//		for(Island island : islands) {
//			System.out.println("Start:"+island.startPt.i+","+island.startPt.j);
//			for(Coordinate pt : island.pts) {			
//				System.out.println(pt.i+","+pt.j);
//			}
//			System.out.println("---------------");			
//		}
		
		/*
		 * for(int i=0; i<islands.size(); i++) { for(int j=i+1; j<islands.size(); j++) {
		 * if(islands.get(i).equals(islands.get(j))) { islands.remove(j); j--; } } }
		 */
		//System.out.println("counter:"+counter);
		
		return islands.size(); 
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int[][] grid = {{1,0,0,0,1}, 
//				        {0,0,0,1,1}, 
//				        {0,1,0,0,0}, 
//				        {1,1,0,0,1}};
		
		//int[][] grid = {{1,1,0,0,1,0,1,1,1,1,1,0,0,0,1,1,0,1,0,1,1,1,1,0,0},{0,0,0,1,0,1,0,1,1,0,0,0,0,1,0,1,1,1,1,0,1,0,0,0,1},{1,0,1,0,1,1,0,0,1,1,1,0,0,0,1,0,0,1,1,1,1,1,1,0,1},{0,0,1,1,0,1,1,0,1,0,0,0,0,0,0,0,1,0,1,0,1,0,0,1,1},{1,1,0,1,1,1,1,1,0,0,1,0,0,1,1,1,1,1,1,0,0,0,0,0,1},{0,1,0,1,0,0,1,1,0,1,0,0,0,0,0,0,0,0,0,1,0,1,1,1,1},{1,1,1,1,0,0,0,1,0,0,1,0,0,1,0,1,1,1,1,1,0,0,0,0,0},{0,1,1,1,1,1,0,1,0,0,0,1,0,1,1,1,0,0,1,0,0,0,0,1,1},{1,1,0,0,1,1,0,1,0,1,1,0,0,0,0,1,1,1,1,0,1,1,0,0,0},{0,1,1,1,1,0,1,1,0,1,1,0,1,1,0,1,0,0,0,0,1,0,1,1,1},{0,1,1,1,0,1,0,0,0,0,0,1,0,1,0,1,0,0,0,0,0,0,0,1,0},{1,0,0,1,0,1,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,0,1,1,1},{1,1,1,1,1,1,1,1,0,0,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1},{1,1,1,0,1,0,1,1,1,1,1,1,0,1,1,0,1,0,1,1,1,0,0,1,1},{0,0,0,1,1,0,1,1,1,0,1,1,1,1,1,1,0,1,1,0,0,0,0,1,0},{0,0,1,0,0,1,0,1,1,1,1,0,1,0,0,0,1,0,1,1,1,1,1,1,1},{0,0,1,1,1,1,0,1,1,0,0,1,1,1,0,0,1,0,1,0,1,1,0,1,1},{1,0,0,1,0,1,0,0,1,1,1,1,0,0,0,1,0,1,0,1,1,1,0,1,1},{0,0,0,0,1,0,0,1,0,1,0,0,0,1,1,1,0,1,0,0,0,1,0,0,0},{1,1,1,1,0,0,0,0,0,1,0,0,0,0,0,1,1,0,1,1,1,0,0,0,1},{1,0,0,1,1,1,1,1,1,1,0,1,0,0,1,1,1,1,1,1,0,1,0,1,1},{1,1,0,0,0,0,0,0,1,0,1,0,1,0,0,1,0,1,0,1,0,0,0,0,0},{1,0,0,0,0,1,1,0,1,1,0,1,0,1,0,1,1,1,0,1,0,1,0,0,0},{0,1,1,0,1,1,0,1,0,1,0,1,1,1,1,1,0,1,0,0,1,1,0,1,1},{1,1,0,1,1,0,1,1,1,0,1,0,1,0,0,1,0,1,0,0,0,1,0,0,0},{0,1,0,0,1,0,0,0,0,0,0,1,0,1,1,0,1,1,0,1,1,1,0,0,0},{0,1,1,1,1,0,0,0,0,0,1,0,1,0,1,0,1,0,1,0,1,1,1,0,1},{1,0,0,1,1,1,0,0,0,1,0,1,0,0,0,0,0,0,1,1,0,0,0,0,0},{1,0,0,0,1,0,1,0,0,0,0,1,0,0,0,0,1,1,1,0,1,0,0,0,0},{0,0,0,1,0,1,0,1,1,1,0,1,1,1,0,1,1,1,0,1,0,0,1,1,0},{1,0,1,1,1,0,1,1,1,0,1,1,1,0,0,0,0,0,1,1,0,0,1,0,1},{0,1,1,0,0,0,0,1,0,1,0,0,1,0,1,1,1,1,0,0,0,1,1,0,0},{0,1,0,0,1,1,0,1,1,0,1,0,1,0,0,0,0,1,1,0,0,1,1,1,1},{0,0,1,0,1,1,1,1,0,1,1,1,0,1,0,1,1,1,1,1,1,1,1,0,0},{1,0,1,0,1,1,0,1,1,0,0,0,1,1,1,1,1,1,1,0,1,1,1,1,1},{1,0,1,0,1,1,1,0,1,0,1,0,1,1,0,0,1,1,0,0,1,1,0,0,1},{0,1,0,0,0,1,0,1,1,1,1,1,0,0,0,1,0,0,0,0,1,0,1,0,1},{0,0,1,0,1,0,1,1,0,0,0,0,0,0,0,0,1,0,0,1,1,1,0,0,1},{1,1,0,1,0,0,1,1,0,0,1,1,1,0,0,1,1,1,1,0,0,0,0,1,0},{1,0,0,0,0,0,0,1,0,0,0,1,0,1,0,1,0,1,1,1,0,0,0,0,0},{1,0,0,0,1,0,0,1,0,0,0,0,1,1,1,1,1,1,0,0,1,0,0,0,1},{0,1,0,1,0,1,1,0,0,1,1,1,0,1,0,0,0,1,1,0,0,1,1,1,0},{1,1,1,1,0,1,0,0,1,0,1,1,0,0,1,1,0,1,0,1,0,0,1,0,1},{1,0,0,1,0,1,0,1,0,0,1,0,1,0,0,0,1,1,0,1,1,1,0,0,0},{1,1,1,0,1,0,0,0,1,1,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0},{1,1,1,0,0,0,1,0,0,1,0,1,1,0,1,1,1,1,0,0,1,1,0,0,1},{0,1,1,0,1,0,0,1,0,0,0,0,1,0,1,1,1,1,1,0,1,1,1,0,1},{0,1,1,1,1,1,1,1,1,1,0,1,0,0,1,1,1,0,0,0,0,1,1,0,1},{0,0,1,0,1,1,0,0,1,1,1,0,1,0,0,0,1,0,1,0,1,0,1,1,0},{1,0,0,1,1,0,1,0,0,1,0,0,1,1,0,1,0,0,1,0,0,1,0,1,0}};
		char[][] grid = {{'1','1','0','0','1'}, 
		         {'1','0','0','1','1'}, 
		         {'0','1','0','0','1'}, 
		         {'1','1','0','1','1'}};
		System.out.println(new Solution().numIslands(grid));
	}	

}