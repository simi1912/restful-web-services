package com.simi.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public MappingJacksonValue retrieveSomeBean(){
        SomeBean someBean = new SomeBean("value1", "value2", "value3");

        PropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("field1", "field2");

        FilterProvider filterProvider = new
                SimpleFilterProvider().addFilter("BeanFilter",
                filter);

        MappingJacksonValue mapping = new MappingJacksonValue(someBean);
        mapping.setFilters(filterProvider);

        return mapping;
    }

    @GetMapping("/filtering-list")
    public MappingJacksonValue retrieveListOfSomeBean(){
        List<SomeBean> someBeansList = Arrays.asList(
                new SomeBean("value1", "value2", "value3"),
                new SomeBean("value1", "value2", "value3"));

        PropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("field1", "field3");

        FilterProvider filterProvider =new
                SimpleFilterProvider().addFilter("BeanFilter", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(someBeansList);
        mapping.setFilters(filterProvider);

        return mapping;
    }

}