package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import com.yunlovec.simple.mapper.SysRoleMapper;
import com.yunlovec.simple.po.SysRole;


public class RoleMapperTest extends BaseMapperTest {

	@Test
	public void testSelectById(){
		//获取 sqlSession
		SqlSession sqlSession = getSqlSession();
		try {
			//获取 RoleMapper 接口
			SysRoleMapper roleMapper = sqlSession.getMapper(SysRoleMapper.class);
			//调用 selectById 方法，查询 id = 1 的角色
			SysRole role = roleMapper.getRole(1l);
			//role 不为空
			Assert.assertNotNull(role);
			//roleName = 管理员
			Assert.assertEquals("管理员", role.getRole_name());
		} finally {
			//不要忘记关闭 sqlSession
			sqlSession.close();
		}
	}
}
