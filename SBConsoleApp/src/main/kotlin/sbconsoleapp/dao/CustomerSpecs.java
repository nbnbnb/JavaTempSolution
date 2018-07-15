package sbconsoleapp.dao;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;
import java.lang.reflect.Field;
import java.util.ArrayList;


/**
 * Created by ZhangJin on 2018/6/16.
 */
public class CustomerSpecs {


    private static <E, T> SingularAttribute<T, E> attribute(EntityType<T> entityType, String fieldName, Class<E> fieldClass) {
        return entityType.getDeclaredSingularAttribute(fieldName, fieldClass);
    }

    private static <T> Object getValue(T entity, Attribute<T, ?> attribute) {
        return ReflectionUtils.getField((Field) attribute.getJavaMember(), entity);
    }

    private static String pattern(String str) {
        return "%" + str + "%";
    }

    public static <T> Specification<T> byAuto(final EntityManager entityManager, final T example) {

        final Class<T> type = (Class<T>) example.getClass();

        return (Specification<T>) (root, query, criteriaBuilder) -> {
            ArrayList predicates = new ArrayList();

            EntityType<T> entity = entityManager.getMetamodel().entity(type);

            for (Attribute<T, ?> attr : entity.getDeclaredAttributes()) {
                Object attrValue = getValue(example, attr);

                if (attrValue != null) {
                    if (attr.getJavaType() == String.class) {
                        if (!StringUtils.isEmpty(attrValue)) {
                            predicates.add(criteriaBuilder.like(root.get(attribute(entity, attr.getName(), String.class)), pattern((String) attrValue)));
                        } else {
                            predicates.add(criteriaBuilder.equal(root.get(attribute(entity, attr.getName(), attrValue.getClass())), attrValue));
                        }
                    }
                }
            }

            Predicate[] pp = (Predicate[]) predicates.toArray();
            return predicates.isEmpty() ? criteriaBuilder.conjunction() : criteriaBuilder.and(pp);
        };
    }
}
