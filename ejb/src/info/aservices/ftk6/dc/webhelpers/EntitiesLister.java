package info.aservices.ftk6.dc.webhelpers;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
    public <T1> int getPagesCount(Class entityClass, int itemsPerPage) {
        String tableName = entityClass.getName();
        Long count = (Long)em.createQuery("SELECT COUNT(alias) FROM " + tableName + " alias").getSingleResult();
        int pagesCount = (int) (count / itemsPerPage);
        pagesCount += (count % itemsPerPage != 0) ? 1 : 0;
        return pagesCount;
    }

    @Override
    public <T1> List<T1> getList(Class entityClass, int page, int itemsPerPage) {
        String tableName = entityClass.getName();

        MyQueryBuilder mQB = new MyQueryBuilder(tableName);
        String query = mQB.getQuery();

        List<T1> result = new ArrayList<>();
        Query createdQuery = em.createQuery(query);

        createdQuery.setFirstResult((page-1) * itemsPerPage).setMaxResults(itemsPerPage);
        List preResult = createdQuery.getResultList();

        for(Object o: preResult) {
            //noinspection unchecked
            result.add((T1)o);
        }
        return result;
    }

    private void setLikeParameterIfValueIsNotEmpty(Query createdQuery, String param, String value) {
        if (param.length() != 0) {
            createdQuery.setParameter(param, value + "%");
        }
    }
    
    @Override
    public <T1> List<T1> getSearchList(Class entityClass, String firstName, String lastName, String patronymicName) {
        String tableName = entityClass.getName();

        
        MyQueryBuilder mQB = new MyQueryBuilder(tableName);
        
        List<Expression> expressions = new ArrayList<>();
        expressions.add(new ILikeEqualityCondition("lastName"));
        expressions.add(new ILikeEqualityCondition("firstName"));
        expressions.add(new ILikeEqualityCondition("patronymicName"));
        mQB.addWhereClauseDisjunctiveConditions(expressions);
        
        String query = mQB.getQuery();
        
        List<T1> result = new ArrayList<>();
        Query createdQuery = em.createQuery(query);
        setLikeParameterIfValueIsNotEmpty(createdQuery, "lastName", lastName);
        setLikeParameterIfValueIsNotEmpty(createdQuery, "firstName", lastName);
        setLikeParameterIfValueIsNotEmpty(createdQuery, "patronymicName", lastName);
        List preResult = createdQuery.getResultList();
        
        for(Object o: preResult) {
            //noinspection unchecked
            result.add((T1)o);
        }
        return result;
    }
}
