
public class PendulumDE extends Derivative{
	private double mCart;
	private double mTrack;
	private double mTotal;
	private double k;
	private double g = 9.81;
	
	public PendulumDE(double mCart,
					  double mTrack,
					  double springConst){
		this.mCart = mCart;
		this.mTrack = mTrack;
		this.mTotal = mCart + mTrack;
		this.k = springConst;
	}
	public PendulumDE(double mCart,
					  double mTrack,
					  double springConst,
					  double gravAccel){
		this(mCart,mTrack,springConst);
		
		g = gravAccel;
	}


	@Override
	public double[] calculate(double[] x, double t) {
		// TODO Auto-generated method stub
		double[] deriv = new double[4];
		deriv[0] = x[1] / mCart;
		deriv[1] = mCart * g * Math.cos(x[3]) +
				   mCart * x[2] * x[2] /(mTotal * mTotal * x[0]) -
				   k * x[0];
		deriv[2] = - mTotal * g * Math.sin(x[3]) 
				   - x[1] * x[2] / (mTotal * x[0]);
		deriv[3] = x[2] / (mTotal * x[0]);
		return deriv;
	}
}