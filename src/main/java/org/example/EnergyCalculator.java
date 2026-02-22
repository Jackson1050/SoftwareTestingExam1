package org.example;

public class EnergyCalculator {

    public double calculateRebate(double kwh, boolean hasSmartDevice, boolean peakOptOut) {
        if (kwh <= 0) {
            throw new IllegalArgumentException("Usage must be positive.");
        }

        double rebatePercent = 0.0;

        if (kwh > 500 && kwh <= 1500) {
            //FAULT ONE
            //if (kwh >= 500 && kwh <= 1500)
            //This is a fault that would be caught by equivalence partitions
            if (hasSmartDevice && peakOptOut) {
                rebatePercent = 0.15;
            } else if (hasSmartDevice || peakOptOut) {
                rebatePercent = 0.10;
            }
        } else if (kwh > 1500) {
            //FAULT TWO
            //else if (kwh >= 1500)
            //This is a fault that would be caught by BVA, but not EP
            if (hasSmartDevice && peakOptOut) {
                //FAULT THREE
                //if (hasSmartDevice || peakOptOut)
                //This is a fault that would be caught by a Decision Table, but not BVA
                rebatePercent = 0.20;
                //FAULT FOUR
                //rebatePercent = .25;
                //This is a fault that would be caught by code coverage

                //FAULT FIVE
                //rebatePercent = .05;
                //This is a fault that would be caught by branch coverage
            } else {
                rebatePercent = 0.05;
            }
        }

        return rebatePercent;
    }
}
