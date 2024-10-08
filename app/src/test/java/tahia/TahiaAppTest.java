package tahia;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TahiaAppTest {
    @Test void appHasAGreeting() {
        TahiaApp classUnderTest = new TahiaApp();
        assertNotNull(classUnderTest.getGreeting(), "app should have a greeting");
    }
}
