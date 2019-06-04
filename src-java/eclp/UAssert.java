package eclp;

import java.text.MessageFormat;

public class UAssert {
    /** System property key for assert flag */
    private static final String ASSERT_FLAG = "pl.util.Assert";

    /** Message format for exception and log message */
    private static MessageFormat shortFmt = new MessageFormat( "{0} [{1}] failed" );

    /** Message format for exception and log message */
    private static MessageFormat longFmt = new MessageFormat( "{0} [{1}] failed: {2}" );

    /** whether assert facility is enabled - based on system property pl.util.Assert */
    private static boolean enabled = true;

    // string resources for exception and log message
    private static final String CHECK_FAILED = "Check";
    private static final String PRECOND_FAILED = "Pre-condition";
    private static final String POSTCOND_FAILED = "Post-condition";
    private static final String NO_MSG = "Reason not available";
    private static final String NO_ASSERT = "Condition not available";

    /** enable assertion */
    public static void enable()
    {
        enabled = true;
    }

    /** disable assertion */
    public static void disable()
    {
        enabled = false;
    }

    /** checks if assertion is enabled */
    public static boolean isEnabled()
    {
        return enabled;
    }

    /**
     * checks (asserts) the specified condition.
     *
     * @param anAssertCond the condition to assert
     * @param anAssertString the string representation of the condition to assert
     */
    public static void check( boolean anAssertCond, String anAssertString )
    {
        if( enabled && !anAssertCond)
        {
            fail( CHECK_FAILED, anAssertString );
        }
    }

    /**
     * checks (asserts) the specified condition.
     *
     * @param anAssertCond the condition to assert
     * @param anAssertString the string representation of the condition to assert
     * @param aMsg the reason for the failure
     */
    public static void check( boolean anAssertCond, String anAssertString, String aMsg )
    {
        if( enabled && !anAssertCond)
        {
            fail( CHECK_FAILED, anAssertString, aMsg );
        }
    }

    /**
     * pre-condition assert of the specified condition.
     *
     * @param anAssertCond the condition to assert
     * @param anAssertString the string representation of the condition to assert
     */
    public static void preCond( boolean anAssertCond, String anAssertString )
    {
        if( enabled && !anAssertCond)
        {
            fail( PRECOND_FAILED, anAssertString );
        }
    }

    /**
     * pre-condition assert of the specified condition.
     *
     * @param anAssertCond the condition to assert
     * @param anAssertString the string representation of the condition to assert
     * @param aMsg the reason for the failure
     */
    public static void preCond( boolean anAssertCond, String anAssertString, String aMsg )
    {
        if( enabled && !anAssertCond)
        {
            fail( PRECOND_FAILED, anAssertString, aMsg );
        }
    }

    /**
     * post-condition assert of the specified condition.
     * checks (asserts) the specified condition.
     *
     * @param anAssertCond the condition to assert
     * @param anAssertString the string representation of the condition to assert
     */
    public static void postCond( boolean anAssertCond, String anAssertString )
    {
        if( enabled && !anAssertCond)
        {
            fail( POSTCOND_FAILED, anAssertString );
        }
    }

    /**
     * post-condition assert of the specified condition.
     *
     * @param anAssertCond the condition to assert
     * @param anAssertString the string representation of the condition to assert
     * @param aMsg the reason for the failure
     */
    public static void postCond( boolean anAssertCond, String anAssertString, String aMsg )
    {
        if( enabled && !anAssertCond)
        {
            fail( POSTCOND_FAILED, anAssertString, aMsg );
        }
    }

    /**
     * called when an assert fails
     * @param aCat the type of assert (check, pre-condition or post-condition)
     * @param anAssertString the string representation of the condition to assert
     */
    private static void fail( String aCat, String anAssertString )
    {
        if( anAssertString == null || anAssertString.trim().length() == 0)
        {
            anAssertString = NO_ASSERT;
        }

        String[] errMsgParam = new String[]{aCat, anAssertString};
        String errMsg = shortFmt.format( errMsgParam );
        fail( errMsg );
    }

    /**
     * called when an assert fails
     * @param aCat the type of assert (check, pre-condition or post-condition)
     * @param anAssertString the string representation of the condition to assert
     * @param aMsg the reason for the failure
     */
    private static void fail( String aCat, String anAssertString, String aMsg )
    {
        if( anAssertString == null || anAssertString.trim().length() == 0)
        {
            anAssertString = NO_ASSERT;
        }
        if( aMsg == null || aMsg.trim().length() == 0)
        {
            aMsg = NO_MSG;
        }

        String[] errMsgParam = new String[]{aCat, anAssertString, aMsg};
        String errMsg = longFmt.format( errMsgParam );
        fail( errMsg );
    }

    /**
     * called when an assert fails
     * @param aErrMsg the message to use in the exception and log message.
     */
    private static void fail( String aErrMsg )
    {
        UAssertException ex = new UAssertException( aErrMsg );
        throw ex;
    }
}
