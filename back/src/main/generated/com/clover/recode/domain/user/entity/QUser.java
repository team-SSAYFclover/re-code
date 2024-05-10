package com.clover.recode.domain.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -1577578317L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final StringPath avatarUrl = createString("avatarUrl");

    public final DateTimePath<java.time.LocalDateTime> createdTime = createDateTime("createdTime", java.time.LocalDateTime.class);

    public final ListPath<com.clover.recode.domain.fcmtoken.entity.FcmToken, com.clover.recode.domain.fcmtoken.entity.QFcmToken> fcmToken = this.<com.clover.recode.domain.fcmtoken.entity.FcmToken, com.clover.recode.domain.fcmtoken.entity.QFcmToken>createList("fcmToken", com.clover.recode.domain.fcmtoken.entity.FcmToken.class, com.clover.recode.domain.fcmtoken.entity.QFcmToken.class, PathInits.DIRECT2);

    public final NumberPath<Long> githubId = createNumber("githubId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final QSetting setting;

    public final com.clover.recode.domain.statistics.entity.QStatistics statistics;

    public final DateTimePath<java.time.LocalDateTime> updatedTime = createDateTime("updatedTime", java.time.LocalDateTime.class);

    public final StringPath uuid = createString("uuid");

    public QUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    public QUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUser(PathMetadata metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.setting = inits.isInitialized("setting") ? new QSetting(forProperty("setting"), inits.get("setting")) : null;
        this.statistics = inits.isInitialized("statistics") ? new com.clover.recode.domain.statistics.entity.QStatistics(forProperty("statistics"), inits.get("statistics")) : null;
    }

}

