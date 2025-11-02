package Packages.EventSystem;

import Packages.CoreTypes.Callable;
import Packages.EventSystem.event.EventListener;
import main.HelperMethods;

/**
 * Represents an event of some kind. Contains information about the event's
 *     origin and type.
 * 
 * Requires an origin and a type to be instantiated.
 * 
 * Used in myriad other classes.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. None of its properties have allowed values of null.
 */
public class Event {
    /**
     * Contains an array of the EventListeners for this program.
     */
    public static EventListener[] eventListeners = new EventListener[0];

    /**
     * The event's origin as an array of StackTraceElements.
     * Must have a length of > 0. Cannot be null.
     */
    private StackTraceElement[] origin;
    /**
     * The event's type (i.e. "damage_taken").
     * Must be a valid value as defined by Event.allowedTypes. Cannot be "".
     * Case-insensitive and stored in lowercase.
     */
    private String type;
    /**
     * Contains an array of valid values for this.type.
     */
    public static final String[] allowedTypes = new String[] {"",
        "damage_taken"};

    public Event(String type) {
        setOrigin();
        setType(type);
        emit();
    }

    public StackTraceElement[] getOrigin() {
        return origin;
    }
    public String getType() {
        return type;
    }
    /**
     * Sets this.origin to a new stack trace.
     * @throws IllegalArgumentException if the stack trace has a length of 0.
     */
    private void setOrigin() {
        this.origin = new Throwable().getStackTrace();
        if (this.origin.length == 0) {
            throw new IllegalArgumentException("New origin array's length is"
                + " 0");
        }
    }
    /**
     * Sets this.duration to the provided value.
     * @param duration a String which cannot be null and cannot be an invalid
     *     value as defined by Event.allowedTypes.
     * @throws IllegalArgumentException if status is duration or an invalid
     *     value as defined by Event.allowedTypes.
     */
    public void setType(String type) {
        HelperMethods.checkString("New type", type);
        type = type.toLowerCase();
        for (String allowedType : Event.allowedTypes) {
            if (type.equals(allowedType)) {
                this.type = type;
                return;
            }
        }
        throw new IllegalArgumentException("New type value is an invalid value:"
            + " \"" + type + "\"");
    }

    public Object[] outputOrigin(int index) {
        StackTraceElement element;
        String fileName;
        String fileNameShort;
        int lineNumber;
        String methodName;
        boolean isNative;
        Object[] output;

        if (index < 0 || index > this.origin.length) {
            throw new IllegalArgumentException("index: " + index + " is out of"
                + " bounds for length: " + this.origin.length);
        }
        element = this.origin[index];
        fileName = element.getFileName();
        fileNameShort = fileName.split("\\.")[0];
        lineNumber = element.getLineNumber();
        methodName = element.getMethodName();
        isNative = element.isNativeMethod();
        output = new Object[] {
            fileName, fileNameShort, Integer.valueOf(lineNumber), methodName,
                Boolean.valueOf(isNative)
        };

        return output;
    }
    public void printOrigin() {
        Object[] element;
        String fileName;
        String fileNameShort;
        int lineNumber;
        String methodName;
        final String spaces = "        ";

        System.out.println("Origin of event of type: \"" + this.type + "\"");
        for (int i = 0; i < this.origin.length; i++) {
            element = outputOrigin(i);
            fileName = (String) element[0];
            fileNameShort = (String) element[1];
            lineNumber = (int) element[2];
            methodName = (String) element[3];
            // Example of an exception:
            //Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 2 out of bounds for length 2
            //        at Event.outputOrigin(Event.java:107)
            System.out.println(String.format(spaces + "at %s.%s(%s:%d)",
                fileNameShort, methodName, fileName, lineNumber));
        }
    }
    // TODO: add activation order of EventListeners - they should activate
    //     events on the same level before "bubbling" upwards
    /**
     * "Activates" the event, causing it to trigger any EventListeners listening
     *     for an Event of this.type.
     */
    private void emit() {
        EventListener listener;

        for (int i = 0; i < Event.eventListeners.length; i++) {
            listener = Event.eventListeners[i];
            if (getType().equals(listener.getType())
                || listener.getType().equals("")) {
                listener.trigger();
                if (listener.isDisposable()) {
                    Event.removeListener(listener.getID());
                }
            }
        }
        printOrigin();
    }
    private static int addListener(EventListener eventListener) {
        int ID = eventListener.getID();

        for (int i = 0; i < Event.eventListeners.length; i++) {
            if (Event.eventListeners[i].getID() == ID) {
                throw new IllegalArgumentException("Attempted to add an"
                    + " EventListener with a duplicate ID value: " + ID);
            }
        }
        Event.eventListeners = HelperMethods.append(Event.eventListeners,
            eventListener);
        
        return ID;
    }
    public static int addListener(String eventType, boolean disposable,
        Callable method) {
        HelperMethods.checkString("eventType", eventType);
        HelperMethods.checkObject("method", method);
        
        return addListener(new EventListener(eventType, disposable, method));
    }
    public static int addListener(String eventType, Callable method) {
        HelperMethods.checkString("eventType", eventType);
        HelperMethods.checkObject("method", method);

        return addListener(new EventListener(eventType, method));
    }
    public static void removeListener(int ID) {
        boolean containsID = false;
        EventListener[] newEventListeners;

        if (Event.eventListeners.length == 0) {
            throw new IllegalArgumentException("Called Event.removeListener()"
                + " when Event.eventListeners.length is 0");
        }
        newEventListeners = new EventListener[Event.eventListeners.length - 1];        
        for (int i = 0; i < Event.eventListeners.length; i++) {
            if (Event.eventListeners[i].getID() == ID) {
                containsID = true;
            }
            if (containsID) {
                if (i == newEventListeners.length) {
                    break;
                }
                newEventListeners[i] = Event.eventListeners[i + 1];
            } else {
                newEventListeners[i] = Event.eventListeners[i];
            }
        }
        if (! containsID) {
            throw new IllegalArgumentException("Could not find EventListener"
                + " with ID value: " + ID);
        }
        Event.eventListeners = newEventListeners;
    }
}
