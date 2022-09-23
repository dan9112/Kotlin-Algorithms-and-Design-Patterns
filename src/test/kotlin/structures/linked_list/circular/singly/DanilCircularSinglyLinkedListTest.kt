package structures.linked_list.circular.singly

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.random.Random.Default.nextInt

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class DanilCircularSinglyLinkedListTest {
    private val danilCircularSinglyLinkedList = DanilCircularSinglyLinkedList<Int>()

    private fun intListProvider() = Stream.of(
        listOf(1, 3, 5, 20, 231, 573, 2134),
        listOf(-5, 983, 0, -8213, -87, 52438, 12215246),
        listOf(0, 121236, -2354353, -8, 1237, 4573, 234, -3, 2),
        listOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        listOf(-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1)
    )

    @BeforeEach
    fun setUp() {
        danilCircularSinglyLinkedList.clear()
    }

    @ParameterizedTest
    @MethodSource("intListProvider")
    fun getSize(testList: List<Int>) = with(danilCircularSinglyLinkedList) {
        val list = testList.onEach { add(it) }

        assertThat(size).isEqualTo(list.size)
    }

    @ParameterizedTest
    @MethodSource("intListProvider")
    fun add(testList: List<Int>) = with(danilCircularSinglyLinkedList) {
        testList.onEach { add(it) }.forEachIndexed { index, item ->
            assertThat(get(index)).isEqualTo(item)
        }
    }

    @ParameterizedTest
    @MethodSource("intListProvider")
    fun addNext(testList: List<Int>) = with(danilCircularSinglyLinkedList) {
        val list = mutableListOf<Int>()
        testList.forEach {
            val index = try {
                val localIndex = nextInt(size)
                addNext(it, localIndex)
                localIndex
            } catch (exception: IllegalArgumentException) {
                add(it)
                0
            }
            list.run {
                // Если следующий индекс больше индекса последнего элемента,
                // то добавить в конец, иначе - на следующую позицию
                if (index + 1 > lastIndex) add(it)
                else add(index + 1, it)
            }
        }

        assertThat(toList()).isEqualTo(list)
    }

    @ParameterizedTest
    @MethodSource("intListProvider")
    fun addPrevious(testList: List<Int>) = with(danilCircularSinglyLinkedList) {
        val list = mutableListOf<Int>()
        testList.forEach {
            val index = try {
                val localIndex = nextInt(size)
                addPrevious(it, localIndex)
                localIndex
            } catch (exception: IllegalArgumentException) {
                add(it)
                0
            }
            list.run {
                // Если индекс равен 0, то добавить в конец (так как в кольце
                // последний элемент является предшествующим для первого),
                // иначе - на указанную позицию со смещением списка вправо
                if (index == 0) add(it)
                else add(index, it)
            }
        }

        assertThat(toList()).isEqualTo(list)
    }

    @ParameterizedTest
    @MethodSource("intListProvider")
    fun removeAt(testList: List<Int>) = with(danilCircularSinglyLinkedList) {
        val list = testList.toMutableList().onEach { add(it) }
        val index = nextInt(size)

        removeAt(index)
        list.removeAt(index)

        assertThat(toList()).isEqualTo(list)
    }

    @ParameterizedTest
    @MethodSource("intListProvider")
    fun remove(testList: List<Int>) = with(danilCircularSinglyLinkedList) {
        val list = testList.toMutableList().onEach { add(it) }
        val item = list.random()

        remove(item)
        list.remove(item)

        assertThat(toList()).isEqualTo(list)
    }

    @Test
    fun isEmpty() = with(danilCircularSinglyLinkedList) {
        assertThat(isEmpty()).isTrue()

        add(2)

        assertThat(isEmpty()).isFalse()
    }

    @ParameterizedTest
    @MethodSource("intListProvider")
    fun toList(testList: List<Int>) = with(danilCircularSinglyLinkedList) {
        val list = testList.onEach { add(it) }
        toList().forEachIndexed { index, element ->
            assertThat(element).isEqualTo(list[index])
        }
    }

    @ParameterizedTest
    @MethodSource("intListProvider")
    fun goForward(testList: List<Int>) = with(danilCircularSinglyLinkedList) {
        testList.onEach { add(it) }.forEach {
            assertThat(current).isEqualTo(it)

            goForward(1)
        }
    }

    @ParameterizedTest
    @MethodSource("intListProvider")
    fun goBack(testList: List<Int>) = with(danilCircularSinglyLinkedList) {
        testList.onEach { add(it) }.reversed().forEach {
            goBack(1)

            assertThat(current).isEqualTo(it)
        }
    }

    @ParameterizedTest
    @MethodSource("intListProvider")
    fun getCurrent(testList: List<Int>) = with(danilCircularSinglyLinkedList) {
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

    @ParameterizedTest
    @MethodSource("intListProvider")
    fun clear(testList: List<Int>) = with(danilCircularSinglyLinkedList) {
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
    fun get(testList: List<Int>) = with(danilCircularSinglyLinkedList) {
        testList.onEach { add(it) }.forEachIndexed { index, item ->
            assertThat(get(index)).isEqualTo(item)
        }
    }
}
