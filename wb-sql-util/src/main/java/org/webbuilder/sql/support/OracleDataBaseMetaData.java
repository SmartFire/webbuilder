package org.webbuilder.sql.support;

import org.webbuilder.sql.DataBaseMetaData;
import org.webbuilder.sql.TableMetaData;
import org.webbuilder.sql.exception.SqlRenderException;
import org.webbuilder.sql.keywords.KeywordsMapper;
import org.webbuilder.sql.keywords.dialect.oracle.OracleKeywordsMapper;
import org.webbuilder.sql.render.template.SqlRenderParam;
import org.webbuilder.sql.render.template.SqlTemplate;
import org.webbuilder.sql.render.template.SqlTemplateRender;
import org.webbuilder.sql.support.common.CommonSqlTemplateRender;
import org.webbuilder.sql.support.common.oracle.OracleTableAlterRender;
import org.webbuilder.sql.support.common.oracle.OracleTableCreateRender;

/**
 * ORACLE 数据库支持
 * Created by 浩 on 2015-11-17 0017.
 */
public class OracleDataBaseMetaData extends DataBaseMetaData {

    //初始化sql模板渲染器,使用通用的sql模板,另外提供ORACLE专用的表结构处理模板渲染器
    protected SqlTemplateRender sqlTemplateRender = new CommonSqlTemplateRender() {
        @Override
        public void init(TableMetaData tableMetaData) {
            super.init(tableMetaData);
            cacheTemplate(tableMetaData.getName(), new OracleTableCreateRender(tableMetaData));

            cacheTemplate(tableMetaData.getName(), new OracleTableAlterRender(tableMetaData));
        }
    };
    //关键字映射器
    protected KeywordsMapper keywordsMapper = new OracleKeywordsMapper();

    protected String name = "orcl";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public SqlTemplateRender getRender() {
        return sqlTemplateRender;
    }

    @Override
    public KeywordsMapper getKeywordsMapper() {
        return keywordsMapper;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setKeywordsMapper(KeywordsMapper keywordsMapper) {
        this.keywordsMapper = keywordsMapper;
    }

    public void setSqlTemplateRender(SqlTemplateRender sqlTemplateRender) {
        this.sqlTemplateRender = sqlTemplateRender;
    }
}

