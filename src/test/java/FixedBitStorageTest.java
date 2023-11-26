import io.github.koufu193.util.BitStorage;
import io.github.koufu193.util.FixedLengthBitStorage;
import org.junit.Test;
import org.junit.matchers.JUnitMatchers;
import org.junit.runners.JUnit4;

public class FixedBitStorageTest {
    @Test
    public void write(){
        long[] longs=new long[256];
        FixedLengthBitStorage storage=new FixedLengthBitStorage(5,longs);
        for(int i=0;i<16*16*16;i++){
            System.out.println(storage.read());
        }
    }
    private static long[] generateLongs(int len){
        long[] result=new long[len];
        for(int i=0;i<result.length;i++){
            result[i]=i;
        }
        return result;
    }
}
