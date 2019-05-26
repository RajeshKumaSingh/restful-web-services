package my.restful.restfulwebservices.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value= {"mouseAssestNumber"})  // Not to be returned in response - static filter
public class Cubical {

	private Integer id;
	
	private String type;
	
	@JsonIgnore  // Not to be returned in response - static filter
	private Integer pcAssestNumber;
	
	private Integer mouseAssestNumber;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getPcAssestNumber() {
		return pcAssestNumber;
	}
	public void setPcAssestNumber(Integer pcAssestNumber) {
		this.pcAssestNumber = pcAssestNumber;
	}
	public Integer getMouseAssestNumber() {
		return mouseAssestNumber;
	}
	public void setMouseAssestNumber(Integer mouseAssestNumber) {
		this.mouseAssestNumber = mouseAssestNumber;
	}
	public Cubical(Integer id, String type, Integer pcAssestNumber, Integer mouseAssestNumber) {
		super();
		this.id = id;
		this.type = type;
		this.pcAssestNumber = pcAssestNumber;
		this.mouseAssestNumber = mouseAssestNumber;
	}
	
	
}
