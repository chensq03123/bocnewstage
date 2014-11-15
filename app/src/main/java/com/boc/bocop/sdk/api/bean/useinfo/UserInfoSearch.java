package com.boc.bocop.sdk.api.bean.useinfo;

import java.util.List;

import com.boc.bocop.sdk.api.bean.ResponseBean;

/**
 * @author fww5205 用户资料查询结果返回实体类
 */
public class UserInfoSearch extends ResponseBean {

	private long pageno = 0;
	private int record_count = 0;
	private List<UserInfo> users;

	/**
	 * @return 返回 页码 000001 000002 000003…终端进行累加,返回时以000000作为结束标识
	 */
	public long getPageno() {
		return pageno;
	}

	public void setPageno(long pageno) {
		this.pageno = pageno;
	}

	/**
	 * @return 返回 笔数
	 */
	public int getRecord_count() {
		return record_count;
	}

	public void setRecord_count(int record_count) {
		this.record_count = record_count;
	}

	/**
	 * @return 当笔数大于O时，返回 多笔循环内容
	 */
	public List<UserInfo> getUsers() {
		return users;
	}

	public void setUsers(List<UserInfo> users) {
		this.users = users;
	}

}
