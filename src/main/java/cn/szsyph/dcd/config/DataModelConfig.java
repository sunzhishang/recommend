package cn.szsyph.dcd.config;

import cn.szsyph.dcd.constant.Constant;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.model.DataModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.beans.PropertyVetoException;

@Configuration
public class DataModelConfig {

    @Value("${jdbc.driver}")
    private String jdbcDriver;

    @Value("${jdbc.url}")
    private String jdbcUrl;

    @Value("${jdbc.username}")
    private String jdbcUsername;

    @Value("${jdbc.password}")
    private String jdbcPassword;

    @Value("${jdbc.database-name}")
    private String jdbcDatabaseName;

    @Bean(name = "dataModel")
    @ConditionalOnMissingBean(DataModel.class)
    public DataModel stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws PropertyVetoException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(jdbcDriver);
        comboPooledDataSource.setJdbcUrl(jdbcUrl);
        comboPooledDataSource.setUser(jdbcUsername);
        comboPooledDataSource.setPassword(jdbcPassword);
        //关闭连接后不自动commit
        comboPooledDataSource.setAutoCommitOnClose(false);
        return new MySQLJDBCDataModel(comboPooledDataSource, Constant.USER_GOODS_PREFERENCE_TABLE,Constant.USER_ID_COLUMN,Constant.ARTICLE_ID_COLUMN,Constant.USER_ARTICLE_PREFERENCE_COLUMN,null);
    }
}
