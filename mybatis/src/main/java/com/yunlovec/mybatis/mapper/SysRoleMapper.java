package com.yunlovec.mybatis.mapper;

import com.yunlovec.mybatis.po.SysRole;

public interface SysRoleMapper {
	public SysRole getRole(Long id);
	public int deleteRole(Long id);
	public int insertRole(SysRole role);
}
