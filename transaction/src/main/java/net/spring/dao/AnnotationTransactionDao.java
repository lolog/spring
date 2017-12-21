package net.spring.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.spring.pojo.Users;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.transaction.annotation.Transactional;

public class AnnotationTransactionDao {
	static String _sql0 = "insert into users (name, age, remark) values (?, ?, ?)";
	static String _sql1 = "insert into users (id, name, age, remark) values (?, ?, ?, ?)";
	static String _sql2 = "select * from users where id = ?";
	
	private JdbcTemplate jdbcTemplate;
	
	@Transactional(rollbackFor={Exception.class})
	public Users annotationTransaction() {
		jdbcTemplate.execute(_sql0, new PreparedStatementCallback<Users>() {
			public Users doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(1, "_name_0");
				ps.setInt(2, 20);
				ps.setString(3, "_remark_0");
				ps.execute();
				
				return new Users("_name_0", 21, "_remark_0");
			}
		});
		
		jdbcTemplate.execute(_sql1, new PreparedStatementCallback<Users>() {
			public Users doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setInt(1, 2);
				ps.setString(2, "_name_1");
				ps.setInt(3, 20);
				ps.setString(4, "_remark_1");
				ps.execute();
				
				return new Users(1, "_name_1", 21, "_remark_1");
			}
		});
		
		Users users = jdbcTemplate.query(_sql2, new Object[] {1}, new ResultSetExtractor<Users>() {
			public Users extractData(ResultSet rs) throws SQLException, DataAccessException {
				Users users = new Users();
				if (rs.next()) {
					users.setId(rs.getInt("id"));
					users.setName(rs.getString("name"));
					users.setAge(rs.getInt("age"));
					users.setRemark("remark");
				}
				return users;
			}
		});
		return users;
	}
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
