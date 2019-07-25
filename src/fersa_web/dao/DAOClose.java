package fersa_web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DAOClose {
    /*private ResultSet resultSet;
    private Connection connection;
    private PreparedStatement preparedStatement;

    public DAOClose(ResultSet resultSet, Connection connection, PreparedStatement preparedStatement){
        this.connection = connection;
        this.preparedStatement = preparedStatement;
        this.resultSet = resultSet;

    }*/

    public void close(ResultSet resultSet, Connection connection, PreparedStatement preparedStatement){
        if (resultSet != null){
            try {
                resultSet.close();

            } catch (Exception rse) {
                rse.printStackTrace();
            }
        }
        try {
            preparedStatement.close();
        } catch (Exception pse){
            pse.printStackTrace();
        }
        try {
            connection.close();
        } catch (Exception ce){
            ce.printStackTrace();
        }
    }

}
