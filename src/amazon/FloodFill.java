/*
 * https://leetcode.com/problems/flood-fill/
 */
package amazon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class FloodFill {

	public static class Coordinate {
		int i;
		int j;

		public Coordinate(int i, int j) {
			// TODO Auto-generated constructor stub
			this.i = i;
			this.j = j;
		}

		@Override
		public boolean equals(Object o) {
			Coordinate c = (Coordinate) o;
			return this.i == c.i && this.j == c.j;

		}

		/*
		 * @Override public int hashCode() { return i+j; }
		 */

	}

	// Set<Coordinate> visited = new HashSet<FloodFill.Coordinate>();

	private void fill(int[][] image, List<Coordinate> list, int matchColor, int newColor, Coordinate pixel) {
		if (pixel.i < 0 || pixel.i >= image.length || pixel.j < 0 || pixel.j >= image[0].length)
			return;
		if (image[pixel.i][pixel.j] == matchColor) {
			list.add(pixel);
		}
	}

	private void fill(int[][] image, List<Coordinate> list, int matchColor, int newColor) {
		while (!list.isEmpty()) {
			Coordinate pixel = list.remove(0);
			image[pixel.i][pixel.j] = newColor;
			fill(image, list, matchColor, newColor, new Coordinate(pixel.i - 1, pixel.j));
			fill(image, list, matchColor, newColor, new Coordinate(pixel.i + 1, pixel.j));
			fill(image, list, matchColor, newColor, new Coordinate(pixel.i, pixel.j - 1));
			fill(image, list, matchColor, newColor, new Coordinate(pixel.i, pixel.j + 1));
		}
	}

	private void fill(int[][] image, List<Integer> listI, List<Integer> listJ, int matchColor, int newColor, int i, int j) {
		if (i < 0 || i >= image.length || j < 0 || j >= image[0].length)
			return;
		if (image[i][j] == matchColor) {
			listI.add(i);
			listJ.add(j);
		}
	}

	private void fillOld(int[][] image, List<Integer> listI, List<Integer> listJ, int matchColor, int newColor) {
		while (!listI.isEmpty()) {
			int i = listI.remove(0);
			int j = listJ.remove(0);
			image[i][j] = newColor;
			fill(image, listI, listJ, matchColor, newColor, i - 1, j);
			fill(image, listI, listJ, matchColor, newColor, i + 1, j);
			fill(image, listI, listJ, matchColor, newColor, i, j - 1);
			fill(image, listI, listJ, matchColor, newColor, i, j + 1);
		}
	}
	
	private void fillBFS(int[][] image, List<Integer> listI, List<Integer> listJ, int matchColor, int newColor) {
		while(!listI.isEmpty()) {
			int i = listI.remove(0);
			int j = listJ.remove(0);
			image[i][j] = newColor;
			if(i-1>=0 && image[i-1][j]==matchColor) {
				listI.add(i-1);
				listJ.add(j);
			}
			if(i+1<image.length && image[i+1][j]==matchColor) {
				listI.add(i+1);
				listJ.add(j);
			}
			if(j-1>=0 && image[i][j-1]==matchColor) {
				listI.add(i);
				listJ.add(j-1);
			}
			if(j+1<image[0].length && image[i][j+1]==matchColor) {
				listI.add(i);
				listJ.add(j+1);
			}
		}
	}
	
	private void fillRecursive(int[][] image, int i, int j, int matchColor, int newColor) {
			image[i][j]=newColor;
			if(i-1>=0 && image[i-1][j]==matchColor)
				fillRecursive(image, i-1, j, matchColor, newColor);
			if(i+1<image.length && image[i+1][j]==matchColor)
				fillRecursive(image, i+1, j, matchColor, newColor);
			if(j-1>=0 && image[i][j-1]==matchColor)
				fillRecursive(image, i, j-1, matchColor, newColor);
			if(j+1<image[0].length && image[i][j+1]==matchColor)
				fillRecursive(image, i, j+1, matchColor, newColor);
	}
	
	public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
		int matchColor = image[sr][sc];
		if(matchColor == newColor)
			return image;
		//fillRecursive(image, sr, sc, matchColor, newColor);
//		List<Integer> listI = new ArrayList<Integer>();
//		List<Integer> listJ = new ArrayList<Integer>();
//		listI.add(sr);
//		listJ.add(sc);
//		fillBFS(image, listI, listJ, matchColor, newColor);
		
//		List<Integer> listI = new ArrayList<Integer>();
//		List<Integer> listJ = new ArrayList<Integer>();
//		listI.add(sr);
//		listJ.add(sc);
//		fillOld(image, listI, listJ, matchColor, newColor);
		 		
		List<Coordinate> list = new ArrayList<FloodFill.Coordinate>();
		list.add(new Coordinate(sr, sc));
		fill(image, list, matchColor, newColor);
		
		return image;
	}

	public static void main(String[] args) {
		/*
		 * Set<FloodFill.Coordinate> visited = new HashSet<FloodFill.Coordinate>();
		 * visited.add(new FloodFill.Coordinate(1, 1)); FloodFill.Coordinate p = new
		 * FloodFill.Coordinate(1, 1); FloodFill.Coordinate p1 = new
		 * FloodFill.Coordinate(1, 1);
		 * 
		 * if (p.equals(p1)) System.out.println("yws111"); if (visited.contains(p))
		 * System.out.println("yws");
		 */

		// TODO Auto-generated method stub

		//int[][] image = { { 1, 1, 1 }, { 1, 1, 0 }, { 1, 0, 1 } };
		int[][] image = { { 0, 0, 0 },{ 0, 1, 0 } };

		int[][] result = new FloodFill().floodFill(image, 1, 1, 2);
		for (int i = 0; i < image.length; i++) {
			for (int j = 0; j < image[0].length; j++)
				System.out.print(result[i][j] + ",");
			System.out.println();
		}

	}
}
