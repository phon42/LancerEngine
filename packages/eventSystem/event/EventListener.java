package packages.EventSystem.event;

import main.HelperMethods;
import packages.CoreTypes.Callable;

public class EventListener {
    /**
     * Tracks the total number of EventListeners created since the program
     *     started running.
     */
    private static int numCreated = 0;
    /**
     * The type of event to listen for. A value of "" triggers on any event.
     * Must be a valid value as defined by Event.allowedTypes.
     */
    private String type;
    /**
     * The ID of the event listener.
     * Can be any int.
     */
    private int ID;
    /**
     * The method to call once the event listener is triggered.
     * Can be any Callable. Cannot be null.
     */
    private Callable method;
    /**
     * Whether or not to automatically delete this EventListener once it's been
     *     triggered.
     * Can be any boolean.
     */
    private boolean disposable;

    public EventListener(String eventType, int ID, Callable method,
        boolean disposable) {
        setType(eventType);
        setID(ID);
        setMethod(method);
        setDisposable(disposable);
        EventListener.numCreated++;
    }
    public EventListener(String eventType, boolean disposable, Callable method)
        {
        this(eventType, EventListener.numCreated, method, disposable);
    }
    public EventListener(String eventType, Callable method) {
        this(eventType, EventListener.numCreated, method, false);
    }
    
    public String getType() {
        return type;
    }
    public int getID() {
        return ID;
    }
    // getMethod() purposefully removed
    public boolean isDisposable() {
        return disposable;
    }
    private void setType(String type) {
        HelperMethods.checkString("type", type);
        this.type = type;
    }
    private void setID(int ID) {
        this.ID = ID;
    }
    private void setMethod(Callable method) {
        Callable copy;

        HelperMethods.checkObject("method", method);
        copy = new Callable() {
            @Override
            public void run() {
                method.run();
            }
        };
        this.method = copy;
    }
    public void setDisposable(boolean disposable) {
        this.disposable = disposable;
    }

    public void trigger() {
        method.run();
    }
}
