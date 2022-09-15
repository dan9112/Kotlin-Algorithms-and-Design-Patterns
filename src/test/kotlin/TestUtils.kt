import org.junit.jupiter.api.Assertions
import kotlin.random.Random

object TestUtils {

    fun <T> assertArrays(expected: Array<T>, actual: Array<T>) {
        Assertions.assertEquals(expected.toList(), actual.toList())
    }

    fun randomArray(size: Int) =  mutableRandomList(size).toTypedArray()
    fun sortedArray(size: Int) = mutableSortedList(size).toTypedArray()

    fun mutableRandomList(size: Int) = MutableList(size) { Random.nextInt(100) }
    fun mutableSortedList(size: Int) = MutableList(size) { it }
    fun list(size: Int) = List(size) { it }
    fun array(size: Int) = Array(size) { it }
}