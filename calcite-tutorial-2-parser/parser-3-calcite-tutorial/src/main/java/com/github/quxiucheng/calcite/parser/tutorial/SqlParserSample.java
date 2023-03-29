package com.github.quxiucheng.calcite.parser.tutorial;

import org.apache.calcite.config.Lex;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.dialect.HiveSqlDialect;
import org.apache.calcite.sql.dialect.MysqlSqlDialect;
import org.apache.calcite.sql.dialect.OracleSqlDialect;
import org.apache.calcite.sql.dialect.PrestoSqlDialect;
import org.apache.calcite.sql.parser.SqlParseException;
import org.apache.calcite.sql.parser.SqlParser;

/**
 * @author quxiucheng
 * @date 2019-04-22 11:45:00
 */
public class SqlParserSample {

    public static void main(String[] args) throws SqlParseException {
        parserSql();
        parserExp();

    }

    /**
     * 解析SQL
     * @throws SqlParseException
     */
    public static void parserSql() throws SqlParseException {
        // 解析配置 - mysql设置
        SqlParser.Config mysqlConfig = SqlParser.configBuilder().setLex(Lex.ORACLE).build();
        // 创建解析器
        SqlParser parser = SqlParser.create("", SqlParser.Config.DEFAULT);
        // Sql语句
        String sql = "select   SUBSTRING(emps.qtzc, 2) from emps where id = 1";
        // 解析sql
        SqlNode sqlNode = parser.parseQuery(sql);
        System.out.println(sqlNode.toSqlString(MysqlSqlDialect.DEFAULT));
        // 还原某个方言的SQL
        System.out.println(sqlNode.toSqlString(OracleSqlDialect.DEFAULT));
        System.out.println(sqlNode.toSqlString(PrestoSqlDialect.DEFAULT));
    }

    /**
     * 解析表达式
     * @throws SqlParseException
     */
    public static void parserExp() throws SqlParseException {
        // 解析配置 - mysql设置
        SqlParser.Config mysqlConfig = SqlParser.configBuilder().setLex(Lex.MYSQL).build();
        String exp = "id = 1 and name='1'";
        SqlParser expressionParser = SqlParser.create(exp, mysqlConfig);
        // Sql语句
        // 解析sql
        SqlNode sqlNode = expressionParser.parseExpression();
        // 还原某个方言的SQL
        System.out.println(sqlNode.toSqlString(HiveSqlDialect.DEFAULT));
        System.out.println(sqlNode.toSqlString(OracleSqlDialect.DEFAULT));
    }







}
