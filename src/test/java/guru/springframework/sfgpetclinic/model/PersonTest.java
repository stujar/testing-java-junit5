package guru.springframework.sfgpetclinic.model;

import guru.springframework.sfgpetclinic.ModelTests;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/*
    Author: jalnor
    Date: 7/12/2021 5:54 AM
    Project: guru.springframework.sfgpetclinic.model
*/
class PersonTest implements ModelTests {

    @Test
    void groupedAssertions() {
        // given
        Person person = new Person(1L, "Joe", "Shmoe");

        // then
        assertAll("Testing Properties are set...",
                () -> assertEquals("Joe", person.getFirstName()),
                () -> assertEquals("Shmoe", person.getLastName()));
    }

    @Test
    void groupedAssertionsMessages() {
        // given
        Person person = new Person(1L, "Joe", "Shmoe");

        // then
        assertAll("Testing Properties are set...",
                () -> assertEquals( "Joe", person.getFirstName(), "First name failed..."),
                () -> assertEquals( "Shmoe", person.getLastName(),"Last name failed..."));
    }

    @RepeatedTest(value = 10, name = "{displayName} : {currentRepetition} of {totalRepetitions}")
    @DisplayName("My Repeated Test")
    void myRepeatedTest() {
        // TODO - impl
    }

    @RepeatedTest(5)
    @DisplayName("My Repeated Test with DI")
    void myRepeatedTestWithDI(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        System.out.println(testInfo.getDisplayName() /*+ " : " +
                repetitionInfo.getCurrentRepetition() + " of " + repetitionInfo.getTotalRepetitions()*/);
    }
}