package patterns

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class SingletonTest {

    @Test
    fun singletonTest(){
        val obj1 = Singleton
        val obj2 = Singleton

        Assertions.assertEquals(obj1,obj2)
    }

}