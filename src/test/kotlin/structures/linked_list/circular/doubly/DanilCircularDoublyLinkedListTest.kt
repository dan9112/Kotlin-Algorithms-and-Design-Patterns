package structures.linked_list.circular.doubly

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.random.Random.Default.nextInt

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class DanilCircularDoublyLinkedListTest {
    private val danilCircularDoubleLinkedList = DanilCircularDoubleLinkedList<Int>()

    @BeforeEach
    fun setUp() {
        danilCircularDoubleLinkedList.clear()
    }

    private fun intListProvider() = Stream.of(
        listOf(1, 3, 5, 20, 231, 573, 2134),
        listOf(-5, 983, 0, -8213, -87, 52438, 12215246),
        listOf(0, 121236, -2354353, -8, 1237, 4573, 234, -3, 2),
        listOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        listOf(-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1)
    )

    @ParameterizedTest
    @MethodSource("intListProvider")
    fun getSize(testList: List<Int>) = with(danilCircularDoubleLinkedList) {
        val list = testList.onEach { add(it) }

        assertThat(size).isEqualTo(list.size)

        add(0)

        assertThat(size).isEqualTo(list.size + 1)
    }

    @ParameterizedTest
    @MethodSource("intListProvider")
    fun addNext(testList: List<Int>) = with(danilCircularDoubleLinkedList) {
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

    @ParameterizedTest
    @MethodSource("intListProvider")
    fun add(testList: List<Int>) = with(danilCircularDoubleLinkedList) {
        val list = testList.onEach { add(it) }

        assertThat(toList()).isEqualTo(list)
    }

    @ParameterizedTest
    @MethodSource("intListProvider")
    fun addPrevious(testList: List<Int>) = with(danilCircularDoubleLinkedList) {
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

    @ParameterizedTest
    @MethodSource("intListProvider")
    fun removeAt(testList: List<Int>) = with(danilCircularDoubleLinkedList) {
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

    @ParameterizedTest
    @MethodSource("intListProvider")
    fun getCurrent(testList: List<Int>) = with(danilCircularDoubleLinkedList) {
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

    @ParameterizedTest
    @MethodSource("intListProvider")
    fun toList(testList: List<Int>) = with(danilCircularDoubleLinkedList) {
        val list = testList.onEach { add(it) }
        toList().forEachIndexed { index, element ->
            assertThat(element).isEqualTo(list[index])
        }
    }

    @ParameterizedTest
    @MethodSource("intListProvider")
    fun toReverseList(testList: List<Int>) = with(danilCircularDoubleLinkedList) {
        val list = testList.onEach { add(it) }.reversed()
        toReversedList().forEachIndexed { index, element ->
            assertThat(element).isEqualTo(list[index])
        }
    }

    @ParameterizedTest
    @MethodSource("intListProvider")
    fun goForward(testList: List<Int>) = with(danilCircularDoubleLinkedList) {
        testList.onEach { add(it) }.forEach {
            assertThat(current).isEqualTo(it)

            goForward(1)
        }
    }

    @ParameterizedTest
    @MethodSource("intListProvider")
    fun goBack(testList: List<Int>) = with(danilCircularDoubleLinkedList) {
        testList.onEach { add(it) }.reversed().forEach {
            goBack(1)

            assertThat(current).isEqualTo(it)
        }
    }

    @ParameterizedTest
    @MethodSource("intListProvider")
    fun clear(testList: List<Int>) = with(danilCircularDoubleLinkedList) {
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

    @ParameterizedTest
    @MethodSource("intListProvider")
    fun get(testList: List<Int>) = with(danilCircularDoubleLinkedList) {
        testList.onEach { add(it) }.forEachIndexed { index, item ->
            assertThat(get(index)).isEqualTo(item)
        }
    }
}