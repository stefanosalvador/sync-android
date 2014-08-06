package cloudant.com.androidtest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.runner.notification.Failure;

/**
 * Created by rhys on 29/08/2014.
 */
public class TestResults {


    private String header;
    private Failure failure;
    private String methodName;
    private String className;
    private boolean failed = false;
    private long executionTime;
    private long startTimeMillis;

    public TestResults(Failure failure){
        this(failure.getTestHeader());
        this.failure = failure;
        failed = true;
    }

    public TestResults(String header){
        this.header = header;
        startTimeMillis = System.currentTimeMillis();
        splitIntoClassAndTestName(header);
    }

    public boolean hasFailed(){
        return failed;
    }

    @Override
    public String toString(){
        return failure.getTestHeader();
    }

    public String testName(){
        return failure.getTestHeader();
    }

    public String exceptionStack(){
        return ExceptionUtils.getStackTrace(failure.getException());
    }

    public String failureMessage(){
        return failure.getMessage();
    }

    public String className() {
        return className;
    }

    public String methodName(){
        return methodName;
    }

    public void calculateExcutionTime(long executionsTime){
        this.executionTime = executionsTime - startTimeMillis;
    }

    public double executionTime(){
        return executionTime / 1000.0 ;
    }

    public void testFailed(Failure failure){
        this.failure = failure;
        this.failed= true;
    }

    private void splitIntoClassAndTestName(String testHeader){
        int indexOfOpenBracket = testHeader.indexOf("(");
        methodName = testHeader.substring(0,indexOfOpenBracket);
        className = testHeader.substring(indexOfOpenBracket+1,testHeader.length()-1);
    }


    public String exceptionType() {
        return failure.getException().getClass().getName();
    }
}
