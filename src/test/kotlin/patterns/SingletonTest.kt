package patterns

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import qa.step

class SingletonTest {

    @Test
    fun singletonTest(){
        val obj1 = Singleton
        val obj2 = Singleton

        step("сравниваем объекты"){
            Assertions.assertEquals(obj1,obj2)
        }
    }

}