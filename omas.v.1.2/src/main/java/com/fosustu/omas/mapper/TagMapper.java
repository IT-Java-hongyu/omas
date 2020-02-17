package com.fosustu.omas.mapper;

import com.fosustu.omas.pojo.Tag;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TagMapper {
    @Select("select * from tag")
    public List<Tag> getAllTags();
}
