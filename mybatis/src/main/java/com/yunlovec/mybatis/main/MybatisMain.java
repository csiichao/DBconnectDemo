package com.yunlovec.mybatis.main;

import java.util.Date;

import org.apache.ibatis.session.SqlSession;

import com.yunlovec.mybatis.mapper.SysRoleMapper;
import com.yunlovec.mybatis.po.SysRole;
import com.yunlovec.mybatis.util.SqlSessionFactoryUtil;

public class MybatisMain {
	public static void main(String[] args) {
		SqlSession sqlSession = null;
		try {
			sqlSession = SqlSessionFactoryUtil.openSqlSession();
			SysRoleMapper sysRoleMapper = sqlSession.getMapper(SysRoleMapper.class);
			SysRole sysRole = new SysRole();
			sysRole.setId("3");
			sysRole.setRole_name("testName");
			sysRole.setEnabled(1);
			sysRole.setCreate_by(1);
			sysRole.setCreate_time(new Date());
			sysRoleMapper.insertRole(sysRole);
			sysRoleMapper.deleteRole(3L);
			sqlSession.commit();
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			sqlSession.rollback();
		}finally {
			if(sqlSession != null) {
				sqlSession.close();
			}
		}
	}
}
