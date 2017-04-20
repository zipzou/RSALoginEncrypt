package site.franksite.encrypt.entity;

public class ResultEntity {

	
	private boolean status;
	
	private String reason;
	
	private Object data;

	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

	public ResultEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResultEntity(boolean status, String reason, Object data) {
		super();
		this.status = status;
		this.reason = reason;
		this.data = data;
	}
	
	
	
}
