package structures.linked_list.singly_linked_list.ordinary

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class DanilSingleLinkedListTest {
    private val dsll = DanilSingleLinkedList<Int>()

    @BeforeEach
    fun setUp() {
        dsll.clear()
    }

    private val testList
        get() = listOf(1, 4, 6, 3, 12, 78)

    @Test
    fun test_add() = with(receiver = dsll) {
        val list = testList.onEach { add(it) }

        forEachIndexed { index, item -> assertThat(item).isEqualTo(list[index]) }
    }

    @Test
    fun test_add_with_index() = with(receiver = dsll) {
        val list = testList.run {
            forEach { add(it, 0) }
            reversed()
        }

        forEachIndexed { index, item -> assertThat(item).isEqualTo(list[index]) }
        // remove(10)
        //
        // assertThat(size).isEqualTo(6)
        //
        // removeFirst()
        //
        // assertThat(size).isEqualTo(5)
    }

    @Test
    fun test_clear() = with(receiver = dsll) {
        testList.forEach { add(it) }
        clear()

        assertThat(dsll.isEmpty()).isTrue()
    }

    @Test
    fun test_getFirst() = with(receiver = dsll) {
        assertThat(first).isNull()

        add(23)
        add(-3, 1)

        assertThrows<IndexOutOfBoundsException> { add(4, 12) }

        assertThat(first).isEqualTo(23)
    }

    @Test
    fun test_remove_by_value() = with(receiver = dsll) {
        val list = testList.onEach { add(it) }
        remove(item = 12)

        assertThat(size).isEqualTo(list.size - 1)

        remove(item = -1423)

        assertThat(size).isEqualTo(list.size - 1)
    }

    @Test
    fun test_remove_by_index() = with(receiver = dsll) {
        val list = testList.onEach { add(it) }
        val index = 2
        remove(index = index)

        assertThat(size).isEqualTo(list.size - 1)
        assertThat(get(index - 1)).isEqualTo(list[index - 1])
        assertThat(get(index)).isEqualTo(list[index + 1])
    }

    @Test
    fun test_toList() = with(receiver = dsll) {
        val list = testList.onEach { add(it) }

        assertThat(toList()).isEqualTo(list)
    }

    @Test
    fun test_isEmpty() = with(dsll) {
        assertThat(isEmpty()).isTrue()

        add(2)

        assertThat(isEmpty()).isFalse()

        remove(item = 2)

        assertThat(isEmpty()).isTrue()
    }

    @Test
    fun test_getSize() = with(receiver = dsll) {
        val testList = testList.onEach { add(it) }

        assertThat(size).isEqualTo(testList.size)
    }

    @Test
    fun test_removeFirst() = with(receiver = dsll) {
        val testList = testList.onEach { add(it) }
        removeFirst()

        assertThat(toList()).isEqualTo(testList.subList(1, testList.size))
    }

    @Test
    fun test_removeLast() = with(receiver = dsll) {
        val testList = testList.onEach { add(it) }
        removeLast()

        assertThat(toList()).isEqualTo(testList.subList(0, testList.lastIndex))
    }

    @Test
    fun test_get() = with(receiver = dsll) {
        testList.onEach { add(it) }.forEachIndexed { index, item ->
            assertThat(get(index)).isEqualTo(item)
        }
    }

    @Test
    fun test_contains() = with(dsll) {
        val randomItem = testList.onEach { add(it) }.random()

        assertThat(contains(randomItem)).isTrue()
    }

    @Test
    fun test_addLast() = with(receiver = dsll) {
        testList.forEach { add(it) }
        val item = -1253
        addLast(item)

        assertThat(get(size - 1)).isEqualTo(item)
    }

    @Test
    fun test_addFirst() = with(receiver = dsll) {
        testList.forEach { add(it) }
        val item = -1253
        addFirst(item)

        assertThat(first).isEqualTo(item)
    }
}
