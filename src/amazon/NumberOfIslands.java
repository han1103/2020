/*
 * https://leetcode.com/problems/number-of-islands/
*/
package amazon;

public class NumberOfIslands {
	boolean[][] visited;
	char[][] grid;
	int count = 0;
	
	void traverse(int i, int j) {
		visited[i][j]=true;
		if(i+1<grid.length && !visited[i+1][j] && grid[i+1][j]=='1')
			traverse(i+1, j);
		if(j+1<grid[0].length && !visited[i][j+1] && grid[i][j+1]=='1')
			traverse(i, j+1);
		if(i-1>=0 && !visited[i-1][j] && grid[i-1][j]=='1')
			traverse(i-1, j);
		if(j-1>=0 && !visited[i][j-1] && grid[i][j-1]=='1')
			traverse(i, j-1);
	}
	
	void visit(int i, int j) {
		if(visited[i][j] || grid[i][j]=='0')
			return;
 		count++;
		traverse(i, j);
	}
	
	public int numIslands(char[][] grid) {
		if(grid==null || grid.length==0)
			return 0;
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
		return count;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//char[][] grid = {{'1','1','1','1','0'}, {'1','1','0','1','0'}, {'1','1','0','0','0'}, {'0','0','0','0','0'}};
		//char[][] grid = {{'1','1','0','0','0'}, {'1','1','0','0','0'}, {'0','0','1','0','0'}, {'0','0','0','1','1'}};
		char[][] grid = {{'1','1','1'}, {'0','1','0'}, {'1','1','1'}};
		System.out.println(new NumberOfIslands().numIslands(grid));
	}

}
