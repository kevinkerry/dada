package com.youyisi.sports.utils;

/***
 * LBS服务 计算工具类
 * 
 * @author shuye
 * @since 2015-06-01
 *
 */
public class LBSKit {

	/**
	 * 生成以中心点为中心的四方形经纬度
	 * 
	 * @param lat
	 *            纬度
	 * @param lon
	 *            精度
	 * @param raidus
	 *            半径（以米为单位）
	 * @return
	 */
	public static double[] getAround(double lat, double lon, int raidus) {

		Double latitude = lat;
		Double longitude = lon;

		Double degree = (24901 * 1609) / 360.0;
		double raidusMile = raidus;

		Double dpmLat = 1 / degree;
		Double radiusLat = dpmLat * raidusMile;
		Double minLat = latitude - radiusLat;
		Double maxLat = latitude + radiusLat;

		Double mpdLng = degree * Math.cos(latitude * (Math.PI / 180));
		Double dpmLng = 1 / mpdLng;
		Double radiusLng = dpmLng * raidusMile;
		Double minLng = longitude - radiusLng;
		Double maxLng = longitude + radiusLng;
		return new double[] { minLat, minLng, maxLat, maxLng };
	}

	/**
	 * 获取2点之间距离 方法一 计算中心经纬度与目标经纬度的距离（米）
	 * 
	 * @param centerLon
	 *            中心精度
	 * @param centerLan
	 *            中心纬度
	 * @param targetLon
	 *            需要计算的精度
	 * @param targetLan
	 *            需要计算的纬度
	 * @return 米
	 */
	public static double distance(double centerLon, double centerLat,
			double targetLon, double targetLat) {

		double jl_jd = 102834.74258026089786013677476285;// 每经度单位米;
		double jl_wd = 111712.69150641055729984301412873;// 每纬度单位米;
		double b = Math.abs((centerLat - targetLat) * jl_jd);

		double a = Math.abs((centerLon - targetLon) * jl_wd);
		return Math.sqrt((a * a + b * b));
	}

	/***
	 * 获取2点之间距离 方法二 从Android的提供的接口（Location.distanceBetween）中拔来的，应该是最精确的方法了
	 * 
	 * @param lat1
	 * @param lon1
	 * @param lat2
	 * @param lon2
	 * @return
	 */
	public static double computeDistance(double lat1, double lon1, double lat2,
			double lon2) {
		// Based on http://www.ngs.noaa.gov/PUBS_LIB/inverse.pdf
		// using the "Inverse Formula" (section 4)

		int MAXITERS = 20;
		// Convert lat/long to radians
		lat1 *= Math.PI / 180.0;
		lat2 *= Math.PI / 180.0;
		lon1 *= Math.PI / 180.0;
		lon2 *= Math.PI / 180.0;

		double a = 6378137.0; // WGS84 major axis
		double b = 6356752.3142; // WGS84 semi-major axis
		double f = (a - b) / a;
		double aSqMinusBSqOverBSq = (a * a - b * b) / (b * b);

		double L = lon2 - lon1;
		double A = 0.0;
		double U1 = Math.atan((1.0 - f) * Math.tan(lat1));
		double U2 = Math.atan((1.0 - f) * Math.tan(lat2));

		double cosU1 = Math.cos(U1);
		double cosU2 = Math.cos(U2);
		double sinU1 = Math.sin(U1);
		double sinU2 = Math.sin(U2);
		double cosU1cosU2 = cosU1 * cosU2;
		double sinU1sinU2 = sinU1 * sinU2;

		double sigma = 0.0;
		double deltaSigma = 0.0;
		double cosSqAlpha = 0.0;
		double cos2SM = 0.0;
		double cosSigma = 0.0;
		double sinSigma = 0.0;
		double cosLambda = 0.0;
		double sinLambda = 0.0;

		double lambda = L; // initial guess
		for (int iter = 0; iter < MAXITERS; iter++) {
			double lambdaOrig = lambda;
			cosLambda = Math.cos(lambda);
			sinLambda = Math.sin(lambda);
			double t1 = cosU2 * sinLambda;
			double t2 = cosU1 * sinU2 - sinU1 * cosU2 * cosLambda;
			double sinSqSigma = t1 * t1 + t2 * t2; // (14)
			sinSigma = Math.sqrt(sinSqSigma);
			cosSigma = sinU1sinU2 + cosU1cosU2 * cosLambda; // (15)
			sigma = Math.atan2(sinSigma, cosSigma); // (16)
			double sinAlpha = (sinSigma == 0) ? 0.0 : cosU1cosU2 * sinLambda
					/ sinSigma; // (17)
			cosSqAlpha = 1.0 - sinAlpha * sinAlpha;
			cos2SM = (cosSqAlpha == 0) ? 0.0 : cosSigma - 2.0 * sinU1sinU2
					/ cosSqAlpha; // (18)

			double uSquared = cosSqAlpha * aSqMinusBSqOverBSq; // defn
			A = 1
					+ (uSquared / 16384.0)
					* // (3)
					(4096.0 + uSquared
							* (-768 + uSquared * (320.0 - 175.0 * uSquared)));
			double B = (uSquared / 1024.0) * // (4)
					(256.0 + uSquared
							* (-128.0 + uSquared * (74.0 - 47.0 * uSquared)));
			double C = (f / 16.0) * cosSqAlpha
					* (4.0 + f * (4.0 - 3.0 * cosSqAlpha)); // (10)
			double cos2SMSq = cos2SM * cos2SM;
			deltaSigma = B
					* sinSigma
					* // (6)
					(cos2SM + (B / 4.0)
							* (cosSigma * (-1.0 + 2.0 * cos2SMSq) - (B / 6.0)
									* cos2SM
									* (-3.0 + 4.0 * sinSigma * sinSigma)
									* (-3.0 + 4.0 * cos2SMSq)));

			lambda = L
					+ (1.0 - C)
					* f
					* sinAlpha
					* (sigma + C
							* sinSigma
							* (cos2SM + C * cosSigma
									* (-1.0 + 2.0 * cos2SM * cos2SM))); // (11)

			double delta = (lambda - lambdaOrig) / lambda;
			if (Math.abs(delta) < 1.0e-12) {
				break;
			}
		}

		return b * A * (sigma - deltaSigma);
	}

	/**
	 * 获取2点之间距离 方法三 一种简单的计算2点间距离（把地球当成圆球来处理的）
	 * 
	 * @param lat1
	 * @param longt1
	 * @param lat2
	 * @param longt2
	 * @return
	 */
	public static double getDistance(double lat1, double longt1, double lat2,
			double longt2) {
		double PI = 3.1415; // 圆周率
		double R = 6371229; // 地球的半径

		double x, y, distance;
		x = (longt2 - longt1) * PI * R
				* Math.cos(((lat1 + lat2) / 2) * PI / 180) / 180;
		y = (lat2 - lat1) * PI * R / 180;
		distance = Math.hypot(x, y);

		return distance;
	}

	public static void main(String[] args) {

		// 经度 longitude :113.2759952545166
		// 纬度 latitude:23.117055306224895
		// 113.400088,23.095165

		double[] around = getAround(23.164681, 113.42041, 10000);

		/*
		 * double around = computeDistance(23.102088, 113.442856, 23.103209,
		 * 113.441857); System.out.println(around);
		 */

		System.out.println("minLat:" + around[0] + " ,minLng:" + around[1]
				+ " ,maxLat:" + around[2] + ",maxLng:" + around[3]);
		// System.out.println("left_lat:"+around[3]+ " ,right_lat:"+around[1]+
		// " ,down_lon:"+around[0]+ ",top_lon:"+around[2]);
		// 23.105968,113.455405
		// System.out.println(getDistance(23.104438, 113.464626, 23.105968,
		// 113.455405));
		// System.out.println(computeDistance(23.104438, 113.464626, 23.105968,
		// 113.455405));
	}
}
