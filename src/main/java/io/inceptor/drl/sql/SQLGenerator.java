package io.inceptor.drl.sql;

import io.inceptor.drl.drl.condition.inner.InnerCondition;
import io.inceptor.drl.util.orm.JdbcResolvedClass;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.dialect.Dialect;
import org.hibernate.sql.Delete;
import org.hibernate.sql.Insert;
import org.hibernate.sql.Select;
import org.hibernate.sql.Update;
import org.mvel2.integration.VariableResolverFactory;

import java.util.List;

public class SQLGenerator {

//    private SQLGeneratorParam sqlGeneratorParam;

    private Dialect dialect;

    public SQLGenerator(Dialect dialect) {
        this.dialect = dialect;
    }

    public String generateInsertSql(JdbcResolvedClass c) {
        Insert insert = new Insert(dialect);
        insert.setTableName(c.getTableName());
        insert.addColumns(getColumns(c));
        return insert.toStatementString();
    }


    public String generateUpdateByIdSql(JdbcResolvedClass c) {
        Update update = new Update(dialect);
        update.setTableName(c.getTableName());
        update.setPrimaryKeyColumnNames(new String[]{c.getId()});
        update.addColumns(getColumns(c));
        return update.toStatementString();

    }

    public String generateUpdateByKeysSql(JdbcResolvedClass c) {
        Update update = new Update(dialect);
        update.setTableName(c.getTableName());
        update.setPrimaryKeyColumnNames(getKeys(c));
        update.addColumns(getColumns(c));
        return update.toStatementString();
    }

    public String generateDeleteSql(JdbcResolvedClass c) {
        Delete delete = new Delete();
        delete.setTableName(c.getTableName());
        delete.addPrimaryKeyColumns(getColumns(c));
        return delete.toStatementString();
    }

    public String generateDeleteByIdSql(JdbcResolvedClass c) {
        Delete delete = new Delete();
        delete.setTableName(c.getTableName());
        delete.addPrimaryKeyColumns(new String[]{c.getId()});
        return delete.toStatementString();
    }

    public String generateDeleteByKeysSql(JdbcResolvedClass c) {
        Delete delete = new Delete();
        delete.setTableName(c.getTableName());
        delete.addPrimaryKeyColumns(getKeys(c));
        return delete.toStatementString();
    }

    public String generateSelectSql(List<InnerCondition> conditions, JdbcResolvedClass c) {
        if (StringUtils.isNotBlank(c.getSelect()))
            return c.getSelect();
        Select select = new Select(dialect);
        select.setSelectClause("*");
        if (conditions != null) {
            String whereClause = conditions.stream().map(condition -> condition.getSql()).reduce((s1, s2) -> s1 + s2).get();
            select.setWhereClause(whereClause);
        }
        select.setFromClause(c.getTableName());
        return select.toStatementString();
    }

    public String generateSelectSql(List<InnerCondition> conditions, JdbcResolvedClass c, VariableResolverFactory variableResolverFactory) {
        if (StringUtils.isNotBlank(c.getSelect()))
            return c.getSelect();
        Select select = new Select(dialect);
        select.setSelectClause("*");
        if (conditions != null) {
            String whereClause = conditions.stream().map(condition -> condition.getSql(variableResolverFactory)).reduce((s1, s2) -> s1 + s2).get();
            select.setWhereClause(whereClause);
        }
        select.setFromClause(c.getTableName());
        return select.toStatementString();
    }

    private String[] getKeys(JdbcResolvedClass c) {
        List<String> keys = c.getKeys();
        return keys.toArray(new String[keys.size()]);
    }

    private String[] getColumns(JdbcResolvedClass c) {
        List<String> columns = c.getColumns();
        return columns.toArray(new String[columns.size()]);
    }

}
