package relay;

import graphql.PublicApi;
import graphql.relay.Edge;
import graphql.relay.PageInfo;
import java.util.List;

/**
 * This represents a connection in Relay, which is a list of {@link Edge edge}s as well as a {@link
 * PageInfo pageInfo} that describes the pagination of that list.
 *
 * <p>See <a
 * href="https://facebook.github.io/relay/graphql/connections.htm">https://facebook.github.io/relay/graphql/connections.htm</a>
 */
@PublicApi
public interface CustomConnection<T> {

    /** @return a list of {@link Edge}s that are really a node of data and its cursor */
    List<Edge<T>> getEdges();

    /** @return {@link PageInfo} pagination data about about that list of edges */
    CustomPageInfo getPageInfo();
}
