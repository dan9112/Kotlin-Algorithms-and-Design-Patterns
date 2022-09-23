package structures.linked_list.circular.doubly

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.random.Random.Default.nextInt

internal class DanilCircularDoublyLinkedListTest {
    private val danilCircularDoubleLinkedList = DanilCircularDoubleLinkedList<Int>()

    @BeforeEach
    fun setUp() {
        danilCircularDoubleLinkedList.clear()
    }

    private val testList
        get() = IntArray(nextInt(6, 50)) { nextInt() }.toList()

    @Test
    fun getSize() = with(danilCircularDoubleLinkedList) {
        val testList = testList.onEach { add(it) }

        assertThat(size).isEqualTo(testList.size)

        add(0)

        assertThat(size).isEqualTo(testList.size + 1)
    }

    @Test
    fun addNext() = with(danilCircularDoubleLinkedList) {
        assertThrows<IllegalArgumentException> { addNext(2, 2) }

        val list = mutableListOf<Int>()
        testList.onEachIndexed { index, item ->
            if (index == 0) {
                add(item)
                list.add(item)
            } else {
                var localIndex = nextInt(size)
                addNext(item, localIndex)
                with(list) {
                    if (++localIndex != size) add(localIndex, item)
                    else add(item)
                }
            }
        }

        assertThat(toList()).isEqualTo(list)
    }

    @Test
    fun add() = with(danilCircularDoubleLinkedList) {
        val list = testList.onEach { add(it) }

        assertThat(toList()).isEqualTo(list)
    }

    @Test
    fun addPrevious() = with(danilCircularDoubleLinkedList) {
        assertThrows<IllegalArgumentException> { addPrevious(2, 2) }

        val list = mutableListOf<Int>()
        testList.onEachIndexed { index, item ->
            if (index == 0) {
                add(item)
                list.add(item)
            } else {
                val localIndex = nextInt(size)
                addPrevious(item, localIndex)
                with(list) {
                    if (localIndex != 0) add(localIndex, item)
                    else add(item)
                }
            }
        }

        assertThat(toList()).isEqualTo(list)
    }

    @Test
    fun removeAt() = with(danilCircularDoubleLinkedList) {
        val list = testList.onEach { add(it) }.toMutableList()
        var index: Int
        for (i in 0 until 5) {
            println("Before: ${toList()}\t$list")
            index = nextInt(size)
            println("RemoveAt: $index")
            list.removeAt(index)
            removeAt(index)
            println("After: ${toList()}\t$list")

            assertThat(toList()).isEqualTo(list)
        }
    }

    @Test
    fun remove() = with(danilCircularDoubleLinkedList) {
        val list = listOf(2, 4, 8, -234, 34564, 321, -235, 0).onEach { add(it) }.toMutableList()
        var item: Int
        for (i in 0 until 5) {
            item = list.random()
            remove(item)
            list.remove(item)

            assertThat(toList()).isEqualTo(list)
        }
    }

    @Test
    fun isEmpty() = with(danilCircularDoubleLinkedList) {
        assertThat(isEmpty()).isTrue()

        add(2)

        assertThat(isEmpty()).isFalse()

        remove(2)

        assertThat(isEmpty()).isTrue()
    }

    @Test
    fun getCurrent() = with(danilCircularDoubleLinkedList) {
        var item = nextInt()
        add(item)

        assertThat(current).isEqualTo(item)

        item = nextInt()
        val steps = nextInt(0, Int.MAX_VALUE)
        val realSteps = steps % size// остальное - полные обороты по кольцу
        addPrevious(item, steps)
        goBack(realSteps + 1)

        assertThat(current).isEqualTo(item)
    }

    @Test
    fun toList() = with(danilCircularDoubleLinkedList) {
        val testList = testList.onEach { add(it) }
        toList().forEachIndexed { index, element ->
            assertThat(element).isEqualTo(testList[index])
        }
    }

    @Test
    fun toReverseList() = with(danilCircularDoubleLinkedList) {
        val testList = testList.onEach { add(it) }.reversed()
        toReversedList().forEachIndexed { index, element ->
            assertThat(element).isEqualTo(testList[index])
        }
    }

    @Test
    fun goForward() = with(danilCircularDoubleLinkedList) {
        testList.onEach { add(it) }.forEach {
            assertThat(current).isEqualTo(it)

            goForward(1)
        }
    }

    @Test
    fun goBack() = with(danilCircularDoubleLinkedList) {
        testList.onEach { add(it) }.reversed().forEach {
            goBack(1)

            assertThat(current).isEqualTo(it)
        }
    }

    @Test
    fun clear() = with(danilCircularDoubleLinkedList) {
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
    fun get() = with(danilCircularDoubleLinkedList) {
        testList.onEach { add(it) }.forEachIndexed { index, item ->
            assertThat(get(index)).isEqualTo(item)
        }
    }
}