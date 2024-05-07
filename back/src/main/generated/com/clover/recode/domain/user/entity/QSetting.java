package com.clover.recode.domain.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSetting is a Querydsl query type for Setting
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSetting extends EntityPathBase<Setting> {

    private static final long serialVersionUID = 29679720L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSetting setting = new QSetting("setting");

    public final NumberPath<Integer> difficulty = createNumber("difficulty", Integer.class);

    public final BooleanPath notificationStatus = createBoolean("notificationStatus");

    public final TimePath<java.sql.Time> notificationTime = createTime("notificationTime", java.sql.Time.class);

    public final QUser user;

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QSetting(String variable) {
        this(Setting.class, forVariable(variable), INITS);
    }

    public QSetting(Path<? extends Setting> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSetting(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSetting(PathMetadata metadata, PathInits inits) {
        this(Setting.class, metadata, inits);
    }

    public QSetting(Class<? extends Setting> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

