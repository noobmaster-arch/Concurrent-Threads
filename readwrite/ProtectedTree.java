package readwrite;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ProtectedTree{

    private Tree tree;
    public ProtectedTree(Tree t){
        tree = t;
    }

    final Lock lock = new ReentrantLock();
    final Condition sucreads = lock.newCondition();    
    int nosr=0, nw=0;

    public void write( int value){
        lock.lock();
        try{
            System.out.println("WE");
            this.tree.write(value);
            System.out.println("WX");
            if(++nw>nosr){
                sucreads.signal();
            }
        } finally{
            lock.unlock();
        }
    } 

    public int read(int value){
        lock.lock();
        int rval=0;
        try{
            while(nosr>=nw){
                sucreads.await();
            }
            rval=this.tree.read(value);
            if(value==rval){
                System.out.println("RS");
                ++nosr;
            }
            else{
                System.out.println("RF");
            }
        } 
        catch( InterruptedException e){
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
        finally{
            lock.unlock();     
            return rval;
        }
    }
} 