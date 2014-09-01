package com.genfu.reform.jpa;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import javax.sql.DataSource;

public class GenfuAuthenticationImpl {

	// private JdbcTemplate jdbaTemplate;
	private DataSource pg_dataSource;

	public DataSource getPg_dataSource() {
		return pg_dataSource;
	}

	public void setPg_dataSource(DataSource pg_dataSource) {
		this.pg_dataSource = pg_dataSource;
	}

	public boolean verify(String actionName, String nameSpace, String method,
			String operate, long userId) {

		// this.jdbaTemplate = new JdbcTemplate(pg_dataSource);
		StringBuffer sqlString = new StringBuffer();

		// jdbaTemplate.queryForList(sqlString.toString(), args,
		// NavigationNode.class);

		sqlString
				.append("select nn.navi_id from  user_info ui,user_info_role_infos ur,");
		sqlString.append("role_info_navigation_nodes rn,navigation_nodes nn");
		sqlString.append(" where nn.navi_action = ? and nn.navi_operate = ?");
		sqlString.append(" and nn.navi_exp_date > ? and nn.navi_eff_date < ?");
		sqlString
				.append(" and ui.user_id = ? and ui.user_flag = ? and ui.user_exp_date > ?");
		sqlString
				.append(" and ui.user_eff_date < ? and ui.user_id=ur.user_user_id");
		sqlString
				.append(" and ur.role_role_id=rn.role_role_id and nn.navi_id=rn.node_navi_id");
		try {
			PreparedStatement pStatement = pg_dataSource.getConnection()
					.prepareStatement(sqlString.toString());

			// Query query = em.createNativeQuery(sqlString.toString(), "nn1");
			pStatement.setString(1, actionName);
			pStatement.setString(2, method);
			pStatement.setDate(3, new java.sql.Date(new Date().getTime()));
			pStatement.setDate(4, new java.sql.Date(new Date().getTime()));
			pStatement.setLong(5, userId);
			pStatement.setString(6, "OPEN");
			pStatement.setDate(7, new java.sql.Date(new Date().getTime()));
			pStatement.setDate(8, new java.sql.Date(new Date().getTime()));

			// query.getParameters();

			if (pStatement.executeQuery().next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

}
