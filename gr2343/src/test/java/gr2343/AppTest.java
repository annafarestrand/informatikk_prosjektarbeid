package gr2343;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue(true);
    }
}

/**  test for input i tekstfelt
 * hvis input inneholder minst én bokstav så er dte ok, for det kan være feks. A3.
 * ikke ok hvis det kun er tall eller andre tegn
 * kan f.eks. loope over strenger og bruke isalpha() el.l.
*/

/** test for input i rangeringsfeltet
 * hvis input er i mengden [1:10] så er det ok
 * hvis input er noe annet er det ikke ok
 */