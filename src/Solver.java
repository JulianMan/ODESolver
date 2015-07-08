
public class Solver {
	public static double[][] solve(Derivative deriv,
							double[] initialConditions,
							double timeStep,
							double maxTime){
		//Solves differential equation given by deriv
		//Uses initial conditions, can set time step and max time
		
		double[][] solution = new double[initialConditions.length][(int)Math.floor(maxTime/timeStep)];
		
		for(int i = 0; i < initialConditions.length; i++){
			solution[i][0] = initialConditions[i];
		}
		double[] current = initialConditions.clone();
		for(int i = 0; i < current.length; i++){
			System.out.println(current[i]);
		}
		int iteration = 0;
		while(iteration * timeStep < maxTime){
			current = calcNext(deriv,current, iteration * timeStep, timeStep);
			for(int var = 0; var < solution.length; var++){
				solution[var][iteration] = current[var];
			}
			
			iteration++;
		}
		
		return solution;
	}
	private static double[] calcNext(Derivative deriv, 
							  double[] current,
							  double time,
							  double timeStep){
		// Use 4th order Runge-Kutta method to calc next state
		double[] k1 = deriv.calculate(current,time);
		double[] k2 = deriv.calculate(vAdd(current,vMult(timeStep / 2,k1)),
									  time + timeStep / 2);
		double[] k3 = deriv.calculate(vAdd(current,vMult(timeStep / 2,k2)),
				  time + timeStep / 2);
		double[] k4 = deriv.calculate(vAdd(current,vMult(timeStep,k2)),
				  time + timeStep);
		return vAdd(current,vMult(timeStep / 6,
				vAdd(k1,
				vAdd(vMult(2,k2),
				vAdd(vMult(2,k3),
					 k4)))));
	}
	private static double[] vAdd(double[] v1, double[] v2){
		//vector addition
		assert(v1.length == v2.length);
		double[] v = new double[v1.length];
		for(int i = 0; i < v1.length; i++){
			v[i] = v1[i] + v2[i];
		}
		return v;
	}
	private static double[] vMult(double k, double[] v){
		//scalar multiplication
		double[] u = new double[v.length];
		for(int i = 0; i < v.length; i++){
			u[i] = k * v[i];
		}
		return u;
	}
}
