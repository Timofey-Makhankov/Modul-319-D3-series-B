package lib;

// Get from Class library (JDK)
import java.util.*;  // Gets a parser for input data, GregorianCalendar
import java.time.*;  // LocalDate, LocalTime for inputDate-Time
import java.time.format.*; // DateTimeFormatter, DateTimeParseException;


/**
 * Input Methods like the BASIC INPUT (or c's scanf()).
 *
 * Because Java has no simple console input, I added this functions to my library.
 * Simply import
 * <code>import static ch.tbz.lib.Input.*</code>
 * and then use
 * <code>int x = inputInt("Please enter x: ");</code>
 *
 * @version 0.1 (Oct 8, 2014) Original
 * @version 0.2 (Jan 24, 2020) JavaDoc updated
 * @version 0.3 (Jan 25, 2020) inputDate, inputTime, inputDateTime added
 * @author Philipp Gressly Freimann V0.1
 * @author Michael Kellenberger V0.2 ...
 */

public class Input {
    // Used to read console input
    private static Scanner sc = new Scanner(System.in);

    /**
     * @see setAskAgain();
     */
    private static boolean askAgain = true;

    /**
     * @see setAddQuestion()
     */
    private static String addQuestion = "";

    /**
     * @see setNumberFormatError()
     */
    private static String numberFormatErrorMessage = "ERROR entering a number!";

    /**
     * @see setDTFormatError()
     */
    private static String dtFormatErrorMessage = "ERROR entering a date/time!";

    // SETTER ======================
    /**
     * If askAgain is false, the user is asked only once.
     * @param askAgain Entering newlines do not ask the user again showing the "question". default: true
     */
    public static void setAskAgain(boolean askAgain) {Input.askAgain = askAgain;}

    /**
     * This message is shown to the user, if she/he does not enter a
     * number while using "intputInt()" or "inputDouble()".
     * @param errorMessage to show on NumberFormatExceptions.
     */
    public static void setNumberFormatError(String errorMessage) {
        Input.numberFormatErrorMessage = errorMessage;
    }

    /**
     * This message is shown to the user, if she/he does not enter a
     * date or time while using intput-Date and/or -Time.
     * @param errorMessage to show on DateTimeFormatExceptions.
     */
    public static void setDTFormatError(String errorMessage) {
        Input.dtFormatErrorMessage = errorMessage;
    }

    /**
     * If the programmer only wants to ask for a "value" instead of a
     * "question", this text has to be set.
     * Typically: setAddQuestion("Please enter the value for %s : ");
     * "%s" is replaced by the programmers question afterwards.
     * @param addQuestion has to contain a %s, where the parameter name should be replaced.
     */
    public static void setAddQuestion(String addQuestion) {
        if(addQuestion.indexOf("%s") < 0) {
            addQuestion = addQuestion + " (%s):";
        }
        Input.addQuestion = addQuestion;
    }


    // Helper methods ==================================

    /**
     * Helper: Prints out question text or preset text with %s replaced
     * @param question text or part to be inserted at 5s in preset question text
     */
    private static void quest(String question) {
        if(null == addQuestion || addQuestion.length() < 1) {
            System.out.println(question);
            return;
        }
        System.out.printf(addQuestion, question);
    }

    /**
     * Helper: Scans any Number out of a object (see number class)
     * @param question
     * @param numberClass
     * @return
     */
    private static Number inputNumber(String question, Object numberClass) {
        String answer = "";
        if(! askAgain) {
            answer = inputString(question);
        }
        while (true) {
            try {
                if(askAgain) {
                    answer = inputString(question);
                }
                Number zahl = 0; // will be changed soon...
                if(numberClass == byte.class) {
                    zahl = Byte.parseByte(answer);
                }
                if(numberClass == short.class) {
                    zahl = Short.parseShort(answer);
                }
                if(numberClass == int.class) {
                    zahl = Integer.parseInt(answer);
                }
                if(numberClass == long.class) {
                    zahl = Long.parseLong(answer);
                }
                if(numberClass == float.class) {
                    zahl = Float.parseFloat(answer);
                }
                if(numberClass == double.class) {
                    zahl = Double.parseDouble(answer);
                }
                return zahl;
            } catch (Exception ex) {
                System.out.println(numberFormatErrorMessage);
                if(!askAgain) {
                    answer = inputString();
                }
            }
        }
    }

    /**
     * Helper: Asks for a text without prompt
     * @return given input text
     */
    private static String inputString() {
        String eingabe = "";
        while(eingabe.length() < 1) {
            eingabe = sc.nextLine().trim();
        }
        return eingabe;
    }


    // Public methods to use ===================

    /**
     * Asks for a string value
     * @param question any Question having a text as answer
     * @return the given text
     */
    public static String inputString(String question) {
        String eingabe = "";
        if(! askAgain) {
            quest(question);
        }
        while(eingabe.length() < 1) {
            if(askAgain) {
                quest(question);
            }
            eingabe = sc.nextLine().trim();
        }
        return eingabe;
    }


    /**
     * Asks for a byte value
     * @param question any Question having a byte as answer
     * @return the given byte
     */
    public static byte inputByte(String question) {
        return (byte) inputNumber(question, byte.class);
    }

    /**
     * Asks for a short value
     * @param question any Question having a short integer  as answer
     * @return the given short integer
     */
    public static short inputShort(String question) {
        return (short) inputNumber(question, short.class);
    }

    /**
     * Asks for a int value
     * @param question any Question having an integer as answer
     * @return the given integer
     */
    public static int inputInt(String question) {
        return (int) inputNumber(question, int.class);
    }

    /** Asks for a long value
     public static long inputLong(String question) {
     return (long) inputNumber(question, long.class);
     }

     /**
     * Asks for a float value
     * @param question any Question having a single precision number as answer
     * @return the given single precision number
     */
    public static float inputFloat(String question) {
        return (float) inputNumber(question, float.class);
    }

    /**
     * Asks for a double value
     * @param question any Question having a double precision number as answer.
     * @return the given double precision number
     */
    public static double inputDouble(String question) {
        return (double) inputNumber(question, double.class);
    }



    /**
     * Asks for a single char.
     * Any further chars on the same line are ignored.
     * @param question any Question having a character as answer.
     * @return the given character number
     */
    public static char inputChar(String question) {
        return inputString(question).trim().charAt(0);
    }


    /**
     * Asks for a decision. Returns true, if the users input does not start with the letter 'n'.
     * @param question any Question having "yes" or "no" as answer.
     * @return true if the users answer does not start with an "n" or "N".
     */
    public static boolean inputBoolean(String question) {
        return 'n' != inputString(question).trim().toLowerCase().charAt(0);
    }

    /**
     * Asks for a single date and time data, formatted like tt.mm.yyyy hh:mm:ss
     * @param question any Question having a datetime as answer.
     * @return the given date
     */
    public static LocalDateTime inputDateTime(String question) {
        //GregorianCalendar calendar = new GregorianCalendar();
        // calendar.setTimeZone( TimeZone.getTimeZone("ECT") );

        DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        question = question + " (tt.mm.yyyy hh:mm:ss) ";
        while (true) {
            String dtString = "";
            if(!askAgain) {
                dtString = inputString(question);
            }
            try {
                if(askAgain) {
                    dtString = inputString(question);
                }
                return LocalDateTime.parse(dtString, dtFormat);
            }
            catch(DateTimeParseException pe) {
                System.out.println(dtFormatErrorMessage);
                if(!askAgain) {
                    dtString = inputString();
                }
            }
        }

    }
    /**
     * Asks for a single date data, formatted like tt.mm.yyyy
     * @param question any Question having a date as answer.
     * @return the given date
     */
    public static LocalDate inputDate(String question) {
        //GregorianCalendar calendar = new GregorianCalendar();
        // calendar.setTimeZone( TimeZone.getTimeZone("ECT") );

        DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        question = question + " (tt.mm.yyyy) ";
        while (true) {
            String dtString = "";
            if(!askAgain) {
                dtString = inputString(question);
            }
            try {
                if(askAgain) {
                    dtString = inputString(question);
                }
                return LocalDate.parse(dtString, dtFormat);
            }
            catch(DateTimeParseException pe) {
                System.out.println(dtFormatErrorMessage);
                if(!askAgain) {
                    dtString = inputString();
                }
            }
        }

    }
    /**
     * Asks for a single time data,  formatted like hh:mm:ss
     * @param question any Question having a time as answer.
     * @return the given date
     */
    public static LocalTime inputTime(String question) {

        DateTimeFormatter dtFormat = DateTimeFormatter.ISO_LOCAL_TIME;
        question = question + " (hh:mm:ss) ";
        while (true) {
            String dtString = "";
            if(!askAgain) {
                dtString = inputString(question);
            }
            try {
                if(askAgain) {
                    dtString = inputString(question);
                }
                return LocalTime.parse(dtString, dtFormat);
            }
            catch(DateTimeParseException pe) {
                System.out.println(dtFormatErrorMessage);
                if(!askAgain) {
                    dtString = inputString();
                }
            }
        }

    }

} // end of class Input