import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class SpaceAgeShould {

    @TestFactory
    internal fun calculate_age_on_planets() = listOf(
        ageOnEarth(31557600.0 ) resultsIn  1.0,
        ageOnEarth(63115200.0 ) resultsIn  2.0,
        ageOnEarth(78894000.0 ) resultsIn  2.5,
        ageOnEarth(1000000000.0) resultsIn  31.69,
        ageOnUranus(2651370019.3296) resultsIn  1.0,
        ageOnUranus(5302740038.6592) resultsIn  2.0,
        ageOnUranus(67079661489.0389) resultsIn  25.3
    ).map{
        (actual, expectedAgeInYears) ->
        DynamicTest.dynamicTest("expecting $actual to be $expectedAgeInYears") {
            assertEquals(expectedAgeInYears, actual)
        }
    }
}

infix fun Double.resultsIn(that: Double): Pair<Double, Double> {
    return this to that
}

private fun ageOnUranus(age: Double): Double {
    return roundToTwoDecimalPlaces(ageOnEarth(age) / 84.02)
}

private fun ageOnEarth(age: Double):Double {
    return roundToTwoDecimalPlaces(age / 31557600)
}

private fun roundToTwoDecimalPlaces(toRound: Double):Double {
    return Math.round(toRound * 100.0) / 100.0
}
