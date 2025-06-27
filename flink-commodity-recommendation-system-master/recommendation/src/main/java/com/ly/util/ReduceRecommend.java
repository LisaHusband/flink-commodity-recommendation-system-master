package com.ly.util;

import com.ly.entity.RecommendEntity;
import com.ly.entity.RecommendReduceEntity;
import org.apache.flink.api.common.functions.ReduceFunction;

import java.util.Collections;
import java.util.Comparator;

public class ReduceRecommend implements ReduceFunction<RecommendReduceEntity> {
    @Override
    public RecommendReduceEntity reduce(RecommendReduceEntity r1, RecommendReduceEntity r2) throws Exception {
        r1.getList().addAll(r2.getList());
        Collections.sort(r1.getList(), new Comparator<RecommendEntity>() {
            @Override
            public int compare(RecommendEntity o1, RecommendEntity o2) {
                return o2.getSim().compareTo(o1.getSim());
            }
        });
        return new RecommendReduceEntity(r1.getProductId(), r1.getList());
    }
}
