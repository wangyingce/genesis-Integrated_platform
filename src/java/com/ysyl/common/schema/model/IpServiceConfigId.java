package com.ysyl.common.schema.model;


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 */
@Embeddable
public class IpServiceConfigId implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private String serverCode;

	private String environmentCode;

	/**
	 */
	public IpServiceConfigId() {
	}

	/**       
	 */

	@Column(name = "servercode")
	public String getServerCode() {
		return this.serverCode;
	}

	/**       
	 */
	public void setServerCode(String serverCode) {
		this.serverCode = serverCode;
	}

	/**       
	 */

	@Column(name = "environmentcode")
	public String getEnvironmentCode() {
		return this.environmentCode;
	}

	/**       
	 */
	public void setEnvironmentCode(String environmentCode) {
		this.environmentCode = environmentCode;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof IpServiceConfigId))
			return false;
		IpServiceConfigId castOther = (IpServiceConfigId) other;

		return ((this.getServerCode() == castOther.getServerCode()) || (this
				.getServerCode() != null
				&& castOther.getServerCode() != null && this.getServerCode()
				.equals(castOther.getServerCode())))
				&& ((this.getEnvironmentCode() == castOther
						.getEnvironmentCode()) || (this.getEnvironmentCode() != null
						&& castOther.getEnvironmentCode() != null && this
						.getEnvironmentCode().equals(
								castOther.getEnvironmentCode())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getServerCode() == null ? 0 : this.getServerCode()
						.hashCode());
		result = 37
				* result
				+ (getEnvironmentCode() == null ? 0 : this.getEnvironmentCode()
						.hashCode());
		return result;
	}

}
