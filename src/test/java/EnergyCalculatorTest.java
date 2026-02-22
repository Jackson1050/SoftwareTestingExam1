import static org.junit.jupiter.api.Assertions.*;

import org.example.EnergyCalculator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class EnergyCalculatorTest {

    @ParameterizedTest
    @CsvFileSource(resources="/EnergyCalculatorTests.csv", numLinesToSkip = 1)
    void testEnergyCalculatorNonError(String testCase, double kwh, boolean hasSmartDevice,
                                      boolean peakOptOut, double expectedRebate) throws Exception {
        EnergyCalculator energyCalculator = new EnergyCalculator();

        assertEquals(expectedRebate, energyCalculator.calculateRebate(kwh, hasSmartDevice, peakOptOut), "Energy Calculator TC " + testCase + " failed.");
    }

    @ParameterizedTest
    @CsvFileSource(resources="/EnergyCalculatorErrorTests.csv", numLinesToSkip = 1)
    void testEnergyCalculatorError(String testCase, double kwh, boolean hasSmartDevice,
                                   boolean peakOptOut, String exception) throws ClassNotFoundException {
        EnergyCalculator energyCalculator = new EnergyCalculator();

        Class<? extends Throwable> exceptionClass = (Class<? extends Throwable>) Class.forName(exception);
        assertThrows(exceptionClass, () -> {
            energyCalculator.calculateRebate(kwh, hasSmartDevice, peakOptOut);
        }, "Energy Calculator TC " + testCase + " failed.");

    }






}
