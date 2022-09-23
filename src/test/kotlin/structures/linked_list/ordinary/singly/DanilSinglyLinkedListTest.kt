package structures.linked_list.ordinary.singly

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
internal class DanilSinglyLinkedListTest {
    private val danilSinglyLinkedList = DanilSinglyLinkedList<Int>()

    @BeforeEach
    fun setUp() {
        danilSinglyLinkedList.clear()
    }

    private fun intListProvider() = Stream.of(
        mutableListOf(1, 3, 5, 20, 231, 573, 2134),
        mutableListOf(-5, 983, 0, -8213, -87, 52438, 12215246),
        mutableListOf(0, 121236, -2354353, -8, 1237, 4573, 234, -3, 2),
        mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        mutableListOf(-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1)
    )

    @ParameterizedTest
    @MethodSource("intListProvider")
    fun add(testList: List<Int>) = with(danilSinglyLinkedList) {
        val list = testList.onEach { add(it) }

        forEachIndexed { index, item -> assertThat(item).isEqualTo(list[index]) }
    }

    @ParameterizedTest
    @MethodSource("intListProvider")
    fun addByIndex(testList: List<Int>) = with(danilSinglyLinkedList) {
        val list = testList.let { list ->
            list.forEach {
                add(it, 0)
            }
            list.reversed()
        }

        forEachIndexed { index, item -> assertThat(item).isEqualTo(list[index]) }
    }

    @ParameterizedTest
    @MethodSource("intListProvider")
    fun clear(testList: List<Int>) = with(danilSinglyLinkedList) {
        testList.forEach { add(it) }
        clear()

        assertThat(danilSinglyLinkedList.isEmpty()).isTrue()
    }

    @ParameterizedTest
    @MethodSource("intListProvider")
    fun getFirst(testList: List<Int>) = with(danilSinglyLinkedList) {
        assertThat(first).isNull()

        add(23)
        add(-3, 1)

        assertThrows<IndexOutOfBoundsException> { add(4, 12) }

        assertThat(first).isEqualTo(23)
    }

    @ParameterizedTest
    @MethodSource("intListProvider")
    fun remove(testList: MutableList<Int>) = with(danilSinglyLinkedList) {
        val list = testList.onEach { add(it) }
        list.random().let {
            remove(it)
            list.remove(it)
        }

        assertThat(toList()).isEqualTo(list)

        var item: Int
        do {
            var exists = true
            item = nextInt()
            if (!list.contains(item)) exists = false
        } while (exists)
        remove(item)

        assertThat(toList()).isEqualTo(list)
    }

    @ParameterizedTest
    @MethodSource("intListProvider")
    fun removeAt(testList: MutableList<Int>) = with(danilSinglyLinkedList) {
        val list = testList.onEach { add(it) }
        nextInt(size).let {
            removeAt(it)
            list.removeAt(it)
        }

        assertThat(toList()).isEqualTo(list)
    }

    @ParameterizedTest
    @MethodSource("intListProvider")
    fun toList(testList: List<Int>) = with(danilSinglyLinkedList) {
        val list = testList.onEach { add(it) }

        assertThat(toList()).isEqualTo(list)
    }

    @Test
    fun isEmpty() = with(danilSinglyLinkedList) {
        assertThat(isEmpty()).isTrue()

        add(2)

        assertThat(isEmpty()).isFalse()

        remove(2)

        assertThat(isEmpty()).isTrue()
    }

    @ParameterizedTest
    @MethodSource("intListProvider")
    fun getSize(testList: List<Int>) = with(danilSinglyLinkedList) {
        val list = testList.onEach { add(it) }

        assertThat(size).isEqualTo(list.size)
    }

    @ParameterizedTest
    @MethodSource("intListProvider")
    fun removeFirst(testList: List<Int>) = with(danilSinglyLinkedList) {
        val list = testList.onEach { add(it) }
        removeFirst()

        assertThat(toList()).isEqualTo(list.subList(1, list.size))
    }

    @ParameterizedTest
    @MethodSource("intListProvider")
    fun removeLast(testList: List<Int>) = with(danilSinglyLinkedList) {
        val list = testList.onEach { add(it) }
        removeLast()

        assertThat(toList()).isEqualTo(list.subList(0, list.lastIndex))
    }

    @ParameterizedTest
    @MethodSource("intListProvider")
    fun get(testList: List<Int>) = with(danilSinglyLinkedList) {
        testList.onEach { add(it) }.forEachIndexed { index, item ->
            assertThat(get(index)).isEqualTo(item)
        }
    }

    @ParameterizedTest
    @MethodSource("intListProvider")
    fun contains(testList: List<Int>) = with(danilSinglyLinkedList) {
        val randomItem = testList.onEach { add(it) }.random()

        assertThat(contains(randomItem)).isTrue()
    }

    @ParameterizedTest
    @MethodSource("intListProvider")
    fun addLast(testList: List<Int>) = with(danilSinglyLinkedList) {
        testList.forEach { add(it) }
        val item = -1253
        addLast(item)

        assertThat(get(size - 1)).isEqualTo(item)
    }

    @ParameterizedTest
    @MethodSource("intListProvider")
    fun addFirst(testList: List<Int>) = with(danilSinglyLinkedList) {
        testList.forEach { add(it) }
        val item = -1253
        addFirst(item)

        assertThat(first).isEqualTo(item)
    }
}
