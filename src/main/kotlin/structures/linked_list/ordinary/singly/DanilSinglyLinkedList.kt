package structures.linked_list.ordinary.singly

open class DanilSinglyLinkedList<T> : SinglyLinkedList<T> {
    protected var data: Node<T>? = null
    protected var mutableSize = 0

    override fun add(item: T, index: Int) {
        if (index > size) {
            throw IndexOutOfBoundsException()
        } else {
            if (isEmpty() || index == 0) {
                data = Node(item, data)
            } else {
                var current = data
                var i = 0
                while (i < index - 1) {
                    i++
                    current = current!!.next
                }
                current!!.next = Node(item)
            }
            mutableSize++
        }
    }

    override fun add(item: T) {
        if (isEmpty()) {
            data = Node(item)
        } else {
            var current = data
            var i = 0
            while (i < size - 1) {
                i++
                current = current!!.next
            }
            current!!.next = Node(item)
        }
        mutableSize++
    }

    override fun clear() {
        data = null
        mutableSize = 0
    }

    override val first
        get() = data?.value

    override fun toList() = mutableListOf<T>().apply { iterate { item -> add(item.value) } }.toList()

    override fun isEmpty() = data == null

    override val size
        get() = mutableSize

    override fun removeFirst() = if (isEmpty()) {
        false
    } else {
        data = if (size == 1) null
        else data!!.next
        mutableSize--
        true
    }

    override fun removeLast() = if (isEmpty()) {
        false
    } else {
        if (size == 1 || data!!.next == null) {
            data = null
        } else {
            var current = data
            while (current!!.next!!.next != null) {
                current = current.next
            }
            current.next = null
        }
        mutableSize--
        true
    }

    override operator fun get(index: Int) = if (index >= size) {
        throw IndexOutOfBoundsException()
    } else {
        var current = data
        var i = 0
        while (i < index) {
            i++
            current = current!!.next
        }
        current!!.value
    }

    override fun removeAt(index: Int) {
        if (index >= size) {
            throw IndexOutOfBoundsException()
        } else {
            if (index == 0) {
                data = data!!.next
            } else {
                var current = data
                var i = 1
                while (i < index) {
                    i++
                    current = current!!.next
                }
                current!!.next = current.next!!.next
            }
            mutableSize--
        }
    }

    override fun remove(item: T) = when {
        isEmpty() -> {
            throw IndexOutOfBoundsException()
        }

        data!!.value == item -> {
            data = data!!.next
            mutableSize--
            true
        }

        else -> {
            var current = data!!
            while (current.next != null && current.next!!.value != item) {
                current = current.next!!
            }
            if (current.next == null) false
            else {
                current.next = current.next!!.next
                mutableSize--
                true
            }
        }
    }

    override fun contains(item: T) = if (isEmpty()) {
        false
    } else {
        var current = data
        var shouldLoop: Boolean? = null
        while (shouldLoop == null) {
            when {
                current?.next == null -> shouldLoop = false
                current.value == item -> shouldLoop = true
                else -> current = current.next
            }
        }
        shouldLoop
    }

    override fun addLast(item: T) {
        if (data == null) {
            data = Node(item)
        } else {
            var current = data!!
            while (current.next != null) {
                current = current.next!!
            }
            current.next = Node(item)
        }
        mutableSize++
    }

    override fun addFirst(item: T) {
        data = Node(item, data)
        mutableSize++
    }

    protected fun iterate(action: (item: Node<T>) -> Unit) {
        var current = data
        while (current != null) {
            action(current)
            current = current.next
        }
    }

    protected fun iterate(action: (index: Int, item: Node<T>) -> Unit) {
        var current = data
        var index = 0
        while (current != null) {
            action(index, current)
            current = current.next
            index++
        }
    }

    override fun forEach(action: (item: T) -> Unit) = iterate { item -> action(item.value!!) }
    override fun forEachIndexed(action: (index: Int, item: T) -> Unit) =
        iterate { index, item -> action(index, item.value) }

    open class Node<T>(var value: T, var next: Node<T>? = null)
}
