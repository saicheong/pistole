package eclp;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class UFormatter {
    //====================================================================
    // Public Static Attributes
    //====================================================================

    /**
     * BWC Code for YES
     */
    public static final String BWC_YES = "Y";

    /**
     * BWC Code for NO
     */
    public static final String BWC_NO = "N";

    /**
     * Empty CIF Number
     */
    public static final String EMPTY_CIF = "0000000000000000000";

    /**
     * Zero
     */
    public static final BigDecimal ZERO = new BigDecimal(0.0);

    /**
     * One
     */
    public static final BigDecimal ONE = new BigDecimal("1");

    /* Long Zero */
    public static final Long LONG_ZERO = new Long(0);

    //====================================================================
    // Private Static Attributes
    //====================================================================

    /**
     * The type of field as alphanumeric
     */
    private static final String ALPHANUMERIC_FIELD_TYPE = "A";

    /**
     * The type of field as alphanumeric, with repeated values
     */
    private static final String ALPHANUMERIC_REPEAT_FIELD_TYPE = "AR";

    /**
     * The type of field as alphanumeric, with variable length
     */
    private static final String ALPHANUMERIC_VAR_LEN_FIELD_TYPE = "AV";

    /**
     * The type of field as numeric
     */
    private static final String NUMERIC_FIELD_TYPE = "N";

    /**
     * The type of field as numeric( not allowed 0 for mandatory )
     */
    private static final String NUMERIC_FIELD_TYPE_NO_ZERO = "NZ";

    /**
     * The type of field as numeric (with null packed as blank).
     */
    private static final String NUMERIC_FIELD_TYPE_W_NULL = "NN";

    /**
     * The type of field as signed number
     */
    private static final String SIGNED_NUMERIC_FIELD_TYPE = "SN";

    /**
     * The type of field as signed number but sign byte at end
     */
    private static final String REV_SN_FIELD_TYPE = "NS";

    /**
     * The type of field as signed number, with repeated values
     */
    private static final String SN_REPEAT_FIELD_TYPE = "SNR";

    /**
     * The type of field as signed number date, yyyyDDD
     */
    private static final String SN_DATE_FIELD_TYPE = "SND";

    /**
     * The type of field as date
     */
    private static final String DATE_FIELD_TYPE = "D";

    /**
     * The type of field as date time, yyyyMMddHHmmss
     */
    private static final String TIME_FIELD_TYPE = "T";

    /**
     * The type of field as date time, yyyyDDDHHmmss
     */
    private static final String TIME_FIELD_TYPE2 = "T2";

    /**
     * The type of field as date time, ddMMyyyyHHmmss
     */
    private static final String TIME_FIELD_TYPE3 = "T3";

    /**
     * The type of field as date time, HH:mm:ss
     */
    private static final String TIME_FIELD_TYPE4 = "T4";

    /**
     * The type of field as date time, HHmmss
     */
    private static final String TIME_FIELD_TYPE5 = "T5";

    /**
     * The type of field as date, YYYYMMDD
     */
    private static final String REV_DATE_FIELD_TYPE = "RD";

    /**
     * The type of field as date, DDMMYY
     */
    private static final String REV_DATE_FIELD_TYPE1 = "RD1";

    /**
     * The type of field as date, YYYY-MM-DD
     */
    private static final String REV_DATE_FIELD_TYPE2 = "RD2";

    /**
     * The type of field as date, YYYYDDD
     */
    private static final String REV_DATE_FIELD_TYPE3 = "RD3";

    /**
     * The type of field as boolean
     */
    private static final String BOOLEAN_FIELD_TYPE = "B";

    /**
     * The type of field as numeric string
     */
    private static final String NUMERIC_STRING_FIELD_TYPE = "AN";

    /**
     * The type of field as packed decimal string
     */
    private static final String PD_FIELD_TYPE = "PD";

    /**
     * The type of field as packed decimal string, with repeated values
     */
    private static final String PD_REPEAT_FIELD_TYPE = "PDR";

    /**
     * The type of field as packed decimal date, yyyyDDD
     */
    private static final String PD_DATE_FIELD_TYPE = "PDD";

    /**
     * The type of field as packed decimal date, yyMM
     */
    private static final String PD_DATE_FIELD_TYPE2 = "PDD2";

    /**
     * The default string for alphanumeric field
     */
    private static final String ALPHANUMERIC_PATTERN = " ";

    /**
     * The default string for alphanumeric field
     */
    private static final String NUMERIC_PATTERN = "0";

    /**
     * The default string for numeric with null field.
     */
    private static final String NUMERIC_W_NULL_PATTERN = " ";

    /**
     * The default string for "YES"
     */
    private static final String YES_IND = "Y";

    /**
     * The default string for "NO"
     */
    private static final String NO_IND = "N";

    /**
     * Indicates that the field is mandatory
     */
    private static final String MANDATORY = "M";

    /**
     * Indicates that the field is optional
     */
    private static final String OPTIONAL = "O";

    /**
     * The format of Time stamp
     */
    private static final String TIME_STAMP_PATTERN = "yyyyMMddHHmmss";

    /**
     * The format of Time stamp pattern 2
     */
    private static final String TIME_STAMP_PATTERN2 = "yyyyDDDHHmmss";

    /**
     * The format of Time stamp pattern 3
     */
    private static final String TIME_STAMP_PATTERN3 = "ddMMyyyyHHmmss";

    /**
     * The format of Time stamp pattern 4
     */
    private static final String TIME_STAMP_PATTERN4 = "HH:mm:ss";

    /**
     * The format of Time stamp pattern 5
     */
    private static final String TIME_STAMP_PATTERN5 = "HHmmss";

    /**
     * The format of Date stamp
     */
    private static final String DATE_STAMP_PATTERN = "ddMMyyyy";

    /**
     * The format of Date but yyyy comes first, used in batch files
     */
    private static final String REV_DATE_STAMP_PATTERN = "yyyyMMdd";

    /**
     * The format of Date but yyyy comes first, used in batch files
     */
    private static final String REV_DATE_STAMP_PATTERN2 = "yyyy-MM-dd";

    /**
     * The format of Date used in batch files
     */
    public static final String REV_DATE_STAMP_PATTERN3 = "yyyyDDD";

    /**
     * The format of Date used in batch files
     */
    private static final String REV_DATE_STAMP_PATTERN4 = "yyMM";

    /**
     * The format of Date used in batch files
     */
    private static final String REV_DATE_STAMP_PATTERN5 = "ddMMyy";

    /**
     * The date padded with zero
     */
    private static final String ZERO_DATE = "000000";

    /**
     * The singleton instance of this class
     */
    private static UFormatter instance = null;

    /**
     * The logger
     */
    private static Logger log = null;

    /**
     * The position of the field type in message format array
     */
    private static final int FIELD_TYPE_POS = 0;

    /**
     * The position of the field length in message format array
     */
    public static final int FIELD_LEN_POS = 1;

    /**
     * The position of the mandatory/optional field in message format array
     */
    private static final int FIELD_MAND_OPT_POS = 2;

    /**
     * The position of the number repeat field in message format array
     */
    public static final int FIELD_REPEAT_POS = 3;

    /**
     * The position of the name field in message format array
     */
    private static final int FIELD_NAME_POS = 4;

    // ------------------------------------------------------------------------
    // Instance attributes
    // ------------------------------------------------------------------------

    /**
     * Positive Sign nibble for packed Decimal
     */
    private static byte pdPosSign;

    /**
     * Negative Sign nibble for packed Decimal
     */
    private static byte pdNegSign;

    private static ArrayList badFieldLst;

    // ------------------------------------------------------------------------
    // Static Methods
    // ------------------------------------------------------------------------

    /**
     * Gets an instance of the UFormatter object
     *
     * @return UFormatter the instance of this class.
     */
    public static UFormatter getInstance() {
        if (instance == null) {
            instance = new UFormatter();
            badFieldLst = new ArrayList();
        }

        badFieldLst.clear();

        return instance;
    }

    /**
     * Gets an instance of the UFormatter object with positive and negative
     * sign nibble for Packed Decimal.
     *
     * @param pPdPosSign Positive Sign nibble for packed Decimal
     * @param pPdNegSign Negative Sign nibble for packed Decimal
     * @return UFormatter the instance of this class.
     */
    public static UFormatter getInstance(byte pPdPosSign, byte pPdNegSign) {
        pdPosSign = pPdPosSign;
        pdNegSign = pPdNegSign;

        return getInstance();
    }

    // ------------------------------------------------------------------------
    // Constructor
    // ------------------------------------------------------------------------

    /**
     * private constructor
     */
    private UFormatter() {
        log = new Logger();
    }

    // ------------------------------------------------------------------------
    // Instance Methods
    // ------------------------------------------------------------------------

    /**
     * Constructs the message according to the expected format by inserting
     * the contents into fields and pads to expected msg length.
     *
     * @param aFormat the message format array.
     * @param nValues the contents.
     */
    public String pack(String[][] aFormat, ArrayList nValues) {
        UAssert.preCond(aFormat.length > 0, "aFormat.length > 0");
        UAssert.preCond((nValues.size() == aFormat.length),
                "has content but diff array size than format");
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < aFormat.length; i++) {
            Object obj = nValues.get(i);
            String tokens[] = aFormat[i];
            buf.append(pack(tokens, obj));
        }

        return buf.toString();
    }

    /**
     * Constructs a message field according to the expected format by
     * inserting the contents into the field and pads to expected length.
     *
     * @param tokens the field format.
     * @param obj    the contents.
     */
    public String pack(String[] tokens, Object obj) {
        String retval = "";
        String type = tokens[FIELD_TYPE_POS];
        String len = tokens[FIELD_LEN_POS];
        boolean mand = (tokens[FIELD_MAND_OPT_POS]).equals(MANDATORY) ?
                true : false;
        int repeat = Integer.parseInt(tokens[FIELD_REPEAT_POS]);
        String fldname = tokens[FIELD_NAME_POS];
        if (obj instanceof ArrayList) {
            ArrayList val = (ArrayList) obj;
            doReqdAndTypeCheck(val, type, mand, fldname);
            retval = filler(val, type, len, repeat, fldname);
        } else {
            doReqdAndTypeCheck(obj, type, mand, fldname);
            retval = filler(obj, type, len, repeat, fldname);
        }

        return retval;
    }

    /**
     * Unpacks reply from basesystem into expected format
     *
     * @param aFormat the expected format
     * @param aReply  the reply from basesystem
     * @return the parsed results
     */
    public ArrayList unpack(String aFormat[][], String aReply) {
        ArrayList retval = new ArrayList();
        unpack(aFormat, aReply, retval, true);

        return retval;
    }

    /**
     * Unpacks reply from basesystem into expected format
     *
     * @param aFormat the expected format
     * @param aReply  the reply from basesystem
     * @param retval  the parsed result
     * @return flag to indicate if retval is good or bad
     */
    public boolean unpack(String aFormat[][],
                          String aReply,
                          ArrayList retval) {
        return unpack(aFormat, aReply, retval, true);
    }

    /**
     * Unpacks reply from basesystem into expected format
     *
     * @param aFormat the expected format
     * @param aReply  the reply from basesystem
     * @param retval  the parsed result
     * @param pTrim   true to trim string result, false otherwise
     * @return flag to indicate if retval is good or bad
     */
    public boolean unpack(String aFormat[][],
                          String aReply,
                          ArrayList retval,
                          boolean pTrim) {
        return unpack(aFormat, aReply, retval, pTrim, false);
        /*
        boolean goodRec = true;
        int start  = 0;
        int totLen = aReply.length();
        for ( int i=0; i<aFormat.length; i++ )
        {
            String tokens[] = aFormat[i];
            String type     = tokens[FIELD_TYPE_POS];
            String name     = tokens[FIELD_NAME_POS];

            // Get the format length and dec position for numbers
            int len = 0;
            int dec = 0;
            if ( type.equals( SIGNED_NUMERIC_FIELD_TYPE ) ||
                 type.equals( NUMERIC_FIELD_TYPE ) ||
                 type.equals( REV_SN_FIELD_TYPE ) )
            {
                String sn_len = tokens[FIELD_LEN_POS];
                int index = sn_len.indexOf(".");
                if ( index != -1 )
                {
                    len = Integer.parseInt( sn_len.substring(0,index) );
                    dec = Integer.parseInt( sn_len.substring(index+1) );
                } else
                {
                    len = Integer.parseInt( sn_len );
                }
            }
            else
            {
                len     = Integer.parseInt( tokens[FIELD_LEN_POS] );
            }

            int repeat  = Integer.parseInt( tokens[FIELD_REPEAT_POS] );
            for ( int j=0; j<repeat; j++ )
            {
                String result = null;
                if ( start+((j+1)*len) > totLen )
                {
                    goodRec = false;
                    if ( i == aFormat.length-1 )
                    {
                        // last field
                        result = aReply.substring( start+j*len );
                    }
                }
                else
                {
                    result    = aReply.substring( start+j*len,
                                                  (start+((j+1)*len) ) );
                }

                if ( type.equals( DATE_FIELD_TYPE) ||
                     type.equals( TIME_FIELD_TYPE) ||
                     type.equals( REV_DATE_FIELD_TYPE ) ||
                     type.equals( REV_DATE_FIELD_TYPE2 ) )
                {
                    if ( result == null ||
                         result.trim().length() == 0 ||
                         result.startsWith(ZERO_DATE) )
                    {
                        retval.add( null );
                    }
                    else
                    {
                        try
                        {
                            retval.add( getDateFormatter(type).parse( result ) );
                        }
                        catch ( ParseException pe )
                        {
                            log.error( "Error parsing date for field " +name,
                                       pe );
                            retval.add( null );
                        }

                    }
                }
                else if ( type.equals( SIGNED_NUMERIC_FIELD_TYPE ) ||
                          type.equals( NUMERIC_FIELD_TYPE ) )
                {
                    if ( result == null ||
                         result.trim().length() == 0 )
                    {
                        retval.add( ZERO );
                    }
                    else
                    {
                        String whole_val = result.substring( 0, len-dec );
                        String dec_val   = result.substring( len-dec );
                        if ( dec_val.length() > 0 )
                        {
                            result = whole_val + "." + dec_val;
                        } else
                        {
                            result = whole_val;
                        }

                        try
                        {
                            retval.add( new BigDecimal( result ) );
                        }
                        catch ( Exception pe )
                        {
                            log.error( "Exception encountered when unpacking field "+
                                       name, pe );
                            goodRec = false;
                            retval.add( ZERO );
                        }
                    }

                }
                else if ( type.equals( REV_SN_FIELD_TYPE ) )
                {
                    if ( result == null ||
                         result.trim().length() == 0 )
                    {
                        retval.add( ZERO );
                    }
                    else
                    {
                        try
                        {
                            String sign_val  = result.substring( len-1 );
                            String whole_val = result.substring( 0, len-1-dec );
                            String dec_val   = result.substring( len-1-dec, len-1 );
                            if ( dec_val.length() > 0 )
                            {
                                result = sign_val + whole_val + "." + dec_val;
                            }
                            else
                            {
                                result = sign_val + whole_val;
                            }
                            retval.add( new BigDecimal( result ) );
                        }
                        catch ( Exception pe )
                        {
                            log.error( "Exception encountered when unpacking field "+
                                       name, pe );
                            goodRec = false;
                            retval.add( ZERO );
                        }
                    }

                }
                else if ( type.equals( BOOLEAN_FIELD_TYPE ) )
                {
                    if ( result == null )
                    {
                        retval.add( null );
                    }
                    else if ( result.equals( YES_IND ) )
                    {
                        retval.add( new Boolean(true) );
                    }
                    else if ( result.equals( NO_IND ) )
                    {
                        retval.add( new Boolean(false) );
                    }
                    else
                    {
                        retval.add( null );
                    }
                }
                else
                {
                    // alpha numeric field & numeric string field, as is
                    if ( result == null )
                    {
                        StringBuffer padbuf = new StringBuffer();
                        pad( padbuf, " ", len, name );
                        retval.add( padbuf.toString() );
                    }
                    else
                    {
                        if ( pTrim )
                        {
                            retval.add( result.trim() );
                        }
                        else
                        {
                            // no trimming - to fix special char like \0
                            // lost in trimming
                            retval.add( result );
                        }
                    }
                }
            }
            if ( !goodRec )
            {
                log.info( "Encountered bad data at field : "+ name );
            }
            start = start + repeat*len;
        }

        if ( !goodRec )
        {
            log.info( "bad Rec : \n" + aReply );
        }
        return goodRec; */

    }

    /**
     * Unpacks reply from basesystem into expected format
     *
     * @param aFormat  the expected format
     * @param aReply   the reply from basesystem
     * @param retval   the parsed result
     * @param pTrim    true to trim string result, false otherwise
     * @param pChkMand true to check mandatory, false otherwise
     * @return flag to indicate if retval is good or bad
     */
    public boolean unpack(String aFormat[][],
                          String aReply,
                          ArrayList retval,
                          boolean pTrim,
                          boolean pChkMand) {
        boolean goodRec = true;
        int start = 0;
        int totLen = aReply.length();

        //System.out.println( ".... tot leng..."  +  totLen);
        for (int i = 0; i < aFormat.length; i++) {
            String tokens[] = aFormat[i];
            String type = tokens[FIELD_TYPE_POS];
            String name = tokens[FIELD_NAME_POS];
            boolean mand = (tokens[FIELD_MAND_OPT_POS]).equals(MANDATORY) ?
                    true : false;

            // Get the format length and dec position for numbers
            int len = 0;
            int dec = 0;
            if (type.equals(SIGNED_NUMERIC_FIELD_TYPE) ||
                    type.equals(NUMERIC_FIELD_TYPE) ||
                    type.equals(NUMERIC_FIELD_TYPE_NO_ZERO) ||
                    type.equals(REV_SN_FIELD_TYPE)) {
                String sn_len = tokens[FIELD_LEN_POS];
                int index = sn_len.indexOf(".");
                if (index != -1) {
                    len = Integer.parseInt(sn_len.substring(0, index));
                    dec = Integer.parseInt(sn_len.substring(index + 1));
                } else {
                    len = Integer.parseInt(sn_len);
                }
            } else {
                len = Integer.parseInt(tokens[FIELD_LEN_POS]);
            }

            int repeat = Integer.parseInt(tokens[FIELD_REPEAT_POS]);
            for (int j = 0; j < repeat; j++) {
                // for mandatory checking, only check the first record
                String result = null;
                if (start + ((j + 1) * len) > totLen) {
                    goodRec = false;
                    log.info("Encountered bad data at field : " + name);
                    if (i == aFormat.length - 1) {
                        // last field
                        result = aReply.substring(start + j * len);
                    }
                } else {
                    result = aReply.substring(start + j * len,
                            (start + ((j + 1) * len)));
                }

                //System.out.println( name + "(" + type + ")=" + result );
                if (type.equals(DATE_FIELD_TYPE) ||
                        type.equals(TIME_FIELD_TYPE) ||
                        type.equals(TIME_FIELD_TYPE2) ||
                        type.equals(TIME_FIELD_TYPE3) ||
                        type.equals(TIME_FIELD_TYPE4) ||
                        type.equals(REV_DATE_FIELD_TYPE) ||
                        type.equals(REV_DATE_FIELD_TYPE1) ||
                        type.equals(REV_DATE_FIELD_TYPE2)) {
                    if (result == null ||
                            result.trim().length() == 0 ||
                            result.startsWith(ZERO_DATE)) {
                        retval.add(null);
                        if (pChkMand && mand && j == 0) {
                            goodRec = false;
                            badFieldLst.add(name);
                            //log.info( "Encountered bad data at field : "+ name );
                        }
                    } else {
                        try {
                            retval.add(getDateFormatter(type).parse(result));
                        } catch (ParseException pe) {
                            log.error("Error parsing date for field " + name,
                                    pe);
                            retval.add(null);
                            if (pChkMand && mand) {
                                goodRec = false;
                                badFieldLst.add(name);
                                //log.info( "Encountered bad data at field : "+ name );
                            }
                        }

                    }
                } else if (type.equals(SIGNED_NUMERIC_FIELD_TYPE) ||
                        type.equals(NUMERIC_FIELD_TYPE) ||
                        type.equals(NUMERIC_FIELD_TYPE_NO_ZERO)) {
                    if (result == null ||
                            result.trim().length() == 0) {
                        retval.add(ZERO);
                        if (pChkMand && mand && j == 0) {
                            goodRec = false;
                            badFieldLst.add(name);
//                            log.info( "Encountered bad data at field : "+ name );
                        }
                    } else {
                        String whole_val = result.substring(0, len - dec);
                        String dec_val = result.substring(len - dec);
                        if (dec_val.length() > 0) {
                            result = whole_val + "." + dec_val;
                        } else {
                            result = whole_val;
                        }

                        try {
                            BigDecimal resultDecimal = new BigDecimal(result);
                            retval.add(resultDecimal);
                            // for mandatory checking non zero field, 0 not allowed
                            if (pChkMand && mand && j == 0 &&
                                    type.equals(NUMERIC_FIELD_TYPE_NO_ZERO) &&
                                    resultDecimal.compareTo(ZERO) == 0) {
                                goodRec = false;
                                badFieldLst.add(name);
//                                log.info( "Encountered bad data at field : "+ name );
                            }
                        } catch (Exception pe) {
                            log.error("Exception encountered when unpacking field " +
                                    name, pe);
                            goodRec = false;
                            log.info("Encountered bad data at field : " + name);
                            retval.add(ZERO);
                        }


                    }

                } else if (type.equals(REV_SN_FIELD_TYPE)) {
                    if (result == null ||
                            result.trim().length() == 0) {
                        retval.add(ZERO);
                        if (pChkMand && mand && j == 0) {
                            goodRec = false;
                            badFieldLst.add(name);
                            //  log.info( "Encountered bad data at field : "+ name );
                        }
                    } else {
                        try {
                            String sign_val = result.substring(len - 1);
                            String whole_val = result.substring(0, len - 1 - dec);
                            String dec_val = result.substring(len - 1 - dec, len - 1);
                            if (dec_val.length() > 0) {
                                result = sign_val + whole_val + "." + dec_val;
                            } else {
                                result = sign_val + whole_val;
                            }
                            retval.add(new BigDecimal(result));
                        } catch (Exception pe) {
                            log.error("Exception encountered when unpacking field " +
                                    name, pe);
                            goodRec = false;
                            log.info("Encountered bad data at field : " + name);
                            retval.add(ZERO);
                        }
                    }

                } else if (type.equals(BOOLEAN_FIELD_TYPE)) {
                    if (result == null) {
                        retval.add(null);
                        if (pChkMand && mand && j == 0) {
                            goodRec = false;
                            badFieldLst.add(name);
                            //   log.info( "Encountered bad data at field : "+ name );
                        }
                    } else if (result.equals(YES_IND)) {
                        retval.add(new Boolean(true));
                    } else if (result.equals(NO_IND)) {
                        retval.add(new Boolean(false));
                    } else {
                        retval.add(null);
                    }
                } else {
                    // alpha numeric field & numeric string field, as is
                    if (result == null) {
                        if (pChkMand && mand && j == 0) {
                            goodRec = false;
                            badFieldLst.add(name);
                            // log.info( "Encountered bad data at field : "+ name );
                        }
                        StringBuffer padbuf = new StringBuffer();
                        pad(padbuf, " ", len, name);
                        retval.add(padbuf.toString());
                    } else {
                        if (pChkMand && mand && result.trim().length() == 0 && j == 0) {
                            goodRec = false;
                            badFieldLst.add(name);
                            //    log.info( "Encountered bad data at field : "+ name );
                        }

                        if (pTrim) {
                            retval.add(result.trim());
                        } else {
                            // no trimming - to fix special char like \0
                            // lost in trimming
                            retval.add(result);
                        }
                    }
                }
            }
            start = start + repeat * len;
        }

        if (!goodRec) {
            log.info("bad Rec : \n" + aReply);
        }
        return goodRec;
    }

    /**
     * Pads a string to required length according to the field type.
     *
     * @param contents the collection of contents to be padded
     * @param type     the field type code of this field
     * @param lenStr   the expected length & format of content, eg 9.2 for SN
     * @param repeat   the no. of occurances
     * @param fldname  the field name, for debug printout
     */
    private String filler(ArrayList contents,
                          String type,
                          String lenStr,
                          int repeat,
                          String fldname) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < repeat; i++) {
            Object input = new String("");
            if (i < contents.size()) {
                input = contents.get(i);
            }
            buf.append(filler(input, type, lenStr, repeat, fldname));
        }
        return buf.toString();
    }

    /**
     * Pads a string to required length according to the field type.
     * Numeric field type pads 0 (default) in front.
     * AlphaNumeric field type pads " " (default).
     * NumericString field type pads "0"
     * Unsigned & Signed Numeric type pads 0 to expected decimal places
     * eg. signed numeric format SN12.6, 1.3456 => +00001345600
     *
     * @param obj     the content to be padded
     * @param type    the field type code of this field, A/N/SN
     * @param lenStr  the expected length & format of content, eg 9.2 for SN
     * @param repeat  the no. of occurances
     * @param fldname the field name, for debug printout
     */
    private String filler(Object obj, String type, String lenStr, int repeat, String fldname) {
        String pattern = ALPHANUMERIC_PATTERN;
        StringBuffer buf = new StringBuffer();
        int len = 0;

        //System.out.println( fldname + "(" + type +")=" + obj +":" + lenStr );
        if (type.equals(DATE_FIELD_TYPE) || type.equals(TIME_FIELD_TYPE) ||
                type.equals(TIME_FIELD_TYPE2) || type.equals(TIME_FIELD_TYPE3) ||
                type.equals(TIME_FIELD_TYPE4) || type.equals(TIME_FIELD_TYPE5) ||
                type.equals(REV_DATE_FIELD_TYPE) || type.equals(REV_DATE_FIELD_TYPE1) ||
                type.equals(REV_DATE_FIELD_TYPE2) || type.equals(REV_DATE_FIELD_TYPE3)) {
            pattern = NUMERIC_PATTERN;
            if (obj == null) {
                len = Integer.parseInt(lenStr);
                pad(buf, pattern, len, fldname);
            } else {
                Date value = (Date) obj;
                buf.append(getDateFormatter(type).format(value));
            }
        } else if (type.equals(PD_DATE_FIELD_TYPE) ||
                type.equals(PD_DATE_FIELD_TYPE2)) {
            len = Integer.parseInt(lenStr);
            if (obj == null) {
                buf.append(getPackedDecimal("0", len));
            } else {
                Date value = (Date) obj;
                buf.append(getPackedDecimal(
                        getDateFormatter(type).format(value), len));
            }
        } else if (type.equals(SN_DATE_FIELD_TYPE)) {
            len = Integer.parseInt(lenStr);
            pattern = NUMERIC_PATTERN;
            if (obj == null) {
                insert(buf, pattern, len - 1);
                buf.insert(0, "+");
            } else {
                Date value = (Date) obj;
                buf.append(getDateFormatter(type).format(value));
                if (buf.length() >= len) {
                    throw new RuntimeException("Exceeds expected field length for "
                            + fldname);
                }
                insert(buf, pattern, len - 1);
                buf.insert(0, "+");
            }
        } else if (type.equals(NUMERIC_FIELD_TYPE) ||
                type.equals(SIGNED_NUMERIC_FIELD_TYPE) ||
                type.equals(SN_REPEAT_FIELD_TYPE)) {
            pattern = NUMERIC_PATTERN;
            BigDecimal content = (BigDecimal) obj;

            int dec = 0;
            int index = lenStr.indexOf(".");
            if (index == -1) {
                len = Integer.parseInt(lenStr);
            } else {
                len = Integer.parseInt(lenStr.substring(0, index));
                dec = Integer.parseInt(lenStr.substring(index + 1));
            }

            if (content == null) {
                content = new BigDecimal(0.0);
            }

            if (type.equals(NUMERIC_FIELD_TYPE)) {
                content = content.abs();
            }

            content = content.setScale(dec, BigDecimal.ROUND_HALF_UP);
            content = content.movePointRight(dec);
            buf.append(content.longValue());
            if (buf.length() > len) {
                throw new RuntimeException("Exceeds expected field length for "
                        + fldname);
            }
            if ((type.equals(SIGNED_NUMERIC_FIELD_TYPE) ||
                    type.equals(SN_REPEAT_FIELD_TYPE)) &&
                    content.longValue() >= 0) {
                //pad( buf, pattern, len-1, fldname );
                insert(buf, pattern, len - 1);
                buf.insert(0, "+");
            } else if ((type.equals(SIGNED_NUMERIC_FIELD_TYPE) ||
                    type.equals(SN_REPEAT_FIELD_TYPE)) &&
                    content.longValue() < 0 &&
                    buf.charAt(0) == '-') {
                insert(buf.deleteCharAt(0), pattern, len - 1);
                buf.insert(0, "-");
            } else {
                insert(buf, pattern, len);
            }

            if (type.equals(SN_REPEAT_FIELD_TYPE)) {
                String strBuf = buf.toString();
                for (int i = 1; i < repeat; i++) {
                    buf.append(strBuf);
                }
            }
        } else if (type.equals(NUMERIC_FIELD_TYPE_W_NULL)) {
            pattern = NUMERIC_PATTERN;
            BigDecimal content = (BigDecimal) obj;

            int dec = 0;
            int index = lenStr.indexOf(".");
            if (index == -1) {
                len = Integer.parseInt(lenStr);
            } else {
                len = Integer.parseInt(lenStr.substring(0, index));
                dec = Integer.parseInt(lenStr.substring(index + 1));
            }

            if (content == null) {
                pad(buf, NUMERIC_W_NULL_PATTERN, len, fldname);
            } else {
                content = content.abs();
                content = content.setScale(dec, BigDecimal.ROUND_HALF_UP);
                content = content.movePointRight(dec);
                buf.append(content.longValue());
                if (buf.length() > len) {
                    throw new RuntimeException("Exceeds expected field length for "
                            + fldname);
                }
                insert(buf, pattern, len);
            }
        } else if (type.equals(BOOLEAN_FIELD_TYPE)) {
            Boolean val = (Boolean) obj;
            len = Integer.parseInt(lenStr);
            if (val == null) {
                pad(buf, pattern, len, fldname);
            } else {
                if (val.booleanValue()) {
                    buf.append(YES_IND);
                } else {
                    buf.append(NO_IND);
                }
            }
        } else if (type.equals(NUMERIC_STRING_FIELD_TYPE)) {
            pattern = NUMERIC_PATTERN;
            len = Integer.parseInt(lenStr);
            if (obj != null) {
                buf.append((String) obj);
            }
            insert(buf, pattern, len);
        } else if (type.equals(PD_FIELD_TYPE) ||
                type.equals(PD_REPEAT_FIELD_TYPE)) {
            len = Integer.parseInt(lenStr);

            for (int i = 0; i < repeat; i++) {
                if (obj != null) {
                    buf.append(getPackedDecimal(
                            ((Long) obj).toString(), len));
                } else {
                    buf.append(getPackedDecimal("0", len));
                }
            }
        } else if (type.equals(ALPHANUMERIC_VAR_LEN_FIELD_TYPE)) {
            len = Integer.parseInt(lenStr);
            if (obj != null) {
                buf.append((String) obj);
            }
            if (buf.length() > len) {
                log.warn("Field " + fldname + " exceeds expected length of " + len);
                buf.setLength(len);
            }

        } else {
            len = Integer.parseInt(lenStr);
            if (obj != null) {
                buf.append((String) obj);
            }
            pad(buf, pattern, len, fldname);

            if (type.equals(ALPHANUMERIC_REPEAT_FIELD_TYPE)) {
                String strBuf = buf.toString();
                for (int i = 1; i < repeat; i++) {
                    buf.append(strBuf);
                }
            }
        }

        return buf.toString();
    }


    /**
     * Convert a val to Packed Decimal. This piece of code is modified from BEA
     * code.
     *
     * @param val the value to be converted.
     * @param len the number of bytes needed for the conversions.
     */
    private String getPackedDecimal(String val, int len) {

        int j = 0;  //The number of digits to the right of the decimal point.
        byte byte0 = new Long(val).longValue() < 0 ? pdNegSign : pdPosSign;

        byte abyte0[] = new byte[len];
        int l = val.length() - 1;
        int i1 = abyte0.length - 1;
        int j1 = byte0 | Character.digit(val.charAt(l--), 10) << 4;

        for (abyte0[i1--] = (byte) j1; l >= 0; abyte0[i1--] = (byte) j1) {
            j1 = Character.digit(val.charAt(l--), 10);
            if (l >= 0)
                j1 |= Character.digit(val.charAt(l--), 10) << 4;
        }

        return new String(abyte0);
    }

    /**
     * Pads String with given pattern to expected length
     *
     * @param buf     the string to pad
     * @param pattern the pattern
     * @param len     the expected length
     * @param fldname the fieldname, for debug printout
     */
    private void pad(StringBuffer buf,
                     String pattern,
                     int len,
                     String fldname) {
        if (buf.length() > len) {
            //throw new RuntimeException( "Exceeds expected field length for "
            //                            +fldname );
            log.warn("Field " + fldname + " exceeds expected length of " + len);
            buf.setLength(len);
        }

        while (buf.length() < len) {
            buf.append(pattern);
        }
    }

    /**
     * Pads String with given pattern to expected length.  Pattern is
     * inserted to beginning of string.
     *
     * @param buf     the string to pad
     * @param pattern the pattern
     * @param len     the expected length
     */
    private void insert(StringBuffer buf, String pattern, int len) {
        if (buf.length() > len) {
            //throw new RuntimeException( "Exceeds expected field length for "
            //                            +fldname );
            log.warn("Exceeds expected length of " + len);
            buf.setLength(len);
        }

        while (buf.length() < len) {
            buf.insert(0, pattern);
        }
    }

    /**
     * Does checks on the field's content. Checks if field is mandatory, it cannot
     * be empty or null.
     *
     * @param contents  the collection of field's content
     * @param type      the field type
     * @param mandatory the mandatory field indicator, true if mandatory
     * @param name      the field name, for error message
     */
    private void doReqdAndTypeCheck(ArrayList contents,
                                    String type,
                                    boolean mandatory,
                                    String name) {
        if (mandatory) {
            UAssert.preCond(contents != null && contents.size() > 0,
                    "content for field, " + name + " not null or empty");
        }
        for (int i = 0; i < contents.size(); i++) {
            doReqdAndTypeCheck(contents.get(i), type, mandatory, name);
        }
    }

    /**
     * Does checks on the field's content. Checks if field is mandatory, it cannot
     * be empty or null. And if field type is numeric, it has to be valid number.
     *
     * @param obj       the field's content
     * @param type      the field type
     * @param mandatory the mandatory field indicator, true if mandatory
     * @param name      the field name, for error message
     */
    private void doReqdAndTypeCheck(Object obj,
                                    String type,
                                    boolean mandatory,
                                    String name) {
        if (mandatory) {
            UAssert.check(obj != null, name + " not null");
        }

        if (type.equals(DATE_FIELD_TYPE) ||
                type.equals(TIME_FIELD_TYPE) ||
                type.equals(TIME_FIELD_TYPE2) ||
                type.equals(TIME_FIELD_TYPE3) ||
                type.equals(TIME_FIELD_TYPE4) ||
                type.equals(TIME_FIELD_TYPE5) ||
                type.equals(REV_DATE_FIELD_TYPE) ||
                type.equals(REV_DATE_FIELD_TYPE1) ||
                type.equals(REV_DATE_FIELD_TYPE2) ||
                type.equals(REV_DATE_FIELD_TYPE3) ||
                type.equals(PD_DATE_FIELD_TYPE) ||
                type.equals(PD_DATE_FIELD_TYPE2) ||
                type.equals(SN_DATE_FIELD_TYPE)) {
            if (mandatory) {
                UAssert.check(obj instanceof Date, name + " must be date");
            }
        } else if (type.equals(BOOLEAN_FIELD_TYPE)) {
            if (mandatory) {
                UAssert.check(obj instanceof Boolean, name + " must be Boolean");
            }
        } else if (type.equals(SIGNED_NUMERIC_FIELD_TYPE) ||
                type.equals(NUMERIC_FIELD_TYPE) ||
                type.equals(NUMERIC_FIELD_TYPE_NO_ZERO) ||
                type.equals(NUMERIC_FIELD_TYPE_W_NULL) ||
                type.equals(SN_REPEAT_FIELD_TYPE)) {
            if (mandatory) {
                UAssert.check(obj instanceof BigDecimal, name + " must be BigDecimal");
            }
        } else if (type.equals(PD_FIELD_TYPE) ||
                type.equals(PD_REPEAT_FIELD_TYPE)) {
            if (mandatory) {
                UAssert.check(obj instanceof Long, name + " must be Long");
            }
        } else {
            if (mandatory) {
                UAssert.check(((String) obj).trim().length() > 0,
                        name + " not empty");
            }
        }
    }

    /**
     * Gets date formatter that formats to ddMMyyyy
     *
     * @return SimpleDateFormat
     */
    private SimpleDateFormat getDateFormatter() {
        return new SimpleDateFormat(DATE_STAMP_PATTERN);
    }

    /**
     * Gets date formatter that formats to ddMMyyyyHHmmss
     *
     * @return SimpleDateFormat
     */
    private SimpleDateFormat getTimeFormatter() {
        return new SimpleDateFormat(TIME_STAMP_PATTERN);
    }

    /**
     * Gets the formatter given the field type
     *
     * @param type either Date field type or Time field type
     * @return SimpleDateFormat
     */
    private SimpleDateFormat getDateFormatter(String type) {
        if (type.equals(DATE_FIELD_TYPE)) {
            return getDateFormatter();
        } else if (type.equals(TIME_FIELD_TYPE)) {
            return getTimeFormatter();
        } else if (type.equals(TIME_FIELD_TYPE2)) {
            return new SimpleDateFormat(TIME_STAMP_PATTERN2);
        } else if (type.equals(TIME_FIELD_TYPE3)) {
            return new SimpleDateFormat(TIME_STAMP_PATTERN3);
        } else if (type.equals(TIME_FIELD_TYPE4)) {
            return new SimpleDateFormat(TIME_STAMP_PATTERN4);
        } else if (type.equals(TIME_FIELD_TYPE5)) {
            return new SimpleDateFormat(TIME_STAMP_PATTERN5);
        } else if (type.equals(REV_DATE_FIELD_TYPE)) {
            return new SimpleDateFormat(REV_DATE_STAMP_PATTERN);
        } else if (type.equals(REV_DATE_FIELD_TYPE1)) {
            return new SimpleDateFormat(REV_DATE_STAMP_PATTERN5);
        } else if (type.equals(PD_DATE_FIELD_TYPE) ||
                type.equals(SN_DATE_FIELD_TYPE) ||
                type.equals(REV_DATE_FIELD_TYPE3)) {
            return new SimpleDateFormat(REV_DATE_STAMP_PATTERN3);
        } else if (type.equals(PD_DATE_FIELD_TYPE2)) {
            return new SimpleDateFormat(REV_DATE_STAMP_PATTERN4);
        } else {
            return new SimpleDateFormat(REV_DATE_STAMP_PATTERN2);
        }
    }


    /**
     * Get the field list for mandatory checking failure
     *
     * @return the fields list
     */
    public ArrayList getBadFieldList() {
        return this.badFieldLst;
    }

    /**
     * Remove preceding zero from string
     *
     * @param aStr the string to be formatted
     * @param num  the number of zeros to remove
     * @return the formatted string
     */
    protected static String removePrecedeZero(String aStr, int num) {
        if (aStr == null) {
            return "";
        }

        if (num <= 0) {
            return aStr;
        }

        for (int i = 0; i < num; i++) {
            if (aStr.startsWith("0")) {
                aStr = aStr.substring(1);
            }
        }
        return aStr;

    }

}
