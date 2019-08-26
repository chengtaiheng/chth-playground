package chth.playground.front.datasource;

/**
 * @author: 程泰恒
 * @date: 2019/6/6 11:53
 */

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;

public class ForkDataSource extends AbstractRoutingDataSource implements DataSource {

    public static final String DEFAULT = "default";

    public static final String SPARE = "spare";

    private static final ThreadLocal<String> DATA_SOURCE_KEY_HOLDER = new InheritableThreadLocal<>();

    public static void setDataSourceKey(String dataSource) {
        ForkDataSource.DATA_SOURCE_KEY_HOLDER.set(dataSource);
    }

    public static void toDefault() {
        ForkDataSource.DATA_SOURCE_KEY_HOLDER.remove();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return ForkDataSource.DATA_SOURCE_KEY_HOLDER.get();
    }
}

