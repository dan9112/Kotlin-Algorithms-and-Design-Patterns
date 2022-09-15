package sorting

import TestUtils.array
import TestUtils.list
import TestUtils.mutableRandomList
import TestUtils.mutableSortedList
import TestUtils.randomArray
import TestUtils.sortedArray
import com.google.common.truth.Truth.assertThat
import org.junit.Test

internal class CocktailSortTest {

    @Test
    fun test_reversed_array() {
        val expected = array(1000)

        val actual = expected.reversed().toTypedArray()
        actual.cocktailSort()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun test_reversed_list() {
        val expected = list(2000)

        val actual = expected.reversed().toMutableList()
        actual.cocktailSort()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun test_random_array() {
        val actual = randomArray(500)

        val expected = actual.sorted().toTypedArray()

        actual.cocktailSort()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun test_random_list() {
        val actual = mutableRandomList(5000)

        val expected = actual.sorted()

        actual.cocktailSort()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun test_shuffled_array() {
        val expected = sortedArray(1000)

        val actual = expected.copyOf().apply {
            shuffle()
            cocktailSort()
        }

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun test_shuffled_list() {
        val expected = mutableSortedList(700)

        val actual = expected.toMutableList().apply {
            shuffle()
            cocktailSort()
        }

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun test_sorted_array() {
        val actual = sortedArray(300)

        val expected = actual.copyOf().apply { cocktailSort() }

        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun test_sorted_list() {
        val actual = mutableSortedList(1400)

        val expected = actual.toMutableList().apply { cocktailSort() }

        assertThat(expected).isEqualTo(actual)
    }
}
