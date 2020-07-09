package Test;

import Model.NumberRanges;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class NumberRangesTest {
    private NumberRanges numberRanges;

    @Before
    public void init() {
        System.out.println("Start Test");
        numberRanges = new NumberRanges();
    }

    @Test
    public void testCheckListIsEmpty(){
        String list_1 = "Abc";
        String list_2 = "";
        //boolean result1 = numberRanges.checkListIsEmpty(list_1);
        //boolean result2= numberRanges.checkListIsEmpty(list_2);
        assertFalse("expected to be false", numberRanges.checkListIsEmpty(list_1));
        assertTrue("expected to be true", numberRanges.checkListIsEmpty(list_2));
        System.out.println("testCheckListIsEmpty is Done.");
    }

    @Test
    public void testTrueNumberIsraelPhoneCheck(){
        String range_1 = "999999";
        String range_2 = "0545929295";
        assertFalse("expected to be false", numberRanges.trueNumberIsraelPhoneCheck(range_1));
        assertTrue("expected to be true", numberRanges.trueNumberIsraelPhoneCheck(range_2));
        System.out.println("testTrueNumberIsraelPhoneCheck is Done.");

    }

    @Test
    public void testNegative(){
        int num_1 = 2;
        int num_2 = 2;
        int num_3 = 3;
        assertFalse("expected to be false", numberRanges.negative(num_3,num_2));
        assertTrue("expected to be true", numberRanges.negative(num_1,num_2));
        System.out.println("testNegative is Done.");
    }

    @Test
    public void testCheckList(){
        String str_1 = "54-5929295";
        String str_t1 = "545929295";
        String str_2 = "  ";
        String str_t2 = "";
        assertTrue("expected to be equals",str_t1.equals(numberRanges.checkList(str_1)));
        assertTrue("expected to be equals",str_t2.equals(numberRanges.checkList(str_2)));
        System.out.println("testCheckList is Done.");
    }

    @Test
    public void testFnrBreakToSingles(){
        String from_1 = "1";
        String to_1 = "9";
        List<Integer> integerList = IntStream.rangeClosed(1,9).boxed().collect(Collectors.toList());
        boolean isEqual = integerList.equals(numberRanges.fnrBreakToSingles(from_1,to_1));
        assertTrue("expected to be true", isEqual);
        System.out.println("testFnrBreakToSingles is Done.");
    }

}


