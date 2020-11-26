package in.inceptor.drl;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Druid {
    public static void main(String[] args) throws Exception{
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/drools?characterEncoding=UTF-8");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("password");

        druidDataSource.init();
        DruidPooledConnection druidPooledConnection = druidDataSource.getConnection();
        PreparedStatement preparedStatement = druidPooledConnection.prepareStatement("show tables;");
        ResultSet resultSet = preparedStatement.executeQuery();
//        druidPooledConnection.close();

        while (resultSet.next()){
            Object c = resultSet.getObject(1);
            int i = 3;
        }
        druidPooledConnection.close();


        return;
    }
}
