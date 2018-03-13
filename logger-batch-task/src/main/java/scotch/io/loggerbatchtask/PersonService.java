package scotch.io.loggerbatchtask;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.activation.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

@Service
public class PersonService {

  private static final Log logger = LogFactory.getLog(LoggerJobConfiguration.class);	
	
	 public List<Person> getAllPerson(JdbcTemplate jdbcTemplate){
		    return jdbcTemplate.query("SELECT * FROM person", new RowMapper<Person>(){

		      public Person mapRow(ResultSet rs, int arg1) throws SQLException {
		        Person p = new Person();
		        p.setFirstName(rs.getString("first_name"));
		        p.setLastName(rs.getString("last_name"));
		        return p;
		      }
		    });
	 }
	 
	public void insert(Person customer, JdbcTemplate jdbcTemplate){
	
		String sql = "INSERT INTO person " +
				"(first_name, last_name) VALUES (?, ?)";
	
		try {
			logger.info("Inside .......PersonService ........."+jdbcTemplate.getDataSource().getConnection());
			if(customer != null) {
				jdbcTemplate.update(
						sql,
					    customer.getFirstName(), customer.getLastName()
					);
			}
	
		} catch (SQLException e) {
			throw new RuntimeException(e);
	
		} finally {
		}
	}
	 
	 
}