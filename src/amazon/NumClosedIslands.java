/*
 * https://leetcode.com/problems/number-of-closed-islands/
*/
package amazon;

public class NumClosedIslands {

	boolean[][] visited;
	int[][] grid;
	int count = 0;

	boolean isBorder(int i, int j) {
		if (i == 0 || j == 0 || i == grid.length - 1 || j == grid[0].length - 1)
			return true;
		return false;
	}
	static boolean borderFd = false;
	
	boolean traverse(int i, int j) {
		boolean retVal = false;
		visited[i][j] = true;
		
		if (!borderFd && isBorder(i, j)) {
			retVal = true;
		}
		if (i + 1 < grid.length && !visited[i + 1][j] && grid[i + 1][j] == 0) {
			boolean traVal = traverse(i + 1, j);
			retVal = retVal || traVal;
		}
		if (j + 1 < grid[0].length && !visited[i][j + 1] && grid[i][j + 1] == 0) {
			boolean traVal = traverse(i, j + 1);
			retVal = retVal || traVal;
		}
		if (i - 1 >= 0 && !visited[i - 1][j] && grid[i - 1][j] == 0) {
			boolean traVal = traverse(i - 1, j);
			retVal = retVal || traVal;
		}
		if (j - 1 >= 0 && !visited[i][j - 1] && grid[i][j - 1] == 0) {
			boolean traVal = traverse(i, j - 1);
			retVal = retVal || traVal;
		}
		
		return retVal;
	}

	void visit(int i, int j) {
		if (visited[i][j] || grid[i][j] == 1)
			return;
		
		borderFd = false;
		//System.out.println(i+","+j);
		//System.out.println("before count="+count);
		if(!traverse(i, j))
			count++;
		//System.out.println("after count="+count);
		//System.out.println("--------------");
	}

	public int closedIsland(int[][] grid) {
		if(grid==null || grid.length==0)
			return 0;
		this.grid = grid;
		visited = new boolean[grid.length][];
		for (int i = 0; i < grid.length; i++)
			visited[i] = new boolean[grid[0].length];

		for (int i = 0; i < grid.length; i++)
			for (int j = 0; j < grid[0].length; j++)
				visited[i][j] = false;

		for (int i = 0; i < grid.length; i++)
			for (int j = 0; j < grid[0].length; j++)
				visit(i, j);
		return count;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int[][] grid = {{1,1,1,1,1,1,1,0},{1,0,0,0,0,1,1,0},{1,0,1,0,1,1,1,0},{1,0,0,0,0,1,0,1},{1,1,1,1,1,1,1,0}};
//		int[][] grid = { { 0, 0, 1, 0, 0 }, { 0, 1, 0, 1, 0 }, { 0, 1, 1, 1, 0 } };
//		int[][] grid = 
//						{{1,1,1,1,1,1,1},
//		                {1,0,0,0,0,0,1},
//		                {1,0,1,1,1,0,1},
//		                {1,0,1,0,1,0,1},
//		                {1,0,1,1,1,0,1},
//		                {1,0,0,0,0,0,1},
//		                {1,1,1,1,1,1,1}};
//		int[][] grid = 
//			   {{0,0,1,1,0,1,0,0,1,0},
//				{1,1,0,1,1,0,1,1,1,0},
//				{1,0,1,1,1,0,0,1,1,0},
//				{0,1,1,0,0,0,0,1,0,1},
//				{0,0,0,0,0,0,1,1,1,0},
//				{0,1,0,1,0,1,0,1,1,1},
//				{1,0,1,0,1,1,0,0,0,1},
//				{1,1,1,1,1,1,0,0,0,0},
//				{1,1,1,0,0,1,0,1,0,1},
//				{1,1,1,0,1,1,0,1,1,0}};

		int[][] grid = 
				{{0,1,0,1,0,0,0,1,1,0,1,1,0,0,1,1,1,0,1,1},
				 {0,1,1,0,0,0,0,1,1,1,0,1,0,1,1,0,0,1,0,1},
				 {1,1,0,1,0,0,1,1,1,0,0,0,1,0,0,1,0,1,0,0},
				 {0,1,1,1,0,0,0,0,0,0,1,1,1,0,0,0,1,1,1,0},
				 {1,1,0,0,1,0,0,1,1,0,1,1,0,1,1,1,0,0,1,1},
				 {1,1,0,0,0,0,0,1,0,1,1,1,0,1,0,0,0,0,0,1},
				 {1,0,1,1,0,1,0,1,0,0,1,0,1,1,1,1,1,0,1,0},
				 {1,0,0,1,1,0,0,1,0,1,0,0,0,0,0,0,0,0,0,1},
				 {0,0,0,1,1,1,0,1,1,1,0,1,0,1,1,0,1,0,0,0},
				 {1,1,0,0,0,0,1,1,0,0,0,1,0,0,1,0,1,0,1,1},
				 {1,0,0,1,1,1,1,0,1,0,1,1,1,0,0,0,0,1,1,0},
				 {1,1,0,0,0,0,0,0,1,1,1,1,0,1,0,1,0,1,1,1},
				 {0,1,1,0,0,1,1,0,0,1,0,1,1,1,1,1,1,0,0,0},
				 {1,0,0,0,1,1,0,1,1,1,0,0,1,0,1,1,0,1,0,1}};
		System.out.println(new NumClosedIslands().closedIsland(grid));
	}

}
