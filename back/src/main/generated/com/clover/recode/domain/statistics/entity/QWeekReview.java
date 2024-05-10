package com.clover.recode.domain.statistics.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWeekReview is a Querydsl query type for WeekReview
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWeekReview extends EntityPathBase<WeekReview> {

    private static final long serialVersionUID = 1442267820L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWeekReview weekReview = new QWeekReview("weekReview");

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    public final DatePath<java.time.LocalDate> date = createDate("date", java.time.LocalDate.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QStatistics statistics;

    public QWeekReview(String variable) {
        this(WeekReview.class, forVariable(variable), INITS);
    }

    public QWeekReview(Path<? extends WeekReview> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWeekReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWeekReview(PathMetadata metadata, PathInits inits) {
        this(WeekReview.class, metadata, inits);
    }

    public QWeekReview(Class<? extends WeekReview> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.statistics = inits.isInitialized("statistics") ? new QStatistics(forProperty("statistics"), inits.get("statistics")) : null;
    }

}

