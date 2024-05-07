package com.clover.recode.domain.recode.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecode is a Querydsl query type for Recode
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecode extends EntityPathBase<Recode> {

    private static final long serialVersionUID = 685149085L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecode recode = new QRecode("recode");

    public final com.clover.recode.domain.problem.entity.QCode code;

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> reviewTime = createDateTime("reviewTime", java.time.LocalDateTime.class);

    public final NumberPath<Integer> submitCount = createNumber("submitCount", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> submitTime = createDateTime("submitTime", java.time.LocalDateTime.class);

    public QRecode(String variable) {
        this(Recode.class, forVariable(variable), INITS);
    }

    public QRecode(Path<? extends Recode> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecode(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecode(PathMetadata metadata, PathInits inits) {
        this(Recode.class, metadata, inits);
    }

    public QRecode(Class<? extends Recode> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.code = inits.isInitialized("code") ? new com.clover.recode.domain.problem.entity.QCode(forProperty("code"), inits.get("code")) : null;
    }

}

