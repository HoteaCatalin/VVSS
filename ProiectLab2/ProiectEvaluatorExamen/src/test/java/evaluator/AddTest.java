package evaluator;

import static org.junit.Assert.*;

import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.InputValidationFailedException;
import evaluator.model.Intrebare;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import evaluator.controller.AppController;

public class AddTest {

    private AppController ctrl;

    @Before
    public void init() {
        ctrl = new AppController();
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void test1() throws InputValidationFailedException, DuplicateIntrebareException {
        Intrebare intrebare = new Intrebare("1)Cat face 7*9?", "1)Zece", "2)unspe", "1", "Matematica");
        ctrl.addNewIntrebare(intrebare);
        assertEquals(true, ctrl.exists(intrebare));
    }

    @Test
    public void test2() throws InputValidationFailedException, DuplicateIntrebareException {
        expectedEx.expect(InputValidationFailedException.class);
        expectedEx.expectMessage("Enuntul este vid!");
        Intrebare intrebare = new Intrebare("", "zece", "unspe", "doi", "Matematica");
        ctrl.addNewIntrebare(intrebare);
    }

    @Test
    public void test3() throws InputValidationFailedException, DuplicateIntrebareException {
        expectedEx.expect(InputValidationFailedException.class);
        expectedEx.expectMessage("Prima litera din enunt nu e majuscula!");
        Intrebare intrebare = new Intrebare("asdfh?", "zece", "unspe", "doi", "Matematica");
        ctrl.addNewIntrebare(intrebare);
    }

    @Test
    public void test4() throws InputValidationFailedException, DuplicateIntrebareException {
        expectedEx.expect(InputValidationFailedException.class);
        expectedEx.expectMessage("Ultimul caracter din enunt nu e '?'!");
        Intrebare intrebare = new Intrebare("7)Asdfhgg", "1)Zece", "2)Unspe", "2", "Matematica");
        ctrl.addNewIntrebare(intrebare);
    }

    @Test
    public void test5() throws InputValidationFailedException, DuplicateIntrebareException {
        expectedEx.expect(InputValidationFailedException.class);
        expectedEx.expectMessage("Lungimea enuntului depaseste 100 de caractere!");
        Intrebare intrebare = new Intrebare("5)Asdfrysfuhergherhgreiihdugfejhewfukhrghefjkfgrefgyfedjfruedkfjgtyriekdjfgtruiejdfhgurdkjfhurfdkjchrjdfgurjdhturfjgturifkjgurufjghtuirfjkvhgturkedjhgtrrffkjghtjrfklmghtuirfkdcghtuirfkdfmghtuiroedklmfgturioedlkjghturioepdlkjhgturiedlkjghturiedlkjhgtu?", "zece", "unspe", "doi", "Matematica");
        ctrl.addNewIntrebare(intrebare);
    }
}