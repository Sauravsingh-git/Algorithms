package percolation;

import java.util.Arrays;

public class Percolation {
	boolean [][] arr;
	int [] mapping;
	int noOfOpenSites;
	int [] size;
	int top , bottom;
	public Percolation(int n) {
		arr = new boolean [n][n];
		mapping = new int[n*n + 2];
		size = new int[n*n + 2];
		top = n*n;
		bottom = n*n+1;
		for(int i=0; i<n*n + 2; i++) {
			mapping[i] = i;
		}
		Arrays.fill(size, 1);
		noOfOpenSites = 0;
	}
	
	public void open(int row, int col) {
		if(inRange(row, col)) {
			if(row == 0) {
				union(rowAndColMapping(row, col), top);
			}
			if(row == arr.length-1) {
				union(rowAndColMapping(row, col), bottom);
			}
			if(!isOpen(row, col)) {
				arr[row][col] = true;
				connectWithOpenNeighbours(row, col);
			}
			noOfOpenSites++;
		}		
	}
	
	public void connectWithOpenNeighbours(int row, int col) {
		int mappingId = rowAndColMapping(row, col);
		if(inRange(row, col-1)&& isOpen(row, col-1)) 
			union(mappingId, rowAndColMapping(row, col-1));
		if(inRange(row, col+1)&& isOpen(row, col+1)) 
			union(mappingId, rowAndColMapping(row, col+1));
		if(inRange(row+1, col)&& isOpen(row+1, col)) 
			union(mappingId, rowAndColMapping(row+1, col));
		if(inRange(row-1, col)&& isOpen(row-1, col)) 
			union(mappingId, rowAndColMapping(row-1, col));
		
	}
	
	public boolean inRange(int row, int col) {
		if(row>=0 && row < arr.length && col >=0 && col <arr[0].length)
			return true;
		return false;
	}
	
	public boolean isOpen(int row, int col) {
		if(inRange(row, col))
		return arr[row][col];
		
		return false;
	}
	
	public boolean isFull(int row, int col) {
		return isConnected(rowAndColMapping(row, col), top);
	}
	
	public int rowAndColMapping(int row, int col) {
		return row*arr.length + col;
	}
	
	public int numberOfOpenSites() {
		return noOfOpenSites;
	}
	
	public boolean percolates() {
		return isConnected(top, bottom);
	}
	
	public boolean isConnected(int p, int q) {
		return root(p) == root(q);
	}
	
	public int root(int p) {
		while(mapping[p]!= p) {
			p = mapping[p];
		}
		return p;
	}
	
	public void union(int p, int q) {
		if(!isConnected(p, q)) {
			int rootP = root(p);
			int rootQ = root(q);
			if(size[rootP] >= size[rootQ]) {
				size[rootP] += size[rootQ];
				mapping[rootQ] = rootP;
			}else {
				size[rootQ] += size[rootP];
				mapping[rootP] = rootQ;
			}
		}
	}
	public static void main (String [] args) {
		Percolation per = new Percolation(5);
		per.open(0, 1);
		per.open(0, 3);
		per.open(1, 2);
		per.open(1, 0);
		per.open(1, 3);		
		per.open(2, 1);
		per.open(2, 2);
		per.open(2, 3);
		per.open(3, 1);
		per.open(3, 3);
		per.open(4, 1);
		per.open(4, 4);
		System.out.println("is the system percolated : " + per.percolates());
		

		}
	
}

