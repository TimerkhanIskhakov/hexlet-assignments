//package exercise.model;
//
//import com.querydsl.core.types.Path;
//import com.querydsl.core.types.PathMetadata;
//import com.querydsl.core.types.PathMetadataFactory;
//import com.querydsl.core.types.dsl.EntityPathBase;
//import com.querydsl.core.types.dsl.NumberPath;
//import com.querydsl.core.types.dsl.StringPath;
//
//public class QUser extends EntityPathBase<User> {
//    private static final long serialVersionUID = 1546138982L;
//    public static final QUser user = new QUser("user");
//    public final StringPath email = this.createString("email");
//    public final StringPath firstName = this.createString("firstName");
//    public final StringPath gender = this.createString("gender");
//    public final NumberPath<Long> id = this.createNumber("id", Long.class);
//    public final StringPath lastName = this.createString("lastName");
//    public final StringPath profession = this.createString("profession");
//
//    public QUser(String variable) {
//        super(User.class, PathMetadataFactory.forVariable(variable));
//    }
//
//    public QUser(Path<? extends User> path) {
//        super(path.getType(), path.getMetadata());
//    }
//
//    public QUser(PathMetadata metadata) {
//        super(User.class, metadata);
//    }
//}