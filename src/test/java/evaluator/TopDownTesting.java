package evaluator;

import evaluator.controller.AppController;
import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.InputValidationFailedException;
import evaluator.exception.NotAbleToCreateStatisticsException;
import evaluator.exception.NotAbleToCreateTestException;
import evaluator.model.Intrebare;
import evaluator.model.Statistica;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TopDownTesting {

    private AppController ctrl;

    @Before
    public void init() {
        ctrl = new AppController();
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();


    @Test
    public void testModulA() throws DuplicateIntrebareException, InputValidationFailedException {
        Intrebare intrebare = new Intrebare("Cat face 7*9?", "1)Zece", "2)unspe", "1", "Matematica");
        ctrl.addNewIntrebare(intrebare);
        assertEquals(true, ctrl.exists(intrebare));

        expectedEx.expect(InputValidationFailedException.class);
        expectedEx.expectMessage("Enuntul este vid!");
        Intrebare intrebare2 = new Intrebare("", "zece", "unspe", "doi", "Matematica");
        ctrl.addNewIntrebare(intrebare2);
    }

    @Test
    public void testIntegrareB() throws DuplicateIntrebareException, InputValidationFailedException, NotAbleToCreateTestException {
        ctrl.loadTest3();
        evaluator.model.Test testut = ctrl.createNewTest();
        assertEquals(7,testut.getIntrebari().size());
    }

    @Test
    public void testIntegrareC() throws DuplicateIntrebareException, InputValidationFailedException, NotAbleToCreateTestException, NotAbleToCreateStatisticsException {
        ctrl.loadTest3();
        evaluator.model.Test testut = ctrl.createNewTest();
        assertEquals(7,testut.getIntrebari().size());

        Statistica stat = ctrl.getStatistica();
        assertNotEquals(null,stat.getIntrebariDomenii());
    }

}
