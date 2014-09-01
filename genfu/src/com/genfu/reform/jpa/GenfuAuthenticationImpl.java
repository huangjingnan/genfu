package com.genfu.reform.jpa;

import java.sql.PreparedStatement;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.genfu.reform.model.NavigationNode;

public class GenfuAuthenticationImpl {

	private JdbcTemplate jdbaTemplate;
	private DataSource pg_dataSource;

	public DataSource getPg_dataSource() {
		return pg_dataSource;
	}

	public void setPg_dataSource(DataSource pg_dataSource) {
		this.pg_dataSource = pg_dataSource;
	}

	public boolean verify(String actionName, String nameSpace, String method,
			String operate, long userId) {

		this.jdbaTemplate = new JdbcTemplate(pg_dataSource);
		StringBuffer sqlString = new StringBuffer();
		
		jdbaTemplate.queryForList(sqlString.toString(), args, NavigationNode.class);

		sqlString
				.append("select nn.navi_id from  user_info ui,user_info_role_infos ur,");
		sqlString.append("role_info_navigation_nodes rn,navigation_nodes nn");
		sqlString
				.append("where nn.navi_action = :_navi_action0 and nn.navi_operate = :_navi_operate0");
		sqlString
				.append(" and nn.navi_exp_date > :_navi_exp_date0 and nn.navi_eff_date < :_navi_eff_date0");
		sqlString
				.append(" and ui.user_id = :_user_id0 and ui.user_flag = :_user_flag0 and ui.user_exp_date > :_user_exp_date0");
		sqlString
				.append(" and ui.user_eff_date < :_user_eff_date0 and ui.user_id=ur.user_user_id");
		sqlString
				.append(" and ur.role_role_id=rn.role_role_id and nn.navi_id=rn.node_navi_id");

		PreparedStatement pStatement = pg_dataSource.getConnection().prepareStatement(sqlString.toString());
		
		pStatement.
		
		//Query query = em.createNativeQuery(sqlString.toString(), "nn1");
		query.setParameter("_navi_action0", actionName);
		query.setParameter("_navi_operate0", method);
		query.setParameter("_user_flag0", "OPEN");
		query.setParameter("_user_flag0", "OPEN");
		query.setParameter("_navi_exp_date0", new Date());
		query.setParameter("_navi_eff_date0", new Date());
		query.setParameter("_user_exp_date0", new Date());
		query.setParameter("_user_eff_date0", new Date());
		query.setParameter("_user_id0", userId);

		// query.getParameters();
		if (query.getResultList().size() > 0) {
			return true;
		}

		return false;
	}

}
