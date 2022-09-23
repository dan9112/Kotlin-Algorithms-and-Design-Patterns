package structures.linked_list.circular.singly

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.random.Random.Default.nextInt

internal class DanilCircularSinglyLinkedListTest {
    private val danilCircularSinglyLinkedList = DanilCircularSinglyLinkedList<Int>()

    @BeforeEach
    fun setUp() {
        danilCircularSinglyLinkedList.clear()
    }

    private val testList
        get() = IntArray(nextInt(100)) { nextInt() }.toList()

    @Test
    fun getSize() = with(receiver = danilCircularSinglyLinkedList) {
        val testList = testList.onEach { add(it) }

        assertThat(size).isEqualTo(testList.size)
    }

    @Test
    fun add() = with(receiver = danilCircularSinglyLinkedList) {
        testList.onEach { add(it) }.forEachIndexed { index, item ->
            assertThat(get(index)).isEqualTo(item)
        }
    }

    @Test
    fun addNext() = with(receiver = danilCircularSinglyLinkedList) {
        val testList = mutableListOf<Int>()
        this@DanilCircularSinglyLinkedListTest.testList.forEach {
            val index = try {
                val localIndex = nextInt(size)
                addNext(it, localIndex)
                localIndex
            } catch (exception: IllegalArgumentException) {
                add(it)
                0
            }
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
    fun addPrevious() = with(receiver = danilCircularSinglyLinkedList) {
        val testList = mutableListOf<Int>()
        this@DanilCircularSinglyLinkedListTest.testList.forEach {
            val index = try {
                val localIndex = nextInt(size)
                addPrevious(it, localIndex)
                localIndex
            } catch (exception: IllegalArgumentException) {
                add(it)
                0
            }
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
    fun removeAt() = with(receiver = danilCircularSinglyLinkedList) {
        val testList = testList.toMutableList().onEach { add(it) }
        val index = nextInt(size)

        removeAt(index)
        testList.removeAt(index)

        assertThat(toList()).isEqualTo(testList)
    }

    @Test
    fun remove() = with(receiver = danilCircularSinglyLinkedList) {
        val testList = testList.toMutableList().onEach { add(it) }
        val item = testList.random()

        remove(item)
        testList.remove(item)

        assertThat(toList()).isEqualTo(testList)
    }

    @Test
    fun isEmpty() = with(receiver = danilCircularSinglyLinkedList) {
        assertThat(isEmpty()).isTrue()

        add(2)

        assertThat(isEmpty()).isFalse()
    }

    @Test
    fun toList() = with(danilCircularSinglyLinkedList) {
        val testList = testList.onEach { add(it) }
        toList().forEachIndexed { index, element ->
            assertThat(element).isEqualTo(testList[index])
        }
    }

    @Test
    fun goForward() = with(danilCircularSinglyLinkedList) {
        testList.onEach { add(it) }.forEach {
            assertThat(current).isEqualTo(it)

            goForward(1)
        }
    }

    @Test
    fun goBack() = with(danilCircularSinglyLinkedList) {
        testList.onEach { add(it) }.reversed().forEach {
            goBack(1)

            assertThat(current).isEqualTo(it)
        }
    }

    @Test
    fun getCurrent() = with(danilCircularSinglyLinkedList) {
        var item = nextInt()
        add(item)

        assertThat(current).isEqualTo(item)

        item = nextInt()
        val steps = nextInt(0, Int.MAX_VALUE)
        val realSteps = steps % size// остальное - полные обороты по кольцу
        addNext(item, steps)
        goForward(realSteps + 1)

        assertThat(current).isEqualTo(item)
    }

    @Test
    fun clear() = with(danilCircularSinglyLinkedList) {
        assertThat(isEmpty()).isTrue()
        assertThat(size).isEqualTo(0)

        add(2)
        add(5)
        add(3)

        assertThat(isEmpty()).isFalse()
        assertThat(size).isNotEqualTo(0)

        clear()

        assertThat(isEmpty()).isTrue()
        assertThat(size).isEqualTo(0)
    }

    @Test
    fun get() = with(danilCircularSinglyLinkedList) {
        testList.onEach { add(it) }.forEachIndexed { index, item ->
            assertThat(get(index)).isEqualTo(item)
        }
    }
}
