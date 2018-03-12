package scotch.io.springcloudschedulerpayloadinitiator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

  @Autowired
  private JdbcTemplate jdbcTemplate;

	 public List<Person> getAllPerson(){
		    return jdbcTemplate.query("SELECT * FROM person", new RowMapper<Person>(){

		      public Person mapRow(ResultSet rs, int arg1) throws SQLException {
		        Person p = new Person();
		        p.setFirstName(rs.getString("first_name"));
		        p.setLastName(rs.getString("last_name"));
		        return p;
		      }
		    });
	 }
	 
}