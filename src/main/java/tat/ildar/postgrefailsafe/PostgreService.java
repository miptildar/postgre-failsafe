package tat.ildar.postgrefailsafe;

import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.SQLException;

@Service
public class PostgreService {

    private final DataSource dataSource;

    public PostgreService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void validateConnection() throws SQLException {
        dataSource.getConnection().close();
    }
}
