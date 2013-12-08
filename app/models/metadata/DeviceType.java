package models.metadata;

import java.util.*;

import util.APICall;

//import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.*;

public class DeviceType {
	
	private String id;
	private String deviceTypeName;
	private String manufacturer;
	private double version;

	// http://einstein.sv.cmu.edu/get_devices/json
	private static final String API_CALL = util.Constants.API_URL + util.Constants.GET_DEVICE_TYPES + util.Constants.FORMAT;
	private static final String GET_DEVICE_TYPES_CALL =  util.Constants.API_URL + util.Constants.GET_DEVICE_TYPES + util.Constants.FORMAT;
	private static final String ADD_DEVICE_TYPE_CALL = util.Constants.API_URL + util.Constants.ADD_DEVICE_TYPE;
	// Constructors
	
	public DeviceType() {
	}
	
	public DeviceType(String id, String deviceTypeName, String manufacturer,
			double version) {
		super();
		this.id = id;
		this.deviceTypeName = deviceTypeName;
		this.manufacturer = manufacturer;
		this.version = version;
	}

	public static JsonNode create(JsonNode jsonData) {
		String tmpTest = "http://einstein.sv.cmu.edu/add_device_type";
		return APICall.postAPI(ADD_DEVICE_TYPE_CALL, jsonData);	
	}

	public static void delete(String id) {
	}
	
	/**
	 * Method to display all devices
	 * @return List<Device> List of all devices
	 */
	public static List<DeviceType> all() {
		
		List<DeviceType> allDeviceTypes = new ArrayList<DeviceType>();

		// API Call: http://einstein.sv.cmu.edu/get_device_types/json
		JsonNode deviceTypesNode = APICall.callAPI(GET_DEVICE_TYPES_CALL);

		if(deviceTypesNode == null)
			return allDeviceTypes;
		
		//id, name, manufacturer, version
		for (int i=0;i<deviceTypesNode.size();i++) {
			 JsonNode json = deviceTypesNode.path(i);
			 DeviceType newDeviceType = new DeviceType();
			 newDeviceType.setId(json.findPath("device_type_key").asText());
			 newDeviceType.setDeviceTypeName(json.findPath("device_type_name").asText());
			 newDeviceType.setManufacturer(json.findPath("manufacturer").asText());
			 newDeviceType.setVersion(json.findPath("version").asDouble());
			 allDeviceTypes.add(newDeviceType);
		}
					
		return allDeviceTypes;

	}
	
	// Getters
	public String getDeviceTypeName() {
		return deviceTypeName;
	}	
	
	// Setters
	public void setId(String id) {
		this.id = id;
	}

	public void setDeviceTypeName(String deviceTypeName) {
		this.deviceTypeName = deviceTypeName;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public double getVersion() {
		return version;
	}

	public void setVersion(double version) {
		this.version = version;
	}

	public String getId() {
		return id;
	}

}
