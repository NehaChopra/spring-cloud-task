package scotch.io.loggerbatchtask;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.activation.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

@Service
public class PersonService {

  private static final Log logger = LogFactory.getLog(LoggerJobConfiguration.class);	
	
  @Autowired
  private JdbcTemplate jdbcTemplate;	

	 public List<Person> getAllPerson(){
		    return jdbcTemplate.query("SELECT * FROM person", new RowMapper<Person>(){

		      public Person mapRow(ResultSet rs, int arg1) throws SQLException {
		    	logger.info("Inside .......getAllPerson PersonService ........."+jdbcTemplate.getDataSource());  
		        Person p = new Person();
		        p.setFirstName(rs.getString("first_name"));
		        p.setLastName(rs.getString("last_name"));
		        return p;
		      }
		    });
	 }
	 
	public void insert(Person customer){
	
		String sql = "INSERT INTO person " +
				"(first_name, last_name) VALUES (?, ?)";
	
		try {
			if(customer != null) {
				logger.info("Inside insert statement.......PersonService ........."+jdbcTemplate.getDataSource());
				jdbcTemplate.update(
						sql,
					    customer.getFirstName(), customer.getLastName()
					);
			}
	
		} finally {
		}
	}
	 
	 
}
