import java.io.PrintWriter;
import java.io.IOException;

public class Main {
	public static String fname = "/Volumes/Macintosh HD 2/Uni Stuff/3A/SYDE351Solution";
	
	public static void main(String [] args){
		PendulumDE d = new PendulumDE(1.0,3.0,10.0);
		double[] ic = {2.0,-0.01,0.00001,0.3};
		double[][] solution = Solver.solve(d,ic,0.0001, 10);
		
		writeSolution(solution,fname);
	}
	public static void writeSolution(double[][] solution, String fname){
		PrintWriter file = null;
		try{
			file = new PrintWriter(fname);
			for(int iter = 0; iter < solution[0].length; iter++){
				for(int var = 0; var < solution.length; var++){
					file.write(Double.toString(solution[var][iter]));
					file.write("\t");
				}
				file.write("\n");
			}
		} catch(IOException e){
			e.printStackTrace();
		} finally {
			file.close();
		}
	}
}
