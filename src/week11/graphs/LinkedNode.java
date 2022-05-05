package week11.graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LinkedNode<T> implements GraphNode<T> {
    public T value;
    public List<LinkedNode<T>> links;

    public LinkedNode(T value) {
        this.value = value;
        this.links = new ArrayList<>();
    }

    @Override
    public boolean equals(Object anything) {
        if (anything instanceof LinkedNode<?>) {
            LinkedNode<?> other = (LinkedNode<?>) anything;
            return this.value.equals(other.value);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }

    @Override
    public Set<GraphNode<T>> neighbors() {
        HashSet<GraphNode<T>> output = new HashSet<>();
        output.addAll(this.links);
        return output;
    }

    @Override
    public T getValue() {
        return this.value;
    }
}
