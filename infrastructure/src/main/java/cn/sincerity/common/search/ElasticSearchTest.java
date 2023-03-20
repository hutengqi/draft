package cn.sincerity.common.search;

import cn.sincerity.common.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.get.GetResult;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.metrics.MaxAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;

import javax.naming.directory.SearchResult;
import java.io.IOException;
import java.util.Arrays;

/**
 * ElasticSearchTest
 *
 * @author Ht7_Sincerity
 * @since 2023/2/26
 */
public class ElasticSearchTest {

    public static void main(String[] args) throws IOException {
        // 创建 ES 客户端
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));

        // 创建索引
//        CreateIndexRequest request = new CreateIndexRequest("draft");
//        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
//        boolean acknowledged = response.isAcknowledged();
//        System.out.println("索引状态: " + acknowledged);

        // 查询索引
//        GetIndexRequest request = new GetIndexRequest("draft");
//        GetIndexResponse response = client.indices().get(request, RequestOptions.DEFAULT);
//        System.out.println(response.getAliases());
//        System.out.println(response.getMappings());
//        System.out.println(response.getSettings());

        // 删除索引
//        DeleteIndexRequest request = new DeleteIndexRequest("draft");
//        AcknowledgedResponse response = client.indices().delete(request, RequestOptions.DEFAULT);
//        System.out.println("删除索引: " + response.isAcknowledged());

        // 添加数据
//        IndexRequest request = new IndexRequest();
//        request.index("user").id("102");
//        User user = User.newInstance(1);
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = objectMapper.writeValueAsString(user);
//        request.source(json, XContentType.JSON);
//        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
//        DocWriteResponse.Result result = response.getResult();
//        System.out.println(result);

        // 修改数据
//        UpdateRequest request = new UpdateRequest();
//        request.index("user").id("102");
//        request.doc(XContentType.JSON, "sex", 0);
//        UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
//        GetResult result = response.getGetResult();
//        System.out.println(request);

        // 查询数据
//        GetRequest request = new GetRequest();
//        request.index("user").id("102");
//        GetResponse response = client.get(request, RequestOptions.DEFAULT);
//        System.out.println(response.getSourceAsString());

        // 删除数据
//        DeleteRequest request = new DeleteRequest();
//        request.index("user").id("102");
//        DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
//        System.out.println(response.status());

        // 批量插入数据
//        BulkRequest bulkRequest = new BulkRequest();
//        bulkRequest.add(new IndexRequest().index("user").id("102").source(XContentType.JSON, "name", "张三"));
//        bulkRequest.add(new IndexRequest().index("user").id("103").source(XContentType.JSON, "name", "李四"));
//        bulkRequest.add(new IndexRequest().index("user").id("104").source(XContentType.JSON, "name", "王五"));
//        BulkResponse response = client.bulk(bulkRequest, RequestOptions.DEFAULT);
//        System.out.println(response.getTook());
//        System.out.println(Arrays.toString(response.getItems()));

        // 批量修改数据
//        BulkRequest bulkRequest = new BulkRequest();
//        bulkRequest.add(new DeleteRequest().index("user").id("102"));
//        bulkRequest.add(new DeleteRequest().index("user").id("103"));
//        bulkRequest.add(new DeleteRequest().index("user").id("104"));
//        BulkResponse response = client.bulk(bulkRequest, RequestOptions.DEFAULT);
//        System.out.println(response.getTook());
//        System.out.println(Arrays.toString(response.getItems()));

        // 查询数据
        SearchRequest request = new SearchRequest();
        request.indices("user");
        SearchSourceBuilder builder = new SearchSourceBuilder();
//        builder.from(0);
//        builder.size(2);
        // 排序
//        builder.sort("age", SortOrder.DESC);

        // 指定字段
//        String[] excludes = {"tel"};
//        String[] includes = {};
//        builder.fetchSource(includes, excludes);

        //条件查询
//        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
//        boolQuery.must(QueryBuilders.matchQuery("sex", "女"));
//        builder.query(boolQuery);
        // 范围查询
//        RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("age");
//        rangeQuery.gte(20);
//        rangeQuery.lte(24);
//        builder.query(rangeQuery);
        // 模糊查询
//        FuzzyQueryBuilder fuzziness = QueryBuilders.fuzzyQuery("name", "李").fuzziness(Fuzziness.ONE);
//        builder.query(fuzziness);

        // 聚合查询
        MaxAggregationBuilder aggregationBuilder = AggregationBuilders.max("maxAge").field("age");
        builder.aggregation(aggregationBuilder);
        request.source(builder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        System.out.println(response.toString());
        System.out.println(hits.getTotalHits());
        System.out.println(response.getTook());
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }


        // 关闭 ES 客户端
        client.close();
    }
}
