/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender and Stefan Mayer
 * 
 * Created:	07.05.2011
 */

package at.easydiet.teamb.application.util;

import java.util.HashMap;

import at.easydiet.teamb.domain.util.GenderEnum;


/**
 * The Class PercentileUtil.
 * Calculates the percentiles for patients (0-18years)
 */
public class BMIUtil {
	private static HashMap<Double, Percentile> _mapBoy;
	private static HashMap<Double, Percentile> _mapGirl;	
	
	/**
	 * The Enum PercentileEnum.
	 */
	public static enum BMIEvaluationEnum {
		// categories for people < 19 years
		/** Starkes Untergewicht */
		P3("Perzentil", "Starkes Untergewicht"), 
		/** Untergewicht */
		P10("Perzentil", "Untergewicht"), 
		/** Normalgewicht */
		P25("Perzentil", "Normalgewicht"), 
		/** Normalgewicht */
		P50("Perzentil", "Normalgewicht"), 
		/** Normalgewicht */
		P75("Perzentil", "Normalgewicht"), 
		/** Übergewicht */
		P90("Perzentil", "Übergewicht"), 
		/** Starkes Übergewicht */
		P97("Perzentil", "Starkes Übergewicht"),
		
		// categories for people >= 19 years
		/** Starkes Untergewicht */
		SU("BMI", "Starkes Untergewicht"),
		/** Mäßiges Untergewicht */
		MU("BMI", "Mäßiges Untergewicht"), 
		/** Leichtes Untergewicht */
		LU("BMI", "Leichtes Untergewicht"), 
		/** Normalgewicht */
		N("BMI", "Normalgewicht"), 
		/** Präadipositas */
		P("BMI", "Präadipositas"), 
		/** Adipositas Grad I */
		A1("BMI", "Adipositas Grad I"), 
		/** Adipositas Grad II */
		A2("BMI", "Adipositas Grad II"), 
		/** Adipositas Grad III */
		A3("BMI", "Adipositas Grad III");
		

		private String _calculatedWith;
		private String _text;
		

		/**
		 * Instantiates a new bMI evaluation enum.
		 *
		 * @param calculatedWith the calculated with
		 * @param text the text
		 */
		private BMIEvaluationEnum(String calculatedWith, String text) {
			_calculatedWith = calculatedWith;
			_text = text;
		}

		/**
		 * Gets the evaluated with.
		 *
		 * @return the evaluated with
		 */
		public String getEvaluatedWith(){
			return _calculatedWith;
		}
		
		/**
		 * Gets the text.
		 *
		 * @return the text
		 */
		public String getText() {
			return _text;
		}
	}
	
	/**
	 * Gets the bMI value
	 *
	 * @param height the height (kilogramm)
	 * @param weight the weight (meter)
	 * @return the BMI (Body Mass Index)
	 */
	public static double getBMI(double weight, double height) {
		return weight / ((height/100) * (height/100));
	}
	
	/**
	 * Gets the bMI evaluation.
	 *
	 * @param gender the gender
	 * @param age the age
	 * @param bmi the bmi
	 * @return the bMI evaluation
	 */
	public static BMIEvaluationEnum getBMIEvaluation(GenderEnum gender, double age, double bmi){
		if(age < 19){
			return getPercentile(gender, age, bmi);
		} else {
			return getBMIEnum(bmi);
		}
	}

	private static void initiliaze() {
		_mapBoy = new HashMap<Double, Percentile>();
		_mapGirl = new HashMap<Double, Percentile>();
		
		//Percentiletable for boys
		//http://www.dr-hintermueller.at/bmibuben.htm
		_mapBoy.put(0d, new Percentile(10.20, 11.01, 11.81, 12.68, 13.53, 14.28, 15.01));
		_mapBoy.put(0.5d, new Percentile(14.38, 15.06, 15.80, 16.70, 17.69, 18.66, 19.72));
		_mapBoy.put(1d, new Percentile(14.58, 15.22, 15.93, 16.79, 17.76, 18.73, 19.81));
		_mapBoy.put(1.5d, new Percentile(14.31, 14.92, 15.60, 16.44, 17.40, 18.37, 19.47));
		_mapBoy.put(2d, new Percentile(14.00, 14.58, 15.25, 16.08, 17.03, 18.01, 19.14));
		_mapBoy.put(2.5d, new Percentile(13.73, 14.31, 14.97, 15.80, 16.76, 17.76, 18.92));
		_mapBoy.put(3d, new Percentile(13.55, 14.13, 14.79, 15.62, 16.59, 17.62, 18.82));
		_mapBoy.put(3.5d, new Percentile(13.44, 14.01, 14.67, 15.51, 16.50, 17.56, 18.80));
		_mapBoy.put(4d, new Percentile(13.36, 13.94, 14.60, 15.45, 16.46, 17.54, 18.83));
		_mapBoy.put(4.5d, new Percentile(13.30, 13.88, 14.55, 15.42, 16.45, 17.56, 18.90));
		_mapBoy.put(5d, new Percentile(13.24, 13.83, 14.51, 15.40, 16.46, 17.61, 19.02));
		_mapBoy.put(5.5d, new Percentile(13.20, 13.80, 14.50, 15.40, 16.50, 17.71, 19.19));
		_mapBoy.put(6d, new Percentile(13.18, 13.79, 14.51, 15.45, 16.59, 17.86, 19.44));
		_mapBoy.put(6.5d, new Percentile(13.19, 13.82, 14.56, 15.53, 16.73, 18.07, 19.76));
		_mapBoy.put(7d, new Percentile(13.23, 13.88, 14.64, 15.66, 16.92, 18.34, 20.15));
		_mapBoy.put(7.5d, new Percentile(13.29, 13.96, 14.76, 15.82, 17.14, 18.65, 20.60));
		_mapBoy.put(8d, new Percentile(13.37, 14.07, 14.90, 16.01, 17.40, 19.01, 21.11));
		_mapBoy.put(8.5d, new Percentile(13.46, 14.18, 15.05, 16.21, 17.68, 19.38, 21.64));
		_mapBoy.put(9d, new Percentile(13.56, 14.31, 15.21, 16.42, 17.97, 19.78, 22.21));
		_mapBoy.put(9.5d, new Percentile(13.67, 14.45, 15.38, 16.65, 18.27, 20.19, 22.78));
		_mapBoy.put(10d, new Percentile(13.80, 14.60, 15.57, 16.89, 18.58, 20.60, 23.35));
		_mapBoy.put(10.5d, new Percentile(13.94, 14.78, 15.78, 17.14, 18.91, 21.02, 23.91));
		_mapBoy.put(11d, new Percentile(14.11, 14.97, 16.00, 17.41, 19.24, 21.43, 24.45));
		_mapBoy.put(11.5d, new Percentile(14.30, 15.18, 16.24, 17.70, 19.58, 21.84, 24.96));
		_mapBoy.put(12d, new Percentile(14.50, 15.41, 16.50, 17.99, 19.93, 22.25, 25.44));
		_mapBoy.put(12.5d, new Percentile(14.73, 15.66, 16.77, 18.30, 20.27, 22.64, 25.88));
		_mapBoy.put(13d, new Percentile(14.97, 15.92, 17.06, 18.62, 20.62, 23.01, 26.28));
		_mapBoy.put(13.5d, new Percentile(15.23, 16.19, 17.35, 18.94, 20.97, 23.38, 26.64));
		_mapBoy.put(14d, new Percentile(15.50, 16.48, 17.65, 19.26, 21.30, 23.72, 26.97));
		_mapBoy.put(14.5d, new Percentile(15.77, 16.76, 17.96, 19.58, 21.63, 24.05, 27.26));
		_mapBoy.put(15d, new Percentile(16.04, 17.05, 18.25, 19.89, 21.95, 24.36, 27.53));
		_mapBoy.put(15.5d, new Percentile(16.31, 17.33, 18.55, 20.19, 22.26, 24.65, 27.77));
		_mapBoy.put(16d, new Percentile(16.57, 17.60, 18.83, 20.48, 22.55, 24.92, 27.99));
		_mapBoy.put(16.5d, new Percentile(16.83, 17.87, 19.11, 20.77, 22.83, 25.18, 28.20));
		_mapBoy.put(17d, new Percentile(17.08, 18.13, 19.38, 21.04, 23.10, 25.44, 28.40));
		_mapBoy.put(17.5d, new Percentile(17.32, 18.39, 19.64, 21.31, 23.36, 25.68, 28.60));
		_mapBoy.put(18d, new Percentile(17.56, 18.63, 19.89, 21.57, 23.61, 25.91, 28.78));

		//Percentiletable for girls
		//http://www.dr-hintermueller.at/bmimaedchen.htm
		
		
		_mapGirl.put(0d, new Percentile(10.20, 10.99, 11.75, 12.58, 13.40, 14.12, 14.81));
		_mapGirl.put(0.5d, new Percentile(13.86, 14.55, 15.29, 16.16, 17.08, 17.95, 18.85));
		_mapGirl.put(1d, new Percentile(14.14, 14.81, 15.53, 16.40, 17.32, 18.25, 19.22));
		_mapGirl.put(1.5d, new Percentile(13.94, 14.59, 15.32, 16.19, 17.16, 18.11, 19.15));
		_mapGirl.put(2d, new Percentile(13.68, 14.33, 15.05, 15.93, 16.93, 17.92, 19.03));
		_mapGirl.put(2.5d, new Percentile(13.46, 14.10, 14.82, 15.71, 16.73, 17.76, 18.92));
		_mapGirl.put(3d, new Percentile(13.29, 13.93, 14.64, 15.54, 16.57, 17.64, 18.84));
		_mapGirl.put(3.5d, new Percentile(13.16, 13.79, 14.51, 15.42, 16.46, 17.56, 18.81));
		_mapGirl.put(4d, new Percentile(13.06, 13.69, 14.42, 15.33, 16.40, 17.54, 18.85));
		_mapGirl.put(4.5d, new Percentile(13.00, 13.64, 14.37, 15.31, 16.41, 17.58, 18.97));
		_mapGirl.put(5d, new Percentile(12.97, 13.61, 14.36, 15.32, 16.46, 17.69, 19.16));
		_mapGirl.put(5.5d, new Percentile(12.94, 13.60, 14.36, 15.35, 16.53, 17.83, 19.40));
		_mapGirl.put(6d, new Percentile(12.92, 13.59, 14.37, 15.39, 16.63, 17.99, 19.67));
		_mapGirl.put(6.5d, new Percentile(12.93, 13.62, 14.42, 15.48, 16.77, 18.21, 20.01));
		_mapGirl.put(7d, new Percentile(12.98, 13.69, 14.52, 15.62, 16.98, 18.51, 20.44));
		_mapGirl.put(7.5d, new Percentile(13.06, 13.80, 14.66, 15.81, 17.24, 18.86, 20.93));
		_mapGirl.put(8d, new Percentile(13.16, 13.92, 14.82, 16.03, 17.53, 19.25, 21.47));
		_mapGirl.put(8.5d, new Percentile(13.27, 14.06, 15.00, 16.25, 17.83, 19.65, 22.01));
		_mapGirl.put(9d, new Percentile(13.38, 14.19, 15.17, 16.48, 18.13, 20.04, 22.54));
		_mapGirl.put(9.5d, new Percentile(13.48, 14.33, 15.34, 16.70, 18.42, 20.42, 23.04));
		_mapGirl.put(10d, new Percentile(13.61, 14.48, 15.53, 16.94, 18.72, 20.80, 23.54));
		_mapGirl.put(10.5d, new Percentile(13.76, 14.66, 15.74, 17.20, 19.05, 21.20, 24.03));
		_mapGirl.put(11d, new Percentile(13.95, 14.88, 15.99, 17.50, 19.40, 21.61, 24.51));
		_mapGirl.put(11.5d, new Percentile(14.18, 15.14, 16.28, 17.83, 19.78, 22.04, 25.00));
		_mapGirl.put(12d, new Percentile(14.45, 15.43, 16.60, 18.19, 20.18, 22.48, 25.47));
		_mapGirl.put(12.5d, new Percentile(14.74, 15.75, 16.95, 18.56, 20.58, 22.91, 25.92));
		_mapGirl.put(13d, new Percentile(15.04, 16.07, 17.30, 18.94, 20.98, 23.33, 26.33));
		_mapGirl.put(13.5d, new Percentile(15.35, 16.40, 17.64, 19.30, 21.36, 23.71, 26.70));
		_mapGirl.put(14d, new Percentile(15.65, 16.71, 17.97, 19.64, 21.71, 24.05, 27.01));
		_mapGirl.put(14.5d, new Percentile(15.92, 17.00, 18.27, 19.95, 22.02, 24.35, 27.26));
		_mapGirl.put(15d, new Percentile(16.18, 17.26, 18.53, 20.22, 22.28, 24.59, 27.45));
		_mapGirl.put(15.5d, new Percentile(16.40, 17.49, 18.76, 20.45, 22.50, 24.77, 27.57));
		_mapGirl.put(16d, new Percentile(16.60, 17.69, 18.96, 20.64, 22.67, 24.91, 27.65));
		_mapGirl.put(16.5d, new Percentile(16.78, 17.87, 19.14, 20.81, 22.82, 25.02, 27.69));
		_mapGirl.put(17d, new Percentile(16.95, 18.04, 19.31, 20.96, 22.95, 25.11, 27.72));
		_mapGirl.put(17.5d, new Percentile(17.11, 18.20, 19.47, 21.11, 23.07, 25.20, 27.74));
		_mapGirl.put(18d, new Percentile(17.27, 18.36, 19.62, 21.25, 23.19, 25.28, 27.76));
	}
	
	/**
	 * Gets the bMI enum.
	 *
	 * @param bmi the bmi
	 * @return the bMI enum
	 */
	private static BMIEvaluationEnum getBMIEnum(double bmi) {
		BMIEvaluationEnum bmie = null;
		if (bmi < 16) {
			bmie = BMIEvaluationEnum.SU;
		} else if (bmi < 17) {
			bmie = BMIEvaluationEnum.MU;
		} else if (bmi < 18.5) {
			bmie = BMIEvaluationEnum.LU;
		} else if (bmi < 25) {
			bmie = BMIEvaluationEnum.N;
		} else if (bmi < 30) {
			bmie = BMIEvaluationEnum.P;
		} else if (bmi < 35) {
			bmie = BMIEvaluationEnum.A1;
		} else if (bmi < 40) {
			bmie = BMIEvaluationEnum.A2;
		} else{
			bmie = BMIEvaluationEnum.A3;
		}
		return bmie;
	}
	
	/**
	 * Gets the percentile.
	 *
	 * @param gender the gender
	 * @param age the age
	 * @param bmi the bmi
	 * @return the percentile
	 */
	private static BMIEvaluationEnum getPercentile(GenderEnum gender, double age, double bmi) {
		if(_mapBoy == null && _mapGirl == null){
			initiliaze();
		}
		Percentile p = null;
		if(gender == GenderEnum.MALE){
			p = _mapBoy.get(age);
		} else{
			p = _mapGirl.get(age);
		}
		return p.getPercentile(bmi);
	}

	private static class Percentile {
		private double _p3;
		private double _p10;
		private double _p25;
		private double _p50;
		private double _p75;
		private double _p90;
		private double _p97;

		public Percentile(double p3, double p10, double p25, double p50, double p75, double p90, double p97) {
			_p3 = p3;
			_p10 = p10;
			_p25 = p25;
			_p50 = p50;
			_p75 = p75;
			_p90 = p90;
			_p97 = p97;
		}

		public BMIEvaluationEnum getPercentile(double bmi) {
			BMIEvaluationEnum percentile = null;
			if (bmi <= _p3) {
				percentile = BMIEvaluationEnum.P3;
			} else if (bmi <= _p10) {
				percentile = BMIEvaluationEnum.P10;
			} else if (bmi <= _p25) {
				percentile = BMIEvaluationEnum.P25;
			} else if (bmi <= _p50) {
				percentile = BMIEvaluationEnum.P50;
			} else if (bmi <= _p75) {
				percentile = BMIEvaluationEnum.P75;
			} else if (bmi <= _p90) {
				percentile = BMIEvaluationEnum.P90;
			} else if (bmi <= _p97 || bmi > _p97) {
				percentile = BMIEvaluationEnum.P97;
			}
			return percentile;
		}
	}
}

