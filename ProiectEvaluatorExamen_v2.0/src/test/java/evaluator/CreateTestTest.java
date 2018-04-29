package evaluator;
import evaluator.model.*;
import evaluator.controller.AppController;
import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.InputValidationFailedException;
import evaluator.exception.NotAbleToCreateTestException;
import evaluator.model.Intrebare;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class CreateTestTest {

    private AppController ctrl;

    @Before
    public void init() {
        ctrl = new AppController();
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void test1() throws NotAbleToCreateTestException {
        expectedEx.expect(NotAbleToCreateTestException.class);
        expectedEx.expectMessage("Nu exista suficiente intrebari pentru crearea unui test!(5)");
        ctrl.createNewTest();
    }

    @Test
    public void test2() throws NotAbleToCreateTestException,InputValidationFailedException,DuplicateIntrebareException {
        ctrl.loadTest2();
        expectedEx.expect(NotAbleToCreateTestException.class);
        expectedEx.expectMessage("Nu exista suficiente domenii pentru crearea unui test!(5)");
        ctrl.createNewTest();
    }

    @Test
    public void test3() throws NotAbleToCreateTestException,InputValidationFailedException,DuplicateIntrebareException {
        ctrl.loadTest3();
        evaluator.model.Test testut = ctrl.createNewTest();
        assertEquals(7,testut.getIntrebari().size());

    }

}
