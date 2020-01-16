/**
 * Copyright (c) 2015-2020, Michael Yang 杨福海 (fuhai999@gmail.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.jboot.db.dialect;

import com.jfinal.plugin.activerecord.dialect.Sqlite3Dialect;
import io.jboot.db.model.Column;
import io.jboot.db.model.SqlBuilderUtil;
import io.jboot.db.model.Join;

import java.util.List;


public class JbootSqlite3Dialect extends Sqlite3Dialect implements JbootDialect {


    @Override
    public String forFindByColumns(List<Join> joins, String table, String loadColumns, List<Column> columns, String orderBy, Object limit) {
        StringBuilder sqlBuilder = SqlBuilderUtil.forFindByColumns(joins, table, loadColumns, columns, orderBy, ' ');

        if (limit != null) {
            sqlBuilder.append(" LIMIT " + limit);
        }

        return sqlBuilder.toString();
    }

    @Override
    public String forFindCountByColumns(String table, List<Column> columns) {
        return SqlBuilderUtil.forFindCountByColumns(table, columns, ' ');
    }

    @Override
    public String forDeleteByColumns(String table, List<Column> columns) {
        return SqlBuilderUtil.forDeleteByColumns(table, columns, ' ');
    }


    @Override
    public String forPaginateSelect(String loadColumns) {
        return "SELECT " + loadColumns;
    }


    @Override
    public String forPaginateFrom(List<Join> joins, String table, List<Column> columns, String orderBy) {
        return SqlBuilderUtil.forPaginateFrom(joins, table, columns, orderBy, ' ');
    }


}
