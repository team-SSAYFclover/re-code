package com.clover.recode.domain.statistics.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTodayProblem is a Querydsl query type for TodayProblem
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTodayProblem extends EntityPathBase<TodayProblem> {

    private static final long serialVersionUID = 637548126L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTodayProblem todayProblem = new QTodayProblem("todayProblem");

    public final com.clover.recode.domain.problem.entity.QCode code;

    public final DatePath<java.time.LocalDate> date = createDate("date", java.time.LocalDate.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isCompleted = createBoolean("isCompleted");

    public final StringPath name = createString("name");

    public final NumberPath<Integer> reviewCnt = createNumber("reviewCnt", Integer.class);

    public QTodayProblem(String variable) {
        this(TodayProblem.class, forVariable(variable), INITS);
    }

    public QTodayProblem(Path<? extends TodayProblem> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTodayProblem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTodayProblem(PathMetadata metadata, PathInits inits) {
        this(TodayProblem.class, metadata, inits);
    }

    public QTodayProblem(Class<? extends TodayProblem> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.code = inits.isInitialized("code") ? new com.clover.recode.domain.problem.entity.QCode(forProperty("code"), inits.get("code")) : null;
    }

}

