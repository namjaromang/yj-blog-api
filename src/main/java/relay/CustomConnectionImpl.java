package relay;

import static graphql.Assert.assertNotNull;
import static java.util.Collections.unmodifiableList;

import graphql.PublicApi;
import graphql.relay.Connection;
import graphql.relay.Edge;
import java.util.Collections;
import java.util.List;

/** A default implementation of {@link Connection} */
@PublicApi
public class CustomConnectionImpl<T> implements CustomConnection<T> {

    private final List<Edge<T>> edges;
    private final CustomPageInfo pageInfo;

    /**
     * A connection consists of a list of edges and page info
     *
     * @param edges a non null list of edges
     * @param pageInfo a non null page info
     * @throws IllegalArgumentException if edges or page info is null. use {@link
     *     Collections#emptyList()} for empty edges.
     */
    public CustomConnectionImpl(List<Edge<T>> edges, CustomPageInfo pageInfo) {
        this.edges = unmodifiableList(assertNotNull(edges, "edges cannot be null"));
        this.pageInfo = assertNotNull(pageInfo, "page info cannot be null");
    }

    @Override
    public List<Edge<T>> getEdges() {
        return edges;
    }

    @Override
    public CustomPageInfo getPageInfo() {
        return pageInfo;
    }

    @Override
    public String toString() {
        return "RSConnection{" + "edges=" + edges + ", pageInfo=" + pageInfo + '}';
    }
}
