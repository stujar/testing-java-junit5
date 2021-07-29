package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.exceptionhandlers.ValueNotFoundException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

/*
    Author: jalnor
    Date: 7/12/2021 5:08 AM
    Project: guru.springframework.sfgpetclinic.controllers
*/
@Tag("controllers")
class IndexControllerTest {

    IndexController indexController;

    @BeforeEach
    void setUp() {
        indexController = new IndexController();
    }

    @DisplayName("Test proper view name is returned for the index page")
    @Test
    void index() {
        assertEquals("index", indexController.index());
        assertEquals("index", indexController.index(), "Wrong view returned");

        assertEquals("index", indexController.index(), () -> "Another expensive message, " +
                "make only if necessary!!!!!");

        assertThat(indexController.index()).isEqualTo("index");
    }

    @Test
    @DisplayName("Test exception")
    void oupsHandler() throws ValueNotFoundException {

        assertThrows(ValueNotFoundException.class, () -> indexController.oupsHandler());
//        assertTrue("notimplemented".equals(indexController.oupsHandler()), () -> "This is some expensive " +
//                "message to build " +
//                "for my test");
    }

    @Disabled("Test timeout")
    @Test
    void testTimeout() {
        assertTimeout(Duration.ofMillis(100),
                () -> {
                    Thread.sleep(2000);
                    System.out.println("I got here!");
                });
    }

    @Disabled("Test timeout preemptively")
    @Test
    void testTimeoutPreemptive() {
        assertTimeoutPreemptively(Duration.ofMillis(100),
                () -> {
                    Thread.sleep(2000);
                    System.out.println("I got here! (testTimeoutPreemptive())");
                });
    }

    @Test
    void testAssumptinTrue() {
//        System.out.println(System.getenv());
        assumeTrue("C:\\Users\\hal90\\AppData\\Local".equalsIgnoreCase(System.getenv("LOCALAPPDATA")));
    }

    @EnabledOnOs(OS.MAC)
    @Test
    void testMeOnMacOS() {
    }

    @EnabledOnOs(OS.WINDOWS)
    @Test
    void testMeOnWindows() {
    }

    @EnabledOnJre(JRE.JAVA_8)
    @Test
    void testMeOnJava8() {
    }

    @EnabledOnJre(JRE.JAVA_11)
    @Test
    void testMeOnJava11() {
    }

    @EnabledIfEnvironmentVariable(named = "USER", matches = "hal90")
    @Test
    void testIfUserHal90() {
    }

    @EnabledIfEnvironmentVariable(named = "USER", matches = "hal")
    @Test
    void testIfUserHal() {
    }
}