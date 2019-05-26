package my.restful.restfulwebservices.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import my.restful.restfulwebservices.model.Cubical;
import my.restful.restfulwebservices.model.ODC;

@RestController
public class FilterController {

	@GetMapping("/filter-cubical")
	public Cubical staticFilterExample() {
		return new Cubical(1,"team", 12, 12);
	}
	
	@GetMapping("/filter-cubical-list")
	public List<Cubical> staticFilterListExample() {
		return Arrays.asList(new Cubical(1,"team", 12, 12), new Cubical(2,"team", 22,22));
	}
	
	@GetMapping("/filter-odc")
	public MappingJacksonValue dynamicFilterExample() {
		ODC userODC = new ODC(1,"First", "First Project ODC");
		MappingJacksonValue mapping = new MappingJacksonValue(userODC);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","name");
		FilterProvider filters = new SimpleFilterProvider().addFilter("odcFilter", filter);
		// Put @JsonFilter("odcFilter") on ODC class
		mapping.setFilters(filters);
		return mapping;
	}
}
