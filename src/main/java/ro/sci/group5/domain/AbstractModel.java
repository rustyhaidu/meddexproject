package ro.sci.group5.domain;

/**
 * Base class for ID field.
 *
 */
public abstract class AbstractModel {
	private long id;

	/**
	 * Id Getter
	 * 
	 * @return id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Id setter
	 * 
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Hash Code method
	 * 
	 * @return int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	/**
	 * Equals method
	 * 
	 * @param obj
	 * @return boolean
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractModel other = (AbstractModel) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
