package com.clover.recode.domain.statistics.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAlgoReview is a Querydsl query type for AlgoReview
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAlgoReview extends EntityPathBase<AlgoReview> {

    private static final long serialVersionUID = 149019083L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAlgoReview algoReview = new QAlgoReview("algoReview");

    public final NumberPath<Integer> data_structuresCnt = createNumber("data_structuresCnt", Integer.class);

    public final NumberPath<Integer> dpCnt = createNumber("dpCnt", Integer.class);

    public final NumberPath<Integer> geometryCnt = createNumber("geometryCnt", Integer.class);

    public final NumberPath<Integer> graphsCnt = createNumber("graphsCnt", Integer.class);

    public final NumberPath<Integer> greedyCnt = createNumber("greedyCnt", Integer.class);

    public final NumberPath<Integer> implementationCnt = createNumber("implementationCnt", Integer.class);

    public final NumberPath<Integer> mathCnt = createNumber("mathCnt", Integer.class);

    public final QStatistics statistics;

    public final NumberPath<Long> statisticsId = createNumber("statisticsId", Long.class);

    public final NumberPath<Integer> stringCnt = createNumber("stringCnt", Integer.class);

    public QAlgoReview(String variable) {
        this(AlgoReview.class, forVariable(variable), INITS);
    }

    public QAlgoReview(Path<? extends AlgoReview> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAlgoReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAlgoReview(PathMetadata metadata, PathInits inits) {
        this(AlgoReview.class, metadata, inits);
    }

    public QAlgoReview(Class<? extends AlgoReview> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.statistics = inits.isInitialized("statistics") ? new QStatistics(forProperty("statistics"), inits.get("statistics")) : null;
    }

}

