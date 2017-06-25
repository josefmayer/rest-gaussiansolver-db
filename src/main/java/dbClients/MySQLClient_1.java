package dbClients;
import java.sql.*;

/**
 * Created by Josef Mayer on 23.06.2017.
 */
public class MySQLClient_1 {
    String url = "jdbc:mysql://localhost:3306/gaussian";
    String user = "root";
    String password = "root";

    public void createTable() {
        String query = "CREATE TABLE gaussian1 (id int not null primary key auto_increment, requestTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP, document varchar(255), result varchar(255));";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(query);){
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void insert(String indocument, String result) {
        String query = "INSERT INTO `gaussian1` (`id`, `requestTime`, `document`,  `result`) VALUES (NULL , NULL , ? , ?);";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(query);){

            ps.setString(1, indocument);
            ps.setString(2, result);
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public StringBuffer getAll() {
        String query = "SELECT id, requestTime, document, result FROM gaussian1";
        StringBuffer sb = new StringBuffer();

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(query);){

            while (rs.next()){
                sb.append("\"id\" : ");
                sb.append(rs.getInt("id"));
                sb.append("\n");
                sb.append("\"reqestTime\" : ");
                sb.append(rs.getTimestamp("requestTime"));
                sb.append("\n");
                sb.append("\"document\" : ");
                sb.append(rs.getString("document"));
                sb.append("\n");
                sb.append("\"result\" : ");
                sb.append(rs.getString("result"));
                sb.append("\n\n");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return sb;
    }


}
