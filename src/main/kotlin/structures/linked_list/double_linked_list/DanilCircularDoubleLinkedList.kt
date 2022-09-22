package structures.linked_list.double_linked_list

import structures.linked_list.singly_linked_list.circular.CircularLinkedList

open class DanilCircularDoubleLinkedList<T> : CircularLinkedList<T> {
    protected open val data = CurrentNodeSingleLink<T>()
    protected var mutableSize = 0
    final override val size
        get() = mutableSize

    private val median
        get() = size / 2

    override fun addNext(item: T, index: Int) {
        if (isEmpty()) {
            data.link = DanilNode(item)
        } else {
            var current = node(index)
            current.next = DanilNode(item, current.next, current)
            current = current.next
            current.previous = current.previous.next
        }
        mutableSize++
    }

    override fun add(item: T) {
        if (isEmpty()) {
            data.link = DanilNode(item)
        } else with(data) {
            val current = link!!.previous
            current.next = DanilNode(item, link!!, current)
            link!!.previous = current.next
        }
        mutableSize++
    }

    override fun addPrevious(item: T, index: Int) {
        if (isEmpty()) {
            data.link = DanilNode(item)
        } else {
            var current = node(index)
            current.previous = DanilNode(item, current, current.previous)
            current = current.previous
            current.next = current.next.previous
        }
        mutableSize++
    }

    override fun removeAt(index: Int) = if (isEmpty()) {
        throw IllegalArgumentException()
    } else {
        mutableSize--

        if (size == 1) {
            data.link = null
        } else {
            val localIndex = index.toRealIndex
            if (localIndex == 0) data.link = data.link!!.next

            var current: NodeDouble<T>
            if (localIndex <= median) {
                current = node(localIndex - 1)
                current.next = current.next.next
                current = current.next
                current.previous = current.previous.previous
            } else {
                current = node(size - localIndex - 1)
                current.previous = current.previous.previous
                current = current.previous
                current.next = current.next.next
            }
        }
    }

    override fun remove(item: T) = if (isEmpty()) {
        false
    } else {
        var current = data.link!!
        var i = size
        var deleted = false
        while (i > 0 && !deleted) {
            i--
            if (current.value == item) {
                if (size == 1) data.link = null
                else current.value = current.next.value.apply {
                    if (current.next == data.link) data.link = current.next.next
                    current.next = current.next.next
                    current = current.next
                    current.previous = current.previous.previous
                    deleted = true
                }
                mutableSize--
            } else current = current.next
        }
        deleted
    }

    final override fun isEmpty() = size == 0

    final override val current
        get() = data.link?.value ?: throw IllegalArgumentException()

    override fun toList() = with(receiver = mutableListOf<T>()) {
        data.link?.let {
            var current = it
            do {
                add(current.value)
                current = current.next
            } while (current != it)
        }
        toList()
    }

    fun toReverseList() = with(receiver = mutableListOf<T>()) {
        data.link?.let {
            var current = it
            do {
                current = current.previous
                add(current.value)
            } while (current != it)
        }
        toList()
    }

    override fun goForward(steps: Int) = if (size == 0) throw IllegalArgumentException()
    else {
        var i = steps % size
        while (i > 0) {
            i--
            data.link = data.link!!.next
        }
    }

    override fun goBack(steps: Int) = if (size == 0) throw IllegalArgumentException()
    else {
        var i = steps % size
        while (i > 0) {
            i--
            data.link = data.link!!.previous
        }
    }

    override fun clear() {
        if (!isEmpty()) with(receiver = data) {
            if (size == 1) link = null
            else {
                // Разомкнуть кольцо ссылок, чтобы сборщик мусора мог освободить память
                var current = data.link!!
                current.previous = current
                current = current.next
                current.previous = current
                data.link!!.next = data.link!!
                link = null
            }
            mutableSize = 0
        }
    }

    private fun iterator(index: Int, action: () -> Unit) {
        var localIndex = index
        while (localIndex > 0) {
            localIndex--
            action.invoke()
        }
    }

    private val Int.toRealIndex
        get() = run {
            var current = this
            if (current < 0) while (current < 0) {
                current += size
            } else if (current >= size) while (current >= size) {
                current -= size
            }
            current
        }

    override fun get(index: Int) = if (isEmpty()) {
        throw IllegalArgumentException()
    } else node(index).value

    private fun node(index: Int) = run{
        var current = data.link!!
        val localIndex = index.toRealIndex
        if (localIndex <= median) iterator(localIndex) { current = current.next }
        else iterator(size - localIndex) { current = current.previous }
        current
    }

    protected interface NodeDouble<T> {
        var value: T
        var next: NodeDouble<T>
        var previous: NodeDouble<T>
    }

    protected class DanilNode<T>(override var value: T) : NodeDouble<T> {
        constructor(value: T, next: NodeDouble<T>, previous: NodeDouble<T>) : this(value) {
            this.next = next
            this.previous = previous
        }

        override var previous: NodeDouble<T> = this
        override var next: NodeDouble<T> = this
    }

    protected open class CurrentNodeSingleLink<T>(var link: NodeDouble<T>? = null)
}
