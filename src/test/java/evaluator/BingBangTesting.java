package evaluator;

import evaluator.controller.AppController;
import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.InputValidationFailedException;
import evaluator.exception.NotAbleToCreateStatisticsException;
import evaluator.exception.NotAbleToCreateTestException;
import evaluator.model.Intrebare;
import evaluator.model.Statistica;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class BingBangTesting {

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
    public void testModulB() throws DuplicateIntrebareException, InputValidationFailedException, NotAbleToCreateTestException {
        ctrl.loadTest3();
        evaluator.model.Test testut = ctrl.createNewTest();
        assertEquals(7,testut.getIntrebari().size());

        ctrl = new AppController();

        ctrl.loadTest2();
        expectedEx.expect(NotAbleToCreateTestException.class);
        expectedEx.expectMessage("Nu exista suficiente domenii pentru crearea unui test!(5)");
        ctrl.createNewTest();
    }

    @Test
    public void testModulC() throws NotAbleToCreateStatisticsException, DuplicateIntrebareException, InputValidationFailedException {
        ctrl.loadTest3();
        ctrl.getStatistica();

        ctrl = new AppController();

        expectedEx.expect(NotAbleToCreateStatisticsException.class);
        expectedEx.expectMessage("Repository-ul nu contine nicio intrebare!");
        ctrl.getStatistica();
    }
    @Test
    public void testIntegrare() throws DuplicateIntrebareException, InputValidationFailedException, NotAbleToCreateTestException, NotAbleToCreateStatisticsException {
        ctrl.loadTest3(); // creaza intrebari
        evaluator.model.Test test = ctrl.createNewTest();
        assertEquals(7,test.getIntrebari().size());;

        Statistica stat = ctrl.getStatistica();
        Assert.assertNotEquals(null,stat);
        Assert.assertNotEquals(null,stat.getIntrebariDomenii());

    }
}
