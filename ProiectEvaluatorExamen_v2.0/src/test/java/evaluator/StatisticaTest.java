package evaluator;

import evaluator.controller.AppController;
import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.InputValidationFailedException;
import evaluator.exception.NotAbleToCreateStatisticsException;
import evaluator.exception.NotAbleToCreateTestException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class StatisticaTest {

    private AppController ctrl;

    @Before
    public void init() {
        ctrl = new AppController();
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void test0() throws NotAbleToCreateStatisticsException {
        expectedEx.expect(NotAbleToCreateStatisticsException.class);
        expectedEx.expectMessage("Repository-ul nu contine nicio intrebare!");
        ctrl.getStatistica();
    }

    @Test
    public void test1() throws NotAbleToCreateStatisticsException, DuplicateIntrebareException, InputValidationFailedException {
        ctrl.loadTest3();
        ctrl.getStatistica();
    }
}
