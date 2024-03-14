import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Class that represents a reentrant lock that we created
 */

public class MyReentrantLock implements Lock{

    private Thread userLock;
    private AtomicBoolean isLocked;
    private int countOfLocks;

    public MyReentrantLock(){
        this.userLock = null;
        this.isLocked = new AtomicBoolean();
        this.countOfLocks = 0;
    }

    /**
     * Checks if the lock is locked, if it is locked so the thread will wait a few milliseconds and will try again.
     */

    @Override
    public void acquire() {
        try {
            while (!tryAcquire()) {
                userLock.sleep(18);
                tryAcquire();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * try to lock the lock
     * @return true if it is possible, false if it isn't.
     */

    @Override
    public boolean tryAcquire() {
        boolean lockSucceeded = isLocked.compareAndSet(false,true);
        if(lockSucceeded){
            userLock = Thread.currentThread();
            countOfLocks++;
        }
        else{
            if (userLock == Thread.currentThread()){
                lockSucceeded = true;
                countOfLocks++;
            }
        }
        return lockSucceeded;
    }

    /**
     * unlock the lock by the current thread
     */
    @Override
    public void release() {
        if (!isLocked.get() || userLock != Thread.currentThread()){
            throw new IllegalReleaseAttempt();
        }
        else{
            countOfLocks--;
            if (countOfLocks == 0){
                userLock = null;
                isLocked.set(false);
            }
        }

    }

    @Override
    public void close() {
        release();
    }
}
