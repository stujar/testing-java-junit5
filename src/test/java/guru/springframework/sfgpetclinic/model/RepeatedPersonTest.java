package guru.springframework.sfgpetclinic.model;

import guru.springframework.sfgpetclinic.RepeatedTests;
import org.junit.jupiter.api.*;


public class RepeatedPersonTest implements RepeatedTests {

    @RepeatedTest(value = 10, name = "{displayName} : {currentRepetition} of {totalRepetitions}")
    @DisplayName("My Repeated Test")
    void myRepeatedTest() {
        // TODO - impl
    }

    @RepeatedTest(5)
    @DisplayName("My Repeated Test with DI")
    void myRepeatedTestWithDI(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        System.out.println(testInfo.getDisplayName() + " : " +
                repetitionInfo.getCurrentRepetition() + " of " + repetitionInfo.getTotalRepetitions());
    }
}
