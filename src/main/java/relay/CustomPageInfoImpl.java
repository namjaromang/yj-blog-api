package relay;

import graphql.PublicApi;
import graphql.relay.ConnectionCursor;

@PublicApi
public class CustomPageInfoImpl implements CustomPageInfo {

    private final ConnectionCursor startCursor;
    private final ConnectionCursor endCursor;
    private final boolean hasPreviousPage;
    private final boolean hasNextPage;
    private final long totalPagesCount;
    private final long totalElementsCount;

    public CustomPageInfoImpl(
            ConnectionCursor startCursor,
            ConnectionCursor endCursor,
            boolean hasPreviousPage,
            boolean hasNextPage,
            long totalPagesCount,
            long totalElementsCount) {
        this.startCursor = startCursor;
        this.endCursor = endCursor;
        this.hasPreviousPage = hasPreviousPage;
        this.hasNextPage = hasNextPage;
        this.totalPagesCount = totalPagesCount;
        this.totalElementsCount = totalElementsCount;
    }

    @Override
    public ConnectionCursor getStartCursor() {
        return startCursor;
    }

    @Override
    public ConnectionCursor getEndCursor() {
        return endCursor;
    }

    @Override
    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    @Override
    public boolean isHasNextPage() {
        return hasNextPage;
    }

    @Override
    public long getTotalPagesCount() {
        return totalPagesCount;
    }

    @Override
    public long getTotalElementsCount() {
        return totalElementsCount;
    }

    @Override
    public String toString() {
        return "DefaultPageInfo{"
                + " startCursor="
                + startCursor
                + ", endCursor="
                + endCursor
                + ", hasPreviousPage="
                + hasPreviousPage
                + ", hasNextPage="
                + hasNextPage
                + ", totalPagesCount="
                + totalPagesCount
                + ", totalElementsCount="
                + totalElementsCount
                + '}';
    }
}
