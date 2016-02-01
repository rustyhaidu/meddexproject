package ro.sci.group5.domain;

import java.util.*;

public class Hospital extends AbstractModel {
	private String hospitalName;
	private String streetName;
	private int streetNumber;
	private String neighbourhood;
	private String phoneNumber;
	private String hospitalEmail;
	private ArrayList<Doctor> listOfDoctors;
	
	
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public int getStreetNumber() {
		return streetNumber;
	}
	public void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}
	public String getNeighbourhood() {
		return neighbourhood;
	}
	public void setNeighbourhood(String neighbourhood) {
		this.neighbourhood = neighbourhood;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getHospitalEmail() {
		return hospitalEmail;
	}
	public void setHospitalEmail(String hospitalEmail) {
		this.hospitalEmail = hospitalEmail;
	}
	public ArrayList<Doctor> getListOfDoctors() {
		return listOfDoctors;
	}
	public void setListOfDoctors(ArrayList<Doctor> listOfDoctors) {
		this.listOfDoctors = listOfDoctors;
	}
	
	public Hospital(long id) {
		setId(id);
	}

	public Hospital() {
		this(0);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		
		result = prime * result + ((hospitalName == null) ? 0 : hospitalName.hashCode());
		result = prime * result + ((streetName == null) ? 0 : streetName.hashCode());
		result = prime * result + ((neighbourhood == null) ? 0 : neighbourhood.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((hospitalEmail == null) ? 0 : hospitalEmail.hashCode());	
		result = prime * result + ((listOfDoctors == null) ? 0 : listOfDoctors.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hospital other = (Hospital) obj;
		
		if (hospitalName == null) {
			if (other.hospitalName != null)
				return false;
		} else if (!hospitalName.equals(other.hospitalName))
			return false;
		
		
		if (streetName == null) {
			if (other.streetName != null)
				return false;
		} else if (!streetName.equals(other.streetName))
			return false;
		
		if (neighbourhood == null) {
			if (other.neighbourhood != null)
				return false;
		} else if (!neighbourhood.equals(other.neighbourhood))
			return false;
		
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		
		if (hospitalEmail == null) {
			if (other.hospitalEmail != null)
				return false;
		} else if (!hospitalEmail.equals(other.hospitalEmail))
			return false;
		
		if (listOfDoctors == null) {
			if (other.listOfDoctors != null)
				return false;
		} else if (!listOfDoctors.equals(other.listOfDoctors))
			return false;
		
		
		return true;
	}

	


}
