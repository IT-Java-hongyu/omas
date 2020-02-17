package com.fosustu.omas.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface VisitTimeMapper {
    List<Date> SearchVisitList(@Param("Did")String Did,@Param("nowDate")Date nowDate);
}