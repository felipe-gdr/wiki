package data;

import com.atlassian.ari.principled.ARI;
import com.atlassian.ari.principled.ARILike;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

public class Test {
    private ObjectMapper objectMapper = new ObjectMapper();

    @org.junit.Test
    public void test() throws JsonProcessingException {
        Config config = new Config();

        config.setName("name-value");


        config.setAris(Arrays.asList("1", "2"));


        final String string = objectMapper.writeValueAsString(config);

        Assert.assertEquals(string, "{\"name\":\"name-value\",\"routed-aris\":[\"1\",\"2\"]}");
    }

    @org.junit.Test
    public void test2() throws JsonProcessingException {
        String json = "{\"name\":\"name-value\",\"routed-aris\":[\"1\",\"2\"]}";

        final Config config = objectMapper.readValue(json, Config.class);

        Assert.assertEquals(config.getRoutedARIs().aris.size(), 2);

    }

    @org.junit.Test
    public void test3() throws JsonProcessingException {
        Config config = new Config();

        config.setName("name-value");

        config.setAris(new ArrayList<>());

        final String string = objectMapper.writeValueAsString(config);

        Assert.assertEquals("{\"name\":\"name-value\"}", string);

    }

    @org.junit.Test
    public void test4() throws JsonProcessingException {
        Config config = new Config();

        config.setAris(Collections.emptyList());

        final String string = objectMapper.writeValueAsString(config);

        Assert.assertEquals("{}", string);

    }

    @org.junit.Test
    public void ari()  {
//        final Optional<ARILike> ariLike = ARILike.tryParse("ari:cloud:jira:123:comment/321");
        final Optional<ARILike> ariLike = ARILike.tryParse("ari:cloud:jira:123:component/activation/321/456");


        ariLike.ifPresent(value -> {
            System.out.println(value.getResourceOwner());
            System.out.println(value.getResourceType());
        });

    }

    @org.junit.Test
    public void regex()  {
        String regex = "^[a-z]+:[a-z]+$";

        Assert.assertTrue("asdf:asdf".matches(regex));
        Assert.assertTrue("a:a".matches(regex));
        Assert.assertFalse("123:123".matches(regex));
        Assert.assertFalse("1asdf:asdf".matches(regex));
        Assert.assertFalse("asdf:asdf1".matches(regex));

    }
}
