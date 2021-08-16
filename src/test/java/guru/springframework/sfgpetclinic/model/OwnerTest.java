package guru.springframework.sfgpetclinic.model;

import guru.springframework.sfgpetclinic.ModelTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

/*
    Author: jalnor
    Date: 7/13/2021 7:32 AM
    Project: guru.springframework.sfgpetclinic.model
*/
class OwnerTest implements ModelTest {

    Owner owner;

    @BeforeEach
    void setUp() {
        owner = new Owner(1L, "Joe", "Schmoe");
        owner.setCity("Key West");
        owner.setTelephone("1231231234");
    }
    // Can run multiple tests in same method
    @Test
    void dependentAssertions() {
        assertAll("Properties Test",
                () -> assertAll("Person Properties",
                        () -> assertEquals("Joe", owner.getFirstName()),
                        () -> assertEquals("Schmoe", owner.getLastName())),
                () -> assertAll("Owner Properties",
                        () -> assertEquals("Key West", owner.getCity()),
                        () -> assertEquals("1231231234", owner.getTelephone())
                ));
        assertThat(owner.getCity(), is("Key West"));
    }

    @DisplayName("Value Source Test")
    @ParameterizedTest(name = "{displayName} - {index}] {arguments}")
    @ValueSource(strings = {"Spring", "Framework", "Guru"})
    void testValueSource(String val) {
        System.out.println(val);
    }

    @DisplayName("Enum Source Test")
    @ParameterizedTest(name = "{displayName} - {index}] {arguments}")
    @EnumSource(OwnerType.class)
    void enumTest(OwnerType ownerType) {
        System.out.println(ownerType);
    }

    @DisplayName("CSV Input Test")
    @ParameterizedTest(name = "{displayName} - {index}] {arguments}")
    @CsvSource({
            "NC, 1, 1",
            "ND, 2, 2",
            "NM, 3, 3"
    })
    void csvInputTest(String stateName, int val1, int val2) {
        System.out.println(stateName + " : " + val1 + " : " + val2);
    }

    @DisplayName("CSV From File Test")
    @ParameterizedTest(name = "{displayName} - {index}] {arguments}")
    @CsvFileSource(resources = "/input.csv", numLinesToSkip = 1)
    void csvFromFileTest(String stateName, int val1, int val2) {
        System.out.println(stateName + " : " + val1 + " : " + val2);
    }

    @AfterEach
    void tearDown() {
    }

}