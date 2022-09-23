package structures.linked_list.ordinary.singly

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.random.Random.Default.nextInt

internal class DanilSinglyLinkedListTest {
    private val danilSinglyLinkedList = DanilSinglyLinkedList<Int>()

    @BeforeEach
    fun setUp() {
        danilSinglyLinkedList.clear()
    }

    private val testList
        get() = IntArray(nextInt(100)) { nextInt() }.toMutableList()

    @Test
    fun add() = with(danilSinglyLinkedList) {
        val list = testList.onEach { add(it) }

        forEachIndexed { index, item -> assertThat(item).isEqualTo(list[index]) }
    }

    @Test
    fun addByIndex() = with(danilSinglyLinkedList) {
        val list = testList.let { list ->
            list.forEach {
                add(it, 0)
            }
            list.reversed()
        }

        forEachIndexed { index, item -> assertThat(item).isEqualTo(list[index]) }
    }

    @Test
    fun clear() = with(danilSinglyLinkedList) {
        testList.forEach { add(it) }
        clear()

        assertThat(danilSinglyLinkedList.isEmpty()).isTrue()
    }

    @Test
    fun getFirst() = with(danilSinglyLinkedList) {
        assertThat(first).isNull()

        add(23)
        add(-3, 1)

        assertThrows<IndexOutOfBoundsException> { add(4, 12) }

        assertThat(first).isEqualTo(23)
    }

    @Test
    fun remove() = with(danilSinglyLinkedList) {
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

    @Test
    fun removeAt() = with(danilSinglyLinkedList) {
        val list = testList.onEach { add(it) }
        nextInt(size).let {
            removeAt(it)
            list.removeAt(it)
        }

        assertThat(toList()).isEqualTo(list)
    }

    @Test
    fun toList() = with(danilSinglyLinkedList) {
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

    @Test
    fun getSize() = with(danilSinglyLinkedList) {
        val testList = testList.onEach { add(it) }

        assertThat(size).isEqualTo(testList.size)
    }

    @Test
    fun removeFirst() = with(danilSinglyLinkedList) {
        val testList = testList.onEach { add(it) }
        removeFirst()

        assertThat(toList()).isEqualTo(testList.subList(1, testList.size))
    }

    @Test
    fun removeLast() = with(danilSinglyLinkedList) {
        val testList = testList.onEach { add(it) }
        removeLast()

        assertThat(toList()).isEqualTo(testList.subList(0, testList.lastIndex))
    }

    @Test
    fun get() = with(danilSinglyLinkedList) {
        testList.onEach { add(it) }.forEachIndexed { index, item ->
            assertThat(get(index)).isEqualTo(item)
        }
    }

    @Test
    fun contains() = with(danilSinglyLinkedList) {
        val randomItem = testList.onEach { add(it) }.random()

        assertThat(contains(randomItem)).isTrue()
    }

    @Test
    fun addLast() = with(danilSinglyLinkedList) {
        testList.forEach { add(it) }
        val item = -1253
        addLast(item)

        assertThat(get(size - 1)).isEqualTo(item)
    }

    @Test
    fun addFirst() = with(danilSinglyLinkedList) {
        testList.forEach { add(it) }
        val item = -1253
        addFirst(item)

        assertThat(first).isEqualTo(item)
    }
}
