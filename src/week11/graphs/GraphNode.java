package week11.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface GraphNode<T> {
    public Set<GraphNode<T>> neighbors();

    public T getValue();
}