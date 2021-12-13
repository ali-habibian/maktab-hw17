import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("------------- Strong Reference -------------");
        strongReference();

        System.out.println();
        System.out.println("------------- Weak References -------------");
        weakReferences();

        System.out.println();
        System.out.println("------------- Soft References -------------");
        softReference();

        System.out.println();
        System.out.println("------------- Phantom reference -------------");
        phantomReference();
    }

    private static void strongReference() throws InterruptedException {
        // Strong Reference - by default
        SampleObject object = new SampleObject();
        System.out.println("Object: " + object);

        // Now, object is eligible for garbage collection
        object = null;

        System.gc(); // Hint to run gc
        Thread.sleep(2000L); // sleep hoping to let GC thread run

        System.out.println(object);
    }

    // We use java.lang.ref.WeakReference class to create Weak References
    private static void weakReferences() throws InterruptedException {
        // Strong Reference
        SampleObject object = new SampleObject();
        System.out.println("Object: " + object);

        // Weak References
        WeakReference<SampleObject> weakReferenceObj = new WeakReference<SampleObject>(object);

        // Now, object is available for garbage collection.
        // But, it will be garbage collected only when JVM needs memory.
        object = null;

        System.out.println("Before GC: " + weakReferenceObj.get());

        System.gc(); // Hint to run gc
        Thread.sleep(2000L); // sleep hoping to let GC thread run

        System.out.println("After GC: " + weakReferenceObj.get());
    }

    // To create Soft Reference references java.lang.ref.SoftReference class is used
    private static void softReference() throws InterruptedException {
        // Strong Reference
        SampleObject object = new SampleObject();
        System.out.println("Object: " + object);

        // Soft Reference
        SoftReference<SampleObject> softReferenceObj = new SoftReference<>(object);

        // Now, object is available for garbage collection.
        // But, The objects gets cleared from the memory when JVM runs out of memory.
        object = null;

        System.out.println("Before GC: " + softReferenceObj.get());

        System.gc(); // Hint to run gc
        Thread.sleep(2000L); // sleep hoping to let GC thread run

        System.out.println("After GC: " + softReferenceObj.get());
    }

    //Code to illustrate Phantom reference
    private static void phantomReference() throws InterruptedException {
        // Strong Reference
        SampleObject object = new SampleObject();
        System.out.println("Object: " + object);

        // Creating reference queue
        ReferenceQueue<SampleObject> refQueue = new ReferenceQueue<>();

        // Creating Phantom Reference for object
        PhantomReference<SampleObject> phantomRef = new PhantomReference<SampleObject>(object, refQueue);

        // Now, object is available for garbage collection.
        // But, this object is kept in 'refQueue' before removing it from the memory.
        object = null;

        System.out.println("Before GC: " + phantomRef.get());

        System.gc(); // Hint to run gc
        Thread.sleep(2000L); // sleep hoping to let GC thread run

        System.out.println("After GC: " + phantomRef.get());
    }
}
