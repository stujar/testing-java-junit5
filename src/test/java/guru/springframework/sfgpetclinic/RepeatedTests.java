package guru.springframework.sfgpetclinic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInfo;

/*
    Author: jalnor
    Date: 7/28/2021 10:00 PM
    Project: guru.springframework.sfgpetclinic
*/
@Tag("repeated")
public interface RepeatedTests {

    @BeforeEach
    default void beforeEach(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        System.out.println("Testing... " + testInfo.getDisplayName() + " : " +
                repetitionInfo.getCurrentRepetition() + " | " + repetitionInfo.getTotalRepetitions());
    }
}
