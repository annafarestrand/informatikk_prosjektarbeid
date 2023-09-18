package gr2343;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.beans.Transient;

import org.junit.jupiter.api.Test;

// det er litt vanskelig å skrive tester når jeg ikke vet hvilke funksjoner som lages...

public class AppTest 
{
    /**  test for input i tekstfelt
 * hvis input inneholder minst én bokstav så er det ok, for det kan være feks. A3.
 * ikke ok hvis det kun er tall eller andre tegn
 * kan f.eks. loope over strenger og bruke isalpha() el.l.
 * 
 * kan kun bruke utsagn om klassens konstruktører og metoder
 * etter at noe(?) er konstruert skal den returnere en tom streng
 * etter getInput el.l. skal den returnere et tall som er mellom 1 og 10 (min 1 og max 10)
*/

    @Test
    public void inputIsText(){
        boolean containsLetter = false;

        for (int i = 0; i < inputFraTekstfelt.length(); i ++) {
            if (inputFraTekstfelt.at(i).isAlphabetic()) {            // isAlphabetic vs. isLetter 
                containsLetter = true;                               // hvis det finnes en bokstav blir denne true og man hopper ut av løkken
                break;
            }
        }
        assertTrue(containsLetter);                                  // ok hvis strengen inneholder letter, ikke ok hvis ikke
    }

    /** test for input i rangeringsfeltet
 * hvis input er i mengden [1:10] så er det ok
 * hvis input er noe annet er det ikke ok
 */

    @Test
    public void numberIsInInterval() {
        assertTrue(number >= 1 && number <= 10);
    }
}
