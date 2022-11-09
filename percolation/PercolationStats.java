package percolation;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
public class PercolationStats {
	double [] percolationArr;
	final int t;
	public PercolationStats(int n, int trials) {
		this.t = trials;
		percolationArr = new double [t];
		int counter;
		for(int i=0; i<t; i++) {
			Percolation percolation = new Percolation(n);
			counter = 0;
			while(true) {
				int row = StdRandom.uniformInt(n);
				int col= StdRandom.uniformInt(n);
				if(!percolation.isOpen(row, col)) {
					percolation.open(row,  col);
					counter++;
				}
				if(percolation.percolates()) {
					percolationArr[i] = (double)counter/(n*n);
					break;
				}
			}
			
		}
	}
	
	
	public double mean() {
		return StdStats.mean(percolationArr);
	}
	
	public double stddev() {
		return StdStats.stddev(percolationArr);
	}
	public double confidenceLo() {
		return (mean()-(1.96*stddev()/Math.sqrt(percolationArr.length)));
	}
	
	public double confidenceHi() {
		return (mean()+(1.96*stddev()/Math.sqrt(percolationArr.length)));
	}
	
	public void printT() {
		System.out.println("The value of T is : " + t);
	}
	public static void main(String [] args) {
		PercolationStats ps = new PercolationStats(200, 100);
		System.out.println("Mean : " + ps.mean());
		System.out.println("StdDev : " + ps.stddev());
		System.out.println("95% confidence interval : " +"[ " + ps.confidenceLo() +", " + ps.confidenceHi()+ "]" );
		ps.printT();
	}
}
