package models;

import org.apache.commons.math3.distribution.AbstractRealDistribution;
import org.apache.commons.math3.exception.OutOfRangeException;

/**
 * Created by denis on 21.01.2018.
 */
public class DetermineDistribution extends AbstractRealDistribution {

    int a;

   public DetermineDistribution(int a){
        this.a = a;
    }

    @Override
    public double density(double v) {
        return 0;
    }

    @Override
    public double cumulativeProbability(double v) {
        return 0;
    }

    @Override
    public double inverseCumulativeProbability(double p) throws OutOfRangeException {
        return a;
    }

    @Override
    public double getNumericalMean() {
        return 0;
    }

    @Override
    public double getNumericalVariance() {
        return 0;
    }

    @Override
    public double getSupportLowerBound() {
        return 0;
    }

    @Override
    public double getSupportUpperBound() {
        return 0;
    }

    @Override
    public boolean isSupportLowerBoundInclusive() {
        return false;
    }

    @Override
    public boolean isSupportUpperBoundInclusive() {
        return false;
    }

    @Override
    public boolean isSupportConnected() {
        return false;
    }

    @Override
    public double sample() {
        return a;
    }
}
