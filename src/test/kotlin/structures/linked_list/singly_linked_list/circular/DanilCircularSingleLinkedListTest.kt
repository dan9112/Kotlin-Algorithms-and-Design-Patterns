package structures.linked_list.singly_linked_list.circular

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.random.Random.Default.nextInt

internal class DanilCircularSingleLinkedListTest {
    private val dcsll = DanilCircularSingleLinkedList<Int>()

    @BeforeEach
    fun setUp() {
        dcsll.clear()
    }

    private val testList
        get() = IntArray(nextInt(100)) { nextInt() }.toList()

    @Test
    fun getSize() = with(receiver = dcsll) {
        val testList = testList.onEach { add(it) }

        assertThat(size).isEqualTo(testList.size)
    }

    @Test
    fun add() = with(receiver = dcsll) {
        testList.onEach { add(it) }.forEachIndexed { index, item ->
            assertThat(get(index)).isEqualTo(item)
        }
    }

    @Test
    fun addNext() = with(receiver = dcsll) {
        val testList = mutableListOf<Int>()
        this@DanilCircularSingleLinkedListTest.testList.forEach {
            val index = try {
                nextInt(size)
            } catch (exception: IllegalArgumentException) {
                0
            }
            addNext(it, index)
            testList.run {
                // Если следующий индекс больше индекса последнего элемента,
                // то добавить в конец, иначе - на следующую позицию
                if (index + 1 > lastIndex) add(it)
                else add(index + 1, it)
            }
        }

        assertThat(toList()).isEqualTo(testList)
    }

    @Test
    fun addPrevious() = with(receiver = dcsll) {
        val testList = mutableListOf<Int>()
        this@DanilCircularSingleLinkedListTest.testList.forEach {
            val index = try {
                nextInt(size)
            } catch (exception: IllegalArgumentException) {
                0
            }
            addPrevious(it, index)
            testList.run {
                // Если индекс равен 0, то добавить в конец (так как в кольце
                // последний элемент является предшествующим для первого),
                // иначе - на указанную позицию со смещением списка вправо
                if (index == 0) add(it)
                else add(index, it)
            }
        }

        assertThat(toList()).isEqualTo(testList)
    }

    @Test
    fun removeAt() = with(receiver = dcsll) {
        val testList = testList.toMutableList().onEach { add(it) }
        val index = nextInt(size)

        removeAt(index)
        testList.removeAt(index)

        assertThat(toList()).isEqualTo(testList)
    }

    @Test
    fun remove() = with(receiver = dcsll) {
        val testList = testList.toMutableList().onEach { add(it) }
        val item = testList.random()

        remove(item)
        testList.remove(item)

        assertThat(toList()).isEqualTo(testList)
    }

    @Test
    fun isEmpty() = with(receiver = dcsll) {
        assertThat(isEmpty()).isTrue()

        add(2)

        assertThat(isEmpty()).isFalse()
    }

    @Test
    fun toList() = with(dcsll) {
        val testList = testList.onEach { add(it) }
        toList().forEachIndexed { index, element ->
            assertThat(element).isEqualTo(testList[index])
        }
    }

    @Test
    fun goForward() = with(dcsll) {
        testList.onEach { add(it) }.forEach {
            assertThat(current).isEqualTo(it)

            goForward(1)
        }
    }

    @Test
    fun goBack() = with(dcsll) {
        testList.onEach { add(it) }.reversed().forEach {
            goBack(1)

            assertThat(current).isEqualTo(it)
        }
    }

    @Test
    fun getCurrent() = with(dcsll) {
        var item = nextInt()
        add(item)

        assertThat(current).isEqualTo(item)

        item = nextInt()
        val steps = nextInt(0, Int.MAX_VALUE)
        val realSteps = steps % size// остальное - полные обороты по кольцу
        addNext(item, realSteps)
        goForward(realSteps + 1)

        assertThat(current).isEqualTo(item)
    }
}
