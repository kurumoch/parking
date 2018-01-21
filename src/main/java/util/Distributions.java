//package util;
//
///**
// * Created by denis on 21.01.2018.
// */
//public class Distributions {
//    public static double Uniform(double a, double b) {
//        return a + Math.random() * (b - a) ;
//    }
//
//    static double[] stairWidth = new double[257], stairHeight = new double[256];
//    static double stairWidth[257], stairHeight[256];
//    double x1 = 3.6541528853610088;
//    double A = 4.92867323399e-3; /// area under rectangle
//
//    void setupNormalTables() {
//        // coordinates of the implicit rectangle in base layer
//        stairHeight[0] = exp(-.5 * x1 * x1);
//        stairWidth[0] = A / stairHeight[0];
//        // implicit value for the top layer
//        stairWidth[256] = 0;
//        for (unsigned i = 1; i <= 255; ++i)
//        {
//            // such x_i that f(x_i) = y_{i-1}
//            stairWidth[i] = sqrt(-2 * log(stairHeight[i - 1]));
//            stairHeight[i] = stairHeight[i - 1] + A / stairWidth[i];
//        }
//    }
//
//    double NormalZiggurat() {
//        int iter = 0;
//        do {
//            unsigned long long B = BasicRandGenerator();
//            int stairId = B & 255;
//            double x = Uniform(0, stairWidth[stairId]); // get horizontal coordinate
//            if (x < stairWidth[stairId + 1])
//                return ((signed)B > 0) ? x : -x;
//            if (stairId == 0) // handle the base layer
//            {
//                static double z = -1;
//                double y;
//                if (z > 0) // we don't have to generate another exponential variable as we already have one
//                {
//                    x = Exponential(x1);
//                    z -= 0.5 * x * x;
//                }
//                if (z <= 0) // if previous generation wasn't successful
//                {
//                    do {
//                        x = Exponential(x1);
//                        y = Exponential(1);
//                        z = y - 0.5 * x * x; // we storage this value as after acceptance it becomes exponentially distributed
//                    } while (z <= 0);
//                }
//                x += x1;
//                return ((signed)B > 0) ? x : -x;
//            }
//            // handle the wedges of other stairs
//            if (Uniform(stairHeight[stairId - 1], stairHeight[stairId]) < exp(-.5 * x * x))
//                return ((signed)B > 0) ? x : -x;
//        } while (++iter <= 1e9); /// one billion should be enough
//        return NAN; /// fail due to some error
//    }
//
//    double Normal(double mu, double sigma) {
//        return mu + NormalZiggurat() * sigma;
//    }
//
//final static double xx1 = 7.69711747013104972;
//final  static double AA = 3.9496598225815571993e-3; /// area under rectangle
//
//    void setupExpTables() {
//        // coordinates of the implicit rectangle in base layer
//        stairHeight[0] = exp(-xx1);
//        stairWidth[0] = AA / stairHeight[0];
//        // implicit value for the top layer
//        stairWidth[256] = 0;
//        for (unsigned i = 1; i <= 255; ++i)
//        {
//            // such x_i that f(x_i) = y_{i-1}
//            stairWidth[i] = -log(stairHeight[i - 1]);
//            stairHeight[i] = stairHeight[i - 1] + AA / stairWidth[i];
//        }
//    }
//
//    double ExpZiggurat() {
//        int iter = 0;
//        do {
//            int stairId = BasicRandGenerator() & 255;
//            double x = Uniform(0, stairWidth[stairId]); // get horizontal coordinate
//            if (x < stairWidth[stairId + 1]) /// if we are under the upper stair - accept
//                return x;
//            if (stairId == 0) // if we catch the tail
//                return xx1 + ExpZiggurat();
//            if (Uniform(stairHeight[stairId - 1], stairHeight[stairId]) < exp(-x)) // if we are under the curve - accept
//                return x;
//            // rejection - go back
//        } while (++iter <= 1e9); // one billion should be enough to be sure there is a bug
//        return NAAN; // fail due to some error
//    }
//
//    double Exponential(double rate) {
//        return ExpZiggurat() / rate;
//    }
//}
