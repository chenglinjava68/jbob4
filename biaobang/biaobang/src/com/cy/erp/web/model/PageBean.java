package com.cy.erp.web.model;

public class PageBean {
	public int allPageCount = 0;// �ܵ�ҳ��
	public int currPageIndex = 0;// ��ǰҳ�����
	public int rowCount = 20;// ÿҳ������
	public int recordCount;// ��¼����
	public String field = "";// ������ֶ���
	public int desc = 0;// ����ʽ��0������1��С����2�Ӵ�С

	public int getAllPageCount() {
		allPageCount = (int) Math.floor(this.recordCount / this.rowCount);
		if (this.recordCount % this.rowCount != 0) {
			allPageCount++;
		}
		return allPageCount;
	}

	public void setAllPageCount(int allPageCount) {
		this.allPageCount = allPageCount;
	}

	public int getCurrPageIndex() {
		return currPageIndex;
	}

	public void setCurrPageIndex(int currPageIndex) {
		this.currPageIndex = currPageIndex;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public int getDesc() {
		return desc;
	}

	public void setDesc(int desc) {
		this.desc = desc;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
}
