import com.jetbrain.Date;
import com.jetbrain.DefaultMember;
import com.jetbrain.Over60Member;
import org.junit.Test;

import static org.junit.Assert.*;

public class Over60MemberTest {

    @Test
    public void getAge() {
        int chckage= 85;
        Over60Member Over60MemberTest = new Over60Member("Hamza","10",85,new Date(10,10,2020));
        Over60MemberTest.setAge(chckage);
        assertEquals(chckage,Over60MemberTest.getAge());
    }
}