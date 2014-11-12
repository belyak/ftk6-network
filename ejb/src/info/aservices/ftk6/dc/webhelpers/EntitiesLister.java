package info.aservices.ftk6.dc.webhelpers;

import info.aservices.ftk6.dc.entities.Person;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

interface Expression {
    public String getExpression(String entityAlias);
}

class ILikeEqualityCondition implements Expression {
    private final String attributeName;
   
    public ILikeEqualityCondition(String attributeName) {
        this.attributeName = attributeName;
    }
    
    @Override
    public String getExpression(String entityAlias) {
        String result;
        String fullAttributeName = entityAlias + "." + attributeName;
        result = " lower(" + fullAttributeName + ") like lower(:" + attributeName + ") ";
        return result;
    }
}

class MyQueryBuilder {
    String entityName, entityAlias;
    List<Expression> whereDisjunctiveConditions;
    boolean hasWhereClause;
    
    public MyQueryBuilder(String entityName) {
        this.entityName = entityName;
        this.entityAlias = entityName.substring(0, 1).toLowerCase();
        this.whereDisjunctiveConditions = null;
        this.hasWhereClause = false;
    }
    
    public void addWhereClauseDisjunctiveConditions(List<Expression> expressions) {
        this.whereDisjunctiveConditions = expressions;
        if (!expressions.isEmpty()) {
            hasWhereClause = true;
        }
    }
    
    public String getQuery() {
        String mainQuery = "SELECT " + entityAlias + " FROM " + entityName + " " + entityAlias;
        String whereClause;
        if (hasWhereClause) {
            StringBuilder sb = new StringBuilder();
            for (Expression e: whereDisjunctiveConditions) {
                if (sb.length() != 0) {
                    sb.append(" OR ");
                }
                sb.append(e.getExpression(entityAlias));
            }
            whereClause = " WHERE " + sb.toString();
        } else {
            whereClause = "";
        }
        System.out.println(mainQuery + whereClause);
        return mainQuery + whereClause;
    }
    
}
@Stateless
public class EntitiesLister implements EntitiesListerRemote {
    @PersistenceContext(unitName = "ESystem-ejbPU")
    private EntityManager em;
    
    @Override
    public <T1> List<T1> getList(Class entityClass) {
        String tableName = entityClass.getName();

        
        MyQueryBuilder mQB = new MyQueryBuilder(tableName);
        String query = mQB.getQuery();
        List<T1> result = new ArrayList<>();
        List preResult = em.createQuery(query).getResultList();
        for(Object o: preResult) {
            //noinspection unchecked
            result.add((T1)o);
        }
        return result;
    }

    @Override
    public int getPagesCount(Class entityClass, int itemsPerPage) {
        String tableName = entityClass.getName();
        Long count = (Long)em.createQuery("SELECT COUNT(alias) FROM " + tableName + " alias").getSingleResult();
        int pagesCount = (int) (count / itemsPerPage);
        pagesCount += (count % itemsPerPage != 0) ? 1 : 0;
        return pagesCount;
    }

    @Override
    public <T1> List<T1> getList(Class<T1> entityClass, int page, int itemsPerPage) {
        String tableName = entityClass.getName();

        MyQueryBuilder mQB = new MyQueryBuilder(tableName);
        String query = mQB.getQuery();

        final TypedQuery<T1> createdQuery = em.createQuery(query, entityClass);

        createdQuery.setFirstResult((page-1) * itemsPerPage).setMaxResults(itemsPerPage);
        return createdQuery.getResultList();
    }
    
    @Override
    public Collection<Person> getPersonsFilteredList(String firstName, String lastName, String patronymicName) {
        String tableName = Person.class.getName();

        
        MyQueryBuilder mQB = new MyQueryBuilder(tableName);
        
        List<Expression> expressions = new ArrayList<>();
        final String fieldNames[] = {"lastName", "firstName", "patronymicName"};
        final String fieldValues[] = {lastName, firstName, patronymicName};

        for (int i = 0; i < 3; i++) {
            if (!fieldValues[i].isEmpty()) {
                expressions.add(new ILikeEqualityCondition(fieldNames[i]));
            }
        }

        mQB.addWhereClauseDisjunctiveConditions(expressions);
        
        String query = mQB.getQuery();

        final TypedQuery<Person> createdQuery = em.createQuery(query, Person.class);
        for (int i = 0; i < 3; i++) {
            if (!fieldValues[i].isEmpty()) {
                createdQuery.setParameter(fieldNames[i], fieldValues[i]);
            }
        }

        return createdQuery.getResultList();
    }
}
