package com.cy.erp.web.model;

import java.io.Serializable;

public class LoginData implements Serializable {
	private static final long serialVersionUID = -2407572876245484007L;
	
	public String loginId;//��¼�ʺ�

	public String loginPwd;//��¼����

	public String roleName;//��ɫ����
	
	public String roleCode;//��ɫ����
	
	public String organizationScope;//������֯��Χ

	public String funcScope;//�˵���Դ
	
	public String localHostIp;//����IP

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getOrganizationScope() {
		return organizationScope;
	}

	public void setOrganizationScope(String organizationScope) {
		this.organizationScope = organizationScope;
	}

	public String getFuncScope() {
		return funcScope;
	}

	public void setFuncScope(String funcScope) {
		this.funcScope = funcScope;
	}

	public String getLocalHostIp() {
		return localHostIp;
	}

	public void setLocalHostIp(String localHostIp) {
		this.localHostIp = localHostIp;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	

}
