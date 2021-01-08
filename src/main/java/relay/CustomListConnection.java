package relay;

import static graphql.Assert.assertNotNull;
import static graphql.Assert.assertTrue;

import com.blog.api.exception.BusinessException;
import com.blog.api.exception.ErrorCode;
import graphql.PublicApi;
import graphql.relay.DefaultConnectionCursor;
import graphql.relay.DefaultEdge;
import graphql.relay.Edge;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.data.domain.Page;

@PublicApi
public class CustomListConnection<T> implements DataFetcher<CustomConnection<T>> {

    static final String DUMMY_CURSOR_PREFIX = "rs-cursor-";
    private final String prefix;
    private final Page<T> data;

    public CustomListConnection(Page<T> data, String prefix) {
        this.data = assertNotNull(data, " data cannot be null");
        assertTrue(prefix != null && !prefix.isEmpty(), "prefix cannot be null or empty");
        this.prefix = prefix;
    }

    public CustomListConnection(Page<T> data) {
        this(data, DUMMY_CURSOR_PREFIX);
    }

    private List<Edge<T>> buildEdges() {
        List<Edge<T>> edges = new ArrayList<>();
        int ix = data.getNumber() * data.getSize(); // 현재 페이지 수(0부터 시작) * 페이지 컨텐츠 수 로 시작
        for (T object : data) {
            edges.add(new DefaultEdge<>(object, new DefaultConnectionCursor(String.valueOf(ix++))));
        }
        return edges;
    }

    @Override
    public CustomConnection<T> get(DataFetchingEnvironment environment) {

        List<Edge<T>> edges = buildEdges();

        if (edges.size() == 0) {
            return emptyConnection();
        }

        Integer first = environment.getArgument("first");
        if (first != null) {
            if (first < 0) {
                throw new BusinessException(ErrorCode.DUPLICATE_DEVICE_TOKEN_ERROR);
            }
        }

        Edge<T> firstEdge = edges.get(0);
        Edge<T> lastEdge = edges.get(edges.size() - 1);

        CustomPageInfo pageInfo =
                new CustomPageInfoImpl(
                        firstEdge.getCursor(),
                        lastEdge.getCursor(),
                        data.hasPrevious(),
                        data.hasNext(),
                        data.getTotalPages(),
                        data.getTotalElements());

        return new CustomConnectionImpl<>(edges, pageInfo);
    }

    private CustomConnection<T> emptyConnection() {
        CustomPageInfo pageInfo =
                new CustomPageInfoImpl(
                        new DefaultConnectionCursor("0"),
                        new DefaultConnectionCursor(String.valueOf(data.getTotalElements())),
                        data.hasPrevious(),
                        data.hasNext(),
                        data.getTotalPages(),
                        data.getTotalElements());
        return new CustomConnectionImpl<>(Collections.emptyList(), pageInfo);
    }
}
