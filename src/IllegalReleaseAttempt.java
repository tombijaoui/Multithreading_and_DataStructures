public class IllegalReleaseAttempt extends IllegalMonitorStateException{

    /**
     * Constructor sets values to the attributes.
     */

    public IllegalReleaseAttempt() {
        super();
    }

    /**
     * Constructor sets values to the attributes.
     * @param msg the message to print.
     */

    public IllegalReleaseAttempt(String msg) {
        super(msg);
    }
}
