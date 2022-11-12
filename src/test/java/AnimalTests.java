import org.example.Activity;
import org.example.Animal;
import org.example.InvalidStateException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import static org.junit.jupiter.api.Assertions.*;

@Testable
public class AnimalTests {
    private static Animal cat;
    private static Animal mouse;

    @BeforeEach
    public void setup() {
        cat = new Animal("Barsik", 2, 10);
        mouse = new Animal("Myshik", 1, 1);
    }

    @AfterEach
    public void teardown() {
        cat = null;
        mouse = null;
    }

    @Test
    public void ifSleepingTest() {
        assertFalse(mouse.ifSleeping());
    }

    @Test
    public void massAfterEatTest() throws InvalidStateException {
        int expected = 3;
        int massFood = 2;
        assertEquals(expected, mouse.massAfterEat(massFood));
    }

    @Test
    public void testAnimalException() {
        mouse.setActivity(Activity.SLEEPING);
        assertThrows(InvalidStateException.class, () -> mouse.massAfterEat(5));
    }
}
